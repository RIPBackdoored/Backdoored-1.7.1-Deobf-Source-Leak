package javassist.bytecode;

static class InsertLocal extends SimpleCopy
{
    private int varIndex;
    private int varTag;
    private int varData;
    
    public InsertLocal(final byte[] array, final int varIndex, final int varTag, final int varData) {
        super(array);
        this.varIndex = varIndex;
        this.varTag = varTag;
        this.varData = varData;
    }
    
    @Override
    public void fullFrame(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4) {
        final int length = array.length;
        if (length < this.varIndex) {
            super.fullFrame(n, n2, array, array2, array3, array4);
            return;
        }
        final int n3 = (this.varTag == 4 || this.varTag == 3) ? 2 : 1;
        final int[] array5 = new int[length + n3];
        final int[] array6 = new int[length + n3];
        final int varIndex = this.varIndex;
        int n4 = 0;
        for (int i = 0; i < length; ++i) {
            if (n4 == varIndex) {
                n4 += n3;
            }
            array5[n4] = array[i];
            array6[n4++] = array2[i];
        }
        array5[varIndex] = this.varTag;
        array6[varIndex] = this.varData;
        if (n3 > 1) {
            array6[varIndex + 1] = (array5[varIndex + 1] = 0);
        }
        super.fullFrame(n, n2, array5, array6, array3, array4);
    }
}
