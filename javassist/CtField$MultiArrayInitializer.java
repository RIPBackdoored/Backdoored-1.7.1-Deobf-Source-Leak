package javassist;

import javassist.bytecode.Descriptor;
import javassist.compiler.Javac;
import javassist.bytecode.Bytecode;

static class MultiArrayInitializer extends Initializer
{
    CtClass type;
    int[] dim;
    
    MultiArrayInitializer(final CtClass type, final int[] dim) {
        super();
        this.type = type;
        this.dim = dim;
    }
    
    @Override
    void check(final String s) throws CannotCompileException {
        if (s.charAt(0) != '[') {
            throw new CannotCompileException("type mismatch");
        }
    }
    
    @Override
    int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) throws CannotCompileException {
        bytecode.addAload(0);
        final int addMultiNewarray = bytecode.addMultiNewarray(ctClass, this.dim);
        bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
        return addMultiNewarray + 1;
    }
    
    @Override
    int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) throws CannotCompileException {
        final int addMultiNewarray = bytecode.addMultiNewarray(ctClass, this.dim);
        bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctClass));
        return addMultiNewarray;
    }
}
