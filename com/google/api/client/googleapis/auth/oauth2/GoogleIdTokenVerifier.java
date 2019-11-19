package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.util.Clock;
import java.util.Collection;
import java.util.Arrays;
import com.google.api.client.util.Preconditions;
import java.util.Iterator;
import com.google.api.client.auth.openidconnect.IdToken;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.util.List;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Beta;
import com.google.api.client.auth.openidconnect.IdTokenVerifier;

@Beta
public class GoogleIdTokenVerifier extends IdTokenVerifier
{
    private final GooglePublicKeysManager publicKeys;
    
    public GoogleIdTokenVerifier(final HttpTransport httpTransport, final JsonFactory jsonFactory) {
        this(new Builder(httpTransport, jsonFactory));
    }
    
    public GoogleIdTokenVerifier(final GooglePublicKeysManager googlePublicKeysManager) {
        this(new Builder(googlePublicKeysManager));
    }
    
    protected GoogleIdTokenVerifier(final Builder builder) {
        super(builder);
        this.publicKeys = builder.publicKeys;
    }
    
    public final GooglePublicKeysManager getPublicKeysManager() {
        return this.publicKeys;
    }
    
    public final HttpTransport getTransport() {
        return this.publicKeys.getTransport();
    }
    
    public final JsonFactory getJsonFactory() {
        return this.publicKeys.getJsonFactory();
    }
    
    @Deprecated
    @Deprecated
    public final String getPublicCertsEncodedUrl() {
        return this.publicKeys.getPublicCertsEncodedUrl();
    }
    
    @Deprecated
    @Deprecated
    public final List<PublicKey> getPublicKeys() throws GeneralSecurityException, IOException {
        return this.publicKeys.getPublicKeys();
    }
    
    @Deprecated
    @Deprecated
    public final long getExpirationTimeMilliseconds() {
        return this.publicKeys.getExpirationTimeMilliseconds();
    }
    
    public boolean verify(final GoogleIdToken googleIdToken) throws GeneralSecurityException, IOException {
        if (!super.verify(googleIdToken)) {
            return false;
        }
        final Iterator<PublicKey> iterator = this.publicKeys.getPublicKeys().iterator();
        while (iterator.hasNext()) {
            if (googleIdToken.verifySignature(iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    public GoogleIdToken verify(final String s) throws GeneralSecurityException, IOException {
        final GoogleIdToken parse = GoogleIdToken.parse(this.getJsonFactory(), s);
        return this.verify(parse) ? parse : null;
    }
    
    @Deprecated
    @Deprecated
    public GoogleIdTokenVerifier loadPublicCerts() throws GeneralSecurityException, IOException {
        this.publicKeys.refresh();
        return this;
    }
}
