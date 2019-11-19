package javassist.bytecode;

abstract static class Branch
{
    int pos;
    int orgPos;
    
    Branch(final int n) {
        super();
        this.orgPos = n;
        this.pos = n;
    }
    
    void shift(final int n, final int n2, final boolean b) {
        if (n < this.pos || (n == this.pos && b)) {
            this.pos += n2;
        }
    }
    
    static int shiftOffset(final int n, int n2, final int n3, final int n4, final boolean b) {
        final int n5 = n + n2;
        if (n < n3) {
            if (n3 < n5 || (b && n3 == n5)) {
                n2 += n4;
            }
        }
        else if (n == n3) {
            if (n5 < n3 && b) {
                n2 -= n4;
            }
            else if (n3 < n5) {
                n2 += n4;
            }
        }
        else if (n5 < n3 || n3 == n5) {
            n2 -= n4;
        }
        return n2;
    }
    
    boolean expanded() {
        return false;
    }
    
    int gapChanged() {
        return 0;
    }
    
    int deltaSize() {
        return 0;
    }
    
    abstract int write(final int p0, final byte[] p1, final int p2, final byte[] p3) throws BadBytecode;
}
