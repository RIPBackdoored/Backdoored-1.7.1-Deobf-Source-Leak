package javassist.tools.rmi;

import java.lang.reflect.Method;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;
import javassist.CtField;
import javassist.Modifier;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import javassist.CtClass;
import javassist.CtMethod;
import java.util.Hashtable;
import javassist.ClassPool;
import javassist.Translator;

public class StubGenerator implements Translator
{
    private static final String fieldImporter = "importer";
    private static final String fieldObjectId = "objectId";
    private static final String accessorObjectId = "_getObjectId";
    private static final String sampleClass = "javassist.tools.rmi.Sample";
    private ClassPool classPool;
    private Hashtable proxyClasses;
    private CtMethod forwardMethod;
    private CtMethod forwardStaticMethod;
    private CtClass[] proxyConstructorParamTypes;
    private CtClass[] interfacesForProxy;
    private CtClass[] exceptionForProxy;
    
    public StubGenerator() {
        super();
        this.proxyClasses = new Hashtable();
    }
    
    @Override
    public void start(final ClassPool classPool) throws NotFoundException {
        this.classPool = classPool;
        final CtClass value = classPool.get("javassist.tools.rmi.Sample");
        this.forwardMethod = value.getDeclaredMethod("forward");
        this.forwardStaticMethod = value.getDeclaredMethod("forwardStatic");
        this.proxyConstructorParamTypes = classPool.get(new String[] { "javassist.tools.rmi.ObjectImporter", "int" });
        this.interfacesForProxy = classPool.get(new String[] { "java.io.Serializable", "javassist.tools.rmi.Proxy" });
        this.exceptionForProxy = new CtClass[] { classPool.get("javassist.tools.rmi.RemoteException") };
    }
    
    @Override
    public void onLoad(final ClassPool classPool, final String s) {
    }
    
    public boolean isProxyClass(final String s) {
        return this.proxyClasses.get(s) != null;
    }
    
    public synchronized boolean makeProxyClass(final Class clazz) throws CannotCompileException, NotFoundException {
        final String name = clazz.getName();
        if (this.proxyClasses.get(name) != null) {
            return false;
        }
        final CtClass produceProxyClass = this.produceProxyClass(this.classPool.get(name), clazz);
        this.proxyClasses.put(name, produceProxyClass);
        this.modifySuperclass(produceProxyClass);
        return true;
    }
    
    private CtClass produceProxyClass(final CtClass ctClass, final Class clazz) throws CannotCompileException, NotFoundException {
        final int modifiers = ctClass.getModifiers();
        if (Modifier.isAbstract(modifiers) || Modifier.isNative(modifiers) || !Modifier.isPublic(modifiers)) {
            throw new CannotCompileException(ctClass.getName() + " must be public, non-native, and non-abstract.");
        }
        final CtClass class1 = this.classPool.makeClass(ctClass.getName(), ctClass.getSuperclass());
        class1.setInterfaces(this.interfacesForProxy);
        final CtField ctField = new CtField(this.classPool.get("javassist.tools.rmi.ObjectImporter"), "importer", class1);
        ctField.setModifiers(2);
        class1.addField(ctField, CtField.Initializer.byParameter(0));
        final CtField ctField2 = new CtField(CtClass.intType, "objectId", class1);
        ctField2.setModifiers(2);
        class1.addField(ctField2, CtField.Initializer.byParameter(1));
        class1.addMethod(CtNewMethod.getter("_getObjectId", ctField2));
        class1.addConstructor(CtNewConstructor.defaultConstructor(class1));
        class1.addConstructor(CtNewConstructor.skeleton(this.proxyConstructorParamTypes, null, class1));
        try {
            this.addMethods(class1, clazz.getMethods());
            return class1;
        }
        catch (SecurityException ex) {
            throw new CannotCompileException(ex);
        }
    }
    
    private CtClass toCtClass(Class componentType) throws NotFoundException {
        String s;
        if (!componentType.isArray()) {
            s = componentType.getName();
        }
        else {
            final StringBuffer sb = new StringBuffer();
            do {
                sb.append("[]");
                componentType = componentType.getComponentType();
            } while (componentType.isArray());
            sb.insert(0, componentType.getName());
            s = sb.toString();
        }
        return this.classPool.get(s);
    }
    
    private CtClass[] toCtClass(final Class[] array) throws NotFoundException {
        final int length = array.length;
        final CtClass[] array2 = new CtClass[length];
        for (int i = 0; i < length; ++i) {
            array2[i] = this.toCtClass(array[i]);
        }
        return array2;
    }
    
    private void addMethods(final CtClass ctClass, final Method[] array) throws CannotCompileException, NotFoundException {
        for (int i = 0; i < array.length; ++i) {
            final Method method = array[i];
            final int modifiers = method.getModifiers();
            if (method.getDeclaringClass() != Object.class && !Modifier.isFinal(modifiers)) {
                if (Modifier.isPublic(modifiers)) {
                    CtMethod ctMethod;
                    if (Modifier.isStatic(modifiers)) {
                        ctMethod = this.forwardStaticMethod;
                    }
                    else {
                        ctMethod = this.forwardMethod;
                    }
                    final CtMethod wrapped = CtNewMethod.wrapped(this.toCtClass(method.getReturnType()), method.getName(), this.toCtClass((Class[])method.getParameterTypes()), this.exceptionForProxy, ctMethod, CtMethod.ConstParameter.integer(i), ctClass);
                    wrapped.setModifiers(modifiers);
                    ctClass.addMethod(wrapped);
                }
                else if (!Modifier.isProtected(modifiers) && !Modifier.isPrivate(modifiers)) {
                    throw new CannotCompileException("the methods must be public, protected, or private.");
                }
            }
        }
    }
    
    private void modifySuperclass(CtClass ctClass) throws CannotCompileException, NotFoundException {
        while (true) {
            final CtClass superclass = ctClass.getSuperclass();
            if (superclass == null) {
                break;
            }
            try {
                superclass.getDeclaredConstructor(null);
            }
            catch (NotFoundException ex) {
                superclass.addConstructor(CtNewConstructor.defaultConstructor(superclass));
                ctClass = superclass;
                continue;
            }
            break;
        }
    }
}
