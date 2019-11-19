package com.google.api.client.googleapis.notifications;

import com.google.api.client.util.Beta;

@Beta
public class TypedNotification<T> extends AbstractNotification
{
    private T content;
    
    public TypedNotification(final long n, final String s, final String s2, final String s3, final String s4) {
        super(n, s, s2, s3, s4);
    }
    
    public TypedNotification(final UnparsedNotification unparsedNotification) {
        super(unparsedNotification);
    }
    
    public final T getContent() {
        return this.content;
    }
    
    public TypedNotification<T> setContent(final T content) {
        this.content = content;
        return this;
    }
    
    @Override
    public TypedNotification<T> setMessageNumber(final long messageNumber) {
        return (TypedNotification)super.setMessageNumber(messageNumber);
    }
    
    @Override
    public TypedNotification<T> setResourceState(final String resourceState) {
        return (TypedNotification)super.setResourceState(resourceState);
    }
    
    @Override
    public TypedNotification<T> setResourceId(final String resourceId) {
        return (TypedNotification)super.setResourceId(resourceId);
    }
    
    @Override
    public TypedNotification<T> setResourceUri(final String resourceUri) {
        return (TypedNotification)super.setResourceUri(resourceUri);
    }
    
    @Override
    public TypedNotification<T> setChannelId(final String channelId) {
        return (TypedNotification)super.setChannelId(channelId);
    }
    
    @Override
    public TypedNotification<T> setChannelExpiration(final String channelExpiration) {
        return (TypedNotification)super.setChannelExpiration(channelExpiration);
    }
    
    @Override
    public TypedNotification<T> setChannelToken(final String channelToken) {
        return (TypedNotification)super.setChannelToken(channelToken);
    }
    
    @Override
    public TypedNotification<T> setChanged(final String changed) {
        return (TypedNotification)super.setChanged(changed);
    }
    
    @Override
    public String toString() {
        return super.toStringHelper().add("content", this.content).toString();
    }
    
    @Override
    public /* bridge */ AbstractNotification setChanged(final String changed) {
        return this.setChanged(changed);
    }
    
    @Override
    public /* bridge */ AbstractNotification setChannelToken(final String channelToken) {
        return this.setChannelToken(channelToken);
    }
    
    @Override
    public /* bridge */ AbstractNotification setChannelExpiration(final String channelExpiration) {
        return this.setChannelExpiration(channelExpiration);
    }
    
    @Override
    public /* bridge */ AbstractNotification setChannelId(final String channelId) {
        return this.setChannelId(channelId);
    }
    
    @Override
    public /* bridge */ AbstractNotification setResourceUri(final String resourceUri) {
        return this.setResourceUri(resourceUri);
    }
    
    @Override
    public /* bridge */ AbstractNotification setResourceId(final String resourceId) {
        return this.setResourceId(resourceId);
    }
    
    @Override
    public /* bridge */ AbstractNotification setResourceState(final String resourceState) {
        return this.setResourceState(resourceState);
    }
    
    @Override
    public /* bridge */ AbstractNotification setMessageNumber(final long messageNumber) {
        return this.setMessageNumber(messageNumber);
    }
}
