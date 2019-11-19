package com.sun.jna;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.io.IOException;
import java.util.Collection;
import java.util.ArrayList;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.lang.ref.Reference;
import java.util.Map;

public class NativeLibrary
{
    private long handle;
    private final String libraryName;
    private final String libraryPath;
    private final Map<String, Function> functions;
    final int callFlags;
    private String encoding;
    final Map<String, ?> options;
    private static final Map<String, Reference<NativeLibrary>> libraries;
    private static final Map<String, List<String>> searchPaths;
    private static final List<String> librarySearchPath;
    private static final int DEFAULT_OPEN_OPTIONS = -1;
    
    private static String functionKey(final String s, final int n, final String s2) {
        return s + "|" + n + "|" + s2;
    }
    
    private NativeLibrary(final String s, final String libraryPath, final long handle, final Map<String, ?> options) {
        super();
        this.functions = new HashMap<String, Function>();
        this.libraryName = this.getLibraryName(s);
        this.libraryPath = libraryPath;
        this.handle = handle;
        final Object value = options.get("calling-convention");
        this.callFlags = ((value instanceof Number) ? ((Number)value).intValue() : 0);
        this.options = options;
        this.encoding = (String)options.get("string-encoding");
        if (this.encoding == null) {
            this.encoding = Native.getDefaultStringEncoding();
        }
        if (Platform.isWindows() && "kernel32".equals(this.libraryName.toLowerCase())) {
            synchronized (this.functions) {
                this.functions.put(functionKey("GetLastError", this.callFlags, this.encoding), new Function(this, "GetLastError", 63, this.encoding) {
                    final /* synthetic */ NativeLibrary this$0;
                    
                    NativeLibrary$1(final NativeLibrary nativeLibrary, final String s, final int n, final String s2) {
                        this.this$0 = this$0;
                        super(nativeLibrary, s, n, s2);
                    }
                    
                    @Override
                    Object invoke(final Object[] array, final Class<?> clazz, final boolean b, final int n) {
                        return Native.getLastError();
                    }
                    
                    @Override
                    Object invoke(final Method method, final Class<?>[] array, final Class<?> clazz, final Object[] array2, final Map<String, ?> map) {
                        return Native.getLastError();
                    }
                });
            }
        }
    }
    
    private static int openFlags(final Map<String, ?> map) {
        final Object value = map.get("open-flags");
        if (value instanceof Number) {
            return ((Number)value).intValue();
        }
        return -1;
    }
    
    private static NativeLibrary loadLibrary(final String s, final Map<String, ?> map) {
        if (Native.DEBUG_LOAD) {
            System.out.println("Looking for library '" + s + "'");
        }
        new File(s).isAbsolute();
        final ArrayList<String> list = new ArrayList<String>();
        final int openFlags = openFlags(map);
        final String webStartLibraryPath = Native.getWebStartLibraryPath(s);
        if (webStartLibraryPath != null) {
            if (Native.DEBUG_LOAD) {
                System.out.println("Adding web start path " + webStartLibraryPath);
            }
            list.add(webStartLibraryPath);
        }
        final List<String> list2 = NativeLibrary.searchPaths.get(s);
        if (list2 != null) {
            synchronized (list2) {
                list.addAll(0, (Collection<?>)list2);
            }
        }
        if (Native.DEBUG_LOAD) {
            System.out.println("Adding paths from jna.library.path: " + System.getProperty("jna.library.path"));
        }
        list.addAll((Collection<?>)initPaths("jna.library.path"));
        String s2 = findLibraryPath(s, list);
        long n = 0L;
        try {
            if (Native.DEBUG_LOAD) {
                System.out.println("Trying " + s2);
            }
            n = Native.open(s2, openFlags);
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError6) {
            if (Native.DEBUG_LOAD) {
                System.out.println("Adding system paths: " + NativeLibrary.librarySearchPath);
            }
            list.addAll((Collection<?>)NativeLibrary.librarySearchPath);
        }
        try {
            if (n == 0L) {
                s2 = findLibraryPath(s, list);
                if (Native.DEBUG_LOAD) {
                    System.out.println("Trying " + s2);
                }
                n = Native.open(s2, openFlags);
                if (n == 0L) {
                    throw new UnsatisfiedLinkError("Failed to load library '" + s + "'");
                }
            }
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            if (Platform.isAndroid()) {
                try {
                    if (Native.DEBUG_LOAD) {
                        System.out.println("Preload (via System.loadLibrary) " + s);
                    }
                    System.loadLibrary(s);
                    n = Native.open(s2, openFlags);
                }
                catch (UnsatisfiedLinkError unsatisfiedLinkError2) {
                    unsatisfiedLinkError = unsatisfiedLinkError2;
                }
            }
            else if (Platform.isLinux() || Platform.isFreeBSD()) {
                if (Native.DEBUG_LOAD) {
                    System.out.println("Looking for version variants");
                }
                s2 = matchLibrary(s, list);
                if (s2 != null) {
                    if (Native.DEBUG_LOAD) {
                        System.out.println("Trying " + s2);
                    }
                    try {
                        n = Native.open(s2, openFlags);
                    }
                    catch (UnsatisfiedLinkError unsatisfiedLinkError3) {
                        unsatisfiedLinkError = unsatisfiedLinkError3;
                    }
                }
            }
            else if (Platform.isMac() && !s.endsWith(".dylib")) {
                if (Native.DEBUG_LOAD) {
                    System.out.println("Looking for matching frameworks");
                }
                s2 = matchFramework(s);
                if (s2 != null) {
                    try {
                        if (Native.DEBUG_LOAD) {
                            System.out.println("Trying " + s2);
                        }
                        n = Native.open(s2, openFlags);
                    }
                    catch (UnsatisfiedLinkError unsatisfiedLinkError4) {
                        unsatisfiedLinkError = unsatisfiedLinkError4;
                    }
                }
            }
            else if (Platform.isWindows()) {
                if (Native.DEBUG_LOAD) {
                    System.out.println("Looking for lib- prefix");
                }
                s2 = findLibraryPath("lib" + s, list);
                if (s2 != null) {
                    if (Native.DEBUG_LOAD) {
                        System.out.println("Trying " + s2);
                    }
                    try {
                        n = Native.open(s2, openFlags);
                    }
                    catch (UnsatisfiedLinkError unsatisfiedLinkError5) {
                        unsatisfiedLinkError = unsatisfiedLinkError5;
                    }
                }
            }
            if (n == 0L) {
                try {
                    final File fromResourcePath = Native.extractFromResourcePath(s, (ClassLoader)map.get("classloader"));
                    try {
                        n = Native.open(fromResourcePath.getAbsolutePath(), openFlags);
                        s2 = fromResourcePath.getAbsolutePath();
                    }
                    finally {
                        if (Native.isUnpacked(fromResourcePath)) {
                            Native.deleteLibrary(fromResourcePath);
                        }
                    }
                }
                catch (IOException ex) {
                    unsatisfiedLinkError = new UnsatisfiedLinkError(ex.getMessage());
                }
            }
            if (n == 0L) {
                throw new UnsatisfiedLinkError("Unable to load library '" + s + "': " + unsatisfiedLinkError.getMessage());
            }
        }
        if (Native.DEBUG_LOAD) {
            System.out.println("Found library '" + s + "' at " + s2);
        }
        return new NativeLibrary(s, s2, n, map);
    }
    
    static String matchFramework(final String s) {
        final File file = new File(s);
        if (file.isAbsolute()) {
            if (s.indexOf(".framework") != -1 && file.exists()) {
                return file.getAbsolutePath();
            }
            final File file2 = new File(new File(file.getParentFile(), file.getName() + ".framework"), file.getName());
            if (file2.exists()) {
                return file2.getAbsolutePath();
            }
        }
        else {
            final String[] array = { System.getProperty("user.home"), "", "/System" };
            final String s2 = (s.indexOf(".framework") == -1) ? (s + ".framework/" + s) : s;
            for (int i = 0; i < array.length; ++i) {
                final String string = array[i] + "/Library/Frameworks/" + s2;
                if (new File(string).exists()) {
                    return string;
                }
            }
        }
        return null;
    }
    
    private String getLibraryName(final String s) {
        String s2 = s;
        final String mapSharedLibraryName = mapSharedLibraryName("---");
        final int index = mapSharedLibraryName.indexOf("---");
        if (index > 0 && s2.startsWith(mapSharedLibraryName.substring(0, index))) {
            s2 = s2.substring(index);
        }
        final int index2 = s2.indexOf(mapSharedLibraryName.substring(index + "---".length()));
        if (index2 != -1) {
            s2 = s2.substring(0, index2);
        }
        return s2;
    }
    
    public static final NativeLibrary getInstance(final String s) {
        return getInstance(s, Collections.<String, ?>emptyMap());
    }
    
    public static final NativeLibrary getInstance(final String s, final ClassLoader classLoader) {
        return getInstance(s, Collections.<String, ClassLoader>singletonMap("classloader", classLoader));
    }
    
    public static final NativeLibrary getInstance(String s, final Map<String, ?> map) {
        final HashMap<Object, Object> hashMap = new HashMap<Object, Object>(map);
        if (hashMap.get("calling-convention") == null) {
            hashMap.put("calling-convention", 0);
        }
        if ((Platform.isLinux() || Platform.isFreeBSD() || Platform.isAIX()) && Platform.C_LIBRARY_NAME.equals(s)) {
            s = null;
        }
        synchronized (NativeLibrary.libraries) {
            final Reference<NativeLibrary> reference = NativeLibrary.libraries.get(s + hashMap);
            NativeLibrary loadLibrary = (reference != null) ? reference.get() : null;
            if (loadLibrary == null) {
                if (s == null) {
                    loadLibrary = new NativeLibrary("<process>", null, Native.open(null, openFlags((Map<String, ?>)hashMap)), (Map<String, ?>)hashMap);
                }
                else {
                    loadLibrary = loadLibrary(s, (Map<String, ?>)hashMap);
                }
                final WeakReference weakReference = new WeakReference<NativeLibrary>(loadLibrary);
                NativeLibrary.libraries.put(loadLibrary.getName() + hashMap, (WeakReference<NativeLibrary>)weakReference);
                final File file = loadLibrary.getFile();
                if (file != null) {
                    NativeLibrary.libraries.put(file.getAbsolutePath() + hashMap, (WeakReference<NativeLibrary>)weakReference);
                    NativeLibrary.libraries.put(file.getName() + hashMap, (WeakReference<NativeLibrary>)weakReference);
                }
            }
            return loadLibrary;
        }
    }
    
    public static final synchronized NativeLibrary getProcess() {
        return getInstance(null);
    }
    
    public static final synchronized NativeLibrary getProcess(final Map<String, ?> map) {
        return getInstance(null, map);
    }
    
    public static final void addSearchPath(final String s, final String s2) {
        synchronized (NativeLibrary.searchPaths) {
            List<String> synchronizedList = NativeLibrary.searchPaths.get(s);
            if (synchronizedList == null) {
                synchronizedList = Collections.<String>synchronizedList(new ArrayList<String>());
                NativeLibrary.searchPaths.put(s, synchronizedList);
            }
            synchronizedList.add(s2);
        }
    }
    
    public Function getFunction(final String s) {
        return this.getFunction(s, this.callFlags);
    }
    
    Function getFunction(String s, final Method method) {
        final FunctionMapper functionMapper = (FunctionMapper)this.options.get("function-mapper");
        if (functionMapper != null) {
            s = functionMapper.getFunctionName(this, method);
        }
        final String property = System.getProperty("jna.profiler.prefix", "$$YJP$$");
        if (s.startsWith(property)) {
            s = s.substring(property.length());
        }
        int callFlags = this.callFlags;
        final Class<?>[] exceptionTypes = method.getExceptionTypes();
        for (int i = 0; i < exceptionTypes.length; ++i) {
            if (LastErrorException.class.isAssignableFrom(exceptionTypes[i])) {
                callFlags |= 0x40;
            }
        }
        return this.getFunction(s, callFlags);
    }
    
    public Function getFunction(final String s, final int n) {
        return this.getFunction(s, n, this.encoding);
    }
    
    public Function getFunction(final String s, final int n, final String s2) {
        if (s == null) {
            throw new NullPointerException("Function name may not be null");
        }
        synchronized (this.functions) {
            final String functionKey = functionKey(s, n, s2);
            Function function = this.functions.get(functionKey);
            if (function == null) {
                function = new Function(this, s, n, s2);
                this.functions.put(functionKey, function);
            }
            return function;
        }
    }
    
    public Map<String, ?> getOptions() {
        return this.options;
    }
    
    public Pointer getGlobalVariableAddress(final String s) {
        try {
            return new Pointer(this.getSymbolAddress(s));
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            throw new UnsatisfiedLinkError("Error looking up '" + s + "': " + unsatisfiedLinkError.getMessage());
        }
    }
    
    long getSymbolAddress(final String s) {
        if (this.handle == 0L) {
            throw new UnsatisfiedLinkError("Library has been unloaded");
        }
        return Native.findSymbol(this.handle, s);
    }
    
    @Override
    public String toString() {
        return "Native Library <" + this.libraryPath + "@" + this.handle + ">";
    }
    
    public String getName() {
        return this.libraryName;
    }
    
    public File getFile() {
        if (this.libraryPath == null) {
            return null;
        }
        return new File(this.libraryPath);
    }
    
    @Override
    protected void finalize() {
        this.dispose();
    }
    
    static void disposeAll() {
        final LinkedHashSet<Reference<NativeLibrary>> set;
        synchronized (NativeLibrary.libraries) {
            set = (LinkedHashSet<Reference<NativeLibrary>>)new LinkedHashSet<Object>(NativeLibrary.libraries.values());
        }
        final Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()) {
            final NativeLibrary nativeLibrary = iterator.next().get();
            if (nativeLibrary != null) {
                nativeLibrary.dispose();
            }
        }
    }
    
    public void dispose() {
        final HashSet<String> set = new HashSet<String>();
        synchronized (NativeLibrary.libraries) {
            for (final Map.Entry<String, Reference<NativeLibrary>> entry : NativeLibrary.libraries.entrySet()) {
                if (entry.getValue().get() == this) {
                    set.add(entry.getKey());
                }
            }
            final Iterator<Object> iterator2 = set.iterator();
            while (iterator2.hasNext()) {
                NativeLibrary.libraries.remove(iterator2.next());
            }
        }
        synchronized (this) {
            if (this.handle != 0L) {
                Native.close(this.handle);
                this.handle = 0L;
            }
        }
    }
    
    private static List<String> initPaths(final String s) {
        final String property = System.getProperty(s, "");
        if ("".equals(property)) {
            return Collections.<String>emptyList();
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(property, File.pathSeparator);
        final ArrayList<String> list = new ArrayList<String>();
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (!"".equals(nextToken)) {
                list.add(nextToken);
            }
        }
        return list;
    }
    
    private static String findLibraryPath(final String s, final List<String> list) {
        if (new File(s).isAbsolute()) {
            return s;
        }
        final String mapSharedLibraryName = mapSharedLibraryName(s);
        for (final String s2 : list) {
            final File file = new File(s2, mapSharedLibraryName);
            if (file.exists()) {
                return file.getAbsolutePath();
            }
            if (!Platform.isMac() || !mapSharedLibraryName.endsWith(".dylib")) {
                continue;
            }
            final File file2 = new File(s2, mapSharedLibraryName.substring(0, mapSharedLibraryName.lastIndexOf(".dylib")) + ".jnilib");
            if (file2.exists()) {
                return file2.getAbsolutePath();
            }
        }
        return mapSharedLibraryName;
    }
    
    static String mapSharedLibraryName(final String s) {
        if (!Platform.isMac()) {
            if (Platform.isLinux() || Platform.isFreeBSD()) {
                if (isVersionedName(s) || s.endsWith(".so")) {
                    return s;
                }
            }
            else if (Platform.isAIX()) {
                if (s.startsWith("lib")) {
                    return s;
                }
            }
            else if (Platform.isWindows() && (s.endsWith(".drv") || s.endsWith(".dll"))) {
                return s;
            }
            return System.mapLibraryName(s);
        }
        if (s.startsWith("lib") && (s.endsWith(".dylib") || s.endsWith(".jnilib"))) {
            return s;
        }
        final String mapLibraryName = System.mapLibraryName(s);
        if (mapLibraryName.endsWith(".jnilib")) {
            return mapLibraryName.substring(0, mapLibraryName.lastIndexOf(".jnilib")) + ".dylib";
        }
        return mapLibraryName;
    }
    
    private static boolean isVersionedName(final String s) {
        if (s.startsWith("lib")) {
            final int lastIndex = s.lastIndexOf(".so.");
            if (lastIndex != -1 && lastIndex + 4 < s.length()) {
                for (int i = lastIndex + 4; i < s.length(); ++i) {
                    final char char1 = s.charAt(i);
                    if (!Character.isDigit(char1) && char1 != '.') {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    static String matchLibrary(final String s, List<String> list) {
        final File file = new File(s);
        if (file.isAbsolute()) {
            list = Arrays.<String>asList(file.getParent());
        }
        final FilenameFilter filenameFilter = new FilenameFilter() {
            final /* synthetic */ String val$libName;
            
            NativeLibrary$2() {
                super();
            }
            
            @Override
            public boolean accept(final File file, final String s) {
                return (s.startsWith("lib" + s + ".so") || (s.startsWith(s + ".so") && s.startsWith("lib"))) && isVersionedName(s);
            }
        };
        final LinkedList<File> list2 = new LinkedList<File>();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final File[] listFiles = new File(iterator.next()).listFiles(filenameFilter);
            if (listFiles != null && listFiles.length > 0) {
                list2.addAll((Collection<?>)Arrays.<File>asList(listFiles));
            }
        }
        double n = -1.0;
        String s2 = null;
        final Iterator<Object> iterator2 = list2.iterator();
        while (iterator2.hasNext()) {
            final String absolutePath = iterator2.next().getAbsolutePath();
            final double version = parseVersion(absolutePath.substring(absolutePath.lastIndexOf(".so.") + 4));
            if (version > n) {
                n = version;
                s2 = absolutePath;
            }
        }
        return s2;
    }
    
    static double parseVersion(String substring) {
        double n = 0.0;
        double n2 = 1.0;
        int n3 = substring.indexOf(".");
        while (substring != null) {
            String substring2;
            if (n3 != -1) {
                substring2 = substring.substring(0, n3);
                substring = substring.substring(n3 + 1);
                n3 = substring.indexOf(".");
            }
            else {
                substring2 = substring;
                substring = null;
            }
            try {
                n += Integer.parseInt(substring2) / n2;
            }
            catch (NumberFormatException ex) {
                return 0.0;
            }
            n2 *= 100.0;
        }
        return n;
    }
    
    private static String getMultiArchPath() {
        String arch = Platform.ARCH;
        final String s = Platform.iskFreeBSD() ? "-kfreebsd" : (Platform.isGNU() ? "" : "-linux");
        String s2 = "-gnu";
        if (Platform.isIntel()) {
            arch = (Platform.is64Bit() ? "x86_64" : "i386");
        }
        else if (Platform.isPPC()) {
            arch = (Platform.is64Bit() ? "powerpc64" : "powerpc");
        }
        else if (Platform.isARM()) {
            arch = "arm";
            s2 = "-gnueabi";
        }
        return arch + s + s2;
    }
    
    private static ArrayList<String> getLinuxLdPaths() {
        final ArrayList<String> list = new ArrayList<String>();
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("/sbin/ldconfig -p").getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final int index = line.indexOf(" => ");
                final int lastIndex = line.lastIndexOf(47);
                if (index != -1 && lastIndex != -1 && index < lastIndex) {
                    final String substring = line.substring(index + 4, lastIndex);
                    if (list.contains(substring)) {
                        continue;
                    }
                    list.add(substring);
                }
            }
            bufferedReader.close();
        }
        catch (Exception ex) {}
        return list;
    }
    
    static /* synthetic */ boolean access$000(final String s) {
        return isVersionedName(s);
    }
    
    static {
        libraries = new HashMap<String, Reference<NativeLibrary>>();
        searchPaths = Collections.<String, List<String>>synchronizedMap(new HashMap<String, List<String>>());
        librarySearchPath = new ArrayList<String>();
        if (Native.POINTER_SIZE == 0) {
            throw new Error("Native library not initialized");
        }
        final String webStartLibraryPath = Native.getWebStartLibraryPath("jnidispatch");
        if (webStartLibraryPath != null) {
            NativeLibrary.librarySearchPath.add(webStartLibraryPath);
        }
        if (System.getProperty("jna.platform.library.path") == null && !Platform.isWindows()) {
            String string = "";
            String pathSeparator = "";
            String string2 = "";
            if (Platform.isLinux() || Platform.isSolaris() || Platform.isFreeBSD() || Platform.iskFreeBSD()) {
                string2 = (Platform.isSolaris() ? "/" : "") + Pointer.SIZE * 8;
            }
            String[] array = { "/usr/lib" + string2, "/lib" + string2, "/usr/lib", "/lib" };
            if (Platform.isLinux() || Platform.iskFreeBSD() || Platform.isGNU()) {
                final String multiArchPath = getMultiArchPath();
                array = new String[] { "/usr/lib/" + multiArchPath, "/lib/" + multiArchPath, "/usr/lib" + string2, "/lib" + string2, "/usr/lib", "/lib" };
            }
            if (Platform.isLinux()) {
                final ArrayList<String> linuxLdPaths = getLinuxLdPaths();
                for (int n = array.length - 1; 0 <= n; --n) {
                    final int index = linuxLdPaths.indexOf(array[n]);
                    if (index != -1) {
                        linuxLdPaths.remove(index);
                    }
                    linuxLdPaths.add(0, array[n]);
                }
                array = linuxLdPaths.<String>toArray(new String[linuxLdPaths.size()]);
            }
            for (int i = 0; i < array.length; ++i) {
                final File file = new File(array[i]);
                if (file.exists() && file.isDirectory()) {
                    string = string + pathSeparator + array[i];
                    pathSeparator = File.pathSeparator;
                }
            }
            if (!"".equals(string)) {
                System.setProperty("jna.platform.library.path", string);
            }
        }
        NativeLibrary.librarySearchPath.addAll(initPaths("jna.platform.library.path"));
    }
}
