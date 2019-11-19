package javassist;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;

static class DelayedFileOutputStream extends OutputStream
{
    private FileOutputStream file;
    private String filename;
    
    DelayedFileOutputStream(final String filename) {
        super();
        this.file = null;
        this.filename = filename;
    }
    
    private void init() throws IOException {
        if (this.file == null) {
            this.file = new FileOutputStream(this.filename);
        }
    }
    
    @Override
    public void write(final int n) throws IOException {
        this.init();
        this.file.write(n);
    }
    
    @Override
    public void write(final byte[] array) throws IOException {
        this.init();
        this.file.write(array);
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        this.init();
        this.file.write(array, n, n2);
    }
    
    @Override
    public void flush() throws IOException {
        this.init();
        this.file.flush();
    }
    
    @Override
    public void close() throws IOException {
        this.init();
        this.file.close();
    }
}
