package javassist.bytecode;

static class SimpleCopy extends Walker
{
    Writer writer;
    
    SimpleCopy(final StackMap stackMap) {
        super(stackMap);
        this.writer = new Writer();
    }
    
    byte[] doit() {
        this.visit();
        return this.writer.toByteArray();
    }
    
    @Override
    public void visit() {
        this.writer.write16bit(ByteArray.readU16bit(this.info, 0));
        super.visit();
    }
    
    @Override
    public int locals(final int n, final int n2, final int n3) {
        this.writer.write16bit(n2);
        return super.locals(n, n2, n3);
    }
    
    @Override
    public int typeInfoArray(final int n, final int n2, final int n3, final boolean b) {
        this.writer.write16bit(n3);
        return super.typeInfoArray(n, n2, n3, b);
    }
    
    @Override
    public void typeInfo(final int n, final byte b) {
        this.writer.writeVerifyTypeInfo(b, 0);
    }
    
    @Override
    public void objectVariable(final int n, final int n2) {
        this.writer.writeVerifyTypeInfo(7, n2);
    }
    
    @Override
    public void uninitialized(final int n, final int n2) {
        this.writer.writeVerifyTypeInfo(8, n2);
    }
}
