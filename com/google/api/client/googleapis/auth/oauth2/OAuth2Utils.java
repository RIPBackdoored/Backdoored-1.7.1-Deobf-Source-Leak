package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.net.SocketTimeoutException;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import java.util.Iterator;
import java.util.Collection;
import com.google.api.client.http.HttpHeaders;
import java.util.logging.Logger;
import java.nio.charset.Charset;
import com.google.api.client.util.Beta;

@Beta
public class OAuth2Utils
{
    static final Charset UTF_8;
    private static final Logger LOGGER;
    private static final String DEFAULT_METADATA_SERVER_URL = "http://169.254.169.254";
    private static final int MAX_COMPUTE_PING_TRIES = 3;
    private static final int COMPUTE_PING_CONNECTION_TIMEOUT_MS = 500;
    
    public OAuth2Utils() {
        super();
    }
    
    static <T extends Throwable> T exceptionWithCause(final T t, final Throwable t2) {
        t.initCause(t2);
        return t;
    }
    
    static boolean headersContainValue(final HttpHeaders httpHeaders, final String s, final String s2) {
        final Object value = httpHeaders.get(s);
        if (value instanceof Collection) {
            for (final String next : (Collection<Object>)value) {
                if (next instanceof String && next.equals(s2)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    static boolean runningOnComputeEngine(final HttpTransport httpTransport, final SystemEnvironmentProvider systemEnvironmentProvider) {
        if (Boolean.parseBoolean(systemEnvironmentProvider.getEnv("NO_GCE_CHECK"))) {
            return false;
        }
        final GenericUrl genericUrl = new GenericUrl(getMetadataServerUrl(systemEnvironmentProvider));
        for (int i = 1; i <= 3; ++i) {
            try {
                final HttpRequest buildGetRequest = httpTransport.createRequestFactory().buildGetRequest(genericUrl);
                buildGetRequest.setConnectTimeout(500);
                final HttpResponse execute = buildGetRequest.execute();
                try {
                    return headersContainValue(execute.getHeaders(), "Metadata-Flavor", "Google");
                }
                finally {
                    execute.disconnect();
                }
            }
            catch (SocketTimeoutException ex2) {}
            catch (IOException ex) {
                OAuth2Utils.LOGGER.log(Level.WARNING, "Failed to detect whether we are running on Google Compute Engine.", ex);
            }
        }
        return false;
    }
    
    public static String getMetadataServerUrl() {
        return getMetadataServerUrl(SystemEnvironmentProvider.INSTANCE);
    }
    
    static String getMetadataServerUrl(final SystemEnvironmentProvider systemEnvironmentProvider) {
        final String env = systemEnvironmentProvider.getEnv("GCE_METADATA_HOST");
        if (env != null) {
            final String s = "http://";
            final String value = String.valueOf(env);
            return (value.length() != 0) ? s.concat(value) : new String(s);
        }
        return "http://169.254.169.254";
    }
    
    static {
        UTF_8 = Charset.forName("UTF-8");
        LOGGER = Logger.getLogger(OAuth2Utils.class.getName());
    }
}
