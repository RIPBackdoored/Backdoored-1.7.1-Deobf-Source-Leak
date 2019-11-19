package org.reflections.vfs;

import java.util.jar.JarFile;
import java.io.File;
import org.reflections.util.ClasspathHelper;
import java.net.URL;

enum Vfs$DefaultUrlTypes$4
{
    Vfs$DefaultUrlTypes$4(final String s, final int n) {
    }
    
    @Override
    public boolean matches(final URL url) {
        return url.getProtocol().equals("vfs");
    }
    
    @Override
    public Dir createDir(final URL url) throws Exception {
        final Object content = url.openConnection().getContent();
        final Class<?> loadClass = ClasspathHelper.contextClassLoader().loadClass("org.jboss.vfs.VirtualFile");
        final java.io.File file = (java.io.File)loadClass.getMethod("getPhysicalFile", (Class<?>[])new Class[0]).invoke(content, new Object[0]);
        java.io.File file2 = new java.io.File(file.getParentFile(), (String)loadClass.getMethod("getName", (Class<?>[])new Class[0]).invoke(content, new Object[0]));
        if (!file2.exists() || !file2.canRead()) {
            file2 = file;
        }
        return file2.isDirectory() ? new SystemDir(file2) : new ZipDir(new JarFile(file2));
    }
}