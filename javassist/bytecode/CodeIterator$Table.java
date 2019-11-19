package javassist.bytecode;

static class Table extends Switcher
{
    int low;
    int high;
    
    Table(final int n, final int n2, final int low, final int high, final int[] array, final Pointers pointers) {
        super(n, n2, array, pointers);
        this.low = low;
        this.high = high;
    }
    
    @Override
    int write2(int n, final byte[] array) {
        ByteArray.write32bit(this.low, array, n);
        ByteArray.write32bit(this.high, array, n + 4);
        final int length = this.offsets.length;
        n += 8;
        for (int i = 0; i < length; ++i) {
            ByteArray.write32bit(this.offsets[i], array, n);
            n += 4;
        }
        return 8 + 4 * length;
    }
    
    @Override
    int tableSize() {
        return 8 + 4 * this.offsets.length;
    }
}
