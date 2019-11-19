package com.google.api.client.util;

import java.lang.reflect.TypeVariable;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.HashSet;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.WildcardType;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;
import java.lang.reflect.Array;
import java.util.concurrent.ConcurrentHashMap;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Data
{
    public static final Boolean NULL_BOOLEAN;
    public static final String NULL_STRING;
    public static final Character NULL_CHARACTER;
    public static final Byte NULL_BYTE;
    public static final Short NULL_SHORT;
    public static final Integer NULL_INTEGER;
    public static final Float NULL_FLOAT;
    public static final Long NULL_LONG;
    public static final Double NULL_DOUBLE;
    public static final BigInteger NULL_BIG_INTEGER;
    public static final BigDecimal NULL_BIG_DECIMAL;
    public static final DateTime NULL_DATE_TIME;
    private static final ConcurrentHashMap<Class<?>, Object> NULL_CACHE;
    
    public Data() {
        super();
    }
    
    public static <T> T nullOf(final Class<?> clazz) {
        Object o = Data.NULL_CACHE.get(clazz);
        if (o == null) {
            synchronized (Data.NULL_CACHE) {
                o = Data.NULL_CACHE.get(clazz);
                if (o == null) {
                    if (clazz.isArray()) {
                        int n = 0;
                        Class<?> componentType = clazz;
                        do {
                            componentType = (Class<?>)componentType.getComponentType();
                            ++n;
                        } while (componentType.isArray());
                        o = Array.newInstance(componentType, new int[n]);
                    }
                    else if (clazz.isEnum()) {
                        final FieldInfo fieldInfo = ClassInfo.of(clazz).getFieldInfo(null);
                        Preconditions.<FieldInfo>checkNotNull(fieldInfo, "enum missing constant with @NullValue annotation: %s", clazz);
                        o = fieldInfo.<Enum>enumValue();
                    }
                    else {
                        o = Types.<T>newInstance(clazz);
                    }
                    Data.NULL_CACHE.put(clazz, o);
                }
            }
        }
        return (T)o;
    }
    
    public static boolean isNull(final Object o) {
        return o != null && o == Data.NULL_CACHE.get(o.getClass());
    }
    
    public static Map<String, Object> mapOf(final Object o) {
        if (o == null || isNull(o)) {
            return Collections.<String, Object>emptyMap();
        }
        if (o instanceof Map) {
            return (Map<String, Object>)o;
        }
        return new DataMap(o, false);
    }
    
    public static <T> T clone(final T t) {
        if (t == null || isPrimitive(t.getClass())) {
            return t;
        }
        if (t instanceof GenericData) {
            return (T)((GenericData)t).clone();
        }
        final Class<?> class1 = t.getClass();
        Object o;
        if (class1.isArray()) {
            o = Array.newInstance(class1.getComponentType(), Array.getLength(t));
        }
        else if (t instanceof ArrayMap) {
            o = ((ArrayMap)t).clone();
        }
        else {
            if ("java.util.Arrays$ArrayList".equals(class1.getName())) {
                final Object[] array = ((List)t).toArray();
                deepCopy(array, array);
                return (T)Arrays.<Object>asList(array);
            }
            o = Types.<T>newInstance(class1);
        }
        deepCopy(t, o);
        return (T)o;
    }
    
    public static void deepCopy(final Object o, final Object o2) {
        final Class<?> class1 = o.getClass();
        Preconditions.checkArgument(class1 == o2.getClass());
        if (class1.isArray()) {
            Preconditions.checkArgument(Array.getLength(o) == Array.getLength(o2));
            int n = 0;
            final Iterator<T> iterator = Types.<T>iterableOf(o).iterator();
            while (iterator.hasNext()) {
                Array.set(o2, n++, Data.<Object>clone((Object)iterator.next()));
            }
        }
        else if (Collection.class.isAssignableFrom(class1)) {
            final Collection collection = (Collection)o;
            if (ArrayList.class.isAssignableFrom(class1)) {
                ((ArrayList)o2).ensureCapacity(collection.size());
            }
            final Collection collection2 = (Collection)o2;
            final Iterator<T> iterator2 = collection.iterator();
            while (iterator2.hasNext()) {
                collection2.add(Data.<Object>clone((Object)iterator2.next()));
            }
        }
        else {
            final boolean assignable = GenericData.class.isAssignableFrom(class1);
            if (!Map.class.isAssignableFrom(class1)) {
                final ClassInfo classInfo = assignable ? ((GenericData)o).classInfo : ClassInfo.of(class1);
                final Iterator<String> iterator3 = classInfo.names.iterator();
                while (iterator3.hasNext()) {
                    final FieldInfo fieldInfo = classInfo.getFieldInfo(iterator3.next());
                    if (!fieldInfo.isFinal() && (!assignable || !fieldInfo.isPrimitive())) {
                        final Object value = fieldInfo.getValue(o);
                        if (value == null) {
                            continue;
                        }
                        fieldInfo.setValue(o2, Data.<Object>clone(value));
                    }
                }
            }
            else if (ArrayMap.class.isAssignableFrom(class1)) {
                final ArrayMap arrayMap = (ArrayMap)o2;
                final ArrayMap arrayMap2 = (ArrayMap)o;
                for (int size = arrayMap2.size(), i = 0; i < size; ++i) {
                    arrayMap.set(i, Data.<Object>clone(arrayMap2.getValue(i)));
                }
            }
            else {
                final Map map = (Map)o2;
                for (final Map.Entry<Object, V> entry : ((Map)o).entrySet()) {
                    map.put(entry.getKey(), Data.<V>clone(entry.getValue()));
                }
            }
        }
    }
    
    public static boolean isPrimitive(Type bound) {
        if (bound instanceof WildcardType) {
            bound = Types.getBound((WildcardType)bound);
        }
        if (!(bound instanceof Class)) {
            return false;
        }
        final Class clazz = (Class)bound;
        return clazz.isPrimitive() || clazz == Character.class || clazz == String.class || clazz == Integer.class || clazz == Long.class || clazz == Short.class || clazz == Byte.class || clazz == Float.class || clazz == Double.class || clazz == BigInteger.class || clazz == BigDecimal.class || clazz == DateTime.class || clazz == Boolean.class;
    }
    
    public static boolean isValueOfPrimitiveType(final Object o) {
        return o == null || isPrimitive(o.getClass());
    }
    
    public static Object parsePrimitiveValue(final Type type, final String s) {
        final Class clazz = (type instanceof Class) ? ((Class)type) : null;
        if (type == null || clazz != null) {
            if (clazz == Void.class) {
                return null;
            }
            if (s == null || clazz == null || clazz.isAssignableFrom(String.class)) {
                return s;
            }
            if (clazz == Character.class || clazz == Character.TYPE) {
                if (s.length() != 1) {
                    throw new IllegalArgumentException("expected type Character/char but got " + clazz);
                }
                return s.charAt(0);
            }
            else {
                if (clazz == Boolean.class || clazz == Boolean.TYPE) {
                    return Boolean.valueOf(s);
                }
                if (clazz == Byte.class || clazz == Byte.TYPE) {
                    return Byte.valueOf(s);
                }
                if (clazz == Short.class || clazz == Short.TYPE) {
                    return Short.valueOf(s);
                }
                if (clazz == Integer.class || clazz == Integer.TYPE) {
                    return Integer.valueOf(s);
                }
                if (clazz == Long.class || clazz == Long.TYPE) {
                    return Long.valueOf(s);
                }
                if (clazz == Float.class || clazz == Float.TYPE) {
                    return Float.valueOf(s);
                }
                if (clazz == Double.class || clazz == Double.TYPE) {
                    return Double.valueOf(s);
                }
                if (clazz == DateTime.class) {
                    return DateTime.parseRfc3339(s);
                }
                if (clazz == BigInteger.class) {
                    return new BigInteger(s);
                }
                if (clazz == BigDecimal.class) {
                    return new BigDecimal(s);
                }
                if (clazz.isEnum()) {
                    return ClassInfo.of(clazz).getFieldInfo(s).<Enum>enumValue();
                }
            }
        }
        throw new IllegalArgumentException("expected primitive class, but got: " + type);
    }
    
    public static Collection<Object> newCollectionInstance(Type type) {
        if (type instanceof WildcardType) {
            type = Types.getBound((WildcardType)type);
        }
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType)type).getRawType();
        }
        final Class clazz = (type instanceof Class) ? ((Class)type) : null;
        if (type == null || type instanceof GenericArrayType || (clazz != null && (clazz.isArray() || clazz.isAssignableFrom(ArrayList.class)))) {
            return new ArrayList<Object>();
        }
        if (clazz == null) {
            throw new IllegalArgumentException("unable to create new instance of type: " + type);
        }
        if (clazz.isAssignableFrom(HashSet.class)) {
            return new HashSet<Object>();
        }
        if (clazz.isAssignableFrom(TreeSet.class)) {
            return new TreeSet<Object>();
        }
        return Types.<Collection<Object>>newInstance((Class<Collection<Object>>)clazz);
    }
    
    public static Map<String, Object> newMapInstance(final Class<?> clazz) {
        if (clazz == null || clazz.isAssignableFrom(ArrayMap.class)) {
            return (Map<String, Object>)ArrayMap.<Object, Object>create();
        }
        if (clazz.isAssignableFrom(TreeMap.class)) {
            return new TreeMap<String, Object>();
        }
        return Types.<Map<String, Object>>newInstance(clazz);
    }
    
    public static Type resolveWildcardTypeOrTypeVariable(final List<Type> list, Type bound) {
        if (bound instanceof WildcardType) {
            bound = Types.getBound((WildcardType)bound);
        }
        while (bound instanceof TypeVariable) {
            final Type resolveTypeVariable = Types.resolveTypeVariable(list, (TypeVariable<?>)bound);
            if (resolveTypeVariable != null) {
                bound = resolveTypeVariable;
            }
            if (bound instanceof TypeVariable) {
                bound = ((TypeVariable)bound).getBounds()[0];
            }
        }
        return bound;
    }
    
    static {
        NULL_BOOLEAN = new Boolean(true);
        NULL_STRING = new String();
        NULL_CHARACTER = new Character('\0');
        NULL_BYTE = new Byte((byte)0);
        NULL_SHORT = new Short((short)0);
        NULL_INTEGER = new Integer(0);
        NULL_FLOAT = new Float(0.0f);
        NULL_LONG = new Long(0L);
        NULL_DOUBLE = new Double(0.0);
        NULL_BIG_INTEGER = new BigInteger("0");
        NULL_BIG_DECIMAL = new BigDecimal("0");
        NULL_DATE_TIME = new DateTime(0L);
        (NULL_CACHE = new ConcurrentHashMap<Class<?>, Object>()).put(Boolean.class, Data.NULL_BOOLEAN);
        Data.NULL_CACHE.put(String.class, Data.NULL_STRING);
        Data.NULL_CACHE.put(Character.class, Data.NULL_CHARACTER);
        Data.NULL_CACHE.put(Byte.class, Data.NULL_BYTE);
        Data.NULL_CACHE.put(Short.class, Data.NULL_SHORT);
        Data.NULL_CACHE.put(Integer.class, Data.NULL_INTEGER);
        Data.NULL_CACHE.put(Float.class, Data.NULL_FLOAT);
        Data.NULL_CACHE.put(Long.class, Data.NULL_LONG);
        Data.NULL_CACHE.put(Double.class, Data.NULL_DOUBLE);
        Data.NULL_CACHE.put(BigInteger.class, Data.NULL_BIG_INTEGER);
        Data.NULL_CACHE.put(BigDecimal.class, Data.NULL_BIG_DECIMAL);
        Data.NULL_CACHE.put(DateTime.class, Data.NULL_DATE_TIME);
    }
}
