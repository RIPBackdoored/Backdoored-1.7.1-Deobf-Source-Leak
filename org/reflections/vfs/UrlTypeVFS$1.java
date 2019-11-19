package org.reflections.vfs;

import java.io.File;
import com.google.common.base.Predicate;

class UrlTypeVFS$1 implements Predicate<java.io.File> {
    final /* synthetic */ UrlTypeVFS this$0;
    
    UrlTypeVFS$1(final UrlTypeVFS this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public boolean apply(final java.io.File file) {
        return file.exists() && file.isFile();
    }
    
    @Override
    public /* bridge */ boolean apply(final Object o) {
        return this.apply((java.io.File)o);
    }
}