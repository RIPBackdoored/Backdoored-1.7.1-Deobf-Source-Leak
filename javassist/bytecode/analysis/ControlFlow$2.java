package javassist.bytecode.analysis;

import javassist.bytecode.stackmap.BasicBlock;

class ControlFlow$2 extends Access {
    final /* synthetic */ ControlFlow this$0;
    
    ControlFlow$2(final ControlFlow this$0, final Node[] array) {
        this.this$0 = this$0;
        super(array);
    }
    
    @Override
    BasicBlock[] exits(final Node node) {
        return node.block.getExit();
    }
    
    @Override
    BasicBlock[] entrances(final Node node) {
        return node.block.entrances;
    }
}