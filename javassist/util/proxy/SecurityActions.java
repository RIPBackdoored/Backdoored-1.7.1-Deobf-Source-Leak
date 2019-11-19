package javassist.util.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.AccessibleObject;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.lang.reflect.Constructor;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.lang.reflect.Method;

class SecurityActions
{
    SecurityActions() {
        super();
    }
    
    static Method[] getDeclaredMethods(final Class clazz) {
        if (System.getSecurityManager() == null) {
            return clazz.getDeclaredMethods();
        }
        return AccessController.<Method[]>doPrivileged((PrivilegedAction<Method[]>)new PrivilegedAction() {
            final /* synthetic */ Class val$clazz;
            
            SecurityActions$1() {
                super();
            }
            
            @Override
            public Object run() {
                return clazz.getDeclaredMethods();
            }
        });
    }
    
    static Constructor[] getDeclaredConstructors(final Class clazz) {
        if (System.getSecurityManager() == null) {
            return clazz.getDeclaredConstructors();
        }
        return AccessController.<Constructor[]>doPrivileged((PrivilegedAction<Constructor[]>)new PrivilegedAction() {
            final /* synthetic */ Class val$clazz;
            
            SecurityActions$2() {
                super();
            }
            
            @Override
            public Object run() {
                return clazz.getDeclaredConstructors();
            }
        });
    }
    
    static Method getDeclaredMethod(final Class clazz, final String s, final Class[] array) throws NoSuchMethodException {
        if (System.getSecurityManager() == null) {
            return clazz.getDeclaredMethod(s, (Class[])array);
        }
        try {
            return AccessController.<Method>doPrivileged((PrivilegedExceptionAction<Method>)new PrivilegedExceptionAction() {
                final /* synthetic */ Class val$clazz;
                final /* synthetic */ String val$name;
                final /* synthetic */ Class[] val$types;
                
                SecurityActions$3() {
                    super();
                }
                
                @Override
                public Object run() throws Exception {
                    return clazz.getDeclaredMethod(s, (Class[])array);
                }
            });
        }
        catch (PrivilegedActionException ex) {
            if (ex.getCause() instanceof NoSuchMethodException) {
                throw (NoSuchMethodException)ex.getCause();
            }
            throw new RuntimeException(ex.getCause());
        }
    }
    
    static Constructor getDeclaredConstructor(final Class clazz, final Class[] array) throws NoSuchMethodException {
        if (System.getSecurityManager() == null) {
            return clazz.getDeclaredConstructor((Class[])array);
        }
        try {
            return AccessController.<Constructor>doPrivileged((PrivilegedExceptionAction<Constructor>)new PrivilegedExceptionAction() {
                final /* synthetic */ Class val$clazz;
                final /* synthetic */ Class[] val$types;
                
                SecurityActions$4() {
                    super();
                }
                
                @Override
                public Object run() throws Exception {
                    return clazz.getDeclaredConstructor((Class[])array);
                }
            });
        }
        catch (PrivilegedActionException ex) {
            if (ex.getCause() instanceof NoSuchMethodException) {
                throw (NoSuchMethodException)ex.getCause();
            }
            throw new RuntimeException(ex.getCause());
        }
    }
    
    static void setAccessible(final AccessibleObject accessibleObject, final boolean accessible) {
        if (System.getSecurityManager() == null) {
            accessibleObject.setAccessible(accessible);
        }
        else {
            AccessController.<Object>doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
                final /* synthetic */ AccessibleObject val$ao;
                final /* synthetic */ boolean val$accessible;
                
                SecurityActions$5() {
                    super();
                }
                
                @Override
                public Object run() {
                    accessibleObject.setAccessible(accessible);
                    return null;
                }
            });
        }
    }
    
    static void set(final Field field, final Object o, final Object o2) throws IllegalAccessException {
        if (System.getSecurityManager() == null) {
            field.set(o, o2);
        }
        else {
            try {
                AccessController.<Object>doPrivileged((PrivilegedExceptionAction<Object>)new PrivilegedExceptionAction() {
                    final /* synthetic */ Field val$fld;
                    final /* synthetic */ Object val$target;
                    final /* synthetic */ Object val$value;
                    
                    SecurityActions$6() {
                        super();
                    }
                    
                    @Override
                    public Object run() throws Exception {
                        field.set(o, o2);
                        return null;
                    }
                });
            }
            catch (PrivilegedActionException ex) {
                if (ex.getCause() instanceof NoSuchMethodException) {
                    throw (IllegalAccessException)ex.getCause();
                }
                throw new RuntimeException(ex.getCause());
            }
        }
    }
}
