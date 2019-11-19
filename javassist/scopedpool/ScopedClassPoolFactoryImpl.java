package javassist.scopedpool;

import javassist.ClassPool;

public class ScopedClassPoolFactoryImpl implements ScopedClassPoolFactory
{
    public ScopedClassPoolFactoryImpl() {
        super();
    }
    
    @Override
    public ScopedClassPool create(final ClassLoader classLoader, final ClassPool classPool, final ScopedClassPoolRepository scopedClassPoolRepository) {
        return new ScopedClassPool(classLoader, classPool, scopedClassPoolRepository, false);
    }
    
    @Override
    public ScopedClassPool create(final ClassPool classPool, final ScopedClassPoolRepository scopedClassPoolRepository) {
        return new ScopedClassPool(null, classPool, scopedClassPoolRepository, true);
    }
}
