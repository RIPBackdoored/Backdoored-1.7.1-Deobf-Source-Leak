package org.reflections;

import java.util.Collections;
import java.util.ArrayList;
import org.reflections.util.ClasspathHelper;
import com.google.common.collect.Lists;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import java.lang.reflect.Member;
import com.google.common.collect.Iterables;
import com.google.common.base.Predicates;
import org.reflections.util.Utils;
import java.lang.reflect.AnnotatedElement;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Collection;
import com.google.common.collect.Sets;
import java.util.Set;
import com.google.common.base.Predicate;
import java.util.List;

public abstract class ReflectionUtils
{
    public static boolean includeObject;
    private static List<String> primitiveNames;
    private static List<Class> primitiveTypes;
    private static List<String> primitiveDescriptors;
    
    public ReflectionUtils() {
        super();
    }
    
    public static Set<Class<?>> getAllSuperTypes(final Class<?> clazz, final Predicate<? super Class<?>>... array) {
        final LinkedHashSet linkedHashSet = Sets.newLinkedHashSet();
        if (clazz != null && (ReflectionUtils.includeObject || !clazz.equals(Object.class))) {
            linkedHashSet.add(clazz);
            final Iterator<Class<?>> iterator = getSuperTypes(clazz).iterator();
            while (iterator.hasNext()) {
                linkedHashSet.addAll(getAllSuperTypes(iterator.next(), (Predicate<? super Class<?>>[])new Predicate[0]));
            }
        }
        return ReflectionUtils.<Class<?>>filter((Iterable<Class<?>>)linkedHashSet, array);
    }
    
    public static Set<Class<?>> getSuperTypes(final Class<?> clazz) {
        final LinkedHashSet<Object> set = new LinkedHashSet<Object>();
        final Class<?> superclass = clazz.getSuperclass();
        final Class[] interfaces = clazz.getInterfaces();
        if (superclass != null && (ReflectionUtils.includeObject || !superclass.equals(Object.class))) {
            set.add(superclass);
        }
        if (interfaces != null && interfaces.length > 0) {
            set.addAll(Arrays.<Class<?>>asList((Class<?>[])interfaces));
        }
        return (Set<Class<?>>)set;
    }
    
    public static Set<Method> getAllMethods(final Class<?> clazz, final Predicate<? super Method>... array) {
        final HashSet<Object> hashSet = Sets.<Object>newHashSet();
        final Iterator<Class<?>> iterator = getAllSuperTypes(clazz, (Predicate<? super Class<?>>[])new Predicate[0]).iterator();
        while (iterator.hasNext()) {
            hashSet.addAll(getMethods(iterator.next(), array));
        }
        return (Set<Method>)hashSet;
    }
    
    public static Set<Method> getMethods(final Class<?> clazz, final Predicate<? super Method>... array) {
        return ReflectionUtils.<Method>filter(clazz.isInterface() ? clazz.getMethods() : clazz.getDeclaredMethods(), array);
    }
    
    public static Set<Constructor> getAllConstructors(final Class<?> clazz, final Predicate<? super Constructor>... array) {
        final HashSet<Object> hashSet = Sets.<Object>newHashSet();
        final Iterator<Class<?>> iterator = getAllSuperTypes(clazz, (Predicate<? super Class<?>>[])new Predicate[0]).iterator();
        while (iterator.hasNext()) {
            hashSet.addAll(getConstructors(iterator.next(), array));
        }
        return (Set<Constructor>)hashSet;
    }
    
    public static Set<Constructor> getConstructors(final Class<?> clazz, final Predicate<? super Constructor>... array) {
        return (Set<Constructor>)ReflectionUtils.<Constructor<?>>filter(clazz.getDeclaredConstructors(), (Predicate<? super Constructor<?>>[])array);
    }
    
    public static Set<Field> getAllFields(final Class<?> clazz, final Predicate<? super Field>... array) {
        final HashSet<Object> hashSet = Sets.<Object>newHashSet();
        final Iterator<Class<?>> iterator = getAllSuperTypes(clazz, (Predicate<? super Class<?>>[])new Predicate[0]).iterator();
        while (iterator.hasNext()) {
            hashSet.addAll(getFields(iterator.next(), array));
        }
        return (Set<Field>)hashSet;
    }
    
    public static Set<Field> getFields(final Class<?> clazz, final Predicate<? super Field>... array) {
        return ReflectionUtils.<Field>filter(clazz.getDeclaredFields(), array);
    }
    
    public static <T extends AnnotatedElement> Set<Annotation> getAllAnnotations(final T t, final Predicate<Annotation>... array) {
        final HashSet<Object> hashSet = Sets.<Object>newHashSet();
        if (t instanceof Class) {
            final Iterator<Class<?>> iterator = getAllSuperTypes((Class<?>)t, (Predicate<? super Class<?>>[])new Predicate[0]).iterator();
            while (iterator.hasNext()) {
                hashSet.addAll(ReflectionUtils.<Class<?>>getAnnotations(iterator.next(), array));
            }
        }
        else {
            hashSet.addAll(ReflectionUtils.<AnnotatedElement>getAnnotations((AnnotatedElement)t, array));
        }
        return (Set<Annotation>)hashSet;
    }
    
    public static <T extends AnnotatedElement> Set<Annotation> getAnnotations(final T t, final Predicate<Annotation>... array) {
        return ReflectionUtils.<Annotation>filter(t.getDeclaredAnnotations(), (Predicate<? super Annotation>[])array);
    }
    
    public static <T extends AnnotatedElement> Set<T> getAll(final Set<T> unfiltered, final Predicate<? super T>... array) {
        return (Set<T>)(Utils.isEmpty(array) ? unfiltered : Sets.<Object>newHashSet((Iterable<?>)Iterables.<? extends E>filter((Iterable<? extends E>)unfiltered, Predicates.and((Predicate[])array))));
    }
    
    public static <T extends Member> Predicate<T> withName(final String s) {
        return new Predicate<T>() {
            final /* synthetic */ String val$name;
            
            ReflectionUtils$1() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final T t) {
                return t != null && t.getName().equals(s);
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((Member)o);
            }
        };
    }
    
    public static <T extends Member> Predicate<T> withPrefix(final String s) {
        return new Predicate<T>() {
            final /* synthetic */ String val$prefix;
            
            ReflectionUtils$2() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final T t) {
                return t != null && t.getName().startsWith(s);
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((Member)o);
            }
        };
    }
    
    public static <T extends AnnotatedElement> Predicate<T> withPattern(final String s) {
        return new Predicate<T>() {
            final /* synthetic */ String val$regex;
            
            ReflectionUtils$3() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final T t) {
                return Pattern.matches(s, t.toString());
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((AnnotatedElement)o);
            }
        };
    }
    
    public static <T extends AnnotatedElement> Predicate<T> withAnnotation(final Class<? extends Annotation> clazz) {
        return new Predicate<T>() {
            final /* synthetic */ Class val$annotation;
            
            ReflectionUtils$4() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final T t) {
                return t != null && t.isAnnotationPresent(clazz);
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((AnnotatedElement)o);
            }
        };
    }
    
    public static <T extends AnnotatedElement> Predicate<T> withAnnotations(final Class<? extends Annotation>... array) {
        return new Predicate<T>() {
            final /* synthetic */ Class[] val$annotations;
            
            ReflectionUtils$5() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final T t) {
                return t != null && Arrays.equals(array, annotationTypes(t.getAnnotations()));
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((AnnotatedElement)o);
            }
        };
    }
    
    public static <T extends AnnotatedElement> Predicate<T> withAnnotation(final Annotation annotation) {
        return new Predicate<T>() {
            final /* synthetic */ Annotation val$annotation;
            
            ReflectionUtils$6() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final T t) {
                return t != null && t.isAnnotationPresent(annotation.annotationType()) && areAnnotationMembersMatching(t.<Annotation>getAnnotation(annotation.annotationType()), annotation);
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((AnnotatedElement)o);
            }
        };
    }
    
    public static <T extends AnnotatedElement> Predicate<T> withAnnotations(final Annotation... array) {
        return new Predicate<T>() {
            final /* synthetic */ Annotation[] val$annotations;
            
            ReflectionUtils$7() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final T t) {
                if (t != null) {
                    final Annotation[] annotations = t.getAnnotations();
                    if (annotations.length == array.length) {
                        for (int i = 0; i < annotations.length; ++i) {
                            if (!areAnnotationMembersMatching(annotations[i], array[i])) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((AnnotatedElement)o);
            }
        };
    }
    
    public static Predicate<Member> withParameters(final Class<?>... array) {
        return new Predicate<Member>() {
            final /* synthetic */ Class[] val$types;
            
            ReflectionUtils$8() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final Member member) {
                return Arrays.equals(parameterTypes(member), array);
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((Member)o);
            }
        };
    }
    
    public static Predicate<Member> withParametersAssignableTo(final Class... array) {
        return new Predicate<Member>() {
            final /* synthetic */ Class[] val$types;
            
            ReflectionUtils$9() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final Member member) {
                if (member != null) {
                    final Class[] access$200 = parameterTypes(member);
                    if (access$200.length == array.length) {
                        for (int i = 0; i < access$200.length; ++i) {
                            if (!access$200[i].isAssignableFrom(array[i]) || (access$200[i] == Object.class && array[i] != Object.class)) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
                return false;
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((Member)o);
            }
        };
    }
    
    public static Predicate<Member> withParametersCount(final int n) {
        return new Predicate<Member>() {
            final /* synthetic */ int val$count;
            
            ReflectionUtils$10() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final Member member) {
                return member != null && parameterTypes(member).length == n;
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((Member)o);
            }
        };
    }
    
    public static Predicate<Member> withAnyParameterAnnotation(final Class<? extends Annotation> clazz) {
        return new Predicate<Member>() {
            final /* synthetic */ Class val$annotationClass;
            
            ReflectionUtils$11() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final Member member) {
                return member != null && Iterables.<Object>any((Iterable<Object>)annotationTypes(parameterAnnotations(member)), (Predicate<? super Object>)new Predicate<Class<? extends Annotation>>() {
                    final /* synthetic */ ReflectionUtils$11 this$0;
                    
                    ReflectionUtils$11$1() {
                        this.this$0 = this$0;
                        super();
                    }
                    
                    @Override
                    public boolean apply(@Nullable final Class<? extends Annotation> clazz) {
                        return clazz.equals(clazz);
                    }
                    
                    @Override
                    public /* bridge */ boolean apply(@Nullable final Object o) {
                        return this.apply((Class<? extends Annotation>)o);
                    }
                });
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((Member)o);
            }
        };
    }
    
    public static Predicate<Member> withAnyParameterAnnotation(final Annotation annotation) {
        return new Predicate<Member>() {
            final /* synthetic */ Annotation val$annotation;
            
            ReflectionUtils$12() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final Member member) {
                return member != null && Iterables.<Object>any((Iterable<Object>)parameterAnnotations(member), (Predicate<? super Object>)new Predicate<Annotation>() {
                    final /* synthetic */ ReflectionUtils$12 this$0;
                    
                    ReflectionUtils$12$1() {
                        this.this$0 = this$0;
                        super();
                    }
                    
                    @Override
                    public boolean apply(@Nullable final Annotation annotation) {
                        return areAnnotationMembersMatching(annotation, annotation);
                    }
                    
                    @Override
                    public /* bridge */ boolean apply(@Nullable final Object o) {
                        return this.apply((Annotation)o);
                    }
                });
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((Member)o);
            }
        };
    }
    
    public static <T> Predicate<Field> withType(final Class<T> clazz) {
        return new Predicate<Field>() {
            final /* synthetic */ Class val$type;
            
            ReflectionUtils$13() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final Field field) {
                return field != null && field.getType().equals(clazz);
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((Field)o);
            }
        };
    }
    
    public static <T> Predicate<Field> withTypeAssignableTo(final Class<T> clazz) {
        return new Predicate<Field>() {
            final /* synthetic */ Class val$type;
            
            ReflectionUtils$14() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final Field field) {
                return field != null && clazz.isAssignableFrom(field.getType());
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((Field)o);
            }
        };
    }
    
    public static <T> Predicate<Method> withReturnType(final Class<T> clazz) {
        return new Predicate<Method>() {
            final /* synthetic */ Class val$type;
            
            ReflectionUtils$15() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final Method method) {
                return method != null && method.getReturnType().equals(clazz);
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((Method)o);
            }
        };
    }
    
    public static <T> Predicate<Method> withReturnTypeAssignableTo(final Class<T> clazz) {
        return new Predicate<Method>() {
            final /* synthetic */ Class val$type;
            
            ReflectionUtils$16() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final Method method) {
                return method != null && clazz.isAssignableFrom(method.getReturnType());
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((Method)o);
            }
        };
    }
    
    public static <T extends Member> Predicate<T> withModifier(final int n) {
        return new Predicate<T>() {
            final /* synthetic */ int val$mod;
            
            ReflectionUtils$17() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final T t) {
                return t != null && (t.getModifiers() & n) != 0x0;
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((Member)o);
            }
        };
    }
    
    public static Predicate<Class<?>> withClassModifier(final int n) {
        return new Predicate<Class<?>>() {
            final /* synthetic */ int val$mod;
            
            ReflectionUtils$18() {
                super();
            }
            
            @Override
            public boolean apply(@Nullable final Class<?> clazz) {
                return clazz != null && (clazz.getModifiers() & n) != 0x0;
            }
            
            @Override
            public /* bridge */ boolean apply(@Nullable final Object o) {
                return this.apply((Class<?>)o);
            }
        };
    }
    
    public static Class<?> forName(final String s, final ClassLoader... array) {
        if (getPrimitiveNames().contains(s)) {
            return getPrimitiveTypes().get(getPrimitiveNames().indexOf(s));
        }
        String string2;
        if (s.contains("[")) {
            final int index = s.indexOf("[");
            final String substring = s.substring(0, index);
            final String replace = s.substring(index).replace("]", "");
            String string;
            if (getPrimitiveNames().contains(substring)) {
                string = getPrimitiveDescriptors().get(getPrimitiveNames().indexOf(substring));
            }
            else {
                string = "L" + substring + ";";
            }
            string2 = replace + string;
        }
        else {
            string2 = s;
        }
        final ArrayList<Object> arrayList = Lists.<Object>newArrayList();
        final ClassLoader[] classLoaders = ClasspathHelper.classLoaders(array);
        final int length = classLoaders.length;
        int i = 0;
        while (i < length) {
            final ClassLoader classLoader = classLoaders[i];
            if (string2.contains("[")) {
                try {
                    return Class.forName(string2, false, classLoader);
                }
                catch (Throwable t) {
                    arrayList.add(new ReflectionsException("could not get type for name " + s, t));
                }
            }
            try {
                return classLoader.loadClass(string2);
            }
            catch (Throwable t2) {
                arrayList.add(new ReflectionsException("could not get type for name " + s, t2));
                ++i;
                continue;
            }
            break;
        }
        if (Reflections.log != null) {
            final Iterator<ReflectionsException> iterator = arrayList.iterator();
            while (iterator.hasNext()) {
                Reflections.log.warn("could not get type for name " + s + " from any class loader", iterator.next());
            }
        }
        return null;
    }
    
    public static <T> List<Class<? extends T>> forNames(final Iterable<String> iterable, final ClassLoader... array) {
        final ArrayList<Class<?>> list = (ArrayList<Class<?>>)new ArrayList<Class<? extends T>>();
        final Iterator<String> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            final Class<?> forName = forName(iterator.next(), array);
            if (forName != null) {
                list.add(forName);
            }
        }
        return (List<Class<? extends T>>)list;
    }
    
    private static Class[] parameterTypes(final Member member) {
        return (Class[])((member != null) ? ((member.getClass() == Method.class) ? ((Method)member).getParameterTypes() : ((member.getClass() == Constructor.class) ? ((Constructor)member).getParameterTypes() : null)) : null);
    }
    
    private static Set<Annotation> parameterAnnotations(final Member member) {
        final HashSet<Object> hashSet = Sets.<Object>newHashSet();
        final Annotation[][] array = (member instanceof Method) ? ((Method)member).getParameterAnnotations() : ((member instanceof Constructor) ? ((Constructor)member).getParameterAnnotations() : null);
        for (int length = array.length, i = 0; i < length; ++i) {
            Collections.<Annotation>addAll(hashSet, array[i]);
        }
        return (Set<Annotation>)hashSet;
    }
    
    private static Set<Class<? extends Annotation>> annotationTypes(final Iterable<Annotation> iterable) {
        final HashSet<Class<? extends Annotation>> hashSet = Sets.<Class<? extends Annotation>>newHashSet();
        final Iterator<Annotation> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            hashSet.add(iterator.next().annotationType());
        }
        return hashSet;
    }
    
    private static Class<? extends Annotation>[] annotationTypes(final Annotation[] array) {
        final Class[] array2 = new Class[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i].annotationType();
        }
        return (Class<? extends Annotation>[])array2;
    }
    
    private static void initPrimitives() {
        if (ReflectionUtils.primitiveNames == null) {
            ReflectionUtils.primitiveNames = Lists.<String>newArrayList("boolean", "char", "byte", "short", "int", "long", "float", "double", "void");
            ReflectionUtils.primitiveTypes = (List<Class>)Lists.<Class>newArrayList(Boolean.TYPE, Character.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Void.TYPE);
            ReflectionUtils.primitiveDescriptors = Lists.<String>newArrayList("Z", "C", "B", "S", "I", "J", "F", "D", "V");
        }
    }
    
    private static List<String> getPrimitiveNames() {
        initPrimitives();
        return ReflectionUtils.primitiveNames;
    }
    
    private static List<Class> getPrimitiveTypes() {
        initPrimitives();
        return ReflectionUtils.primitiveTypes;
    }
    
    private static List<String> getPrimitiveDescriptors() {
        initPrimitives();
        return ReflectionUtils.primitiveDescriptors;
    }
    
    static <T> Set<T> filter(final T[] array, final Predicate<? super T>... array2) {
        return (Set<T>)(Utils.isEmpty(array2) ? Sets.newHashSet((Object[])array) : Sets.<Object>newHashSet((Iterable<?>)Iterables.<? extends E>filter((Iterable<? extends E>)Arrays.<T>asList(array), Predicates.and((Predicate[])array2))));
    }
    
    static <T> Set<T> filter(final Iterable<T> iterable, final Predicate<? super T>... array) {
        return (Set<T>)(Utils.isEmpty(array) ? Sets.<Object>newHashSet((Iterable<?>)iterable) : Sets.<Object>newHashSet((Iterable<?>)Iterables.<? extends E>filter((Iterable<? extends E>)iterable, Predicates.and((Predicate[])array))));
    }
    
    private static boolean areAnnotationMembersMatching(final Annotation annotation, final Annotation annotation2) {
        if (annotation2 != null && annotation.annotationType() == annotation2.annotationType()) {
            for (final Method method : annotation.annotationType().getDeclaredMethods()) {
                try {
                    if (!method.invoke(annotation, new Object[0]).equals(method.invoke(annotation2, new Object[0]))) {
                        return false;
                    }
                }
                catch (Exception ex) {
                    throw new ReflectionsException(String.format("could not invoke method %s on annotation %s", method.getName(), annotation.annotationType()), ex);
                }
            }
            return true;
        }
        return false;
    }
    
    static /* synthetic */ Class[] access$000(final Annotation[] array) {
        return annotationTypes(array);
    }
    
    static /* synthetic */ boolean access$100(final Annotation annotation, final Annotation annotation2) {
        return areAnnotationMembersMatching(annotation, annotation2);
    }
    
    static /* synthetic */ Class[] access$200(final Member member) {
        return parameterTypes(member);
    }
    
    static /* synthetic */ Set access$300(final Member member) {
        return parameterAnnotations(member);
    }
    
    static /* synthetic */ Set access$400(final Iterable iterable) {
        return annotationTypes(iterable);
    }
    
    static {
        ReflectionUtils.includeObject = false;
    }
}
