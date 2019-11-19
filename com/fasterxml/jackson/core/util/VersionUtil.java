package com.fasterxml.jackson.core.util;

import java.io.InputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.Version;
import java.util.regex.Pattern;

public class VersionUtil
{
    private static final Pattern V_SEP;
    
    protected VersionUtil() {
        super();
    }
    
    @Deprecated
    public Version version() {
        return Version.unknownVersion();
    }
    
    public static Version versionFor(final Class<?> clazz) {
        final Version packageVersion = packageVersionFor(clazz);
        return (packageVersion == null) ? Version.unknownVersion() : packageVersion;
    }
    
    public static Version packageVersionFor(final Class<?> clazz) {
        Version version = null;
        try {
            final Class<?> forName = Class.forName(clazz.getPackage().getName() + ".PackageVersion", true, clazz.getClassLoader());
            try {
                version = ((Versioned)forName.getDeclaredConstructor((Class<?>[])new Class[0]).newInstance(new Object[0])).version();
            }
            catch (Exception ex) {
                throw new IllegalArgumentException("Failed to get Versioned out of " + forName);
            }
        }
        catch (Exception ex2) {}
        return (version == null) ? Version.unknownVersion() : version;
    }
    
    @Deprecated
    public static Version mavenVersionFor(final ClassLoader classLoader, final String s, final String s2) {
        final InputStream resourceAsStream = classLoader.getResourceAsStream("META-INF/maven/" + s.replaceAll("\\.", "/") + "/" + s2 + "/pom.properties");
        if (resourceAsStream != null) {
            try {
                final Properties properties = new Properties();
                properties.load(resourceAsStream);
                return parseVersion(properties.getProperty("version"), properties.getProperty("groupId"), properties.getProperty("artifactId"));
            }
            catch (IOException ex) {}
            finally {
                _close(resourceAsStream);
            }
        }
        return Version.unknownVersion();
    }
    
    public static Version parseVersion(String trim, final String s, final String s2) {
        if (trim != null && (trim = trim.trim()).length() > 0) {
            final String[] split = VersionUtil.V_SEP.split(trim);
            return new Version(parseVersionPart(split[0]), (split.length > 1) ? parseVersionPart(split[1]) : 0, (split.length > 2) ? parseVersionPart(split[2]) : 0, (split.length > 3) ? split[3] : null, s, s2);
        }
        return Version.unknownVersion();
    }
    
    protected static int parseVersionPart(final String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 > '9') {
                break;
            }
            if (char1 < '0') {
                break;
            }
            n = n * 10 + (char1 - '0');
        }
        return n;
    }
    
    private static final void _close(final Closeable closeable) {
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
    
    public static final void throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }
    
    static {
        V_SEP = Pattern.compile("[-_./;:]");
    }
}
