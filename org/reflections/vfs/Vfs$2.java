package org.reflections.vfs;

import java.util.Iterator;
import java.net.URL;

static final class Vfs$2 implements Iterable<File> {
    final /* synthetic */ URL val$url;
    
    Vfs$2(final URL val$url) {
        this.val$url = val$url;
        super();
    }
    
    @Override
    public Iterator<File> iterator() {
        return Vfs.fromURL(this.val$url).getFiles().iterator();
    }
}