package org.reflections.vfs;

import java.util.zip.ZipEntry;
import java.util.Enumeration;
import com.google.common.collect.AbstractIterator;

class ZipDir$1$1 extends AbstractIterator<Vfs.File> {
    final Enumeration<? extends ZipEntry> entries = this.this$1.this$0.jarFile.entries();
    final /* synthetic */ ZipDir$1 this$1;
    
    ZipDir$1$1(final ZipDir$1 this$1) {
        this.this$1 = this$1;
        super();
    }
    
    @Override
    protected Vfs.File computeNext() {
        while (this.entries.hasMoreElements()) {
            final ZipEntry zipEntry = (ZipEntry)this.entries.nextElement();
            if (!zipEntry.isDirectory()) {
                return new ZipFile(this.this$1.this$0, zipEntry);
            }
        }
        return this.endOfData();
    }
    
    @Override
    protected /* bridge */ Object computeNext() {
        return this.computeNext();
    }
}