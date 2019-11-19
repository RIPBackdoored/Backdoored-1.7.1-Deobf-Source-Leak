package javassist;

import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.compiler.Javac;
import javassist.bytecode.Bytecode;

static class StringInitializer extends Initializer
{
    String value;
    
    StringInitializer(final String value) {
        super();
        this.value = value;
    }
    
    @Override
    int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) throws CannotCompileException {
        bytecode.addAload(0);
        bytecode.addLdc(this.value);
        bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
        return 2;
    }
    
    @Override
    int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) throws CannotCompileException {
        bytecode.addLdc(this.value);
        bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctClass));
        return 1;
    }
    
    @Override
    int getConstantValue(final ConstPool constPool, final CtClass ctClass) {
        if (ctClass.getName().equals("java.lang.String")) {
            return constPool.addStringInfo(this.value);
        }
        return 0;
    }
}
