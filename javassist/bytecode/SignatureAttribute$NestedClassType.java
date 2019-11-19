package javassist.bytecode;

public static class NestedClassType extends ClassType
{
    ClassType parent;
    
    NestedClassType(final String s, final int n, final int n2, final TypeArgument[] array, final ClassType parent) {
        super(s, n, n2, array);
        this.parent = parent;
    }
    
    public NestedClassType(final ClassType parent, final String s, final TypeArgument[] array) {
        super(s, array);
        this.parent = parent;
    }
    
    @Override
    public ClassType getDeclaringClass() {
        return this.parent;
    }
}
