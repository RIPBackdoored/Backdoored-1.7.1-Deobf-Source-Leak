package com.fasterxml.jackson.core;

public class JsonGenerationException extends JsonProcessingException
{
    private static final long serialVersionUID = 123L;
    protected transient JsonGenerator _processor;
    
    @Deprecated
    public JsonGenerationException(final Throwable t) {
        super(t);
    }
    
    @Deprecated
    public JsonGenerationException(final String s) {
        super(s, (JsonLocation)null);
    }
    
    @Deprecated
    public JsonGenerationException(final String s, final Throwable t) {
        super(s, null, t);
    }
    
    public JsonGenerationException(final Throwable t, final JsonGenerator processor) {
        super(t);
        this._processor = processor;
    }
    
    public JsonGenerationException(final String s, final JsonGenerator processor) {
        super(s, (JsonLocation)null);
        this._processor = processor;
    }
    
    public JsonGenerationException(final String s, final Throwable t, final JsonGenerator processor) {
        super(s, null, t);
        this._processor = processor;
    }
    
    public JsonGenerationException withGenerator(final JsonGenerator processor) {
        this._processor = processor;
        return this;
    }
    
    @Override
    public JsonGenerator getProcessor() {
        return this._processor;
    }
    
    @Override
    public /* bridge */ Object getProcessor() {
        return this.getProcessor();
    }
}
