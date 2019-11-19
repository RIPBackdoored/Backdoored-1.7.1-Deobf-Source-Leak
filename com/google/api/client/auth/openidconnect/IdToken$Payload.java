package com.google.api.client.auth.openidconnect;

import com.google.api.client.util.GenericData;
import com.google.api.client.json.GenericJson;
import java.util.List;
import com.google.api.client.util.Key;
import com.google.api.client.util.Beta;
import com.google.api.client.json.webtoken.JsonWebToken;

@Beta
public static class Payload extends JsonWebToken.Payload
{
    @Key("auth_time")
    private Long authorizationTimeSeconds;
    @Key("azp")
    private String authorizedParty;
    @Key
    private String nonce;
    @Key("at_hash")
    private String accessTokenHash;
    @Key("acr")
    private String classReference;
    @Key("amr")
    private List<String> methodsReferences;
    
    public Payload() {
        super();
    }
    
    public final Long getAuthorizationTimeSeconds() {
        return this.authorizationTimeSeconds;
    }
    
    public Payload setAuthorizationTimeSeconds(final Long authorizationTimeSeconds) {
        this.authorizationTimeSeconds = authorizationTimeSeconds;
        return this;
    }
    
    public final String getAuthorizedParty() {
        return this.authorizedParty;
    }
    
    public Payload setAuthorizedParty(final String authorizedParty) {
        this.authorizedParty = authorizedParty;
        return this;
    }
    
    public final String getNonce() {
        return this.nonce;
    }
    
    public Payload setNonce(final String nonce) {
        this.nonce = nonce;
        return this;
    }
    
    public final String getAccessTokenHash() {
        return this.accessTokenHash;
    }
    
    public Payload setAccessTokenHash(final String accessTokenHash) {
        this.accessTokenHash = accessTokenHash;
        return this;
    }
    
    public final String getClassReference() {
        return this.classReference;
    }
    
    public Payload setClassReference(final String classReference) {
        this.classReference = classReference;
        return this;
    }
    
    public final List<String> getMethodsReferences() {
        return this.methodsReferences;
    }
    
    public Payload setMethodsReferences(final List<String> methodsReferences) {
        this.methodsReferences = methodsReferences;
        return this;
    }
    
    @Override
    public Payload setExpirationTimeSeconds(final Long expirationTimeSeconds) {
        return (Payload)super.setExpirationTimeSeconds(expirationTimeSeconds);
    }
    
    @Override
    public Payload setNotBeforeTimeSeconds(final Long notBeforeTimeSeconds) {
        return (Payload)super.setNotBeforeTimeSeconds(notBeforeTimeSeconds);
    }
    
    @Override
    public Payload setIssuedAtTimeSeconds(final Long issuedAtTimeSeconds) {
        return (Payload)super.setIssuedAtTimeSeconds(issuedAtTimeSeconds);
    }
    
    @Override
    public Payload setIssuer(final String issuer) {
        return (Payload)super.setIssuer(issuer);
    }
    
    @Override
    public Payload setAudience(final Object audience) {
        return (Payload)super.setAudience(audience);
    }
    
    @Override
    public Payload setJwtId(final String jwtId) {
        return (Payload)super.setJwtId(jwtId);
    }
    
    @Override
    public Payload setType(final String type) {
        return (Payload)super.setType(type);
    }
    
    @Override
    public Payload setSubject(final String subject) {
        return (Payload)super.setSubject(subject);
    }
    
    @Override
    public Payload set(final String s, final Object o) {
        return (Payload)super.set(s, o);
    }
    
    @Override
    public Payload clone() {
        return (Payload)super.clone();
    }
    
    @Override
    public /* bridge */ JsonWebToken.Payload clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ JsonWebToken.Payload set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ JsonWebToken.Payload setSubject(final String subject) {
        return this.setSubject(subject);
    }
    
    @Override
    public /* bridge */ JsonWebToken.Payload setType(final String type) {
        return this.setType(type);
    }
    
    @Override
    public /* bridge */ JsonWebToken.Payload setJwtId(final String jwtId) {
        return this.setJwtId(jwtId);
    }
    
    @Override
    public /* bridge */ JsonWebToken.Payload setAudience(final Object audience) {
        return this.setAudience(audience);
    }
    
    @Override
    public /* bridge */ JsonWebToken.Payload setIssuer(final String issuer) {
        return this.setIssuer(issuer);
    }
    
    @Override
    public /* bridge */ JsonWebToken.Payload setIssuedAtTimeSeconds(final Long issuedAtTimeSeconds) {
        return this.setIssuedAtTimeSeconds(issuedAtTimeSeconds);
    }
    
    @Override
    public /* bridge */ JsonWebToken.Payload setNotBeforeTimeSeconds(final Long notBeforeTimeSeconds) {
        return this.setNotBeforeTimeSeconds(notBeforeTimeSeconds);
    }
    
    @Override
    public /* bridge */ JsonWebToken.Payload setExpirationTimeSeconds(final Long expirationTimeSeconds) {
        return this.setExpirationTimeSeconds(expirationTimeSeconds);
    }
    
    @Override
    public /* bridge */ GenericJson set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ GenericJson clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ GenericData clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ GenericData set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    public /* bridge */ Object clone() throws CloneNotSupportedException {
        return this.clone();
    }
}
