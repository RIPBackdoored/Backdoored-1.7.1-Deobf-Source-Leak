package javassist;

import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.compiler.Javac;
import javassist.bytecode.Bytecode;

static class IntInitializer extends Initializer
{
    int value;
    
    IntInitializer(final int value) {
        super();
        this.value = value;
    }
    
    @Override
    void check(final String s) throws CannotCompileException {
        final char char1 = s.charAt(0);
        if (char1 != 'I' && char1 != 'S' && char1 != 'B' && char1 != 'C' && char1 != 'Z') {
            throw new CannotCompileException("type mismatch");
        }
    }
    
    @Override
    int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) throws CannotCompileException {
        bytecode.addAload(0);
        bytecode.addIconst(this.value);
        bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
        return 2;
    }
    
    @Override
    int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) throws CannotCompileException {
        bytecode.addIconst(this.value);
        bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctClass));
        return 1;
    }
    
    @Override
    int getConstantValue(final ConstPool constPool, final CtClass ctClass) {
        return constPool.addIntegerInfo(this.value);
    }
}
