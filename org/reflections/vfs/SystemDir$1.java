package org.reflections.vfs;

import java.util.Collection;
import java.io.File;
import java.util.Stack;
import com.google.common.collect.AbstractIterator;
import java.util.Iterator;

class SystemDir$1 implements Iterable<Vfs.File> {
    final /* synthetic */ SystemDir this$0;
    
    SystemDir$1(final SystemDir this$0) {
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
                (this.stack = new Stack<java.io.File>()).addAll((Collection<?>)SystemDir.access$100(SystemDir.access$000(this.this$1.this$0)));
            }
            
            @Override
            protected Vfs.File computeNext() {
                while (!this.stack.isEmpty()) {
                    final java.io.File file = this.stack.pop();
                    if (!file.isDirectory()) {
                        return new SystemFile(this.this$1.this$0, file);
                    }
                    this.stack.addAll((Collection<?>)SystemDir.access$100(file));
                }
                return this.endOfData();
            }
            
            @Override
            protected /* bridge */ Object computeNext() {
                return this.computeNext();
            }
        };
    }
}