package org.reflections.util;

import com.google.common.base.Joiner;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.io.IOException;
import org.reflections.Reflections;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.HashSet;
import com.google.common.collect.Sets;
import java.lang.reflect.Method;
import java.util.Set;
import org.reflections.ReflectionsException;
import org.reflections.ReflectionUtils;
import java.util.ArrayList;
import java.lang.reflect.Member;
import java.io.File;

public abstract class Utils
{
    public Utils() {
        super();
    }
    
    public static String repeat(final String s, final int n) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append(s);
        }
        return sb.toString();
    }
    
    public static boolean isEmpty(final String s) {
        return s == null || s.length() == 0;
    }
    
    public static boolean isEmpty(final Object[] array) {
        return array == null || array.length == 0;
    }
    
    public static File prepareFile(final String s) {
        final File file = new File(s);
        final File parentFile = file.getAbsoluteFile().getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        return file;
    }
    
    public static Member getMemberFromDescriptor(final String s, final ClassLoader... array) throws ReflectionsException {
        final int lastIndex = s.lastIndexOf(40);
        final String s2 = (lastIndex != -1) ? s.substring(0, lastIndex) : s;
        final String s3 = (lastIndex != -1) ? s.substring(lastIndex + 1, s.lastIndexOf(41)) : "";
        final int max = Math.max(s2.lastIndexOf(46), s2.lastIndexOf("$"));
        final String substring = s2.substring(s2.lastIndexOf(32) + 1, max);
        final String substring2 = s2.substring(max + 1);
        Class<?>[] array2 = null;
        if (!isEmpty(s3)) {
            final String[] split = s3.split(",");
            final ArrayList list = new ArrayList<Class<?>>(split.length);
            final String[] array3 = split;
            for (int length = array3.length, i = 0; i < length; ++i) {
                list.add(ReflectionUtils.forName(array3[i].trim(), array));
            }
            array2 = list.<Class<?>>toArray(new Class[list.size()]);
        }
        Class<?> clazz = ReflectionUtils.forName(substring, array);
        while (clazz != null) {
            try {
                if (!s.contains("(")) {
                    return clazz.isInterface() ? clazz.getField(substring2) : clazz.getDeclaredField(substring2);
                }
                if (isConstructor(s)) {
                    return clazz.isInterface() ? clazz.getConstructor(array2) : clazz.getDeclaredConstructor(array2);
                }
                return clazz.isInterface() ? clazz.getMethod(substring2, array2) : clazz.getDeclaredMethod(substring2, array2);
            }
            catch (Exception ex) {
                clazz = clazz.getSuperclass();
                continue;
            }
            break;
        }
        throw new ReflectionsException("Can't resolve member named " + substring2 + " for class " + substring);
    }
    
    public static Set<Method> getMethodsFromDescriptors(final Iterable<String> iterable, final ClassLoader... array) {
        final HashSet<Method> hashSet = Sets.<Method>newHashSet();
        for (final String s : iterable) {
            if (!isConstructor(s)) {
                final Method method = (Method)getMemberFromDescriptor(s, array);
                if (method == null) {
                    continue;
                }
                hashSet.add(method);
            }
        }
        return hashSet;
    }
    
    public static Set<Constructor> getConstructorsFromDescriptors(final Iterable<String> iterable, final ClassLoader... array) {
        final HashSet<Constructor> hashSet = (HashSet<Constructor>)Sets.<Object>newHashSet();
        for (final String s : iterable) {
            if (isConstructor(s)) {
                final Constructor constructor = (Constructor)getMemberFromDescriptor(s, array);
                if (constructor == null) {
                    continue;
                }
                hashSet.add(constructor);
            }
        }
        return (Set<Constructor>)hashSet;
    }
    
    public static Set<Member> getMembersFromDescriptors(final Iterable<String> iterable, final ClassLoader... array) {
        final HashSet<Member> hashSet = Sets.<Member>newHashSet();
        for (final String s : iterable) {
            try {
                hashSet.add(getMemberFromDescriptor(s, array));
            }
            catch (ReflectionsException ex) {
                throw new ReflectionsException("Can't resolve member named " + s, ex);
            }
        }
        return hashSet;
    }
    
    public static Field getFieldFromString(final String s, final ClassLoader... array) {
        final String substring = s.substring(0, s.lastIndexOf(46));
        final String substring2 = s.substring(s.lastIndexOf(46) + 1);
        try {
            return ReflectionUtils.forName(substring, array).getDeclaredField(substring2);
        }
        catch (NoSuchFieldException ex) {
            throw new ReflectionsException("Can't resolve field named " + substring2, ex);
        }
    }
    
    public static void close(final InputStream inputStream) {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        catch (IOException ex) {
            if (Reflections.log != null) {
                Reflections.log.warn("Could not close InputStream", ex);
            }
        }
    }
    
    @Nullable
    public static Logger findLogger(final Class<?> clazz) {
        try {
            Class.forName("org.slf4j.impl.StaticLoggerBinder");
            return LoggerFactory.getLogger(clazz);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static boolean isConstructor(final String s) {
        return s.contains("init>");
    }
    
    public static String name(Class componentType) {
        if (!componentType.isArray()) {
            return componentType.getName();
        }
        int n = 0;
        while (componentType.isArray()) {
            ++n;
            componentType = componentType.getComponentType();
        }
        return componentType.getName() + repeat("[]", n);
    }
    
    public static List<String> names(final Iterable<Class<?>> iterable) {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<Class<?>> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            list.add(name(iterator.next()));
        }
        return list;
    }
    
    public static List<String> names(final Class<?>... array) {
        return names(Arrays.<Class<?>>asList(array));
    }
    
    public static String name(final Constructor constructor) {
        return constructor.getName() + ".<init>(" + Joiner.on(", ").join(names((Class<?>[])constructor.getParameterTypes())) + ")";
    }
    
    public static String name(final Method method) {
        return method.getDeclaringClass().getName() + "." + method.getName() + "(" + Joiner.on(", ").join(names(method.getParameterTypes())) + ")";
    }
    
    public static String name(final Field field) {
        return field.getDeclaringClass().getName() + "." + field.getName();
    }
}
