package com.fasterxml.jackson.core.util;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;

public static class FixedSpaceIndenter extends NopIndenter
{
    public static final FixedSpaceIndenter instance;
    
    public FixedSpaceIndenter() {
        super();
    }
    
    @Override
    public void writeIndentation(final JsonGenerator jsonGenerator, final int n) throws IOException {
        jsonGenerator.writeRaw(' ');
    }
    
    @Override
    public boolean isInline() {
        return true;
    }
    
    static {
        instance = new FixedSpaceIndenter();
    }
}
