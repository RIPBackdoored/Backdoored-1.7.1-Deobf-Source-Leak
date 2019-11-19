package javassist.compiler;

import javassist.compiler.ast.Visitor;
import javassist.compiler.ast.CallExpr;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.Expr;
import javassist.compiler.ast.Member;
import javassist.compiler.ast.Symbol;
import javassist.compiler.ast.ASTList;
import javassist.bytecode.Bytecode;

class Javac$2 implements ProceedHandler {
    final /* synthetic */ String val$c;
    final /* synthetic */ String val$m;
    final /* synthetic */ Javac this$0;
    
    Javac$2(final Javac this$0, final String val$c, final String val$m) {
        this.this$0 = this$0;
        this.val$c = val$c;
        this.val$m = val$m;
        super();
    }
    
    @Override
    public void doit(final JvstCodeGen jvstCodeGen, final Bytecode bytecode, final ASTList list) throws CompileError {
        jvstCodeGen.compileExpr(CallExpr.makeCall(Expr.make(35, new Symbol(this.val$c), new Member(this.val$m)), list));
        jvstCodeGen.addNullIfVoid();
    }
    
    @Override
    public void setReturnType(final JvstTypeChecker jvstTypeChecker, final ASTList list) throws CompileError {
        CallExpr.makeCall(Expr.make(35, new Symbol(this.val$c), new Member(this.val$m)), list).accept(jvstTypeChecker);
        jvstTypeChecker.addNullIfVoid();
    }
}