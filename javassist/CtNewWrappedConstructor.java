package javassist;

import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;

class CtNewWrappedConstructor extends CtNewWrappedMethod
{
    private static final int PASS_NONE = 0;
    private static final int PASS_PARAMS = 2;
    
    CtNewWrappedConstructor() {
        super();
    }
    
    public static CtConstructor wrapped(final CtClass[] array, final CtClass[] exceptionTypes, final int n, final CtMethod ctMethod, final CtMethod.ConstParameter constParameter, final CtClass ctClass) throws CannotCompileException {
        try {
            final CtConstructor ctConstructor = new CtConstructor(array, ctClass);
            ctConstructor.setExceptionTypes(exceptionTypes);
            ctConstructor.getMethodInfo2().setCodeAttribute(makeBody(ctClass, ctClass.getClassFile2(), n, ctMethod, array, constParameter).toCodeAttribute());
            return ctConstructor;
        }
        catch (NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
    }
    
    protected static Bytecode makeBody(final CtClass ctClass, final ClassFile classFile, final int n, final CtMethod ctMethod, final CtClass[] array, final CtMethod.ConstParameter constParameter) throws CannotCompileException {
        final int superclassId = classFile.getSuperclassId();
        final Bytecode bytecode = new Bytecode(classFile.getConstPool(), 0, 0);
        bytecode.setMaxLocals(false, array, 0);
        bytecode.addAload(0);
        int maxStack = 1;
        bytecode.addInvokespecial(superclassId, "<init>", "()V");
        if (ctMethod == null) {
            bytecode.add(177);
        }
        else {
            final int body0 = CtNewWrappedMethod.makeBody0(ctClass, classFile, ctMethod, false, array, CtClass.voidType, constParameter, bytecode);
            if (maxStack < body0) {
                maxStack = body0;
            }
        }
        bytecode.setMaxStack(maxStack);
        return bytecode;
    }
}
