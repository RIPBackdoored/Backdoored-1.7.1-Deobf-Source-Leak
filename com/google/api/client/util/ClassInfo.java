package com.google.api.client.util;

import java.util.WeakHashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Collection;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.List;
import java.util.IdentityHashMap;
import java.util.Map;

public final class ClassInfo
{
    private static final Map<Class<?>, ClassInfo> CACHE;
    private static final Map<Class<?>, ClassInfo> CACHE_IGNORE_CASE;
    private final Class<?> clazz;
    private final boolean ignoreCase;
    private final IdentityHashMap<String, FieldInfo> nameToFieldInfoMap;
    final List<String> names;
    
    public static ClassInfo of(final Class<?> clazz) {
        return of(clazz, false);
    }
    
    public static ClassInfo of(final Class<?> clazz, final boolean b) {
        if (clazz == null) {
            return null;
        }
        final Map<Class<?>, ClassInfo> map = b ? ClassInfo.CACHE_IGNORE_CASE : ClassInfo.CACHE;
        ClassInfo classInfo;
        synchronized (map) {
            classInfo = map.get(clazz);
            if (classInfo == null) {
                classInfo = new ClassInfo(clazz, b);
                map.put(clazz, classInfo);
            }
        }
        return classInfo;
    }
    
    public Class<?> getUnderlyingClass() {
        return this.clazz;
    }
    
    public final boolean getIgnoreCase() {
        return this.ignoreCase;
    }
    
    public FieldInfo getFieldInfo(String s) {
        if (s != null) {
            if (this.ignoreCase) {
                s = s.toLowerCase(Locale.US);
            }
            s = s.intern();
        }
        return this.nameToFieldInfoMap.get(s);
    }
    
    public Field getField(final String s) {
        final FieldInfo fieldInfo = this.getFieldInfo(s);
        return (fieldInfo == null) ? null : fieldInfo.getField();
    }
    
    public boolean isEnum() {
        return this.clazz.isEnum();
    }
    
    public Collection<String> getNames() {
        return this.names;
    }
    
    private ClassInfo(final Class<?> clazz, final boolean ignoreCase) {
        super();
        this.nameToFieldInfoMap = new IdentityHashMap<String, FieldInfo>();
        this.clazz = clazz;
        this.ignoreCase = ignoreCase;
        Preconditions.checkArgument(!ignoreCase || !clazz.isEnum(), (Object)("cannot ignore case on an enum: " + clazz));
        final TreeSet<String> set = new TreeSet<String>(new Comparator<String>() {
            final /* synthetic */ ClassInfo this$0;
            
            ClassInfo$1() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public int compare(final String s, final String s2) {
                return Objects.equal(s, s2) ? 0 : ((s == null) ? -1 : ((s2 == null) ? 1 : s.compareTo(s2)));
            }
            
            @Override
            public /* bridge */ int compare(final Object o, final Object o2) {
                return this.compare((String)o, (String)o2);
            }
        });
        for (final Field field : clazz.getDeclaredFields()) {
            final FieldInfo of = FieldInfo.of(field);
            if (of != null) {
                String s = of.getName();
                if (ignoreCase) {
                    s = s.toLowerCase(Locale.US).intern();
                }
                final FieldInfo fieldInfo = this.nameToFieldInfoMap.get(s);
                Preconditions.checkArgument(fieldInfo == null, "two fields have the same %sname <%s>: %s and %s", ignoreCase ? "case-insensitive " : "", s, field, (fieldInfo == null) ? null : fieldInfo.getField());
                this.nameToFieldInfoMap.put(s, of);
                set.add(s);
            }
        }
        final Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            final ClassInfo of2 = of(superclass, ignoreCase);
            set.addAll(of2.names);
            for (final Map.Entry<String, FieldInfo> entry : of2.nameToFieldInfoMap.entrySet()) {
                final String s2 = entry.getKey();
                if (!this.nameToFieldInfoMap.containsKey(s2)) {
                    this.nameToFieldInfoMap.put(s2, entry.getValue());
                }
            }
        }
        this.names = (List<String>)(set.isEmpty() ? Collections.<Object>emptyList() : Collections.<Object>unmodifiableList((List<?>)new ArrayList<Object>(set)));
    }
    
    public Collection<FieldInfo> getFieldInfos() {
        return Collections.<FieldInfo>unmodifiableCollection((Collection<? extends FieldInfo>)this.nameToFieldInfoMap.values());
    }
    
    static {
        CACHE = new WeakHashMap<Class<?>, ClassInfo>();
        CACHE_IGNORE_CASE = new WeakHashMap<Class<?>, ClassInfo>();
    }
}
