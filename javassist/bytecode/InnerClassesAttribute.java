package javassist.bytecode;

import java.util.Map;
import java.io.IOException;
import java.io.DataInputStream;

public class InnerClassesAttribute extends AttributeInfo
{
    public static final String tag = "InnerClasses";
    
    InnerClassesAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) throws IOException {
        super(constPool, n, dataInputStream);
    }
    
    private InnerClassesAttribute(final ConstPool constPool, final byte[] array) {
        super(constPool, "InnerClasses", array);
    }
    
    public InnerClassesAttribute(final ConstPool constPool) {
        super(constPool, "InnerClasses", new byte[2]);
        ByteArray.write16bit(0, this.get(), 0);
    }
    
    public int tableLength() {
        return ByteArray.readU16bit(this.get(), 0);
    }
    
    public int innerClassIndex(final int n) {
        return ByteArray.readU16bit(this.get(), n * 8 + 2);
    }
    
    public String innerClass(final int n) {
        this.innerClassIndex(n);
        return null;
    }
    
    public void setInnerClassIndex(final int n, final int n2) {
        ByteArray.write16bit(n2, this.get(), n * 8 + 2);
    }
    
    public int outerClassIndex(final int n) {
        return ByteArray.readU16bit(this.get(), n * 8 + 4);
    }
    
    public String outerClass(final int n) {
        this.outerClassIndex(n);
        return null;
    }
    
    public void setOuterClassIndex(final int n, final int n2) {
        ByteArray.write16bit(n2, this.get(), n * 8 + 4);
    }
    
    public int innerNameIndex(final int n) {
        return ByteArray.readU16bit(this.get(), n * 8 + 6);
    }
    
    public String innerName(final int n) {
        this.innerNameIndex(n);
        return null;
    }
    
    public void setInnerNameIndex(final int n, final int n2) {
        ByteArray.write16bit(n2, this.get(), n * 8 + 6);
    }
    
    public int accessFlags(final int n) {
        return ByteArray.readU16bit(this.get(), n * 8 + 8);
    }
    
    public void setAccessFlags(final int n, final int n2) {
        ByteArray.write16bit(n2, this.get(), n * 8 + 8);
    }
    
    public void append(final String s, final String s2, final String s3, final int n) {
        this.append(this.constPool.addClassInfo(s), this.constPool.addClassInfo(s2), this.constPool.addUtf8Info(s3), n);
    }
    
    public void append(final int n, final int n2, final int n3, final int n4) {
        final byte[] value = this.get();
        final int length = value.length;
        final byte[] array = new byte[length + 8];
        for (int i = 2; i < length; ++i) {
            array[i] = value[i];
        }
        ByteArray.write16bit(ByteArray.readU16bit(value, 0) + 1, array, 0);
        ByteArray.write16bit(n, array, length);
        ByteArray.write16bit(n2, array, length + 2);
        ByteArray.write16bit(n3, array, length + 4);
        ByteArray.write16bit(n4, array, length + 6);
        this.set(array);
    }
    
    public int remove(final int n) {
        final byte[] value = this.get();
        final int length = value.length;
        if (length < 10) {
            return 0;
        }
        final int u16bit = ByteArray.readU16bit(value, 0);
        final int n2 = 2 + n * 8;
        if (u16bit <= n) {
            return u16bit;
        }
        final byte[] array = new byte[length - 8];
        ByteArray.write16bit(u16bit - 1, array, 0);
        int i = 2;
        int n3 = 2;
        while (i < length) {
            if (i == n2) {
                i += 8;
            }
            else {
                array[n3++] = value[i++];
            }
        }
        this.set(array);
        return u16bit - 1;
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map map) {
        final byte[] value = this.get();
        final byte[] array = new byte[value.length];
        final ConstPool constPool2 = this.getConstPool();
        final InnerClassesAttribute innerClassesAttribute = new InnerClassesAttribute(constPool, array);
        final int u16bit = ByteArray.readU16bit(value, 0);
        ByteArray.write16bit(u16bit, array, 0);
        int n = 2;
        for (int i = 0; i < u16bit; ++i) {
            int n2 = ByteArray.readU16bit(value, n);
            int n3 = ByteArray.readU16bit(value, n + 2);
            int n4 = ByteArray.readU16bit(value, n + 4);
            final int u16bit2 = ByteArray.readU16bit(value, n + 6);
            if (n2 != 0) {
                n2 = constPool2.copy(n2, constPool, map);
            }
            ByteArray.write16bit(n2, array, n);
            if (n3 != 0) {
                n3 = constPool2.copy(n3, constPool, map);
            }
            ByteArray.write16bit(n3, array, n + 2);
            if (n4 != 0) {
                n4 = constPool2.copy(n4, constPool, map);
            }
            ByteArray.write16bit(n4, array, n + 4);
            ByteArray.write16bit(u16bit2, array, n + 6);
            n += 8;
        }
        return innerClassesAttribute;
    }
}
