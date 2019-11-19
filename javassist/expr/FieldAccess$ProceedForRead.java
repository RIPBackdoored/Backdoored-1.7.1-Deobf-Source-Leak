package javassist.expr;

import javassist.compiler.JvstTypeChecker;
import javassist.CtPrimitiveType;
import javassist.compiler.CompileError;
import javassist.compiler.ast.ASTList;
import javassist.bytecode.Bytecode;
import javassist.compiler.JvstCodeGen;
import javassist.CtClass;
import javassist.compiler.ProceedHandler;

static class ProceedForRead implements ProceedHandler
{
    CtClass fieldType;
    int opcode;
    int targetVar;
    int index;
    
    ProceedForRead(final CtClass fieldType, final int opcode, final int index, final int targetVar) {
        super();
        this.fieldType = fieldType;
        this.targetVar = targetVar;
        this.opcode = opcode;
        this.index = index;
    }
    
    @Override
    public void doit(final JvstCodeGen jvstCodeGen, final Bytecode bytecode, final ASTList list) throws CompileError {
        if (list != null && !jvstCodeGen.isParamListName(list)) {
            throw new CompileError("$proceed() cannot take a parameter for field reading");
        }
        int n;
        if (FieldAccess.isStatic(this.opcode)) {
            n = 0;
        }
        else {
            n = -1;
            bytecode.addAload(this.targetVar);
        }
        if (this.fieldType instanceof CtPrimitiveType) {
            n += ((CtPrimitiveType)this.fieldType).getDataSize();
        }
        else {
            ++n;
        }
        bytecode.add(this.opcode);
        bytecode.addIndex(this.index);
        bytecode.growStack(n);
        jvstCodeGen.setType(this.fieldType);
    }
    
    @Override
    public void setReturnType(final JvstTypeChecker jvstTypeChecker, final ASTList list) throws CompileError {
        jvstTypeChecker.setType(this.fieldType);
    }
}
