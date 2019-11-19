package javassist.util.proxy;

import java.lang.ref.WeakReference;

static class ProxyDetails
{
    byte[] signature;
    WeakReference proxyClass;
    boolean isUseWriteReplace;
    
    ProxyDetails(final byte[] signature, final Class clazz, final boolean isUseWriteReplace) {
        super();
        this.signature = signature;
        this.proxyClass = new WeakReference((T)clazz);
        this.isUseWriteReplace = isUseWriteReplace;
    }
}
