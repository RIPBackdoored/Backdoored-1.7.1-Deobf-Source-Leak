package javassist.bytecode;

static class NewRemover extends SimpleCopy
{
    int posOfNew;
    
    public NewRemover(final byte[] array, final int posOfNew) {
        super(array);
        this.posOfNew = posOfNew;
    }
    
    @Override
    public void sameLocals(final int n, final int n2, final int n3, final int n4) {
        if (n3 == 8 && n4 == this.posOfNew) {
            super.sameFrame(n, n2);
        }
        else {
            super.sameLocals(n, n2, n3, n4);
        }
    }
    
    @Override
    public void fullFrame(final int n, final int n2, final int[] array, final int[] array2, int[] array3, int[] array4) {
        for (int n3 = array3.length - 1, i = 0; i < n3; ++i) {
            if (array3[i] == 8 && array4[i] == this.posOfNew && array3[i + 1] == 8 && array4[i + 1] == this.posOfNew) {
                final int[] array5 = new int[++n3 - 2];
                final int[] array6 = new int[n3 - 2];
                int n4 = 0;
                for (int j = 0; j < n3; ++j) {
                    if (j == i) {
                        ++j;
                    }
                    else {
                        array5[n4] = array3[j];
                        array6[n4++] = array4[j];
                    }
                }
                array3 = array5;
                array4 = array6;
                break;
            }
        }
        super.fullFrame(n, n2, array, array2, array3, array4);
    }
}
