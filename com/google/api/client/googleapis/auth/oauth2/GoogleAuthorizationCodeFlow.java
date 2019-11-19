package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.util.Clock;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.util.Preconditions;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.Beta;
import com.google.api.client.auth.oauth2.CredentialStore;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;
import java.io.IOException;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import java.util.Collection;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;

public class GoogleAuthorizationCodeFlow extends AuthorizationCodeFlow
{
    private final String approvalPrompt;
    private final String accessType;
    
    public GoogleAuthorizationCodeFlow(final HttpTransport httpTransport, final JsonFactory jsonFactory, final String s, final String s2, final Collection<String> collection) {
        this(new Builder(httpTransport, jsonFactory, s, s2, collection));
    }
    
    protected GoogleAuthorizationCodeFlow(final Builder builder) {
        super(builder);
        this.accessType = builder.accessType;
        this.approvalPrompt = builder.approvalPrompt;
    }
    
    @Override
    public GoogleAuthorizationCodeTokenRequest newTokenRequest(final String s) {
        return new GoogleAuthorizationCodeTokenRequest(this.getTransport(), this.getJsonFactory(), this.getTokenServerEncodedUrl(), "", "", s, "").setClientAuthentication(this.getClientAuthentication()).setRequestInitializer(this.getRequestInitializer()).setScopes(this.getScopes());
    }
    
    @Override
    public GoogleAuthorizationCodeRequestUrl newAuthorizationUrl() {
        return new GoogleAuthorizationCodeRequestUrl(this.getAuthorizationServerEncodedUrl(), this.getClientId(), "", this.getScopes()).setAccessType(this.accessType).setApprovalPrompt(this.approvalPrompt);
    }
    
    public final String getApprovalPrompt() {
        return this.approvalPrompt;
    }
    
    public final String getAccessType() {
        return this.accessType;
    }
    
    @Override
    public /* bridge */ AuthorizationCodeTokenRequest newTokenRequest(final String s) {
        return this.newTokenRequest(s);
    }
    
    @Override
    public /* bridge */ AuthorizationCodeRequestUrl newAuthorizationUrl() {
        return this.newAuthorizationUrl();
    }
}
