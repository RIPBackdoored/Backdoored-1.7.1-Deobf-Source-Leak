package javassist.convert;

import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeIterator;
import javassist.NotFoundException;
import javassist.bytecode.ConstPool;
import javassist.ClassPool;
import javassist.Modifier;
import javassist.CtField;
import javassist.CtClass;

public class TransformReadField extends Transformer
{
    protected String fieldname;
    protected CtClass fieldClass;
    protected boolean isPrivate;
    protected String methodClassname;
    protected String methodName;
    
    public TransformReadField(final Transformer transformer, final CtField ctField, final String methodClassname, final String methodName) {
        super(transformer);
        this.fieldClass = ctField.getDeclaringClass();
        this.fieldname = ctField.getName();
        this.methodClassname = methodClassname;
        this.methodName = methodName;
        this.isPrivate = Modifier.isPrivate(ctField.getModifiers());
    }
    
    static String isField(final ClassPool classPool, final ConstPool constPool, final CtClass ctClass, final String s, final boolean b, final int n) {
        if (!constPool.getFieldrefName(n).equals(s)) {
            return null;
        }
        try {
            final CtClass value = classPool.get(constPool.getFieldrefClassName(n));
            if (value == ctClass || isFieldInSuper(value, ctClass, s)) {
                return constPool.getFieldrefType(n);
            }
        }
        catch (NotFoundException ex) {}
        return null;
    }
    
    static boolean isFieldInSuper(final CtClass ctClass, final CtClass ctClass2, final String s) {
        if (!ctClass.subclassOf(ctClass2)) {
            return false;
        }
        try {
            return ctClass.getField(s).getDeclaringClass() == ctClass2;
        }
        catch (NotFoundException ex) {
            return false;
        }
    }
    
    @Override
    public int transform(final CtClass ctClass, int n, final CodeIterator codeIterator, final ConstPool constPool) throws BadBytecode {
        final int byte1 = codeIterator.byteAt(n);
        if (byte1 == 180 || byte1 == 178) {
            final String field = isField(ctClass.getClassPool(), constPool, this.fieldClass, this.fieldname, this.isPrivate, codeIterator.u16bitAt(n + 1));
            if (field != null) {
                if (byte1 == 178) {
                    codeIterator.move(n);
                    n = codeIterator.insertGap(1);
                    codeIterator.writeByte(1, n);
                    n = codeIterator.next();
                }
                final int addMethodrefInfo = constPool.addMethodrefInfo(constPool.addClassInfo(this.methodClassname), this.methodName, "(Ljava/lang/Object;)" + field);
                codeIterator.writeByte(184, n);
                codeIterator.write16bit(addMethodrefInfo, n + 1);
                return n;
            }
        }
        return n;
    }
}
