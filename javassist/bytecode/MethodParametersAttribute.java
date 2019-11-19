package javassist.bytecode;

import java.util.Map;
import java.io.IOException;
import java.io.DataInputStream;

public class MethodParametersAttribute extends AttributeInfo
{
    public static final String tag = "MethodParameters";
    
    MethodParametersAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) throws IOException {
        super(constPool, n, dataInputStream);
    }
    
    public MethodParametersAttribute(final ConstPool constPool, final String[] array, final int[] array2) {
        super(constPool, "MethodParameters");
        final byte[] array3 = new byte[array.length * 4 + 1];
        array3[0] = (byte)array.length;
        for (int i = 0; i < array.length; ++i) {
            ByteArray.write16bit(constPool.addUtf8Info(array[i]), array3, i * 4 + 1);
            ByteArray.write16bit(array2[i], array3, i * 4 + 3);
        }
        this.set(array3);
    }
    
    public int size() {
        return this.info[0] & 0xFF;
    }
    
    public int name(final int n) {
        return ByteArray.readU16bit(this.info, n * 4 + 1);
    }
    
    public int accessFlags(final int n) {
        return ByteArray.readU16bit(this.info, n * 4 + 3);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map map) {
        final int size = this.size();
        final ConstPool constPool2 = this.getConstPool();
        final String[] array = new String[size];
        final int[] array2 = new int[size];
        for (int i = 0; i < size; ++i) {
            array[i] = constPool2.getUtf8Info(this.name(i));
            array2[i] = this.accessFlags(i);
        }
        return new MethodParametersAttribute(constPool, array, array2);
    }
}
