package javassist.bytecode.stackmap;

import java.util.HashSet;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

public static class UninitTypeVar extends AbsTypeVar
{
    protected TypeData type;
    
    public UninitTypeVar(final UninitData type) {
        super();
        this.type = type;
    }
    
    @Override
    public int getTypeTag() {
        return this.type.getTypeTag();
    }
    
    @Override
    public int getTypeData(final ConstPool constPool) {
        return this.type.getTypeData(constPool);
    }
    
    @Override
    public BasicType isBasicType() {
        return this.type.isBasicType();
    }
    
    @Override
    public boolean is2WordType() {
        return this.type.is2WordType();
    }
    
    @Override
    public boolean isUninit() {
        return this.type.isUninit();
    }
    
    @Override
    public boolean eq(final TypeData typeData) {
        return this.type.eq(typeData);
    }
    
    @Override
    public String getName() {
        return this.type.getName();
    }
    
    @Override
    protected TypeVar toTypeVar(final int n) {
        return null;
    }
    
    @Override
    public TypeData join() {
        return this.type.join();
    }
    
    @Override
    public void setType(final String s, final ClassPool classPool) throws BadBytecode {
        this.type.setType(s, classPool);
    }
    
    @Override
    public void merge(final TypeData typeData) {
        if (!typeData.eq(this.type)) {
            this.type = TypeTag.TOP;
        }
    }
    
    @Override
    public void constructorCalled(final int n) {
        this.type.constructorCalled(n);
    }
    
    public int offset() {
        if (this.type instanceof UninitData) {
            return ((UninitData)this.type).offset;
        }
        throw new RuntimeException("not available");
    }
    
    @Override
    public TypeData getArrayType(final int n) throws NotFoundException {
        return this.type.getArrayType(n);
    }
    
    @Override
    String toString2(final HashSet set) {
        return "";
    }
}
