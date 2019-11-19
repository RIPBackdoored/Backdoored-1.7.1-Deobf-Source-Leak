package javassist;

import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.compiler.Javac;
import javassist.bytecode.Bytecode;

static class LongInitializer extends Initializer
{
    long value;
    
    LongInitializer(final long value) {
        super();
        this.value = value;
    }
    
    @Override
    void check(final String s) throws CannotCompileException {
        if (!s.equals("J")) {
            throw new CannotCompileException("type mismatch");
        }
    }
    
    @Override
    int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) throws CannotCompileException {
        bytecode.addAload(0);
        bytecode.addLdc2w(this.value);
        bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
        return 3;
    }
    
    @Override
    int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) throws CannotCompileException {
        bytecode.addLdc2w(this.value);
        bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctClass));
        return 2;
    }
    
    @Override
    int getConstantValue(final ConstPool constPool, final CtClass ctClass) {
        if (ctClass == CtClass.longType) {
            return constPool.addLongInfo(this.value);
        }
        return 0;
    }
}
