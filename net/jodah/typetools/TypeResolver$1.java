package net.jodah.typetools;

import java.lang.reflect.Field;
import sun.misc.Unsafe;
import java.security.PrivilegedExceptionAction;

static final class TypeResolver$1 implements PrivilegedExceptionAction<Unsafe> {
    TypeResolver$1() {
        super();
    }
    
    @Override
    public Unsafe run() throws Exception {
        final Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
        declaredField.setAccessible(true);
        return (Unsafe)declaredField.get(null);
    }
    
    @Override
    public /* bridge */ Object run() throws Exception {
        return this.run();
    }
}