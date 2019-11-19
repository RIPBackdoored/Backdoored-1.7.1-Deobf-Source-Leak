package javassist.bytecode;

public abstract static class ObjectType extends Type
{
    public ObjectType() {
        super();
    }
    
    public String encode() {
        final StringBuffer sb = new StringBuffer();
        this.encode(sb);
        return sb.toString();
    }
}
