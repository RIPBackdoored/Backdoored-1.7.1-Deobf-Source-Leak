package javassist.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ClassFileWriter
{
    private ByteStream output;
    private ConstPoolWriter constPool;
    private FieldWriter fields;
    private MethodWriter methods;
    int thisClass;
    int superClass;
    
    public ClassFileWriter(final int n, final int n2) {
        super();
        (this.output = new ByteStream(512)).writeInt(-889275714);
        this.output.writeShort(n2);
        this.output.writeShort(n);
        this.constPool = new ConstPoolWriter(this.output);
        this.fields = new FieldWriter(this.constPool);
        this.methods = new MethodWriter(this.constPool);
    }
    
    public ConstPoolWriter getConstPool() {
        return this.constPool;
    }
    
    public FieldWriter getFieldWriter() {
        return this.fields;
    }
    
    public MethodWriter getMethodWriter() {
        return this.methods;
    }
    
    public byte[] end(final int n, final int n2, final int n3, final int[] array, final AttributeWriter attributeWriter) {
        this.constPool.end();
        this.output.writeShort(n);
        this.output.writeShort(n2);
        this.output.writeShort(n3);
        if (array == null) {
            this.output.writeShort(0);
        }
        else {
            final int length = array.length;
            this.output.writeShort(length);
            for (int i = 0; i < length; ++i) {
                this.output.writeShort(array[i]);
            }
        }
        this.output.enlarge(this.fields.dataSize() + this.methods.dataSize() + 6);
        try {
            this.output.writeShort(this.fields.size());
            this.fields.write(this.output);
            this.output.writeShort(this.methods.numOfMethods());
            this.methods.write(this.output);
        }
        catch (IOException ex) {}
        writeAttribute(this.output, attributeWriter, 0);
        return this.output.toByteArray();
    }
    
    public void end(final DataOutputStream dataOutputStream, final int n, final int n2, final int n3, final int[] array, final AttributeWriter attributeWriter) throws IOException {
        this.constPool.end();
        this.output.writeTo(dataOutputStream);
        dataOutputStream.writeShort(n);
        dataOutputStream.writeShort(n2);
        dataOutputStream.writeShort(n3);
        if (array == null) {
            dataOutputStream.writeShort(0);
        }
        else {
            final int length = array.length;
            dataOutputStream.writeShort(length);
            for (int i = 0; i < length; ++i) {
                dataOutputStream.writeShort(array[i]);
            }
        }
        dataOutputStream.writeShort(this.fields.size());
        this.fields.write(dataOutputStream);
        dataOutputStream.writeShort(this.methods.numOfMethods());
        this.methods.write(dataOutputStream);
        if (attributeWriter == null) {
            dataOutputStream.writeShort(0);
        }
        else {
            dataOutputStream.writeShort(attributeWriter.size());
            attributeWriter.write(dataOutputStream);
        }
    }
    
    static void writeAttribute(final ByteStream byteStream, final AttributeWriter attributeWriter, final int n) {
        if (attributeWriter == null) {
            byteStream.writeShort(n);
            return;
        }
        byteStream.writeShort(attributeWriter.size() + n);
        final DataOutputStream dataOutputStream = new DataOutputStream(byteStream);
        try {
            attributeWriter.write(dataOutputStream);
            dataOutputStream.flush();
        }
        catch (IOException ex) {}
    }
}
