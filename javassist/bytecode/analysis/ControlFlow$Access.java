package javassist.bytecode.analysis;

import javassist.bytecode.stackmap.BasicBlock;

abstract static class Access
{
    Node[] all;
    
    Access(final Node[] all) {
        super();
        this.all = all;
    }
    
    Node node(final BasicBlock basicBlock) {
        return this.all[((Block)basicBlock).index];
    }
    
    abstract BasicBlock[] exits(final Node p0);
    
    abstract BasicBlock[] entrances(final Node p0);
}
