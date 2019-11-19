package javassist.bytecode;

import java.io.ByteArrayOutputStream;
import javassist.CannotCompileException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.util.Map;
import java.io.IOException;
import java.io.DataInputStream;

public class StackMapTable extends AttributeInfo
{
    public static final String tag = "StackMapTable";
    public static final int TOP = 0;
    public static final int INTEGER = 1;
    public static final int FLOAT = 2;
    public static final int DOUBLE = 3;
    public static final int LONG = 4;
    public static final int NULL = 5;
    public static final int THIS = 6;
    public static final int OBJECT = 7;
    public static final int UNINIT = 8;
    
    StackMapTable(final ConstPool constPool, final byte[] array) {
        super(constPool, "StackMapTable", array);
    }
    
    StackMapTable(final ConstPool constPool, final int n, final DataInputStream dataInputStream) throws IOException {
        super(constPool, n, dataInputStream);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map map) throws RuntimeCopyException {
        try {
            return new StackMapTable(constPool, new Copier(this.constPool, this.info, constPool, map).doit());
        }
        catch (BadBytecode badBytecode) {
            throw new RuntimeCopyException("bad bytecode. fatal?");
        }
    }
    
    @Override
    void write(final DataOutputStream dataOutputStream) throws IOException {
        super.write(dataOutputStream);
    }
    
    public void insertLocal(final int n, final int n2, final int n3) throws BadBytecode {
        this.set(new InsertLocal(this.get(), n, n2, n3).doit());
    }
    
    public static int typeTagOf(final char c) {
        switch (c) {
            case 'D':
                return 3;
            case 'F':
                return 2;
            case 'J':
                return 4;
            case 'L':
            case '[':
                return 7;
            default:
                return 1;
        }
    }
    
    public void println(final PrintWriter printWriter) {
        Printer.print(this, printWriter);
    }
    
    public void println(final PrintStream printStream) {
        Printer.print(this, new PrintWriter(printStream, true));
    }
    
    void shiftPc(final int n, final int n2, final boolean b) throws BadBytecode {
        new OffsetShifter(this, n, n2).parse();
        new Shifter(this, n, n2, b).doit();
    }
    
    void shiftForSwitch(final int n, final int n2) throws BadBytecode {
        new SwitchShifter(this, n, n2).doit();
    }
    
    public void removeNew(final int n) throws CannotCompileException {
        try {
            this.set(new NewRemover(this.get(), n).doit());
        }
        catch (BadBytecode badBytecode) {
            throw new CannotCompileException("bad stack map table", badBytecode);
        }
    }
}
