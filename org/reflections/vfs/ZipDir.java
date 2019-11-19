package org.reflections.vfs;

import java.io.IOException;
import org.reflections.Reflections;
import java.util.zip.ZipEntry;
import java.util.Enumeration;
import com.google.common.collect.AbstractIterator;
import java.util.Iterator;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

public class ZipDir implements Vfs.Dir
{
    final ZipFile jarFile;
    
    public ZipDir(final JarFile jarFile) {
        super();
        this.jarFile = jarFile;
    }
    
    @Override
    public String getPath() {
        return this.jarFile.getName();
    }
    
    @Override
    public Iterable<Vfs.File> getFiles() {
        return new Iterable<Vfs.File>() {
            final /* synthetic */ ZipDir this$0;
            
            ZipDir$1() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public Iterator<Vfs.File> iterator() {
                return new AbstractIterator<Vfs.File>() {
                    final Enumeration<? extends ZipEntry> entries = this.this$1.this$0.jarFile.entries();
                    final /* synthetic */ ZipDir$1 this$1;
                    
                    ZipDir$1$1() {
                        this.this$1 = this$1;
                        super();
                    }
                    
                    @Override
                    protected Vfs.File computeNext() {
                        while (this.entries.hasMoreElements()) {
                            final ZipEntry zipEntry = (ZipEntry)this.entries.nextElement();
                            if (!zipEntry.isDirectory()) {
                                return new org.reflections.vfs.ZipFile(this.this$1.this$0, zipEntry);
                            }
                        }
                        return this.endOfData();
                    }
                    
                    @Override
                    protected /* bridge */ Object computeNext() {
                        return this.computeNext();
                    }
                };
            }
        };
    }
    
    @Override
    public void close() {
        try {
            this.jarFile.close();
        }
        catch (IOException ex) {
            if (Reflections.log != null) {
                Reflections.log.warn("Could not close JarFile", ex);
            }
        }
    }
    
    @Override
    public String toString() {
        return this.jarFile.getName();
    }
}
