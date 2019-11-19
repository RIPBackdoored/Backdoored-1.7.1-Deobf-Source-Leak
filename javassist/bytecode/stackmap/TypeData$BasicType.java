package javassist.bytecode.stackmap;

import java.util.HashSet;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

protected static class BasicType extends TypeData
{
    private String name;
    private int typeTag;
    private char decodedName;
    
    public BasicType(final String name, final int typeTag, final char decodedName) {
        super();
        this.name = name;
        this.typeTag = typeTag;
        this.decodedName = decodedName;
    }
    
    @Override
    public int getTypeTag() {
        return this.typeTag;
    }
    
    @Override
    public int getTypeData(final ConstPool constPool) {
        return 0;
    }
    
    @Override
    public TypeData join() {
        if (this == TypeTag.TOP) {
            return this;
        }
        return super.join();
    }
    
    @Override
    public BasicType isBasicType() {
        return this;
    }
    
    @Override
    public boolean is2WordType() {
        return this.typeTag == 4 || this.typeTag == 3;
    }
    
    @Override
    public boolean eq(final TypeData typeData) {
        return this == typeData;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    public char getDecodedName() {
        return this.decodedName;
    }
    
    @Override
    public void setType(final String s, final ClassPool classPool) throws BadBytecode {
        throw new BadBytecode("conflict: " + this.name + " and " + s);
    }
    
    @Override
    public TypeData getArrayType(final int n) throws NotFoundException {
        if (this == TypeTag.TOP) {
            return this;
        }
        if (n < 0) {
            throw new NotFoundException("no element type: " + this.name);
        }
        return this;
    }
    
    @Override
    String toString2(final HashSet set) {
        return this.name;
    }
    
    static /* synthetic */ char access$100(final BasicType basicType) {
        return basicType.decodedName;
    }
}
