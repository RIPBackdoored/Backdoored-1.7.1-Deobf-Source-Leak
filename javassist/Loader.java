package javassist;

import java.io.InputStream;
import java.net.URL;
import java.lang.reflect.InvocationTargetException;
import java.security.ProtectionDomain;
import java.util.Vector;
import java.util.Hashtable;

public class Loader extends ClassLoader
{
    private Hashtable notDefinedHere;
    private Vector notDefinedPackages;
    private ClassPool source;
    private Translator translator;
    private ProtectionDomain domain;
    public boolean doDelegation;
    
    public Loader() {
        this((ClassPool)null);
    }
    
    public Loader(final ClassPool classPool) {
        super();
        this.doDelegation = true;
        this.init(classPool);
    }
    
    public Loader(final ClassLoader classLoader, final ClassPool classPool) {
        super(classLoader);
        this.doDelegation = true;
        this.init(classPool);
    }
    
    private void init(final ClassPool source) {
        this.notDefinedHere = new Hashtable();
        this.notDefinedPackages = new Vector();
        this.source = source;
        this.translator = null;
        this.domain = null;
        this.delegateLoadingOf("javassist.Loader");
    }
    
    public void delegateLoadingOf(final String s) {
        if (s.endsWith(".")) {
            this.notDefinedPackages.addElement(s);
        }
        else {
            this.notDefinedHere.put(s, this);
        }
    }
    
    public void setDomain(final ProtectionDomain domain) {
        this.domain = domain;
    }
    
    public void setClassPool(final ClassPool source) {
        this.source = source;
    }
    
    public void addTranslator(final ClassPool source, final Translator translator) throws NotFoundException, CannotCompileException {
        this.source = source;
        (this.translator = translator).start(source);
    }
    
    public static void main(final String[] array) throws Throwable {
        new Loader().run(array);
    }
    
    public void run(final String[] array) throws Throwable {
        final int n = array.length - 1;
        if (n >= 0) {
            final String[] array2 = new String[n];
            for (int i = 0; i < n; ++i) {
                array2[i] = array[i + 1];
            }
            this.run(array[0], array2);
        }
    }
    
    public void run(final String s, final String[] array) throws Throwable {
        final Class<?> loadClass = this.loadClass(s);
        try {
            loadClass.getDeclaredMethod("main", String[].class).invoke(null, array);
        }
        catch (InvocationTargetException ex) {
            throw ex.getTargetException();
        }
    }
    
    @Override
    protected Class loadClass(String intern, final boolean b) throws ClassFormatError, ClassNotFoundException {
        intern = intern.intern();
        synchronized (intern) {
            Class<?> clazz = this.findLoadedClass(intern);
            if (clazz == null) {
                clazz = (Class<?>)this.loadClassByDelegation(intern);
            }
            if (clazz == null) {
                clazz = (Class<?>)this.findClass(intern);
            }
            if (clazz == null) {
                clazz = (Class<?>)this.delegateToParent(intern);
            }
            if (b) {
                this.resolveClass(clazz);
            }
            return clazz;
        }
    }
    
    @Override
    protected Class findClass(final String s) throws ClassNotFoundException {
        byte[] array = null;
        try {
            Label_0101: {
                if (this.source != null) {
                    if (this.translator != null) {
                        this.translator.onLoad(this.source, s);
                    }
                    try {
                        array = this.source.get(s).toBytecode();
                        break Label_0101;
                    }
                    catch (NotFoundException ex2) {
                        return null;
                    }
                }
                final InputStream resourceAsStream = this.getClass().getResourceAsStream("/" + s.replace('.', '/') + ".class");
                if (resourceAsStream == null) {
                    return null;
                }
                array = ClassPoolTail.readStream(resourceAsStream);
            }
        }
        catch (Exception ex) {
            throw new ClassNotFoundException("caught an exception while obtaining a class file for " + s, ex);
        }
        final int lastIndex = s.lastIndexOf(46);
        if (lastIndex != -1) {
            final String substring = s.substring(0, lastIndex);
            if (this.getPackage(substring) == null) {
                try {
                    this.definePackage(substring, null, null, null, null, null, null, null);
                }
                catch (IllegalArgumentException ex3) {}
            }
        }
        if (this.domain == null) {
            return this.defineClass(s, array, 0, array.length);
        }
        return this.defineClass(s, array, 0, array.length, this.domain);
    }
    
    protected Class loadClassByDelegation(final String s) throws ClassNotFoundException {
        Class delegateToParent = null;
        if (this.doDelegation && (s.startsWith("java.") || s.startsWith("javax.") || s.startsWith("sun.") || s.startsWith("com.sun.") || s.startsWith("org.w3c.") || s.startsWith("org.xml.") || this.notDelegated(s))) {
            delegateToParent = this.delegateToParent(s);
        }
        return delegateToParent;
    }
    
    private boolean notDelegated(final String s) {
        if (this.notDefinedHere.get(s) != null) {
            return true;
        }
        for (int size = this.notDefinedPackages.size(), i = 0; i < size; ++i) {
            if (s.startsWith((String)this.notDefinedPackages.elementAt(i))) {
                return true;
            }
        }
        return false;
    }
    
    protected Class delegateToParent(final String s) throws ClassNotFoundException {
        final ClassLoader parent = this.getParent();
        if (parent != null) {
            return parent.loadClass(s);
        }
        return this.findSystemClass(s);
    }
}
