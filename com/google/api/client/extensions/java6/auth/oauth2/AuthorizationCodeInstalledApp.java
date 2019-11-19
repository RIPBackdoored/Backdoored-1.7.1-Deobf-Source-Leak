package com.google.api.client.extensions.java6.auth.oauth2;

import java.io.PrintStream;
import java.util.logging.Level;
import java.net.URI;
import java.awt.Desktop;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import java.io.IOException;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.util.Preconditions;
import java.util.logging.Logger;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;

public class AuthorizationCodeInstalledApp
{
    private final AuthorizationCodeFlow flow;
    private final VerificationCodeReceiver receiver;
    private static final Logger LOGGER;
    
    public AuthorizationCodeInstalledApp(final AuthorizationCodeFlow authorizationCodeFlow, final VerificationCodeReceiver verificationCodeReceiver) {
        super();
        this.flow = Preconditions.<AuthorizationCodeFlow>checkNotNull(authorizationCodeFlow);
        this.receiver = Preconditions.<VerificationCodeReceiver>checkNotNull(verificationCodeReceiver);
    }
    
    public Credential authorize(final String s) throws IOException {
        try {
            final Credential loadCredential = this.flow.loadCredential(s);
            if (loadCredential != null && (loadCredential.getRefreshToken() != null || loadCredential.getExpiresInSeconds() == null || loadCredential.getExpiresInSeconds() > 60L)) {
                return loadCredential;
            }
            final String redirectUri = this.receiver.getRedirectUri();
            this.onAuthorization(this.flow.newAuthorizationUrl().setRedirectUri(redirectUri));
            return this.flow.createAndStoreCredential(this.flow.newTokenRequest(this.receiver.waitForCode()).setRedirectUri(redirectUri).execute(), s);
        }
        finally {
            this.receiver.stop();
        }
    }
    
    protected void onAuthorization(final AuthorizationCodeRequestUrl authorizationCodeRequestUrl) throws IOException {
        browse(authorizationCodeRequestUrl.build());
    }
    
    public static void browse(final String s) {
        Preconditions.<String>checkNotNull(s);
        System.out.println("Please open the following address in your browser:");
        final PrintStream out = System.out;
        final String s2 = "  ";
        final String value = String.valueOf(s);
        out.println((value.length() != 0) ? s2.concat(value) : new String(s2));
        try {
            if (Desktop.isDesktopSupported()) {
                final Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    System.out.println("Attempting to open that address in the default browser now...");
                    desktop.browse(URI.create(s));
                }
            }
        }
        catch (IOException ex) {
            AuthorizationCodeInstalledApp.LOGGER.log(Level.WARNING, "Unable to open browser", ex);
        }
        catch (InternalError internalError) {
            AuthorizationCodeInstalledApp.LOGGER.log(Level.WARNING, "Unable to open browser", internalError);
        }
    }
    
    public final AuthorizationCodeFlow getFlow() {
        return this.flow;
    }
    
    public final VerificationCodeReceiver getReceiver() {
        return this.receiver;
    }
    
    static {
        LOGGER = Logger.getLogger(AuthorizationCodeInstalledApp.class.getName());
    }
}
