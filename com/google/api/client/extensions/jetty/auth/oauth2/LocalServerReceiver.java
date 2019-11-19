package com.google.api.client.extensions.jetty.auth.oauth2;

import java.io.PrintWriter;
import org.mortbay.jetty.Request;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.Connector;
import java.io.IOException;
import com.google.api.client.util.Throwables;
import org.mortbay.jetty.Handler;
import java.util.concurrent.Semaphore;
import org.mortbay.jetty.Server;
import com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver;

public final class LocalServerReceiver implements VerificationCodeReceiver
{
    private static final String LOCALHOST = "localhost";
    private static final String CALLBACK_PATH = "/Callback";
    private Server server;
    String code;
    String error;
    final Semaphore waitUnlessSignaled;
    private int port;
    private final String host;
    private final String callbackPath;
    private String successLandingPageUrl;
    private String failureLandingPageUrl;
    
    public LocalServerReceiver() {
        this("localhost", -1, "/Callback", null, null);
    }
    
    LocalServerReceiver(final String s, final int n, final String s2, final String s3) {
        this(s, n, "/Callback", s2, s3);
    }
    
    LocalServerReceiver(final String host, final int port, final String callbackPath, final String successLandingPageUrl, final String failureLandingPageUrl) {
        super();
        this.waitUnlessSignaled = new Semaphore(0);
        this.host = host;
        this.port = port;
        this.callbackPath = callbackPath;
        this.successLandingPageUrl = successLandingPageUrl;
        this.failureLandingPageUrl = failureLandingPageUrl;
    }
    
    public String getRedirectUri() throws IOException {
        this.server = new Server((this.port != -1) ? this.port : 0);
        final Connector connector = this.server.getConnectors()[0];
        connector.setHost(this.host);
        this.server.addHandler((Handler)new CallbackHandler());
        try {
            this.server.start();
            this.port = connector.getLocalPort();
        }
        catch (Exception ex) {
            Throwables.propagateIfPossible(ex);
            throw new IOException(ex);
        }
        final String value = String.valueOf(String.valueOf(this.host));
        final int port = this.port;
        final String value2 = String.valueOf(String.valueOf(this.callbackPath));
        return new StringBuilder(19 + value.length() + value2.length()).append("http://").append(value).append(":").append(port).append(value2).toString();
    }
    
    public String waitForCode() throws IOException {
        this.waitUnlessSignaled.acquireUninterruptibly();
        if (this.error != null) {
            final String value = String.valueOf(String.valueOf(this.error));
            throw new IOException(new StringBuilder(28 + value.length()).append("User authorization failed (").append(value).append(")").toString());
        }
        return this.code;
    }
    
    public void stop() throws IOException {
        this.waitUnlessSignaled.release();
        if (this.server != null) {
            try {
                this.server.stop();
            }
            catch (Exception ex) {
                Throwables.propagateIfPossible(ex);
                throw new IOException(ex);
            }
            this.server = null;
        }
    }
    
    public String getHost() {
        return this.host;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public String getCallbackPath() {
        return this.callbackPath;
    }
    
    static /* synthetic */ String access$000(final LocalServerReceiver localServerReceiver) {
        return localServerReceiver.successLandingPageUrl;
    }
    
    static /* synthetic */ String access$100(final LocalServerReceiver localServerReceiver) {
        return localServerReceiver.failureLandingPageUrl;
    }
}
