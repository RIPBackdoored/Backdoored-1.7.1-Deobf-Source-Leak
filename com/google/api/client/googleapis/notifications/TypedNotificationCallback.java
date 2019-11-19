package com.google.api.client.googleapis.notifications;

import com.google.api.client.util.Preconditions;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.util.ObjectParser;
import java.io.IOException;
import com.google.api.client.util.Beta;

@Beta
public abstract class TypedNotificationCallback<T> implements UnparsedNotificationCallback
{
    private static final long serialVersionUID = 1L;
    
    public TypedNotificationCallback() {
        super();
    }
    
    protected abstract void onNotification(final StoredChannel p0, final TypedNotification<T> p1) throws IOException;
    
    protected abstract ObjectParser getObjectParser() throws IOException;
    
    protected abstract Class<T> getDataClass() throws IOException;
    
    public final void onNotification(final StoredChannel storedChannel, final UnparsedNotification unparsedNotification) throws IOException {
        final TypedNotification<T> typedNotification = new TypedNotification<T>(unparsedNotification);
        final String contentType = unparsedNotification.getContentType();
        if (contentType != null) {
            typedNotification.setContent(this.getObjectParser().<T>parseAndClose(unparsedNotification.getContentStream(), new HttpMediaType(contentType).getCharsetParameter(), Preconditions.<Class<T>>checkNotNull(this.getDataClass())));
        }
        this.onNotification(storedChannel, typedNotification);
    }
}
