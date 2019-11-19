package com.google.api.client.auth.oauth2;

import com.google.api.client.util.Preconditions;
import java.io.IOException;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.Beta;

@Beta
public final class DataStoreCredentialRefreshListener implements CredentialRefreshListener
{
    private final DataStore<StoredCredential> credentialDataStore;
    private final String userId;
    
    public DataStoreCredentialRefreshListener(final String s, final DataStoreFactory dataStoreFactory) throws IOException {
        this(s, StoredCredential.getDefaultDataStore(dataStoreFactory));
    }
    
    public DataStoreCredentialRefreshListener(final String s, final DataStore<StoredCredential> dataStore) {
        super();
        this.userId = Preconditions.<String>checkNotNull(s);
        this.credentialDataStore = Preconditions.<DataStore<StoredCredential>>checkNotNull(dataStore);
    }
    
    @Override
    public void onTokenResponse(final Credential credential, final TokenResponse tokenResponse) throws IOException {
        this.makePersistent(credential);
    }
    
    @Override
    public void onTokenErrorResponse(final Credential credential, final TokenErrorResponse tokenErrorResponse) throws IOException {
        this.makePersistent(credential);
    }
    
    public DataStore<StoredCredential> getCredentialDataStore() {
        return this.credentialDataStore;
    }
    
    public void makePersistent(final Credential credential) throws IOException {
        this.credentialDataStore.set(this.userId, new StoredCredential(credential));
    }
}
