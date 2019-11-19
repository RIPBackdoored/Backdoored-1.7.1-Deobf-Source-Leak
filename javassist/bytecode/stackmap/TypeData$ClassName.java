package javassist.bytecode.stackmap;

import java.util.HashSet;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

public static class ClassName extends TypeData
{
    private String name;
    
    public ClassName(final String name) {
        super();
        this.name = name;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public BasicType isBasicType() {
        return null;
    }
    
    @Override
    public boolean is2WordType() {
        return false;
    }
    
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
        return this.name.equals(typeData.getName());
    }
    
    @Override
    public void setType(final String s, final ClassPool classPool) throws BadBytecode {
    }
    
    @Override
    public TypeData getArrayType(final int n) throws NotFoundException {
        return this;
    }
    
    @Override
    String toString2(final HashSet set) {
        return this.name;
    }
}
