package javassist.bytecode;

import java.util.Map;

static class Copier extends SimpleCopy
{
    private ConstPool srcPool;
    private ConstPool destPool;
    private Map classnames;
    
    public Copier(final ConstPool srcPool, final byte[] array, final ConstPool destPool, final Map classnames) {
        super(array);
        this.srcPool = srcPool;
        this.destPool = destPool;
        this.classnames = classnames;
    }
    
    @Override
    protected int copyData(final int n, final int n2) {
        if (n == 7) {
            return this.srcPool.copy(n2, this.destPool, this.classnames);
        }
        return n2;
    }
    
    @Override
    protected int[] copyData(final int[] array, final int[] array2) {
        final int[] array3 = new int[array2.length];
        for (int i = 0; i < array2.length; ++i) {
            if (array[i] == 7) {
                array3[i] = this.srcPool.copy(array2[i], this.destPool, this.classnames);
            }
            else {
                array3[i] = array2[i];
            }
        }
        return array3;
    }
}
