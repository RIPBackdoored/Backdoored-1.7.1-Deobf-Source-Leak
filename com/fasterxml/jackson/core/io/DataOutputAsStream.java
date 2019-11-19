package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.DataOutput;
import java.io.OutputStream;

public class DataOutputAsStream extends OutputStream
{
    protected final DataOutput _output;
    
    public DataOutputAsStream(final DataOutput output) {
        super();
        this._output = output;
    }
    
    @Override
    public void write(final int n) throws IOException {
        this._output.write(n);
    }
    
    @Override
    public void write(final byte[] array) throws IOException {
        this._output.write(array, 0, array.length);
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        this._output.write(array, n, n2);
    }
}
