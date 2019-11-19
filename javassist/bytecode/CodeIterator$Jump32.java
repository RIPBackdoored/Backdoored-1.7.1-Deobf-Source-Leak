package javassist.bytecode;

static class Jump32 extends Branch
{
    int offset;
    
    Jump32(final int n, final int offset) {
        super(n);
        this.offset = offset;
    }
    
    @Override
    void shift(final int n, final int n2, final boolean b) {
        this.offset = Branch.shiftOffset(this.pos, this.offset, n, n2, b);
        super.shift(n, n2, b);
    }
    
    @Override
    int write(final int n, final byte[] array, final int n2, final byte[] array2) {
        array2[n2] = array[n];
        ByteArray.write32bit(this.offset, array2, n2 + 1);
        return 5;
    }
}
