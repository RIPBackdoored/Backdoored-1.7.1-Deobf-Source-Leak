package javassist.bytecode.stackmap;

public static class Catch
{
    public Catch next;
    public BasicBlock body;
    public int typeIndex;
    
    Catch(final BasicBlock body, final int typeIndex, final Catch next) {
        super();
        this.body = body;
        this.typeIndex = typeIndex;
        this.next = next;
    }
}
