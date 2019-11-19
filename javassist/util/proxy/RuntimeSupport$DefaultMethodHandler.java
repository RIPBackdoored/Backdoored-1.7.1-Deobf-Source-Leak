package javassist.util.proxy;

import java.lang.reflect.Method;
import java.io.Serializable;

static class DefaultMethodHandler implements MethodHandler, Serializable
{
    DefaultMethodHandler() {
        super();
    }
    
    @Override
    public Object invoke(final Object o, final Method method, final Method method2, final Object[] array) throws Exception {
        return method2.invoke(o, array);
    }
}
