package javassist.bytecode;

static class Lookup extends Switcher
{
    int[] matches;
    
    Lookup(final int n, final int n2, final int[] matches, final int[] array, final Pointers pointers) {
        super(n, n2, array, pointers);
        this.matches = matches;
    }
    
    @Override
    int write2(int n, final byte[] array) {
        final int length = this.matches.length;
        ByteArray.write32bit(length, array, n);
        n += 4;
        for (int i = 0; i < length; ++i) {
            ByteArray.write32bit(this.matches[i], array, n);
            ByteArray.write32bit(this.offsets[i], array, n + 4);
            n += 8;
        }
        return 4 + 8 * length;
    }
    
    @Override
    int tableSize() {
        return 4 + 8 * this.matches.length;
    }
}
