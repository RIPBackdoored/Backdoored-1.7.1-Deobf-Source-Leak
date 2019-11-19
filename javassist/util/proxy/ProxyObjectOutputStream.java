package javassist.util.proxy;

import java.io.ObjectStreamClass;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;

public class ProxyObjectOutputStream extends ObjectOutputStream
{
    public ProxyObjectOutputStream(final OutputStream outputStream) throws IOException {
        super(outputStream);
    }
    
    @Override
    protected void writeClassDescriptor(final ObjectStreamClass objectStreamClass) throws IOException {
        final Class<?> forClass = objectStreamClass.forClass();
        if (ProxyFactory.isProxyClass(forClass)) {
            this.writeBoolean(true);
            final Class<?> superclass = forClass.getSuperclass();
            final Class[] interfaces = forClass.getInterfaces();
            final byte[] filterSignature = ProxyFactory.getFilterSignature(forClass);
            this.writeObject(superclass.getName());
            this.writeInt(interfaces.length - 1);
            for (int i = 0; i < interfaces.length; ++i) {
                final Class clazz = interfaces[i];
                if (clazz != ProxyObject.class && clazz != Proxy.class) {
                    this.writeObject(interfaces[i].getName());
                }
            }
            this.writeInt(filterSignature.length);
            this.write(filterSignature);
        }
        else {
            this.writeBoolean(false);
            super.writeClassDescriptor(objectStreamClass);
        }
    }
}
