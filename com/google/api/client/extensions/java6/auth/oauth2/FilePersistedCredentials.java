package com.google.api.client.extensions.java6.auth.oauth2;

import com.google.api.client.util.GenericData;
import java.io.IOException;
import java.util.Iterator;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.Preconditions;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.util.Maps;
import com.google.api.client.util.Key;
import java.util.Map;
import com.google.api.client.util.Beta;
import com.google.api.client.json.GenericJson;

@Deprecated
@Beta
public class FilePersistedCredentials extends GenericJson
{
    @Key
    private Map<String, FilePersistedCredential> credentials;
    
    public FilePersistedCredentials() {
        super();
        this.credentials = (Map<String, FilePersistedCredential>)Maps.<Object, Object>newHashMap();
    }
    
    void store(final String s, final Credential credential) {
        Preconditions.<String>checkNotNull(s);
        FilePersistedCredential filePersistedCredential = this.credentials.get(s);
        if (filePersistedCredential == null) {
            filePersistedCredential = new FilePersistedCredential();
            this.credentials.put(s, filePersistedCredential);
        }
        filePersistedCredential.store(credential);
    }
    
    boolean load(final String s, final Credential credential) {
        Preconditions.<String>checkNotNull(s);
        final FilePersistedCredential filePersistedCredential = this.credentials.get(s);
        if (filePersistedCredential == null) {
            return false;
        }
        filePersistedCredential.load(credential);
        return true;
    }
    
    void delete(final String s) {
        Preconditions.<String>checkNotNull(s);
        this.credentials.remove(s);
    }
    
    @Override
    public FilePersistedCredentials set(final String s, final Object o) {
        return (FilePersistedCredentials)super.set(s, o);
    }
    
    @Override
    public FilePersistedCredentials clone() {
        return (FilePersistedCredentials)super.clone();
    }
    
    void migrateTo(final DataStore<StoredCredential> dataStore) throws IOException {
        for (final Map.Entry<String, FilePersistedCredential> entry : this.credentials.entrySet()) {
            dataStore.set(entry.getKey(), entry.getValue().toStoredCredential());
        }
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
