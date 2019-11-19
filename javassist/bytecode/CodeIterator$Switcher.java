package javassist.bytecode;

abstract static class Switcher extends Branch
{
    int gap;
    int defaultByte;
    int[] offsets;
    Pointers pointers;
    
    Switcher(final int n, final int defaultByte, final int[] offsets, final Pointers pointers) {
        super(n);
        this.gap = 3 - (n & 0x3);
        this.defaultByte = defaultByte;
        this.offsets = offsets;
        this.pointers = pointers;
    }
    
    @Override
    void shift(final int n, final int n2, final boolean b) {
        final int pos = this.pos;
        this.defaultByte = Branch.shiftOffset(pos, this.defaultByte, n, n2, b);
        for (int length = this.offsets.length, i = 0; i < length; ++i) {
            this.offsets[i] = Branch.shiftOffset(pos, this.offsets[i], n, n2, b);
        }
        super.shift(n, n2, b);
    }
    
    @Override
    int gapChanged() {
        final int gap = 3 - (this.pos & 0x3);
        if (gap > this.gap) {
            final int n = gap - this.gap;
            this.gap = gap;
            return n;
        }
        return 0;
    }
    
    @Override
    int deltaSize() {
        return this.gap - (3 - (this.orgPos & 0x3));
    }
    
    @Override
    int write(final int n, final byte[] array, int n2, final byte[] array2) throws BadBytecode {
        int n3 = 3 - (this.pos & 0x3);
        int n4 = this.gap - n3;
        final int n5 = 5 + (3 - (this.orgPos & 0x3)) + this.tableSize();
        if (n4 > 0) {
            this.adjustOffsets(n5, n4);
        }
        array2[n2++] = array[n];
        while (n3-- > 0) {
            array2[n2++] = 0;
        }
        ByteArray.write32bit(this.defaultByte, array2, n2);
        final int write2 = this.write2(n2 + 4, array2);
        n2 += write2 + 4;
        while (n4-- > 0) {
            array2[n2++] = 0;
        }
        return 5 + (3 - (this.orgPos & 0x3)) + write2;
    }
    
    abstract int write2(final int p0, final byte[] p1);
    
    abstract int tableSize();
    
    void adjustOffsets(final int n, final int n2) throws BadBytecode {
        this.pointers.shiftForSwitch(this.pos + n, n2);
        if (this.defaultByte == n) {
            this.defaultByte -= n2;
        }
        for (int i = 0; i < this.offsets.length; ++i) {
            if (this.offsets[i] == n) {
                final int[] offsets = this.offsets;
                final int n3 = i;
                offsets[n3] -= n2;
            }
        }
    }
}
