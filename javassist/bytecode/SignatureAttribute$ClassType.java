package javassist.bytecode;

public static class ClassType extends ObjectType
{
    String name;
    TypeArgument[] arguments;
    public static ClassType OBJECT;
    
    static ClassType make(final String s, final int n, final int n2, final TypeArgument[] array, final ClassType classType) {
        if (classType == null) {
            return new ClassType(s, n, n2, array);
        }
        return new NestedClassType(s, n, n2, array, classType);
    }
    
    ClassType(final String s, final int n, final int n2, final TypeArgument[] arguments) {
        super();
        this.name = s.substring(n, n2).replace('/', '.');
        this.arguments = arguments;
    }
    
    public ClassType(final String name, final TypeArgument[] arguments) {
        super();
        this.name = name;
        this.arguments = arguments;
    }
    
    public ClassType(final String s) {
        this(s, null);
    }
    
    public String getName() {
        return this.name;
    }
    
    public TypeArgument[] getTypeArguments() {
        return this.arguments;
    }
    
    public ClassType getDeclaringClass() {
        return null;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        final ClassType declaringClass = this.getDeclaringClass();
        if (declaringClass != null) {
            sb.append(declaringClass.toString()).append('.');
        }
        return this.toString2(sb);
    }
    
    private String toString2(final StringBuffer sb) {
        sb.append(this.name);
        if (this.arguments != null) {
            sb.append('<');
            for (int length = this.arguments.length, i = 0; i < length; ++i) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(this.arguments[i].toString());
            }
            sb.append('>');
        }
        return sb.toString();
    }
    
    @Override
    public String jvmTypeName() {
        final StringBuffer sb = new StringBuffer();
        final ClassType declaringClass = this.getDeclaringClass();
        if (declaringClass != null) {
            sb.append(declaringClass.jvmTypeName()).append('$');
        }
        return this.toString2(sb);
    }
    
    @Override
    void encode(final StringBuffer sb) {
        sb.append('L');
        this.encode2(sb);
        sb.append(';');
    }
    
    void encode2(final StringBuffer sb) {
        final ClassType declaringClass = this.getDeclaringClass();
        if (declaringClass != null) {
            declaringClass.encode2(sb);
            sb.append('$');
        }
        sb.append(this.name.replace('.', '/'));
        if (this.arguments != null) {
            TypeArgument.encode(sb, this.arguments);
        }
    }
    
    static {
        ClassType.OBJECT = new ClassType("java.lang.Object", null);
    }
}
