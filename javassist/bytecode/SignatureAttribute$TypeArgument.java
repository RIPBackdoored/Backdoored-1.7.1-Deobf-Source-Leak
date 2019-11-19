package javassist.bytecode;

public static class TypeArgument
{
    ObjectType arg;
    char wildcard;
    
    TypeArgument(final ObjectType arg, final char wildcard) {
        super();
        this.arg = arg;
        this.wildcard = wildcard;
    }
    
    public TypeArgument(final ObjectType objectType) {
        this(objectType, ' ');
    }
    
    public TypeArgument() {
        this(null, '*');
    }
    
    public static TypeArgument subclassOf(final ObjectType objectType) {
        return new TypeArgument(objectType, '+');
    }
    
    public static TypeArgument superOf(final ObjectType objectType) {
        return new TypeArgument(objectType, '-');
    }
    
    public char getKind() {
        return this.wildcard;
    }
    
    public boolean isWildcard() {
        return this.wildcard != ' ';
    }
    
    public ObjectType getType() {
        return this.arg;
    }
    
    @Override
    public String toString() {
        if (this.wildcard == '*') {
            return "?";
        }
        final String string = this.arg.toString();
        if (this.wildcard == ' ') {
            return string;
        }
        if (this.wildcard == '+') {
            return "? extends " + string;
        }
        return "? super " + string;
    }
    
    static void encode(final StringBuffer sb, final TypeArgument[] array) {
        sb.append('<');
        for (int i = 0; i < array.length; ++i) {
            final TypeArgument typeArgument = array[i];
            if (typeArgument.isWildcard()) {
                sb.append(typeArgument.wildcard);
            }
            if (typeArgument.getType() != null) {
                typeArgument.getType().encode(sb);
            }
        }
        sb.append('>');
    }
}
