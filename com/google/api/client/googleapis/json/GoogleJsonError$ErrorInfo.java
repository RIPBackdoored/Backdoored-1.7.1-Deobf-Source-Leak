package com.google.api.client.googleapis.json;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public static class ErrorInfo extends GenericJson
{
    @Key
    private String domain;
    @Key
    private String reason;
    @Key
    private String message;
    @Key
    private String location;
    @Key
    private String locationType;
    
    public ErrorInfo() {
        super();
    }
    
    public final String getDomain() {
        return this.domain;
    }
    
    public final void setDomain(final String domain) {
        this.domain = domain;
    }
    
    public final String getReason() {
        return this.reason;
    }
    
    public final void setReason(final String reason) {
        this.reason = reason;
    }
    
    public final String getMessage() {
        return this.message;
    }
    
    public final void setMessage(final String message) {
        this.message = message;
    }
    
    public final String getLocation() {
        return this.location;
    }
    
    public final void setLocation(final String location) {
        this.location = location;
    }
    
    public final String getLocationType() {
        return this.locationType;
    }
    
    public final void setLocationType(final String locationType) {
        this.locationType = locationType;
    }
    
    @Override
    public ErrorInfo set(final String s, final Object o) {
        return (ErrorInfo)super.set(s, o);
    }
    
    @Override
    public ErrorInfo clone() {
        return (ErrorInfo)super.clone();
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
