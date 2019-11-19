package javassist.bytecode;

static class SwitchShifter extends Shifter
{
    SwitchShifter(final StackMapTable stackMapTable, final int n, final int n2) {
        super(stackMapTable, n, n2, false);
    }
    
    @Override
    void update(final int n, final int n2, final int n3, final int n4) {
        final int position = this.position;
        this.position = position + n2 + 0;
        int n5;
        if (this.where == this.position) {
            n5 = n2 - this.gap;
        }
        else {
            if (this.where != position) {
                return;
            }
            n5 = n2 + this.gap;
        }
        if (n2 < 64) {
            if (n5 < 64) {
                this.info[n] = (byte)(n5 + n3);
            }
            else {
                final byte[] insertGap = Shifter.insertGap(this.info, n, 2);
                insertGap[n] = (byte)n4;
                ByteArray.write16bit(n5, insertGap, n + 1);
                this.updatedInfo = insertGap;
            }
        }
        else if (n5 < 64) {
            final byte[] deleteGap = deleteGap(this.info, n, 2);
            deleteGap[n] = (byte)(n5 + n3);
            this.updatedInfo = deleteGap;
        }
        else {
            ByteArray.write16bit(n5, this.info, n + 1);
        }
    }
    
    static byte[] deleteGap(final byte[] array, int n, final int n2) {
        n += n2;
        final int length = array.length;
        final byte[] array2 = new byte[length - n2];
        for (int i = 0; i < length; ++i) {
            array2[i - ((i < n) ? 0 : n2)] = array[i];
        }
        return array2;
    }
    
    @Override
    void update(final int n, final int n2) {
        final int position = this.position;
        this.position = position + n2 + 0;
        int n3;
        if (this.where == this.position) {
            n3 = n2 - this.gap;
        }
        else {
            if (this.where != position) {
                return;
            }
            n3 = n2 + this.gap;
        }
        ByteArray.write16bit(n3, this.info, n + 1);
    }
}
