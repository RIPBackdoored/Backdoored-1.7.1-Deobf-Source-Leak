package javassist.convert;

import javassist.NotFoundException;
import javassist.ClassPool;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeIterator;
import javassist.CtClass;
import javassist.bytecode.CodeAttribute;
import javassist.Modifier;
import javassist.CtMethod;
import javassist.bytecode.ConstPool;

public class TransformCall extends Transformer
{
    protected String classname;
    protected String methodname;
    protected String methodDescriptor;
    protected String newClassname;
    protected String newMethodname;
    protected boolean newMethodIsPrivate;
    protected int newIndex;
    protected ConstPool constPool;
    
    public TransformCall(final Transformer transformer, final CtMethod ctMethod, final CtMethod ctMethod2) {
        this(transformer, ctMethod.getName(), ctMethod2);
        this.classname = ctMethod.getDeclaringClass().getName();
    }
    
    public TransformCall(final Transformer transformer, final String methodname, final CtMethod ctMethod) {
        super(transformer);
        this.methodname = methodname;
        this.methodDescriptor = ctMethod.getMethodInfo2().getDescriptor();
        final String name = ctMethod.getDeclaringClass().getName();
        this.newClassname = name;
        this.classname = name;
        this.newMethodname = ctMethod.getName();
        this.constPool = null;
        this.newMethodIsPrivate = Modifier.isPrivate(ctMethod.getModifiers());
    }
    
    @Override
    public void initialize(final ConstPool constPool, final CodeAttribute codeAttribute) {
        if (this.constPool != constPool) {
            this.newIndex = 0;
        }
    }
    
    @Override
    public int transform(final CtClass ctClass, int match, final CodeIterator codeIterator, final ConstPool constPool) throws BadBytecode {
        final int byte1 = codeIterator.byteAt(match);
        if (byte1 == 185 || byte1 == 183 || byte1 == 184 || byte1 == 182) {
            final int u16bit = codeIterator.u16bitAt(match + 1);
            final String eqMember = constPool.eqMember(this.methodname, this.methodDescriptor, u16bit);
            if (eqMember != null && this.matchClass(eqMember, ctClass.getClassPool())) {
                match = this.match(byte1, match, codeIterator, constPool.getNameAndTypeDescriptor(constPool.getMemberNameAndType(u16bit)), constPool);
            }
        }
        return match;
    }
    
    private boolean matchClass(final String s, final ClassPool classPool) {
        if (this.classname.equals(s)) {
            return true;
        }
        try {
            final CtClass value = classPool.get(s);
            if (value.subtypeOf(classPool.get(this.classname))) {
                try {
                    return value.getMethod(this.methodname, this.methodDescriptor).getDeclaringClass().getName().equals(this.classname);
                }
                catch (NotFoundException ex) {
                    return true;
                }
            }
        }
        catch (NotFoundException ex2) {
            return false;
        }
        return false;
    }
    
    protected int match(final int n, final int n2, final CodeIterator codeIterator, final int n3, final ConstPool constPool) throws BadBytecode {
        if (this.newIndex == 0) {
            final int addNameAndTypeInfo = constPool.addNameAndTypeInfo(constPool.addUtf8Info(this.newMethodname), n3);
            final int addClassInfo = constPool.addClassInfo(this.newClassname);
            if (n == 185) {
                this.newIndex = constPool.addInterfaceMethodrefInfo(addClassInfo, addNameAndTypeInfo);
            }
            else {
                if (this.newMethodIsPrivate && n == 182) {
                    codeIterator.writeByte(183, n2);
                }
                this.newIndex = constPool.addMethodrefInfo(addClassInfo, addNameAndTypeInfo);
            }
            this.constPool = constPool;
        }
        codeIterator.write16bit(this.newIndex, n2 + 1);
        return n2;
    }
}
