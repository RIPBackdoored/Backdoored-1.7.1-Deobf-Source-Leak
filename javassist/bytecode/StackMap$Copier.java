package javassist.bytecode;

import java.util.Map;

static class Copier extends Walker
{
    byte[] dest;
    ConstPool srcCp;
    ConstPool destCp;
    Map classnames;
    
    Copier(final StackMap stackMap, final ConstPool destCp, final Map classnames) {
        super(stackMap);
        this.srcCp = stackMap.getConstPool();
        this.dest = new byte[this.info.length];
        this.destCp = destCp;
        this.classnames = classnames;
    }
    
    @Override
    public void visit() {
        ByteArray.write16bit(ByteArray.readU16bit(this.info, 0), this.dest, 0);
        super.visit();
    }
    
    @Override
    public int locals(final int n, final int n2, final int n3) {
        ByteArray.write16bit(n2, this.dest, n - 4);
        return super.locals(n, n2, n3);
    }
    
    @Override
    public int typeInfoArray(final int n, final int n2, final int n3, final boolean b) {
        ByteArray.write16bit(n3, this.dest, n - 2);
        return super.typeInfoArray(n, n2, n3, b);
    }
    
    @Override
    public void typeInfo(final int n, final byte b) {
        this.dest[n] = b;
    }
    
    @Override
    public void objectVariable(final int n, final int n2) {
        this.dest[n] = 7;
        ByteArray.write16bit(this.srcCp.copy(n2, this.destCp, this.classnames), this.dest, n + 1);
    }
    
    @Override
    public void uninitialized(final int n, final int n2) {
        this.dest[n] = 8;
        ByteArray.write16bit(n2, this.dest, n + 1);
    }
    
    public StackMap getStackMap() {
        return new StackMap(this.destCp, this.dest);
    }
}
