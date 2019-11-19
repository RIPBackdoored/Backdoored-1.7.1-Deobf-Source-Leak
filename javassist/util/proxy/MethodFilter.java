package javassist.util.proxy;

import java.lang.reflect.Method;

public interface MethodFilter
{
    boolean isHandled(final Method p0);
}
