package javassist.bytecode;

import javassist.CtClass;

public static class BaseType extends Type
{
    char descriptor;
    
    BaseType(final char descriptor) {
        super();
        this.descriptor = descriptor;
    }
    
    public BaseType(final String s) {
        this(Descriptor.of(s).charAt(0));
    }
    
    public char getDescriptor() {
        return this.descriptor;
    }
    
    public CtClass getCtlass() {
        return Descriptor.toPrimitiveClass(this.descriptor);
    }
    
    @Override
    public String toString() {
        return Descriptor.toClassName(Character.toString(this.descriptor));
    }
    
    @Override
    void encode(final StringBuffer sb) {
        sb.append(this.descriptor);
    }
}
