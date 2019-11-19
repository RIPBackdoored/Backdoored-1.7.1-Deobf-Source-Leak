package javassist.bytecode.stackmap;

public static class Maker extends BasicBlock.Maker
{
    public Maker() {
        super();
    }
    
    @Override
    protected BasicBlock makeBlock(final int n) {
        return new TypedBlock(n);
    }
    
    @Override
    protected BasicBlock[] makeArray(final int n) {
        return new TypedBlock[n];
    }
}
