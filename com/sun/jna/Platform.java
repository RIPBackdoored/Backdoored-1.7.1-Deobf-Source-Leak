package com.sun.jna;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;

public final class Platform
{
    public static final int UNSPECIFIED = -1;
    public static final int MAC = 0;
    public static final int LINUX = 1;
    public static final int WINDOWS = 2;
    public static final int SOLARIS = 3;
    public static final int FREEBSD = 4;
    public static final int OPENBSD = 5;
    public static final int WINDOWSCE = 6;
    public static final int AIX = 7;
    public static final int ANDROID = 8;
    public static final int GNU = 9;
    public static final int KFREEBSD = 10;
    public static final int NETBSD = 11;
    public static final boolean RO_FIELDS;
    public static final boolean HAS_BUFFERS;
    public static final boolean HAS_AWT;
    public static final boolean HAS_JAWT;
    public static final String MATH_LIBRARY_NAME;
    public static final String C_LIBRARY_NAME;
    public static final boolean HAS_DLL_CALLBACKS;
    public static final String RESOURCE_PREFIX;
    private static final int osType;
    public static final String ARCH;
    
    private Platform() {
        super();
    }
    
    public static final int getOSType() {
        return Platform.osType;
    }
    
    public static final boolean isMac() {
        return Platform.osType == 0;
    }
    
    public static final boolean isAndroid() {
        return Platform.osType == 8;
    }
    
    public static final boolean isLinux() {
        return Platform.osType == 1;
    }
    
    public static final boolean isAIX() {
        return Platform.osType == 7;
    }
    
    @Deprecated
    public static final boolean isAix() {
        return isAIX();
    }
    
    public static final boolean isWindowsCE() {
        return Platform.osType == 6;
    }
    
    public static final boolean isWindows() {
        return Platform.osType == 2 || Platform.osType == 6;
    }
    
    public static final boolean isSolaris() {
        return Platform.osType == 3;
    }
    
    public static final boolean isFreeBSD() {
        return Platform.osType == 4;
    }
    
    public static final boolean isOpenBSD() {
        return Platform.osType == 5;
    }
    
    public static final boolean isNetBSD() {
        return Platform.osType == 11;
    }
    
    public static final boolean isGNU() {
        return Platform.osType == 9;
    }
    
    public static final boolean iskFreeBSD() {
        return Platform.osType == 10;
    }
    
    public static final boolean isX11() {
        return !isWindows() && !isMac();
    }
    
    public static final boolean hasRuntimeExec() {
        return !isWindowsCE() || !"J9".equals(System.getProperty("java.vm.name"));
    }
    
    public static final boolean is64Bit() {
        final String property = System.getProperty("sun.arch.data.model", System.getProperty("com.ibm.vm.bitmode"));
        if (property != null) {
            return "64".equals(property);
        }
        return "x86-64".equals(Platform.ARCH) || "ia64".equals(Platform.ARCH) || "ppc64".equals(Platform.ARCH) || "ppc64le".equals(Platform.ARCH) || "sparcv9".equals(Platform.ARCH) || "amd64".equals(Platform.ARCH) || Native.POINTER_SIZE == 8;
    }
    
    public static final boolean isIntel() {
        return Platform.ARCH.startsWith("x86");
    }
    
    public static final boolean isPPC() {
        return Platform.ARCH.startsWith("ppc");
    }
    
    public static final boolean isARM() {
        return Platform.ARCH.startsWith("arm");
    }
    
    public static final boolean isSPARC() {
        return Platform.ARCH.startsWith("sparc");
    }
    
    static String getCanonicalArchitecture(String trim, final boolean b) {
        trim = trim.toLowerCase().trim();
        if ("powerpc".equals(trim)) {
            trim = "ppc";
        }
        else if ("powerpc64".equals(trim)) {
            trim = "ppc64";
        }
        else if ("i386".equals(trim) || "i686".equals(trim)) {
            trim = "x86";
        }
        else if ("x86_64".equals(trim) || "amd64".equals(trim)) {
            trim = "x86-64";
        }
        if ("ppc64".equals(trim) && "little".equals(System.getProperty("sun.cpu.endian"))) {
            trim = "ppc64le";
        }
        if ("arm".equals(trim) && b) {
            trim = "armel";
        }
        return trim;
    }
    
    private static boolean isSoftFloat() {
        try {
            return ELFAnalyser.analyse(new File("/proc/self/exe").getCanonicalPath()).isArmSoftFloat();
        }
        catch (IOException ex) {
            Logger.getLogger(Platform.class.getName()).log(Level.FINE, null, ex);
            return false;
        }
    }
    
    static String getNativeLibraryResourcePrefix() {
        final String property = System.getProperty("jna.prefix");
        if (property != null) {
            return property;
        }
        return getNativeLibraryResourcePrefix(getOSType(), System.getProperty("os.arch"), System.getProperty("os.name"));
    }
    
    static String getNativeLibraryResourcePrefix(final int n, final String s, final String s2) {
        return getNativeLibraryResourcePrefix(n, s, s2, isSoftFloat());
    }
    
    static String getNativeLibraryResourcePrefix(final int n, String canonicalArchitecture, final String s, final boolean b) {
        canonicalArchitecture = getCanonicalArchitecture(canonicalArchitecture, b);
        String s2 = null;
        switch (n) {
            case 8:
                if (canonicalArchitecture.startsWith("arm")) {
                    canonicalArchitecture = "arm";
                }
                s2 = "android-" + canonicalArchitecture;
                break;
            case 2:
                s2 = "win32-" + canonicalArchitecture;
                break;
            case 6:
                s2 = "w32ce-" + canonicalArchitecture;
                break;
            case 0:
                s2 = "darwin";
                break;
            case 1:
                s2 = "linux-" + canonicalArchitecture;
                break;
            case 3:
                s2 = "sunos-" + canonicalArchitecture;
                break;
            case 4:
                s2 = "freebsd-" + canonicalArchitecture;
                break;
            case 5:
                s2 = "openbsd-" + canonicalArchitecture;
                break;
            case 11:
                s2 = "netbsd-" + canonicalArchitecture;
                break;
            case 10:
                s2 = "kfreebsd-" + canonicalArchitecture;
                break;
            default: {
                String s3 = s.toLowerCase();
                final int index = s3.indexOf(" ");
                if (index != -1) {
                    s3 = s3.substring(0, index);
                }
                s2 = s3 + "-" + canonicalArchitecture;
                break;
            }
        }
        return s2;
    }
    
    static {
        final String property = System.getProperty("os.name");
        if (property.startsWith("Linux")) {
            if ("dalvik".equals(System.getProperty("java.vm.name").toLowerCase())) {
                osType = 8;
                System.setProperty("jna.nounpack", "true");
            }
            else {
                osType = 1;
            }
        }
        else if (property.startsWith("AIX")) {
            osType = 7;
        }
        else if (property.startsWith("Mac") || property.startsWith("Darwin")) {
            osType = 0;
        }
        else if (property.startsWith("Windows CE")) {
            osType = 6;
        }
        else if (property.startsWith("Windows")) {
            osType = 2;
        }
        else if (property.startsWith("Solaris") || property.startsWith("SunOS")) {
            osType = 3;
        }
        else if (property.startsWith("FreeBSD")) {
            osType = 4;
        }
        else if (property.startsWith("OpenBSD")) {
            osType = 5;
        }
        else if (property.equalsIgnoreCase("gnu")) {
            osType = 9;
        }
        else if (property.equalsIgnoreCase("gnu/kfreebsd")) {
            osType = 10;
        }
        else if (property.equalsIgnoreCase("netbsd")) {
            osType = 11;
        }
        else {
            osType = -1;
        }
        boolean has_BUFFERS = false;
        try {
            Class.forName("java.nio.Buffer");
            has_BUFFERS = true;
        }
        catch (ClassNotFoundException ex) {}
        HAS_AWT = (Platform.osType != 6 && Platform.osType != 8 && Platform.osType != 7);
        HAS_JAWT = (Platform.HAS_AWT && Platform.osType != 0);
        HAS_BUFFERS = has_BUFFERS;
        RO_FIELDS = (Platform.osType != 6);
        C_LIBRARY_NAME = ((Platform.osType == 2) ? "msvcrt" : ((Platform.osType == 6) ? "coredll" : "c"));
        MATH_LIBRARY_NAME = ((Platform.osType == 2) ? "msvcrt" : ((Platform.osType == 6) ? "coredll" : "m"));
        HAS_DLL_CALLBACKS = (Platform.osType == 2);
        ARCH = getCanonicalArchitecture(System.getProperty("os.arch"), isSoftFloat());
        RESOURCE_PREFIX = getNativeLibraryResourcePrefix();
    }
}
