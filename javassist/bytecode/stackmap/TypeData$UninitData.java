package javassist.bytecode.stackmap;

import java.util.HashSet;
import javassist.bytecode.ConstPool;

public static class UninitData extends ClassName
{
    int offset;
    boolean initialized;
    
    UninitData(final int offset, final String s) {
        super(s);
        this.offset = offset;
        this.initialized = false;
    }
    
    public UninitData copy() {
        return new UninitData(this.offset, this.getName());
    }
    
    @Override
    public int getTypeTag() {
        return 8;
    }
    
    @Override
    public int getTypeData(final ConstPool constPool) {
        return this.offset;
    }
    
    @Override
    public TypeData join() {
        if (this.initialized) {
            return new TypeVar(new ClassName(this.getName()));
        }
        return new UninitTypeVar(this.copy());
    }
    
    @Override
    public boolean isUninit() {
        return true;
    }
    
    @Override
    public boolean eq(final TypeData typeData) {
        if (typeData instanceof UninitData) {
            final UninitData uninitData = (UninitData)typeData;
            return this.offset == uninitData.offset && this.getName().equals(uninitData.getName());
        }
        return false;
    }
    
    public int offset() {
        return this.offset;
    }
    
    @Override
    public void constructorCalled(final int n) {
        if (n == this.offset) {
            this.initialized = true;
        }
    }
    
    @Override
    String toString2(final HashSet set) {
        return this.getName() + "," + this.offset;
    }
}
