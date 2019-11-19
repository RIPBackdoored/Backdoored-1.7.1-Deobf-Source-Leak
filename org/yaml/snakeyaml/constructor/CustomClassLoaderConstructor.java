package org.yaml.snakeyaml.constructor;

public class CustomClassLoaderConstructor extends Constructor
{
    private ClassLoader loader;
    
    public CustomClassLoaderConstructor(final ClassLoader classLoader) {
        this(Object.class, classLoader);
    }
    
    public CustomClassLoaderConstructor(final Class<?> theRoot, final ClassLoader loader) {
        super(theRoot);
        this.loader = CustomClassLoaderConstructor.class.getClassLoader();
        if (loader == null) {
            throw new NullPointerException("Loader must be provided.");
        }
        this.loader = loader;
    }
    
    @Override
    protected Class<?> getClassForName(final String s) throws ClassNotFoundException {
        return Class.forName(s, true, this.loader);
    }
}
