package javassist.scopedpool;

import java.util.Iterator;
import java.util.ArrayList;
import javassist.ClassPath;
import javassist.LoaderClassPath;
import java.util.Collections;
import java.util.WeakHashMap;
import javassist.ClassPool;
import java.util.Map;

public class ScopedClassPoolRepositoryImpl implements ScopedClassPoolRepository
{
    private static final ScopedClassPoolRepositoryImpl instance;
    private boolean prune;
    boolean pruneWhenCached;
    protected Map registeredCLs;
    protected ClassPool classpool;
    protected ScopedClassPoolFactory factory;
    
    public static ScopedClassPoolRepository getInstance() {
        return ScopedClassPoolRepositoryImpl.instance;
    }
    
    private ScopedClassPoolRepositoryImpl() {
        super();
        this.prune = true;
        this.registeredCLs = Collections.<Object, Object>synchronizedMap(new WeakHashMap<Object, Object>());
        this.factory = new ScopedClassPoolFactoryImpl();
        (this.classpool = ClassPool.getDefault()).insertClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
    }
    
    @Override
    public boolean isPrune() {
        return this.prune;
    }
    
    @Override
    public void setPrune(final boolean prune) {
        this.prune = prune;
    }
    
    @Override
    public ScopedClassPool createScopedClassPool(final ClassLoader classLoader, final ClassPool classPool) {
        return this.factory.create(classLoader, classPool, this);
    }
    
    @Override
    public ClassPool findClassPool(final ClassLoader classLoader) {
        if (classLoader == null) {
            return this.registerClassLoader(ClassLoader.getSystemClassLoader());
        }
        return this.registerClassLoader(classLoader);
    }
    
    @Override
    public ClassPool registerClassLoader(final ClassLoader classLoader) {
        synchronized (this.registeredCLs) {
            if (this.registeredCLs.containsKey(classLoader)) {
                return this.registeredCLs.get(classLoader);
            }
            final ScopedClassPool scopedClassPool = this.createScopedClassPool(classLoader, this.classpool);
            this.registeredCLs.put(classLoader, scopedClassPool);
            return scopedClassPool;
        }
    }
    
    @Override
    public Map getRegisteredCLs() {
        this.clearUnregisteredClassLoaders();
        return this.registeredCLs;
    }
    
    @Override
    public void clearUnregisteredClassLoaders() {
        ArrayList<ClassLoader> list = null;
        synchronized (this.registeredCLs) {
            final Iterator<ScopedClassPool> iterator = this.registeredCLs.values().iterator();
            while (iterator.hasNext()) {
                final ScopedClassPool scopedClassPool = iterator.next();
                if (scopedClassPool.isUnloadedClassLoader()) {
                    iterator.remove();
                    final ClassLoader classLoader = scopedClassPool.getClassLoader();
                    if (classLoader == null) {
                        continue;
                    }
                    if (list == null) {
                        list = new ArrayList<ClassLoader>();
                    }
                    list.add(classLoader);
                }
            }
            if (list != null) {
                for (int i = 0; i < list.size(); ++i) {
                    this.unregisterClassLoader(list.get(i));
                }
            }
        }
    }
    
    @Override
    public void unregisterClassLoader(final ClassLoader classLoader) {
        synchronized (this.registeredCLs) {
            final ScopedClassPool scopedClassPool = this.registeredCLs.remove(classLoader);
            if (scopedClassPool != null) {
                scopedClassPool.close();
            }
        }
    }
    
    public void insertDelegate(final ScopedClassPoolRepository scopedClassPoolRepository) {
    }
    
    @Override
    public void setClassPoolFactory(final ScopedClassPoolFactory factory) {
        this.factory = factory;
    }
    
    @Override
    public ScopedClassPoolFactory getClassPoolFactory() {
        return this.factory;
    }
    
    static {
        instance = new ScopedClassPoolRepositoryImpl();
    }
}
