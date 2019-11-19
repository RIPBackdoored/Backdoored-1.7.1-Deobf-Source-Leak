package javassist.bytecode.annotation;

import java.io.IOException;
import javassist.bytecode.Descriptor;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

public class EnumMemberValue extends MemberValue
{
    int typeIndex;
    int valueIndex;
    
    public EnumMemberValue(final int typeIndex, final int valueIndex, final ConstPool constPool) {
        super('e', constPool);
        this.typeIndex = typeIndex;
        this.valueIndex = valueIndex;
    }
    
    public EnumMemberValue(final ConstPool constPool) {
        super('e', constPool);
        final int n = 0;
        this.valueIndex = n;
        this.typeIndex = n;
    }
    
    @Override
    Object getValue(final ClassLoader classLoader, final ClassPool classPool, final Method method) throws ClassNotFoundException {
        try {
            return this.getType(classLoader).getField(this.getValue()).get(null);
        }
        catch (NoSuchFieldException ex) {
            throw new ClassNotFoundException(this.getType() + "." + this.getValue());
        }
        catch (IllegalAccessException ex2) {
            throw new ClassNotFoundException(this.getType() + "." + this.getValue());
        }
    }
    
    @Override
    Class getType(final ClassLoader classLoader) throws ClassNotFoundException {
        return MemberValue.loadClass(classLoader, this.getType());
    }
    
    public String getType() {
        return Descriptor.toClassName(this.cp.getUtf8Info(this.typeIndex));
    }
    
    public void setType(final String s) {
        this.typeIndex = this.cp.addUtf8Info(Descriptor.of(s));
    }
    
    public String getValue() {
        return this.cp.getUtf8Info(this.valueIndex);
    }
    
    public void setValue(final String s) {
        this.valueIndex = this.cp.addUtf8Info(s);
    }
    
    @Override
    public String toString() {
        return this.getType() + "." + this.getValue();
    }
    
    @Override
    public void write(final AnnotationsWriter annotationsWriter) throws IOException {
        annotationsWriter.enumConstValue(this.cp.getUtf8Info(this.typeIndex), this.getValue());
    }
    
    @Override
    public void accept(final MemberValueVisitor memberValueVisitor) {
        memberValueVisitor.visitEnumMemberValue(this);
    }
}
