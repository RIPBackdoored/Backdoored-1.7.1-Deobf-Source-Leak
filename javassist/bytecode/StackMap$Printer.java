package javassist.bytecode;

import java.io.PrintWriter;

static class Printer extends Walker
{
    private PrintWriter writer;
    
    public Printer(final StackMap stackMap, final PrintWriter writer) {
        super(stackMap);
        this.writer = writer;
    }
    
    public void print() {
        this.writer.println(ByteArray.readU16bit(this.info, 0) + " entries");
        this.visit();
    }
    
    @Override
    public int locals(final int n, final int n2, final int n3) {
        this.writer.println("  * offset " + n2);
        return super.locals(n, n2, n3);
    }
}
