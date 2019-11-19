package com.fasterxml.jackson.core.util;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.Serializable;

public static class NopIndenter implements Indenter, Serializable
{
    public static final NopIndenter instance;
    
    public NopIndenter() {
        super();
    }
    
    @Override
    public void writeIndentation(final JsonGenerator jsonGenerator, final int n) throws IOException {
    }
    
    @Override
    public boolean isInline() {
        return true;
    }
    
    static {
        instance = new NopIndenter();
    }
}
