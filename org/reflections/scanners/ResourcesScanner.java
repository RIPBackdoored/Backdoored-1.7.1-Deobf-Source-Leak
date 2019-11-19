package org.reflections.scanners;

import org.reflections.vfs.Vfs;

public class ResourcesScanner extends AbstractScanner
{
    public ResourcesScanner() {
        super();
    }
    
    @Override
    public boolean acceptsInput(final String s) {
        return !s.endsWith(".class");
    }
    
    @Override
    public Object scan(final Vfs.File file, final Object o) {
        this.getStore().put((Object)file.getName(), (Object)file.getRelativePath());
        return o;
    }
    
    @Override
    public void scan(final Object o) {
        throw new UnsupportedOperationException();
    }
}
