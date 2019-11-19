package com.google.api.client.googleapis.notifications;

import java.io.InputStream;
import com.google.api.client.util.Beta;

@Beta
public class UnparsedNotification extends AbstractNotification
{
    private String contentType;
    private InputStream contentStream;
    
    public UnparsedNotification(final long n, final String s, final String s2, final String s3, final String s4) {
        super(n, s, s2, s3, s4);
    }
    
    public final String getContentType() {
        return this.contentType;
    }
    
    public UnparsedNotification setContentType(final String contentType) {
        this.contentType = contentType;
        return this;
    }
    
    public final InputStream getContentStream() {
        return this.contentStream;
    }
    
    public UnparsedNotification setContentStream(final InputStream contentStream) {
        this.contentStream = contentStream;
        return this;
    }
    
    @Override
    public UnparsedNotification setMessageNumber(final long messageNumber) {
        return (UnparsedNotification)super.setMessageNumber(messageNumber);
    }
    
    @Override
    public UnparsedNotification setResourceState(final String resourceState) {
        return (UnparsedNotification)super.setResourceState(resourceState);
    }
    
    @Override
    public UnparsedNotification setResourceId(final String resourceId) {
        return (UnparsedNotification)super.setResourceId(resourceId);
    }
    
    @Override
    public UnparsedNotification setResourceUri(final String resourceUri) {
        return (UnparsedNotification)super.setResourceUri(resourceUri);
    }
    
    @Override
    public UnparsedNotification setChannelId(final String channelId) {
        return (UnparsedNotification)super.setChannelId(channelId);
    }
    
    @Override
    public UnparsedNotification setChannelExpiration(final String channelExpiration) {
        return (UnparsedNotification)super.setChannelExpiration(channelExpiration);
    }
    
    @Override
    public UnparsedNotification setChannelToken(final String channelToken) {
        return (UnparsedNotification)super.setChannelToken(channelToken);
    }
    
    @Override
    public UnparsedNotification setChanged(final String changed) {
        return (UnparsedNotification)super.setChanged(changed);
    }
    
    @Override
    public String toString() {
        return super.toStringHelper().add("contentType", this.contentType).toString();
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
