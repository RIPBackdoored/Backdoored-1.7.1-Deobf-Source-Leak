package org.reflections.scanners;

import java.util.Iterator;
import com.google.common.base.Predicate;
import org.reflections.util.FilterBuilder;

public class SubTypesScanner extends AbstractScanner
{
    public SubTypesScanner() {
        this(true);
    }
    
    public SubTypesScanner(final boolean b) {
        super();
        if (b) {
            this.filterResultsBy(new FilterBuilder().exclude(Object.class.getName()));
        }
    }
    
    @Override
    public void scan(final Object o) {
        final String className = this.getMetadataAdapter().getClassName(o);
        final String superclassName = this.getMetadataAdapter().getSuperclassName(o);
        if (this.acceptResult(superclassName)) {
            this.getStore().put((Object)superclassName, (Object)className);
        }
        for (final String s : this.getMetadataAdapter().getInterfacesNames(o)) {
            if (this.acceptResult(s)) {
                this.getStore().put((Object)s, (Object)className);
            }
        }
    }
}
