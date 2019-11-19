package org.reflections.scanners;

import java.util.Iterator;
import com.google.common.base.Joiner;

public class TypeElementsScanner extends AbstractScanner
{
    private boolean includeFields;
    private boolean includeMethods;
    private boolean includeAnnotations;
    private boolean publicOnly;
    
    public TypeElementsScanner() {
        super();
        this.includeFields = true;
        this.includeMethods = true;
        this.includeAnnotations = true;
        this.publicOnly = true;
    }
    
    @Override
    public void scan(final Object o) {
        final String className = this.getMetadataAdapter().getClassName(o);
        if (!this.acceptResult(className)) {
            return;
        }
        this.getStore().put((Object)className, (Object)"");
        if (this.includeFields) {
            final Iterator<Object> iterator = this.getMetadataAdapter().getFields(o).iterator();
            while (iterator.hasNext()) {
                this.getStore().put((Object)className, (Object)this.getMetadataAdapter().getFieldName(iterator.next()));
            }
        }
        if (this.includeMethods) {
            for (final Object next : this.getMetadataAdapter().getMethods(o)) {
                if (!this.publicOnly || this.getMetadataAdapter().isPublic(next)) {
                    this.getStore().put((Object)className, (Object)(this.getMetadataAdapter().getMethodName(next) + "(" + Joiner.on(", ").join(this.getMetadataAdapter().getParameterNames(next)) + ")"));
                }
            }
        }
        if (this.includeAnnotations) {
            final Iterator<String> iterator3 = this.getMetadataAdapter().getClassAnnotationNames(o).iterator();
            while (iterator3.hasNext()) {
                this.getStore().put((Object)className, (Object)("@" + (Object)iterator3.next()));
            }
        }
    }
    
    public TypeElementsScanner includeFields() {
        return this.includeFields(true);
    }
    
    public TypeElementsScanner includeFields(final boolean includeFields) {
        this.includeFields = includeFields;
        return this;
    }
    
    public TypeElementsScanner includeMethods() {
        return this.includeMethods(true);
    }
    
    public TypeElementsScanner includeMethods(final boolean includeMethods) {
        this.includeMethods = includeMethods;
        return this;
    }
    
    public TypeElementsScanner includeAnnotations() {
        return this.includeAnnotations(true);
    }
    
    public TypeElementsScanner includeAnnotations(final boolean includeAnnotations) {
        this.includeAnnotations = includeAnnotations;
        return this;
    }
    
    public TypeElementsScanner publicOnly(final boolean publicOnly) {
        this.publicOnly = publicOnly;
        return this;
    }
    
    public TypeElementsScanner publicOnly() {
        return this.publicOnly(true);
    }
}
