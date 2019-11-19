package javassist;

import java.security.PrivilegedActionException;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.lang.reflect.InvocationTargetException;
import java.security.ProtectionDomain;
import javassist.bytecode.ClassFile;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.URL;
import javassist.bytecode.Descriptor;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Hashtable;
import java.lang.reflect.Method;

public class ClassPool
{
    private static Method defineClass1;
    private static Method defineClass2;
    private static Method definePackage;
    public boolean childFirstLookup;
    public static boolean doPruning;
    private int compressCount;
    private static final int COMPRESS_THRESHOLD = 100;
    public static boolean releaseUnmodifiedClassFile;
    protected ClassPoolTail source;
    protected ClassPool parent;
    protected Hashtable classes;
    private Hashtable cflow;
    private static final int INIT_HASH_SIZE = 191;
    private ArrayList importedPackages;
    private static ClassPool defaultPool;
    
    public ClassPool() {
        this(null);
    }
    
    public ClassPool(final boolean b) {
        this(null);
        if (b) {
            this.appendSystemPath();
        }
    }
    
    public ClassPool(final ClassPool parent) {
        super();
        this.childFirstLookup = false;
        this.cflow = null;
        this.classes = new Hashtable(191);
        this.source = new ClassPoolTail();
        this.parent = parent;
        if (parent == null) {
            final CtClass[] primitiveTypes = CtClass.primitiveTypes;
            for (int i = 0; i < primitiveTypes.length; ++i) {
                this.classes.put(primitiveTypes[i].getName(), primitiveTypes[i]);
            }
        }
        this.cflow = null;
        this.compressCount = 0;
        this.clearImportedPackages();
    }
    
    public static synchronized ClassPool getDefault() {
        if (ClassPool.defaultPool == null) {
            (ClassPool.defaultPool = new ClassPool(null)).appendSystemPath();
        }
        return ClassPool.defaultPool;
    }
    
    protected CtClass getCached(final String s) {
        return this.classes.get(s);
    }
    
    protected void cacheCtClass(final String s, final CtClass ctClass, final boolean b) {
        this.classes.put(s, ctClass);
    }
    
    protected CtClass removeCached(final String s) {
        return this.classes.remove(s);
    }
    
    @Override
    public String toString() {
        return this.source.toString();
    }
    
    void compress() {
        if (this.compressCount++ > 100) {
            this.compressCount = 0;
            final Enumeration<CtClass> elements = this.classes.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().compress();
            }
        }
    }
    
    public void importPackage(final String s) {
        this.importedPackages.add(s);
    }
    
    public void clearImportedPackages() {
        (this.importedPackages = new ArrayList()).add("java.lang");
    }
    
    public Iterator getImportedPackages() {
        return this.importedPackages.iterator();
    }
    
    @Deprecated
    public void recordInvalidClassName(final String s) {
    }
    
    void recordCflow(final String s, final String s2, final String s3) {
        if (this.cflow == null) {
            this.cflow = new Hashtable();
        }
        this.cflow.put(s, new Object[] { s2, s3 });
    }
    
    public Object[] lookupCflow(final String s) {
        if (this.cflow == null) {
            this.cflow = new Hashtable();
        }
        return this.cflow.get(s);
    }
    
    public CtClass getAndRename(final String s, final String name) throws NotFoundException {
        final CtClass get0 = this.get0(s, false);
        if (get0 == null) {
            throw new NotFoundException(s);
        }
        if (get0 instanceof CtClassType) {
            ((CtClassType)get0).setClassPool(this);
        }
        get0.setName(name);
        return get0;
    }
    
    synchronized void classNameChanged(final String s, final CtClass ctClass) {
        if (this.getCached(s) == ctClass) {
            this.removeCached(s);
        }
        final String name = ctClass.getName();
        this.checkNotFrozen(name);
        this.cacheCtClass(name, ctClass, false);
    }
    
    public CtClass get(final String s) throws NotFoundException {
        CtClass get0;
        if (s == null) {
            get0 = null;
        }
        else {
            get0 = this.get0(s, true);
        }
        if (get0 == null) {
            throw new NotFoundException(s);
        }
        get0.incGetCounter();
        return get0;
    }
    
    public CtClass getOrNull(final String s) {
        CtClass get0 = null;
        if (s == null) {
            get0 = null;
        }
        else {
            try {
                get0 = this.get0(s, true);
            }
            catch (NotFoundException ex) {}
        }
        if (get0 != null) {
            get0.incGetCounter();
        }
        return get0;
    }
    
    public CtClass getCtClass(final String s) throws NotFoundException {
        if (s.charAt(0) == '[') {
            return Descriptor.toCtClass(s, this);
        }
        return this.get(s);
    }
    
    protected synchronized CtClass get0(final String s, final boolean b) throws NotFoundException {
        if (b) {
            final CtClass cached = this.getCached(s);
            if (cached != null) {
                return cached;
            }
        }
        if (!this.childFirstLookup && this.parent != null) {
            final CtClass get0 = this.parent.get0(s, b);
            if (get0 != null) {
                return get0;
            }
        }
        CtClass ctClass = this.createCtClass(s, b);
        if (ctClass != null) {
            if (b) {
                this.cacheCtClass(ctClass.getName(), ctClass, false);
            }
            return ctClass;
        }
        if (this.childFirstLookup && this.parent != null) {
            ctClass = this.parent.get0(s, b);
        }
        return ctClass;
    }
    
    protected CtClass createCtClass(String className, final boolean b) {
        if (className.charAt(0) == '[') {
            className = Descriptor.toClassName(className);
        }
        if (className.endsWith("[]")) {
            final String substring = className.substring(0, className.indexOf(91));
            if ((!b || this.getCached(substring) == null) && this.find(substring) == null) {
                return null;
            }
            return new CtArray(className, this);
        }
        else {
            if (this.find(className) == null) {
                return null;
            }
            return new CtClassType(className, this);
        }
    }
    
    public URL find(final String s) {
        return this.source.find(s);
    }
    
    void checkNotFrozen(final String s) throws RuntimeException {
        CtClass ctClass = this.getCached(s);
        if (ctClass == null) {
            if (!this.childFirstLookup && this.parent != null) {
                try {
                    ctClass = this.parent.get0(s, true);
                }
                catch (NotFoundException ex) {}
                if (ctClass != null) {
                    throw new RuntimeException(s + " is in a parent ClassPool.  Use the parent.");
                }
            }
        }
        else if (ctClass.isFrozen()) {
            throw new RuntimeException(s + ": frozen class (cannot edit)");
        }
    }
    
    CtClass checkNotExists(final String s) {
        CtClass ctClass = this.getCached(s);
        if (ctClass == null && !this.childFirstLookup && this.parent != null) {
            try {
                ctClass = this.parent.get0(s, true);
            }
            catch (NotFoundException ex) {}
        }
        return ctClass;
    }
    
    InputStream openClassfile(final String s) throws NotFoundException {
        return this.source.openClassfile(s);
    }
    
    void writeClassfile(final String s, final OutputStream outputStream) throws NotFoundException, IOException, CannotCompileException {
        this.source.writeClassfile(s, outputStream);
    }
    
    public CtClass[] get(final String[] array) throws NotFoundException {
        if (array == null) {
            return new CtClass[0];
        }
        final int length = array.length;
        final CtClass[] array2 = new CtClass[length];
        for (int i = 0; i < length; ++i) {
            array2[i] = this.get(array[i]);
        }
        return array2;
    }
    
    public CtMethod getMethod(final String s, final String s2) throws NotFoundException {
        return this.get(s).getDeclaredMethod(s2);
    }
    
    public CtClass makeClass(final InputStream inputStream) throws IOException, RuntimeException {
        return this.makeClass(inputStream, true);
    }
    
    public CtClass makeClass(final InputStream inputStream, final boolean b) throws IOException, RuntimeException {
        this.compress();
        final CtClassType ctClassType = new CtClassType(new BufferedInputStream(inputStream), this);
        ctClassType.checkModify();
        final String name = ctClassType.getName();
        if (b) {
            this.checkNotFrozen(name);
        }
        this.cacheCtClass(name, ctClassType, true);
        return ctClassType;
    }
    
    public CtClass makeClass(final ClassFile classFile) throws RuntimeException {
        return this.makeClass(classFile, true);
    }
    
    public CtClass makeClass(final ClassFile classFile, final boolean b) throws RuntimeException {
        this.compress();
        final CtClassType ctClassType = new CtClassType(classFile, this);
        ctClassType.checkModify();
        final String name = ctClassType.getName();
        if (b) {
            this.checkNotFrozen(name);
        }
        this.cacheCtClass(name, ctClassType, true);
        return ctClassType;
    }
    
    public CtClass makeClassIfNew(final InputStream inputStream) throws IOException, RuntimeException {
        this.compress();
        final CtClassType ctClassType = new CtClassType(new BufferedInputStream(inputStream), this);
        ctClassType.checkModify();
        final String name = ctClassType.getName();
        final CtClass checkNotExists = this.checkNotExists(name);
        if (checkNotExists != null) {
            return checkNotExists;
        }
        this.cacheCtClass(name, ctClassType, true);
        return ctClassType;
    }
    
    public CtClass makeClass(final String s) throws RuntimeException {
        return this.makeClass(s, null);
    }
    
    public synchronized CtClass makeClass(final String s, final CtClass ctClass) throws RuntimeException {
        this.checkNotFrozen(s);
        final CtNewClass ctNewClass = new CtNewClass(s, this, false, ctClass);
        this.cacheCtClass(s, ctNewClass, true);
        return ctNewClass;
    }
    
    synchronized CtClass makeNestedClass(final String s) {
        this.checkNotFrozen(s);
        final CtNewNestedClass ctNewNestedClass = new CtNewNestedClass(s, this, false, null);
        this.cacheCtClass(s, ctNewNestedClass, true);
        return ctNewNestedClass;
    }
    
    public CtClass makeInterface(final String s) throws RuntimeException {
        return this.makeInterface(s, null);
    }
    
    public synchronized CtClass makeInterface(final String s, final CtClass ctClass) throws RuntimeException {
        this.checkNotFrozen(s);
        final CtNewClass ctNewClass = new CtNewClass(s, this, true, ctClass);
        this.cacheCtClass(s, ctNewClass, true);
        return ctNewClass;
    }
    
    public CtClass makeAnnotation(final String s) throws RuntimeException {
        try {
            final CtClass interface1 = this.makeInterface(s, this.get("java.lang.annotation.Annotation"));
            interface1.setModifiers(interface1.getModifiers() | 0x2000);
            return interface1;
        }
        catch (NotFoundException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
    
    public ClassPath appendSystemPath() {
        return this.source.appendSystemPath();
    }
    
    public ClassPath insertClassPath(final ClassPath classPath) {
        return this.source.insertClassPath(classPath);
    }
    
    public ClassPath appendClassPath(final ClassPath classPath) {
        return this.source.appendClassPath(classPath);
    }
    
    public ClassPath insertClassPath(final String s) throws NotFoundException {
        return this.source.insertClassPath(s);
    }
    
    public ClassPath appendClassPath(final String s) throws NotFoundException {
        return this.source.appendClassPath(s);
    }
    
    public void removeClassPath(final ClassPath classPath) {
        this.source.removeClassPath(classPath);
    }
    
    public void appendPathList(final String s) throws NotFoundException {
        int n = 0;
        while (true) {
            final int n2;
            final int index = s.indexOf(n2, n);
            if (index < 0) {
                break;
            }
            this.appendClassPath(s.substring(n, index));
            n = index + 1;
        }
        this.appendClassPath(s.substring(n));
    }
    
    public Class toClass(final CtClass ctClass) throws CannotCompileException {
        return this.toClass(ctClass, this.getClassLoader());
    }
    
    public ClassLoader getClassLoader() {
        return getContextClassLoader();
    }
    
    static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
    
    @Deprecated
    public Class toClass(final CtClass ctClass, final ClassLoader classLoader) throws CannotCompileException {
        return this.toClass(ctClass, classLoader, null);
    }
    
    public Class toClass(final CtClass ctClass, final ClassLoader classLoader, final ProtectionDomain protectionDomain) throws CannotCompileException {
        try {
            final byte[] bytecode = ctClass.toBytecode();
            Method method;
            Object[] array;
            if (protectionDomain == null) {
                method = ClassPool.defineClass1;
                array = new Object[] { ctClass.getName(), bytecode, new Integer(0), new Integer(bytecode.length) };
            }
            else {
                method = ClassPool.defineClass2;
                array = new Object[] { ctClass.getName(), bytecode, new Integer(0), new Integer(bytecode.length), protectionDomain };
            }
            return (Class)toClass2(method, classLoader, array);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (InvocationTargetException ex2) {
            throw new CannotCompileException(ex2.getTargetException());
        }
        catch (Exception ex3) {
            throw new CannotCompileException(ex3);
        }
    }
    
    private static synchronized Object toClass2(final Method method, final ClassLoader classLoader, final Object[] array) throws Exception {
        method.setAccessible(true);
        try {
            return method.invoke(classLoader, array);
        }
        finally {
            method.setAccessible(false);
        }
    }
    
    public void makePackage(final ClassLoader classLoader, final String s) throws CannotCompileException {
        final Object[] array = { s, null, null, null, null, null, null, null };
        Throwable targetException;
        try {
            toClass2(ClassPool.definePackage, classLoader, array);
            return;
        }
        catch (InvocationTargetException ex) {
            targetException = ex.getTargetException();
            if (targetException == null) {
                targetException = ex;
            }
            else if (targetException instanceof IllegalArgumentException) {
                return;
            }
        }
        catch (Exception ex2) {
            targetException = ex2;
        }
        throw new CannotCompileException(targetException);
    }
    
    static /* synthetic */ Method access$002(final Method defineClass1) {
        return ClassPool.defineClass1 = defineClass1;
    }
    
    static /* synthetic */ Method access$102(final Method defineClass2) {
        return ClassPool.defineClass2 = defineClass2;
    }
    
    static /* synthetic */ Method access$202(final Method definePackage) {
        return ClassPool.definePackage = definePackage;
    }
    
    static {
        try {
            AccessController.<Object>doPrivileged((PrivilegedExceptionAction<Object>)new PrivilegedExceptionAction() {
                ClassPool$1() {
                    super();
                }
                
                @Override
                public Object run() throws Exception {
                    final Class<?> forName = Class.forName("java.lang.ClassLoader");
                    ClassPool.defineClass1 = forName.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE);
                    ClassPool.defineClass2 = forName.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class);
                    ClassPool.definePackage = forName.getDeclaredMethod("definePackage", String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class);
                    return null;
                }
            });
        }
        catch (PrivilegedActionException ex) {
            throw new RuntimeException("cannot initialize ClassPool", ex.getException());
        }
        ClassPool.doPruning = false;
        ClassPool.releaseUnmodifiedClassFile = true;
        ClassPool.defaultPool = null;
    }
}
