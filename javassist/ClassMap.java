package javassist;

import javassist.bytecode.Descriptor;
import java.util.HashMap;

public class ClassMap extends HashMap
{
    private ClassMap parent;
    
    public ClassMap() {
        super();
        this.parent = null;
    }
    
    ClassMap(final ClassMap parent) {
        super();
        this.parent = parent;
    }
    
    public void put(final CtClass ctClass, final CtClass ctClass2) {
        this.put(ctClass.getName(), ctClass2.getName());
    }
    
    public void put(final String s, final String s2) {
        if (s == s2) {
            return;
        }
        final String jvmName = toJvmName(s);
        final String s3 = (String)this.get(jvmName);
        if (s3 == null || !s3.equals(jvmName)) {
            super.put(jvmName, toJvmName(s2));
        }
    }
    
    public void putIfNone(final String s, final String s2) {
        if (s == s2) {
            return;
        }
        final String jvmName = toJvmName(s);
        if (this.get(jvmName) == null) {
            super.put(jvmName, toJvmName(s2));
        }
    }
    
    protected final void put0(final Object o, final Object o2) {
        super.put(o, o2);
    }
    
    @Override
    public Object get(final Object o) {
        final Object value = super.get(o);
        if (value == null && this.parent != null) {
            return this.parent.get(o);
        }
        return value;
    }
    
    public void fix(final CtClass ctClass) {
        this.fix(ctClass.getName());
    }
    
    public void fix(final String s) {
        final String jvmName = toJvmName(s);
        super.put(jvmName, jvmName);
    }
    
    public static String toJvmName(final String s) {
        return Descriptor.toJvmName(s);
    }
    
    public static String toJavaName(final String s) {
        return Descriptor.toJavaName(s);
    }
}
