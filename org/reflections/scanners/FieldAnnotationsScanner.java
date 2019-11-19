package org.reflections.scanners;

import java.util.Iterator;

public class FieldAnnotationsScanner extends AbstractScanner
{
    public FieldAnnotationsScanner() {
        super();
    }
    
    @Override
    public void scan(final Object o) {
        final String className = this.getMetadataAdapter().getClassName(o);
        for (final Object next : this.getMetadataAdapter().getFields(o)) {
            for (final String s : this.getMetadataAdapter().getFieldAnnotationNames(next)) {
                if (this.acceptResult(s)) {
                    this.getStore().put((Object)s, (Object)String.format("%s.%s", className, this.getMetadataAdapter().getFieldName(next)));
                }
            }
        }
    }
}
