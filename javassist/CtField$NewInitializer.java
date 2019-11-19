package javassist;

import javassist.bytecode.Descriptor;
import javassist.compiler.Javac;
import javassist.bytecode.Bytecode;

static class NewInitializer extends Initializer
{
    CtClass objectType;
    String[] stringParams;
    boolean withConstructorParams;
    
    NewInitializer() {
        super();
    }
    
    @Override
    int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) throws CannotCompileException {
        bytecode.addAload(0);
        bytecode.addNew(this.objectType);
        bytecode.add(89);
        bytecode.addAload(0);
        int n;
        if (this.stringParams == null) {
            n = 4;
        }
        else {
            n = this.compileStringParameter(bytecode) + 4;
        }
        if (this.withConstructorParams) {
            n += CtNewWrappedMethod.compileParameterList(bytecode, array, 1);
        }
        bytecode.addInvokespecial(this.objectType, "<init>", this.getDescriptor());
        bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
        return n;
    }
    
    private String getDescriptor() {
        if (this.stringParams == null) {
            if (this.withConstructorParams) {
                return "(Ljava/lang/Object;[Ljava/lang/Object;)V";
            }
            return "(Ljava/lang/Object;)V";
        }
        else {
            if (this.withConstructorParams) {
                return "(Ljava/lang/Object;[Ljava/lang/String;[Ljava/lang/Object;)V";
            }
            return "(Ljava/lang/Object;[Ljava/lang/String;)V";
        }
    }
    
    @Override
    int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) throws CannotCompileException {
        bytecode.addNew(this.objectType);
        bytecode.add(89);
        int n = 2;
        String s2;
        if (this.stringParams == null) {
            s2 = "()V";
        }
        else {
            s2 = "([Ljava/lang/String;)V";
            n += this.compileStringParameter(bytecode);
        }
        bytecode.addInvokespecial(this.objectType, "<init>", s2);
        bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctClass));
        return n;
    }
    
    protected final int compileStringParameter(final Bytecode bytecode) throws CannotCompileException {
        final int length = this.stringParams.length;
        bytecode.addIconst(length);
        bytecode.addAnewarray("java.lang.String");
        for (int i = 0; i < length; ++i) {
            bytecode.add(89);
            bytecode.addIconst(i);
            bytecode.addLdc(this.stringParams[i]);
            bytecode.add(83);
        }
        return 4;
    }
}
