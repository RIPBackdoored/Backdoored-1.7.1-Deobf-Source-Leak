package javassist.bytecode;

public abstract static class Type
{
    public Type() {
        super();
    }
    
    abstract void encode(final StringBuffer p0);
    
    static void toString(final StringBuffer sb, final Type[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array[i]);
        }
    }
    
    public String jvmTypeName() {
        return this.toString();
    }
}
