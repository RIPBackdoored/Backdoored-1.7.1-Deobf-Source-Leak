package javassist;

import javassist.bytecode.ConstPool;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;
import javassist.compiler.ast.ASTree;

static class PtreeInitializer extends CodeInitializer0
{
    private ASTree expression;
    
    PtreeInitializer(final ASTree expression) {
        super();
        this.expression = expression;
    }
    
    @Override
    void compileExpr(final Javac javac) throws CompileError {
        javac.compileExpr(this.expression);
    }
    
    @Override
    int getConstantValue(final ConstPool constPool, final CtClass ctClass) {
        return this.getConstantValue2(constPool, ctClass, this.expression);
    }
}
