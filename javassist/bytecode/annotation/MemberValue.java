package javassist.bytecode.annotation;

import java.io.IOException;
import javassist.bytecode.Descriptor;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

public abstract class MemberValue
{
    ConstPool cp;
    char tag;
    
    MemberValue(final char tag, final ConstPool cp) {
        super();
        this.cp = cp;
        this.tag = tag;
    }
    
    abstract Object getValue(final ClassLoader p0, final ClassPool p1, final Method p2) throws ClassNotFoundException;
    
    abstract Class getType(final ClassLoader p0) throws ClassNotFoundException;
    
    static Class loadClass(final ClassLoader classLoader, final String s) throws ClassNotFoundException, NoSuchClassError {
        try {
            return Class.forName(convertFromArray(s), true, classLoader);
        }
        catch (LinkageError linkageError) {
            throw new NoSuchClassError(s, linkageError);
        }
    }
    
    private static String convertFromArray(final String s) {
        int i = s.indexOf("[]");
        if (i != -1) {
            final StringBuffer sb = new StringBuffer(Descriptor.of(s.substring(0, i)));
            while (i != -1) {
                sb.insert(0, "[");
                i = s.indexOf("[]", i + 1);
            }
            return sb.toString().replace('/', '.');
        }
        return s;
    }
    
    public abstract void accept(final MemberValueVisitor p0);
    
    public abstract void write(final AnnotationsWriter p0) throws IOException;
}
