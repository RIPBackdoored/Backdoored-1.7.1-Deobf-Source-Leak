package javassist;

import javassist.bytecode.Bytecode;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;

public class CtNewConstructor
{
    public static final int PASS_NONE = 0;
    public static final int PASS_ARRAY = 1;
    public static final int PASS_PARAMS = 2;
    
    public CtNewConstructor() {
        super();
    }
    
    public static CtConstructor make(final String s, final CtClass ctClass) throws CannotCompileException {
        final Javac javac = new Javac(ctClass);
        try {
            final CtMember compile = javac.compile(s);
            if (compile instanceof CtConstructor) {
                return (CtConstructor)compile;
            }
        }
        catch (CompileError compileError) {
            throw new CannotCompileException(compileError);
        }
        throw new CannotCompileException("not a constructor");
    }
    
    public static CtConstructor make(final CtClass[] array, final CtClass[] exceptionTypes, final String body, final CtClass ctClass) throws CannotCompileException {
        try {
            final CtConstructor ctConstructor = new CtConstructor(array, ctClass);
            ctConstructor.setExceptionTypes(exceptionTypes);
            ctConstructor.setBody(body);
            return ctConstructor;
        }
        catch (NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
    }
    
    public static CtConstructor copy(final CtConstructor ctConstructor, final CtClass ctClass, final ClassMap classMap) throws CannotCompileException {
        return new CtConstructor(ctConstructor, ctClass, classMap);
    }
    
    public static CtConstructor defaultConstructor(final CtClass ctClass) throws CannotCompileException {
        final CtConstructor ctConstructor = new CtConstructor((CtClass[])null, ctClass);
        final Bytecode bytecode = new Bytecode(ctClass.getClassFile2().getConstPool(), 1, 1);
        bytecode.addAload(0);
        try {
            bytecode.addInvokespecial(ctClass.getSuperclass(), "<init>", "()V");
        }
        catch (NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
        bytecode.add(177);
        ctConstructor.getMethodInfo2().setCodeAttribute(bytecode.toCodeAttribute());
        return ctConstructor;
    }
    
    public static CtConstructor skeleton(final CtClass[] array, final CtClass[] array2, final CtClass ctClass) throws CannotCompileException {
        return make(array, array2, 0, null, null, ctClass);
    }
    
    public static CtConstructor make(final CtClass[] array, final CtClass[] array2, final CtClass ctClass) throws CannotCompileException {
        return make(array, array2, 2, null, null, ctClass);
    }
    
    public static CtConstructor make(final CtClass[] array, final CtClass[] array2, final int n, final CtMethod ctMethod, final CtMethod.ConstParameter constParameter, final CtClass ctClass) throws CannotCompileException {
        return CtNewWrappedConstructor.wrapped(array, array2, n, ctMethod, constParameter, ctClass);
    }
}
