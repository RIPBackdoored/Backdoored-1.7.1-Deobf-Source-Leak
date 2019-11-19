package com.google.api.client.auth.oauth2;

import com.google.api.client.util.Lists;
import com.google.api.client.http.GenericUrl;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import com.google.api.client.util.Objects;
import com.google.api.client.http.HttpResponse;
import java.io.IOException;
import com.google.api.client.http.HttpRequest;
import java.util.Collections;
import com.google.api.client.util.Preconditions;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Collection;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Clock;
import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpExecuteInterceptor;

public class Credential implements HttpExecuteInterceptor, HttpRequestInitializer, HttpUnsuccessfulResponseHandler
{
    static final Logger LOGGER;
    private final Lock lock;
    private final AccessMethod method;
    private final Clock clock;
    private String accessToken;
    private Long expirationTimeMilliseconds;
    private String refreshToken;
    private final HttpTransport transport;
    private final HttpExecuteInterceptor clientAuthentication;
    private final JsonFactory jsonFactory;
    private final String tokenServerEncodedUrl;
    private final Collection<CredentialRefreshListener> refreshListeners;
    private final HttpRequestInitializer requestInitializer;
    
    public Credential(final AccessMethod accessMethod) {
        this(new Builder(accessMethod));
    }
    
    protected Credential(final Builder builder) {
        super();
        this.lock = new ReentrantLock();
        this.method = Preconditions.<AccessMethod>checkNotNull(builder.method);
        this.transport = builder.transport;
        this.jsonFactory = builder.jsonFactory;
        this.tokenServerEncodedUrl = ((builder.tokenServerUrl == null) ? null : builder.tokenServerUrl.build());
        this.clientAuthentication = builder.clientAuthentication;
        this.requestInitializer = builder.requestInitializer;
        this.refreshListeners = Collections.<CredentialRefreshListener>unmodifiableCollection((Collection<? extends CredentialRefreshListener>)builder.refreshListeners);
        this.clock = Preconditions.<Clock>checkNotNull(builder.clock);
    }
    
    @Override
    public void intercept(final HttpRequest httpRequest) throws IOException {
        this.lock.lock();
        try {
            final Long expiresInSeconds = this.getExpiresInSeconds();
            if (this.accessToken == null || (expiresInSeconds != null && expiresInSeconds <= 60L)) {
                this.refreshToken();
                if (this.accessToken == null) {
                    return;
                }
            }
            this.method.intercept(httpRequest, this.accessToken);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public boolean handleResponse(final HttpRequest httpRequest, final HttpResponse httpResponse, final boolean b) {
        final List<String> authenticateAsList = httpResponse.getHeaders().getAuthenticateAsList();
        if (authenticateAsList != null) {
            for (final String s : authenticateAsList) {
                if (s.startsWith("Bearer ")) {
                    BearerToken.INVALID_TOKEN_ERROR.matcher(s).find();
                    break;
                }
            }
        }
        if (httpResponse.getStatusCode() == 401) {
            try {
                this.lock.lock();
                try {
                    return !Objects.equal(this.accessToken, this.method.getAccessTokenFromRequest(httpRequest)) || this.refreshToken();
                }
                finally {
                    this.lock.unlock();
                }
            }
            catch (IOException ex) {
                Credential.LOGGER.log(Level.SEVERE, "unable to refresh token", ex);
            }
        }
        return false;
    }
    
    @Override
    public void initialize(final HttpRequest httpRequest) throws IOException {
        httpRequest.setInterceptor(this);
        httpRequest.setUnsuccessfulResponseHandler(this);
    }
    
    public final String getAccessToken() {
        this.lock.lock();
        try {
            return this.accessToken;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public Credential setAccessToken(final String accessToken) {
        this.lock.lock();
        try {
            this.accessToken = accessToken;
        }
        finally {
            this.lock.unlock();
        }
        return this;
    }
    
    public final AccessMethod getMethod() {
        return this.method;
    }
    
    public final Clock getClock() {
        return this.clock;
    }
    
    public final HttpTransport getTransport() {
        return this.transport;
    }
    
    public final JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }
    
    public final String getTokenServerEncodedUrl() {
        return this.tokenServerEncodedUrl;
    }
    
    public final String getRefreshToken() {
        this.lock.lock();
        try {
            return this.refreshToken;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public Credential setRefreshToken(final String refreshToken) {
        this.lock.lock();
        try {
            if (refreshToken != null) {
                Preconditions.checkArgument(this.jsonFactory != null && this.transport != null && this.clientAuthentication != null && this.tokenServerEncodedUrl != null, (Object)"Please use the Builder and call setJsonFactory, setTransport, setClientAuthentication and setTokenServerUrl/setTokenServerEncodedUrl");
            }
            this.refreshToken = refreshToken;
        }
        finally {
            this.lock.unlock();
        }
        return this;
    }
    
    public final Long getExpirationTimeMilliseconds() {
        this.lock.lock();
        try {
            return this.expirationTimeMilliseconds;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public Credential setExpirationTimeMilliseconds(final Long expirationTimeMilliseconds) {
        this.lock.lock();
        try {
            this.expirationTimeMilliseconds = expirationTimeMilliseconds;
        }
        finally {
            this.lock.unlock();
        }
        return this;
    }
    
    public final Long getExpiresInSeconds() {
        this.lock.lock();
        try {
            if (this.expirationTimeMilliseconds == null) {
                return null;
            }
            return (this.expirationTimeMilliseconds - this.clock.currentTimeMillis()) / 1000L;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public Credential setExpiresInSeconds(final Long n) {
        return this.setExpirationTimeMilliseconds((n == null) ? null : Long.valueOf(this.clock.currentTimeMillis() + n * 1000L));
    }
    
    public final HttpExecuteInterceptor getClientAuthentication() {
        return this.clientAuthentication;
    }
    
    public final HttpRequestInitializer getRequestInitializer() {
        return this.requestInitializer;
    }
    
    public final boolean refreshToken() throws IOException {
        this.lock.lock();
        try {
            try {
                final TokenResponse executeRefreshToken = this.executeRefreshToken();
                if (executeRefreshToken != null) {
                    this.setFromTokenResponse(executeRefreshToken);
                    final Iterator<CredentialRefreshListener> iterator = this.refreshListeners.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onTokenResponse(this, executeRefreshToken);
                    }
                    return true;
                }
            }
            catch (TokenResponseException ex) {
                final boolean b = 400 <= ex.getStatusCode() && ex.getStatusCode() < 500;
                if (ex.getDetails() != null && b) {
                    this.setAccessToken(null);
                    this.setExpiresInSeconds(null);
                }
                final Iterator<CredentialRefreshListener> iterator2 = this.refreshListeners.iterator();
                while (iterator2.hasNext()) {
                    iterator2.next().onTokenErrorResponse(this, ex.getDetails());
                }
                if (b) {
                    throw ex;
                }
            }
            return false;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public Credential setFromTokenResponse(final TokenResponse tokenResponse) {
        this.setAccessToken(tokenResponse.getAccessToken());
        if (tokenResponse.getRefreshToken() != null) {
            this.setRefreshToken(tokenResponse.getRefreshToken());
        }
        this.setExpiresInSeconds(tokenResponse.getExpiresInSeconds());
        return this;
    }
    
    protected TokenResponse executeRefreshToken() throws IOException {
        if (this.refreshToken == null) {
            return null;
        }
        return new RefreshTokenRequest(this.transport, this.jsonFactory, new GenericUrl(this.tokenServerEncodedUrl), this.refreshToken).setClientAuthentication(this.clientAuthentication).setRequestInitializer(this.requestInitializer).execute();
    }
    
    public final Collection<CredentialRefreshListener> getRefreshListeners() {
        return this.refreshListeners;
    }
    
    static {
        LOGGER = Logger.getLogger(Credential.class.getName());
    }
}
