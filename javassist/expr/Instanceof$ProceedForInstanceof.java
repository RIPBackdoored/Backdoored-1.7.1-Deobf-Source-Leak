package javassist.expr;

import javassist.compiler.JvstTypeChecker;
import javassist.CtClass;
import javassist.compiler.CompileError;
import javassist.compiler.ast.ASTList;
import javassist.bytecode.Bytecode;
import javassist.compiler.JvstCodeGen;
import javassist.compiler.ProceedHandler;

static class ProceedForInstanceof implements ProceedHandler
{
    int index;
    
    ProceedForInstanceof(final int index) {
        super();
        this.index = index;
    }
    
    @Override
    public void doit(final JvstCodeGen jvstCodeGen, final Bytecode bytecode, final ASTList list) throws CompileError {
        if (jvstCodeGen.getMethodArgsLength(list) != 1) {
            throw new CompileError("$proceed() cannot take more than one parameter for instanceof");
        }
        jvstCodeGen.atMethodArgs(list, new int[1], new int[1], new String[1]);
        bytecode.addOpcode(193);
        bytecode.addIndex(this.index);
        jvstCodeGen.setType(CtClass.booleanType);
    }
    
    @Override
    public void setReturnType(final JvstTypeChecker jvstTypeChecker, final ASTList list) throws CompileError {
        jvstTypeChecker.atMethodArgs(list, new int[1], new int[1], new String[1]);
        jvstTypeChecker.setType(CtClass.booleanType);
    }
}
