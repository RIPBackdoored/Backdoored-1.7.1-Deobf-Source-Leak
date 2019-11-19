package javassist;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ByteArrayClassPath implements ClassPath
{
    protected String classname;
    protected byte[] classfile;
    
    public ByteArrayClassPath(final String classname, final byte[] classfile) {
        super();
        this.classname = classname;
        this.classfile = classfile;
    }
    
    @Override
    public void close() {
    }
    
    @Override
    public String toString() {
        return "byte[]:" + this.classname;
    }
    
    @Override
    public InputStream openClassfile(final String s) {
        if (this.classname.equals(s)) {
            return new ByteArrayInputStream(this.classfile);
        }
        return null;
    }
    
    @Override
    public URL find(final String s) {
        if (this.classname.equals(s)) {
            final String string = s.replace('.', '/') + ".class";
            try {
                return new URL("file:/ByteArrayClassPath/" + string);
            }
            catch (MalformedURLException ex) {}
        }
        return null;
    }
}
