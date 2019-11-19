package org.spongepowered.asm.launch;

import java.util.ServiceLoader;
import org.spongepowered.asm.service.IGlobalPropertyService;

public final class GlobalProperties
{
    private static IGlobalPropertyService service;
    
    private GlobalProperties() {
        super();
    }
    
    private static IGlobalPropertyService getService() {
        if (GlobalProperties.service == null) {
            GlobalProperties.service = ServiceLoader.<IGlobalPropertyService>load(IGlobalPropertyService.class, GlobalProperties.class.getClassLoader()).iterator().next();
        }
        return GlobalProperties.service;
    }
    
    public static <T> T get(final String s) {
        return getService().<T>getProperty(s);
    }
    
    public static void put(final String s, final Object o) {
        getService().setProperty(s, o);
    }
    
    public static <T> T get(final String s, final T t) {
        return getService().<T>getProperty(s, t);
    }
    
    public static String getString(final String s, final String s2) {
        return getService().getPropertyString(s, s2);
    }
}
