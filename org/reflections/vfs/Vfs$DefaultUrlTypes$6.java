package org.reflections.vfs;

import org.reflections.util.ClasspathHelper;
import java.net.URL;

enum Vfs$DefaultUrlTypes$6
{
    Vfs$DefaultUrlTypes$6(final String s, final int n) {
    }
    
    @Override
    public boolean matches(final URL url) throws Exception {
        return url.getProtocol().startsWith("bundle");
    }
    
    @Override
    public Dir createDir(final URL url) throws Exception {
        return Vfs.fromURL((URL)ClasspathHelper.contextClassLoader().loadClass("org.eclipse.core.runtime.FileLocator").getMethod("resolve", URL.class).invoke(null, url));
    }
}