package javassist.tools.reflect;

import java.util.Arrays;
import java.lang.reflect.InvocationTargetException;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.io.Serializable;

public class ClassMetaobject implements Serializable
{
    static final String methodPrefix = "_m_";
    static final int methodPrefixLen = 3;
    private Class javaClass;
    private Constructor[] constructors;
    private Method[] methods;
    public static boolean useContextClassLoader;
    
    public ClassMetaobject(final String[] array) {
        super();
        try {
            this.javaClass = this.getClassObject(array[0]);
        }
        catch (ClassNotFoundException ex) {
            throw new RuntimeException("not found: " + array[0] + ", useContextClassLoader: " + Boolean.toString(ClassMetaobject.useContextClassLoader), ex);
        }
        this.constructors = this.javaClass.getConstructors();
        this.methods = null;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeUTF(this.javaClass.getName());
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.javaClass = this.getClassObject(objectInputStream.readUTF());
        this.constructors = this.javaClass.getConstructors();
        this.methods = null;
    }
    
    private Class getClassObject(final String s) throws ClassNotFoundException {
        if (ClassMetaobject.useContextClassLoader) {
            return Thread.currentThread().getContextClassLoader().loadClass(s);
        }
        return Class.forName(s);
    }
    
    public final Class getJavaClass() {
        return this.javaClass;
    }
    
    public final String getName() {
        return this.javaClass.getName();
    }
    
    public final boolean isInstance(final Object o) {
        return this.javaClass.isInstance(o);
    }
    
    public final Object newInstance(final Object[] array) throws CannotCreateException {
        for (int length = this.constructors.length, i = 0; i < length; ++i) {
            try {
                return this.constructors[i].newInstance(array);
            }
            catch (IllegalArgumentException ex4) {}
            catch (InstantiationException ex) {
                throw new CannotCreateException(ex);
            }
            catch (IllegalAccessException ex2) {
                throw new CannotCreateException(ex2);
            }
            catch (InvocationTargetException ex3) {
                throw new CannotCreateException(ex3);
            }
        }
        throw new CannotCreateException("no constructor matches");
    }
    
    public Object trapFieldRead(final String s) {
        final Class javaClass = this.getJavaClass();
        try {
            return javaClass.getField(s).get(null);
        }
        catch (NoSuchFieldException ex) {
            throw new RuntimeException(ex.toString());
        }
        catch (IllegalAccessException ex2) {
            throw new RuntimeException(ex2.toString());
        }
    }
    
    public void trapFieldWrite(final String s, final Object o) {
        final Class javaClass = this.getJavaClass();
        try {
            javaClass.getField(s).set(null, o);
        }
        catch (NoSuchFieldException ex) {
            throw new RuntimeException(ex.toString());
        }
        catch (IllegalAccessException ex2) {
            throw new RuntimeException(ex2.toString());
        }
    }
    
    public static Object invoke(final Object o, final int n, final Object[] array) throws Throwable {
        final Method[] methods = o.getClass().getMethods();
        final int length = methods.length;
        final String string = "_m_" + n;
        for (int i = 0; i < length; ++i) {
            if (methods[i].getName().startsWith(string)) {
                try {
                    return methods[i].invoke(o, array);
                }
                catch (InvocationTargetException ex) {
                    throw ex.getTargetException();
                }
                catch (IllegalAccessException ex2) {
                    throw new CannotInvokeException(ex2);
                }
            }
        }
        throw new CannotInvokeException("cannot find a method");
    }
    
    public Object trapMethodcall(final int n, final Object[] array) throws Throwable {
        try {
            return this.getReflectiveMethods()[n].invoke(null, array);
        }
        catch (InvocationTargetException ex) {
            throw ex.getTargetException();
        }
        catch (IllegalAccessException ex2) {
            throw new CannotInvokeException(ex2);
        }
    }
    
    public final Method[] getReflectiveMethods() {
        if (this.methods != null) {
            return this.methods;
        }
        final Method[] declaredMethods = this.getJavaClass().getDeclaredMethods();
        final int length = declaredMethods.length;
        final int[] array = new int[length];
        int n = 0;
        for (int i = 0; i < length; ++i) {
            final String name = declaredMethods[i].getName();
            if (name.startsWith("_m_")) {
                int n2 = 0;
                int n3 = 3;
                while (true) {
                    final char char1 = name.charAt(n3);
                    if ('0' > char1 || char1 > '9') {
                        break;
                    }
                    n2 = n2 * 10 + char1 - 48;
                    ++n3;
                }
                array[i] = ++n2;
                if (n2 > n) {
                    n = n2;
                }
            }
        }
        this.methods = new Method[n];
        for (int j = 0; j < length; ++j) {
            if (array[j] > 0) {
                this.methods[array[j] - 1] = declaredMethods[j];
            }
        }
        return this.methods;
    }
    
    public final Method getMethod(final int n) {
        return this.getReflectiveMethods()[n];
    }
    
    public final String getMethodName(final int n) {
        final String name = this.getReflectiveMethods()[n].getName();
        int n2 = 3;
        char char1;
        do {
            char1 = name.charAt(n2++);
        } while (char1 >= '0' && '9' >= char1);
        return name.substring(n2);
    }
    
    public final Class[] getParameterTypes(final int n) {
        return this.getReflectiveMethods()[n].getParameterTypes();
    }
    
    public final Class getReturnType(final int n) {
        return this.getReflectiveMethods()[n].getReturnType();
    }
    
    public final int getMethodIndex(final String s, final Class[] array) throws NoSuchMethodException {
        final Method[] reflectiveMethods = this.getReflectiveMethods();
        for (int i = 0; i < reflectiveMethods.length; ++i) {
            if (reflectiveMethods[i] != null) {
                if (this.getMethodName(i).equals(s) && Arrays.equals(array, reflectiveMethods[i].getParameterTypes())) {
                    return i;
                }
            }
        }
        throw new NoSuchMethodException("Method " + s + " not found");
    }
    
    static {
        ClassMetaobject.useContextClassLoader = false;
    }
}
