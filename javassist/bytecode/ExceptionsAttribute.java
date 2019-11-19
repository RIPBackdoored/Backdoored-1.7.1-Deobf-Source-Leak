package javassist.bytecode;

import java.util.Map;
import java.io.IOException;
import java.io.DataInputStream;

public class ExceptionsAttribute extends AttributeInfo
{
    public static final String tag = "Exceptions";
    
    ExceptionsAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) throws IOException {
        super(constPool, n, dataInputStream);
    }
    
    private ExceptionsAttribute(final ConstPool constPool, final ExceptionsAttribute exceptionsAttribute, final Map map) {
        super(constPool, "Exceptions");
        this.copyFrom(exceptionsAttribute, map);
    }
    
    public ExceptionsAttribute(final ConstPool constPool) {
        super(constPool, "Exceptions");
        final byte[] info = new byte[2];
        info[0] = (info[1] = 0);
        this.info = info;
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map map) {
        return new ExceptionsAttribute(constPool, this, map);
    }
    
    private void copyFrom(final ExceptionsAttribute exceptionsAttribute, final Map map) {
        final ConstPool constPool = exceptionsAttribute.constPool;
        final ConstPool constPool2 = this.constPool;
        final byte[] info = exceptionsAttribute.info;
        final int length = info.length;
        final byte[] info2 = new byte[length];
        info2[0] = info[0];
        info2[1] = info[1];
        for (int i = 2; i < length; i += 2) {
            ByteArray.write16bit(constPool.copy(ByteArray.readU16bit(info, i), constPool2, map), info2, i);
        }
        this.info = info2;
    }
    
    public int[] getExceptionIndexes() {
        final byte[] info = this.info;
        final int length = info.length;
        if (length <= 2) {
            return null;
        }
        final int[] array = new int[length / 2 - 1];
        int n = 0;
        for (int i = 2; i < length; i += 2) {
            array[n++] = ((info[i] & 0xFF) << 8 | (info[i + 1] & 0xFF));
        }
        return array;
    }
    
    public String[] getExceptions() {
        final byte[] info = this.info;
        final int length = info.length;
        if (length <= 2) {
            return null;
        }
        final String[] array = new String[length / 2 - 1];
        int n = 0;
        for (int i = 2; i < length; i += 2) {
            array[n++] = this.constPool.getClassInfo((info[i] & 0xFF) << 8 | (info[i + 1] & 0xFF));
        }
        return array;
    }
    
    public void setExceptionIndexes(final int[] array) {
        final int length = array.length;
        final byte[] info = new byte[length * 2 + 2];
        ByteArray.write16bit(length, info, 0);
        for (int i = 0; i < length; ++i) {
            ByteArray.write16bit(array[i], info, i * 2 + 2);
        }
        this.info = info;
    }
    
    public void setExceptions(final String[] array) {
        final int length = array.length;
        final byte[] info = new byte[length * 2 + 2];
        ByteArray.write16bit(length, info, 0);
        for (int i = 0; i < length; ++i) {
            ByteArray.write16bit(this.constPool.addClassInfo(array[i]), info, i * 2 + 2);
        }
        this.info = info;
    }
    
    public int tableLength() {
        return this.info.length / 2 - 1;
    }
    
    public int getException(final int n) {
        final int n2 = n * 2 + 2;
        return (this.info[n2] & 0xFF) << 8 | (this.info[n2 + 1] & 0xFF);
    }
}
