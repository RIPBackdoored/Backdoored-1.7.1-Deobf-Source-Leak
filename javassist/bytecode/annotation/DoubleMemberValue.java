package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

public class DoubleMemberValue extends MemberValue
{
    int valueIndex;
    
    public DoubleMemberValue(final int valueIndex, final ConstPool constPool) {
        super('D', constPool);
        this.valueIndex = valueIndex;
    }
    
    public DoubleMemberValue(final double value, final ConstPool constPool) {
        super('D', constPool);
        this.setValue(value);
    }
    
    public DoubleMemberValue(final ConstPool constPool) {
        super('D', constPool);
        this.setValue(0.0);
    }
    
    @Override
    Object getValue(final ClassLoader classLoader, final ClassPool classPool, final Method method) {
        return new Double(this.getValue());
    }
    
    @Override
    Class getType(final ClassLoader classLoader) {
        return Double.TYPE;
    }
    
    public double getValue() {
        return this.cp.getDoubleInfo(this.valueIndex);
    }
    
    public void setValue(final double n) {
        this.valueIndex = this.cp.addDoubleInfo(n);
    }
    
    @Override
    public String toString() {
        return Double.toString(this.getValue());
    }
    
    @Override
    public void write(final AnnotationsWriter annotationsWriter) throws IOException {
        annotationsWriter.constValueIndex(this.getValue());
    }
    
    @Override
    public void accept(final MemberValueVisitor memberValueVisitor) {
        memberValueVisitor.visitDoubleMemberValue(this);
    }
}
