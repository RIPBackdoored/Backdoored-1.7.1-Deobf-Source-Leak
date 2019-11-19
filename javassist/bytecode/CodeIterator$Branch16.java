package javassist.bytecode;

abstract static class Branch16 extends Branch
{
    int offset;
    int state;
    static final int BIT16 = 0;
    static final int EXPAND = 1;
    static final int BIT32 = 2;
    
    Branch16(final int n, final int offset) {
        super(n);
        this.offset = offset;
        this.state = 0;
    }
    
    @Override
    void shift(final int n, final int n2, final boolean b) {
        this.offset = Branch.shiftOffset(this.pos, this.offset, n, n2, b);
        super.shift(n, n2, b);
        if (this.state == 0 && (this.offset < -32768 || 32767 < this.offset)) {
            this.state = 1;
        }
    }
    
    @Override
    boolean expanded() {
        if (this.state == 1) {
            this.state = 2;
            return true;
        }
        return false;
    }
    
    @Override
    abstract int deltaSize();
    
    abstract void write32(final int p0, final byte[] p1, final int p2, final byte[] p3);
    
    @Override
    int write(final int n, final byte[] array, final int n2, final byte[] array2) {
        if (this.state == 2) {
            this.write32(n, array, n2, array2);
        }
        else {
            array2[n2] = array[n];
            ByteArray.write16bit(this.offset, array2, n2 + 1);
        }
        return 3;
    }
}
