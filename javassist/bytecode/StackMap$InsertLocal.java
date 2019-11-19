package javassist.bytecode;

static class InsertLocal extends SimpleCopy
{
    private int varIndex;
    private int varTag;
    private int varData;
    
    InsertLocal(final StackMap stackMap, final int varIndex, final int varTag, final int varData) {
        super(stackMap);
        this.varIndex = varIndex;
        this.varTag = varTag;
        this.varData = varData;
    }
    
    @Override
    public int typeInfoArray(int typeInfoArray2, final int n, final int n2, final boolean b) {
        if (!b || n2 < this.varIndex) {
            return super.typeInfoArray(typeInfoArray2, n, n2, b);
        }
        this.writer.write16bit(n2 + 1);
        for (int i = 0; i < n2; ++i) {
            if (i == this.varIndex) {
                this.writeVarTypeInfo();
            }
            typeInfoArray2 = this.typeInfoArray2(i, typeInfoArray2);
        }
        if (n2 == this.varIndex) {
            this.writeVarTypeInfo();
        }
        return typeInfoArray2;
    }
    
    private void writeVarTypeInfo() {
        if (this.varTag == 7) {
            this.writer.writeVerifyTypeInfo(7, this.varData);
        }
        else if (this.varTag == 8) {
            this.writer.writeVerifyTypeInfo(8, this.varData);
        }
        else {
            this.writer.writeVerifyTypeInfo(this.varTag, 0);
        }
    }
}
