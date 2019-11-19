package org.reflections.vfs;

import java.util.jar.JarEntry;
import java.io.IOException;
import java.util.zip.ZipEntry;
import org.reflections.ReflectionsException;
import java.util.jar.JarInputStream;
import com.google.common.collect.AbstractIterator;
import java.util.Iterator;

class JarInputDir$1 implements Iterable<Vfs.File> {
    final /* synthetic */ JarInputDir this$0;
    
    JarInputDir$1(final JarInputDir this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public Iterator<Vfs.File> iterator() {
        return new AbstractIterator<Vfs.File>() {
            final /* synthetic */ JarInputDir$1 this$1;
            
            JarInputDir$1$1() throws Error {
                this.this$1 = this$1;
                super();
            }
            
            {
                try {
                    this.this$1.this$0.jarInputStream = new JarInputStream(JarInputDir.access$000(this.this$1.this$0).openConnection().getInputStream());
                }
                catch (Exception ex) {
                    throw new ReflectionsException("Could not open url connection", ex);
                }
            }
            
            @Override
            protected Vfs.File computeNext() {
                try {
                    while (true) {
                        final JarEntry nextJarEntry = this.this$1.this$0.jarInputStream.getNextJarEntry();
                        if (nextJarEntry == null) {
                            return this.endOfData();
                        }
                        long size = nextJarEntry.getSize();
                        if (size < 0L) {
                            size += 4294967295L;
                        }
                        final JarInputDir this$0 = this.this$1.this$0;
                        this$0.nextCursor += size;
                        if (!nextJarEntry.isDirectory()) {
                            return new JarInputFile(nextJarEntry, this.this$1.this$0, this.this$1.this$0.cursor, this.this$1.this$0.nextCursor);
                        }
                    }
                }
                catch (IOException ex) {
                    throw new ReflectionsException("could not get next zip entry", ex);
                }
            }
            
            @Override
            protected /* bridge */ Object computeNext() {
                return this.computeNext();
            }
        };
    }
}