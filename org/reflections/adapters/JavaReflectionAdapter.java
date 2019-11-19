package org.reflections.adapters;

import org.reflections.util.Utils;
import com.google.common.base.Joiner;
import java.lang.reflect.Modifier;
import org.reflections.ReflectionUtils;
import javax.annotation.Nullable;
import org.reflections.vfs.Vfs;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.lang.reflect.Method;
import java.util.Arrays;
import com.google.common.collect.Lists;
import java.util.List;
import java.lang.reflect.Member;
import java.lang.reflect.Field;

public class JavaReflectionAdapter implements MetadataAdapter<Class, Field, Member>
{
    public JavaReflectionAdapter() {
        super();
    }
    
    @Override
    public List<Field> getFields(final Class clazz) {
        return Lists.<Field>newArrayList(clazz.getDeclaredFields());
    }
    
    @Override
    public List<Member> getMethods(final Class clazz) {
        final ArrayList<Object> arrayList = Lists.<Object>newArrayList();
        arrayList.addAll(Arrays.<Method>asList(clazz.getDeclaredMethods()));
        arrayList.addAll(Arrays.<Constructor>asList(clazz.getDeclaredConstructors()));
        return (List<Member>)arrayList;
    }
    
    @Override
    public String getMethodName(final Member member) {
        return (member instanceof Method) ? member.getName() : ((member instanceof Constructor) ? "<init>" : null);
    }
    
    @Override
    public List<String> getParameterNames(final Member member) {
        final ArrayList<String> arrayList = Lists.<String>newArrayList();
        final Class<?>[] array = (member instanceof Method) ? ((Method)member).getParameterTypes() : ((member instanceof Constructor) ? ((Constructor)member).getParameterTypes() : null);
        if (array != null) {
            final Class<?>[] array2 = array;
            for (int length = array2.length, i = 0; i < length; ++i) {
                arrayList.add(getName(array2[i]));
            }
        }
        return arrayList;
    }
    
    @Override
    public List<String> getClassAnnotationNames(final Class clazz) {
        return this.getAnnotationNames(clazz.getDeclaredAnnotations());
    }
    
    @Override
    public List<String> getFieldAnnotationNames(final Field field) {
        return this.getAnnotationNames(field.getDeclaredAnnotations());
    }
    
    @Override
    public List<String> getMethodAnnotationNames(final Member member) {
        return this.getAnnotationNames((member instanceof Method) ? ((Method)member).getDeclaredAnnotations() : ((member instanceof Constructor) ? ((Constructor)member).getDeclaredAnnotations() : null));
    }
    
    @Override
    public List<String> getParameterAnnotationNames(final Member member, final int n) {
        final Annotation[][] array = (member instanceof Method) ? ((Method)member).getParameterAnnotations() : ((member instanceof Constructor) ? ((Constructor)member).getParameterAnnotations() : null);
        return this.getAnnotationNames((Annotation[])((array != null) ? array[n] : null));
    }
    
    @Override
    public String getReturnTypeName(final Member member) {
        return ((Method)member).getReturnType().getName();
    }
    
    @Override
    public String getFieldName(final Field field) {
        return field.getName();
    }
    
    @Override
    public Class getOfCreateClassObject(final Vfs.File file) throws Exception {
        return this.getOfCreateClassObject(file, (ClassLoader[])null);
    }
    
    public Class getOfCreateClassObject(final Vfs.File file, @Nullable final ClassLoader... array) throws Exception {
        return ReflectionUtils.forName(file.getRelativePath().replace("/", ".").replace(".class", ""), array);
    }
    
    @Override
    public String getMethodModifier(final Member member) {
        return Modifier.toString(member.getModifiers());
    }
    
    @Override
    public String getMethodKey(final Class clazz, final Member member) {
        return this.getMethodName(member) + "(" + Joiner.on(", ").join(this.getParameterNames(member)) + ")";
    }
    
    @Override
    public String getMethodFullKey(final Class clazz, final Member member) {
        return this.getClassName(clazz) + "." + this.getMethodKey(clazz, member);
    }
    
    @Override
    public boolean isPublic(final Object o) {
        final Integer value = (o instanceof Class) ? ((Class)o).getModifiers() : ((o instanceof Member) ? Integer.valueOf(((Member)o).getModifiers()) : null);
        return value != null && Modifier.isPublic(value);
    }
    
    @Override
    public String getClassName(final Class clazz) {
        return clazz.getName();
    }
    
    @Override
    public String getSuperclassName(final Class clazz) {
        final Class superclass = clazz.getSuperclass();
        return (superclass != null) ? superclass.getName() : "";
    }
    
    @Override
    public List<String> getInterfacesNames(final Class clazz) {
        final Class[] interfaces = clazz.getInterfaces();
        final ArrayList list = new ArrayList<String>((interfaces != null) ? interfaces.length : 0);
        if (interfaces != null) {
            final Class[] array = interfaces;
            for (int length = array.length, i = 0; i < length; ++i) {
                list.add(array[i].getName());
            }
        }
        return (List<String>)list;
    }
    
    @Override
    public boolean acceptsInput(final String s) {
        return s.endsWith(".class");
    }
    
    private List<String> getAnnotationNames(final Annotation[] array) {
        final ArrayList<String> list = new ArrayList<String>(array.length);
        for (int length = array.length, i = 0; i < length; ++i) {
            list.add(array[i].annotationType().getName());
        }
        return list;
    }
    
    public static String getName(final Class clazz) {
        if (clazz.isArray()) {
            try {
                Class componentType = clazz;
                int n = 0;
                while (componentType.isArray()) {
                    ++n;
                    componentType = componentType.getComponentType();
                }
                return componentType.getName() + Utils.repeat("[]", n);
            }
            catch (Throwable t) {}
        }
        return clazz.getName();
    }
    
    @Override
    public /* bridge */ String getMethodFullKey(final Object o, final Object o2) {
        return this.getMethodFullKey((Class)o, (Member)o2);
    }
    
    @Override
    public /* bridge */ String getMethodKey(final Object o, final Object o2) {
        return this.getMethodKey((Class)o, (Member)o2);
    }
    
    @Override
    public /* bridge */ String getMethodModifier(final Object o) {
        return this.getMethodModifier((Member)o);
    }
    
    @Override
    public /* bridge */ Object getOfCreateClassObject(final Vfs.File file) throws Exception {
        return this.getOfCreateClassObject(file);
    }
    
    @Override
    public /* bridge */ String getFieldName(final Object o) {
        return this.getFieldName((Field)o);
    }
    
    @Override
    public /* bridge */ String getReturnTypeName(final Object o) {
        return this.getReturnTypeName((Member)o);
    }
    
    @Override
    public /* bridge */ List getParameterAnnotationNames(final Object o, final int n) {
        return this.getParameterAnnotationNames((Member)o, n);
    }
    
    @Override
    public /* bridge */ List getMethodAnnotationNames(final Object o) {
        return this.getMethodAnnotationNames((Member)o);
    }
    
    @Override
    public /* bridge */ List getFieldAnnotationNames(final Object o) {
        return this.getFieldAnnotationNames((Field)o);
    }
    
    @Override
    public /* bridge */ List getClassAnnotationNames(final Object o) {
        return this.getClassAnnotationNames((Class)o);
    }
    
    @Override
    public /* bridge */ List getParameterNames(final Object o) {
        return this.getParameterNames((Member)o);
    }
    
    @Override
    public /* bridge */ String getMethodName(final Object o) {
        return this.getMethodName((Member)o);
    }
    
    @Override
    public /* bridge */ List getMethods(final Object o) {
        return this.getMethods((Class)o);
    }
    
    @Override
    public /* bridge */ List getFields(final Object o) {
        return this.getFields((Class)o);
    }
    
    @Override
    public /* bridge */ List getInterfacesNames(final Object o) {
        return this.getInterfacesNames((Class)o);
    }
    
    @Override
    public /* bridge */ String getSuperclassName(final Object o) {
        return this.getSuperclassName((Class)o);
    }
    
    @Override
    public /* bridge */ String getClassName(final Object o) {
        return this.getClassName((Class)o);
    }
}
