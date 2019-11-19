package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.util.RequestPayload;

public class JsonParseException extends JsonProcessingException
{
    private static final long serialVersionUID = 2L;
    protected transient JsonParser _processor;
    protected RequestPayload _requestPayload;
    
    @Deprecated
    public JsonParseException(final String s, final JsonLocation jsonLocation) {
        super(s, jsonLocation);
    }
    
    @Deprecated
    public JsonParseException(final String s, final JsonLocation jsonLocation, final Throwable t) {
        super(s, jsonLocation, t);
    }
    
    public JsonParseException(final JsonParser processor, final String s) {
        super(s, (processor == null) ? null : processor.getCurrentLocation());
        this._processor = processor;
    }
    
    public JsonParseException(final JsonParser processor, final String s, final Throwable t) {
        super(s, (processor == null) ? null : processor.getCurrentLocation(), t);
        this._processor = processor;
    }
    
    public JsonParseException(final JsonParser processor, final String s, final JsonLocation jsonLocation) {
        super(s, jsonLocation);
        this._processor = processor;
    }
    
    public JsonParseException(final JsonParser processor, final String s, final JsonLocation jsonLocation, final Throwable t) {
        super(s, jsonLocation, t);
        this._processor = processor;
    }
    
    public JsonParseException withParser(final JsonParser processor) {
        this._processor = processor;
        return this;
    }
    
    public JsonParseException withRequestPayload(final RequestPayload requestPayload) {
        this._requestPayload = requestPayload;
        return this;
    }
    
    @Override
    public JsonParser getProcessor() {
        return this._processor;
    }
    
    public RequestPayload getRequestPayload() {
        return this._requestPayload;
    }
    
    public String getRequestPayloadAsString() {
        return (this._requestPayload != null) ? this._requestPayload.toString() : null;
    }
    
    @Override
    public String getMessage() {
        String s = super.getMessage();
        if (this._requestPayload != null) {
            s = s + "\nRequest payload : " + this._requestPayload.toString();
        }
        return s;
    }
    
    @Override
    public /* bridge */ Object getProcessor() {
        return this.getProcessor();
    }
}
