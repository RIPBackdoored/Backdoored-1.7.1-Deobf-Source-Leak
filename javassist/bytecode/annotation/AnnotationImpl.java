package javassist.bytecode.annotation;

import javassist.bytecode.MethodInfo;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationDefaultAttribute;
import java.lang.reflect.Proxy;
import javassist.ClassPool;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;

public class AnnotationImpl implements InvocationHandler
{
    private static final String JDK_ANNOTATION_CLASS_NAME = "java.lang.annotation.Annotation";
    private static Method JDK_ANNOTATION_TYPE_METHOD;
    private Annotation annotation;
    private ClassPool pool;
    private ClassLoader classLoader;
    private transient Class annotationType;
    private transient int cachedHashCode;
    
    public static Object make(final ClassLoader classLoader, final Class clazz, final ClassPool classPool, final Annotation annotation) {
        return Proxy.newProxyInstance(classLoader, new Class[] { clazz }, new AnnotationImpl(annotation, classPool, classLoader));
    }
    
    private AnnotationImpl(final Annotation annotation, final ClassPool pool, final ClassLoader classLoader) {
        super();
        this.cachedHashCode = Integer.MIN_VALUE;
        this.annotation = annotation;
        this.pool = pool;
        this.classLoader = classLoader;
    }
    
    public String getTypeName() {
        return this.annotation.getTypeName();
    }
    
    private Class getAnnotationType() {
        if (this.annotationType == null) {
            final String typeName = this.annotation.getTypeName();
            try {
                this.annotationType = this.classLoader.loadClass(typeName);
            }
            catch (ClassNotFoundException ex) {
                final NoClassDefFoundError noClassDefFoundError = new NoClassDefFoundError("Error loading annotation class: " + typeName);
                noClassDefFoundError.setStackTrace(ex.getStackTrace());
                throw noClassDefFoundError;
            }
        }
        return this.annotationType;
    }
    
    public Annotation getAnnotation() {
        return this.annotation;
    }
    
    @Override
    public Object invoke(final Object o, final Method method, final Object[] array) throws Throwable {
        final String name = method.getName();
        if (Object.class == method.getDeclaringClass()) {
            if ("equals".equals(name)) {
                return new Boolean(this.checkEquals(array[0]));
            }
            if ("toString".equals(name)) {
                return this.annotation.toString();
            }
            if ("hashCode".equals(name)) {
                return new Integer(this.hashCode());
            }
        }
        else if ("annotationType".equals(name) && method.getParameterTypes().length == 0) {
            return this.getAnnotationType();
        }
        final MemberValue memberValue = this.annotation.getMemberValue(name);
        if (memberValue == null) {
            return this.getDefault(name, method);
        }
        return memberValue.getValue(this.classLoader, this.pool, method);
    }
    
    private Object getDefault(final String s, final Method method) throws ClassNotFoundException, RuntimeException {
        final String typeName = this.annotation.getTypeName();
        if (this.pool != null) {
            try {
                final MethodInfo method2 = this.pool.get(typeName).getClassFile2().getMethod(s);
                if (method2 != null) {
                    final AnnotationDefaultAttribute annotationDefaultAttribute = (AnnotationDefaultAttribute)method2.getAttribute("AnnotationDefault");
                    if (annotationDefaultAttribute != null) {
                        return annotationDefaultAttribute.getDefaultValue().getValue(this.classLoader, this.pool, method);
                    }
                }
            }
            catch (NotFoundException ex) {
                throw new RuntimeException("cannot find a class file: " + typeName);
            }
        }
        throw new RuntimeException("no default value: " + typeName + "." + s + "()");
    }
    
    @Override
    public int hashCode() {
        if (this.cachedHashCode == Integer.MIN_VALUE) {
            int cachedHashCode = 0;
            this.getAnnotationType();
            final Method[] declaredMethods = this.annotationType.getDeclaredMethods();
            for (int i = 0; i < declaredMethods.length; ++i) {
                final String name = declaredMethods[i].getName();
                int n = 0;
                final MemberValue memberValue = this.annotation.getMemberValue(name);
                Object o = null;
                try {
                    if (memberValue != null) {
                        o = memberValue.getValue(this.classLoader, this.pool, declaredMethods[i]);
                    }
                    if (o == null) {
                        o = this.getDefault(name, declaredMethods[i]);
                    }
                }
                catch (RuntimeException ex) {
                    throw ex;
                }
                catch (Exception ex2) {
                    throw new RuntimeException("Error retrieving value " + name + " for annotation " + this.annotation.getTypeName(), ex2);
                }
                if (o != null) {
                    if (o.getClass().isArray()) {
                        n = arrayHashCode(o);
                    }
                    else {
                        n = o.hashCode();
                    }
                }
                cachedHashCode += (127 * name.hashCode() ^ n);
            }
            this.cachedHashCode = cachedHashCode;
        }
        return this.cachedHashCode;
    }
    
    private boolean checkEquals(final Object o) throws Exception {
        if (o == null) {
            return false;
        }
        if (o instanceof Proxy) {
            final InvocationHandler invocationHandler = Proxy.getInvocationHandler(o);
            if (invocationHandler instanceof AnnotationImpl) {
                return this.annotation.equals(((AnnotationImpl)invocationHandler).annotation);
            }
        }
        if (!this.getAnnotationType().equals(AnnotationImpl.JDK_ANNOTATION_TYPE_METHOD.invoke(o, (Object[])null))) {
            return false;
        }
        final Method[] declaredMethods = this.annotationType.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; ++i) {
            final String name = declaredMethods[i].getName();
            final MemberValue memberValue = this.annotation.getMemberValue(name);
            Object o2 = null;
            Object invoke;
            try {
                if (memberValue != null) {
                    o2 = memberValue.getValue(this.classLoader, this.pool, declaredMethods[i]);
                }
                if (o2 == null) {
                    o2 = this.getDefault(name, declaredMethods[i]);
                }
                invoke = declaredMethods[i].invoke(o, (Object[])null);
            }
            catch (RuntimeException ex) {
                throw ex;
            }
            catch (Exception ex2) {
                throw new RuntimeException("Error retrieving value " + name + " for annotation " + this.annotation.getTypeName(), ex2);
            }
            if (o2 == null && invoke != null) {
                return false;
            }
            if (o2 != null && !o2.equals(invoke)) {
                return false;
            }
        }
        return true;
    }
    
    private static int arrayHashCode(final Object o) {
        if (o == null) {
            return 0;
        }
        int n = 1;
        final Object[] array = (Object[])o;
        for (int i = 0; i < array.length; ++i) {
            int hashCode = 0;
            if (array[i] != null) {
                hashCode = array[i].hashCode();
            }
            n = 31 * n + hashCode;
        }
        return n;
    }
    
    static {
        AnnotationImpl.JDK_ANNOTATION_TYPE_METHOD = null;
        try {
            AnnotationImpl.JDK_ANNOTATION_TYPE_METHOD = Class.forName("java.lang.annotation.Annotation").getMethod("annotationType", (Class<?>[])null);
        }
        catch (Exception ex) {}
    }
}
