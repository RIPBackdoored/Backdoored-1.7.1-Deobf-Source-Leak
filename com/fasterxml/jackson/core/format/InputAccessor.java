package com.fasterxml.jackson.core.format;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.EOFException;
import java.io.InputStream;
import java.io.IOException;

public interface InputAccessor
{
    boolean hasMoreBytes() throws IOException;
    
    byte nextByte() throws IOException;
    
    void reset();
}
