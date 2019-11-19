package javassist.bytecode;

static class Jump16 extends Branch16
{
    Jump16(final int n, final int n2) {
        super(n, n2);
    }
    
    @Override
    int deltaSize() {
        return (this.state == 2) ? 2 : 0;
    }
    
    @Override
    void write32(final int n, final byte[] array, final int n2, final byte[] array2) {
        array2[n2] = (byte)(((array[n] & 0xFF) == 0xA7) ? 200 : 201);
        ByteArray.write32bit(this.offset, array2, n2 + 1);
    }
}
