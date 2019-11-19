package javassist.util.proxy;

import java.io.ObjectStreamException;
import java.io.InvalidObjectException;
import java.io.InvalidClassException;
import java.security.PrivilegedActionException;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.io.Serializable;

class SerializedProxy implements Serializable
{
    private String superClass;
    private String[] interfaces;
    private byte[] filterSignature;
    private MethodHandler handler;
    
    SerializedProxy(final Class clazz, final byte[] filterSignature, final MethodHandler handler) {
        super();
        this.filterSignature = filterSignature;
        this.handler = handler;
        this.superClass = clazz.getSuperclass().getName();
        final Class[] interfaces = clazz.getInterfaces();
        final int length = interfaces.length;
        this.interfaces = new String[length - 1];
        final String name = ProxyObject.class.getName();
        final String name2 = Proxy.class.getName();
        for (int i = 0; i < length; ++i) {
            final String name3 = interfaces[i].getName();
            if (!name3.equals(name) && !name3.equals(name2)) {
                this.interfaces[i] = name3;
            }
        }
    }
    
    protected Class loadClass(final String s) throws ClassNotFoundException {
        try {
            return AccessController.<Class>doPrivileged((PrivilegedExceptionAction<Class>)new PrivilegedExceptionAction() {
                final /* synthetic */ String val$className;
                final /* synthetic */ SerializedProxy this$0;
                
                SerializedProxy$1() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public Object run() throws Exception {
                    return Class.forName(s, true, Thread.currentThread().getContextClassLoader());
                }
            });
        }
        catch (PrivilegedActionException ex) {
            throw new RuntimeException("cannot load the class: " + s, ex.getException());
        }
    }
    
    Object readResolve() throws ObjectStreamException {
        try {
            final int length = this.interfaces.length;
            final Class[] interfaces = new Class[length];
            for (int i = 0; i < length; ++i) {
                interfaces[i] = this.loadClass(this.interfaces[i]);
            }
            final ProxyFactory proxyFactory = new ProxyFactory();
            proxyFactory.setSuperclass(this.loadClass(this.superClass));
            proxyFactory.setInterfaces(interfaces);
            final Proxy proxy = proxyFactory.createClass(this.filterSignature).newInstance();
            proxy.setHandler(this.handler);
            return proxy;
        }
        catch (ClassNotFoundException ex) {
            throw new InvalidClassException(ex.getMessage());
        }
        catch (InstantiationException ex2) {
            throw new InvalidObjectException(ex2.getMessage());
        }
        catch (IllegalAccessException ex3) {
            throw new InvalidClassException(ex3.getMessage());
        }
    }
}
