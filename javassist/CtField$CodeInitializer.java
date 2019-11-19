package javassist;

import javassist.compiler.SymbolTable;
import javassist.bytecode.ConstPool;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;

static class CodeInitializer extends CodeInitializer0
{
    private String expression;
    
    CodeInitializer(final String expression) {
        super();
        this.expression = expression;
    }
    
    @Override
    void compileExpr(final Javac javac) throws CompileError {
        javac.compileExpr(this.expression);
    }
    
    @Override
    int getConstantValue(final ConstPool constPool, final CtClass ctClass) {
        try {
            return this.getConstantValue2(constPool, ctClass, Javac.parseExpr(this.expression, new SymbolTable()));
        }
        catch (CompileError compileError) {
            return 0;
        }
    }
}
