package org.reflections.vfs;

import java.net.URL;

enum Vfs$DefaultUrlTypes$5
{
    Vfs$DefaultUrlTypes$5(final String s, final int n) {
    }
    
    @Override
    public boolean matches(final URL url) throws Exception {
        return "vfszip".equals(url.getProtocol()) || "vfsfile".equals(url.getProtocol());
    }
    
    @Override
    public Dir createDir(final URL url) throws Exception {
        return new UrlTypeVFS().createDir(url);
    }
}