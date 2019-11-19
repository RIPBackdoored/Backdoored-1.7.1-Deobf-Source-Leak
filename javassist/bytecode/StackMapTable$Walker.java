package javassist.bytecode;

public static class Walker
{
    byte[] info;
    int numOfEntries;
    
    public Walker(final StackMapTable stackMapTable) {
        this(stackMapTable.get());
    }
    
    public Walker(final byte[] info) {
        super();
        this.info = info;
        this.numOfEntries = ByteArray.readU16bit(info, 0);
    }
    
    public final int size() {
        return this.numOfEntries;
    }
    
    public void parse() throws BadBytecode {
        final int numOfEntries = this.numOfEntries;
        int stackMapFrames = 2;
        for (int i = 0; i < numOfEntries; ++i) {
            stackMapFrames = this.stackMapFrames(stackMapFrames, i);
        }
    }
    
    int stackMapFrames(int n, final int n2) throws BadBytecode {
        final int n3 = this.info[n] & 0xFF;
        if (n3 < 64) {
            this.sameFrame(n, n3);
            ++n;
        }
        else if (n3 < 128) {
            n = this.sameLocals(n, n3);
        }
        else {
            if (n3 < 247) {
                throw new BadBytecode("bad frame_type in StackMapTable");
            }
            if (n3 == 247) {
                n = this.sameLocals(n, n3);
            }
            else if (n3 < 251) {
                this.chopFrame(n, ByteArray.readU16bit(this.info, n + 1), 251 - n3);
                n += 3;
            }
            else if (n3 == 251) {
                this.sameFrame(n, ByteArray.readU16bit(this.info, n + 1));
                n += 3;
            }
            else if (n3 < 255) {
                n = this.appendFrame(n, n3);
            }
            else {
                n = this.fullFrame(n);
            }
        }
        return n;
    }
    
    public void sameFrame(final int n, final int n2) throws BadBytecode {
    }
    
    private int sameLocals(int n, final int n2) throws BadBytecode {
        final int n3 = n;
        int u16bit;
        if (n2 < 128) {
            u16bit = n2 - 64;
        }
        else {
            u16bit = ByteArray.readU16bit(this.info, n + 1);
            n += 2;
        }
        final int n4 = this.info[n + 1] & 0xFF;
        int u16bit2 = 0;
        if (n4 == 7 || n4 == 8) {
            u16bit2 = ByteArray.readU16bit(this.info, n + 2);
            this.objectOrUninitialized(n4, u16bit2, n + 2);
            n += 2;
        }
        this.sameLocals(n3, u16bit, n4, u16bit2);
        return n + 2;
    }
    
    public void sameLocals(final int n, final int n2, final int n3, final int n4) throws BadBytecode {
    }
    
    public void chopFrame(final int n, final int n2, final int n3) throws BadBytecode {
    }
    
    private int appendFrame(final int n, final int n2) throws BadBytecode {
        final int n3 = n2 - 251;
        final int u16bit = ByteArray.readU16bit(this.info, n + 1);
        final int[] array = new int[n3];
        final int[] array2 = new int[n3];
        int n4 = n + 3;
        for (int i = 0; i < n3; ++i) {
            final int n5 = this.info[n4] & 0xFF;
            array[i] = n5;
            if (n5 == 7 || n5 == 8) {
                this.objectOrUninitialized(n5, array2[i] = ByteArray.readU16bit(this.info, n4 + 1), n4 + 1);
                n4 += 3;
            }
            else {
                array2[i] = 0;
                ++n4;
            }
        }
        this.appendFrame(n, u16bit, array, array2);
        return n4;
    }
    
    public void appendFrame(final int n, final int n2, final int[] array, final int[] array2) throws BadBytecode {
    }
    
    private int fullFrame(final int n) throws BadBytecode {
        final int u16bit = ByteArray.readU16bit(this.info, n + 1);
        final int u16bit2 = ByteArray.readU16bit(this.info, n + 3);
        final int[] array = new int[u16bit2];
        final int[] array2 = new int[u16bit2];
        final int verifyTypeInfo = this.verifyTypeInfo(n + 5, u16bit2, array, array2);
        final int u16bit3 = ByteArray.readU16bit(this.info, verifyTypeInfo);
        final int[] array3 = new int[u16bit3];
        final int[] array4 = new int[u16bit3];
        final int verifyTypeInfo2 = this.verifyTypeInfo(verifyTypeInfo + 2, u16bit3, array3, array4);
        this.fullFrame(n, u16bit, array, array2, array3, array4);
        return verifyTypeInfo2;
    }
    
    public void fullFrame(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4) throws BadBytecode {
    }
    
    private int verifyTypeInfo(int n, final int n2, final int[] array, final int[] array2) {
        for (int i = 0; i < n2; ++i) {
            final int n3 = this.info[n++] & 0xFF;
            array[i] = n3;
            if (n3 == 7 || n3 == 8) {
                this.objectOrUninitialized(n3, array2[i] = ByteArray.readU16bit(this.info, n), n);
                n += 2;
            }
        }
        return n;
    }
    
    public void objectOrUninitialized(final int n, final int n2, final int n3) {
    }
}
