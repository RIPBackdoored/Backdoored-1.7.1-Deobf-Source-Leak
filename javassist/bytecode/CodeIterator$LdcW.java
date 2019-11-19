package javassist.bytecode;

static class LdcW extends Branch
{
    int index;
    boolean state;
    
    LdcW(final int n, final int index) {
        super(n);
        this.index = index;
        this.state = true;
    }
    
    @Override
    boolean expanded() {
        if (this.state) {
            this.state = false;
            return true;
        }
        return false;
    }
    
    @Override
    int deltaSize() {
        return 1;
    }
    
    @Override
    int write(final int n, final byte[] array, final int n2, final byte[] array2) {
        array2[n2] = 19;
        ByteArray.write16bit(this.index, array2, n2 + 1);
        return 2;
    }
}
