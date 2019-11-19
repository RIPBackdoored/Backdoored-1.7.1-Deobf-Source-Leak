package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.util.GenericData;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.webtoken.JsonWebToken;
import java.util.List;
import com.google.api.client.util.Key;
import com.google.api.client.util.Beta;
import com.google.api.client.auth.openidconnect.IdToken;

@Beta
public static class Payload extends IdToken.Payload
{
    @Key("hd")
    private String hostedDomain;
    @Key("email")
    private String email;
    @Key("email_verified")
    private Object emailVerified;
    
    public Payload() {
        super();
    }
    
    @Deprecated
    @Deprecated
    public String getUserId() {
        return this.getSubject();
    }
    
    @Deprecated
    @Deprecated
    public Payload setUserId(final String subject) {
        return this.setSubject(subject);
    }
    
    @Deprecated
    @Deprecated
    public String getIssuee() {
        return this.getAuthorizedParty();
    }
    
    @Deprecated
    @Deprecated
    public Payload setIssuee(final String authorizedParty) {
        return this.setAuthorizedParty(authorizedParty);
    }
    
    public String getHostedDomain() {
        return this.hostedDomain;
    }
    
    public Payload setHostedDomain(final String hostedDomain) {
        this.hostedDomain = hostedDomain;
        return this;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public Payload setEmail(final String email) {
        this.email = email;
        return this;
    }
    
    public Boolean getEmailVerified() {
        if (this.emailVerified == null) {
            return null;
        }
        if (this.emailVerified instanceof Boolean) {
            return (Boolean)this.emailVerified;
        }
        return Boolean.valueOf((String)this.emailVerified);
    }
    
    public Payload setEmailVerified(final Boolean emailVerified) {
        this.emailVerified = emailVerified;
        return this;
    }
    
    @Override
    public Payload setAuthorizationTimeSeconds(final Long authorizationTimeSeconds) {
        return (Payload)super.setAuthorizationTimeSeconds(authorizationTimeSeconds);
    }
    
    @Override
    public Payload setAuthorizedParty(final String authorizedParty) {
        return (Payload)super.setAuthorizedParty(authorizedParty);
    }
    
    @Override
    public Payload setNonce(final String nonce) {
        return (Payload)super.setNonce(nonce);
    }
    
    @Override
    public Payload setAccessTokenHash(final String accessTokenHash) {
        return (Payload)super.setAccessTokenHash(accessTokenHash);
    }
    
    @Override
    public Payload setClassReference(final String classReference) {
        return (Payload)super.setClassReference(classReference);
    }
    
    @Override
    public Payload setMethodsReferences(final List<String> methodsReferences) {
        return (Payload)super.setMethodsReferences(methodsReferences);
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
    public /* bridge */ IdToken.Payload clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ IdToken.Payload set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ IdToken.Payload setSubject(final String subject) {
        return this.setSubject(subject);
    }
    
    @Override
    public /* bridge */ IdToken.Payload setType(final String type) {
        return this.setType(type);
    }
    
    @Override
    public /* bridge */ IdToken.Payload setJwtId(final String jwtId) {
        return this.setJwtId(jwtId);
    }
    
    @Override
    public /* bridge */ IdToken.Payload setAudience(final Object audience) {
        return this.setAudience(audience);
    }
    
    @Override
    public /* bridge */ IdToken.Payload setIssuer(final String issuer) {
        return this.setIssuer(issuer);
    }
    
    @Override
    public /* bridge */ IdToken.Payload setIssuedAtTimeSeconds(final Long issuedAtTimeSeconds) {
        return this.setIssuedAtTimeSeconds(issuedAtTimeSeconds);
    }
    
    @Override
    public /* bridge */ IdToken.Payload setNotBeforeTimeSeconds(final Long notBeforeTimeSeconds) {
        return this.setNotBeforeTimeSeconds(notBeforeTimeSeconds);
    }
    
    @Override
    public /* bridge */ IdToken.Payload setExpirationTimeSeconds(final Long expirationTimeSeconds) {
        return this.setExpirationTimeSeconds(expirationTimeSeconds);
    }
    
    @Override
    public /* bridge */ IdToken.Payload setMethodsReferences(final List methodsReferences) {
        return this.setMethodsReferences(methodsReferences);
    }
    
    @Override
    public /* bridge */ IdToken.Payload setClassReference(final String classReference) {
        return this.setClassReference(classReference);
    }
    
    @Override
    public /* bridge */ IdToken.Payload setAccessTokenHash(final String accessTokenHash) {
        return this.setAccessTokenHash(accessTokenHash);
    }
    
    @Override
    public /* bridge */ IdToken.Payload setNonce(final String nonce) {
        return this.setNonce(nonce);
    }
    
    @Override
    public /* bridge */ IdToken.Payload setAuthorizedParty(final String authorizedParty) {
        return this.setAuthorizedParty(authorizedParty);
    }
    
    @Override
    public /* bridge */ IdToken.Payload setAuthorizationTimeSeconds(final Long authorizationTimeSeconds) {
        return this.setAuthorizationTimeSeconds(authorizationTimeSeconds);
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
