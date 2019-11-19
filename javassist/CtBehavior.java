package javassist;

import javassist.bytecode.LineNumberAttribute;
import javassist.bytecode.Bytecode;
import javassist.bytecode.CodeIterator;
import javassist.expr.ExprEditor;
import javassist.bytecode.StackMap;
import javassist.bytecode.StackMapTable;
import javassist.bytecode.LocalVariableTypeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.CodeAttribute;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;
import javassist.bytecode.ExceptionsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.SignatureAttribute;
import javassist.bytecode.Descriptor;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.ConstPool;
import javassist.bytecode.BadBytecode;
import java.util.Map;
import javassist.bytecode.MethodInfo;

public abstract class CtBehavior extends CtMember
{
    protected MethodInfo methodInfo;
    
    protected CtBehavior(final CtClass ctClass, final MethodInfo methodInfo) {
        super(ctClass);
        this.methodInfo = methodInfo;
    }
    
    void copy(final CtBehavior ctBehavior, final boolean b, ClassMap classMap) throws CannotCompileException {
        final CtClass declaringClass = this.declaringClass;
        final MethodInfo methodInfo = ctBehavior.methodInfo;
        final CtClass declaringClass2 = ctBehavior.getDeclaringClass();
        final ConstPool constPool = declaringClass.getClassFile2().getConstPool();
        classMap = new ClassMap(classMap);
        classMap.put(declaringClass2.getName(), declaringClass.getName());
        try {
            boolean b2 = false;
            final CtClass superclass = declaringClass2.getSuperclass();
            final CtClass superclass2 = declaringClass.getSuperclass();
            String name = null;
            if (superclass != null && superclass2 != null) {
                final String name2 = superclass.getName();
                name = superclass2.getName();
                if (!name2.equals(name)) {
                    if (name2.equals("java.lang.Object")) {
                        b2 = true;
                    }
                    else {
                        classMap.putIfNone(name2, name);
                    }
                }
            }
            this.methodInfo = new MethodInfo(constPool, methodInfo.getName(), methodInfo, classMap);
            if (b && b2) {
                this.methodInfo.setSuperclass(name);
            }
        }
        catch (NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
        catch (BadBytecode badBytecode) {
            throw new CannotCompileException(badBytecode);
        }
    }
    
    @Override
    protected void extendToString(final StringBuffer sb) {
        sb.append(' ');
        sb.append(this.getName());
        sb.append(' ');
        sb.append(this.methodInfo.getDescriptor());
    }
    
    public abstract String getLongName();
    
    public MethodInfo getMethodInfo() {
        this.declaringClass.checkModify();
        return this.methodInfo;
    }
    
    public MethodInfo getMethodInfo2() {
        return this.methodInfo;
    }
    
    @Override
    public int getModifiers() {
        return AccessFlag.toModifier(this.methodInfo.getAccessFlags());
    }
    
    @Override
    public void setModifiers(final int n) {
        this.declaringClass.checkModify();
        this.methodInfo.setAccessFlags(AccessFlag.of(n));
    }
    
    @Override
    public boolean hasAnnotation(final String s) {
        final MethodInfo methodInfo2 = this.getMethodInfo2();
        return CtClassType.hasAnnotationType(s, this.getDeclaringClass().getClassPool(), (AnnotationsAttribute)methodInfo2.getAttribute("RuntimeInvisibleAnnotations"), (AnnotationsAttribute)methodInfo2.getAttribute("RuntimeVisibleAnnotations"));
    }
    
    @Override
    public Object getAnnotation(final Class clazz) throws ClassNotFoundException {
        final MethodInfo methodInfo2 = this.getMethodInfo2();
        return CtClassType.getAnnotationType(clazz, this.getDeclaringClass().getClassPool(), (AnnotationsAttribute)methodInfo2.getAttribute("RuntimeInvisibleAnnotations"), (AnnotationsAttribute)methodInfo2.getAttribute("RuntimeVisibleAnnotations"));
    }
    
    @Override
    public Object[] getAnnotations() throws ClassNotFoundException {
        return this.getAnnotations(false);
    }
    
    @Override
    public Object[] getAvailableAnnotations() {
        try {
            return this.getAnnotations(true);
        }
        catch (ClassNotFoundException ex) {
            throw new RuntimeException("Unexpected exception", ex);
        }
    }
    
    private Object[] getAnnotations(final boolean b) throws ClassNotFoundException {
        final MethodInfo methodInfo2 = this.getMethodInfo2();
        return CtClassType.toAnnotationType(b, this.getDeclaringClass().getClassPool(), (AnnotationsAttribute)methodInfo2.getAttribute("RuntimeInvisibleAnnotations"), (AnnotationsAttribute)methodInfo2.getAttribute("RuntimeVisibleAnnotations"));
    }
    
    public Object[][] getParameterAnnotations() throws ClassNotFoundException {
        return this.getParameterAnnotations(false);
    }
    
    public Object[][] getAvailableParameterAnnotations() {
        try {
            return this.getParameterAnnotations(true);
        }
        catch (ClassNotFoundException ex) {
            throw new RuntimeException("Unexpected exception", ex);
        }
    }
    
    Object[][] getParameterAnnotations(final boolean b) throws ClassNotFoundException {
        final MethodInfo methodInfo2 = this.getMethodInfo2();
        return CtClassType.toAnnotationType(b, this.getDeclaringClass().getClassPool(), (ParameterAnnotationsAttribute)methodInfo2.getAttribute("RuntimeInvisibleParameterAnnotations"), (ParameterAnnotationsAttribute)methodInfo2.getAttribute("RuntimeVisibleParameterAnnotations"), methodInfo2);
    }
    
    public CtClass[] getParameterTypes() throws NotFoundException {
        return Descriptor.getParameterTypes(this.methodInfo.getDescriptor(), this.declaringClass.getClassPool());
    }
    
    CtClass getReturnType0() throws NotFoundException {
        return Descriptor.getReturnType(this.methodInfo.getDescriptor(), this.declaringClass.getClassPool());
    }
    
    @Override
    public String getSignature() {
        return this.methodInfo.getDescriptor();
    }
    
    @Override
    public String getGenericSignature() {
        final SignatureAttribute signatureAttribute = (SignatureAttribute)this.methodInfo.getAttribute("Signature");
        return (signatureAttribute == null) ? null : signatureAttribute.getSignature();
    }
    
    @Override
    public void setGenericSignature(final String s) {
        this.declaringClass.checkModify();
        this.methodInfo.addAttribute(new SignatureAttribute(this.methodInfo.getConstPool(), s));
    }
    
    public CtClass[] getExceptionTypes() throws NotFoundException {
        final ExceptionsAttribute exceptionsAttribute = this.methodInfo.getExceptionsAttribute();
        String[] exceptions;
        if (exceptionsAttribute == null) {
            exceptions = null;
        }
        else {
            exceptions = exceptionsAttribute.getExceptions();
        }
        return this.declaringClass.getClassPool().get(exceptions);
    }
    
    public void setExceptionTypes(final CtClass[] array) throws NotFoundException {
        this.declaringClass.checkModify();
        if (array == null || array.length == 0) {
            this.methodInfo.removeExceptionsAttribute();
            return;
        }
        final String[] exceptions = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            exceptions[i] = array[i].getName();
        }
        ExceptionsAttribute exceptionsAttribute = this.methodInfo.getExceptionsAttribute();
        if (exceptionsAttribute == null) {
            exceptionsAttribute = new ExceptionsAttribute(this.methodInfo.getConstPool());
            this.methodInfo.setExceptionsAttribute(exceptionsAttribute);
        }
        exceptionsAttribute.setExceptions(exceptions);
    }
    
    public abstract boolean isEmpty();
    
    public void setBody(final String s) throws CannotCompileException {
        this.setBody(s, null, null);
    }
    
    public void setBody(final String s, final String s2, final String s3) throws CannotCompileException {
        final CtClass declaringClass = this.declaringClass;
        declaringClass.checkModify();
        try {
            final Javac javac = new Javac(declaringClass);
            if (s3 != null) {
                javac.recordProceed(s2, s3);
            }
            this.methodInfo.setCodeAttribute(javac.compileBody(this, s).toCodeAttribute());
            this.methodInfo.setAccessFlags(this.methodInfo.getAccessFlags() & 0xFFFFFBFF);
            this.methodInfo.rebuildStackMapIf6(declaringClass.getClassPool(), declaringClass.getClassFile2());
            this.declaringClass.rebuildClassFile();
        }
        catch (CompileError compileError) {
            throw new CannotCompileException(compileError);
        }
        catch (BadBytecode badBytecode) {
            throw new CannotCompileException(badBytecode);
        }
    }
    
    static void setBody0(final CtClass ctClass, final MethodInfo methodInfo, final CtClass ctClass2, final MethodInfo methodInfo2, ClassMap classMap) throws CannotCompileException {
        ctClass2.checkModify();
        classMap = new ClassMap(classMap);
        classMap.put(ctClass.getName(), ctClass2.getName());
        try {
            final CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            if (codeAttribute != null) {
                methodInfo2.setCodeAttribute((CodeAttribute)codeAttribute.copy(methodInfo2.getConstPool(), classMap));
            }
        }
        catch (CodeAttribute.RuntimeCopyException ex) {
            throw new CannotCompileException(ex);
        }
        methodInfo2.setAccessFlags(methodInfo2.getAccessFlags() & 0xFFFFFBFF);
        ctClass2.rebuildClassFile();
    }
    
    @Override
    public byte[] getAttribute(final String s) {
        final AttributeInfo attribute = this.methodInfo.getAttribute(s);
        if (attribute == null) {
            return null;
        }
        return attribute.get();
    }
    
    @Override
    public void setAttribute(final String s, final byte[] array) {
        this.declaringClass.checkModify();
        this.methodInfo.addAttribute(new AttributeInfo(this.methodInfo.getConstPool(), s, array));
    }
    
    public void useCflow(final String s) throws CannotCompileException {
        final CtClass declaringClass = this.declaringClass;
        declaringClass.checkModify();
        final ClassPool classPool = declaringClass.getClassPool();
        int n = 0;
        while (true) {
            final String string = "_cflow$" + n++;
            try {
                declaringClass.getDeclaredField(string);
            }
            catch (NotFoundException ex2) {
                classPool.recordCflow(s, this.declaringClass.getName(), string);
                try {
                    final CtClass value = classPool.get("javassist.runtime.Cflow");
                    final CtField ctField = new CtField(value, string, declaringClass);
                    ctField.setModifiers(9);
                    declaringClass.addField(ctField, CtField.Initializer.byNew(value));
                    this.insertBefore(string + ".enter();", false);
                    this.insertAfter(string + ".exit();", true);
                }
                catch (NotFoundException ex) {
                    throw new CannotCompileException(ex);
                }
            }
        }
    }
    
    public void addLocalVariable(final String s, final CtClass ctClass) throws CannotCompileException {
        this.declaringClass.checkModify();
        final ConstPool constPool = this.methodInfo.getConstPool();
        final CodeAttribute codeAttribute = this.methodInfo.getCodeAttribute();
        if (codeAttribute == null) {
            throw new CannotCompileException("no method body");
        }
        LocalVariableAttribute localVariableAttribute = (LocalVariableAttribute)codeAttribute.getAttribute("LocalVariableTable");
        if (localVariableAttribute == null) {
            localVariableAttribute = new LocalVariableAttribute(constPool);
            codeAttribute.getAttributes().add(localVariableAttribute);
        }
        final int maxLocals = codeAttribute.getMaxLocals();
        final String of = Descriptor.of(ctClass);
        localVariableAttribute.addEntry(0, codeAttribute.getCodeLength(), constPool.addUtf8Info(s), constPool.addUtf8Info(of), maxLocals);
        codeAttribute.setMaxLocals(maxLocals + Descriptor.dataSize(of));
    }
    
    public void insertParameter(final CtClass ctClass) throws CannotCompileException {
        this.declaringClass.checkModify();
        final String descriptor = this.methodInfo.getDescriptor();
        final String insertParameter = Descriptor.insertParameter(ctClass, descriptor);
        try {
            this.addParameter2(Modifier.isStatic(this.getModifiers()) ? 0 : 1, ctClass, descriptor);
        }
        catch (BadBytecode badBytecode) {
            throw new CannotCompileException(badBytecode);
        }
        this.methodInfo.setDescriptor(insertParameter);
    }
    
    public void addParameter(final CtClass ctClass) throws CannotCompileException {
        this.declaringClass.checkModify();
        final String descriptor = this.methodInfo.getDescriptor();
        final String appendParameter = Descriptor.appendParameter(ctClass, descriptor);
        final int n = Modifier.isStatic(this.getModifiers()) ? 0 : 1;
        try {
            this.addParameter2(n + Descriptor.paramSize(descriptor), ctClass, descriptor);
        }
        catch (BadBytecode badBytecode) {
            throw new CannotCompileException(badBytecode);
        }
        this.methodInfo.setDescriptor(appendParameter);
    }
    
    private void addParameter2(final int n, final CtClass ctClass, final String s) throws BadBytecode {
        final CodeAttribute codeAttribute = this.methodInfo.getCodeAttribute();
        if (codeAttribute != null) {
            int dataSize = 1;
            char descriptor = 'L';
            int addClassInfo = 0;
            if (ctClass.isPrimitive()) {
                final CtPrimitiveType ctPrimitiveType = (CtPrimitiveType)ctClass;
                dataSize = ctPrimitiveType.getDataSize();
                descriptor = ctPrimitiveType.getDescriptor();
            }
            else {
                addClassInfo = this.methodInfo.getConstPool().addClassInfo(ctClass);
            }
            codeAttribute.insertLocalVar(n, dataSize);
            final LocalVariableAttribute localVariableAttribute = (LocalVariableAttribute)codeAttribute.getAttribute("LocalVariableTable");
            if (localVariableAttribute != null) {
                localVariableAttribute.shiftIndex(n, dataSize);
            }
            final LocalVariableTypeAttribute localVariableTypeAttribute = (LocalVariableTypeAttribute)codeAttribute.getAttribute("LocalVariableTypeTable");
            if (localVariableTypeAttribute != null) {
                localVariableTypeAttribute.shiftIndex(n, dataSize);
            }
            final StackMapTable stackMapTable = (StackMapTable)codeAttribute.getAttribute("StackMapTable");
            if (stackMapTable != null) {
                stackMapTable.insertLocal(n, StackMapTable.typeTagOf(descriptor), addClassInfo);
            }
            final StackMap stackMap = (StackMap)codeAttribute.getAttribute("StackMap");
            if (stackMap != null) {
                stackMap.insertLocal(n, StackMapTable.typeTagOf(descriptor), addClassInfo);
            }
        }
    }
    
    public void instrument(final CodeConverter codeConverter) throws CannotCompileException {
        this.declaringClass.checkModify();
        codeConverter.doit(this.getDeclaringClass(), this.methodInfo, this.methodInfo.getConstPool());
    }
    
    public void instrument(final ExprEditor exprEditor) throws CannotCompileException {
        if (this.declaringClass.isFrozen()) {
            this.declaringClass.checkModify();
        }
        if (exprEditor.doit(this.declaringClass, this.methodInfo)) {
            this.declaringClass.checkModify();
        }
    }
    
    public void insertBefore(final String s) throws CannotCompileException {
        this.insertBefore(s, true);
    }
    
    private void insertBefore(final String s, final boolean b) throws CannotCompileException {
        final CtClass declaringClass = this.declaringClass;
        declaringClass.checkModify();
        final CodeAttribute codeAttribute = this.methodInfo.getCodeAttribute();
        if (codeAttribute == null) {
            throw new CannotCompileException("no method body");
        }
        final CodeIterator iterator = codeAttribute.iterator();
        final Javac javac = new Javac(declaringClass);
        try {
            javac.recordParamNames(codeAttribute, javac.recordParams(this.getParameterTypes(), Modifier.isStatic(this.getModifiers())));
            javac.recordLocalVariables(codeAttribute, 0);
            javac.recordType(this.getReturnType0());
            javac.compileStmnt(s);
            final Bytecode bytecode = javac.getBytecode();
            final int maxStack = bytecode.getMaxStack();
            final int maxLocals = bytecode.getMaxLocals();
            if (maxStack > codeAttribute.getMaxStack()) {
                codeAttribute.setMaxStack(maxStack);
            }
            if (maxLocals > codeAttribute.getMaxLocals()) {
                codeAttribute.setMaxLocals(maxLocals);
            }
            iterator.insert(bytecode.getExceptionTable(), iterator.insertEx(bytecode.get()));
            if (b) {
                this.methodInfo.rebuildStackMapIf6(declaringClass.getClassPool(), declaringClass.getClassFile2());
            }
        }
        catch (NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
        catch (CompileError compileError) {
            throw new CannotCompileException(compileError);
        }
        catch (BadBytecode badBytecode) {
            throw new CannotCompileException(badBytecode);
        }
    }
    
    public void insertAfter(final String s) throws CannotCompileException {
        this.insertAfter(s, false);
    }
    
    public void insertAfter(final String s, final boolean b) throws CannotCompileException {
        final CtClass declaringClass = this.declaringClass;
        declaringClass.checkModify();
        final ConstPool constPool = this.methodInfo.getConstPool();
        final CodeAttribute codeAttribute = this.methodInfo.getCodeAttribute();
        if (codeAttribute == null) {
            throw new CannotCompileException("no method body");
        }
        final CodeIterator iterator = codeAttribute.iterator();
        final Bytecode bytecode = new Bytecode(constPool, 0, codeAttribute.getMaxLocals() + 1);
        bytecode.setStackDepth(codeAttribute.getMaxStack() + 1);
        final Javac javac = new Javac(bytecode, declaringClass);
        try {
            javac.recordParamNames(codeAttribute, javac.recordParams(this.getParameterTypes(), Modifier.isStatic(this.getModifiers())));
            final CtClass returnType0 = this.getReturnType0();
            final int recordReturnType = javac.recordReturnType(returnType0, true);
            javac.recordLocalVariables(codeAttribute, 0);
            int insertAfterHandler = this.insertAfterHandler(b, bytecode, returnType0, recordReturnType, javac, s);
            int codeLength = iterator.getCodeLength();
            if (b) {
                codeAttribute.getExceptionTable().add(this.getStartPosOfBody(codeAttribute), codeLength, codeLength, 0);
            }
            int insertAfterAdvice = 0;
            int n = 0;
            int n2 = 1;
            while (iterator.hasNext()) {
                final int next = iterator.next();
                if (next >= codeLength) {
                    break;
                }
                final int byte1 = iterator.byteAt(next);
                if (byte1 != 176 && byte1 != 172 && byte1 != 174 && byte1 != 173 && byte1 != 175 && byte1 != 177) {
                    continue;
                }
                if (n2 != 0) {
                    insertAfterAdvice = this.insertAfterAdvice(bytecode, javac, s, constPool, returnType0, recordReturnType);
                    final int append = iterator.append(bytecode.get());
                    iterator.append(bytecode.getExceptionTable(), append);
                    n = iterator.getCodeLength() - insertAfterAdvice;
                    insertAfterHandler = n - append;
                    n2 = 0;
                }
                this.insertGoto(iterator, n, next);
                n = iterator.getCodeLength() - insertAfterAdvice;
                codeLength = n - insertAfterHandler;
            }
            if (n2 != 0) {
                iterator.append(bytecode.getExceptionTable(), iterator.append(bytecode.get()));
            }
            codeAttribute.setMaxStack(bytecode.getMaxStack());
            codeAttribute.setMaxLocals(bytecode.getMaxLocals());
            this.methodInfo.rebuildStackMapIf6(declaringClass.getClassPool(), declaringClass.getClassFile2());
        }
        catch (NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
        catch (CompileError compileError) {
            throw new CannotCompileException(compileError);
        }
        catch (BadBytecode badBytecode) {
            throw new CannotCompileException(badBytecode);
        }
    }
    
    private int insertAfterAdvice(final Bytecode bytecode, final Javac javac, final String s, final ConstPool constPool, final CtClass ctClass, final int n) throws CompileError {
        final int currentPc = bytecode.currentPc();
        if (ctClass == CtClass.voidType) {
            bytecode.addOpcode(1);
            bytecode.addAstore(n);
            javac.compileStmnt(s);
            bytecode.addOpcode(177);
            if (bytecode.getMaxLocals() < 1) {
                bytecode.setMaxLocals(1);
            }
        }
        else {
            bytecode.addStore(n, ctClass);
            javac.compileStmnt(s);
            bytecode.addLoad(n, ctClass);
            if (ctClass.isPrimitive()) {
                bytecode.addOpcode(((CtPrimitiveType)ctClass).getReturnOp());
            }
            else {
                bytecode.addOpcode(176);
            }
        }
        return bytecode.currentPc() - currentPc;
    }
    
    private void insertGoto(final CodeIterator codeIterator, final int mark, int n) throws BadBytecode {
        codeIterator.setMark(mark);
        codeIterator.writeByte(0, n);
        final boolean b = mark + 2 - n > 32767;
        final int n2 = b ? 4 : 2;
        final CodeIterator.Gap insertGap = codeIterator.insertGapAt(n, n2, false);
        n = insertGap.position + insertGap.length - n2;
        final int n3 = codeIterator.getMark() - n;
        if (b) {
            codeIterator.writeByte(200, n);
            codeIterator.write32bit(n3, n + 1);
        }
        else if (n3 <= 32767) {
            codeIterator.writeByte(167, n);
            codeIterator.write16bit(n3, n + 1);
        }
        else {
            if (insertGap.length < 4) {
                final CodeIterator.Gap insertGap2 = codeIterator.insertGapAt(insertGap.position, 2, false);
                n = insertGap2.position + insertGap2.length + insertGap.length - 4;
            }
            codeIterator.writeByte(200, n);
            codeIterator.write32bit(codeIterator.getMark() - n, n + 1);
        }
    }
    
    private int insertAfterHandler(final boolean b, final Bytecode bytecode, final CtClass ctClass, final int n, final Javac javac, final String s) throws CompileError {
        return 0;
    }
    
    public void addCatch(final String s, final CtClass ctClass) throws CannotCompileException {
        this.addCatch(s, ctClass, "$e");
    }
    
    public void addCatch(final String s, final CtClass ctClass, final String s2) throws CannotCompileException {
        final CtClass declaringClass = this.declaringClass;
        declaringClass.checkModify();
        final ConstPool constPool = this.methodInfo.getConstPool();
        final CodeAttribute codeAttribute = this.methodInfo.getCodeAttribute();
        final CodeIterator iterator = codeAttribute.iterator();
        final Bytecode bytecode = new Bytecode(constPool, codeAttribute.getMaxStack(), codeAttribute.getMaxLocals());
        bytecode.setStackDepth(1);
        final Javac javac = new Javac(bytecode, declaringClass);
        try {
            javac.recordParams(this.getParameterTypes(), Modifier.isStatic(this.getModifiers()));
            bytecode.addAstore(javac.recordVariable(ctClass, s2));
            javac.compileStmnt(s);
            final int maxStack = bytecode.getMaxStack();
            final int maxLocals = bytecode.getMaxLocals();
            if (maxStack > codeAttribute.getMaxStack()) {
                codeAttribute.setMaxStack(maxStack);
            }
            if (maxLocals > codeAttribute.getMaxLocals()) {
                codeAttribute.setMaxLocals(maxLocals);
            }
            final int codeLength = iterator.getCodeLength();
            final int append = iterator.append(bytecode.get());
            codeAttribute.getExceptionTable().add(this.getStartPosOfBody(codeAttribute), codeLength, codeLength, constPool.addClassInfo(ctClass));
            iterator.append(bytecode.getExceptionTable(), append);
            this.methodInfo.rebuildStackMapIf6(declaringClass.getClassPool(), declaringClass.getClassFile2());
        }
        catch (NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
        catch (CompileError compileError) {
            throw new CannotCompileException(compileError);
        }
        catch (BadBytecode badBytecode) {
            throw new CannotCompileException(badBytecode);
        }
    }
    
    int getStartPosOfBody(final CodeAttribute codeAttribute) throws CannotCompileException {
        return 0;
    }
    
    public int insertAt(final int n, final String s) throws CannotCompileException {
        return this.insertAt(n, true, s);
    }
    
    public int insertAt(int line, final boolean b, final String s) throws CannotCompileException {
        final CodeAttribute codeAttribute = this.methodInfo.getCodeAttribute();
        if (codeAttribute == null) {
            throw new CannotCompileException("no method body");
        }
        final LineNumberAttribute lineNumberAttribute = (LineNumberAttribute)codeAttribute.getAttribute("LineNumberTable");
        if (lineNumberAttribute == null) {
            throw new CannotCompileException("no line number info");
        }
        final LineNumberAttribute.Pc nearPc = lineNumberAttribute.toNearPc(line);
        line = nearPc.line;
        final int index = nearPc.index;
        return line;
    }
}
