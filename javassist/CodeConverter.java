package javassist;

import javassist.bytecode.CodeIterator;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.convert.TransformAfter;
import javassist.convert.TransformBefore;
import javassist.convert.TransformCall;
import javassist.convert.TransformAccessArrayField;
import javassist.convert.TransformWriteField;
import javassist.convert.TransformReadField;
import javassist.convert.TransformFieldAccess;
import javassist.convert.TransformNewClass;
import javassist.convert.TransformNew;
import javassist.convert.Transformer;

public class CodeConverter
{
    protected Transformer transformers;
    
    public CodeConverter() {
        super();
        this.transformers = null;
    }
    
    public void replaceNew(final CtClass ctClass, final CtClass ctClass2, final String s) {
        this.transformers = new TransformNew(this.transformers, ctClass.getName(), ctClass2.getName(), s);
    }
    
    public void replaceNew(final CtClass ctClass, final CtClass ctClass2) {
        this.transformers = new TransformNewClass(this.transformers, ctClass.getName(), ctClass2.getName());
    }
    
    public void redirectFieldAccess(final CtField ctField, final CtClass ctClass, final String s) {
        this.transformers = new TransformFieldAccess(this.transformers, ctField, ctClass.getName(), s);
    }
    
    public void replaceFieldRead(final CtField ctField, final CtClass ctClass, final String s) {
        this.transformers = new TransformReadField(this.transformers, ctField, ctClass.getName(), s);
    }
    
    public void replaceFieldWrite(final CtField ctField, final CtClass ctClass, final String s) {
        this.transformers = new TransformWriteField(this.transformers, ctField, ctClass.getName(), s);
    }
    
    public void replaceArrayAccess(final CtClass ctClass, final ArrayAccessReplacementMethodNames arrayAccessReplacementMethodNames) throws NotFoundException {
        this.transformers = new TransformAccessArrayField(this.transformers, ctClass.getName(), arrayAccessReplacementMethodNames);
    }
    
    public void redirectMethodCall(final CtMethod ctMethod, final CtMethod ctMethod2) throws CannotCompileException {
        if (!ctMethod.getMethodInfo2().getDescriptor().equals(ctMethod2.getMethodInfo2().getDescriptor())) {
            throw new CannotCompileException("signature mismatch: " + ctMethod2.getLongName());
        }
        final int modifiers = ctMethod.getModifiers();
        final int modifiers2 = ctMethod2.getModifiers();
        if (Modifier.isStatic(modifiers) != Modifier.isStatic(modifiers2) || (Modifier.isPrivate(modifiers) && !Modifier.isPrivate(modifiers2)) || ctMethod.getDeclaringClass().isInterface() != ctMethod2.getDeclaringClass().isInterface()) {
            throw new CannotCompileException("invoke-type mismatch " + ctMethod2.getLongName());
        }
        this.transformers = new TransformCall(this.transformers, ctMethod, ctMethod2);
    }
    
    public void redirectMethodCall(final String s, final CtMethod ctMethod) throws CannotCompileException {
        this.transformers = new TransformCall(this.transformers, s, ctMethod);
    }
    
    public void insertBeforeMethod(final CtMethod ctMethod, final CtMethod ctMethod2) throws CannotCompileException {
        try {
            this.transformers = new TransformBefore(this.transformers, ctMethod, ctMethod2);
        }
        catch (NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
    }
    
    public void insertAfterMethod(final CtMethod ctMethod, final CtMethod ctMethod2) throws CannotCompileException {
        try {
            this.transformers = new TransformAfter(this.transformers, ctMethod, ctMethod2);
        }
        catch (NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
    }
    
    protected void doit(final CtClass ctClass, final MethodInfo methodInfo, final ConstPool constPool) throws CannotCompileException {
        final CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        if (codeAttribute == null || this.transformers == null) {
            return;
        }
        for (Transformer transformer = this.transformers; transformer != null; transformer = transformer.getNext()) {
            transformer.initialize(constPool, ctClass, methodInfo);
        }
        final CodeIterator iterator = codeAttribute.iterator();
        while (iterator.hasNext()) {
            try {
                int n = iterator.next();
                for (Transformer transformer2 = this.transformers; transformer2 != null; transformer2 = transformer2.getNext()) {
                    n = transformer2.transform(ctClass, n, iterator, constPool);
                }
                continue;
            }
            catch (BadBytecode badBytecode) {
                throw new CannotCompileException(badBytecode);
            }
            break;
        }
        int n2 = 0;
        int n3 = 0;
        for (Transformer transformer3 = this.transformers; transformer3 != null; transformer3 = transformer3.getNext()) {
            final int extraLocals = transformer3.extraLocals();
            if (extraLocals > n2) {
                n2 = extraLocals;
            }
            final int extraStack = transformer3.extraStack();
            if (extraStack > n3) {
                n3 = extraStack;
            }
        }
        for (Transformer transformer4 = this.transformers; transformer4 != null; transformer4 = transformer4.getNext()) {
            transformer4.clean();
        }
        if (n2 > 0) {
            codeAttribute.setMaxLocals(codeAttribute.getMaxLocals() + n2);
        }
        if (n3 > 0) {
            codeAttribute.setMaxStack(codeAttribute.getMaxStack() + n3);
        }
        try {
            methodInfo.rebuildStackMapIf6(ctClass.getClassPool(), ctClass.getClassFile2());
        }
        catch (BadBytecode badBytecode2) {
            throw new CannotCompileException(badBytecode2.getMessage(), badBytecode2);
        }
    }
}
