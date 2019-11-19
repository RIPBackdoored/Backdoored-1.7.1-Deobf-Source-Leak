package com.fasterxml.jackson.core.util;

import java.io.IOException;
import java.io.Serializable;

public class RequestPayload implements Serializable
{
    private static final long serialVersionUID = 1L;
    protected byte[] _payloadAsBytes;
    protected CharSequence _payloadAsText;
    protected String _charset;
    
    public RequestPayload(final byte[] payloadAsBytes, final String s) {
        super();
        if (payloadAsBytes == null) {
            throw new IllegalArgumentException();
        }
        this._payloadAsBytes = payloadAsBytes;
        this._charset = ((s == null || s.isEmpty()) ? "UTF-8" : s);
    }
    
    public RequestPayload(final CharSequence payloadAsText) {
        super();
        if (payloadAsText == null) {
            throw new IllegalArgumentException();
        }
        this._payloadAsText = payloadAsText;
    }
    
    public Object getRawPayload() {
        if (this._payloadAsBytes != null) {
            return this._payloadAsBytes;
        }
        return this._payloadAsText;
    }
    
    @Override
    public String toString() {
        if (this._payloadAsBytes != null) {
            try {
                return new String(this._payloadAsBytes, this._charset);
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return this._payloadAsText.toString();
    }
}
