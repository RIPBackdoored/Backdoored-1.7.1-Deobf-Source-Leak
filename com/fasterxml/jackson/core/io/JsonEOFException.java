package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonParseException;

public class JsonEOFException extends JsonParseException
{
    private static final long serialVersionUID = 1L;
    protected final JsonToken _token;
    
    public JsonEOFException(final JsonParser jsonParser, final JsonToken token, final String s) {
        super(jsonParser, s);
        this._token = token;
    }
    
    public JsonToken getTokenBeingDecoded() {
        return this._token;
    }
}
