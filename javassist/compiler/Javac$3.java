package javassist.compiler;

import javassist.compiler.ast.ASTList;
import javassist.bytecode.Bytecode;
import javassist.compiler.ast.ASTree;

class Javac$3 implements ProceedHandler {
    final /* synthetic */ ASTree val$texpr;
    final /* synthetic */ int val$methodIndex;
    final /* synthetic */ String val$descriptor;
    final /* synthetic */ String val$classname;
    final /* synthetic */ String val$methodname;
    final /* synthetic */ Javac this$0;
    
    Javac$3(final Javac this$0, final ASTree val$texpr, final int val$methodIndex, final String val$descriptor, final String val$classname, final String val$methodname) {
        this.this$0 = this$0;
        this.val$texpr = val$texpr;
        this.val$methodIndex = val$methodIndex;
        this.val$descriptor = val$descriptor;
        this.val$classname = val$classname;
        this.val$methodname = val$methodname;
        super();
    }
    
    @Override
    public void doit(final JvstCodeGen jvstCodeGen, final Bytecode bytecode, final ASTList list) throws CompileError {
        jvstCodeGen.compileInvokeSpecial(this.val$texpr, this.val$methodIndex, this.val$descriptor, list);
    }
    
    @Override
    public void setReturnType(final JvstTypeChecker jvstTypeChecker, final ASTList list) throws CompileError {
        jvstTypeChecker.compileInvokeSpecial(this.val$texpr, this.val$classname, this.val$methodname, this.val$descriptor, list);
    }
}