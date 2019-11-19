package javassist.bytecode;

import java.io.IOException;
import java.io.OutputStream;

public static final class FieldWriter
{
    protected ByteStream output;
    protected ConstPoolWriter constPool;
    private int fieldCount;
    
    FieldWriter(final ConstPoolWriter constPool) {
        super();
        this.output = new ByteStream(128);
        this.constPool = constPool;
        this.fieldCount = 0;
    }
    
    public void add(final int n, final String s, final String s2, final AttributeWriter attributeWriter) {
        this.add(n, this.constPool.addUtf8Info(s), this.constPool.addUtf8Info(s2), attributeWriter);
    }
    
    public void add(final int n, final int n2, final int n3, final AttributeWriter attributeWriter) {
        ++this.fieldCount;
        this.output.writeShort(n);
        this.output.writeShort(n2);
        this.output.writeShort(n3);
        ClassFileWriter.writeAttribute(this.output, attributeWriter, 0);
    }
    
    int size() {
        return this.fieldCount;
    }
    
    int dataSize() {
        return this.output.size();
    }
    
    void write(final OutputStream outputStream) throws IOException {
        this.output.writeTo(outputStream);
    }
}
