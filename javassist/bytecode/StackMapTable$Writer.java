package javassist.bytecode;

import java.io.ByteArrayOutputStream;

public static class Writer
{
    ByteArrayOutputStream output;
    int numOfEntries;
    
    public Writer(final int n) {
        super();
        this.output = new ByteArrayOutputStream(n);
        this.numOfEntries = 0;
        this.output.write(0);
        this.output.write(0);
    }
    
    public byte[] toByteArray() {
        final byte[] byteArray = this.output.toByteArray();
        ByteArray.write16bit(this.numOfEntries, byteArray, 0);
        return byteArray;
    }
    
    public StackMapTable toStackMapTable(final ConstPool constPool) {
        return new StackMapTable(constPool, this.toByteArray());
    }
    
    public void sameFrame(final int n) {
        ++this.numOfEntries;
        if (n < 64) {
            this.output.write(n);
        }
        else {
            this.output.write(251);
            this.write16(n);
        }
    }
    
    public void sameLocals(final int n, final int n2, final int n3) {
        ++this.numOfEntries;
        if (n < 64) {
            this.output.write(n + 64);
        }
        else {
            this.output.write(247);
            this.write16(n);
        }
        this.writeTypeInfo(n2, n3);
    }
    
    public void chopFrame(final int n, final int n2) {
        ++this.numOfEntries;
        this.output.write(251 - n2);
        this.write16(n);
    }
    
    public void appendFrame(final int n, final int[] array, final int[] array2) {
        ++this.numOfEntries;
        final int length = array.length;
        this.output.write(length + 251);
        this.write16(n);
        for (int i = 0; i < length; ++i) {
            this.writeTypeInfo(array[i], array2[i]);
        }
    }
    
    public void fullFrame(final int n, final int[] array, final int[] array2, final int[] array3, final int[] array4) {
        ++this.numOfEntries;
        this.output.write(255);
        this.write16(n);
        final int length = array.length;
        this.write16(length);
        for (int i = 0; i < length; ++i) {
            this.writeTypeInfo(array[i], array2[i]);
        }
        final int length2 = array3.length;
        this.write16(length2);
        for (int j = 0; j < length2; ++j) {
            this.writeTypeInfo(array3[j], array4[j]);
        }
    }
    
    private void writeTypeInfo(final int n, final int n2) {
        this.output.write(n);
        if (n == 7 || n == 8) {
            this.write16(n2);
        }
    }
    
    private void write16(final int n) {
        this.output.write(n >>> 8 & 0xFF);
        this.output.write(n & 0xFF);
    }
}
