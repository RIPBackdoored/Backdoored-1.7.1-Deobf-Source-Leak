package javassist.bytecode;

static class NewRemover extends SimpleCopy
{
    int posOfNew;
    
    NewRemover(final StackMap stackMap, final int posOfNew) {
        super(stackMap);
        this.posOfNew = posOfNew;
    }
    
    @Override
    public int stack(final int n, final int n2, final int n3) {
        return this.stackTypeInfoArray(n, n2, n3);
    }
    
    private int stackTypeInfoArray(int n, final int n2, final int n3) {
        int n4 = n;
        int n5 = 0;
        for (int i = 0; i < n3; ++i) {
            final byte b = this.info[n4];
            if (b == 7) {
                n4 += 3;
            }
            else if (b == 8) {
                if (ByteArray.readU16bit(this.info, n4 + 1) == this.posOfNew) {
                    ++n5;
                }
                n4 += 3;
            }
            else {
                ++n4;
            }
        }
        this.writer.write16bit(n3 - n5);
        for (int j = 0; j < n3; ++j) {
            final byte b2 = this.info[n];
            if (b2 == 7) {
                this.objectVariable(n, ByteArray.readU16bit(this.info, n + 1));
                n += 3;
            }
            else if (b2 == 8) {
                final int u16bit = ByteArray.readU16bit(this.info, n + 1);
                if (u16bit != this.posOfNew) {
                    this.uninitialized(n, u16bit);
                }
                n += 3;
            }
            else {
                this.typeInfo(n, b2);
                ++n;
            }
        }
        return n;
    }
}
