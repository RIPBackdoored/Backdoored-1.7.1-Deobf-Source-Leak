package javassist.bytecode;

static class OffsetShifter extends Walker
{
    int where;
    int gap;
    
    public OffsetShifter(final StackMapTable stackMapTable, final int where, final int gap) {
        super(stackMapTable);
        this.where = where;
        this.gap = gap;
    }
    
    @Override
    public void objectOrUninitialized(final int n, final int n2, final int n3) {
        if (n == 8 && this.where <= n2) {
            ByteArray.write16bit(n2 + this.gap, this.info, n3);
        }
    }
}
