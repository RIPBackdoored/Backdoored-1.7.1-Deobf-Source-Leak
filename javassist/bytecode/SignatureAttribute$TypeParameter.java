package javassist.bytecode;

public static class TypeParameter
{
    String name;
    ObjectType superClass;
    ObjectType[] superInterfaces;
    
    TypeParameter(final String s, final int n, final int n2, final ObjectType superClass, final ObjectType[] superInterfaces) {
        super();
        this.name = s.substring(n, n2);
        this.superClass = superClass;
        this.superInterfaces = superInterfaces;
    }
    
    public TypeParameter(final String name, final ObjectType superClass, final ObjectType[] superInterfaces) {
        super();
        this.name = name;
        this.superClass = superClass;
        if (superInterfaces == null) {
            this.superInterfaces = new ObjectType[0];
        }
        else {
            this.superInterfaces = superInterfaces;
        }
    }
    
    public TypeParameter(final String s) {
        this(s, null, null);
    }
    
    public String getName() {
        return this.name;
    }
    
    public ObjectType getClassBound() {
        return this.superClass;
    }
    
    public ObjectType[] getInterfaceBound() {
        return this.superInterfaces;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(this.getName());
        if (this.superClass != null) {
            sb.append(" extends ").append(this.superClass.toString());
        }
        final int length = this.superInterfaces.length;
        if (length > 0) {
            for (int i = 0; i < length; ++i) {
                if (i > 0 || this.superClass != null) {
                    sb.append(" & ");
                }
                else {
                    sb.append(" extends ");
                }
                sb.append(this.superInterfaces[i].toString());
            }
        }
        return sb.toString();
    }
    
    static void toString(final StringBuffer sb, final TypeParameter[] array) {
        sb.append('<');
        for (int i = 0; i < array.length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array[i]);
        }
        sb.append('>');
    }
    
    void encode(final StringBuffer sb) {
        sb.append(this.name);
        if (this.superClass == null) {
            sb.append(":Ljava/lang/Object;");
        }
        else {
            sb.append(':');
            this.superClass.encode(sb);
        }
        for (int i = 0; i < this.superInterfaces.length; ++i) {
            sb.append(':');
            this.superInterfaces[i].encode(sb);
        }
    }
}
