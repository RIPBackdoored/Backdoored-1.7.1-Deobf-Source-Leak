package org.reflections.scanners;

import org.reflections.vfs.Vfs;

@Deprecated
public class TypesScanner extends AbstractScanner
{
    public TypesScanner() {
        super();
    }
    
    @Override
    public Object scan(final Vfs.File file, Object scan) {
        scan = super.scan(file, scan);
        final String className = this.getMetadataAdapter().getClassName(scan);
        this.getStore().put((Object)className, (Object)className);
        return scan;
    }
    
    @Override
    public void scan(final Object o) {
        throw new UnsupportedOperationException("should not get here");
    }
}
