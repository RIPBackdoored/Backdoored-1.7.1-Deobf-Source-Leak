package javassist.bytecode.stackmap;

import javassist.bytecode.ConstPool;

public abstract static class AbsTypeVar extends TypeData
{
    public AbsTypeVar() {
        super();
    }
    
    public abstract void merge(final TypeData p0);
    
    @Override
    public int getTypeTag() {
        return 7;
    }
    
    @Override
    public int getTypeData(final ConstPool constPool) {
        return constPool.addClassInfo(this.getName());
    }
    
    @Override
    public boolean eq(final TypeData typeData) {
        return this.getName().equals(typeData.getName());
    }
}
