package org.reflections.vfs;

import org.reflections.util.Utils;
import com.google.common.base.Predicate;

static final class Vfs$1 implements Predicate<File> {
    final /* synthetic */ String val$packagePrefix;
    final /* synthetic */ Predicate val$nameFilter;
    
    Vfs$1(final String val$packagePrefix, final Predicate val$nameFilter) {
        this.val$packagePrefix = val$packagePrefix;
        this.val$nameFilter = val$nameFilter;
        super();
    }
    
    @Override
    public boolean apply(final File file) {
        final String relativePath = file.getRelativePath();
        if (relativePath.startsWith(this.val$packagePrefix)) {
            final String substring = relativePath.substring(relativePath.indexOf(this.val$packagePrefix) + this.val$packagePrefix.length());
            return !Utils.isEmpty(substring) && this.val$nameFilter.apply(substring.substring(1));
        }
        return false;
    }
    
    @Override
    public /* bridge */ boolean apply(final Object o) {
        return this.apply((File)o);
    }
}