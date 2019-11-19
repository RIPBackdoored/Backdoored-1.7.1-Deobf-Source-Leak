package javassist.bytecode;

import java.util.Map;
import java.io.IOException;
import java.io.DataInputStream;

public class LocalVariableAttribute extends AttributeInfo
{
    public static final String tag = "LocalVariableTable";
    public static final String typeTag = "LocalVariableTypeTable";
    
    public LocalVariableAttribute(final ConstPool constPool) {
        super(constPool, "LocalVariableTable", new byte[2]);
        ByteArray.write16bit(0, this.info, 0);
    }
    
    @Deprecated
    public LocalVariableAttribute(final ConstPool constPool, final String s) {
        super(constPool, s, new byte[2]);
        ByteArray.write16bit(0, this.info, 0);
    }
    
    LocalVariableAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) throws IOException {
        super(constPool, n, dataInputStream);
    }
    
    LocalVariableAttribute(final ConstPool constPool, final String s, final byte[] array) {
        super(constPool, s, array);
    }
    
    public void addEntry(final int n, final int n2, final int n3, final int n4, final int n5) {
        final int length = this.info.length;
        final byte[] info = new byte[length + 10];
        ByteArray.write16bit(this.tableLength() + 1, info, 0);
        for (int i = 2; i < length; ++i) {
            info[i] = this.info[i];
        }
        ByteArray.write16bit(n, info, length);
        ByteArray.write16bit(n2, info, length + 2);
        ByteArray.write16bit(n3, info, length + 4);
        ByteArray.write16bit(n4, info, length + 6);
        ByteArray.write16bit(n5, info, length + 8);
        this.info = info;
    }
    
    @Override
    void renameClass(final String s, final String s2) {
        final ConstPool constPool = this.getConstPool();
        for (int tableLength = this.tableLength(), i = 0; i < tableLength; ++i) {
            final int n = i * 10 + 2;
            final int u16bit = ByteArray.readU16bit(this.info, n + 6);
            if (u16bit != 0) {
                ByteArray.write16bit(constPool.addUtf8Info(this.renameEntry(constPool.getUtf8Info(u16bit), s, s2)), this.info, n + 6);
            }
        }
    }
    
    String renameEntry(final String s, final String s2, final String s3) {
        return Descriptor.rename(s, s2, s3);
    }
    
    @Override
    void renameClass(final Map map) {
        final ConstPool constPool = this.getConstPool();
        for (int tableLength = this.tableLength(), i = 0; i < tableLength; ++i) {
            final int n = i * 10 + 2;
            final int u16bit = ByteArray.readU16bit(this.info, n + 6);
            if (u16bit != 0) {
                ByteArray.write16bit(constPool.addUtf8Info(this.renameEntry(constPool.getUtf8Info(u16bit), map)), this.info, n + 6);
            }
        }
    }
    
    String renameEntry(final String s, final Map map) {
        return Descriptor.rename(s, map);
    }
    
    public void shiftIndex(final int n, final int n2) {
        for (int length = this.info.length, i = 2; i < length; i += 10) {
            final int u16bit = ByteArray.readU16bit(this.info, i + 8);
            if (u16bit >= n) {
                ByteArray.write16bit(u16bit + n2, this.info, i + 8);
            }
        }
    }
    
    public int tableLength() {
        return ByteArray.readU16bit(this.info, 0);
    }
    
    public int startPc(final int n) {
        return ByteArray.readU16bit(this.info, n * 10 + 2);
    }
    
    public int codeLength(final int n) {
        return ByteArray.readU16bit(this.info, n * 10 + 4);
    }
    
    void shiftPc(final int n, final int n2, final boolean b) {
        for (int tableLength = this.tableLength(), i = 0; i < tableLength; ++i) {
            final int n3 = i * 10 + 2;
            final int u16bit = ByteArray.readU16bit(this.info, n3);
            final int u16bit2 = ByteArray.readU16bit(this.info, n3 + 2);
            if (u16bit > n || (b && u16bit == n && u16bit != 0)) {
                ByteArray.write16bit(u16bit + n2, this.info, n3);
            }
            else if (u16bit + u16bit2 > n || (b && u16bit + u16bit2 == n)) {
                ByteArray.write16bit(u16bit2 + n2, this.info, n3 + 2);
            }
        }
    }
    
    public int nameIndex(final int n) {
        return ByteArray.readU16bit(this.info, n * 10 + 6);
    }
    
    public String variableName(final int n) {
        return this.getConstPool().getUtf8Info(this.nameIndex(n));
    }
    
    public int descriptorIndex(final int n) {
        return ByteArray.readU16bit(this.info, n * 10 + 8);
    }
    
    public int signatureIndex(final int n) {
        return this.descriptorIndex(n);
    }
    
    public String descriptor(final int n) {
        return this.getConstPool().getUtf8Info(this.descriptorIndex(n));
    }
    
    public String signature(final int n) {
        return this.descriptor(n);
    }
    
    public int index(final int n) {
        return ByteArray.readU16bit(this.info, n * 10 + 10);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map map) {
        final byte[] value = this.get();
        final byte[] array = new byte[value.length];
        final ConstPool constPool2 = this.getConstPool();
        final LocalVariableAttribute thisAttr = this.makeThisAttr(constPool, array);
        final int u16bit = ByteArray.readU16bit(value, 0);
        ByteArray.write16bit(u16bit, array, 0);
        int n = 2;
        for (int i = 0; i < u16bit; ++i) {
            final int u16bit2 = ByteArray.readU16bit(value, n);
            final int u16bit3 = ByteArray.readU16bit(value, n + 2);
            int n2 = ByteArray.readU16bit(value, n + 4);
            int n3 = ByteArray.readU16bit(value, n + 6);
            final int u16bit4 = ByteArray.readU16bit(value, n + 8);
            ByteArray.write16bit(u16bit2, array, n);
            ByteArray.write16bit(u16bit3, array, n + 2);
            if (n2 != 0) {
                n2 = constPool2.copy(n2, constPool, null);
            }
            ByteArray.write16bit(n2, array, n + 4);
            if (n3 != 0) {
                n3 = constPool.addUtf8Info(Descriptor.rename(constPool2.getUtf8Info(n3), map));
            }
            ByteArray.write16bit(n3, array, n + 6);
            ByteArray.write16bit(u16bit4, array, n + 8);
            n += 10;
        }
        return thisAttr;
    }
    
    LocalVariableAttribute makeThisAttr(final ConstPool constPool, final byte[] array) {
        return new LocalVariableAttribute(constPool, "LocalVariableTable", array);
    }
}
