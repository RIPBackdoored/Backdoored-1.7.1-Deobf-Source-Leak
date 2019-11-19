package com.google.api.client.auth.oauth2;

import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Joiner;
import com.google.api.client.util.Strings;
import java.io.IOException;
import java.util.Collections;
import com.google.api.client.util.Preconditions;
import com.google.api.client.http.GenericUrl;
import java.util.Collection;
import com.google.api.client.util.Clock;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.Beta;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;

public class AuthorizationCodeFlow
{
    private final Credential.AccessMethod method;
    private final HttpTransport transport;
    private final JsonFactory jsonFactory;
    private final String tokenServerEncodedUrl;
    private final HttpExecuteInterceptor clientAuthentication;
    private final String clientId;
    private final String authorizationServerEncodedUrl;
    @Deprecated
    @Beta
    private final CredentialStore credentialStore;
    @Beta
    private final DataStore<StoredCredential> credentialDataStore;
    private final HttpRequestInitializer requestInitializer;
    private final Clock clock;
    private final Collection<String> scopes;
    private final CredentialCreatedListener credentialCreatedListener;
    private final Collection<CredentialRefreshListener> refreshListeners;
    
    public AuthorizationCodeFlow(final Credential.AccessMethod accessMethod, final HttpTransport httpTransport, final JsonFactory jsonFactory, final GenericUrl genericUrl, final HttpExecuteInterceptor httpExecuteInterceptor, final String s, final String s2) {
        this(new Builder(accessMethod, httpTransport, jsonFactory, genericUrl, httpExecuteInterceptor, s, s2));
    }
    
    protected AuthorizationCodeFlow(final Builder builder) {
        super();
        this.method = Preconditions.<Credential.AccessMethod>checkNotNull(builder.method);
        this.transport = Preconditions.<HttpTransport>checkNotNull(builder.transport);
        this.jsonFactory = Preconditions.<JsonFactory>checkNotNull(builder.jsonFactory);
        this.tokenServerEncodedUrl = Preconditions.<GenericUrl>checkNotNull(builder.tokenServerUrl).build();
        this.clientAuthentication = builder.clientAuthentication;
        this.clientId = Preconditions.<String>checkNotNull(builder.clientId);
        this.authorizationServerEncodedUrl = Preconditions.<String>checkNotNull(builder.authorizationServerEncodedUrl);
        this.requestInitializer = builder.requestInitializer;
        this.credentialStore = builder.credentialStore;
        this.credentialDataStore = builder.credentialDataStore;
        this.scopes = Collections.<String>unmodifiableCollection((Collection<? extends String>)builder.scopes);
        this.clock = Preconditions.<Clock>checkNotNull(builder.clock);
        this.credentialCreatedListener = builder.credentialCreatedListener;
        this.refreshListeners = Collections.<CredentialRefreshListener>unmodifiableCollection((Collection<? extends CredentialRefreshListener>)builder.refreshListeners);
    }
    
    public AuthorizationCodeRequestUrl newAuthorizationUrl() {
        return new AuthorizationCodeRequestUrl(this.authorizationServerEncodedUrl, this.clientId).setScopes(this.scopes);
    }
    
    public AuthorizationCodeTokenRequest newTokenRequest(final String s) {
        return new AuthorizationCodeTokenRequest(this.transport, this.jsonFactory, new GenericUrl(this.tokenServerEncodedUrl), s).setClientAuthentication(this.clientAuthentication).setRequestInitializer(this.requestInitializer).setScopes(this.scopes);
    }
    
    public Credential createAndStoreCredential(final TokenResponse fromTokenResponse, final String s) throws IOException {
        final Credential setFromTokenResponse = this.newCredential(s).setFromTokenResponse(fromTokenResponse);
        if (this.credentialStore != null) {
            this.credentialStore.store(s, setFromTokenResponse);
        }
        if (this.credentialDataStore != null) {
            this.credentialDataStore.set(s, new StoredCredential(setFromTokenResponse));
        }
        if (this.credentialCreatedListener != null) {
            this.credentialCreatedListener.onCredentialCreated(setFromTokenResponse, fromTokenResponse);
        }
        return setFromTokenResponse;
    }
    
    public Credential loadCredential(final String s) throws IOException {
        if (Strings.isNullOrEmpty(s)) {
            return null;
        }
        if (this.credentialDataStore == null && this.credentialStore == null) {
            return null;
        }
        final Credential credential = this.newCredential(s);
        if (this.credentialDataStore != null) {
            final StoredCredential storedCredential = this.credentialDataStore.get(s);
            if (storedCredential == null) {
                return null;
            }
            credential.setAccessToken(storedCredential.getAccessToken());
            credential.setRefreshToken(storedCredential.getRefreshToken());
            credential.setExpirationTimeMilliseconds(storedCredential.getExpirationTimeMilliseconds());
        }
        else if (!this.credentialStore.load(s, credential)) {
            return null;
        }
        return credential;
    }
    
    private Credential newCredential(final String s) {
        final Credential.Builder setClock = new Credential.Builder(this.method).setTransport(this.transport).setJsonFactory(this.jsonFactory).setTokenServerEncodedUrl(this.tokenServerEncodedUrl).setClientAuthentication(this.clientAuthentication).setRequestInitializer(this.requestInitializer).setClock(this.clock);
        if (this.credentialDataStore != null) {
            setClock.addRefreshListener(new DataStoreCredentialRefreshListener(s, this.credentialDataStore));
        }
        else if (this.credentialStore != null) {
            setClock.addRefreshListener(new CredentialStoreRefreshListener(s, this.credentialStore));
        }
        setClock.getRefreshListeners().addAll(this.refreshListeners);
        return setClock.build();
    }
    
    public final Credential.AccessMethod getMethod() {
        return this.method;
    }
    
    public final HttpTransport getTransport() {
        return this.transport;
    }
    
    public final JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }
    
    public final String getTokenServerEncodedUrl() {
        return this.tokenServerEncodedUrl;
    }
    
    public final HttpExecuteInterceptor getClientAuthentication() {
        return this.clientAuthentication;
    }
    
    public final String getClientId() {
        return this.clientId;
    }
    
    public final String getAuthorizationServerEncodedUrl() {
        return this.authorizationServerEncodedUrl;
    }
    
    @Deprecated
    @Beta
    public final CredentialStore getCredentialStore() {
        return this.credentialStore;
    }
    
    @Beta
    public final DataStore<StoredCredential> getCredentialDataStore() {
        return this.credentialDataStore;
    }
    
    public final HttpRequestInitializer getRequestInitializer() {
        return this.requestInitializer;
    }
    
    public final String getScopesAsString() {
        return Joiner.on(' ').join(this.scopes);
    }
    
    public final Collection<String> getScopes() {
        return this.scopes;
    }
    
    public final Clock getClock() {
        return this.clock;
    }
    
    public final Collection<CredentialRefreshListener> getRefreshListeners() {
        return this.refreshListeners;
    }
}
