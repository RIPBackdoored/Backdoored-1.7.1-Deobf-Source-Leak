package javassist.bytecode;

public static class Walker
{
    byte[] info;
    
    public Walker(final StackMap stackMap) {
        super();
        this.info = stackMap.get();
    }
    
    public void visit() {
        final int u16bit = ByteArray.readU16bit(this.info, 0);
        int stack = 2;
        for (int i = 0; i < u16bit; ++i) {
            final int u16bit2 = ByteArray.readU16bit(this.info, stack);
            final int locals = this.locals(stack + 4, u16bit2, ByteArray.readU16bit(this.info, stack + 2));
            stack = this.stack(locals + 2, u16bit2, ByteArray.readU16bit(this.info, locals));
        }
    }
    
    public int locals(final int n, final int n2, final int n3) {
        return this.typeInfoArray(n, n2, n3, true);
    }
    
    public int stack(final int n, final int n2, final int n3) {
        return this.typeInfoArray(n, n2, n3, false);
    }
    
    public int typeInfoArray(int typeInfoArray2, final int n, final int n2, final boolean b) {
        for (int i = 0; i < n2; ++i) {
            typeInfoArray2 = this.typeInfoArray2(i, typeInfoArray2);
        }
        return typeInfoArray2;
    }
    
    int typeInfoArray2(final int n, int n2) {
        final byte b = this.info[n2];
        if (b == 7) {
            this.objectVariable(n2, ByteArray.readU16bit(this.info, n2 + 1));
            n2 += 3;
        }
        else if (b == 8) {
            this.uninitialized(n2, ByteArray.readU16bit(this.info, n2 + 1));
            n2 += 3;
        }
        else {
            this.typeInfo(n2, b);
            ++n2;
        }
        return n2;
    }
    
    public void typeInfo(final int n, final byte b) {
    }
    
    public void objectVariable(final int n, final int n2) {
    }
    
    public void uninitialized(final int n, final int n2) {
    }
}
