package org.reflections.vfs;

import org.reflections.util.ClasspathHelper;
import java.io.File;
import java.net.URLConnection;
import java.net.JarURLConnection;
import java.util.jar.JarFile;
import java.net.URL;

public enum DefaultUrlTypes implements UrlType
{
    jarFile {
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
    }, 
    jarUrl {
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
    }, 
    directory {
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
    }, 
    jboss_vfs {
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
    }, 
    jboss_vfsfile {
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
    }, 
    bundle {
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
    }, 
    jarInputStream {
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
    };
    
    private static final /* synthetic */ DefaultUrlTypes[] $VALUES;
    
    public static DefaultUrlTypes[] values() {
        return DefaultUrlTypes.$VALUES.clone();
    }
    
    public static DefaultUrlTypes valueOf(final String s) {
        return Enum.<DefaultUrlTypes>valueOf(DefaultUrlTypes.class, s);
    }
    
    DefaultUrlTypes(final String s, final int n, final Vfs$1 predicate) {
        this();
    }
    
    static {
        $VALUES = new DefaultUrlTypes[] { DefaultUrlTypes.jarFile, DefaultUrlTypes.jarUrl, DefaultUrlTypes.directory, DefaultUrlTypes.jboss_vfs, DefaultUrlTypes.jboss_vfsfile, DefaultUrlTypes.bundle, DefaultUrlTypes.jarInputStream };
    }
}
