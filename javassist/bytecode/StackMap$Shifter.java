package javassist.bytecode;

static class Shifter extends Walker
{
    private int where;
    private int gap;
    private boolean exclusive;
    
    public Shifter(final StackMap stackMap, final int where, final int gap, final boolean exclusive) {
        super(stackMap);
        this.where = where;
        this.gap = gap;
        this.exclusive = exclusive;
    }
    
    @Override
    public int locals(final int n, final int n2, final int n3) {
        if (this.exclusive) {
            if (this.where > n2) {
                return super.locals(n, n2, n3);
            }
        }
        else if (this.where >= n2) {
            return super.locals(n, n2, n3);
        }
        ByteArray.write16bit(n2 + this.gap, this.info, n - 4);
        return super.locals(n, n2, n3);
    }
    
    @Override
    public void uninitialized(final int n, final int n2) {
        if (this.where <= n2) {
            ByteArray.write16bit(n2 + this.gap, this.info, n + 1);
        }
    }
}
