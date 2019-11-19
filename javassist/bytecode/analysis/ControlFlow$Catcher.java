package javassist.bytecode.analysis;

import javassist.bytecode.stackmap.BasicBlock;

public static class Catcher
{
    private Block node;
    private int typeIndex;
    
    Catcher(final BasicBlock.Catch catch1) {
        super();
        this.node = (Block)catch1.body;
        this.typeIndex = catch1.typeIndex;
    }
    
    public Block block() {
        return this.node;
    }
    
    public String type() {
        if (this.typeIndex == 0) {
            return "java.lang.Throwable";
        }
        return this.node.method.getConstPool().getClassInfo(this.typeIndex);
    }
    
    static /* synthetic */ Block access$100(final Catcher catcher) {
        return catcher.node;
    }
}
