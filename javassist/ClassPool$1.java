package javassist;

import java.net.URL;
import java.security.ProtectionDomain;
import java.security.PrivilegedExceptionAction;

static final class ClassPool$1 implements PrivilegedExceptionAction {
    ClassPool$1() {
        super();
    }
    
    @Override
    public Object run() throws Exception {
        final Class<?> forName = Class.forName("java.lang.ClassLoader");
        ClassPool.access$002(forName.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE));
        ClassPool.access$102(forName.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class));
        ClassPool.access$202(forName.getDeclaredMethod("definePackage", String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class));
        return null;
    }
}