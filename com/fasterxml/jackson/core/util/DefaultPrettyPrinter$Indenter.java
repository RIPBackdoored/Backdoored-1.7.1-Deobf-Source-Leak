package com.fasterxml.jackson.core.util;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;

public interface Indenter
{
    void writeIndentation(final JsonGenerator p0, final int p1) throws IOException;
    
    boolean isInline();
}
