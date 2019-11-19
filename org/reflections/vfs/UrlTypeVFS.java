package org.reflections.vfs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.reflections.ReflectionsException;
import java.net.MalformedURLException;
import java.io.IOException;
import org.reflections.Reflections;
import java.util.jar.JarFile;
import java.net.URL;
import java.io.File;
import com.google.common.base.Predicate;

public class UrlTypeVFS implements Vfs.UrlType
{
    public static final String[] REPLACE_EXTENSION;
    final String VFSZIP = "vfszip";
    final String VFSFILE = "vfsfile";
    Predicate<java.io.File> realFile;
    
    public UrlTypeVFS() {
        super();
        this.realFile = new Predicate<java.io.File>() {
            final /* synthetic */ UrlTypeVFS this$0;
            
            UrlTypeVFS$1() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public boolean apply(final java.io.File file) {
                return file.exists() && file.isFile();
            }
            
            @Override
            public /* bridge */ boolean apply(final Object o) {
                return this.apply((java.io.File)o);
            }
        };
    }
    
    @Override
    public boolean matches(final URL url) {
        return "vfszip".equals(url.getProtocol()) || "vfsfile".equals(url.getProtocol());
    }
    
    @Override
    public Vfs.Dir createDir(final URL url) {
        try {
            return new ZipDir(new JarFile(this.adaptURL(url).getFile()));
        }
        catch (Exception ex) {
            try {
                return new ZipDir(new JarFile(url.getFile()));
            }
            catch (IOException ex2) {
                if (Reflections.log != null) {
                    Reflections.log.warn("Could not get URL", ex);
                    Reflections.log.warn("Could not get URL", ex2);
                }
                return null;
            }
        }
    }
    
    public URL adaptURL(final URL url) throws MalformedURLException {
        if ("vfszip".equals(url.getProtocol())) {
            return this.replaceZipSeparators(url.getPath(), this.realFile);
        }
        if ("vfsfile".equals(url.getProtocol())) {
            return new URL(url.toString().replace("vfsfile", "file"));
        }
        return url;
    }
    
    URL replaceZipSeparators(final String s, final Predicate<java.io.File> predicate) throws MalformedURLException {
        int i = 0;
        while (i != -1) {
            i = this.findFirstMatchOfDeployableExtention(s, i);
            if (i > 0) {
                if (predicate.apply(new java.io.File(s.substring(0, i - 1)))) {
                    return this.replaceZipSeparatorStartingFrom(s, i);
                }
                continue;
            }
        }
        throw new ReflectionsException("Unable to identify the real zip file in path '" + s + "'.");
    }
    
    int findFirstMatchOfDeployableExtention(final String s, final int n) {
        final Matcher matcher = Pattern.compile("\\.[ejprw]ar/").matcher(s);
        if (matcher.find(n)) {
            return matcher.end();
        }
        return -1;
    }
    
    URL replaceZipSeparatorStartingFrom(final String s, final int n) throws MalformedURLException {
        final String substring = s.substring(0, n - 1);
        String s2 = s.substring(n);
        int n2 = 1;
        for (final String s3 : UrlTypeVFS.REPLACE_EXTENSION) {
            while (s2.contains(s3)) {
                s2 = s2.replace(s3, s3.substring(0, 4) + "!");
                ++n2;
            }
        }
        String string = "";
        for (int j = 0; j < n2; ++j) {
            string += "zip:";
        }
        if (s2.trim().length() == 0) {
            return new URL(string + "/" + substring);
        }
        return new URL(string + "/" + substring + "!" + s2);
    }
    
    static {
        REPLACE_EXTENSION = new String[] { ".ear/", ".jar/", ".war/", ".sar/", ".har/", ".par/" };
    }
}
