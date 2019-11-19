package javassist.convert;

import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeIterator;
import javassist.CannotCompileException;
import javassist.bytecode.MethodInfo;
import javassist.CtClass;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Opcode;

public abstract class Transformer implements Opcode
{
    private Transformer next;
    
    public Transformer(final Transformer next) {
        super();
        this.next = next;
    }
    
    public Transformer getNext() {
        return this.next;
    }
    
    public void initialize(final ConstPool constPool, final CodeAttribute codeAttribute) {
    }
    
    public void initialize(final ConstPool constPool, final CtClass ctClass, final MethodInfo methodInfo) throws CannotCompileException {
        this.initialize(constPool, methodInfo.getCodeAttribute());
    }
    
    public void clean() {
    }
    
    public abstract int transform(final CtClass p0, final int p1, final CodeIterator p2, final ConstPool p3) throws CannotCompileException, BadBytecode;
    
    public int extraLocals() {
        return 0;
    }
    
    public int extraStack() {
        return 0;
    }
}
