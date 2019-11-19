package com.google.api.client.auth.openidconnect;

import com.google.api.client.util.GenericData;
import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import java.io.IOException;
import com.google.api.client.json.JsonFactory;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import com.google.api.client.json.webtoken.JsonWebToken;
import com.google.api.client.util.Beta;
import com.google.api.client.json.webtoken.JsonWebSignature;

@Beta
public class IdToken extends JsonWebSignature
{
    public IdToken(final Header header, final Payload payload, final byte[] array, final byte[] array2) {
        super(header, payload, array, array2);
    }
    
    @Override
    public Payload getPayload() {
        return (Payload)super.getPayload();
    }
    
    public final boolean verifyIssuer(final String s) {
        return this.verifyIssuer(Collections.<String>singleton(s));
    }
    
    public final boolean verifyIssuer(final Collection<String> collection) {
        return collection.contains(this.getPayload().getIssuer());
    }
    
    public final boolean verifyAudience(final Collection<String> collection) {
        final List<String> audienceAsList = this.getPayload().getAudienceAsList();
        return !audienceAsList.isEmpty() && collection.containsAll(audienceAsList);
    }
    
    public final boolean verifyTime(final long n, final long n2) {
        return this.verifyExpirationTime(n, n2) && this.verifyIssuedAtTime(n, n2);
    }
    
    public final boolean verifyExpirationTime(final long n, final long n2) {
        return n <= (this.getPayload().getExpirationTimeSeconds() + n2) * 1000L;
    }
    
    public final boolean verifyIssuedAtTime(final long n, final long n2) {
        return n >= (this.getPayload().getIssuedAtTimeSeconds() - n2) * 1000L;
    }
    
    public static IdToken parse(final JsonFactory jsonFactory, final String s) throws IOException {
        final JsonWebSignature parse = JsonWebSignature.parser(jsonFactory).setPayloadClass(Payload.class).parse(s);
        return new IdToken(parse.getHeader(), (Payload)parse.getPayload(), parse.getSignatureBytes(), parse.getSignedContentBytes());
    }
    
    @Override
    public /* bridge */ JsonWebToken.Payload getPayload() {
        return this.getPayload();
    }
}
