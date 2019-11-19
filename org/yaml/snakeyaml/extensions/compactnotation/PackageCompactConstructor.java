package org.yaml.snakeyaml.extensions.compactnotation;

public class PackageCompactConstructor extends CompactConstructor
{
    private String packageName;
    
    public PackageCompactConstructor(final String packageName) {
        super();
        this.packageName = packageName;
    }
    
    @Override
    protected Class<?> getClassForName(final String name) throws ClassNotFoundException {
        if (name.indexOf(46) < 0) {
            try {
                return Class.forName(this.packageName + "." + name);
            }
            catch (ClassNotFoundException ex) {}
        }
        return super.getClassForName(name);
    }
}
