package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.util.GenericData;
import com.google.api.client.json.GenericJson;
import java.util.List;
import com.google.api.client.util.Key;
import java.security.GeneralSecurityException;
import java.io.IOException;
import com.google.api.client.json.webtoken.JsonWebToken;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Beta;
import com.google.api.client.auth.openidconnect.IdToken;

@Beta
public class GoogleIdToken extends IdToken
{
    public static GoogleIdToken parse(final JsonFactory jsonFactory, final String s) throws IOException {
        final JsonWebSignature parse = JsonWebSignature.parser(jsonFactory).setPayloadClass(Payload.class).parse(s);
        return new GoogleIdToken(parse.getHeader(), (Payload)parse.getPayload(), parse.getSignatureBytes(), parse.getSignedContentBytes());
    }
    
    public GoogleIdToken(final Header header, final Payload payload, final byte[] array, final byte[] array2) {
        super(header, payload, array, array2);
    }
    
    public boolean verify(final GoogleIdTokenVerifier googleIdTokenVerifier) throws GeneralSecurityException, IOException {
        return googleIdTokenVerifier.verify(this);
    }
    
    @Override
    public Payload getPayload() {
        return (Payload)super.getPayload();
    }
    
    @Override
    public /* bridge */ IdToken.Payload getPayload() {
        return this.getPayload();
    }
    
    @Override
    public /* bridge */ JsonWebToken.Payload getPayload() {
        return this.getPayload();
    }
}
