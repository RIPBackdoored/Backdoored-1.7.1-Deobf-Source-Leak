package org.reflections.vfs;

import java.net.URL;

enum Vfs$DefaultUrlTypes$7
{
    Vfs$DefaultUrlTypes$7(final String s, final int n) {
    }
    
    @Override
    public boolean matches(final URL url) throws Exception {
        return url.toExternalForm().contains(".jar");
    }
    
    @Override
    public Dir createDir(final URL url) throws Exception {
        return new JarInputDir(url);
    }
}