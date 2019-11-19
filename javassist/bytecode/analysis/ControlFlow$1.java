package javassist.bytecode.analysis;

import javassist.bytecode.stackmap.BasicBlock;

class ControlFlow$1 extends BasicBlock.Maker {
    final /* synthetic */ ControlFlow this$0;
    
    ControlFlow$1(final ControlFlow this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    protected BasicBlock makeBlock(final int n) {
        return new Block(n, ControlFlow.access$000(this.this$0));
    }
    
    @Override
    protected BasicBlock[] makeArray(final int n) {
        return new Block[n];
    }
}