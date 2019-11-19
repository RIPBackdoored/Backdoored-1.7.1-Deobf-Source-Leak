package org.reflections.vfs;

import java.util.jar.JarFile;
import java.net.URL;

enum Vfs$DefaultUrlTypes$1
{
    Vfs$DefaultUrlTypes$1(final String s, final int n) {
    }
    
    @Override
    public boolean matches(final URL url) {
        return url.getProtocol().equals("file") && Vfs.access$100(url);
    }
    
    @Override
    public Dir createDir(final URL url) throws Exception {
        return new ZipDir(new JarFile(Vfs.getFile(url)));
    }
}