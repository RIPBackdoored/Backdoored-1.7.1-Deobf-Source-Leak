package org.reflections.vfs;

import java.io.File;
import java.net.URL;

enum Vfs$DefaultUrlTypes$3
{
    Vfs$DefaultUrlTypes$3(final String s, final int n) {
    }
    
    @Override
    public boolean matches(final URL url) {
        if (url.getProtocol().equals("file") && !Vfs.access$100(url)) {
            final java.io.File file = Vfs.getFile(url);
            return file != null && file.isDirectory();
        }
        return false;
    }
    
    @Override
    public Dir createDir(final URL url) throws Exception {
        return new SystemDir(Vfs.getFile(url));
    }
}