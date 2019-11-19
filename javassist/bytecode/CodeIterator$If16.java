package javassist.bytecode;

static class If16 extends Branch16
{
    If16(final int n, final int n2) {
        super(n, n2);
    }
    
    @Override
    int deltaSize() {
        return (this.state == 2) ? 5 : 0;
    }
    
    @Override
    void write32(final int n, final byte[] array, final int n2, final byte[] array2) {
        array2[n2] = (byte)this.opcode(array[n] & 0xFF);
        array2[n2 + 1] = 0;
        array2[n2 + 2] = 8;
        array2[n2 + 3] = -56;
        ByteArray.write32bit(this.offset - 3, array2, n2 + 4);
    }
    
    int opcode(final int n) {
        if (n == 198) {
            return 199;
        }
        if (n == 199) {
            return 198;
        }
        if ((n - 153 & 0x1) == 0x0) {
            return n + 1;
        }
        return n - 1;
    }
}
