package javassist;

import javassist.bytecode.Descriptor;
import javassist.compiler.Javac;
import javassist.bytecode.Bytecode;

static class ParamInitializer extends Initializer
{
    int nthParam;
    
    ParamInitializer() {
        super();
    }
    
    @Override
    int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) throws CannotCompileException {
        if (array != null && this.nthParam < array.length) {
            bytecode.addAload(0);
            final int n = bytecode.addLoad(nthParamToLocal(this.nthParam, array, false), ctClass) + 1;
            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
            return n;
        }
        return 0;
    }
    
    static int nthParamToLocal(final int n, final CtClass[] array, final boolean b) {
        final CtClass longType = CtClass.longType;
        final CtClass doubleType = CtClass.doubleType;
        int n2;
        if (b) {
            n2 = 0;
        }
        else {
            n2 = 1;
        }
        for (final CtClass ctClass : array) {
            if (ctClass == longType || ctClass == doubleType) {
                n2 += 2;
            }
            else {
                ++n2;
            }
        }
        return n2;
    }
    
    @Override
    int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) throws CannotCompileException {
        return 0;
    }
}
