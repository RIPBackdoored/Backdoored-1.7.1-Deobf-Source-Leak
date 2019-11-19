package javassist.expr;

import javassist.compiler.JvstTypeChecker;
import javassist.compiler.CompileError;
import javassist.compiler.ast.ASTList;
import javassist.bytecode.Bytecode;
import javassist.compiler.JvstCodeGen;
import javassist.CtClass;
import javassist.compiler.ProceedHandler;

static class ProceedForCast implements ProceedHandler
{
    int index;
    CtClass retType;
    
    ProceedForCast(final int index, final CtClass retType) {
        super();
        this.index = index;
        this.retType = retType;
    }
    
    @Override
    public void doit(final JvstCodeGen jvstCodeGen, final Bytecode bytecode, final ASTList list) throws CompileError {
        if (jvstCodeGen.getMethodArgsLength(list) != 1) {
            throw new CompileError("$proceed() cannot take more than one parameter for cast");
        }
        jvstCodeGen.atMethodArgs(list, new int[1], new int[1], new String[1]);
        bytecode.addOpcode(192);
        bytecode.addIndex(this.index);
        jvstCodeGen.setType(this.retType);
    }
    
    @Override
    public void setReturnType(final JvstTypeChecker jvstTypeChecker, final ASTList list) throws CompileError {
        jvstTypeChecker.atMethodArgs(list, new int[1], new int[1], new String[1]);
        jvstTypeChecker.setType(this.retType);
    }
}
