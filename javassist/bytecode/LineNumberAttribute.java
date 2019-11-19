package javassist.bytecode;

import java.util.Map;
import java.io.IOException;
import java.io.DataInputStream;

public class LineNumberAttribute extends AttributeInfo
{
    public static final String tag = "LineNumberTable";
    
    LineNumberAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) throws IOException {
        super(constPool, n, dataInputStream);
    }
    
    private LineNumberAttribute(final ConstPool constPool, final byte[] array) {
        super(constPool, "LineNumberTable", array);
    }
    
    public int tableLength() {
        return ByteArray.readU16bit(this.info, 0);
    }
    
    public int startPc(final int n) {
        return ByteArray.readU16bit(this.info, n * 4 + 2);
    }
    
    public int lineNumber(final int n) {
        return ByteArray.readU16bit(this.info, n * 4 + 4);
    }
    
    public int toLineNumber(final int n) {
        int tableLength;
        int i;
        for (tableLength = this.tableLength(), i = 0; i < tableLength; ++i) {
            if (n < this.startPc(i)) {
                return this.lineNumber(0);
            }
        }
        return this.lineNumber(i - 1);
    }
    
    public int toStartPc(final int n) {
        for (int tableLength = this.tableLength(), i = 0; i < tableLength; ++i) {
            if (n == this.lineNumber(i)) {
                return this.startPc(i);
            }
        }
        return -1;
    }
    
    public Pc toNearPc(final int n) {
        final int tableLength = this.tableLength();
        int index = 0;
        int n2 = 0;
        if (tableLength > 0) {
            n2 = this.lineNumber(0) - n;
            index = this.startPc(0);
        }
        for (int i = 1; i < tableLength; ++i) {
            final int n3 = this.lineNumber(i) - n;
            if ((n3 < 0 && n3 > n2) || (n3 >= 0 && (n3 < n2 || n2 < 0))) {
                n2 = n3;
                index = this.startPc(i);
            }
        }
        final Pc pc = new Pc();
        pc.index = index;
        pc.line = n + n2;
        return pc;
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map map) {
        final byte[] info = this.info;
        final int length = info.length;
        final byte[] array = new byte[length];
        for (int i = 0; i < length; ++i) {
            array[i] = info[i];
        }
        return new LineNumberAttribute(constPool, array);
    }
    
    void shiftPc(final int n, final int n2, final boolean b) {
        for (int tableLength = this.tableLength(), i = 0; i < tableLength; ++i) {
            final int n3 = i * 4 + 2;
            final int u16bit = ByteArray.readU16bit(this.info, n3);
            if (u16bit > n || (b && u16bit == n)) {
                ByteArray.write16bit(u16bit + n2, this.info, n3);
            }
        }
    }
}
