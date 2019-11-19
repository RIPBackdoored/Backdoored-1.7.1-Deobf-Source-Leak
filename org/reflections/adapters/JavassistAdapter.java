package org.reflections.adapters;

import javassist.bytecode.Descriptor;
import java.util.Arrays;
import com.google.common.base.Joiner;
import javassist.bytecode.AccessFlag;
import org.reflections.util.Utils;
import java.io.IOException;
import org.reflections.ReflectionsException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import org.reflections.vfs.Vfs;
import javassist.bytecode.annotation.Annotation;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import javassist.bytecode.ParameterAnnotationsAttribute;
import com.google.common.collect.Lists;
import javassist.bytecode.AnnotationsAttribute;
import java.util.List;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.ClassFile;

public class JavassistAdapter implements MetadataAdapter<ClassFile, FieldInfo, MethodInfo>
{
    public static boolean includeInvisibleTag;
    
    public JavassistAdapter() {
        super();
    }
    
    @Override
    public List<FieldInfo> getFields(final ClassFile classFile) {
        return (List<FieldInfo>)classFile.getFields();
    }
    
    @Override
    public List<MethodInfo> getMethods(final ClassFile classFile) {
        return (List<MethodInfo>)classFile.getMethods();
    }
    
    @Override
    public String getMethodName(final MethodInfo methodInfo) {
        return methodInfo.getName();
    }
    
    @Override
    public List<String> getParameterNames(final MethodInfo methodInfo) {
        final String descriptor = methodInfo.getDescriptor();
        return this.splitDescriptorToTypeNames(descriptor.substring(descriptor.indexOf("(") + 1, descriptor.lastIndexOf(")")));
    }
    
    @Override
    public List<String> getClassAnnotationNames(final ClassFile classFile) {
        return this.getAnnotationNames((AnnotationsAttribute)classFile.getAttribute("RuntimeVisibleAnnotations"), JavassistAdapter.includeInvisibleTag ? ((AnnotationsAttribute)classFile.getAttribute("RuntimeInvisibleAnnotations")) : null);
    }
    
    @Override
    public List<String> getFieldAnnotationNames(final FieldInfo fieldInfo) {
        return this.getAnnotationNames((AnnotationsAttribute)fieldInfo.getAttribute("RuntimeVisibleAnnotations"), JavassistAdapter.includeInvisibleTag ? ((AnnotationsAttribute)fieldInfo.getAttribute("RuntimeInvisibleAnnotations")) : null);
    }
    
    @Override
    public List<String> getMethodAnnotationNames(final MethodInfo methodInfo) {
        return this.getAnnotationNames((AnnotationsAttribute)methodInfo.getAttribute("RuntimeVisibleAnnotations"), JavassistAdapter.includeInvisibleTag ? ((AnnotationsAttribute)methodInfo.getAttribute("RuntimeInvisibleAnnotations")) : null);
    }
    
    @Override
    public List<String> getParameterAnnotationNames(final MethodInfo methodInfo, final int n) {
        final ArrayList<Object> arrayList = Lists.<Object>newArrayList();
        final ArrayList<ParameterAnnotationsAttribute> arrayList2 = Lists.<ParameterAnnotationsAttribute>newArrayList((ParameterAnnotationsAttribute)methodInfo.getAttribute("RuntimeVisibleParameterAnnotations"), (ParameterAnnotationsAttribute)methodInfo.getAttribute("RuntimeInvisibleParameterAnnotations"));
        if (arrayList2 != null) {
            for (final ParameterAnnotationsAttribute parameterAnnotationsAttribute : arrayList2) {
                if (parameterAnnotationsAttribute != null) {
                    final Annotation[][] annotations = parameterAnnotationsAttribute.getAnnotations();
                    if (n >= annotations.length) {
                        continue;
                    }
                    arrayList.addAll(this.getAnnotationNames(annotations[n]));
                }
            }
        }
        return (List<String>)arrayList;
    }
    
    @Override
    public String getReturnTypeName(final MethodInfo methodInfo) {
        final String descriptor = methodInfo.getDescriptor();
        return this.splitDescriptorToTypeNames(descriptor.substring(descriptor.lastIndexOf(")") + 1)).get(0);
    }
    
    @Override
    public String getFieldName(final FieldInfo fieldInfo) {
        return fieldInfo.getName();
    }
    
    @Override
    public ClassFile getOfCreateClassObject(final Vfs.File file) {
        InputStream openInputStream = null;
        try {
            openInputStream = file.openInputStream();
            return new ClassFile(new DataInputStream(new BufferedInputStream(openInputStream)));
        }
        catch (IOException ex) {
            throw new ReflectionsException("could not create class file from " + file.getName(), ex);
        }
        finally {
            Utils.close(openInputStream);
        }
    }
    
    @Override
    public String getMethodModifier(final MethodInfo methodInfo) {
        final int accessFlags = methodInfo.getAccessFlags();
        return AccessFlag.isPrivate(accessFlags) ? "private" : (AccessFlag.isProtected(accessFlags) ? "protected" : (this.isPublic(accessFlags) ? "public" : ""));
    }
    
    @Override
    public String getMethodKey(final ClassFile classFile, final MethodInfo methodInfo) {
        return this.getMethodName(methodInfo) + "(" + Joiner.on(", ").join(this.getParameterNames(methodInfo)) + ")";
    }
    
    @Override
    public String getMethodFullKey(final ClassFile classFile, final MethodInfo methodInfo) {
        return this.getClassName(classFile) + "." + this.getMethodKey(classFile, methodInfo);
    }
    
    @Override
    public boolean isPublic(final Object o) {
        final Integer value = (o instanceof ClassFile) ? ((ClassFile)o).getAccessFlags() : ((o instanceof FieldInfo) ? ((FieldInfo)o).getAccessFlags() : ((o instanceof MethodInfo) ? Integer.valueOf(((MethodInfo)o).getAccessFlags()) : null));
        return value != null && AccessFlag.isPublic(value);
    }
    
    @Override
    public String getClassName(final ClassFile classFile) {
        return classFile.getName();
    }
    
    @Override
    public String getSuperclassName(final ClassFile classFile) {
        return classFile.getSuperclass();
    }
    
    @Override
    public List<String> getInterfacesNames(final ClassFile classFile) {
        return Arrays.<String>asList(classFile.getInterfaces());
    }
    
    @Override
    public boolean acceptsInput(final String s) {
        return s.endsWith(".class");
    }
    
    private List<String> getAnnotationNames(final AnnotationsAttribute... array) {
        final ArrayList<String> arrayList = Lists.<String>newArrayList();
        if (array != null) {
            for (final AnnotationsAttribute annotationsAttribute : array) {
                if (annotationsAttribute != null) {
                    final Annotation[] annotations = annotationsAttribute.getAnnotations();
                    for (int length2 = annotations.length, j = 0; j < length2; ++j) {
                        arrayList.add(annotations[j].getTypeName());
                    }
                }
            }
        }
        return arrayList;
    }
    
    private List<String> getAnnotationNames(final Annotation[] array) {
        final ArrayList<String> arrayList = Lists.<String>newArrayList();
        for (int length = array.length, i = 0; i < length; ++i) {
            arrayList.add(array[i].getTypeName());
        }
        return arrayList;
    }
    
    private List<String> splitDescriptorToTypeNames(final String s) {
        final ArrayList<String> arrayList = Lists.<String>newArrayList();
        if (s != null && s.length() != 0) {
            final ArrayList<Object> arrayList2 = Lists.<Object>newArrayList();
            final Descriptor.Iterator iterator = new Descriptor.Iterator(s);
            while (iterator.hasNext()) {
                arrayList2.add(iterator.next());
            }
            arrayList2.add(s.length());
            for (int i = 0; i < arrayList2.size() - 1; ++i) {
                arrayList.add(Descriptor.toString(s.substring(arrayList2.get(i), arrayList2.get(i + 1))));
            }
        }
        return arrayList;
    }
    
    @Override
    public /* bridge */ String getMethodFullKey(final Object o, final Object o2) {
        return this.getMethodFullKey((ClassFile)o, (MethodInfo)o2);
    }
    
    @Override
    public /* bridge */ String getMethodKey(final Object o, final Object o2) {
        return this.getMethodKey((ClassFile)o, (MethodInfo)o2);
    }
    
    @Override
    public /* bridge */ String getMethodModifier(final Object o) {
        return this.getMethodModifier((MethodInfo)o);
    }
    
    @Override
    public /* bridge */ Object getOfCreateClassObject(final Vfs.File file) throws Exception {
        return this.getOfCreateClassObject(file);
    }
    
    @Override
    public /* bridge */ String getFieldName(final Object o) {
        return this.getFieldName((FieldInfo)o);
    }
    
    @Override
    public /* bridge */ String getReturnTypeName(final Object o) {
        return this.getReturnTypeName((MethodInfo)o);
    }
    
    @Override
    public /* bridge */ List getParameterAnnotationNames(final Object o, final int n) {
        return this.getParameterAnnotationNames((MethodInfo)o, n);
    }
    
    @Override
    public /* bridge */ List getMethodAnnotationNames(final Object o) {
        return this.getMethodAnnotationNames((MethodInfo)o);
    }
    
    @Override
    public /* bridge */ List getFieldAnnotationNames(final Object o) {
        return this.getFieldAnnotationNames((FieldInfo)o);
    }
    
    @Override
    public /* bridge */ List getClassAnnotationNames(final Object o) {
        return this.getClassAnnotationNames((ClassFile)o);
    }
    
    @Override
    public /* bridge */ List getParameterNames(final Object o) {
        return this.getParameterNames((MethodInfo)o);
    }
    
    @Override
    public /* bridge */ String getMethodName(final Object o) {
        return this.getMethodName((MethodInfo)o);
    }
    
    @Override
    public /* bridge */ List getMethods(final Object o) {
        return this.getMethods((ClassFile)o);
    }
    
    @Override
    public /* bridge */ List getFields(final Object o) {
        return this.getFields((ClassFile)o);
    }
    
    @Override
    public /* bridge */ List getInterfacesNames(final Object o) {
        return this.getInterfacesNames((ClassFile)o);
    }
    
    @Override
    public /* bridge */ String getSuperclassName(final Object o) {
        return this.getSuperclassName((ClassFile)o);
    }
    
    @Override
    public /* bridge */ String getClassName(final Object o) {
        return this.getClassName((ClassFile)o);
    }
    
    static {
        JavassistAdapter.includeInvisibleTag = true;
    }
}
