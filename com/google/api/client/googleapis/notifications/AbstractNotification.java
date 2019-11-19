package com.google.api.client.googleapis.notifications;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Objects;
import com.google.api.client.util.Beta;

@Beta
public abstract class AbstractNotification
{
    private long messageNumber;
    private String resourceState;
    private String resourceId;
    private String resourceUri;
    private String channelId;
    private String channelExpiration;
    private String channelToken;
    private String changed;
    
    protected AbstractNotification(final long messageNumber, final String resourceState, final String resourceId, final String resourceUri, final String channelId) {
        super();
        this.setMessageNumber(messageNumber);
        this.setResourceState(resourceState);
        this.setResourceId(resourceId);
        this.setResourceUri(resourceUri);
        this.setChannelId(channelId);
    }
    
    protected AbstractNotification(final AbstractNotification abstractNotification) {
        this(abstractNotification.getMessageNumber(), abstractNotification.getResourceState(), abstractNotification.getResourceId(), abstractNotification.getResourceUri(), abstractNotification.getChannelId());
        this.setChannelExpiration(abstractNotification.getChannelExpiration());
        this.setChannelToken(abstractNotification.getChannelToken());
        this.setChanged(abstractNotification.getChanged());
    }
    
    @Override
    public String toString() {
        return this.toStringHelper().toString();
    }
    
    protected Objects.ToStringHelper toStringHelper() {
        return Objects.toStringHelper(this).add("messageNumber", this.messageNumber).add("resourceState", this.resourceState).add("resourceId", this.resourceId).add("resourceUri", this.resourceUri).add("channelId", this.channelId).add("channelExpiration", this.channelExpiration).add("channelToken", this.channelToken).add("changed", this.changed);
    }
    
    public final long getMessageNumber() {
        return this.messageNumber;
    }
    
    public AbstractNotification setMessageNumber(final long messageNumber) {
        Preconditions.checkArgument(messageNumber >= 1L);
        this.messageNumber = messageNumber;
        return this;
    }
    
    public final String getResourceState() {
        return this.resourceState;
    }
    
    public AbstractNotification setResourceState(final String s) {
        this.resourceState = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    public final String getResourceId() {
        return this.resourceId;
    }
    
    public AbstractNotification setResourceId(final String s) {
        this.resourceId = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    public final String getResourceUri() {
        return this.resourceUri;
    }
    
    public AbstractNotification setResourceUri(final String s) {
        this.resourceUri = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    public final String getChannelId() {
        return this.channelId;
    }
    
    public AbstractNotification setChannelId(final String s) {
        this.channelId = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    public final String getChannelExpiration() {
        return this.channelExpiration;
    }
    
    public AbstractNotification setChannelExpiration(final String channelExpiration) {
        this.channelExpiration = channelExpiration;
        return this;
    }
    
    public final String getChannelToken() {
        return this.channelToken;
    }
    
    public AbstractNotification setChannelToken(final String channelToken) {
        this.channelToken = channelToken;
        return this;
    }
    
    public final String getChanged() {
        return this.changed;
    }
    
    public AbstractNotification setChanged(final String changed) {
        this.changed = changed;
        return this;
    }
}
