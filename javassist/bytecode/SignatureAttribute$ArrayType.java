package javassist.bytecode;

public static class ArrayType extends ObjectType
{
    int dim;
    Type componentType;
    
    public ArrayType(final int dim, final Type componentType) {
        super();
        this.dim = dim;
        this.componentType = componentType;
    }
    
    public int getDimension() {
        return this.dim;
    }
    
    public Type getComponentType() {
        return this.componentType;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(this.componentType.toString());
        for (int i = 0; i < this.dim; ++i) {
            sb.append("[]");
        }
        return sb.toString();
    }
    
    @Override
    void encode(final StringBuffer sb) {
        for (int i = 0; i < this.dim; ++i) {
            sb.append('[');
        }
        this.componentType.encode(sb);
    }
}
