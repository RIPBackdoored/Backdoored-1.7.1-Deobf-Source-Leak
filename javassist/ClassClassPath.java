package javassist;

import java.net.URL;
import java.io.InputStream;

public class ClassClassPath implements ClassPath
{
    private Class thisClass;
    
    public ClassClassPath(final Class thisClass) {
        super();
        this.thisClass = thisClass;
    }
    
    ClassClassPath() {
        this(Object.class);
    }
    
    @Override
    public InputStream openClassfile(final String s) {
        return this.thisClass.getResourceAsStream("/" + s.replace('.', '/') + ".class");
    }
    
    @Override
    public URL find(final String s) {
        return this.thisClass.getResource("/" + s.replace('.', '/') + ".class");
    }
    
    @Override
    public void close() {
    }
    
    @Override
    public String toString() {
        return this.thisClass.getName() + ".class";
    }
}
