package com.fasterxml.jackson.core.io;

import java.io.Writer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

public abstract class OutputDecorator implements Serializable
{
    public OutputDecorator() {
        super();
    }
    
    public abstract OutputStream decorate(final IOContext p0, final OutputStream p1) throws IOException;
    
    public abstract Writer decorate(final IOContext p0, final Writer p1) throws IOException;
}
