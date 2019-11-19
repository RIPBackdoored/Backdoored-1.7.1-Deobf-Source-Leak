package javassist.bytecode;

import java.io.ByteArrayOutputStream;

public static class Writer
{
    private ByteArrayOutputStream output;
    
    public Writer() {
        super();
        this.output = new ByteArrayOutputStream();
    }
    
    public byte[] toByteArray() {
        return this.output.toByteArray();
    }
    
    public StackMap toStackMap(final ConstPool constPool) {
        return new StackMap(constPool, this.output.toByteArray());
    }
    
    public void writeVerifyTypeInfo(final int n, final int n2) {
        this.output.write(n);
        if (n == 7 || n == 8) {
            this.write16bit(n2);
        }
    }
    
    public void write16bit(final int n) {
        this.output.write(n >>> 8 & 0xFF);
        this.output.write(n & 0xFF);
    }
}
