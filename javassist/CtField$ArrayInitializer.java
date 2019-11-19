package javassist;

import javassist.bytecode.Descriptor;
import javassist.compiler.Javac;
import javassist.bytecode.Bytecode;

static class ArrayInitializer extends Initializer
{
    CtClass type;
    int size;
    
    ArrayInitializer(final CtClass type, final int size) {
        super();
        this.type = type;
        this.size = size;
    }
    
    private void addNewarray(final Bytecode bytecode) {
        if (this.type.isPrimitive()) {
            bytecode.addNewarray(((CtPrimitiveType)this.type).getArrayType(), this.size);
        }
        else {
            bytecode.addAnewarray(this.type, this.size);
        }
    }
    
    @Override
    int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) throws CannotCompileException {
        bytecode.addAload(0);
        this.addNewarray(bytecode);
        bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
        return 2;
    }
    
    @Override
    int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) throws CannotCompileException {
        this.addNewarray(bytecode);
        bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctClass));
        return 1;
    }
}
