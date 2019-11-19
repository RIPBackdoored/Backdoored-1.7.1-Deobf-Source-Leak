package javassist.bytecode;

import java.io.DataOutputStream;
import java.util.Map;
import java.util.Collection;
import java.io.IOException;
import java.io.DataInputStream;
import java.util.ArrayList;

public class ExceptionTable implements Cloneable
{
    private ConstPool constPool;
    private ArrayList entries;
    
    public ExceptionTable(final ConstPool constPool) {
        super();
        this.constPool = constPool;
        this.entries = new ArrayList();
    }
    
    ExceptionTable(final ConstPool constPool, final DataInputStream dataInputStream) throws IOException {
        super();
        this.constPool = constPool;
        final int unsignedShort = dataInputStream.readUnsignedShort();
        final ArrayList entries = new ArrayList<ExceptionTableEntry>(unsignedShort);
        for (int i = 0; i < unsignedShort; ++i) {
            entries.add(new ExceptionTableEntry(dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort()));
        }
        this.entries = entries;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final ExceptionTable exceptionTable = (ExceptionTable)super.clone();
        exceptionTable.entries = new ArrayList(this.entries);
        return exceptionTable;
    }
    
    public int size() {
        return this.entries.size();
    }
    
    public int startPc(final int n) {
        return this.entries.get(n).startPc;
    }
    
    public void setStartPc(final int n, final int startPc) {
        this.entries.get(n).startPc = startPc;
    }
    
    public int endPc(final int n) {
        return this.entries.get(n).endPc;
    }
    
    public void setEndPc(final int n, final int endPc) {
        this.entries.get(n).endPc = endPc;
    }
    
    public int handlerPc(final int n) {
        return this.entries.get(n).handlerPc;
    }
    
    public void setHandlerPc(final int n, final int handlerPc) {
        this.entries.get(n).handlerPc = handlerPc;
    }
    
    public int catchType(final int n) {
        return this.entries.get(n).catchType;
    }
    
    public void setCatchType(final int n, final int catchType) {
        this.entries.get(n).catchType = catchType;
    }
    
    public void add(final int n, final ExceptionTable exceptionTable, final int n2) {
        int size = exceptionTable.size();
        while (--size >= 0) {
            final ExceptionTableEntry exceptionTableEntry = exceptionTable.entries.get(size);
            this.add(n, exceptionTableEntry.startPc + n2, exceptionTableEntry.endPc + n2, exceptionTableEntry.handlerPc + n2, exceptionTableEntry.catchType);
        }
    }
    
    public void add(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n2 < n3) {
            this.entries.add(n, new ExceptionTableEntry(n2, n3, n4, n5));
        }
    }
    
    public void add(final int n, final int n2, final int n3, final int n4) {
        if (n < n2) {
            this.entries.add(new ExceptionTableEntry(n, n2, n3, n4));
        }
    }
    
    public void remove(final int n) {
        this.entries.remove(n);
    }
    
    public ExceptionTable copy(final ConstPool constPool, final Map map) {
        final ExceptionTable exceptionTable = new ExceptionTable(constPool);
        final ConstPool constPool2 = this.constPool;
        for (int size = this.size(), i = 0; i < size; ++i) {
            final ExceptionTableEntry exceptionTableEntry = this.entries.get(i);
            exceptionTable.add(exceptionTableEntry.startPc, exceptionTableEntry.endPc, exceptionTableEntry.handlerPc, constPool2.copy(exceptionTableEntry.catchType, constPool, map));
        }
        return exceptionTable;
    }
    
    void shiftPc(final int n, final int n2, final boolean b) {
        for (int size = this.size(), i = 0; i < size; ++i) {
            final ExceptionTableEntry exceptionTableEntry = this.entries.get(i);
            exceptionTableEntry.startPc = shiftPc(exceptionTableEntry.startPc, n, n2, b);
            exceptionTableEntry.endPc = shiftPc(exceptionTableEntry.endPc, n, n2, b);
            exceptionTableEntry.handlerPc = shiftPc(exceptionTableEntry.handlerPc, n, n2, b);
        }
    }
    
    private static int shiftPc(int n, final int n2, final int n3, final boolean b) {
        if (n > n2 || (b && n == n2)) {
            n += n3;
        }
        return n;
    }
    
    void write(final DataOutputStream dataOutputStream) throws IOException {
        final int size = this.size();
        dataOutputStream.writeShort(size);
        for (int i = 0; i < size; ++i) {
            final ExceptionTableEntry exceptionTableEntry = this.entries.get(i);
            dataOutputStream.writeShort(exceptionTableEntry.startPc);
            dataOutputStream.writeShort(exceptionTableEntry.endPc);
            dataOutputStream.writeShort(exceptionTableEntry.handlerPc);
            dataOutputStream.writeShort(exceptionTableEntry.catchType);
        }
    }
}
