package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

public class FloatMemberValue extends MemberValue
{
    int valueIndex;
    
    public FloatMemberValue(final int valueIndex, final ConstPool constPool) {
        super('F', constPool);
        this.valueIndex = valueIndex;
    }
    
    public FloatMemberValue(final float value, final ConstPool constPool) {
        super('F', constPool);
        this.setValue(value);
    }
    
    public FloatMemberValue(final ConstPool constPool) {
        super('F', constPool);
        this.setValue(0.0f);
    }
    
    @Override
    Object getValue(final ClassLoader classLoader, final ClassPool classPool, final Method method) {
        return new Float(this.getValue());
    }
    
    @Override
    Class getType(final ClassLoader classLoader) {
        return Float.TYPE;
    }
    
    public float getValue() {
        return this.cp.getFloatInfo(this.valueIndex);
    }
    
    public void setValue(final float n) {
        this.valueIndex = this.cp.addFloatInfo(n);
    }
    
    @Override
    public String toString() {
        return Float.toString(this.getValue());
    }
    
    @Override
    public void write(final AnnotationsWriter annotationsWriter) throws IOException {
        annotationsWriter.constValueIndex(this.getValue());
    }
    
    @Override
    public void accept(final MemberValueVisitor memberValueVisitor) {
        memberValueVisitor.visitFloatMemberValue(this);
    }
}
