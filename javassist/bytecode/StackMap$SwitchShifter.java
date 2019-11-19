package javassist.bytecode;

static class SwitchShifter extends Walker
{
    private int where;
    private int gap;
    
    public SwitchShifter(final StackMap stackMap, final int where, final int gap) {
        super(stackMap);
        this.where = where;
        this.gap = gap;
    }
    
    @Override
    public int locals(final int n, final int n2, final int n3) {
        if (this.where == n + n2) {
            ByteArray.write16bit(n2 - this.gap, this.info, n - 4);
        }
        else if (this.where == n) {
            ByteArray.write16bit(n2 + this.gap, this.info, n - 4);
        }
        return super.locals(n, n2, n3);
    }
}
