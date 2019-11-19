package javassist;

import java.util.Set;
import javassist.compiler.CompileError;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.BadBytecode;
import javassist.compiler.Javac;
import javassist.expr.ExprEditor;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ConstantAttribute;
import java.util.HashMap;
import java.util.List;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.EnclosingMethodAttribute;
import javassist.bytecode.annotation.AnnotationImpl;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.AnnotationsAttribute;
import java.util.ArrayList;
import javassist.bytecode.InnerClassesAttribute;
import javassist.bytecode.AccessFlag;
import java.util.Map;
import javassist.bytecode.Descriptor;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.SignatureAttribute;
import java.net.URL;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Hashtable;
import javassist.compiler.AccessorMaker;
import java.lang.ref.WeakReference;
import javassist.bytecode.ClassFile;

class CtClassType extends CtClass
{
    ClassPool classPool;
    boolean wasChanged;
    private boolean wasFrozen;
    boolean wasPruned;
    boolean gcConstPool;
    ClassFile classfile;
    byte[] rawClassfile;
    private WeakReference memberCache;
    private AccessorMaker accessors;
    private FieldInitLink fieldInitializers;
    private Hashtable hiddenMethods;
    private int uniqueNumberSeed;
    private boolean doPruning;
    private int getCount;
    private static final int GET_THRESHOLD = 2;
    
    CtClassType(final String s, final ClassPool classPool) {
        super(s);
        this.doPruning = ClassPool.doPruning;
        this.classPool = classPool;
        final boolean b = false;
        this.gcConstPool = b;
        this.wasPruned = b;
        this.wasFrozen = b;
        this.wasChanged = b;
        this.classfile = null;
        this.rawClassfile = null;
        this.memberCache = null;
        this.accessors = null;
        this.fieldInitializers = null;
        this.hiddenMethods = null;
        this.uniqueNumberSeed = 0;
        this.getCount = 0;
    }
    
    CtClassType(final InputStream inputStream, final ClassPool classPool) throws IOException {
        this((String)null, classPool);
        this.classfile = new ClassFile(new DataInputStream(inputStream));
        this.qualifiedName = this.classfile.getName();
    }
    
    CtClassType(final ClassFile classfile, final ClassPool classPool) {
        this((String)null, classPool);
        this.classfile = classfile;
        this.qualifiedName = this.classfile.getName();
    }
    
    @Override
    protected void extendToString(final StringBuffer sb) {
        if (this.wasChanged) {
            sb.append("changed ");
        }
        if (this.wasFrozen) {
            sb.append("frozen ");
        }
        if (this.wasPruned) {
            sb.append("pruned ");
        }
        sb.append(Modifier.toString(this.getModifiers()));
        sb.append(" class ");
        sb.append(this.getName());
        try {
            final CtClass superclass = this.getSuperclass();
            if (superclass != null && !superclass.getName().equals("java.lang.Object")) {
                sb.append(" extends " + superclass.getName());
            }
        }
        catch (NotFoundException ex) {
            sb.append(" extends ??");
        }
        try {
            final CtClass[] interfaces = this.getInterfaces();
            if (interfaces.length > 0) {
                sb.append(" implements ");
            }
            for (int i = 0; i < interfaces.length; ++i) {
                sb.append(interfaces[i].getName());
                sb.append(", ");
            }
        }
        catch (NotFoundException ex2) {
            sb.append(" extends ??");
        }
        final CtMember.Cache members = this.getMembers();
        this.exToString(sb, " fields=", members.fieldHead(), members.lastField());
        this.exToString(sb, " constructors=", members.consHead(), members.lastCons());
        this.exToString(sb, " methods=", members.methodHead(), members.lastMethod());
    }
    
    private void exToString(final StringBuffer sb, final String s, CtMember next, final CtMember ctMember) {
        sb.append(s);
        while (next != ctMember) {
            next = next.next();
            sb.append(next);
            sb.append(", ");
        }
    }
    
    @Override
    public AccessorMaker getAccessorMaker() {
        if (this.accessors == null) {
            this.accessors = new AccessorMaker(this);
        }
        return this.accessors;
    }
    
    @Override
    public ClassFile getClassFile2() {
        return this.getClassFile3(true);
    }
    
    public ClassFile getClassFile3(final boolean b) {
        final ClassFile classfile = this.classfile;
        if (classfile != null) {
            return classfile;
        }
        if (b) {
            this.classPool.compress();
        }
        if (this.rawClassfile != null) {
            try {
                final ClassFile classFile = new ClassFile(new DataInputStream(new ByteArrayInputStream(this.rawClassfile)));
                this.rawClassfile = null;
                this.getCount = 2;
                return this.setClassFile(classFile);
            }
            catch (IOException ex) {
                throw new RuntimeException(ex.toString(), ex);
            }
        }
        InputStream openClassfile = null;
        try {
            openClassfile = this.classPool.openClassfile(this.getName());
            if (openClassfile == null) {
                throw new NotFoundException(this.getName());
            }
            openClassfile = new BufferedInputStream(openClassfile);
            final ClassFile classFile2 = new ClassFile(new DataInputStream(openClassfile));
            if (!classFile2.getName().equals(this.qualifiedName)) {
                throw new RuntimeException("cannot find " + this.qualifiedName + ": " + classFile2.getName() + " found in " + this.qualifiedName.replace('.', '/') + ".class");
            }
            return this.setClassFile(classFile2);
        }
        catch (NotFoundException ex2) {
            throw new RuntimeException(ex2.toString(), ex2);
        }
        catch (IOException ex3) {
            throw new RuntimeException(ex3.toString(), ex3);
        }
        finally {
            if (openClassfile != null) {
                try {
                    openClassfile.close();
                }
                catch (IOException ex4) {}
            }
        }
    }
    
    @Override
    final void incGetCounter() {
        ++this.getCount;
    }
    
    @Override
    void compress() {
        if (this.getCount < 2) {
            if (!this.isModified() && ClassPool.releaseUnmodifiedClassFile) {
                this.removeClassFile();
            }
            else if (this.isFrozen() && !this.wasPruned) {
                this.saveClassFile();
            }
        }
        this.getCount = 0;
    }
    
    private synchronized void saveClassFile() {
        if (this.classfile == null || this.hasMemberCache() != null) {
            return;
        }
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            this.classfile.write(dataOutputStream);
            byteArrayOutputStream.close();
            this.rawClassfile = byteArrayOutputStream.toByteArray();
            this.classfile = null;
        }
        catch (IOException ex) {}
    }
    
    private synchronized void removeClassFile() {
        if (this.classfile != null && !this.isModified() && this.hasMemberCache() == null) {
            this.classfile = null;
        }
    }
    
    private synchronized ClassFile setClassFile(final ClassFile classfile) {
        if (this.classfile == null) {
            this.classfile = classfile;
        }
        return this.classfile;
    }
    
    @Override
    public ClassPool getClassPool() {
        return this.classPool;
    }
    
    void setClassPool(final ClassPool classPool) {
        this.classPool = classPool;
    }
    
    @Override
    public URL getURL() throws NotFoundException {
        final URL find = this.classPool.find(this.getName());
        if (find == null) {
            throw new NotFoundException(this.getName());
        }
        return find;
    }
    
    @Override
    public boolean isModified() {
        return this.wasChanged;
    }
    
    @Override
    public boolean isFrozen() {
        return this.wasFrozen;
    }
    
    @Override
    public void freeze() {
        this.wasFrozen = true;
    }
    
    @Override
    void checkModify() throws RuntimeException {
        if (this.isFrozen()) {
            String s = this.getName() + " class is frozen";
            if (this.wasPruned) {
                s += " and pruned";
            }
            throw new RuntimeException(s);
        }
        this.wasChanged = true;
    }
    
    @Override
    public void defrost() {
        this.checkPruned("defrost");
        this.wasFrozen = false;
    }
    
    @Override
    public boolean subtypeOf(final CtClass ctClass) throws NotFoundException {
        final String name = ctClass.getName();
        if (this == ctClass || this.getName().equals(name)) {
            return true;
        }
        final ClassFile classFile2 = this.getClassFile2();
        final String superclass = classFile2.getSuperclass();
        if (superclass != null && superclass.equals(name)) {
            return true;
        }
        final String[] interfaces = classFile2.getInterfaces();
        final int length = interfaces.length;
        for (int i = 0; i < length; ++i) {
            if (interfaces[i].equals(name)) {
                return true;
            }
        }
        if (superclass != null && this.classPool.get(superclass).subtypeOf(ctClass)) {
            return true;
        }
        for (int j = 0; j < length; ++j) {
            if (this.classPool.get(interfaces[j]).subtypeOf(ctClass)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void setName(final String s) throws RuntimeException {
        final String name = this.getName();
        if (s.equals(name)) {
            return;
        }
        this.classPool.checkNotFrozen(s);
        final ClassFile classFile2 = this.getClassFile2();
        super.setName(s);
        classFile2.setName(s);
        this.nameReplaced();
        this.classPool.classNameChanged(name, this);
    }
    
    @Override
    public String getGenericSignature() {
        final SignatureAttribute signatureAttribute = (SignatureAttribute)this.getClassFile2().getAttribute("Signature");
        return (signatureAttribute == null) ? null : signatureAttribute.getSignature();
    }
    
    @Override
    public void setGenericSignature(final String s) {
        final ClassFile classFile = this.getClassFile();
        classFile.addAttribute(new SignatureAttribute(classFile.getConstPool(), s));
    }
    
    @Override
    public void replaceClassName(final ClassMap classMap) throws RuntimeException {
        final String name = this.getName();
        String javaName = (String)classMap.get(Descriptor.toJvmName(name));
        if (javaName != null) {
            javaName = Descriptor.toJavaName(javaName);
            this.classPool.checkNotFrozen(javaName);
        }
        super.replaceClassName(classMap);
        this.getClassFile2().renameClass(classMap);
        this.nameReplaced();
        if (javaName != null) {
            super.setName(javaName);
            this.classPool.classNameChanged(name, this);
        }
    }
    
    @Override
    public void replaceClassName(final String s, final String name) throws RuntimeException {
        if (this.getName().equals(s)) {
            this.setName(name);
        }
        else {
            super.replaceClassName(s, name);
            this.getClassFile2().renameClass(s, name);
            this.nameReplaced();
        }
    }
    
    @Override
    public boolean isInterface() {
        return Modifier.isInterface(this.getModifiers());
    }
    
    @Override
    public boolean isAnnotation() {
        return Modifier.isAnnotation(this.getModifiers());
    }
    
    @Override
    public boolean isEnum() {
        return Modifier.isEnum(this.getModifiers());
    }
    
    @Override
    public int getModifiers() {
        final ClassFile classFile2 = this.getClassFile2();
        int clear = AccessFlag.clear(classFile2.getAccessFlags(), 32);
        final int innerAccessFlags = classFile2.getInnerAccessFlags();
        if (innerAccessFlags != -1 && (innerAccessFlags & 0x8) != 0x0) {
            clear |= 0x8;
        }
        return AccessFlag.toModifier(clear);
    }
    
    @Override
    public CtClass[] getNestedClasses() throws NotFoundException {
        final ClassFile classFile2 = this.getClassFile2();
        final InnerClassesAttribute innerClassesAttribute = (InnerClassesAttribute)classFile2.getAttribute("InnerClasses");
        if (innerClassesAttribute == null) {
            return new CtClass[0];
        }
        final String string = classFile2.getName() + "$";
        final int tableLength = innerClassesAttribute.tableLength();
        final ArrayList list = new ArrayList<CtClass>(tableLength);
        for (int i = 0; i < tableLength; ++i) {
            final String innerClass = innerClassesAttribute.innerClass(i);
            if (innerClass != null && innerClass.startsWith(string) && innerClass.lastIndexOf(36) < string.length()) {
                list.add(this.classPool.get(innerClass));
            }
        }
        return list.<CtClass>toArray(new CtClass[list.size()]);
    }
    
    @Override
    public void setModifiers(int n) {
        final ClassFile classFile2 = this.getClassFile2();
        if (Modifier.isStatic(n)) {
            final int innerAccessFlags = classFile2.getInnerAccessFlags();
            if (innerAccessFlags == -1 || (innerAccessFlags & 0x8) == 0x0) {
                throw new RuntimeException("cannot change " + this.getName() + " into a static class");
            }
            n &= 0xFFFFFFF7;
        }
        this.checkModify();
        classFile2.setAccessFlags(AccessFlag.of(n));
    }
    
    @Override
    public boolean hasAnnotation(final String s) {
        final ClassFile classFile2 = this.getClassFile2();
        return hasAnnotationType(s, this.getClassPool(), (AnnotationsAttribute)classFile2.getAttribute("RuntimeInvisibleAnnotations"), (AnnotationsAttribute)classFile2.getAttribute("RuntimeVisibleAnnotations"));
    }
    
    @Deprecated
    static boolean hasAnnotationType(final Class clazz, final ClassPool classPool, final AnnotationsAttribute annotationsAttribute, final AnnotationsAttribute annotationsAttribute2) {
        return hasAnnotationType(clazz.getName(), classPool, annotationsAttribute, annotationsAttribute2);
    }
    
    static boolean hasAnnotationType(final String s, final ClassPool classPool, final AnnotationsAttribute annotationsAttribute, final AnnotationsAttribute annotationsAttribute2) {
        Annotation[] annotations;
        if (annotationsAttribute == null) {
            annotations = null;
        }
        else {
            annotations = annotationsAttribute.getAnnotations();
        }
        Annotation[] annotations2;
        if (annotationsAttribute2 == null) {
            annotations2 = null;
        }
        else {
            annotations2 = annotationsAttribute2.getAnnotations();
        }
        if (annotations != null) {
            for (int i = 0; i < annotations.length; ++i) {
                if (annotations[i].getTypeName().equals(s)) {
                    return true;
                }
            }
        }
        if (annotations2 != null) {
            for (int j = 0; j < annotations2.length; ++j) {
                if (annotations2[j].getTypeName().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public Object getAnnotation(final Class clazz) throws ClassNotFoundException {
        final ClassFile classFile2 = this.getClassFile2();
        return getAnnotationType(clazz, this.getClassPool(), (AnnotationsAttribute)classFile2.getAttribute("RuntimeInvisibleAnnotations"), (AnnotationsAttribute)classFile2.getAttribute("RuntimeVisibleAnnotations"));
    }
    
    static Object getAnnotationType(final Class clazz, final ClassPool classPool, final AnnotationsAttribute annotationsAttribute, final AnnotationsAttribute annotationsAttribute2) throws ClassNotFoundException {
        Annotation[] annotations;
        if (annotationsAttribute == null) {
            annotations = null;
        }
        else {
            annotations = annotationsAttribute.getAnnotations();
        }
        Annotation[] annotations2;
        if (annotationsAttribute2 == null) {
            annotations2 = null;
        }
        else {
            annotations2 = annotationsAttribute2.getAnnotations();
        }
        final String name = clazz.getName();
        if (annotations != null) {
            for (int i = 0; i < annotations.length; ++i) {
                if (annotations[i].getTypeName().equals(name)) {
                    return toAnnoType(annotations[i], classPool);
                }
            }
        }
        if (annotations2 != null) {
            for (int j = 0; j < annotations2.length; ++j) {
                if (annotations2[j].getTypeName().equals(name)) {
                    return toAnnoType(annotations2[j], classPool);
                }
            }
        }
        return null;
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
            throw new RuntimeException("Unexpected exception ", ex);
        }
    }
    
    private Object[] getAnnotations(final boolean b) throws ClassNotFoundException {
        final ClassFile classFile2 = this.getClassFile2();
        return toAnnotationType(b, this.getClassPool(), (AnnotationsAttribute)classFile2.getAttribute("RuntimeInvisibleAnnotations"), (AnnotationsAttribute)classFile2.getAttribute("RuntimeVisibleAnnotations"));
    }
    
    static Object[] toAnnotationType(final boolean p0, final ClassPool p1, final AnnotationsAttribute p2, final AnnotationsAttribute p3) throws ClassNotFoundException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       13
        //     4: aconst_null    
        //     5: astore          4
        //     7: iconst_0       
        //     8: istore          6
        //    10: goto            24
        //    13: aload_2        
        //    14: invokevirtual   javassist/bytecode/AnnotationsAttribute.getAnnotations:()[Ljavassist/bytecode/annotation/Annotation;
        //    17: astore          4
        //    19: aload           4
        //    21: arraylength    
        //    22: istore          6
        //    24: aload_3        
        //    25: ifnonnull       37
        //    28: aconst_null    
        //    29: astore          5
        //    31: iconst_0       
        //    32: istore          7
        //    34: goto            48
        //    37: aload_3        
        //    38: invokevirtual   javassist/bytecode/AnnotationsAttribute.getAnnotations:()[Ljavassist/bytecode/annotation/Annotation;
        //    41: astore          5
        //    43: aload           5
        //    45: arraylength    
        //    46: istore          7
        //    48: iload           6
        //    50: iload           7
        //    52: iadd           
        //    53: anewarray       Ljava/lang/Object;
        //    56: astore          8
        //    58: iconst_0       
        //    59: istore          9
        //    61: iload           9
        //    63: iload           6
        //    65: if_icmpge       88
        //    68: aload           8
        //    70: iload           9
        //    72: aload           4
        //    74: iload           9
        //    76: aaload         
        //    77: aload_1        
        //    78: invokestatic    javassist/CtClassType.toAnnoType:(Ljavassist/bytecode/annotation/Annotation;Ljavassist/ClassPool;)Ljava/lang/Object;
        //    81: aastore        
        //    82: iinc            9, 1
        //    85: goto            61
        //    88: iconst_0       
        //    89: istore          9
        //    91: iload           9
        //    93: iload           7
        //    95: if_icmpge       121
        //    98: aload           8
        //   100: iload           9
        //   102: iload           6
        //   104: iadd           
        //   105: aload           5
        //   107: iload           9
        //   109: aaload         
        //   110: aload_1        
        //   111: invokestatic    javassist/CtClassType.toAnnoType:(Ljavassist/bytecode/annotation/Annotation;Ljavassist/ClassPool;)Ljava/lang/Object;
        //   114: aastore        
        //   115: iinc            9, 1
        //   118: goto            91
        //   121: aload           8
        //   123: areturn        
        //   124: new             Ljava/util/ArrayList;
        //   127: dup            
        //   128: invokespecial   java/util/ArrayList.<init>:()V
        //   131: astore          8
        //   133: iconst_0       
        //   134: istore          9
        //   136: iload           9
        //   138: iload           6
        //   140: if_icmpge       169
        //   143: aload           8
        //   145: aload           4
        //   147: iload           9
        //   149: aaload         
        //   150: aload_1        
        //   151: invokestatic    javassist/CtClassType.toAnnoType:(Ljavassist/bytecode/annotation/Annotation;Ljavassist/ClassPool;)Ljava/lang/Object;
        //   154: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   157: pop            
        //   158: goto            163
        //   161: astore          10
        //   163: iinc            9, 1
        //   166: goto            136
        //   169: iconst_0       
        //   170: istore          9
        //   172: iload           9
        //   174: iload           7
        //   176: if_icmpge       205
        //   179: aload           8
        //   181: aload           5
        //   183: iload           9
        //   185: aaload         
        //   186: aload_1        
        //   187: invokestatic    javassist/CtClassType.toAnnoType:(Ljavassist/bytecode/annotation/Annotation;Ljavassist/ClassPool;)Ljava/lang/Object;
        //   190: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   193: pop            
        //   194: goto            199
        //   197: astore          10
        //   199: iinc            9, 1
        //   202: goto            172
        //   205: aload           8
        //   207: invokevirtual   java/util/ArrayList.toArray:()[Ljava/lang/Object;
        //   210: areturn        
        //    Exceptions:
        //  throws java.lang.ClassNotFoundException
        //    StackMapTable: 00 11 0D FE 00 0A 07 02 04 00 01 0C FF 00 0A 00 08 01 07 00 2F 07 01 F2 07 01 F2 07 02 04 07 02 04 01 01 00 00 FD 00 0C 07 02 22 01 1A 02 1D F9 00 02 FD 00 0B 07 01 C3 01 58 07 02 0D 01 05 02 58 07 02 0D 01 05
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  143    158    161    163    Ljava/lang/ClassNotFoundException;
        //  179    194    197    199    Ljava/lang/ClassNotFoundException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static Object[][] toAnnotationType(final boolean p0, final ClassPool p1, final ParameterAnnotationsAttribute p2, final ParameterAnnotationsAttribute p3, final MethodInfo p4) throws ClassNotFoundException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore          5
        //     3: aload_2        
        //     4: ifnull          16
        //     7: aload_2        
        //     8: invokevirtual   javassist/bytecode/ParameterAnnotationsAttribute.numParameters:()I
        //    11: istore          5
        //    13: goto            39
        //    16: aload_3        
        //    17: ifnull          29
        //    20: aload_3        
        //    21: invokevirtual   javassist/bytecode/ParameterAnnotationsAttribute.numParameters:()I
        //    24: istore          5
        //    26: goto            39
        //    29: aload           4
        //    31: invokevirtual   javassist/bytecode/MethodInfo.getDescriptor:()Ljava/lang/String;
        //    34: invokestatic    javassist/bytecode/Descriptor.numOfParameters:(Ljava/lang/String;)I
        //    37: istore          5
        //    39: iload           5
        //    41: anewarray       [Ljava/lang/Object;
        //    44: astore          6
        //    46: iconst_0       
        //    47: istore          7
        //    49: iload           7
        //    51: iload           5
        //    53: if_icmpge       292
        //    56: aload_2        
        //    57: ifnonnull       69
        //    60: aconst_null    
        //    61: astore          8
        //    63: iconst_0       
        //    64: istore          10
        //    66: goto            83
        //    69: aload_2        
        //    70: invokevirtual   javassist/bytecode/ParameterAnnotationsAttribute.getAnnotations:()[[Ljavassist/bytecode/annotation/Annotation;
        //    73: iload           7
        //    75: aaload         
        //    76: astore          8
        //    78: aload           8
        //    80: arraylength    
        //    81: istore          10
        //    83: aload_3        
        //    84: ifnonnull       96
        //    87: aconst_null    
        //    88: astore          9
        //    90: iconst_0       
        //    91: istore          11
        //    93: goto            110
        //    96: aload_3        
        //    97: invokevirtual   javassist/bytecode/ParameterAnnotationsAttribute.getAnnotations:()[[Ljavassist/bytecode/annotation/Annotation;
        //   100: iload           7
        //   102: aaload         
        //   103: astore          9
        //   105: aload           9
        //   107: arraylength    
        //   108: istore          11
        //   110: aload           6
        //   112: iload           7
        //   114: iload           10
        //   116: iload           11
        //   118: iadd           
        //   119: anewarray       Ljava/lang/Object;
        //   122: aastore        
        //   123: iconst_0       
        //   124: istore          12
        //   126: iload           12
        //   128: iload           10
        //   130: if_icmpge       156
        //   133: aload           6
        //   135: iload           7
        //   137: aaload         
        //   138: iload           12
        //   140: aload           8
        //   142: iload           12
        //   144: aaload         
        //   145: aload_1        
        //   146: invokestatic    javassist/CtClassType.toAnnoType:(Ljavassist/bytecode/annotation/Annotation;Ljavassist/ClassPool;)Ljava/lang/Object;
        //   149: aastore        
        //   150: iinc            12, 1
        //   153: goto            126
        //   156: iconst_0       
        //   157: istore          12
        //   159: iload           12
        //   161: iload           11
        //   163: if_icmpge       192
        //   166: aload           6
        //   168: iload           7
        //   170: aaload         
        //   171: iload           12
        //   173: iload           10
        //   175: iadd           
        //   176: aload           9
        //   178: iload           12
        //   180: aaload         
        //   181: aload_1        
        //   182: invokestatic    javassist/CtClassType.toAnnoType:(Ljavassist/bytecode/annotation/Annotation;Ljavassist/ClassPool;)Ljava/lang/Object;
        //   185: aastore        
        //   186: iinc            12, 1
        //   189: goto            159
        //   192: goto            286
        //   195: new             Ljava/util/ArrayList;
        //   198: dup            
        //   199: invokespecial   java/util/ArrayList.<init>:()V
        //   202: astore          12
        //   204: iconst_0       
        //   205: istore          13
        //   207: iload           13
        //   209: iload           10
        //   211: if_icmpge       240
        //   214: aload           12
        //   216: aload           8
        //   218: iload           13
        //   220: aaload         
        //   221: aload_1        
        //   222: invokestatic    javassist/CtClassType.toAnnoType:(Ljavassist/bytecode/annotation/Annotation;Ljavassist/ClassPool;)Ljava/lang/Object;
        //   225: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   228: pop            
        //   229: goto            234
        //   232: astore          14
        //   234: iinc            13, 1
        //   237: goto            207
        //   240: iconst_0       
        //   241: istore          13
        //   243: iload           13
        //   245: iload           11
        //   247: if_icmpge       276
        //   250: aload           12
        //   252: aload           9
        //   254: iload           13
        //   256: aaload         
        //   257: aload_1        
        //   258: invokestatic    javassist/CtClassType.toAnnoType:(Ljavassist/bytecode/annotation/Annotation;Ljavassist/ClassPool;)Ljava/lang/Object;
        //   261: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   264: pop            
        //   265: goto            270
        //   268: astore          14
        //   270: iinc            13, 1
        //   273: goto            243
        //   276: aload           6
        //   278: iload           7
        //   280: aload           12
        //   282: invokevirtual   java/util/ArrayList.toArray:()[Ljava/lang/Object;
        //   285: aastore        
        //   286: iinc            7, 1
        //   289: goto            49
        //   292: aload           6
        //   294: areturn        
        //    Exceptions:
        //  throws java.lang.ClassNotFoundException
        //    StackMapTable: 00 17 FC 00 10 01 0C 09 FD 00 09 07 02 36 01 13 FE 00 0D 07 02 04 00 01 0C FF 00 0D 00 0C 01 07 00 2F 07 02 28 07 02 28 07 02 2D 01 07 02 36 01 07 02 04 07 02 04 01 01 00 00 FC 00 0F 01 1D 02 20 FA 00 02 FD 00 0B 07 01 C3 01 58 07 02 0D 01 05 02 58 07 02 0D 01 05 F9 00 09 FF 00 05 00 08 01 07 00 2F 07 02 28 07 02 28 07 02 2D 01 07 02 36 01 00 00
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  214    229    232    234    Ljava/lang/ClassNotFoundException;
        //  250    265    268    270    Ljava/lang/ClassNotFoundException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static Object toAnnoType(final Annotation annotation, final ClassPool classPool) throws ClassNotFoundException {
        try {
            return annotation.toAnnotationType(classPool.getClassLoader(), classPool);
        }
        catch (ClassNotFoundException ex) {
            final ClassLoader classLoader = classPool.getClass().getClassLoader();
            try {
                return annotation.toAnnotationType(classLoader, classPool);
            }
            catch (ClassNotFoundException ex2) {
                try {
                    final Class class1 = classPool.get(annotation.getTypeName()).toClass();
                    return AnnotationImpl.make(class1.getClassLoader(), class1, classPool, annotation);
                }
                catch (Throwable t) {
                    throw new ClassNotFoundException(annotation.getTypeName());
                }
            }
        }
    }
    
    @Override
    public boolean subclassOf(final CtClass ctClass) {
        if (ctClass == null) {
            return false;
        }
        final String name = ctClass.getName();
        CtClass superclass = this;
        try {
            while (superclass != null) {
                if (superclass.getName().equals(name)) {
                    return true;
                }
                superclass = superclass.getSuperclass();
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    @Override
    public CtClass getSuperclass() throws NotFoundException {
        final String superclass = this.getClassFile2().getSuperclass();
        if (superclass == null) {
            return null;
        }
        return this.classPool.get(superclass);
    }
    
    @Override
    public void setSuperclass(final CtClass ctClass) throws CannotCompileException {
        this.checkModify();
        if (this.isInterface()) {
            this.addInterface(ctClass);
        }
        else {
            this.getClassFile2().setSuperclass(ctClass.getName());
        }
    }
    
    @Override
    public CtClass[] getInterfaces() throws NotFoundException {
        final String[] interfaces = this.getClassFile2().getInterfaces();
        final int length = interfaces.length;
        final CtClass[] array = new CtClass[length];
        for (int i = 0; i < length; ++i) {
            array[i] = this.classPool.get(interfaces[i]);
        }
        return array;
    }
    
    @Override
    public void setInterfaces(final CtClass[] array) {
        this.checkModify();
        String[] interfaces;
        if (array == null) {
            interfaces = new String[0];
        }
        else {
            final int length = array.length;
            interfaces = new String[length];
            for (int i = 0; i < length; ++i) {
                interfaces[i] = array[i].getName();
            }
        }
        this.getClassFile2().setInterfaces(interfaces);
    }
    
    @Override
    public void addInterface(final CtClass ctClass) {
        this.checkModify();
        if (ctClass != null) {
            this.getClassFile2().addInterface(ctClass.getName());
        }
    }
    
    @Override
    public CtClass getDeclaringClass() throws NotFoundException {
        final ClassFile classFile2 = this.getClassFile2();
        final InnerClassesAttribute innerClassesAttribute = (InnerClassesAttribute)classFile2.getAttribute("InnerClasses");
        if (innerClassesAttribute == null) {
            return null;
        }
        final String name = this.getName();
        for (int tableLength = innerClassesAttribute.tableLength(), i = 0; i < tableLength; ++i) {
            if (name.equals(innerClassesAttribute.innerClass(i))) {
                final String outerClass = innerClassesAttribute.outerClass(i);
                if (outerClass != null) {
                    return this.classPool.get(outerClass);
                }
                final EnclosingMethodAttribute enclosingMethodAttribute = (EnclosingMethodAttribute)classFile2.getAttribute("EnclosingMethod");
                if (enclosingMethodAttribute != null) {
                    return this.classPool.get(enclosingMethodAttribute.className());
                }
            }
        }
        return null;
    }
    
    @Override
    public CtBehavior getEnclosingBehavior() throws NotFoundException {
        final EnclosingMethodAttribute enclosingMethodAttribute = (EnclosingMethodAttribute)this.getClassFile2().getAttribute("EnclosingMethod");
        if (enclosingMethodAttribute == null) {
            return null;
        }
        final CtClass value = this.classPool.get(enclosingMethodAttribute.className());
        final String methodName = enclosingMethodAttribute.methodName();
        if ("<init>".equals(methodName)) {
            return value.getConstructor(enclosingMethodAttribute.methodDescriptor());
        }
        if ("<clinit>".equals(methodName)) {
            return value.getClassInitializer();
        }
        return value.getMethod(methodName, enclosingMethodAttribute.methodDescriptor());
    }
    
    @Override
    public CtClass makeNestedClass(final String s, final boolean b) {
        throw new RuntimeException("sorry, only nested static class is supported");
    }
    
    private void nameReplaced() {
        final CtMember.Cache hasMemberCache = this.hasMemberCache();
        if (hasMemberCache != null) {
            CtMember ctMember = hasMemberCache.methodHead();
            while (ctMember != hasMemberCache.lastMethod()) {
                ctMember = ctMember.next();
                ctMember.nameReplaced();
            }
        }
    }
    
    protected CtMember.Cache hasMemberCache() {
        final WeakReference memberCache = this.memberCache;
        if (memberCache != null) {
            return memberCache.get();
        }
        return null;
    }
    
    protected synchronized CtMember.Cache getMembers() {
        CtMember.Cache cache;
        if (this.memberCache == null || (cache = (CtMember.Cache)this.memberCache.get()) == null) {
            cache = new CtMember.Cache(this);
            this.makeFieldCache(cache);
            this.makeBehaviorCache(cache);
            this.memberCache = new WeakReference(cache);
        }
        return cache;
    }
    
    private void makeFieldCache(final CtMember.Cache cache) {
        final List fields = this.getClassFile3(false).getFields();
        for (int size = fields.size(), i = 0; i < size; ++i) {
            cache.addField(new CtField(fields.get(i), this));
        }
    }
    
    private void makeBehaviorCache(final CtMember.Cache cache) {
        final List methods = this.getClassFile3(false).getMethods();
        for (int size = methods.size(), i = 0; i < size; ++i) {
            final MethodInfo methodInfo = methods.get(i);
            if (methodInfo.isMethod()) {
                cache.addMethod(new CtMethod(methodInfo, this));
            }
            else {
                cache.addConstructor(new CtConstructor(methodInfo, this));
            }
        }
    }
    
    @Override
    public CtField[] getFields() {
        final ArrayList list = new ArrayList();
        getFields(list, this);
        return list.<CtField>toArray(new CtField[list.size()]);
    }
    
    private static void getFields(final ArrayList list, final CtClass ctClass) {
        if (ctClass == null) {
            return;
        }
        try {
            getFields(list, ctClass.getSuperclass());
        }
        catch (NotFoundException ex) {}
        try {
            final CtClass[] interfaces = ctClass.getInterfaces();
            for (int length = interfaces.length, i = 0; i < length; ++i) {
                getFields(list, interfaces[i]);
            }
        }
        catch (NotFoundException ex2) {}
        final CtMember.Cache members = ((CtClassType)ctClass).getMembers();
        CtMember ctMember = members.fieldHead();
        while (ctMember != members.lastField()) {
            ctMember = ctMember.next();
            if (!Modifier.isPrivate(ctMember.getModifiers())) {
                list.add(ctMember);
            }
        }
    }
    
    @Override
    public CtField getField(final String s, final String s2) throws NotFoundException {
        return this.checkGetField(this.getField2(s, s2), s, s2);
    }
    
    private CtField checkGetField(final CtField ctField, final String s, final String s2) throws NotFoundException {
        if (ctField == null) {
            String s3 = "field: " + s;
            if (s2 != null) {
                s3 = s3 + " type " + s2;
            }
            throw new NotFoundException(s3 + " in " + this.getName());
        }
        return ctField;
    }
    
    @Override
    CtField getField2(final String s, final String s2) {
        final CtField declaredField2 = this.getDeclaredField2(s, s2);
        if (declaredField2 != null) {
            return declaredField2;
        }
        try {
            final CtClass[] interfaces = this.getInterfaces();
            for (int length = interfaces.length, i = 0; i < length; ++i) {
                final CtField field2 = interfaces[i].getField2(s, s2);
                if (field2 != null) {
                    return field2;
                }
            }
            final CtClass superclass = this.getSuperclass();
            if (superclass != null) {
                return superclass.getField2(s, s2);
            }
        }
        catch (NotFoundException ex) {}
        return null;
    }
    
    @Override
    public CtField[] getDeclaredFields() {
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.fieldHead();
        final CtMember lastField = members.lastField();
        final CtField[] array = new CtField[CtMember.Cache.count(ctMember, lastField)];
        for (int n = 0; ctMember != lastField; ctMember = ctMember.next(), array[n++] = (CtField)ctMember) {}
        return array;
    }
    
    @Override
    public CtField getDeclaredField(final String s) throws NotFoundException {
        return this.getDeclaredField(s, null);
    }
    
    @Override
    public CtField getDeclaredField(final String s, final String s2) throws NotFoundException {
        return this.checkGetField(this.getDeclaredField2(s, s2), s, s2);
    }
    
    private CtField getDeclaredField2(final String s, final String s2) {
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.fieldHead();
        while (ctMember != members.lastField()) {
            ctMember = ctMember.next();
            if (ctMember.getName().equals(s) && (s2 == null || s2.equals(ctMember.getSignature()))) {
                return (CtField)ctMember;
            }
        }
        return null;
    }
    
    @Override
    public CtBehavior[] getDeclaredBehaviors() {
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.consHead();
        final CtMember lastCons = members.lastCons();
        final int count = CtMember.Cache.count(ctMember, lastCons);
        CtMember ctMember2 = members.methodHead();
        final CtMember lastMethod = members.lastMethod();
        CtBehavior[] array;
        int n;
        for (array = new CtBehavior[count + CtMember.Cache.count(ctMember2, lastMethod)], n = 0; ctMember != lastCons; ctMember = ctMember.next(), array[n++] = (CtBehavior)ctMember) {}
        while (ctMember2 != lastMethod) {
            ctMember2 = ctMember2.next();
            array[n++] = (CtBehavior)ctMember2;
        }
        return array;
    }
    
    @Override
    public CtConstructor[] getConstructors() {
        final CtMember.Cache members = this.getMembers();
        final CtMember consHead = members.consHead();
        final CtMember lastCons = members.lastCons();
        int n = 0;
        CtMember next = consHead;
        while (next != lastCons) {
            next = next.next();
            if (isPubCons((CtConstructor)next)) {
                ++n;
            }
        }
        final CtConstructor[] array = new CtConstructor[n];
        int n2 = 0;
        CtMember next2 = consHead;
        while (next2 != lastCons) {
            next2 = next2.next();
            final CtConstructor ctConstructor = (CtConstructor)next2;
            if (isPubCons(ctConstructor)) {
                array[n2++] = ctConstructor;
            }
        }
        return array;
    }
    
    private static boolean isPubCons(final CtConstructor ctConstructor) {
        return !Modifier.isPrivate(ctConstructor.getModifiers()) && ctConstructor.isConstructor();
    }
    
    @Override
    public CtConstructor getConstructor(final String s) throws NotFoundException {
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.consHead();
        while (ctMember != members.lastCons()) {
            ctMember = ctMember.next();
            final CtConstructor ctConstructor = (CtConstructor)ctMember;
            if (ctConstructor.getMethodInfo2().getDescriptor().equals(s) && ctConstructor.isConstructor()) {
                return ctConstructor;
            }
        }
        return super.getConstructor(s);
    }
    
    @Override
    public CtConstructor[] getDeclaredConstructors() {
        final CtMember.Cache members = this.getMembers();
        final CtMember consHead = members.consHead();
        final CtMember lastCons = members.lastCons();
        int n = 0;
        CtMember next = consHead;
        while (next != lastCons) {
            next = next.next();
            if (((CtConstructor)next).isConstructor()) {
                ++n;
            }
        }
        final CtConstructor[] array = new CtConstructor[n];
        int n2 = 0;
        CtMember next2 = consHead;
        while (next2 != lastCons) {
            next2 = next2.next();
            final CtConstructor ctConstructor = (CtConstructor)next2;
            if (ctConstructor.isConstructor()) {
                array[n2++] = ctConstructor;
            }
        }
        return array;
    }
    
    @Override
    public CtConstructor getClassInitializer() {
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.consHead();
        while (ctMember != members.lastCons()) {
            ctMember = ctMember.next();
            final CtConstructor ctConstructor = (CtConstructor)ctMember;
            if (ctConstructor.isClassInitializer()) {
                return ctConstructor;
            }
        }
        return null;
    }
    
    @Override
    public CtMethod[] getMethods() {
        final HashMap hashMap = new HashMap();
        getMethods0(hashMap, this);
        return (CtMethod[])hashMap.values().toArray(new CtMethod[hashMap.size()]);
    }
    
    private static void getMethods0(final HashMap hashMap, final CtClass ctClass) {
        try {
            final CtClass[] interfaces = ctClass.getInterfaces();
            for (int length = interfaces.length, i = 0; i < length; ++i) {
                getMethods0(hashMap, interfaces[i]);
            }
        }
        catch (NotFoundException ex) {}
        try {
            final CtClass superclass = ctClass.getSuperclass();
            if (superclass != null) {
                getMethods0(hashMap, superclass);
            }
        }
        catch (NotFoundException ex2) {}
        if (ctClass instanceof CtClassType) {
            final CtMember.Cache members = ((CtClassType)ctClass).getMembers();
            CtMember ctMember = members.methodHead();
            while (ctMember != members.lastMethod()) {
                ctMember = ctMember.next();
                if (!Modifier.isPrivate(ctMember.getModifiers())) {
                    hashMap.put(((CtMethod)ctMember).getStringRep(), ctMember);
                }
            }
        }
    }
    
    @Override
    public CtMethod getMethod(final String s, final String s2) throws NotFoundException {
        final CtMethod method0 = getMethod0(this, s, s2);
        if (method0 != null) {
            return method0;
        }
        throw new NotFoundException(s + "(..) is not found in " + this.getName());
    }
    
    private static CtMethod getMethod0(final CtClass ctClass, final String s, final String s2) {
        if (ctClass instanceof CtClassType) {
            final CtMember.Cache members = ((CtClassType)ctClass).getMembers();
            CtMember ctMember = members.methodHead();
            while (ctMember != members.lastMethod()) {
                ctMember = ctMember.next();
                if (ctMember.getName().equals(s) && ((CtMethod)ctMember).getMethodInfo2().getDescriptor().equals(s2)) {
                    return (CtMethod)ctMember;
                }
            }
        }
        try {
            final CtClass superclass = ctClass.getSuperclass();
            if (superclass != null) {
                final CtMethod method0 = getMethod0(superclass, s, s2);
                if (method0 != null) {
                    return method0;
                }
            }
        }
        catch (NotFoundException ex) {}
        try {
            final CtClass[] interfaces = ctClass.getInterfaces();
            for (int length = interfaces.length, i = 0; i < length; ++i) {
                final CtMethod method2 = getMethod0(interfaces[i], s, s2);
                if (method2 != null) {
                    return method2;
                }
            }
        }
        catch (NotFoundException ex2) {}
        return null;
    }
    
    @Override
    public CtMethod[] getDeclaredMethods() {
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.methodHead();
        final CtMember lastMethod = members.lastMethod();
        final CtMethod[] array = new CtMethod[CtMember.Cache.count(ctMember, lastMethod)];
        for (int n = 0; ctMember != lastMethod; ctMember = ctMember.next(), array[n++] = (CtMethod)ctMember) {}
        return array;
    }
    
    @Override
    public CtMethod[] getDeclaredMethods(final String s) throws NotFoundException {
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.methodHead();
        final CtMember lastMethod = members.lastMethod();
        final ArrayList<CtMethod> list = new ArrayList<CtMethod>();
        while (ctMember != lastMethod) {
            ctMember = ctMember.next();
            if (ctMember.getName().equals(s)) {
                list.add((CtMethod)ctMember);
            }
        }
        return list.<CtMethod>toArray(new CtMethod[list.size()]);
    }
    
    @Override
    public CtMethod getDeclaredMethod(final String s) throws NotFoundException {
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.methodHead();
        while (ctMember != members.lastMethod()) {
            ctMember = ctMember.next();
            if (ctMember.getName().equals(s)) {
                return (CtMethod)ctMember;
            }
        }
        throw new NotFoundException(s + "(..) is not found in " + this.getName());
    }
    
    @Override
    public CtMethod getDeclaredMethod(final String s, final CtClass[] array) throws NotFoundException {
        final String ofParameters = Descriptor.ofParameters(array);
        final CtMember.Cache members = this.getMembers();
        CtMember ctMember = members.methodHead();
        while (ctMember != members.lastMethod()) {
            ctMember = ctMember.next();
            if (ctMember.getName().equals(s) && ((CtMethod)ctMember).getMethodInfo2().getDescriptor().startsWith(ofParameters)) {
                return (CtMethod)ctMember;
            }
        }
        throw new NotFoundException(s + "(..) is not found in " + this.getName());
    }
    
    @Override
    public void addField(final CtField ctField, final String s) throws CannotCompileException {
        this.addField(ctField, CtField.Initializer.byExpr(s));
    }
    
    @Override
    public void addField(final CtField ctField, CtField.Initializer init) throws CannotCompileException {
        this.checkModify();
        if (ctField.getDeclaringClass() != this) {
            throw new CannotCompileException("cannot add");
        }
        if (init == null) {
            init = ctField.getInit();
        }
        if (init != null) {
            init.check(ctField.getSignature());
            final int modifiers = ctField.getModifiers();
            if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)) {
                try {
                    final ConstPool constPool = this.getClassFile2().getConstPool();
                    final int constantValue = init.getConstantValue(constPool, ctField.getType());
                    if (constantValue != 0) {
                        ctField.getFieldInfo2().addAttribute(new ConstantAttribute(constPool, constantValue));
                        init = null;
                    }
                }
                catch (NotFoundException ex) {}
            }
        }
        this.getMembers().addField(ctField);
        this.getClassFile2().addField(ctField.getFieldInfo2());
        if (init != null) {
            final FieldInitLink fieldInitLink = new FieldInitLink(ctField, init);
            FieldInitLink fieldInitLink2 = this.fieldInitializers;
            if (fieldInitLink2 == null) {
                this.fieldInitializers = fieldInitLink;
            }
            else {
                while (fieldInitLink2.next != null) {
                    fieldInitLink2 = fieldInitLink2.next;
                }
                fieldInitLink2.next = fieldInitLink;
            }
        }
    }
    
    @Override
    public void removeField(final CtField ctField) throws NotFoundException {
        this.checkModify();
        if (this.getClassFile2().getFields().remove(ctField.getFieldInfo2())) {
            this.getMembers().remove(ctField);
            this.gcConstPool = true;
            return;
        }
        throw new NotFoundException(ctField.toString());
    }
    
    @Override
    public CtConstructor makeClassInitializer() throws CannotCompileException {
        final CtConstructor classInitializer = this.getClassInitializer();
        if (classInitializer != null) {
            return classInitializer;
        }
        this.checkModify();
        final ClassFile classFile2 = this.getClassFile2();
        this.modifyClassConstructor(classFile2, new Bytecode(classFile2.getConstPool(), 0, 0), 0, 0);
        return this.getClassInitializer();
    }
    
    @Override
    public void addConstructor(final CtConstructor ctConstructor) throws CannotCompileException {
        this.checkModify();
        if (ctConstructor.getDeclaringClass() != this) {
            throw new CannotCompileException("cannot add");
        }
        this.getMembers().addConstructor(ctConstructor);
        this.getClassFile2().addMethod(ctConstructor.getMethodInfo2());
    }
    
    @Override
    public void removeConstructor(final CtConstructor ctConstructor) throws NotFoundException {
        this.checkModify();
        if (this.getClassFile2().getMethods().remove(ctConstructor.getMethodInfo2())) {
            this.getMembers().remove(ctConstructor);
            this.gcConstPool = true;
            return;
        }
        throw new NotFoundException(ctConstructor.toString());
    }
    
    @Override
    public void addMethod(final CtMethod ctMethod) throws CannotCompileException {
        this.checkModify();
        if (ctMethod.getDeclaringClass() != this) {
            throw new CannotCompileException("bad declaring class");
        }
        final int modifiers = ctMethod.getModifiers();
        if ((this.getModifiers() & 0x200) != 0x0) {
            if (Modifier.isProtected(modifiers) || Modifier.isPrivate(modifiers)) {
                throw new CannotCompileException("an interface method must be public: " + ctMethod.toString());
            }
            ctMethod.setModifiers(modifiers | 0x1);
        }
        this.getMembers().addMethod(ctMethod);
        this.getClassFile2().addMethod(ctMethod.getMethodInfo2());
        if ((modifiers & 0x400) != 0x0) {
            this.setModifiers(this.getModifiers() | 0x400);
        }
    }
    
    @Override
    public void removeMethod(final CtMethod ctMethod) throws NotFoundException {
        this.checkModify();
        if (this.getClassFile2().getMethods().remove(ctMethod.getMethodInfo2())) {
            this.getMembers().remove(ctMethod);
            this.gcConstPool = true;
            return;
        }
        throw new NotFoundException(ctMethod.toString());
    }
    
    @Override
    public byte[] getAttribute(final String s) {
        final AttributeInfo attribute = this.getClassFile2().getAttribute(s);
        if (attribute == null) {
            return null;
        }
        return attribute.get();
    }
    
    @Override
    public void setAttribute(final String s, final byte[] array) {
        this.checkModify();
        final ClassFile classFile2 = this.getClassFile2();
        classFile2.addAttribute(new AttributeInfo(classFile2.getConstPool(), s, array));
    }
    
    @Override
    public void instrument(final CodeConverter codeConverter) throws CannotCompileException {
        this.checkModify();
        final ClassFile classFile2 = this.getClassFile2();
        final ConstPool constPool = classFile2.getConstPool();
        final List methods = classFile2.getMethods();
        for (int size = methods.size(), i = 0; i < size; ++i) {
            codeConverter.doit(this, methods.get(i), constPool);
        }
    }
    
    @Override
    public void instrument(final ExprEditor exprEditor) throws CannotCompileException {
        this.checkModify();
        final List methods = this.getClassFile2().getMethods();
        for (int size = methods.size(), i = 0; i < size; ++i) {
            exprEditor.doit(this, methods.get(i));
        }
    }
    
    @Override
    public void prune() {
        if (this.wasPruned) {
            return;
        }
        final boolean b = true;
        this.wasFrozen = b;
        this.wasPruned = b;
        this.getClassFile2().prune();
    }
    
    @Override
    public void rebuildClassFile() {
        this.gcConstPool = true;
    }
    
    @Override
    public void toBytecode(final DataOutputStream dataOutputStream) throws CannotCompileException, IOException {
        try {
            if (this.isModified()) {
                this.checkPruned("toBytecode");
                final ClassFile classFile2 = this.getClassFile2();
                if (this.gcConstPool) {
                    classFile2.compact();
                    this.gcConstPool = false;
                }
                this.modifyClassConstructor(classFile2);
                this.modifyConstructors(classFile2);
                if (CtClassType.debugDump != null) {
                    this.dumpClassFile(classFile2);
                }
                classFile2.write(dataOutputStream);
                dataOutputStream.flush();
                this.fieldInitializers = null;
                if (this.doPruning) {
                    classFile2.prune();
                    this.wasPruned = true;
                }
            }
            else {
                this.classPool.writeClassfile(this.getName(), dataOutputStream);
            }
            this.getCount = 0;
            this.wasFrozen = true;
        }
        catch (NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
        catch (IOException ex2) {
            throw new CannotCompileException(ex2);
        }
    }
    
    private void dumpClassFile(final ClassFile classFile) throws IOException {
        final DataOutputStream fileOutput = this.makeFileOutput(CtClassType.debugDump);
        try {
            classFile.write(fileOutput);
        }
        finally {
            fileOutput.close();
        }
    }
    
    private void checkPruned(final String s) {
        if (this.wasPruned) {
            throw new RuntimeException(s + "(): " + this.getName() + " was pruned.");
        }
    }
    
    @Override
    public boolean stopPruning(final boolean b) {
        final boolean b2 = !this.doPruning;
        this.doPruning = true;
        return b2;
    }
    
    private void modifyClassConstructor(final ClassFile classFile) throws CannotCompileException, NotFoundException {
        if (this.fieldInitializers == null) {
            return;
        }
        final Bytecode bytecode = new Bytecode(classFile.getConstPool(), 0, 0);
        final Javac javac = new Javac(bytecode, this);
        int n = 0;
        boolean b = false;
        for (FieldInitLink fieldInitLink = this.fieldInitializers; fieldInitLink != null; fieldInitLink = fieldInitLink.next) {
            final CtField field = fieldInitLink.field;
            if (Modifier.isStatic(field.getModifiers())) {
                b = true;
                final int compileIfStatic = fieldInitLink.init.compileIfStatic(field.getType(), field.getName(), bytecode, javac);
                if (n < compileIfStatic) {
                    n = compileIfStatic;
                }
            }
        }
        if (b) {
            this.modifyClassConstructor(classFile, bytecode, n, 0);
        }
    }
    
    private void modifyClassConstructor(final ClassFile classFile, final Bytecode bytecode, final int n, final int n2) throws CannotCompileException {
        MethodInfo staticInitializer = classFile.getStaticInitializer();
        if (staticInitializer == null) {
            bytecode.add(177);
            bytecode.setMaxStack(n);
            bytecode.setMaxLocals(n2);
            staticInitializer = new MethodInfo(classFile.getConstPool(), "<clinit>", "()V");
            staticInitializer.setAccessFlags(8);
            staticInitializer.setCodeAttribute(bytecode.toCodeAttribute());
            classFile.addMethod(staticInitializer);
            final CtMember.Cache hasMemberCache = this.hasMemberCache();
            if (hasMemberCache != null) {
                hasMemberCache.addConstructor(new CtConstructor(staticInitializer, this));
            }
        }
        else {
            final CodeAttribute codeAttribute = staticInitializer.getCodeAttribute();
            if (codeAttribute == null) {
                throw new CannotCompileException("empty <clinit>");
            }
            try {
                final CodeIterator iterator = codeAttribute.iterator();
                iterator.insert(bytecode.getExceptionTable(), iterator.insertEx(bytecode.get()));
                if (codeAttribute.getMaxStack() < n) {
                    codeAttribute.setMaxStack(n);
                }
                if (codeAttribute.getMaxLocals() < n2) {
                    codeAttribute.setMaxLocals(n2);
                }
            }
            catch (BadBytecode badBytecode) {
                throw new CannotCompileException(badBytecode);
            }
        }
        try {
            staticInitializer.rebuildStackMapIf6(this.classPool, classFile);
        }
        catch (BadBytecode badBytecode2) {
            throw new CannotCompileException(badBytecode2);
        }
    }
    
    private void modifyConstructors(final ClassFile classFile) throws CannotCompileException, NotFoundException {
        if (this.fieldInitializers == null) {
            return;
        }
        final ConstPool constPool = classFile.getConstPool();
        final List methods = classFile.getMethods();
        for (int size = methods.size(), i = 0; i < size; ++i) {
            final MethodInfo methodInfo = methods.get(i);
            if (methodInfo.isConstructor()) {
                final CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
                if (codeAttribute != null) {
                    try {
                        final Bytecode bytecode = new Bytecode(constPool, 0, codeAttribute.getMaxLocals());
                        insertAuxInitializer(codeAttribute, bytecode, this.makeFieldInitializer(bytecode, Descriptor.getParameterTypes(methodInfo.getDescriptor(), this.classPool)));
                        methodInfo.rebuildStackMapIf6(this.classPool, classFile);
                    }
                    catch (BadBytecode badBytecode) {
                        throw new CannotCompileException(badBytecode);
                    }
                }
            }
        }
    }
    
    private static void insertAuxInitializer(final CodeAttribute codeAttribute, final Bytecode bytecode, final int maxStack) throws BadBytecode {
        final CodeIterator iterator = codeAttribute.iterator();
        if (iterator.skipSuperConstructor() < 0 && iterator.skipThisConstructor() >= 0) {
            return;
        }
        iterator.insert(bytecode.getExceptionTable(), iterator.insertEx(bytecode.get()));
        if (codeAttribute.getMaxStack() < maxStack) {
            codeAttribute.setMaxStack(maxStack);
        }
    }
    
    private int makeFieldInitializer(final Bytecode bytecode, final CtClass[] array) throws CannotCompileException, NotFoundException {
        int n = 0;
        final Javac javac = new Javac(bytecode, this);
        try {
            javac.recordParams(array, false);
        }
        catch (CompileError compileError) {
            throw new CannotCompileException(compileError);
        }
        for (FieldInitLink fieldInitLink = this.fieldInitializers; fieldInitLink != null; fieldInitLink = fieldInitLink.next) {
            final CtField field = fieldInitLink.field;
            if (!Modifier.isStatic(field.getModifiers())) {
                final int compile = fieldInitLink.init.compile(field.getType(), field.getName(), bytecode, array, javac);
                if (n < compile) {
                    n = compile;
                }
            }
        }
        return n;
    }
    
    Hashtable getHiddenMethods() {
        if (this.hiddenMethods == null) {
            this.hiddenMethods = new Hashtable();
        }
        return this.hiddenMethods;
    }
    
    int getUniqueNumber() {
        return this.uniqueNumberSeed++;
    }
    
    @Override
    public String makeUniqueName(final String s) {
        final HashMap hashMap = new HashMap();
        this.makeMemberList(hashMap);
        final Set keySet = hashMap.keySet();
        final String[] array = new String[keySet.size()];
        keySet.toArray(array);
        if (notFindInArray(s, array)) {
            return s;
        }
        int i = 100;
        while (i <= 999) {
            final String string = s + i++;
            if (notFindInArray(string, array)) {
                return string;
            }
        }
        throw new RuntimeException("too many unique name");
    }
    
    private static boolean notFindInArray(final String s, final String[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (array[i].startsWith(s)) {
                return false;
            }
        }
        return true;
    }
    
    private void makeMemberList(final HashMap hashMap) {
        final int modifiers = this.getModifiers();
        Label_0076: {
            if (!Modifier.isAbstract(modifiers)) {
                if (!Modifier.isInterface(modifiers)) {
                    break Label_0076;
                }
            }
            try {
                for (final CtClass ctClass : this.getInterfaces()) {
                    if (ctClass != null && ctClass instanceof CtClassType) {
                        ((CtClassType)ctClass).makeMemberList(hashMap);
                    }
                }
            }
            catch (NotFoundException ex) {}
            try {
                final CtClass superclass = this.getSuperclass();
                if (superclass != null && superclass instanceof CtClassType) {
                    ((CtClassType)superclass).makeMemberList(hashMap);
                }
            }
            catch (NotFoundException ex2) {}
        }
        final List methods = this.getClassFile2().getMethods();
        for (int size = methods.size(), j = 0; j < size; ++j) {
            hashMap.put(methods.get(j).getName(), this);
        }
        final List fields = this.getClassFile2().getFields();
        for (int size2 = fields.size(), k = 0; k < size2; ++k) {
            hashMap.put(fields.get(k).getName(), this);
        }
    }
}
