package com.fasterxml.jackson.core;

import java.io.IOException;

public class JsonProcessingException extends IOException
{
    static final long serialVersionUID = 123L;
    protected JsonLocation _location;
    
    protected JsonProcessingException(final String s, final JsonLocation location, final Throwable t) {
        super(s);
        if (t != null) {
            this.initCause(t);
        }
        this._location = location;
    }
    
    protected JsonProcessingException(final String s) {
        super(s);
    }
    
    protected JsonProcessingException(final String s, final JsonLocation jsonLocation) {
        this(s, jsonLocation, null);
    }
    
    protected JsonProcessingException(final String s, final Throwable t) {
        this(s, null, t);
    }
    
    protected JsonProcessingException(final Throwable t) {
        this(null, null, t);
    }
    
    public JsonLocation getLocation() {
        return this._location;
    }
    
    public void clearLocation() {
        this._location = null;
    }
    
    public String getOriginalMessage() {
        return super.getMessage();
    }
    
    public Object getProcessor() {
        return null;
    }
    
    protected String getMessageSuffix() {
        return null;
    }
    
    @Override
    public String getMessage() {
        String s = super.getMessage();
        if (s == null) {
            s = "N/A";
        }
        final JsonLocation location = this.getLocation();
        final String messageSuffix = this.getMessageSuffix();
        if (location != null || messageSuffix != null) {
            final StringBuilder sb = new StringBuilder(100);
            sb.append(s);
            if (messageSuffix != null) {
                sb.append(messageSuffix);
            }
            if (location != null) {
                sb.append('\n');
                sb.append(" at ");
                sb.append(location.toString());
            }
            s = sb.toString();
        }
        return s;
    }
    
    @Override
    public String toString() {
        return this.getClass().getName() + ": " + this.getMessage();
    }
}
