package org.reflections.vfs;

import java.io.IOException;
import java.io.InputStream;
import org.reflections.util.ClasspathHelper;
import java.net.URLConnection;
import java.net.JarURLConnection;
import java.util.jar.JarFile;
import javax.annotation.Nullable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URISyntaxException;
import java.io.File;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import org.reflections.util.Utils;
import com.google.common.base.Predicate;
import java.util.Collection;
import com.google.common.collect.Lists;
import java.util.Iterator;
import org.reflections.ReflectionsException;
import org.reflections.Reflections;
import java.net.URL;
import java.util.List;

public abstract class Vfs
{
    private static List<UrlType> defaultUrlTypes;
    
    public Vfs() {
        super();
    }
    
    public static List<UrlType> getDefaultUrlTypes() {
        return Vfs.defaultUrlTypes;
    }
    
    public static void setDefaultURLTypes(final List<UrlType> defaultUrlTypes) {
        Vfs.defaultUrlTypes = defaultUrlTypes;
    }
    
    public static void addDefaultURLTypes(final UrlType urlType) {
        Vfs.defaultUrlTypes.add(0, urlType);
    }
    
    public static Dir fromURL(final URL url) {
        return fromURL(url, Vfs.defaultUrlTypes);
    }
    
    public static Dir fromURL(final URL url, final List<UrlType> list) {
        for (final UrlType urlType : list) {
            try {
                if (!urlType.matches(url)) {
                    continue;
                }
                final Dir dir = urlType.createDir(url);
                if (dir != null) {
                    return dir;
                }
                continue;
            }
            catch (Throwable t) {
                if (Reflections.log == null) {
                    continue;
                }
                Reflections.log.warn("could not create Dir using " + urlType + " from url " + url.toExternalForm() + ". skipping.", t);
            }
        }
        throw new ReflectionsException("could not create Vfs.Dir from url, no matching UrlType was found [" + url.toExternalForm() + "]\neither use fromURL(final URL url, final List<UrlType> urlTypes) or use the static setDefaultURLTypes(final List<UrlType> urlTypes) or addDefaultURLTypes(UrlType urlType) with your specialized UrlType.");
    }
    
    public static Dir fromURL(final URL url, final UrlType... elements) {
        return fromURL(url, Lists.<UrlType>newArrayList(elements));
    }
    
    public static Iterable<File> findFiles(final Collection<URL> collection, final String s, final Predicate<String> predicate) {
        return findFiles(collection, new Predicate<File>() {
            final /* synthetic */ String val$packagePrefix;
            final /* synthetic */ Predicate val$nameFilter;
            
            Vfs$1() {
                super();
            }
            
            @Override
            public boolean apply(final File file) {
                final String relativePath = file.getRelativePath();
                if (relativePath.startsWith(s)) {
                    final String substring = relativePath.substring(relativePath.indexOf(s) + s.length());
                    return !Utils.isEmpty(substring) && predicate.apply(substring.substring(1));
                }
                return false;
            }
            
            @Override
            public /* bridge */ boolean apply(final Object o) {
                return this.apply((File)o);
            }
        });
    }
    
    public static Iterable<File> findFiles(final Collection<URL> collection, final Predicate<File> predicate) {
        Iterable<Object> concat = new ArrayList<Object>();
        for (final URL url : collection) {
            try {
                concat = Iterables.<Object>concat((Iterable<?>)concat, (Iterable<?>)Iterables.<? extends T>filter((Iterable<? extends T>)new Iterable<File>() {
                    final /* synthetic */ URL val$url;
                    
                    Vfs$2() {
                        super();
                    }
                    
                    @Override
                    public Iterator<File> iterator() {
                        return Vfs.fromURL(url).getFiles().iterator();
                    }
                }, (Predicate<? super T>)predicate));
            }
            catch (Throwable t) {
                if (Reflections.log == null) {
                    continue;
                }
                Reflections.log.error("could not findFiles for url. continuing. [" + url + "]", t);
            }
        }
        return (Iterable<File>)concat;
    }
    
    @Nullable
    public static java.io.File getFile(final URL url) {
        try {
            final java.io.File file;
            if ((file = new java.io.File(url.toURI().getSchemeSpecificPart())).exists()) {
                return file;
            }
        }
        catch (URISyntaxException ex) {}
        try {
            String s = URLDecoder.decode(url.getPath(), "UTF-8");
            if (s.contains(".jar!")) {
                s = s.substring(0, s.lastIndexOf(".jar!") + ".jar".length());
            }
            final java.io.File file2;
            if ((file2 = new java.io.File(s)).exists()) {
                return file2;
            }
        }
        catch (UnsupportedEncodingException ex2) {}
        try {
            String s2 = url.toExternalForm();
            if (s2.startsWith("jar:")) {
                s2 = s2.substring("jar:".length());
            }
            if (s2.startsWith("wsjar:")) {
                s2 = s2.substring("wsjar:".length());
            }
            if (s2.startsWith("file:")) {
                s2 = s2.substring("file:".length());
            }
            if (s2.contains(".jar!")) {
                s2 = s2.substring(0, s2.indexOf(".jar!") + ".jar".length());
            }
            final java.io.File file3;
            if ((file3 = new java.io.File(s2)).exists()) {
                return file3;
            }
            final java.io.File file4;
            if ((file4 = new java.io.File(s2.replace("%20", " "))).exists()) {
                return file4;
            }
        }
        catch (Exception ex3) {}
        return null;
    }
    
    private static boolean hasJarFileInPath(final URL url) {
        return url.toExternalForm().matches(".*\\.jar(\\!.*|$)");
    }
    
    static /* synthetic */ boolean access$100(final URL url) {
        return hasJarFileInPath(url);
    }
    
    static {
        Vfs.defaultUrlTypes = (List<UrlType>)Lists.<DefaultUrlTypes>newArrayList(DefaultUrlTypes.values());
    }
}
