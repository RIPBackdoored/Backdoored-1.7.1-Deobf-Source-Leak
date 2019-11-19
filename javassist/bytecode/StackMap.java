package javassist.bytecode;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import javassist.CannotCompileException;
import java.util.Map;
import java.io.IOException;
import java.io.DataInputStream;

public class StackMap extends AttributeInfo
{
    public static final String tag = "StackMap";
    public static final int TOP = 0;
    public static final int INTEGER = 1;
    public static final int FLOAT = 2;
    public static final int DOUBLE = 3;
    public static final int LONG = 4;
    public static final int NULL = 5;
    public static final int THIS = 6;
    public static final int OBJECT = 7;
    public static final int UNINIT = 8;
    
    StackMap(final ConstPool constPool, final byte[] array) {
        super(constPool, "StackMap", array);
    }
    
    StackMap(final ConstPool constPool, final int n, final DataInputStream dataInputStream) throws IOException {
        super(constPool, n, dataInputStream);
    }
    
    public int numOfEntries() {
        return ByteArray.readU16bit(this.info, 0);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map map) {
        final Copier copier = new Copier(this, constPool, map);
        copier.visit();
        return copier.getStackMap();
    }
    
    public void insertLocal(final int n, final int n2, final int n3) throws BadBytecode {
        this.set(new InsertLocal(this, n, n2, n3).doit());
    }
    
    void shiftPc(final int n, final int n2, final boolean b) throws BadBytecode {
        new Shifter(this, n, n2, b).visit();
    }
    
    void shiftForSwitch(final int n, final int n2) throws BadBytecode {
        new SwitchShifter(this, n, n2).visit();
    }
    
    public void removeNew(final int n) throws CannotCompileException {
        this.set(new NewRemover(this, n).doit());
    }
    
    public void print(final PrintWriter printWriter) {
        new Printer(this, printWriter).print();
    }
}
