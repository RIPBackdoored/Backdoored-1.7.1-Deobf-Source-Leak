package javassist.bytecode.stackmap;

import java.util.HashSet;
import java.util.ArrayList;
import javassist.NotFoundException;
import javassist.ClassPool;
import javassist.bytecode.BadBytecode;

public static class ArrayType extends AbsTypeVar
{
    private AbsTypeVar element;
    
    private ArrayType(final AbsTypeVar element) {
        super();
        this.element = element;
    }
    
    static TypeData make(final TypeData typeData) throws BadBytecode {
        if (typeData instanceof ArrayElement) {
            return ((ArrayElement)typeData).arrayType();
        }
        if (typeData instanceof AbsTypeVar) {
            return new ArrayType((AbsTypeVar)typeData);
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
                this.element.merge(ArrayElement.make(typeData));
            }
        }
        catch (BadBytecode badBytecode) {
            throw new RuntimeException("fatal: " + badBytecode);
        }
    }
    
    @Override
    public String getName() {
        return typeName(this.element.getName());
    }
    
    public AbsTypeVar elementType() {
        return this.element;
    }
    
    @Override
    public BasicType isBasicType() {
        return null;
    }
    
    @Override
    public boolean is2WordType() {
        return false;
    }
    
    public static String typeName(final String s) {
        if (s.charAt(0) == '[') {
            return "[" + s;
        }
        return "[L" + s.replace('.', '/') + ";";
    }
    
    @Override
    public void setType(final String s, final ClassPool classPool) throws BadBytecode {
        this.element.setType(ArrayElement.access$000(s), classPool);
    }
    
    @Override
    protected TypeVar toTypeVar(final int n) {
        return this.element.toTypeVar(n + 1);
    }
    
    @Override
    public TypeData getArrayType(final int n) throws NotFoundException {
        return this.element.getArrayType(n + 1);
    }
    
    @Override
    public int dfs(final ArrayList list, final int n, final ClassPool classPool) throws NotFoundException {
        return this.element.dfs(list, n, classPool);
    }
    
    @Override
    String toString2(final HashSet set) {
        return "[" + this.element.toString2(set);
    }
}
