package javassist.bytecode;

import java.io.PrintWriter;

static class Printer extends Walker
{
    private PrintWriter writer;
    private int offset;
    
    public static void print(final StackMapTable stackMapTable, final PrintWriter printWriter) {
        try {
            new Printer(stackMapTable.get(), printWriter).parse();
        }
        catch (BadBytecode badBytecode) {
            printWriter.println(badBytecode.getMessage());
        }
    }
    
    Printer(final byte[] array, final PrintWriter writer) {
        super(array);
        this.writer = writer;
        this.offset = -1;
    }
    
    @Override
    public void sameFrame(final int n, final int n2) {
        this.offset += n2 + 1;
        this.writer.println(this.offset + " same frame: " + n2);
    }
    
    @Override
    public void sameLocals(final int n, final int n2, final int n3, final int n4) {
        this.offset += n2 + 1;
        this.writer.println(this.offset + " same locals: " + n2);
        this.printTypeInfo(n3, n4);
    }
    
    @Override
    public void chopFrame(final int n, final int n2, final int n3) {
        this.offset += n2 + 1;
        this.writer.println(this.offset + " chop frame: " + n2 + ",    " + n3 + " last locals");
    }
    
    @Override
    public void appendFrame(final int n, final int n2, final int[] array, final int[] array2) {
        this.offset += n2 + 1;
        this.writer.println(this.offset + " append frame: " + n2);
        for (int i = 0; i < array.length; ++i) {
            this.printTypeInfo(array[i], array2[i]);
        }
    }
    
    @Override
    public void fullFrame(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4) {
        this.offset += n2 + 1;
        this.writer.println(this.offset + " full frame: " + n2);
        this.writer.println("[locals]");
        for (int i = 0; i < array.length; ++i) {
            this.printTypeInfo(array[i], array2[i]);
        }
        this.writer.println("[stack]");
        for (int j = 0; j < array3.length; ++j) {
            this.printTypeInfo(array3[j], array4[j]);
        }
    }
    
    private void printTypeInfo(final int n, final int n2) {
        String s = null;
        switch (n) {
            case 0:
                s = "top";
                break;
            case 1:
                s = "integer";
                break;
            case 2:
                s = "float";
                break;
            case 3:
                s = "double";
                break;
            case 4:
                s = "long";
                break;
            case 5:
                s = "null";
                break;
            case 6:
                s = "this";
                break;
            case 7:
                s = "object (cpool_index " + n2 + ")";
                break;
            case 8:
                s = "uninitialized (offset " + n2 + ")";
                break;
        }
        this.writer.print("    ");
        this.writer.println(s);
    }
}
