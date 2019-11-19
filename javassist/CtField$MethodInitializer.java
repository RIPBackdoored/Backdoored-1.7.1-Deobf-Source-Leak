package javassist;

import javassist.bytecode.Descriptor;
import javassist.compiler.Javac;
import javassist.bytecode.Bytecode;

static class MethodInitializer extends NewInitializer
{
    String methodName;
    
    MethodInitializer() {
        super();
    }
    
    @Override
    int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) throws CannotCompileException {
        bytecode.addAload(0);
        bytecode.addAload(0);
        int n;
        if (this.stringParams == null) {
            n = 2;
        }
        else {
            n = this.compileStringParameter(bytecode) + 2;
        }
        if (this.withConstructorParams) {
            n += CtNewWrappedMethod.compileParameterList(bytecode, array, 1);
        }
        final String of = Descriptor.of(ctClass);
        bytecode.addInvokestatic(this.objectType, this.methodName, this.getDescriptor() + of);
        bytecode.addPutfield(Bytecode.THIS, s, of);
        return n;
    }
    
    private String getDescriptor() {
        if (this.stringParams == null) {
            if (this.withConstructorParams) {
                return "(Ljava/lang/Object;[Ljava/lang/Object;)";
            }
            return "(Ljava/lang/Object;)";
        }
        else {
            if (this.withConstructorParams) {
                return "(Ljava/lang/Object;[Ljava/lang/String;[Ljava/lang/Object;)";
            }
            return "(Ljava/lang/Object;[Ljava/lang/String;)";
        }
    }
    
    @Override
    int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) throws CannotCompileException {
        int n = 1;
        String s2;
        if (this.stringParams == null) {
            s2 = "()";
        }
        else {
            s2 = "([Ljava/lang/String;)";
            n += this.compileStringParameter(bytecode);
        }
        final String of = Descriptor.of(ctClass);
        bytecode.addInvokestatic(this.objectType, this.methodName, s2 + of);
        bytecode.addPutstatic(Bytecode.THIS, s, of);
        return n;
    }
}
