package com.google.api.client.googleapis.auth.oauth2;

import java.util.regex.Matcher;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.json.JsonParser;
import com.google.api.client.http.HttpResponse;
import java.security.cert.CertificateFactory;
import java.util.Collections;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import com.google.api.client.util.StringUtils;
import java.security.cert.X509Certificate;
import com.google.api.client.util.Preconditions;
import com.google.api.client.json.JsonToken;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.SecurityUtils;
import java.util.ArrayList;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.locks.ReentrantLock;
import com.google.api.client.util.Clock;
import java.util.concurrent.locks.Lock;
import com.google.api.client.http.HttpTransport;
import java.security.PublicKey;
import java.util.List;
import com.google.api.client.json.JsonFactory;
import java.util.regex.Pattern;
import com.google.api.client.util.Beta;

@Beta
public class GooglePublicKeysManager
{
    private static final long REFRESH_SKEW_MILLIS = 300000L;
    private static final Pattern MAX_AGE_PATTERN;
    private final JsonFactory jsonFactory;
    private List<PublicKey> publicKeys;
    private long expirationTimeMilliseconds;
    private final HttpTransport transport;
    private final Lock lock;
    private final Clock clock;
    private final String publicCertsEncodedUrl;
    
    public GooglePublicKeysManager(final HttpTransport httpTransport, final JsonFactory jsonFactory) {
        this(new Builder(httpTransport, jsonFactory));
    }
    
    protected GooglePublicKeysManager(final Builder builder) {
        super();
        this.lock = new ReentrantLock();
        this.transport = builder.transport;
        this.jsonFactory = builder.jsonFactory;
        this.clock = builder.clock;
        this.publicCertsEncodedUrl = builder.publicCertsEncodedUrl;
    }
    
    public final HttpTransport getTransport() {
        return this.transport;
    }
    
    public final JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }
    
    public final String getPublicCertsEncodedUrl() {
        return this.publicCertsEncodedUrl;
    }
    
    public final Clock getClock() {
        return this.clock;
    }
    
    public final List<PublicKey> getPublicKeys() throws GeneralSecurityException, IOException {
        this.lock.lock();
        try {
            if (this.publicKeys == null || this.clock.currentTimeMillis() + 300000L > this.expirationTimeMilliseconds) {
                this.refresh();
            }
            return this.publicKeys;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public final long getExpirationTimeMilliseconds() {
        return this.expirationTimeMilliseconds;
    }
    
    public GooglePublicKeysManager refresh() throws GeneralSecurityException, IOException {
        this.lock.lock();
        try {
            this.publicKeys = new ArrayList<PublicKey>();
            final CertificateFactory x509CertificateFactory = SecurityUtils.getX509CertificateFactory();
            final HttpResponse execute = this.transport.createRequestFactory().buildGetRequest(new GenericUrl(this.publicCertsEncodedUrl)).execute();
            this.expirationTimeMilliseconds = this.clock.currentTimeMillis() + this.getCacheTimeInSec(execute.getHeaders()) * 1000L;
            final JsonParser jsonParser = this.jsonFactory.createJsonParser(execute.getContent());
            JsonToken jsonToken = jsonParser.getCurrentToken();
            if (jsonToken == null) {
                jsonToken = jsonParser.nextToken();
            }
            Preconditions.checkArgument(jsonToken == JsonToken.START_OBJECT);
            try {
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    jsonParser.nextToken();
                    this.publicKeys.add(((X509Certificate)x509CertificateFactory.generateCertificate(new ByteArrayInputStream(StringUtils.getBytesUtf8(jsonParser.getText())))).getPublicKey());
                }
                this.publicKeys = Collections.<PublicKey>unmodifiableList((List<? extends PublicKey>)this.publicKeys);
            }
            finally {
                jsonParser.close();
            }
            return this;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    long getCacheTimeInSec(final HttpHeaders httpHeaders) {
        long longValue = 0L;
        if (httpHeaders.getCacheControl() != null) {
            final String[] split = httpHeaders.getCacheControl().split(",");
            for (int length = split.length, i = 0; i < length; ++i) {
                final Matcher matcher = GooglePublicKeysManager.MAX_AGE_PATTERN.matcher(split[i]);
                if (matcher.matches()) {
                    longValue = Long.valueOf(matcher.group(1));
                    break;
                }
            }
        }
        if (httpHeaders.getAge() != null) {
            longValue -= httpHeaders.getAge();
        }
        return Math.max(0L, longValue);
    }
    
    static {
        MAX_AGE_PATTERN = Pattern.compile("\\s*max-age\\s*=\\s*(\\d+)\\s*");
    }
}
