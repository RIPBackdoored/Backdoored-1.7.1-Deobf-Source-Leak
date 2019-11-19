package javassist;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.io.IOException;
import java.io.File;
import java.util.jar.JarFile;

final class JarClassPath implements ClassPath
{
    JarFile jarfile;
    String jarfileURL;
    
    JarClassPath(final String s) throws NotFoundException {
        super();
        try {
            this.jarfile = new JarFile(s);
            this.jarfileURL = new File(s).getCanonicalFile().toURI().toURL().toString();
        }
        catch (IOException ex) {
            throw new NotFoundException(s);
        }
    }
    
    @Override
    public InputStream openClassfile(final String s) throws NotFoundException {
        try {
            final JarEntry jarEntry = this.jarfile.getJarEntry(s.replace('.', '/') + ".class");
            if (jarEntry != null) {
                return this.jarfile.getInputStream(jarEntry);
            }
            return null;
        }
        catch (IOException ex) {
            throw new NotFoundException("broken jar file?: " + this.jarfile.getName());
        }
    }
    
    @Override
    public URL find(final String s) {
        final String string = s.replace('.', '/') + ".class";
        if (this.jarfile.getJarEntry(string) != null) {
            try {
                return new URL("jar:" + this.jarfileURL + "!/" + string);
            }
            catch (MalformedURLException ex) {}
        }
        return null;
    }
    
    @Override
    public void close() {
        try {
            this.jarfile.close();
            this.jarfile = null;
        }
        catch (IOException ex) {}
    }
    
    @Override
    public String toString() {
        return (this.jarfile == null) ? "<null>" : this.jarfile.toString();
    }
}
