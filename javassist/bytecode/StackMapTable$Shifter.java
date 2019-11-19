package javassist.bytecode;

static class Shifter extends Walker
{
    private StackMapTable stackMap;
    int where;
    int gap;
    int position;
    byte[] updatedInfo;
    boolean exclusive;
    
    public Shifter(final StackMapTable stackMap, final int where, final int gap, final boolean exclusive) {
        super(stackMap);
        this.stackMap = stackMap;
        this.where = where;
        this.gap = gap;
        this.position = 0;
        this.updatedInfo = null;
        this.exclusive = exclusive;
    }
    
    public void doit() throws BadBytecode {
        this.parse();
        if (this.updatedInfo != null) {
            this.stackMap.set(this.updatedInfo);
        }
    }
    
    @Override
    public void sameFrame(final int n, final int n2) {
        this.update(n, n2, 0, 251);
    }
    
    @Override
    public void sameLocals(final int n, final int n2, final int n3, final int n4) {
        this.update(n, n2, 64, 247);
    }
    
    void update(final int n, final int n2, final int n3, final int n4) {
        final int position = this.position;
        this.position = position + n2 + 0;
        boolean b;
        if (this.exclusive) {
            b = (position < this.where && this.where <= this.position);
        }
        else {
            b = (position <= this.where && this.where < this.position);
        }
        if (b) {
            final int n5 = n2 + this.gap;
            this.position += this.gap;
            if (n5 < 64) {
                this.info[n] = (byte)(n5 + n3);
            }
            else if (n2 < 64) {
                final byte[] insertGap = insertGap(this.info, n, 2);
                insertGap[n] = (byte)n4;
                ByteArray.write16bit(n5, insertGap, n + 1);
                this.updatedInfo = insertGap;
            }
            else {
                ByteArray.write16bit(n5, this.info, n + 1);
            }
        }
    }
    
    static byte[] insertGap(final byte[] array, final int n, final int n2) {
        final int length = array.length;
        final byte[] array2 = new byte[length + n2];
        for (int i = 0; i < length; ++i) {
            array2[i + ((i < n) ? 0 : n2)] = array[i];
        }
        return array2;
    }
    
    @Override
    public void chopFrame(final int n, final int n2, final int n3) {
        this.update(n, n2);
    }
    
    @Override
    public void appendFrame(final int n, final int n2, final int[] array, final int[] array2) {
        this.update(n, n2);
    }
    
    @Override
    public void fullFrame(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4) {
        this.update(n, n2);
    }
    
    void update(final int n, final int n2) {
        final int position = this.position;
        this.position = position + n2 + 0;
        boolean b;
        if (this.exclusive) {
            b = (position < this.where && this.where <= this.position);
        }
        else {
            b = (position <= this.where && this.where < this.position);
        }
        if (b) {
            ByteArray.write16bit(n2 + this.gap, this.info, n + 1);
            this.position += this.gap;
        }
    }
}
