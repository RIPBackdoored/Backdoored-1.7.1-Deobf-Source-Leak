package javassist.expr;

static final class LoopContext
{
    NewOp newList;
    int maxLocals;
    int maxStack;
    
    LoopContext(final int maxLocals) {
        super();
        this.maxLocals = maxLocals;
        this.maxStack = 0;
        this.newList = null;
    }
    
    void updateMax(final int maxLocals, final int maxStack) {
        if (this.maxLocals < maxLocals) {
            this.maxLocals = maxLocals;
        }
        if (this.maxStack < maxStack) {
            this.maxStack = maxStack;
        }
    }
}
