package javassist.expr;

import javassist.compiler.JvstTypeChecker;
import javassist.compiler.CompileError;
import javassist.compiler.MemberResolver;
import javassist.compiler.ast.ASTList;
import javassist.bytecode.Bytecode;
import javassist.compiler.JvstCodeGen;
import javassist.CtClass;
import javassist.compiler.ProceedHandler;

static class ProceedForNew implements ProceedHandler
{
    CtClass newType;
    int newIndex;
    int methodIndex;
    
    ProceedForNew(final CtClass newType, final int newIndex, final int methodIndex) {
        super();
        this.newType = newType;
        this.newIndex = newIndex;
        this.methodIndex = methodIndex;
    }
    
    @Override
    public void doit(final JvstCodeGen jvstCodeGen, final Bytecode bytecode, final ASTList list) throws CompileError {
        bytecode.addOpcode(187);
        bytecode.addIndex(this.newIndex);
        bytecode.addOpcode(89);
        jvstCodeGen.atMethodCallCore(this.newType, "<init>", list, false, true, -1, null);
        jvstCodeGen.setType(this.newType);
    }
    
    @Override
    public void setReturnType(final JvstTypeChecker jvstTypeChecker, final ASTList list) throws CompileError {
        jvstTypeChecker.atMethodCallCore(this.newType, "<init>", list);
        jvstTypeChecker.setType(this.newType);
    }
}
