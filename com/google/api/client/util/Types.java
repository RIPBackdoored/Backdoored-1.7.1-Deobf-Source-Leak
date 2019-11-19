package com.google.api.client.util;

import java.lang.reflect.GenericDeclaration;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.Map;
import java.lang.reflect.Array;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.lang.reflect.WildcardType;
import java.lang.reflect.GenericArrayType;
import java.util.Iterator;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Types
{
    public static ParameterizedType getSuperParameterizedType(Type genericSuperclass, final Class<?> clazz) {
        if (genericSuperclass instanceof Class || genericSuperclass instanceof ParameterizedType) {
        Label_0014:
            while (genericSuperclass != null && genericSuperclass != Object.class) {
                Class<?> rawClass;
                if (genericSuperclass instanceof Class) {
                    rawClass = (Class<?>)genericSuperclass;
                }
                else {
                    final ParameterizedType parameterizedType = (ParameterizedType)genericSuperclass;
                    rawClass = getRawClass(parameterizedType);
                    if (rawClass == clazz) {
                        return parameterizedType;
                    }
                    if (clazz.isInterface()) {
                        for (final Type type : rawClass.getGenericInterfaces()) {
                            if (clazz.isAssignableFrom((type instanceof Class) ? ((Class<?>)type) : getRawClass((ParameterizedType)type))) {
                                genericSuperclass = type;
                                continue Label_0014;
                            }
                        }
                    }
                }
                genericSuperclass = rawClass.getGenericSuperclass();
            }
        }
        return null;
    }
    
    public static boolean isAssignableToOrFrom(final Class<?> clazz, final Class<?> clazz2) {
        return clazz.isAssignableFrom(clazz2) || clazz2.isAssignableFrom(clazz);
    }
    
    public static <T> T newInstance(final Class<T> clazz) {
        try {
            return clazz.newInstance();
        }
        catch (IllegalAccessException ex) {
            throw handleExceptionForNewInstance(ex, clazz);
        }
        catch (InstantiationException ex2) {
            throw handleExceptionForNewInstance(ex2, clazz);
        }
    }
    
    private static IllegalArgumentException handleExceptionForNewInstance(final Exception ex, final Class<?> clazz) {
        final StringBuilder append = new StringBuilder("unable to create new instance of class ").append(clazz.getName());
        final ArrayList<String> list = new ArrayList<String>();
        if (clazz.isArray()) {
            list.add("because it is an array");
        }
        else if (clazz.isPrimitive()) {
            list.add("because it is primitive");
        }
        else if (clazz == Void.class) {
            list.add("because it is void");
        }
        else {
            if (Modifier.isInterface(clazz.getModifiers())) {
                list.add("because it is an interface");
            }
            else if (Modifier.isAbstract(clazz.getModifiers())) {
                list.add("because it is abstract");
            }
            if (clazz.getEnclosingClass() != null && !Modifier.isStatic(clazz.getModifiers())) {
                list.add("because it is not static");
            }
            if (!Modifier.isPublic(clazz.getModifiers())) {
                list.add("possibly because it is not public");
            }
            else {
                try {
                    clazz.getConstructor((Class<?>[])new Class[0]);
                }
                catch (NoSuchMethodException ex2) {
                    list.add("because it has no accessible default constructor");
                }
            }
        }
        int n = 0;
        for (final String s : list) {
            if (n != 0) {
                append.append(" and");
            }
            else {
                n = 1;
            }
            append.append(" ").append(s);
        }
        return new IllegalArgumentException(append.toString(), ex);
    }
    
    public static boolean isArray(final Type type) {
        return type instanceof GenericArrayType || (type instanceof Class && ((Class)type).isArray());
    }
    
    public static Type getArrayComponentType(final Type type) {
        return (type instanceof GenericArrayType) ? ((GenericArrayType)type).getGenericComponentType() : ((Class)type).getComponentType();
    }
    
    public static Class<?> getRawClass(final ParameterizedType parameterizedType) {
        return (Class<?>)parameterizedType.getRawType();
    }
    
    public static Type getBound(final WildcardType wildcardType) {
        final Type[] lowerBounds = wildcardType.getLowerBounds();
        if (lowerBounds.length != 0) {
            return lowerBounds[0];
        }
        return wildcardType.getUpperBounds()[0];
    }
    
    public static Type resolveTypeVariable(final List<Type> list, final TypeVariable<?> typeVariable) {
        final Object genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            Class<?> clazz;
            int size;
            ParameterizedType superParameterizedType;
            for (clazz = (Class<?>)genericDeclaration, size = list.size(), superParameterizedType = null; superParameterizedType == null && --size >= 0; superParameterizedType = getSuperParameterizedType(list.get(size), clazz)) {}
            if (superParameterizedType != null) {
                TypeVariable<?>[] typeParameters;
                int n;
                for (typeParameters = ((GenericDeclaration)genericDeclaration).getTypeParameters(), n = 0; n < typeParameters.length && !typeParameters[n].equals(typeVariable); ++n) {}
                final Type type = superParameterizedType.getActualTypeArguments()[n];
                if (type instanceof TypeVariable) {
                    final Type resolveTypeVariable = resolveTypeVariable(list, (TypeVariable<?>)type);
                    if (resolveTypeVariable != null) {
                        return resolveTypeVariable;
                    }
                }
                return type;
            }
        }
        return null;
    }
    
    public static Class<?> getRawArrayComponentType(final List<Type> list, Type resolveTypeVariable) {
        if (resolveTypeVariable instanceof TypeVariable) {
            resolveTypeVariable = resolveTypeVariable(list, (TypeVariable<?>)resolveTypeVariable);
        }
        if (resolveTypeVariable instanceof GenericArrayType) {
            return Array.newInstance(getRawArrayComponentType(list, getArrayComponentType(resolveTypeVariable)), 0).getClass();
        }
        if (resolveTypeVariable instanceof Class) {
            return (Class<?>)resolveTypeVariable;
        }
        if (resolveTypeVariable instanceof ParameterizedType) {
            return getRawClass((ParameterizedType)resolveTypeVariable);
        }
        Preconditions.checkArgument(resolveTypeVariable == null, "wildcard type is not supported: %s", resolveTypeVariable);
        return Object.class;
    }
    
    public static Type getIterableParameter(final Type type) {
        return getActualParameterAtPosition(type, Iterable.class, 0);
    }
    
    public static Type getMapValueParameter(final Type type) {
        return getActualParameterAtPosition(type, Map.class, 1);
    }
    
    private static Type getActualParameterAtPosition(final Type type, final Class<?> clazz, final int n) {
        final ParameterizedType superParameterizedType = getSuperParameterizedType(type, clazz);
        if (superParameterizedType == null) {
            return null;
        }
        final Type type2 = superParameterizedType.getActualTypeArguments()[n];
        if (type2 instanceof TypeVariable) {
            final Type resolveTypeVariable = resolveTypeVariable(Arrays.<Type>asList(type), (TypeVariable<?>)type2);
            if (resolveTypeVariable != null) {
                return resolveTypeVariable;
            }
        }
        return type2;
    }
    
    public static <T> Iterable<T> iterableOf(final Object o) {
        if (o instanceof Iterable) {
            return (Iterable<T>)o;
        }
        final Class<?> class1 = o.getClass();
        Preconditions.checkArgument(class1.isArray(), "not an array or Iterable: %s", class1);
        if (!class1.getComponentType().isPrimitive()) {
            return (Iterable<T>)Arrays.<Object>asList((Object[])o);
        }
        return new Iterable<T>() {
            final /* synthetic */ Object val$value;
            
            Types$1() {
                super();
            }
            
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    final int length = Array.getLength(o);
                    int index = 0;
                    final /* synthetic */ Types$1 this$0;
                    
                    Types$1$1() {
                        this.this$0 = this$0;
                        super();
                    }
                    
                    @Override
                    public boolean hasNext() {
                        return this.index < this.length;
                    }
                    
                    @Override
                    public T next() {
                        if (!this.hasNext()) {
                            throw new NoSuchElementException();
                        }
                        return (T)Array.get(o, this.index++);
                    }
                    
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
    
    public static Object toArray(final Collection<?> collection, final Class<?> clazz) {
        if (clazz.isPrimitive()) {
            final Object instance = Array.newInstance(clazz, collection.size());
            int n = 0;
            final Iterator<?> iterator = collection.iterator();
            while (iterator.hasNext()) {
                Array.set(instance, n++, iterator.next());
            }
            return instance;
        }
        return collection.<Object>toArray((Object[])Array.newInstance(clazz, collection.size()));
    }
    
    private Types() {
        super();
    }
}
