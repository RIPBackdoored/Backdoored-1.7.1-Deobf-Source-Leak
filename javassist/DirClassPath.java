package javassist;

import java.io.IOException;
import java.net.MalformedURLException;
import java.io.File;
import java.net.URL;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.InputStream;

final class DirClassPath implements ClassPath
{
    String directory;
    
    DirClassPath(final String directory) {
        super();
        this.directory = directory;
    }
    
    @Override
    public InputStream openClassfile(final String s) {
        try {
            final char c;
            return new FileInputStream((this.directory + c + s.replace('.', c) + ".class").toString());
        }
        catch (FileNotFoundException ex) {}
        catch (SecurityException ex2) {}
        return null;
    }
    
    @Override
    public URL find(final String s) {
        final char c;
        final File file = new File(this.directory + c + s.replace('.', c) + ".class");
        if (file.exists()) {
            try {
                return file.getCanonicalFile().toURI().toURL();
            }
            catch (MalformedURLException ex) {}
            catch (IOException ex2) {}
        }
        return null;
    }
    
    @Override
    public void close() {
    }
    
    @Override
    public String toString() {
        return this.directory;
    }
}
