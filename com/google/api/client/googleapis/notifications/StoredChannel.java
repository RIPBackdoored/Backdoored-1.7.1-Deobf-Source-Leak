package com.google.api.client.googleapis.notifications;

import com.google.api.client.util.Objects;
import com.google.api.client.util.store.DataStore;
import java.io.IOException;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.Preconditions;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import com.google.api.client.util.Beta;
import java.io.Serializable;

@Beta
public final class StoredChannel implements Serializable
{
    public static final String DEFAULT_DATA_STORE_ID;
    private static final long serialVersionUID = 1L;
    private final Lock lock;
    private final UnparsedNotificationCallback notificationCallback;
    private String clientToken;
    private Long expiration;
    private final String id;
    private String topicId;
    
    public StoredChannel(final UnparsedNotificationCallback unparsedNotificationCallback) {
        this(unparsedNotificationCallback, NotificationUtils.randomUuidString());
    }
    
    public StoredChannel(final UnparsedNotificationCallback unparsedNotificationCallback, final String s) {
        super();
        this.lock = new ReentrantLock();
        this.notificationCallback = Preconditions.<UnparsedNotificationCallback>checkNotNull(unparsedNotificationCallback);
        this.id = Preconditions.<String>checkNotNull(s);
    }
    
    public StoredChannel store(final DataStoreFactory dataStoreFactory) throws IOException {
        return this.store(getDefaultDataStore(dataStoreFactory));
    }
    
    public StoredChannel store(final DataStore<StoredChannel> dataStore) throws IOException {
        this.lock.lock();
        try {
            dataStore.set(this.getId(), this);
            return this;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public UnparsedNotificationCallback getNotificationCallback() {
        this.lock.lock();
        try {
            return this.notificationCallback;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public String getClientToken() {
        this.lock.lock();
        try {
            return this.clientToken;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public StoredChannel setClientToken(final String clientToken) {
        this.lock.lock();
        try {
            this.clientToken = clientToken;
        }
        finally {
            this.lock.unlock();
        }
        return this;
    }
    
    public Long getExpiration() {
        this.lock.lock();
        try {
            return this.expiration;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public StoredChannel setExpiration(final Long expiration) {
        this.lock.lock();
        try {
            this.expiration = expiration;
        }
        finally {
            this.lock.unlock();
        }
        return this;
    }
    
    public String getId() {
        this.lock.lock();
        try {
            return this.id;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public String getTopicId() {
        this.lock.lock();
        try {
            return this.topicId;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public StoredChannel setTopicId(final String topicId) {
        this.lock.lock();
        try {
            this.topicId = topicId;
        }
        finally {
            this.lock.unlock();
        }
        return this;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(StoredChannel.class).add("notificationCallback", this.getNotificationCallback()).add("clientToken", this.getClientToken()).add("expiration", this.getExpiration()).add("id", this.getId()).add("topicId", this.getTopicId()).toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof StoredChannel && this.getId().equals(((StoredChannel)o).getId()));
    }
    
    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
    
    public static DataStore<StoredChannel> getDefaultDataStore(final DataStoreFactory dataStoreFactory) throws IOException {
        return dataStoreFactory.<StoredChannel>getDataStore(StoredChannel.DEFAULT_DATA_STORE_ID);
    }
    
    static {
        DEFAULT_DATA_STORE_ID = StoredChannel.class.getSimpleName();
    }
}
