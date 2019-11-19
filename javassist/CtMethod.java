package javassist;

import javassist.bytecode.Bytecode;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.Descriptor;
import javassist.bytecode.MethodInfo;

public final class CtMethod extends CtBehavior
{
    protected String cachedStringRep;
    
    CtMethod(final MethodInfo methodInfo, final CtClass ctClass) {
        super(ctClass, methodInfo);
        this.cachedStringRep = null;
    }
    
    public CtMethod(final CtClass ctClass, final String s, final CtClass[] array, final CtClass ctClass2) {
        this(null, ctClass2);
        this.methodInfo = new MethodInfo(ctClass2.getClassFile2().getConstPool(), s, Descriptor.ofMethod(ctClass, array));
        this.setModifiers(1025);
    }
    
    public CtMethod(final CtMethod ctMethod, final CtClass ctClass, final ClassMap classMap) throws CannotCompileException {
        this(null, ctClass);
        this.copy(ctMethod, false, classMap);
    }
    
    public static CtMethod make(final String s, final CtClass ctClass) throws CannotCompileException {
        return CtNewMethod.make(s, ctClass);
    }
    
    public static CtMethod make(final MethodInfo methodInfo, final CtClass ctClass) throws CannotCompileException {
        if (ctClass.getClassFile2().getConstPool() != methodInfo.getConstPool()) {
            throw new CannotCompileException("bad declaring class");
        }
        return new CtMethod(methodInfo, ctClass);
    }
    
    @Override
    public int hashCode() {
        return this.getStringRep().hashCode();
    }
    
    @Override
    void nameReplaced() {
        this.cachedStringRep = null;
    }
    
    final String getStringRep() {
        if (this.cachedStringRep == null) {
            this.cachedStringRep = this.methodInfo.getName() + Descriptor.getParamDescriptor(this.methodInfo.getDescriptor());
        }
        return this.cachedStringRep;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o != null && o instanceof CtMethod && ((CtMethod)o).getStringRep().equals(this.getStringRep());
    }
    
    @Override
    public String getLongName() {
        return this.getDeclaringClass().getName() + "." + this.getName() + Descriptor.toString(this.getSignature());
    }
    
    @Override
    public String getName() {
        return this.methodInfo.getName();
    }
    
    public void setName(final String name) {
        this.declaringClass.checkModify();
        this.methodInfo.setName(name);
    }
    
    public CtClass getReturnType() throws NotFoundException {
        return this.getReturnType0();
    }
    
    @Override
    public boolean isEmpty() {
        final CodeAttribute codeAttribute = this.getMethodInfo2().getCodeAttribute();
        if (codeAttribute == null) {
            return (this.getModifiers() & 0x400) != 0x0;
        }
        final CodeIterator iterator = codeAttribute.iterator();
        try {
            return iterator.hasNext() && iterator.byteAt(iterator.next()) == 177 && !iterator.hasNext();
        }
        catch (BadBytecode badBytecode) {
            return false;
        }
    }
    
    public void setBody(final CtMethod ctMethod, final ClassMap classMap) throws CannotCompileException {
        CtBehavior.setBody0(ctMethod.declaringClass, ctMethod.methodInfo, this.declaringClass, this.methodInfo, classMap);
    }
    
    public void setWrappedBody(final CtMethod ctMethod, final ConstParameter constParameter) throws CannotCompileException {
        this.declaringClass.checkModify();
        final CtClass declaringClass = this.getDeclaringClass();
        CtClass[] parameterTypes;
        CtClass returnType;
        try {
            parameterTypes = this.getParameterTypes();
            returnType = this.getReturnType();
        }
        catch (NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
        this.methodInfo.setCodeAttribute(CtNewWrappedMethod.makeBody(declaringClass, declaringClass.getClassFile2(), ctMethod, parameterTypes, returnType, constParameter).toCodeAttribute());
        this.methodInfo.setAccessFlags(this.methodInfo.getAccessFlags() & 0xFFFFFBFF);
    }
}
