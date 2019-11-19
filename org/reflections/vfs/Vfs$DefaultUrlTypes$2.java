package org.reflections.vfs;

import java.io.File;
import java.net.URLConnection;
import java.util.jar.JarFile;
import java.net.JarURLConnection;
import java.net.URL;

enum Vfs$DefaultUrlTypes$2
{
    Vfs$DefaultUrlTypes$2(final String s, final int n) {
    }
    
    @Override
    public boolean matches(final URL url) {
        return "jar".equals(url.getProtocol()) || "zip".equals(url.getProtocol()) || "wsjar".equals(url.getProtocol());
    }
    
    @Override
    public Dir createDir(final URL url) throws Exception {
        try {
            final URLConnection openConnection = url.openConnection();
            if (openConnection instanceof JarURLConnection) {
                return new ZipDir(((JarURLConnection)openConnection).getJarFile());
            }
        }
        catch (Throwable t) {}
        final java.io.File file = Vfs.getFile(url);
        if (file != null) {
            return new ZipDir(new JarFile(file));
        }
        return null;
    }
}