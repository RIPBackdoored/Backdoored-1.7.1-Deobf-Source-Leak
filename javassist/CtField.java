package javassist;

import javassist.compiler.SymbolTable;
import javassist.compiler.ast.StringL;
import javassist.compiler.ast.DoubleConst;
import javassist.compiler.ast.IntConst;
import javassist.bytecode.Bytecode;
import javassist.bytecode.SignatureAttribute;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AccessFlag;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;
import javassist.compiler.ast.ASTree;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import java.util.ListIterator;
import java.util.Map;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.Descriptor;
import javassist.bytecode.FieldInfo;

public class CtField extends CtMember
{
    static final String javaLangString = "java.lang.String";
    protected FieldInfo fieldInfo;
    
    public CtField(final CtClass ctClass, final String s, final CtClass ctClass2) throws CannotCompileException {
        this(Descriptor.of(ctClass), s, ctClass2);
    }
    
    public CtField(final CtField ctField, final CtClass ctClass) throws CannotCompileException {
        this(ctField.fieldInfo.getDescriptor(), ctField.fieldInfo.getName(), ctClass);
        final ListIterator<AttributeInfo> listIterator = ctField.fieldInfo.getAttributes().listIterator();
        final FieldInfo fieldInfo = this.fieldInfo;
        fieldInfo.setAccessFlags(ctField.fieldInfo.getAccessFlags());
        final ConstPool constPool = fieldInfo.getConstPool();
        while (listIterator.hasNext()) {
            fieldInfo.addAttribute(listIterator.next().copy(constPool, null));
        }
    }
    
    private CtField(final String s, final String s2, final CtClass ctClass) throws CannotCompileException {
        super(ctClass);
        final ClassFile classFile2 = ctClass.getClassFile2();
        if (classFile2 == null) {
            throw new CannotCompileException("bad declaring class: " + ctClass.getName());
        }
        this.fieldInfo = new FieldInfo(classFile2.getConstPool(), s2, s);
    }
    
    CtField(final FieldInfo fieldInfo, final CtClass ctClass) {
        super(ctClass);
        this.fieldInfo = fieldInfo;
    }
    
    @Override
    public String toString() {
        return this.getDeclaringClass().getName() + "." + this.getName() + ":" + this.fieldInfo.getDescriptor();
    }
    
    @Override
    protected void extendToString(final StringBuffer sb) {
        sb.append(' ');
        sb.append(this.getName());
        sb.append(' ');
        sb.append(this.fieldInfo.getDescriptor());
    }
    
    protected ASTree getInitAST() {
        return null;
    }
    
    Initializer getInit() {
        final ASTree initAST = this.getInitAST();
        if (initAST == null) {
            return null;
        }
        return Initializer.byExpr(initAST);
    }
    
    public static CtField make(final String s, final CtClass ctClass) throws CannotCompileException {
        final Javac javac = new Javac(ctClass);
        try {
            final CtMember compile = javac.compile(s);
            if (compile instanceof CtField) {
                return (CtField)compile;
            }
        }
        catch (CompileError compileError) {
            throw new CannotCompileException(compileError);
        }
        throw new CannotCompileException("not a field");
    }
    
    public FieldInfo getFieldInfo() {
        this.declaringClass.checkModify();
        return this.fieldInfo;
    }
    
    public FieldInfo getFieldInfo2() {
        return this.fieldInfo;
    }
    
    @Override
    public CtClass getDeclaringClass() {
        return super.getDeclaringClass();
    }
    
    @Override
    public String getName() {
        return this.fieldInfo.getName();
    }
    
    public void setName(final String name) {
        this.declaringClass.checkModify();
        this.fieldInfo.setName(name);
    }
    
    @Override
    public int getModifiers() {
        return AccessFlag.toModifier(this.fieldInfo.getAccessFlags());
    }
    
    @Override
    public void setModifiers(final int n) {
        this.declaringClass.checkModify();
        this.fieldInfo.setAccessFlags(AccessFlag.of(n));
    }
    
    @Override
    public boolean hasAnnotation(final String s) {
        final FieldInfo fieldInfo2 = this.getFieldInfo2();
        return CtClassType.hasAnnotationType(s, this.getDeclaringClass().getClassPool(), (AnnotationsAttribute)fieldInfo2.getAttribute("RuntimeInvisibleAnnotations"), (AnnotationsAttribute)fieldInfo2.getAttribute("RuntimeVisibleAnnotations"));
    }
    
    @Override
    public Object getAnnotation(final Class clazz) throws ClassNotFoundException {
        final FieldInfo fieldInfo2 = this.getFieldInfo2();
        return CtClassType.getAnnotationType(clazz, this.getDeclaringClass().getClassPool(), (AnnotationsAttribute)fieldInfo2.getAttribute("RuntimeInvisibleAnnotations"), (AnnotationsAttribute)fieldInfo2.getAttribute("RuntimeVisibleAnnotations"));
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
        final FieldInfo fieldInfo2 = this.getFieldInfo2();
        return CtClassType.toAnnotationType(b, this.getDeclaringClass().getClassPool(), (AnnotationsAttribute)fieldInfo2.getAttribute("RuntimeInvisibleAnnotations"), (AnnotationsAttribute)fieldInfo2.getAttribute("RuntimeVisibleAnnotations"));
    }
    
    @Override
    public String getSignature() {
        return this.fieldInfo.getDescriptor();
    }
    
    @Override
    public String getGenericSignature() {
        final SignatureAttribute signatureAttribute = (SignatureAttribute)this.fieldInfo.getAttribute("Signature");
        return (signatureAttribute == null) ? null : signatureAttribute.getSignature();
    }
    
    @Override
    public void setGenericSignature(final String s) {
        this.declaringClass.checkModify();
        this.fieldInfo.addAttribute(new SignatureAttribute(this.fieldInfo.getConstPool(), s));
    }
    
    public CtClass getType() throws NotFoundException {
        return Descriptor.toCtClass(this.fieldInfo.getDescriptor(), this.declaringClass.getClassPool());
    }
    
    public void setType(final CtClass ctClass) {
        this.declaringClass.checkModify();
        this.fieldInfo.setDescriptor(Descriptor.of(ctClass));
    }
    
    public Object getConstantValue() {
        this.fieldInfo.getConstantValue();
        return null;
    }
    
    @Override
    public byte[] getAttribute(final String s) {
        final AttributeInfo attribute = this.fieldInfo.getAttribute(s);
        if (attribute == null) {
            return null;
        }
        return attribute.get();
    }
    
    @Override
    public void setAttribute(final String s, final byte[] array) {
        this.declaringClass.checkModify();
        this.fieldInfo.addAttribute(new AttributeInfo(this.fieldInfo.getConstPool(), s, array));
    }
}
