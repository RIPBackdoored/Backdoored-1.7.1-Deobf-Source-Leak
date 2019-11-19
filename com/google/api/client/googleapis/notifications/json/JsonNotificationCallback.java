package com.google.api.client.googleapis.notifications.json;

import com.google.api.client.util.ObjectParser;
import com.google.api.client.json.JsonFactory;
import java.io.IOException;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.Beta;
import com.google.api.client.googleapis.notifications.TypedNotificationCallback;

@Beta
public abstract class JsonNotificationCallback<T> extends TypedNotificationCallback<T>
{
    private static final long serialVersionUID = 1L;
    
    public JsonNotificationCallback() {
        super();
    }
    
    @Override
    protected final JsonObjectParser getObjectParser() throws IOException {
        return new JsonObjectParser(this.getJsonFactory());
    }
    
    protected abstract JsonFactory getJsonFactory() throws IOException;
    
    @Override
    protected /* bridge */ ObjectParser getObjectParser() throws IOException {
        return this.getObjectParser();
    }
}
