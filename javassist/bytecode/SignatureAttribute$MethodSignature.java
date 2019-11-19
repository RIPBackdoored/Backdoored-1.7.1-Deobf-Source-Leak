package javassist.bytecode;

public static class MethodSignature
{
    TypeParameter[] typeParams;
    Type[] params;
    Type retType;
    ObjectType[] exceptions;
    
    public MethodSignature(final TypeParameter[] array, final Type[] array2, final Type type, final ObjectType[] array3) {
        super();
        this.typeParams = ((array == null) ? new TypeParameter[0] : array);
        this.params = ((array2 == null) ? new Type[0] : array2);
        this.retType = ((type == null) ? new BaseType("void") : type);
        this.exceptions = ((array3 == null) ? new ObjectType[0] : array3);
    }
    
    public TypeParameter[] getTypeParameters() {
        return this.typeParams;
    }
    
    public Type[] getParameterTypes() {
        return this.params;
    }
    
    public Type getReturnType() {
        return this.retType;
    }
    
    public ObjectType[] getExceptionTypes() {
        return this.exceptions;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        TypeParameter.toString(sb, this.typeParams);
        sb.append(" (");
        Type.toString(sb, this.params);
        sb.append(") ");
        sb.append(this.retType);
        if (this.exceptions.length > 0) {
            sb.append(" throws ");
            Type.toString(sb, this.exceptions);
        }
        return sb.toString();
    }
    
    public String encode() {
        final StringBuffer sb = new StringBuffer();
        if (this.typeParams.length > 0) {
            sb.append('<');
            for (int i = 0; i < this.typeParams.length; ++i) {
                this.typeParams[i].encode(sb);
            }
            sb.append('>');
        }
        sb.append('(');
        for (int j = 0; j < this.params.length; ++j) {
            this.params[j].encode(sb);
        }
        sb.append(')');
        this.retType.encode(sb);
        if (this.exceptions.length > 0) {
            for (int k = 0; k < this.exceptions.length; ++k) {
                sb.append('^');
                this.exceptions[k].encode(sb);
            }
        }
        return sb.toString();
    }
}
