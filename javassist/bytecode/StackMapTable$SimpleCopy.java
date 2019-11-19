package javassist.bytecode;

static class SimpleCopy extends Walker
{
    private Writer writer;
    
    public SimpleCopy(final byte[] array) {
        super(array);
        this.writer = new Writer(array.length);
    }
    
    public byte[] doit() throws BadBytecode {
        this.parse();
        return this.writer.toByteArray();
    }
    
    @Override
    public void sameFrame(final int n, final int n2) {
        this.writer.sameFrame(n2);
    }
    
    @Override
    public void sameLocals(final int n, final int n2, final int n3, final int n4) {
        this.writer.sameLocals(n2, n3, this.copyData(n3, n4));
    }
    
    @Override
    public void chopFrame(final int n, final int n2, final int n3) {
        this.writer.chopFrame(n2, n3);
    }
    
    @Override
    public void appendFrame(final int n, final int n2, final int[] array, final int[] array2) {
        this.writer.appendFrame(n2, array, this.copyData(array, array2));
    }
    
    @Override
    public void fullFrame(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4) {
        this.writer.fullFrame(n2, array, this.copyData(array, array2), array3, this.copyData(array3, array4));
    }
    
    protected int copyData(final int n, final int n2) {
        return n2;
    }
    
    protected int[] copyData(final int[] array, final int[] array2) {
        return array2;
    }
}
