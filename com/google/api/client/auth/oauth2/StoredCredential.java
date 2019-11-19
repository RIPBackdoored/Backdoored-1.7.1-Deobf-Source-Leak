package com.google.api.client.auth.oauth2;

import java.io.IOException;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;
import java.util.Arrays;
import com.google.api.client.util.Objects;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import com.google.api.client.util.Beta;
import java.io.Serializable;

@Beta
public final class StoredCredential implements Serializable
{
    public static final String DEFAULT_DATA_STORE_ID;
    private static final long serialVersionUID = 1L;
    private final Lock lock;
    private String accessToken;
    private Long expirationTimeMilliseconds;
    private String refreshToken;
    
    public StoredCredential() {
        super();
        this.lock = new ReentrantLock();
    }
    
    public StoredCredential(final Credential credential) {
        super();
        this.lock = new ReentrantLock();
        this.setAccessToken(credential.getAccessToken());
        this.setRefreshToken(credential.getRefreshToken());
        this.setExpirationTimeMilliseconds(credential.getExpirationTimeMilliseconds());
    }
    
    public String getAccessToken() {
        this.lock.lock();
        try {
            return this.accessToken;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public StoredCredential setAccessToken(final String accessToken) {
        this.lock.lock();
        try {
            this.accessToken = accessToken;
        }
        finally {
            this.lock.unlock();
        }
        return this;
    }
    
    public Long getExpirationTimeMilliseconds() {
        this.lock.lock();
        try {
            return this.expirationTimeMilliseconds;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public StoredCredential setExpirationTimeMilliseconds(final Long expirationTimeMilliseconds) {
        this.lock.lock();
        try {
            this.expirationTimeMilliseconds = expirationTimeMilliseconds;
        }
        finally {
            this.lock.unlock();
        }
        return this;
    }
    
    public String getRefreshToken() {
        this.lock.lock();
        try {
            return this.refreshToken;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public StoredCredential setRefreshToken(final String refreshToken) {
        this.lock.lock();
        try {
            this.refreshToken = refreshToken;
        }
        finally {
            this.lock.unlock();
        }
        return this;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(StoredCredential.class).add("accessToken", this.getAccessToken()).add("refreshToken", this.getRefreshToken()).add("expirationTimeMilliseconds", this.getExpirationTimeMilliseconds()).toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StoredCredential)) {
            return false;
        }
        final StoredCredential storedCredential = (StoredCredential)o;
        return Objects.equal(this.getAccessToken(), storedCredential.getAccessToken()) && Objects.equal(this.getRefreshToken(), storedCredential.getRefreshToken()) && Objects.equal(this.getExpirationTimeMilliseconds(), storedCredential.getExpirationTimeMilliseconds());
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] { this.getAccessToken(), this.getRefreshToken(), this.getExpirationTimeMilliseconds() });
    }
    
    public static DataStore<StoredCredential> getDefaultDataStore(final DataStoreFactory dataStoreFactory) throws IOException {
        return dataStoreFactory.<StoredCredential>getDataStore(StoredCredential.DEFAULT_DATA_STORE_ID);
    }
    
    static {
        DEFAULT_DATA_STORE_ID = StoredCredential.class.getSimpleName();
    }
}
