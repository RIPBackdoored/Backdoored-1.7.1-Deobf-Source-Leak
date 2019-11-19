package javassist.bytecode;

static class SubWalker
{
    byte[] info;
    
    SubWalker(final byte[] info) {
        super();
        this.info = info;
    }
    
    final int targetInfo(final int n, final int n2) throws Exception {
        switch (n2) {
            case 0:
            case 1:
                this.typeParameterTarget(n, n2, this.info[n] & 0xFF);
                return n + 1;
            case 16:
                this.supertypeTarget(n, ByteArray.readU16bit(this.info, n));
                return n + 2;
            case 17:
            case 18:
                this.typeParameterBoundTarget(n, n2, this.info[n] & 0xFF, this.info[n + 1] & 0xFF);
                return n + 2;
            case 19:
            case 20:
            case 21:
                this.emptyTarget(n, n2);
                return n;
            case 22:
                this.formalParameterTarget(n, this.info[n] & 0xFF);
                return n + 1;
            case 23:
                this.throwsTarget(n, ByteArray.readU16bit(this.info, n));
                return n + 2;
            case 64:
            case 65:
                return this.localvarTarget(n + 2, n2, ByteArray.readU16bit(this.info, n));
            case 66:
                this.catchTarget(n, ByteArray.readU16bit(this.info, n));
                return n + 2;
            case 67:
            case 68:
            case 69:
            case 70:
                this.offsetTarget(n, n2, ByteArray.readU16bit(this.info, n));
                return n + 2;
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
                this.typeArgumentTarget(n, n2, ByteArray.readU16bit(this.info, n), this.info[n + 2] & 0xFF);
                return n + 3;
            default:
                throw new RuntimeException("invalid target type: " + n2);
        }
    }
    
    void typeParameterTarget(final int n, final int n2, final int n3) throws Exception {
    }
    
    void supertypeTarget(final int n, final int n2) throws Exception {
    }
    
    void typeParameterBoundTarget(final int n, final int n2, final int n3, final int n4) throws Exception {
    }
    
    void emptyTarget(final int n, final int n2) throws Exception {
    }
    
    void formalParameterTarget(final int n, final int n2) throws Exception {
    }
    
    void throwsTarget(final int n, final int n2) throws Exception {
    }
    
    int localvarTarget(int n, final int n2, final int n3) throws Exception {
        for (int i = 0; i < n3; ++i) {
            this.localvarTarget(n, n2, ByteArray.readU16bit(this.info, n), ByteArray.readU16bit(this.info, n + 2), ByteArray.readU16bit(this.info, n + 4));
            n += 6;
        }
        return n;
    }
    
    void localvarTarget(final int n, final int n2, final int n3, final int n4, final int n5) throws Exception {
    }
    
    void catchTarget(final int n, final int n2) throws Exception {
    }
    
    void offsetTarget(final int n, final int n2, final int n3) throws Exception {
    }
    
    void typeArgumentTarget(final int n, final int n2, final int n3, final int n4) throws Exception {
    }
    
    final int typePath(int n) throws Exception {
        return this.typePath(n, this.info[n++] & 0xFF);
    }
    
    int typePath(int n, final int n2) throws Exception {
        for (int i = 0; i < n2; ++i) {
            this.typePath(n, this.info[n] & 0xFF, this.info[n + 1] & 0xFF);
            n += 2;
        }
        return n;
    }
    
    void typePath(final int n, final int n2, final int n3) throws Exception {
    }
}
