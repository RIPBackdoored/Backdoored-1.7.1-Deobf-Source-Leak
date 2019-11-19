package javassist.compiler;

import javassist.bytecode.Bytecode;

class CodeGen$1 extends ReturnHook {
    final /* synthetic */ int val$var;
    final /* synthetic */ CodeGen this$0;
    
    CodeGen$1(final CodeGen this$0, final CodeGen codeGen, final int val$var) {
        this.this$0 = this$0;
        this.val$var = val$var;
        super(codeGen);
    }
    
    @Override
    protected boolean doit(final Bytecode bytecode, final int n) {
        bytecode.addAload(this.val$var);
        bytecode.addOpcode(195);
        return false;
    }
}