package javassist.compiler;

import javassist.bytecode.FieldInfo;
import javassist.bytecode.ExceptionsAttribute;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ClassFile;
import javassist.NotFoundException;
import javassist.CannotCompileException;
import javassist.bytecode.Bytecode;
import java.util.Map;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.SyntheticAttribute;
import javassist.bytecode.Descriptor;
import javassist.bytecode.MethodInfo;
import java.util.HashMap;
import javassist.CtClass;

public class AccessorMaker
{
    private CtClass clazz;
    private int uniqueNumber;
    private HashMap accessors;
    static final String lastParamType = "javassist.runtime.Inner";
    
    public AccessorMaker(final CtClass clazz) {
        super();
        this.clazz = clazz;
        this.uniqueNumber = 1;
        this.accessors = new HashMap();
    }
    
    public String getConstructor(final CtClass ctClass, final String s, final MethodInfo methodInfo) throws CompileError {
        final String string = "<init>:" + s;
        final String s2 = this.accessors.get(string);
        if (s2 != null) {
            return s2;
        }
        final String appendParameter = Descriptor.appendParameter("javassist.runtime.Inner", s);
        final ClassFile classFile = this.clazz.getClassFile();
        try {
            final ConstPool constPool = classFile.getConstPool();
            final ClassPool classPool = this.clazz.getClassPool();
            final MethodInfo methodInfo2 = new MethodInfo(constPool, "<init>", appendParameter);
            methodInfo2.setAccessFlags(0);
            methodInfo2.addAttribute(new SyntheticAttribute(constPool));
            final ExceptionsAttribute exceptionsAttribute = methodInfo.getExceptionsAttribute();
            if (exceptionsAttribute != null) {
                methodInfo2.addAttribute(exceptionsAttribute.copy(constPool, null));
            }
            final CtClass[] parameterTypes = Descriptor.getParameterTypes(s, classPool);
            final Bytecode bytecode = new Bytecode(constPool);
            bytecode.addAload(0);
            int n = 1;
            for (int i = 0; i < parameterTypes.length; ++i) {
                n += bytecode.addLoad(n, parameterTypes[i]);
            }
            bytecode.setMaxLocals(n + 1);
            bytecode.addInvokespecial(this.clazz, "<init>", s);
            bytecode.addReturn(null);
            methodInfo2.setCodeAttribute(bytecode.toCodeAttribute());
            classFile.addMethod(methodInfo2);
        }
        catch (CannotCompileException ex) {
            throw new CompileError(ex);
        }
        catch (NotFoundException ex2) {
            throw new CompileError(ex2);
        }
        this.accessors.put(string, appendParameter);
        return appendParameter;
    }
    
    public String getMethodAccessor(final String s, final String s2, final String s3, final MethodInfo methodInfo) throws CompileError {
        final String string = s + ":" + s2;
        final String s4 = this.accessors.get(string);
        if (s4 != null) {
            return s4;
        }
        final ClassFile classFile = this.clazz.getClassFile();
        final String accessorName = this.findAccessorName(classFile);
        try {
            final ConstPool constPool = classFile.getConstPool();
            final ClassPool classPool = this.clazz.getClassPool();
            final MethodInfo methodInfo2 = new MethodInfo(constPool, accessorName, s3);
            methodInfo2.setAccessFlags(8);
            methodInfo2.addAttribute(new SyntheticAttribute(constPool));
            final ExceptionsAttribute exceptionsAttribute = methodInfo.getExceptionsAttribute();
            if (exceptionsAttribute != null) {
                methodInfo2.addAttribute(exceptionsAttribute.copy(constPool, null));
            }
            final CtClass[] parameterTypes = Descriptor.getParameterTypes(s3, classPool);
            int maxLocals = 0;
            final Bytecode bytecode = new Bytecode(constPool);
            for (int i = 0; i < parameterTypes.length; ++i) {
                maxLocals += bytecode.addLoad(maxLocals, parameterTypes[i]);
            }
            bytecode.setMaxLocals(maxLocals);
            if (s2 == s3) {
                bytecode.addInvokestatic(this.clazz, s, s2);
            }
            else {
                bytecode.addInvokevirtual(this.clazz, s, s2);
            }
            bytecode.addReturn(Descriptor.getReturnType(s2, classPool));
            methodInfo2.setCodeAttribute(bytecode.toCodeAttribute());
            classFile.addMethod(methodInfo2);
        }
        catch (CannotCompileException ex) {
            throw new CompileError(ex);
        }
        catch (NotFoundException ex2) {
            throw new CompileError(ex2);
        }
        this.accessors.put(string, accessorName);
        return accessorName;
    }
    
    public MethodInfo getFieldGetter(final FieldInfo fieldInfo, final boolean b) throws CompileError {
        final String name = fieldInfo.getName();
        final String string = name + ":getter";
        final Object value = this.accessors.get(string);
        if (value != null) {
            return (MethodInfo)value;
        }
        final ClassFile classFile = this.clazz.getClassFile();
        final String accessorName = this.findAccessorName(classFile);
        try {
            final ConstPool constPool = classFile.getConstPool();
            final ClassPool classPool = this.clazz.getClassPool();
            final String descriptor = fieldInfo.getDescriptor();
            String s;
            if (b) {
                s = "()" + descriptor;
            }
            else {
                s = "(" + Descriptor.of(this.clazz) + ")" + descriptor;
            }
            final MethodInfo methodInfo = new MethodInfo(constPool, accessorName, s);
            methodInfo.setAccessFlags(8);
            methodInfo.addAttribute(new SyntheticAttribute(constPool));
            final Bytecode bytecode = new Bytecode(constPool);
            if (b) {
                bytecode.addGetstatic(Bytecode.THIS, name, descriptor);
            }
            else {
                bytecode.addAload(0);
                bytecode.addGetfield(Bytecode.THIS, name, descriptor);
                bytecode.setMaxLocals(1);
            }
            bytecode.addReturn(Descriptor.toCtClass(descriptor, classPool));
            methodInfo.setCodeAttribute(bytecode.toCodeAttribute());
            classFile.addMethod(methodInfo);
            this.accessors.put(string, methodInfo);
            return methodInfo;
        }
        catch (CannotCompileException ex) {
            throw new CompileError(ex);
        }
        catch (NotFoundException ex2) {
            throw new CompileError(ex2);
        }
    }
    
    public MethodInfo getFieldSetter(final FieldInfo fieldInfo, final boolean b) throws CompileError {
        final String name = fieldInfo.getName();
        final String string = name + ":setter";
        final Object value = this.accessors.get(string);
        if (value != null) {
            return (MethodInfo)value;
        }
        final ClassFile classFile = this.clazz.getClassFile();
        final String accessorName = this.findAccessorName(classFile);
        try {
            final ConstPool constPool = classFile.getConstPool();
            final ClassPool classPool = this.clazz.getClassPool();
            final String descriptor = fieldInfo.getDescriptor();
            String s;
            if (b) {
                s = "(" + descriptor + ")V";
            }
            else {
                s = "(" + Descriptor.of(this.clazz) + descriptor + ")V";
            }
            final MethodInfo methodInfo = new MethodInfo(constPool, accessorName, s);
            methodInfo.setAccessFlags(8);
            methodInfo.addAttribute(new SyntheticAttribute(constPool));
            final Bytecode bytecode = new Bytecode(constPool);
            int addLoad;
            if (b) {
                addLoad = bytecode.addLoad(0, Descriptor.toCtClass(descriptor, classPool));
                bytecode.addPutstatic(Bytecode.THIS, name, descriptor);
            }
            else {
                bytecode.addAload(0);
                addLoad = bytecode.addLoad(1, Descriptor.toCtClass(descriptor, classPool)) + 1;
                bytecode.addPutfield(Bytecode.THIS, name, descriptor);
            }
            bytecode.addReturn(null);
            bytecode.setMaxLocals(addLoad);
            methodInfo.setCodeAttribute(bytecode.toCodeAttribute());
            classFile.addMethod(methodInfo);
            this.accessors.put(string, methodInfo);
            return methodInfo;
        }
        catch (CannotCompileException ex) {
            throw new CompileError(ex);
        }
        catch (NotFoundException ex2) {
            throw new CompileError(ex2);
        }
    }
    
    private String findAccessorName(final ClassFile classFile) {
        String string;
        do {
            string = "access$" + this.uniqueNumber++;
        } while (classFile.getMethod(string) != null);
        return string;
    }
}
