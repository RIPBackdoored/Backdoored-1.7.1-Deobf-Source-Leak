package org.reflections.vfs;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Collection;
import java.util.Stack;
import com.google.common.collect.AbstractIterator;
import java.util.Iterator;
import java.util.Collections;
import java.io.File;

public class SystemDir implements Vfs.Dir
{
    private final java.io.File file;
    
    public SystemDir(final java.io.File file) {
        super();
        if (file != null && (!file.isDirectory() || !file.canRead())) {
            throw new RuntimeException("cannot use dir " + file);
        }
        this.file = file;
    }
    
    @Override
    public String getPath() {
        if (this.file == null) {
            return "/NO-SUCH-DIRECTORY/";
        }
        return this.file.getPath().replace("\\", "/");
    }
    
    @Override
    public Iterable<Vfs.File> getFiles() {
        if (this.file == null || !this.file.exists()) {
            return (Iterable<Vfs.File>)Collections.<Object>emptyList();
        }
        return new Iterable<Vfs.File>() {
            final /* synthetic */ SystemDir this$0;
            
            SystemDir$1() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public Iterator<Vfs.File> iterator() {
                return new AbstractIterator<Vfs.File>() {
                    final Stack<java.io.File> stack;
                    final /* synthetic */ SystemDir$1 this$1;
                    
                    SystemDir$1$1() {
                        this.this$1 = this$1;
                        super();
                    }
                    
                    {
                        (this.stack = new Stack<java.io.File>()).addAll((Collection<?>)listFiles(this.this$1.this$0.file));
                    }
                    
                    @Override
                    protected Vfs.File computeNext() {
                        while (!this.stack.isEmpty()) {
                            final java.io.File file = this.stack.pop();
                            if (!file.isDirectory()) {
                                return new SystemFile(this.this$1.this$0, file);
                            }
                            this.stack.addAll((Collection<?>)listFiles(file));
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
    
    private static List<java.io.File> listFiles(final java.io.File file) {
        final java.io.File[] listFiles = file.listFiles();
        if (listFiles != null) {
            return Lists.<java.io.File>newArrayList(listFiles);
        }
        return (List<java.io.File>)Lists.<Object>newArrayList();
    }
    
    @Override
    public void close() {
    }
    
    @Override
    public String toString() {
        return this.getPath();
    }
    
    static /* synthetic */ java.io.File access$000(final SystemDir systemDir) {
        return systemDir.file;
    }
    
    static /* synthetic */ List access$100(final java.io.File file) {
        return listFiles(file);
    }
}
