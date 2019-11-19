package javassist.expr;

import javassist.compiler.JvstTypeChecker;
import javassist.compiler.CompileError;
import javassist.compiler.ast.ASTList;
import javassist.bytecode.Bytecode;
import javassist.compiler.JvstCodeGen;
import javassist.CtClass;
import javassist.compiler.ProceedHandler;

static class ProceedForArray implements ProceedHandler
{
    CtClass arrayType;
    int opcode;
    int index;
    int dimension;
    
    ProceedForArray(final CtClass arrayType, final int opcode, final int index, final int dimension) {
        super();
        this.arrayType = arrayType;
        this.opcode = opcode;
        this.index = index;
        this.dimension = dimension;
    }
    
    @Override
    public void doit(final JvstCodeGen jvstCodeGen, final Bytecode bytecode, final ASTList list) throws CompileError {
        final int methodArgsLength = jvstCodeGen.getMethodArgsLength(list);
        if (methodArgsLength != this.dimension) {
            throw new CompileError("$proceed() with a wrong number of parameters");
        }
        jvstCodeGen.atMethodArgs(list, new int[methodArgsLength], new int[methodArgsLength], new String[methodArgsLength]);
        bytecode.addOpcode(this.opcode);
        if (this.opcode == 189) {
            bytecode.addIndex(this.index);
        }
        else if (this.opcode == 188) {
            bytecode.add(this.index);
        }
        else {
            bytecode.addIndex(this.index);
            bytecode.add(this.dimension);
            bytecode.growStack(1 - this.dimension);
        }
        jvstCodeGen.setType(this.arrayType);
    }
    
    @Override
    public void setReturnType(final JvstTypeChecker jvstTypeChecker, final ASTList list) throws CompileError {
        jvstTypeChecker.setType(this.arrayType);
    }
}
