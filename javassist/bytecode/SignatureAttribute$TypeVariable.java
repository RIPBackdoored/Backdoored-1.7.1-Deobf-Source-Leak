package javassist.bytecode;

public static class TypeVariable extends ObjectType
{
    String name;
    
    TypeVariable(final String s, final int n, final int n2) {
        super();
        this.name = s.substring(n, n2);
    }
    
    public TypeVariable(final String name) {
        super();
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    @Override
    void encode(final StringBuffer sb) {
        sb.append('T').append(this.name).append(';');
    }
}
