package javassist.bytecode.stackmap;

static class Mark implements Comparable
{
    int position;
    BasicBlock block;
    BasicBlock[] jump;
    boolean alwaysJmp;
    int size;
    Catch catcher;
    
    Mark(final int position) {
        super();
        this.position = position;
        this.block = null;
        this.jump = null;
        this.alwaysJmp = false;
        this.size = 0;
        this.catcher = null;
    }
    
    @Override
    public int compareTo(final Object o) {
        if (o instanceof Mark) {
            return this.position - ((Mark)o).position;
        }
        return -1;
    }
    
    void setJump(final BasicBlock[] jump, final int size, final boolean alwaysJmp) {
        this.jump = jump;
        this.size = size;
        this.alwaysJmp = alwaysJmp;
    }
}
