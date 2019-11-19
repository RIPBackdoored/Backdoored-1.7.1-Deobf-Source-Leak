package org.reflections.vfs;

import java.io.IOException;
import java.io.InputStream;

class JarInputFile$1 extends InputStream {
    final /* synthetic */ JarInputFile this$0;
    
    JarInputFile$1(final JarInputFile this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public int read() throws IOException {
        if (JarInputFile.access$000(this.this$0).cursor >= JarInputFile.access$100(this.this$0) && JarInputFile.access$000(this.this$0).cursor <= JarInputFile.access$200(this.this$0)) {
            final int read = JarInputFile.access$000(this.this$0).jarInputStream.read();
            final JarInputDir access$000 = JarInputFile.access$000(this.this$0);
            ++access$000.cursor;
            return read;
        }
        return -1;
    }
}