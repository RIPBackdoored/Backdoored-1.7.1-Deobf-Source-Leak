package javassist.compiler;

import javassist.compiler.ast.Visitor;
import javassist.compiler.ast.CallExpr;
import javassist.compiler.ast.Expr;
import javassist.compiler.ast.Member;
import javassist.compiler.ast.ASTList;
import javassist.bytecode.Bytecode;
import javassist.compiler.ast.ASTree;

class Javac$1 implements ProceedHandler {
    final /* synthetic */ String val$m;
    final /* synthetic */ ASTree val$texpr;
    final /* synthetic */ Javac this$0;
    
    Javac$1(final Javac this$0, final String val$m, final ASTree val$texpr) {
        this.this$0 = this$0;
        this.val$m = val$m;
        this.val$texpr = val$texpr;
        super();
    }
    
    @Override
    public void doit(final JvstCodeGen jvstCodeGen, final Bytecode bytecode, final ASTList list) throws CompileError {
        ASTree make = new Member(this.val$m);
        if (this.val$texpr != null) {
            make = Expr.make(46, this.val$texpr, make);
        }
        jvstCodeGen.compileExpr(CallExpr.makeCall(make, list));
        jvstCodeGen.addNullIfVoid();
    }
    
    @Override
    public void setReturnType(final JvstTypeChecker jvstTypeChecker, final ASTList list) throws CompileError {
        ASTree make = new Member(this.val$m);
        if (this.val$texpr != null) {
            make = Expr.make(46, this.val$texpr, make);
        }
        CallExpr.makeCall(make, list).accept(jvstTypeChecker);
        jvstTypeChecker.addNullIfVoid();
    }
}