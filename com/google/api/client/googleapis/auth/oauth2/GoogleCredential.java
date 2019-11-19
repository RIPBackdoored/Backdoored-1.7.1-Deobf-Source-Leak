package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.http.HttpRequestInitializer;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.File;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.util.Clock;
import com.google.api.client.auth.oauth2.BearerToken;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import com.google.api.client.util.SecurityUtils;
import java.security.spec.PKCS8EncodedKeySpec;
import java.io.Reader;
import com.google.api.client.util.PemReader;
import java.io.StringReader;
import java.security.GeneralSecurityException;
import com.google.api.client.auth.oauth2.TokenRequest;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Joiner;
import com.google.api.client.json.webtoken.JsonWebToken;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.auth.oauth2.TokenResponse;
import java.util.Collections;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.GenericJson;
import java.io.InputStream;
import com.google.api.client.util.Preconditions;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;
import java.io.IOException;
import com.google.api.client.googleapis.util.Utils;
import java.security.PrivateKey;
import java.util.Collection;
import com.google.api.client.util.Beta;
import com.google.api.client.auth.oauth2.Credential;

public class GoogleCredential extends Credential
{
    static final String USER_FILE_TYPE = "authorized_user";
    static final String SERVICE_ACCOUNT_FILE_TYPE = "service_account";
    @Beta
    private static DefaultCredentialProvider defaultCredentialProvider;
    private String serviceAccountId;
    private String serviceAccountProjectId;
    private Collection<String> serviceAccountScopes;
    private PrivateKey serviceAccountPrivateKey;
    private String serviceAccountPrivateKeyId;
    private String serviceAccountUser;
    
    @Beta
    @Beta
    public static GoogleCredential getApplicationDefault() throws IOException {
        return getApplicationDefault(Utils.getDefaultTransport(), Utils.getDefaultJsonFactory());
    }
    
    @Beta
    @Beta
    public static GoogleCredential getApplicationDefault(final HttpTransport httpTransport, final JsonFactory jsonFactory) throws IOException {
        Preconditions.<HttpTransport>checkNotNull(httpTransport);
        Preconditions.<JsonFactory>checkNotNull(jsonFactory);
        return GoogleCredential.defaultCredentialProvider.getDefaultCredential(httpTransport, jsonFactory);
    }
    
    @Beta
    @Beta
    public static GoogleCredential fromStream(final InputStream inputStream) throws IOException {
        return fromStream(inputStream, Utils.getDefaultTransport(), Utils.getDefaultJsonFactory());
    }
    
    @Beta
    @Beta
    public static GoogleCredential fromStream(final InputStream inputStream, final HttpTransport httpTransport, final JsonFactory jsonFactory) throws IOException {
        Preconditions.<InputStream>checkNotNull(inputStream);
        Preconditions.<HttpTransport>checkNotNull(httpTransport);
        Preconditions.<JsonFactory>checkNotNull(jsonFactory);
        final GenericJson genericJson = new JsonObjectParser(jsonFactory).<GenericJson>parseAndClose(inputStream, OAuth2Utils.UTF_8, GenericJson.class);
        final String s = (String)genericJson.get("type");
        if (s == null) {
            throw new IOException("Error reading credentials from stream, 'type' field not specified.");
        }
        if ("authorized_user".equals(s)) {
            return fromStreamUser(genericJson, httpTransport, jsonFactory);
        }
        if ("service_account".equals(s)) {
            return fromStreamServiceAccount(genericJson, httpTransport, jsonFactory);
        }
        throw new IOException(String.format("Error reading credentials from stream, 'type' value '%s' not recognized. Expecting '%s' or '%s'.", s, "authorized_user", "service_account"));
    }
    
    public GoogleCredential() {
        this(new Builder());
    }
    
    protected GoogleCredential(final Builder builder) {
        super(builder);
        if (builder.serviceAccountPrivateKey == null) {
            Preconditions.checkArgument(builder.serviceAccountId == null && builder.serviceAccountScopes == null && builder.serviceAccountUser == null);
        }
        else {
            this.serviceAccountId = Preconditions.<String>checkNotNull(builder.serviceAccountId);
            this.serviceAccountProjectId = builder.serviceAccountProjectId;
            this.serviceAccountScopes = (Collection<String>)((builder.serviceAccountScopes == null) ? Collections.<Object>emptyList() : Collections.<Object>unmodifiableCollection((Collection<?>)builder.serviceAccountScopes));
            this.serviceAccountPrivateKey = builder.serviceAccountPrivateKey;
            this.serviceAccountPrivateKeyId = builder.serviceAccountPrivateKeyId;
            this.serviceAccountUser = builder.serviceAccountUser;
        }
    }
    
    @Override
    public GoogleCredential setAccessToken(final String accessToken) {
        return (GoogleCredential)super.setAccessToken(accessToken);
    }
    
    @Override
    public GoogleCredential setRefreshToken(final String refreshToken) {
        if (refreshToken != null) {
            Preconditions.checkArgument(this.getJsonFactory() != null && this.getTransport() != null && this.getClientAuthentication() != null, (Object)"Please use the Builder and call setJsonFactory, setTransport and setClientSecrets");
        }
        return (GoogleCredential)super.setRefreshToken(refreshToken);
    }
    
    @Override
    public GoogleCredential setExpirationTimeMilliseconds(final Long expirationTimeMilliseconds) {
        return (GoogleCredential)super.setExpirationTimeMilliseconds(expirationTimeMilliseconds);
    }
    
    @Override
    public GoogleCredential setExpiresInSeconds(final Long expiresInSeconds) {
        return (GoogleCredential)super.setExpiresInSeconds(expiresInSeconds);
    }
    
    @Override
    public GoogleCredential setFromTokenResponse(final TokenResponse fromTokenResponse) {
        return (GoogleCredential)super.setFromTokenResponse(fromTokenResponse);
    }
    
    @Beta
    @Beta
    @Override
    protected TokenResponse executeRefreshToken() throws IOException {
        if (this.serviceAccountPrivateKey == null) {
            return super.executeRefreshToken();
        }
        final JsonWebSignature.Header header = new JsonWebSignature.Header();
        header.setAlgorithm("RS256");
        header.setType("JWT");
        header.setKeyId(this.serviceAccountPrivateKeyId);
        final JsonWebToken.Payload payload = new JsonWebToken.Payload();
        final long currentTimeMillis = this.getClock().currentTimeMillis();
        payload.setIssuer(this.serviceAccountId);
        payload.setAudience(this.getTokenServerEncodedUrl());
        payload.setIssuedAtTimeSeconds(currentTimeMillis / 1000L);
        payload.setExpirationTimeSeconds(currentTimeMillis / 1000L + 3600L);
        payload.setSubject(this.serviceAccountUser);
        payload.put("scope", Joiner.on(' ').join(this.serviceAccountScopes));
        try {
            final String signUsingRsaSha256 = JsonWebSignature.signUsingRsaSha256(this.serviceAccountPrivateKey, this.getJsonFactory(), header, payload);
            final TokenRequest tokenRequest = new TokenRequest(this.getTransport(), this.getJsonFactory(), new GenericUrl(this.getTokenServerEncodedUrl()), "urn:ietf:params:oauth:grant-type:jwt-bearer");
            tokenRequest.put("assertion", signUsingRsaSha256);
            return tokenRequest.execute();
        }
        catch (GeneralSecurityException ex2) {
            final IOException ex = new IOException();
            ex.initCause(ex2);
            throw ex;
        }
    }
    
    public final String getServiceAccountId() {
        return this.serviceAccountId;
    }
    
    public final String getServiceAccountProjectId() {
        return this.serviceAccountProjectId;
    }
    
    public final Collection<String> getServiceAccountScopes() {
        return this.serviceAccountScopes;
    }
    
    public final String getServiceAccountScopesAsString() {
        return (this.serviceAccountScopes == null) ? null : Joiner.on(' ').join(this.serviceAccountScopes);
    }
    
    public final PrivateKey getServiceAccountPrivateKey() {
        return this.serviceAccountPrivateKey;
    }
    
    @Beta
    @Beta
    public final String getServiceAccountPrivateKeyId() {
        return this.serviceAccountPrivateKeyId;
    }
    
    public final String getServiceAccountUser() {
        return this.serviceAccountUser;
    }
    
    @Beta
    @Beta
    public boolean createScopedRequired() {
        return this.serviceAccountPrivateKey != null && (this.serviceAccountScopes == null || this.serviceAccountScopes.isEmpty());
    }
    
    @Beta
    @Beta
    public GoogleCredential createScoped(final Collection<String> serviceAccountScopes) {
        if (this.serviceAccountPrivateKey == null) {
            return this;
        }
        return new Builder().setServiceAccountPrivateKey(this.serviceAccountPrivateKey).setServiceAccountPrivateKeyId(this.serviceAccountPrivateKeyId).setServiceAccountId(this.serviceAccountId).setServiceAccountProjectId(this.serviceAccountProjectId).setServiceAccountUser(this.serviceAccountUser).setServiceAccountScopes(serviceAccountScopes).setTokenServerEncodedUrl(this.getTokenServerEncodedUrl()).setTransport(this.getTransport()).setJsonFactory(this.getJsonFactory()).setClock(this.getClock()).build();
    }
    
    @Beta
    @Beta
    private static GoogleCredential fromStreamUser(final GenericJson genericJson, final HttpTransport transport, final JsonFactory jsonFactory) throws IOException {
        final String s = (String)genericJson.get("client_id");
        final String s2 = (String)genericJson.get("client_secret");
        final String refreshToken = (String)genericJson.get("refresh_token");
        if (s == null || s2 == null || refreshToken == null) {
            throw new IOException("Error reading user credential from stream,  expecting 'client_id', 'client_secret' and 'refresh_token'.");
        }
        final GoogleCredential build = new Builder().setClientSecrets(s, s2).setTransport(transport).setJsonFactory(jsonFactory).build();
        build.setRefreshToken(refreshToken);
        build.refreshToken();
        return build;
    }
    
    @Beta
    @Beta
    private static GoogleCredential fromStreamServiceAccount(final GenericJson genericJson, final HttpTransport transport, final JsonFactory jsonFactory) throws IOException {
        final String s = (String)genericJson.get("client_id");
        final String serviceAccountId = (String)genericJson.get("client_email");
        final String s2 = (String)genericJson.get("private_key");
        final String serviceAccountPrivateKeyId = (String)genericJson.get("private_key_id");
        if (s == null || serviceAccountId == null || s2 == null || serviceAccountPrivateKeyId == null) {
            throw new IOException("Error reading service account credential from stream, expecting  'client_id', 'client_email', 'private_key' and 'private_key_id'.");
        }
        final Builder setServiceAccountPrivateKeyId = new Builder().setTransport(transport).setJsonFactory(jsonFactory).setServiceAccountId(serviceAccountId).setServiceAccountScopes((Collection<String>)Collections.<Object>emptyList()).setServiceAccountPrivateKey(privateKeyFromPkcs8(s2)).setServiceAccountPrivateKeyId(serviceAccountPrivateKeyId);
        final String tokenServerEncodedUrl = (String)genericJson.get("token_uri");
        if (tokenServerEncodedUrl != null) {
            setServiceAccountPrivateKeyId.setTokenServerEncodedUrl(tokenServerEncodedUrl);
        }
        final String serviceAccountProjectId = (String)genericJson.get("project_id");
        if (serviceAccountProjectId != null) {
            setServiceAccountPrivateKeyId.setServiceAccountProjectId(serviceAccountProjectId);
        }
        return setServiceAccountPrivateKeyId.build();
    }
    
    @Beta
    @Beta
    private static PrivateKey privateKeyFromPkcs8(final String s) throws IOException {
        final PemReader.Section firstSectionAndClose = PemReader.readFirstSectionAndClose(new StringReader(s), "PRIVATE KEY");
        if (firstSectionAndClose == null) {
            throw new IOException("Invalid PKCS8 data.");
        }
        final PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(firstSectionAndClose.getBase64DecodedBytes());
        NoSuchAlgorithmException ex;
        try {
            return SecurityUtils.getRsaKeyFactory().generatePrivate(pkcs8EncodedKeySpec);
        }
        catch (NoSuchAlgorithmException ex2) {
            ex = ex2;
        }
        catch (InvalidKeySpecException ex3) {
            ex = (NoSuchAlgorithmException)ex3;
        }
        throw OAuth2Utils.<IOException>exceptionWithCause(new IOException("Unexpected exception reading PKCS data"), ex);
    }
    
    @Override
    public /* bridge */ Credential setFromTokenResponse(final TokenResponse fromTokenResponse) {
        return this.setFromTokenResponse(fromTokenResponse);
    }
    
    @Override
    public /* bridge */ Credential setExpiresInSeconds(final Long expiresInSeconds) {
        return this.setExpiresInSeconds(expiresInSeconds);
    }
    
    @Override
    public /* bridge */ Credential setExpirationTimeMilliseconds(final Long expirationTimeMilliseconds) {
        return this.setExpirationTimeMilliseconds(expirationTimeMilliseconds);
    }
    
    @Override
    public /* bridge */ Credential setRefreshToken(final String refreshToken) {
        return this.setRefreshToken(refreshToken);
    }
    
    @Override
    public /* bridge */ Credential setAccessToken(final String accessToken) {
        return this.setAccessToken(accessToken);
    }
    
    static {
        GoogleCredential.defaultCredentialProvider = new DefaultCredentialProvider();
    }
}
