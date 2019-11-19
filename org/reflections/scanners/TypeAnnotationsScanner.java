package org.reflections.scanners;

import java.util.Iterator;
import java.lang.annotation.Inherited;

public class TypeAnnotationsScanner extends AbstractScanner
{
    public TypeAnnotationsScanner() {
        super();
    }
    
    @Override
    public void scan(final Object o) {
        final String className = this.getMetadataAdapter().getClassName(o);
        for (final String s : this.getMetadataAdapter().getClassAnnotationNames(o)) {
            if (this.acceptResult(s) || s.equals(Inherited.class.getName())) {
                this.getStore().put((Object)s, (Object)className);
            }
        }
    }
}
