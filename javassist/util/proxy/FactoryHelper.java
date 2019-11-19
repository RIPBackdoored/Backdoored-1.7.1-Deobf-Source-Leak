package javassist.util.proxy;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import javassist.CannotCompileException;
import java.security.ProtectionDomain;
import javassist.bytecode.ClassFile;
import java.lang.reflect.Method;

public class FactoryHelper
{
    private static Method defineClass1;
    private static Method defineClass2;
    public static final Class[] primitiveTypes;
    public static final String[] wrapperTypes;
    public static final String[] wrapperDesc;
    public static final String[] unwarpMethods;
    public static final String[] unwrapDesc;
    public static final int[] dataSize;
    
    public FactoryHelper() {
        super();
    }
    
    public static final int typeIndex(final Class clazz) {
        final Class[] primitiveTypes = FactoryHelper.primitiveTypes;
        for (int length = primitiveTypes.length, i = 0; i < length; ++i) {
            if (primitiveTypes[i] == clazz) {
                return i;
            }
        }
        throw new RuntimeException("bad type:" + clazz.getName());
    }
    
    public static Class toClass(final ClassFile classFile, final ClassLoader classLoader) throws CannotCompileException {
        return toClass(classFile, classLoader, null);
    }
    
    public static Class toClass(final ClassFile classFile, final ClassLoader classLoader, final ProtectionDomain protectionDomain) throws CannotCompileException {
        try {
            final byte[] bytecode = toBytecode(classFile);
            Method method;
            Object[] array;
            if (protectionDomain == null) {
                method = FactoryHelper.defineClass1;
                array = new Object[] { classFile.getName(), bytecode, new Integer(0), new Integer(bytecode.length) };
            }
            else {
                method = FactoryHelper.defineClass2;
                array = new Object[] { classFile.getName(), bytecode, new Integer(0), new Integer(bytecode.length), protectionDomain };
            }
            return toClass2(method, classLoader, array);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (InvocationTargetException ex2) {
            throw new CannotCompileException(ex2.getTargetException());
        }
        catch (Exception ex3) {
            throw new CannotCompileException(ex3);
        }
    }
    
    private static synchronized Class toClass2(final Method method, final ClassLoader classLoader, final Object[] array) throws Exception {
        SecurityActions.setAccessible(method, true);
        final Class clazz = (Class)method.invoke(classLoader, array);
        SecurityActions.setAccessible(method, false);
        return clazz;
    }
    
    private static byte[] toBytecode(final ClassFile classFile) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            classFile.write(dataOutputStream);
        }
        finally {
            dataOutputStream.close();
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    public static void writeFile(final ClassFile classFile, final String s) throws CannotCompileException {
        try {
            writeFile0(classFile, s);
        }
        catch (IOException ex) {
            throw new CannotCompileException(ex);
        }
    }
    
    private static void writeFile0(final ClassFile classFile, final String s) throws CannotCompileException, IOException {
        final String string = s + File.separatorChar + classFile.getName().replace('.', File.separatorChar) + ".class";
        final int lastIndex = string.lastIndexOf(File.separatorChar);
        if (lastIndex > 0) {
            final String substring = string.substring(0, lastIndex);
            if (!substring.equals(".")) {
                new File(substring).mkdirs();
            }
        }
        final DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(string)));
        try {
            classFile.write(dataOutputStream);
        }
        catch (IOException ex) {
            throw ex;
        }
        finally {
            dataOutputStream.close();
        }
    }
    
    static {
        try {
            final Class<?> forName = Class.forName("java.lang.ClassLoader");
            FactoryHelper.defineClass1 = SecurityActions.getDeclaredMethod(forName, "defineClass", new Class[] { String.class, byte[].class, Integer.TYPE, Integer.TYPE });
            FactoryHelper.defineClass2 = SecurityActions.getDeclaredMethod(forName, "defineClass", new Class[] { String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class });
        }
        catch (Exception ex) {
            throw new RuntimeException("cannot initialize");
        }
        primitiveTypes = new Class[] { Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Void.TYPE };
        wrapperTypes = new String[] { "java.lang.Boolean", "java.lang.Byte", "java.lang.Character", "java.lang.Short", "java.lang.Integer", "java.lang.Long", "java.lang.Float", "java.lang.Double", "java.lang.Void" };
        wrapperDesc = new String[] { "(Z)V", "(B)V", "(C)V", "(S)V", "(I)V", "(J)V", "(F)V", "(D)V" };
        unwarpMethods = new String[] { "booleanValue", "byteValue", "charValue", "shortValue", "intValue", "longValue", "floatValue", "doubleValue" };
        unwrapDesc = new String[] { "()Z", "()B", "()C", "()S", "()I", "()J", "()F", "()D" };
        dataSize = new int[] { 1, 1, 1, 1, 1, 2, 1, 2 };
    }
}
