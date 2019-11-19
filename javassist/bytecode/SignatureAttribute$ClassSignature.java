package javassist.bytecode;

public static class ClassSignature
{
    TypeParameter[] params;
    ClassType superClass;
    ClassType[] interfaces;
    
    public ClassSignature(final TypeParameter[] array, final ClassType classType, final ClassType[] array2) {
        super();
        this.params = ((array == null) ? new TypeParameter[0] : array);
        this.superClass = ((classType == null) ? ClassType.OBJECT : classType);
        this.interfaces = ((array2 == null) ? new ClassType[0] : array2);
    }
    
    public ClassSignature(final TypeParameter[] array) {
        this(array, null, null);
    }
    
    public TypeParameter[] getParameters() {
        return this.params;
    }
    
    public ClassType getSuperClass() {
        return this.superClass;
    }
    
    public ClassType[] getInterfaces() {
        return this.interfaces;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        TypeParameter.toString(sb, this.params);
        sb.append(" extends ").append(this.superClass);
        if (this.interfaces.length > 0) {
            sb.append(" implements ");
            Type.toString(sb, this.interfaces);
        }
        return sb.toString();
    }
    
    public String encode() {
        final StringBuffer sb = new StringBuffer();
        if (this.params.length > 0) {
            sb.append('<');
            for (int i = 0; i < this.params.length; ++i) {
                this.params[i].encode(sb);
            }
            sb.append('>');
        }
        this.superClass.encode(sb);
        for (int j = 0; j < this.interfaces.length; ++j) {
            this.interfaces[j].encode(sb);
        }
        return sb.toString();
    }
}
