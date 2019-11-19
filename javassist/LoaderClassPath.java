package javassist;

import java.net.URL;
import java.io.InputStream;
import java.lang.ref.WeakReference;

public class LoaderClassPath implements ClassPath
{
    private WeakReference clref;
    
    public LoaderClassPath(final ClassLoader classLoader) {
        super();
        this.clref = new WeakReference((T)classLoader);
    }
    
    @Override
    public String toString() {
        Object value = null;
        if (this.clref != null) {
            value = this.clref.get();
        }
        return (value == null) ? "<null>" : value.toString();
    }
    
    @Override
    public InputStream openClassfile(final String s) {
        final String string = s.replace('.', '/') + ".class";
        final ClassLoader classLoader = (ClassLoader)this.clref.get();
        if (classLoader == null) {
            return null;
        }
        return classLoader.getResourceAsStream(string);
    }
    
    @Override
    public URL find(final String s) {
        final String string = s.replace('.', '/') + ".class";
        final ClassLoader classLoader = (ClassLoader)this.clref.get();
        if (classLoader == null) {
            return null;
        }
        return classLoader.getResource(string);
    }
    
    @Override
    public void close() {
        this.clref = null;
    }
}
