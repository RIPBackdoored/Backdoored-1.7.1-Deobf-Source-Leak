package javassist.expr;

import javassist.compiler.JvstTypeChecker;
import javassist.CtPrimitiveType;
import javassist.compiler.CompileError;
import javassist.compiler.ast.ASTList;
import javassist.bytecode.Bytecode;
import javassist.compiler.JvstCodeGen;
import javassist.CtClass;
import javassist.compiler.ProceedHandler;

static class ProceedForWrite implements ProceedHandler
{
    CtClass fieldType;
    int opcode;
    int targetVar;
    int index;
    
    ProceedForWrite(final CtClass fieldType, final int opcode, final int index, final int targetVar) {
        super();
        this.fieldType = fieldType;
        this.targetVar = targetVar;
        this.opcode = opcode;
        this.index = index;
    }
    
    @Override
    public void doit(final JvstCodeGen jvstCodeGen, final Bytecode bytecode, final ASTList list) throws CompileError {
        if (jvstCodeGen.getMethodArgsLength(list) != 1) {
            throw new CompileError("$proceed() cannot take more than one parameter for field writing");
        }
        int n;
        if (FieldAccess.isStatic(this.opcode)) {
            n = 0;
        }
        else {
            n = -1;
            bytecode.addAload(this.targetVar);
        }
        jvstCodeGen.atMethodArgs(list, new int[1], new int[1], new String[1]);
        jvstCodeGen.doNumCast(this.fieldType);
        if (this.fieldType instanceof CtPrimitiveType) {
            n -= ((CtPrimitiveType)this.fieldType).getDataSize();
        }
        else {
            --n;
        }
        bytecode.add(this.opcode);
        bytecode.addIndex(this.index);
        bytecode.growStack(n);
        jvstCodeGen.setType(CtClass.voidType);
        jvstCodeGen.addNullIfVoid();
    }
    
    @Override
    public void setReturnType(final JvstTypeChecker jvstTypeChecker, final ASTList list) throws CompileError {
        jvstTypeChecker.atMethodArgs(list, new int[1], new int[1], new String[1]);
        jvstTypeChecker.setType(CtClass.voidType);
        jvstTypeChecker.addNullIfVoid();
    }
}
