package org.spongepowered.asm.service.mojang;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;
import net.minecraft.launchwrapper.LaunchClassLoader;

final class LaunchClassLoaderUtil
{
    private static final String CACHED_CLASSES_FIELD = "cachedClasses";
    private static final String INVALID_CLASSES_FIELD = "invalidClasses";
    private static final String CLASS_LOADER_EXCEPTIONS_FIELD = "classLoaderExceptions";
    private static final String TRANSFORMER_EXCEPTIONS_FIELD = "transformerExceptions";
    private final LaunchClassLoader classLoader;
    private final Map<String, Class<?>> cachedClasses;
    private final Set<String> invalidClasses;
    private final Set<String> classLoaderExceptions;
    private final Set<String> transformerExceptions;
    
    LaunchClassLoaderUtil(final LaunchClassLoader classLoader) {
        super();
        this.classLoader = classLoader;
        this.cachedClasses = LaunchClassLoaderUtil.<Map<String, Class<?>>>getField(classLoader, "cachedClasses");
        this.invalidClasses = LaunchClassLoaderUtil.<Set<String>>getField(classLoader, "invalidClasses");
        this.classLoaderExceptions = LaunchClassLoaderUtil.<Set<String>>getField(classLoader, "classLoaderExceptions");
        this.transformerExceptions = LaunchClassLoaderUtil.<Set<String>>getField(classLoader, "transformerExceptions");
    }
    
    LaunchClassLoader getClassLoader() {
        return this.classLoader;
    }
    
    boolean isClassLoaded(final String s) {
        return this.cachedClasses.containsKey(s);
    }
    
    boolean isClassExcluded(final String s, final String s2) {
        return this.isClassClassLoaderExcluded(s, s2) || this.isClassTransformerExcluded(s, s2);
    }
    
    boolean isClassClassLoaderExcluded(final String s, final String s2) {
        for (final String s3 : this.getClassLoaderExceptions()) {
            if ((s2 != null && s2.startsWith(s3)) || s.startsWith(s3)) {
                return true;
            }
        }
        return false;
    }
    
    boolean isClassTransformerExcluded(final String s, final String s2) {
        for (final String s3 : this.getTransformerExceptions()) {
            if ((s2 != null && s2.startsWith(s3)) || s.startsWith(s3)) {
                return true;
            }
        }
        return false;
    }
    
    void registerInvalidClass(final String s) {
        if (this.invalidClasses != null) {
            this.invalidClasses.add(s);
        }
    }
    
    Set<String> getClassLoaderExceptions() {
        if (this.classLoaderExceptions != null) {
            return this.classLoaderExceptions;
        }
        return Collections.<String>emptySet();
    }
    
    Set<String> getTransformerExceptions() {
        if (this.transformerExceptions != null) {
            return this.transformerExceptions;
        }
        return Collections.<String>emptySet();
    }
    
    private static <T> T getField(final LaunchClassLoader launchClassLoader, final String s) {
        try {
            final Field declaredField = LaunchClassLoader.class.getDeclaredField(s);
            declaredField.setAccessible(true);
            return (T)declaredField.get(launchClassLoader);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
