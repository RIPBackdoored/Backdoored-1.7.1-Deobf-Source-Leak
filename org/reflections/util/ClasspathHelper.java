package org.reflections.util;

import java.util.LinkedHashMap;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.jar.Manifest;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.ServletContext;
import java.io.File;
import java.util.Arrays;
import java.net.URLClassLoader;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.io.IOException;
import java.util.ArrayList;
import java.net.URL;
import java.util.Collection;
import org.reflections.Reflections;

public abstract class ClasspathHelper
{
    public ClasspathHelper() {
        super();
    }
    
    public static ClassLoader contextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
    
    public static ClassLoader staticClassLoader() {
        return Reflections.class.getClassLoader();
    }
    
    public static ClassLoader[] classLoaders(final ClassLoader... array) {
        if (array != null && array.length != 0) {
            return array;
        }
        final ClassLoader contextClassLoader = contextClassLoader();
        final ClassLoader staticClassLoader = staticClassLoader();
        return (contextClassLoader != null) ? ((staticClassLoader != null && contextClassLoader != staticClassLoader) ? new ClassLoader[] { contextClassLoader, staticClassLoader } : new ClassLoader[] { contextClassLoader }) : new ClassLoader[0];
    }
    
    public static Collection<URL> forPackage(final String s, final ClassLoader... array) {
        return forResource(resourceName(s), array);
    }
    
    public static Collection<URL> forResource(final String s, final ClassLoader... array) {
        final ArrayList<URL> list = new ArrayList<URL>();
        for (final ClassLoader classLoader : classLoaders(array)) {
            try {
                final Enumeration<URL> resources = classLoader.getResources(s);
                while (resources.hasMoreElements()) {
                    final URL url = resources.nextElement();
                    final int lastIndex = url.toExternalForm().lastIndexOf(s);
                    if (lastIndex != -1) {
                        list.add(new URL(url, url.toExternalForm().substring(0, lastIndex)));
                    }
                    else {
                        list.add(url);
                    }
                }
            }
            catch (IOException ex) {
                if (Reflections.log != null) {
                    Reflections.log.error("error getting resources for " + s, ex);
                }
            }
        }
        return distinctUrls(list);
    }
    
    public static URL forClass(final Class<?> clazz, final ClassLoader... array) {
        final ClassLoader[] classLoaders = classLoaders(array);
        final String string = clazz.getName().replace(".", "/") + ".class";
        for (final ClassLoader classLoader : classLoaders) {
            try {
                final URL resource = classLoader.getResource(string);
                if (resource != null) {
                    return new URL(resource.toExternalForm().substring(0, resource.toExternalForm().lastIndexOf(clazz.getPackage().getName().replace(".", "/"))));
                }
            }
            catch (MalformedURLException ex) {
                if (Reflections.log != null) {
                    Reflections.log.warn("Could not get URL", ex);
                }
            }
        }
        return null;
    }
    
    public static Collection<URL> forClassLoader() {
        return forClassLoader(classLoaders(new ClassLoader[0]));
    }
    
    public static Collection<URL> forClassLoader(final ClassLoader... array) {
        final ArrayList<URL> list = new ArrayList<URL>();
        for (ClassLoader parent : classLoaders(array)) {
            while (parent != null) {
                if (parent instanceof URLClassLoader) {
                    final URL[] urLs = ((URLClassLoader)parent).getURLs();
                    if (urLs != null) {
                        list.addAll((Collection<?>)Arrays.<URL>asList(urLs));
                    }
                }
                parent = parent.getParent();
            }
        }
        return distinctUrls(list);
    }
    
    public static Collection<URL> forJavaClassPath() {
        final ArrayList<URL> list = new ArrayList<URL>();
        final String property = System.getProperty("java.class.path");
        if (property != null) {
            for (final String s : property.split(File.pathSeparator)) {
                try {
                    list.add(new File(s).toURI().toURL());
                }
                catch (Exception ex) {
                    if (Reflections.log != null) {
                        Reflections.log.warn("Could not get URL", ex);
                    }
                }
            }
        }
        return distinctUrls(list);
    }
    
    public static Collection<URL> forWebInfLib(final ServletContext servletContext) {
        final ArrayList<URL> list = new ArrayList<URL>();
        final Set resourcePaths = servletContext.getResourcePaths("/WEB-INF/lib");
        if (resourcePaths == null) {
            return list;
        }
        for (final String next : resourcePaths) {
            try {
                list.add(servletContext.getResource((String)next));
            }
            catch (MalformedURLException ex) {}
        }
        return distinctUrls(list);
    }
    
    public static URL forWebInfClasses(final ServletContext servletContext) {
        try {
            final String realPath = servletContext.getRealPath("/WEB-INF/classes");
            if (realPath == null) {
                return servletContext.getResource("/WEB-INF/classes");
            }
            final File file = new File(realPath);
            if (file.exists()) {
                return file.toURL();
            }
        }
        catch (MalformedURLException ex) {}
        return null;
    }
    
    public static Collection<URL> forManifest() {
        return forManifest(forClassLoader());
    }
    
    public static Collection<URL> forManifest(final URL url) {
        final ArrayList<URL> list = new ArrayList<URL>();
        list.add(url);
        try {
            final String cleanPath = cleanPath(url);
            final File file = new File(cleanPath);
            final JarFile jarFile = new JarFile(cleanPath);
            final URL tryToGetValidUrl = tryToGetValidUrl(file.getPath(), new File(cleanPath).getParent(), cleanPath);
            if (tryToGetValidUrl != null) {
                list.add(tryToGetValidUrl);
            }
            final Manifest manifest = jarFile.getManifest();
            if (manifest != null) {
                final String value = manifest.getMainAttributes().getValue(new Attributes.Name("Class-Path"));
                if (value != null) {
                    final String[] split = value.split(" ");
                    for (int length = split.length, i = 0; i < length; ++i) {
                        final URL tryToGetValidUrl2 = tryToGetValidUrl(file.getPath(), new File(cleanPath).getParent(), split[i]);
                        if (tryToGetValidUrl2 != null) {
                            list.add(tryToGetValidUrl2);
                        }
                    }
                }
            }
        }
        catch (IOException ex) {}
        return distinctUrls(list);
    }
    
    public static Collection<URL> forManifest(final Iterable<URL> iterable) {
        final ArrayList<URL> list = new ArrayList<URL>();
        final Iterator<URL> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            list.addAll((Collection<?>)forManifest(iterator.next()));
        }
        return distinctUrls(list);
    }
    
    static URL tryToGetValidUrl(final String s, final String s2, final String s3) {
        try {
            if (new File(s3).exists()) {
                return new File(s3).toURI().toURL();
            }
            if (new File(s2 + File.separator + s3).exists()) {
                return new File(s2 + File.separator + s3).toURI().toURL();
            }
            if (new File(s + File.separator + s3).exists()) {
                return new File(s + File.separator + s3).toURI().toURL();
            }
            if (new File(new URL(s3).getFile()).exists()) {
                return new File(new URL(s3).getFile()).toURI().toURL();
            }
        }
        catch (MalformedURLException ex) {}
        return null;
    }
    
    public static String cleanPath(final URL url) {
        String s = url.getPath();
        try {
            s = URLDecoder.decode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {}
        if (s.startsWith("jar:")) {
            s = s.substring("jar:".length());
        }
        if (s.startsWith("file:")) {
            s = s.substring("file:".length());
        }
        if (s.endsWith("!/")) {
            s = s.substring(0, s.lastIndexOf("!/")) + "/";
        }
        return s;
    }
    
    private static String resourceName(final String s) {
        if (s != null) {
            String s2 = s.replace(".", "/").replace("\\", "/");
            if (s2.startsWith("/")) {
                s2 = s2.substring(1);
            }
            return s2;
        }
        return null;
    }
    
    private static Collection<URL> distinctUrls(final Collection<URL> collection) {
        final LinkedHashMap<String, URL> linkedHashMap = new LinkedHashMap<String, URL>(collection.size());
        for (final URL url : collection) {
            linkedHashMap.put(url.toExternalForm(), url);
        }
        return linkedHashMap.values();
    }
}
