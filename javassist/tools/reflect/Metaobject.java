package javassist.tools.reflect;

import java.lang.reflect.InvocationTargetException;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.io.Serializable;

public class Metaobject implements Serializable
{
    protected ClassMetaobject classmetaobject;
    protected Metalevel baseobject;
    protected Method[] methods;
    
    public Metaobject(final Object o, final Object[] array) {
        super();
        this.baseobject = (Metalevel)o;
        this.classmetaobject = this.baseobject._getClass();
        this.methods = this.classmetaobject.getReflectiveMethods();
    }
    
    protected Metaobject() {
        super();
        this.baseobject = null;
        this.classmetaobject = null;
        this.methods = null;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.baseobject);
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.baseobject = (Metalevel)objectInputStream.readObject();
        this.classmetaobject = this.baseobject._getClass();
        this.methods = this.classmetaobject.getReflectiveMethods();
    }
    
    public final ClassMetaobject getClassMetaobject() {
        return this.classmetaobject;
    }
    
    public final Object getObject() {
        return this.baseobject;
    }
    
    public final void setObject(final Object o) {
        this.baseobject = (Metalevel)o;
        this.classmetaobject = this.baseobject._getClass();
        this.methods = this.classmetaobject.getReflectiveMethods();
        this.baseobject._setMetaobject(this);
    }
    
    public final String getMethodName(final int n) {
        final String name = this.methods[n].getName();
        int n2 = 3;
        char char1;
        do {
            char1 = name.charAt(n2++);
        } while (char1 >= '0' && '9' >= char1);
        return name.substring(n2);
    }
    
    public final Class[] getParameterTypes(final int n) {
        return this.methods[n].getParameterTypes();
    }
    
    public final Class getReturnType(final int n) {
        return this.methods[n].getReturnType();
    }
    
    public Object trapFieldRead(final String s) {
        final Class javaClass = this.getClassMetaobject().getJavaClass();
        try {
            return javaClass.getField(s).get(this.getObject());
        }
        catch (NoSuchFieldException ex) {
            throw new RuntimeException(ex.toString());
        }
        catch (IllegalAccessException ex2) {
            throw new RuntimeException(ex2.toString());
        }
    }
    
    public void trapFieldWrite(final String s, final Object o) {
        final Class javaClass = this.getClassMetaobject().getJavaClass();
        try {
            javaClass.getField(s).set(this.getObject(), o);
        }
        catch (NoSuchFieldException ex) {
            throw new RuntimeException(ex.toString());
        }
        catch (IllegalAccessException ex2) {
            throw new RuntimeException(ex2.toString());
        }
    }
    
    public Object trapMethodcall(final int n, final Object[] array) throws Throwable {
        try {
            return this.methods[n].invoke(this.getObject(), array);
        }
        catch (InvocationTargetException ex) {
            throw ex.getTargetException();
        }
        catch (IllegalAccessException ex2) {
            throw new CannotInvokeException(ex2);
        }
    }
}
