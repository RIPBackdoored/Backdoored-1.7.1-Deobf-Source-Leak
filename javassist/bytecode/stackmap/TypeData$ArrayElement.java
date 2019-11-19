package javassist.bytecode.stackmap;

import java.util.HashSet;
import java.util.ArrayList;
import javassist.NotFoundException;
import javassist.ClassPool;
import javassist.bytecode.BadBytecode;

public static class ArrayElement extends AbsTypeVar
{
    private AbsTypeVar array;
    
    private ArrayElement(final AbsTypeVar array) {
        super();
        this.array = array;
    }
    
    public static TypeData make(final TypeData typeData) throws BadBytecode {
        if (typeData instanceof ArrayType) {
            return ((ArrayType)typeData).elementType();
        }
        if (typeData instanceof AbsTypeVar) {
            return new ArrayElement((AbsTypeVar)typeData);
        }
        if (typeData instanceof ClassName && !typeData.isNullType()) {
            return new ClassName(typeName(typeData.getName()));
        }
        throw new BadBytecode("bad AASTORE: " + typeData);
    }
    
    @Override
    public void merge(final TypeData typeData) {
        try {
            if (!typeData.isNullType()) {
                this.array.merge(ArrayType.make(typeData));
            }
        }
        catch (BadBytecode badBytecode) {
            throw new RuntimeException("fatal: " + badBytecode);
        }
    }
    
    @Override
    public String getName() {
        return typeName(this.array.getName());
    }
    
    public AbsTypeVar arrayType() {
        return this.array;
    }
    
    @Override
    public BasicType isBasicType() {
        return null;
    }
    
    @Override
    public boolean is2WordType() {
        return false;
    }
    
    private static String typeName(final String s) {
        if (s.length() > 1 && s.charAt(0) == '[') {
            final char char1 = s.charAt(1);
            if (char1 == 'L') {
                return s.substring(2, s.length() - 1).replace('/', '.');
            }
            if (char1 == '[') {
                return s.substring(1);
            }
        }
        return "java.lang.Object";
    }
    
    @Override
    public void setType(final String s, final ClassPool classPool) throws BadBytecode {
        this.array.setType(ArrayType.typeName(s), classPool);
    }
    
    @Override
    protected TypeVar toTypeVar(final int n) {
        return this.array.toTypeVar(n - 1);
    }
    
    @Override
    public TypeData getArrayType(final int n) throws NotFoundException {
        return this.array.getArrayType(n - 1);
    }
    
    @Override
    public int dfs(final ArrayList list, final int n, final ClassPool classPool) throws NotFoundException {
        return this.array.dfs(list, n, classPool);
    }
    
    @Override
    String toString2(final HashSet set) {
        return "*" + this.array.toString2(set);
    }
    
    static /* synthetic */ String access$000(final String s) {
        return typeName(s);
    }
}
