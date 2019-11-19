package javassist.bytecode;

import java.io.IOException;
import java.io.OutputStream;

public static final class MethodWriter
{
    protected ByteStream output;
    protected ConstPoolWriter constPool;
    private int methodCount;
    protected int codeIndex;
    protected int throwsIndex;
    protected int stackIndex;
    private int startPos;
    private boolean isAbstract;
    private int catchPos;
    private int catchCount;
    
    MethodWriter(final ConstPoolWriter constPool) {
        super();
        this.output = new ByteStream(256);
        this.constPool = constPool;
        this.methodCount = 0;
        this.codeIndex = 0;
        this.throwsIndex = 0;
        this.stackIndex = 0;
    }
    
    public void begin(final int n, final String s, final String s2, final String[] array, final AttributeWriter attributeWriter) {
        final int addUtf8Info = this.constPool.addUtf8Info(s);
        final int addUtf8Info2 = this.constPool.addUtf8Info(s2);
        int[] addClassInfo;
        if (array == null) {
            addClassInfo = null;
        }
        else {
            addClassInfo = this.constPool.addClassInfo(array);
        }
        this.begin(n, addUtf8Info, addUtf8Info2, addClassInfo, attributeWriter);
    }
    
    public void begin(final int n, final int n2, final int n3, final int[] array, final AttributeWriter attributeWriter) {
        ++this.methodCount;
        this.output.writeShort(n);
        this.output.writeShort(n2);
        this.output.writeShort(n3);
        this.isAbstract = ((n & 0x400) != 0x0);
        int n4 = this.isAbstract ? 0 : 1;
        if (array != null) {
            ++n4;
        }
        ClassFileWriter.writeAttribute(this.output, attributeWriter, n4);
        if (array != null) {
            this.writeThrows(array);
        }
        if (!this.isAbstract) {
            if (this.codeIndex == 0) {
                this.codeIndex = this.constPool.addUtf8Info("Code");
            }
            this.startPos = this.output.getPos();
            this.output.writeShort(this.codeIndex);
            this.output.writeBlank(12);
        }
        this.catchPos = -1;
        this.catchCount = 0;
    }
    
    private void writeThrows(final int[] array) {
        if (this.throwsIndex == 0) {
            this.throwsIndex = this.constPool.addUtf8Info("Exceptions");
        }
        this.output.writeShort(this.throwsIndex);
        this.output.writeInt(array.length * 2 + 2);
        this.output.writeShort(array.length);
        for (int i = 0; i < array.length; ++i) {
            this.output.writeShort(array[i]);
        }
    }
    
    public void add(final int n) {
        this.output.write(n);
    }
    
    public void add16(final int n) {
        this.output.writeShort(n);
    }
    
    public void add32(final int n) {
        this.output.writeInt(n);
    }
    
    public void addInvoke(final int n, final String s, final String s2, final String s3) {
        final int addMethodrefInfo = this.constPool.addMethodrefInfo(this.constPool.addClassInfo(s), this.constPool.addNameAndTypeInfo(s2, s3));
        this.add(n);
        this.add16(addMethodrefInfo);
    }
    
    public void codeEnd(final int n, final int n2) {
        if (!this.isAbstract) {
            this.output.writeShort(this.startPos + 6, n);
            this.output.writeShort(this.startPos + 8, n2);
            this.output.writeInt(this.startPos + 10, this.output.getPos() - this.startPos - 14);
            this.catchPos = this.output.getPos();
            this.catchCount = 0;
            this.output.writeShort(0);
        }
    }
    
    public void addCatch(final int n, final int n2, final int n3, final int n4) {
        ++this.catchCount;
        this.output.writeShort(n);
        this.output.writeShort(n2);
        this.output.writeShort(n3);
        this.output.writeShort(n4);
    }
    
    public void end(final StackMapTable.Writer writer, final AttributeWriter attributeWriter) {
        if (this.isAbstract) {
            return;
        }
        this.output.writeShort(this.catchPos, this.catchCount);
        ClassFileWriter.writeAttribute(this.output, attributeWriter, (writer != null) ? 1 : 0);
        if (writer != null) {
            if (this.stackIndex == 0) {
                this.stackIndex = this.constPool.addUtf8Info("StackMapTable");
            }
            this.output.writeShort(this.stackIndex);
            final byte[] byteArray = writer.toByteArray();
            this.output.writeInt(byteArray.length);
            this.output.write(byteArray);
        }
        this.output.writeInt(this.startPos + 2, this.output.getPos() - this.startPos - 6);
    }
    
    public int size() {
        return this.output.getPos() - this.startPos - 14;
    }
    
    int numOfMethods() {
        return this.methodCount;
    }
    
    int dataSize() {
        return this.output.size();
    }
    
    void write(final OutputStream outputStream) throws IOException {
        this.output.writeTo(outputStream);
    }
}
