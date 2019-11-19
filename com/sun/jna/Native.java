package com.sun.jna;

import java.awt.GraphicsEnvironment;
import java.util.WeakHashMap;
import java.nio.charset.Charset;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.lang.reflect.Array;
import java.io.FilenameFilter;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.lang.reflect.Method;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.net.URLClassLoader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.lang.reflect.Field;
import java.lang.ref.WeakReference;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.nio.Buffer;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Window;
import java.io.File;
import java.lang.ref.Reference;
import java.util.Map;

public final class Native implements Version
{
    public static final String DEFAULT_ENCODING;
    public static boolean DEBUG_LOAD;
    public static boolean DEBUG_JNA_LOAD;
    static String jnidispatchPath;
    private static final Map<Class<?>, Map<String, Object>> typeOptions;
    private static final Map<Class<?>, Reference<?>> libraries;
    private static final String _OPTION_ENCLOSING_LIBRARY = "enclosing-library";
    private static final Callback.UncaughtExceptionHandler DEFAULT_HANDLER;
    private static Callback.UncaughtExceptionHandler callbackExceptionHandler;
    public static final int POINTER_SIZE;
    public static final int LONG_SIZE;
    public static final int WCHAR_SIZE;
    public static final int SIZE_T_SIZE;
    public static final int BOOL_SIZE;
    private static final int TYPE_VOIDP = 0;
    private static final int TYPE_LONG = 1;
    private static final int TYPE_WCHAR_T = 2;
    private static final int TYPE_SIZE_T = 3;
    private static final int TYPE_BOOL = 4;
    static final int MAX_ALIGNMENT;
    static final int MAX_PADDING;
    private static final Object finalizer;
    static final String JNA_TMPLIB_PREFIX = "jna";
    private static Map<Class<?>, long[]> registeredClasses;
    private static Map<Class<?>, NativeLibrary> registeredLibraries;
    static final int CB_HAS_INITIALIZER = 1;
    private static final int CVT_UNSUPPORTED = -1;
    private static final int CVT_DEFAULT = 0;
    private static final int CVT_POINTER = 1;
    private static final int CVT_STRING = 2;
    private static final int CVT_STRUCTURE = 3;
    private static final int CVT_STRUCTURE_BYVAL = 4;
    private static final int CVT_BUFFER = 5;
    private static final int CVT_ARRAY_BYTE = 6;
    private static final int CVT_ARRAY_SHORT = 7;
    private static final int CVT_ARRAY_CHAR = 8;
    private static final int CVT_ARRAY_INT = 9;
    private static final int CVT_ARRAY_LONG = 10;
    private static final int CVT_ARRAY_FLOAT = 11;
    private static final int CVT_ARRAY_DOUBLE = 12;
    private static final int CVT_ARRAY_BOOLEAN = 13;
    private static final int CVT_BOOLEAN = 14;
    private static final int CVT_CALLBACK = 15;
    private static final int CVT_FLOAT = 16;
    private static final int CVT_NATIVE_MAPPED = 17;
    private static final int CVT_NATIVE_MAPPED_STRING = 18;
    private static final int CVT_NATIVE_MAPPED_WSTRING = 19;
    private static final int CVT_WSTRING = 20;
    private static final int CVT_INTEGER_TYPE = 21;
    private static final int CVT_POINTER_TYPE = 22;
    private static final int CVT_TYPE_MAPPER = 23;
    private static final int CVT_TYPE_MAPPER_STRING = 24;
    private static final int CVT_TYPE_MAPPER_WSTRING = 25;
    static final int CB_OPTION_DIRECT = 1;
    static final int CB_OPTION_IN_DLL = 2;
    private static final ThreadLocal<Memory> nativeThreadTerminationFlag;
    private static final Map<Thread, Pointer> nativeThreads;
    
    @Deprecated
    public static float parseVersion(final String s) {
        return Float.parseFloat(s.substring(0, s.lastIndexOf(".")));
    }
    
    static boolean isCompatibleVersion(final String s, final String s2) {
        final String[] split = s.split("\\.");
        final String[] split2 = s2.split("\\.");
        if (split.length < 3 || split2.length < 3) {
            return false;
        }
        final int int1 = Integer.parseInt(split[0]);
        final int int2 = Integer.parseInt(split2[0]);
        final int int3 = Integer.parseInt(split[1]);
        final int int4 = Integer.parseInt(split2[1]);
        return int1 == int2 && int3 <= int4;
    }
    
    private static void dispose() {
        CallbackReference.disposeAll();
        Memory.disposeAll();
        NativeLibrary.disposeAll();
        unregisterAll();
        Native.jnidispatchPath = null;
        System.setProperty("jna.loaded", "false");
    }
    
    static boolean deleteLibrary(final File file) {
        if (file.delete()) {
            return true;
        }
        markTemporaryFile(file);
        return false;
    }
    
    private Native() {
        super();
    }
    
    private static native void initIDs();
    
    public static synchronized native void setProtected(final boolean p0);
    
    public static synchronized native boolean isProtected();
    
    @Deprecated
    public static void setPreserveLastError(final boolean b) {
    }
    
    @Deprecated
    public static boolean getPreserveLastError() {
        return true;
    }
    
    public static long getWindowID(final Window window) throws HeadlessException {
        return AWT.getWindowID(window);
    }
    
    public static long getComponentID(final Component component) throws HeadlessException {
        return AWT.getComponentID(component);
    }
    
    public static Pointer getWindowPointer(final Window window) throws HeadlessException {
        return new Pointer(AWT.getWindowID(window));
    }
    
    public static Pointer getComponentPointer(final Component component) throws HeadlessException {
        return new Pointer(AWT.getComponentID(component));
    }
    
    static native long getWindowHandle0(final Component p0);
    
    public static Pointer getDirectBufferPointer(final Buffer buffer) {
        final long getDirectBufferPointer = _getDirectBufferPointer(buffer);
        return (getDirectBufferPointer == 0L) ? null : new Pointer(getDirectBufferPointer);
    }
    
    private static native long _getDirectBufferPointer(final Buffer p0);
    
    public static String toString(final byte[] array) {
        return toString(array, getDefaultStringEncoding());
    }
    
    public static String toString(final byte[] array, final String s) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (array[i] == 0) {
                length = i;
                break;
            }
        }
        return "";
    }
    
    public static String toString(final char[] array) {
        for (int length = array.length, n = 0; n < length && array[n] != '\0'; ++n) {}
        return "";
    }
    
    public static List<String> toStringList(final char[] array) {
        return toStringList(array, 0, array.length);
    }
    
    public static List<String> toStringList(final char[] array, final int n, final int n2) {
        final ArrayList<String> list = new ArrayList<String>();
        int n3 = n;
        final int n4 = n + n2;
        for (int i = n; i < n4; ++i) {
            if (array[i] == '\0') {
                if (n3 == i) {
                    return list;
                }
                list.add(new String(array, n3, i - n3));
                n3 = i + 1;
            }
        }
        if (n3 < n4) {
            list.add(new String(array, n3, n4 - n3));
        }
        return list;
    }
    
    public static <T> T loadLibrary(final Class<T> clazz) {
        return Native.<T>loadLibrary(null, clazz);
    }
    
    public static <T> T loadLibrary(final Class<T> clazz, final Map<String, ?> map) {
        return Native.<T>loadLibrary(null, clazz, map);
    }
    
    public static <T> T loadLibrary(final String s, final Class<T> clazz) {
        return Native.<T>loadLibrary(s, clazz, Collections.<String, ?>emptyMap());
    }
    
    public static <T> T loadLibrary(final String s, final Class<T> clazz, final Map<String, ?> map) {
        if (!Library.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("Interface (" + clazz.getSimpleName() + ") of library=" + s + " does not extend " + Library.class.getSimpleName());
        }
        final Object proxyInstance = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, new Library.Handler(s, clazz, map));
        cacheOptions(clazz, map, proxyInstance);
        return (T)clazz.cast(proxyInstance);
    }
    
    private static void loadLibraryInstance(final Class<?> clazz) {
        synchronized (Native.libraries) {
            if (clazz != null && !Native.libraries.containsKey(clazz)) {
                try {
                    final Field[] fields = clazz.getFields();
                    for (int i = 0; i < fields.length; ++i) {
                        final Field field = fields[i];
                        if (field.getType() == clazz && Modifier.isStatic(field.getModifiers())) {
                            Native.libraries.put(clazz, new WeakReference<Object>(field.get(null)));
                            break;
                        }
                    }
                }
                catch (Exception ex) {
                    throw new IllegalArgumentException("Could not access instance of " + clazz + " (" + ex + ")");
                }
            }
        }
    }
    
    static Class<?> findEnclosingLibraryClass(Class<?> callbackClass) {
        if (callbackClass == null) {
            return null;
        }
        synchronized (Native.libraries) {
            if (Native.typeOptions.containsKey(callbackClass)) {
                final Class<?> clazz = Native.typeOptions.get(callbackClass).get("enclosing-library");
                if (clazz != null) {
                    return clazz;
                }
                return callbackClass;
            }
        }
        if (Library.class.isAssignableFrom(callbackClass)) {
            return callbackClass;
        }
        if (Callback.class.isAssignableFrom(callbackClass)) {
            callbackClass = CallbackReference.findCallbackClass(callbackClass);
        }
        final Class<?> enclosingLibraryClass = findEnclosingLibraryClass(callbackClass.getDeclaringClass());
        if (enclosingLibraryClass != null) {
            return enclosingLibraryClass;
        }
        return findEnclosingLibraryClass(callbackClass.getSuperclass());
    }
    
    public static Map<String, Object> getLibraryOptions(final Class<?> clazz) {
        synchronized (Native.libraries) {
            final Map<String, Object> map = Native.typeOptions.get(clazz);
            if (map != null) {
                return map;
            }
        }
        Class<?> enclosingLibraryClass = findEnclosingLibraryClass(clazz);
        if (enclosingLibraryClass != null) {
            loadLibraryInstance(enclosingLibraryClass);
        }
        else {
            enclosingLibraryClass = clazz;
        }
        synchronized (Native.libraries) {
            final Map<String, Object> map2 = Native.typeOptions.get(enclosingLibraryClass);
            if (map2 != null) {
                Native.typeOptions.put(clazz, map2);
                return map2;
            }
            Map<? extends String, ?> emptyMap;
            try {
                final Field field = enclosingLibraryClass.getField("OPTIONS");
                field.setAccessible(true);
                emptyMap = (Map<? extends String, ?>)field.get(null);
                if (emptyMap == null) {
                    throw new IllegalStateException("Null options field");
                }
            }
            catch (NoSuchFieldException ex2) {
                emptyMap = Collections.<? extends String, ?>emptyMap();
            }
            catch (Exception ex) {
                throw new IllegalArgumentException("OPTIONS must be a public field of type java.util.Map (" + ex + "): " + enclosingLibraryClass);
            }
            final HashMap hashMap = new HashMap<String, Object>(emptyMap);
            if (!hashMap.containsKey("type-mapper")) {
                hashMap.put("type-mapper", lookupField(enclosingLibraryClass, "TYPE_MAPPER", TypeMapper.class));
            }
            if (!hashMap.containsKey("structure-alignment")) {
                hashMap.put("structure-alignment", lookupField(enclosingLibraryClass, "STRUCTURE_ALIGNMENT", Integer.class));
            }
            if (!hashMap.containsKey("string-encoding")) {
                hashMap.put("string-encoding", lookupField(enclosingLibraryClass, "STRING_ENCODING", String.class));
            }
            final Map<String, Object> cacheOptions = cacheOptions(enclosingLibraryClass, (Map<String, ?>)hashMap, null);
            if (clazz != enclosingLibraryClass) {
                Native.typeOptions.put(clazz, cacheOptions);
            }
            return cacheOptions;
        }
    }
    
    private static Object lookupField(final Class<?> clazz, final String s, final Class<?> clazz2) {
        try {
            final Field field = clazz.getField(s);
            field.setAccessible(true);
            return field.get(null);
        }
        catch (NoSuchFieldException ex2) {
            return null;
        }
        catch (Exception ex) {
            throw new IllegalArgumentException(s + " must be a public field of type " + clazz2.getName() + " (" + ex + "): " + clazz);
        }
    }
    
    public static TypeMapper getTypeMapper(final Class<?> clazz) {
        return getLibraryOptions(clazz).get("type-mapper");
    }
    
    public static String getStringEncoding(final Class<?> clazz) {
        final String s = getLibraryOptions(clazz).get("string-encoding");
        return (s != null) ? s : getDefaultStringEncoding();
    }
    
    public static String getDefaultStringEncoding() {
        return System.getProperty("jna.encoding", Native.DEFAULT_ENCODING);
    }
    
    public static int getStructureAlignment(final Class<?> clazz) {
        final Integer n = getLibraryOptions(clazz).get("structure-alignment");
        return (n == null) ? 0 : n;
    }
    
    static byte[] getBytes(final String s) {
        return getBytes(s, getDefaultStringEncoding());
    }
    
    static byte[] getBytes(final String s, final String s2) {
        if (s2 != null) {
            try {
                return s.getBytes(s2);
            }
            catch (UnsupportedEncodingException ex) {
                System.err.println("JNA Warning: Encoding '" + s2 + "' is unsupported");
            }
        }
        System.err.println("JNA Warning: Encoding with fallback " + System.getProperty("file.encoding"));
        return s.getBytes();
    }
    
    public static byte[] toByteArray(final String s) {
        return toByteArray(s, getDefaultStringEncoding());
    }
    
    public static byte[] toByteArray(final String s, final String s2) {
        final byte[] bytes = getBytes(s, s2);
        final byte[] array = new byte[bytes.length + 1];
        System.arraycopy(bytes, 0, array, 0, bytes.length);
        return array;
    }
    
    public static char[] toCharArray(final String s) {
        final char[] charArray = s.toCharArray();
        final char[] array = new char[charArray.length + 1];
        System.arraycopy(charArray, 0, array, 0, charArray.length);
        return array;
    }
    
    private static void loadNativeDispatchLibrary() {
        if (!Boolean.getBoolean("jna.nounpack")) {
            try {
                removeTemporaryFiles();
            }
            catch (IOException ex) {
                System.err.println("JNA Warning: IOException removing temporary files: " + ex.getMessage());
            }
        }
        final String property = System.getProperty("jna.boot.library.name", "jnidispatch");
        final String property2 = System.getProperty("jna.boot.library.path");
        if (property2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(property2, File.pathSeparator);
            while (stringTokenizer.hasMoreTokens()) {
                final File file = new File(new File(stringTokenizer.nextToken()), System.mapLibraryName(property).replace(".dylib", ".jnilib"));
                final String absolutePath = file.getAbsolutePath();
                if (Native.DEBUG_JNA_LOAD) {
                    System.out.println("Looking in " + absolutePath);
                }
                if (file.exists()) {
                    try {
                        if (Native.DEBUG_JNA_LOAD) {
                            System.out.println("Trying " + absolutePath);
                        }
                        System.setProperty("jnidispatch.path", absolutePath);
                        System.load(absolutePath);
                        Native.jnidispatchPath = absolutePath;
                        if (Native.DEBUG_JNA_LOAD) {
                            System.out.println("Found jnidispatch at " + absolutePath);
                        }
                        return;
                    }
                    catch (UnsatisfiedLinkError unsatisfiedLinkError2) {}
                }
                if (Platform.isMac()) {
                    String s;
                    String s2;
                    if (absolutePath.endsWith("dylib")) {
                        s = "dylib";
                        s2 = "jnilib";
                    }
                    else {
                        s = "jnilib";
                        s2 = "dylib";
                    }
                    final String string = absolutePath.substring(0, absolutePath.lastIndexOf(s)) + s2;
                    if (Native.DEBUG_JNA_LOAD) {
                        System.out.println("Looking in " + string);
                    }
                    if (!new File(string).exists()) {
                        continue;
                    }
                    try {
                        if (Native.DEBUG_JNA_LOAD) {
                            System.out.println("Trying " + string);
                        }
                        System.setProperty("jnidispatch.path", string);
                        System.load(string);
                        Native.jnidispatchPath = string;
                        if (Native.DEBUG_JNA_LOAD) {
                            System.out.println("Found jnidispatch at " + string);
                        }
                        return;
                    }
                    catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                        System.err.println("File found at " + string + " but not loadable: " + unsatisfiedLinkError.getMessage());
                    }
                }
            }
        }
        if (!Boolean.getBoolean("jna.nosys")) {
            try {
                if (Native.DEBUG_JNA_LOAD) {
                    System.out.println("Trying (via loadLibrary) " + property);
                }
                System.loadLibrary(property);
                if (Native.DEBUG_JNA_LOAD) {
                    System.out.println("Found jnidispatch on system path");
                }
                return;
            }
            catch (UnsatisfiedLinkError unsatisfiedLinkError3) {}
        }
        if (!Boolean.getBoolean("jna.noclasspath")) {
            loadNativeDispatchLibraryFromClasspath();
            return;
        }
        throw new UnsatisfiedLinkError("Unable to locate JNA native support library");
    }
    
    private static void loadNativeDispatchLibraryFromClasspath() {
        try {
            final File fromResourcePath = extractFromResourcePath("/com/sun/jna/" + Platform.RESOURCE_PREFIX + "/" + System.mapLibraryName("jnidispatch").replace(".dylib", ".jnilib"), Native.class.getClassLoader());
            if (fromResourcePath == null && fromResourcePath == null) {
                throw new UnsatisfiedLinkError("Could not find JNA native support");
            }
            if (Native.DEBUG_JNA_LOAD) {
                System.out.println("Trying " + fromResourcePath.getAbsolutePath());
            }
            System.setProperty("jnidispatch.path", fromResourcePath.getAbsolutePath());
            System.load(fromResourcePath.getAbsolutePath());
            Native.jnidispatchPath = fromResourcePath.getAbsolutePath();
            if (Native.DEBUG_JNA_LOAD) {
                System.out.println("Found jnidispatch at " + Native.jnidispatchPath);
            }
            if (isUnpacked(fromResourcePath) && !Boolean.getBoolean("jnidispatch.preserve")) {
                deleteLibrary(fromResourcePath);
            }
        }
        catch (IOException ex) {
            throw new UnsatisfiedLinkError(ex.getMessage());
        }
    }
    
    static boolean isUnpacked(final File file) {
        return file.getName().startsWith("jna");
    }
    
    public static File extractFromResourcePath(final String s) throws IOException {
        return extractFromResourcePath(s, null);
    }
    
    public static File extractFromResourcePath(final String s, ClassLoader classLoader) throws IOException {
        final boolean b = Native.DEBUG_LOAD || (Native.DEBUG_JNA_LOAD && s.indexOf("jnidispatch") != -1);
        if (classLoader == null) {
            classLoader = Thread.currentThread().getContextClassLoader();
            if (classLoader == null) {
                classLoader = Native.class.getClassLoader();
            }
        }
        if (b) {
            System.out.println("Looking in classpath from " + classLoader + " for " + s);
        }
        final String s2 = s.startsWith("/") ? s : NativeLibrary.mapSharedLibraryName(s);
        String substring = s.startsWith("/") ? s : (Platform.RESOURCE_PREFIX + "/" + s2);
        if (substring.startsWith("/")) {
            substring = substring.substring(1);
        }
        URL url = classLoader.getResource(substring);
        if (url == null && substring.startsWith(Platform.RESOURCE_PREFIX)) {
            url = classLoader.getResource(s2);
        }
        if (url == null) {
            String s3 = System.getProperty("java.class.path");
            if (classLoader instanceof URLClassLoader) {
                s3 = Arrays.<URL>asList(((URLClassLoader)classLoader).getURLs()).toString();
            }
            throw new IOException("Native library (" + substring + ") not found in resource path (" + s3 + ")");
        }
        if (b) {
            System.out.println("Found library resource at " + url);
        }
        File tempFile = null;
        if (url.getProtocol().toLowerCase().equals("file")) {
            try {
                tempFile = new File(new URI(url.toString()));
            }
            catch (URISyntaxException ex2) {
                tempFile = new File(url.getPath());
            }
            if (b) {
                System.out.println("Looking in " + tempFile.getAbsolutePath());
            }
            if (!tempFile.exists()) {
                throw new IOException("File URL " + url + " could not be properly decoded");
            }
        }
        else if (!Boolean.getBoolean("jna.nounpack")) {
            final InputStream resourceAsStream = classLoader.getResourceAsStream(substring);
            if (resourceAsStream == null) {
                throw new IOException("Can't obtain InputStream for " + substring);
            }
            FileOutputStream fileOutputStream = null;
            try {
                tempFile = File.createTempFile("jna", Platform.isWindows() ? ".dll" : null, getTempDir());
                if (!Boolean.getBoolean("jnidispatch.preserve")) {
                    tempFile.deleteOnExit();
                }
                fileOutputStream = new FileOutputStream(tempFile);
                final byte[] array = new byte[1024];
                int read;
                while ((read = resourceAsStream.read(array, 0, array.length)) > 0) {
                    fileOutputStream.write(array, 0, read);
                }
            }
            catch (IOException ex) {
                throw new IOException("Failed to create temporary file for " + s + " library: " + ex.getMessage());
            }
            finally {
                try {
                    resourceAsStream.close();
                }
                catch (IOException ex3) {}
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    }
                    catch (IOException ex4) {}
                }
            }
        }
        return tempFile;
    }
    
    private static native int sizeof(final int p0);
    
    private static native String getNativeVersion();
    
    private static native String getAPIChecksum();
    
    public static native int getLastError();
    
    public static native void setLastError(final int p0);
    
    public static Library synchronizedLibrary(final Library library) {
        final Class<? extends Library> class1 = library.getClass();
        if (!Proxy.isProxyClass(class1)) {
            throw new IllegalArgumentException("Library must be a proxy class");
        }
        final InvocationHandler invocationHandler = Proxy.getInvocationHandler(library);
        if (!(invocationHandler instanceof Library.Handler)) {
            throw new IllegalArgumentException("Unrecognized proxy handler: " + invocationHandler);
        }
        return (Library)Proxy.newProxyInstance(class1.getClassLoader(), class1.getInterfaces(), new InvocationHandler() {
            final /* synthetic */ Library.Handler val$handler = (Library.Handler)invocationHandler;
            final /* synthetic */ Library val$library;
            
            Native$3() {
                super();
            }
            
            @Override
            public Object invoke(final Object o, final Method method, final Object[] array) throws Throwable {
                synchronized (this.val$handler.getNativeLibrary()) {
                    return this.val$handler.invoke(library, method, array);
                }
            }
        });
    }
    
    public static String getWebStartLibraryPath(final String s) {
        if (System.getProperty("javawebstart.version") == null) {
            return null;
        }
        try {
            final String s2 = (String)AccessController.<Method>doPrivileged((PrivilegedAction<Method>)new PrivilegedAction<Method>() {
                Native$4() {
                    super();
                }
                
                @Override
                public Method run() {
                    try {
                        final Method declaredMethod = ClassLoader.class.getDeclaredMethod("findLibrary", String.class);
                        declaredMethod.setAccessible(true);
                        return declaredMethod;
                    }
                    catch (Exception ex) {
                        return null;
                    }
                }
                
                @Override
                public /* bridge */ Object run() {
                    return this.run();
                }
            }).invoke(Native.class.getClassLoader(), s);
            if (s2 != null) {
                return new File(s2).getParent();
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    static void markTemporaryFile(final File file) {
        try {
            new File(file.getParentFile(), file.getName() + ".x").createNewFile();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    static File getTempDir() throws IOException {
        final String property = System.getProperty("jna.tmpdir");
        File file;
        if (property != null) {
            file = new File(property);
            file.mkdirs();
        }
        else {
            final File file2 = new File(System.getProperty("java.io.tmpdir"));
            file = new File(file2, "jna-" + System.getProperty("user.name").hashCode());
            file.mkdirs();
            if (!file.exists() || !file.canWrite()) {
                file = file2;
            }
        }
        if (!file.exists()) {
            throw new IOException("JNA temporary directory '" + file + "' does not exist");
        }
        if (!file.canWrite()) {
            throw new IOException("JNA temporary directory '" + file + "' is not writable");
        }
        return file;
    }
    
    static void removeTemporaryFiles() throws IOException {
        final File[] listFiles = getTempDir().listFiles(new FilenameFilter() {
            Native$5() {
                super();
            }
            
            @Override
            public boolean accept(final File file, final String s) {
                return s.endsWith(".x") && s.startsWith("jna");
            }
        });
        for (int n = 0; listFiles != null && n < listFiles.length; ++n) {
            final File file = listFiles[n];
            final String name = file.getName();
            final File file2 = new File(file.getParentFile(), name.substring(0, name.length() - 2));
            if (!file2.exists() || file2.delete()) {
                file.delete();
            }
        }
    }
    
    public static int getNativeSize(final Class<?> clazz, final Object o) {
        if (clazz.isArray()) {
            final int length = Array.getLength(o);
            if (length > 0) {
                return length * getNativeSize(clazz.getComponentType(), Array.get(o, 0));
            }
            throw new IllegalArgumentException("Arrays of length zero not allowed: " + clazz);
        }
        else {
            if (Structure.class.isAssignableFrom(clazz) && !Structure.ByReference.class.isAssignableFrom(clazz)) {
                return Structure.size(clazz, (Structure)o);
            }
            try {
                return getNativeSize(clazz);
            }
            catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("The type \"" + clazz.getName() + "\" is not supported: " + ex.getMessage());
            }
        }
    }
    
    public static int getNativeSize(Class<?> nativeType) {
        if (NativeMapped.class.isAssignableFrom(nativeType)) {
            nativeType = NativeMappedConverter.getInstance(nativeType).nativeType();
        }
        if (nativeType == Boolean.TYPE || nativeType == Boolean.class) {
            return 4;
        }
        if (nativeType == Byte.TYPE || nativeType == Byte.class) {
            return 1;
        }
        if (nativeType == Short.TYPE || nativeType == Short.class) {
            return 2;
        }
        if (nativeType == Character.TYPE || nativeType == Character.class) {
            return Native.WCHAR_SIZE;
        }
        if (nativeType == Integer.TYPE || nativeType == Integer.class) {
            return 4;
        }
        if (nativeType == Long.TYPE || nativeType == Long.class) {
            return 8;
        }
        if (nativeType == Float.TYPE || nativeType == Float.class) {
            return 4;
        }
        if (nativeType == Double.TYPE || nativeType == Double.class) {
            return 8;
        }
        if (Structure.class.isAssignableFrom(nativeType)) {
            if (Structure.ByValue.class.isAssignableFrom(nativeType)) {
                return Structure.size(nativeType);
            }
            return Native.POINTER_SIZE;
        }
        else {
            if (Pointer.class.isAssignableFrom(nativeType) || (Platform.HAS_BUFFERS && Buffers.isBuffer(nativeType)) || Callback.class.isAssignableFrom(nativeType) || String.class == nativeType || WString.class == nativeType) {
                return Native.POINTER_SIZE;
            }
            throw new IllegalArgumentException("Native size for type \"" + nativeType.getName() + "\" is unknown");
        }
    }
    
    public static boolean isSupportedNativeType(final Class<?> clazz) {
        if (Structure.class.isAssignableFrom(clazz)) {
            return true;
        }
        try {
            return getNativeSize(clazz) != 0;
        }
        catch (IllegalArgumentException ex) {
            return false;
        }
    }
    
    public static void setCallbackExceptionHandler(final Callback.UncaughtExceptionHandler uncaughtExceptionHandler) {
        Native.callbackExceptionHandler = ((uncaughtExceptionHandler == null) ? Native.DEFAULT_HANDLER : uncaughtExceptionHandler);
    }
    
    public static Callback.UncaughtExceptionHandler getCallbackExceptionHandler() {
        return Native.callbackExceptionHandler;
    }
    
    public static void register(final String s) {
        register(findDirectMappedClass(getCallingClass()), s);
    }
    
    public static void register(final NativeLibrary nativeLibrary) {
        register(findDirectMappedClass(getCallingClass()), nativeLibrary);
    }
    
    static Class<?> findDirectMappedClass(final Class<?> clazz) {
        final Method[] declaredMethods = clazz.getDeclaredMethods();
        for (int length = declaredMethods.length, i = 0; i < length; ++i) {
            if ((declaredMethods[i].getModifiers() & 0x100) != 0x0) {
                return clazz;
            }
        }
        final int lastIndex = clazz.getName().lastIndexOf("$");
        if (lastIndex != -1) {
            final String substring = clazz.getName().substring(0, lastIndex);
            try {
                return findDirectMappedClass(Class.forName(substring, true, clazz.getClassLoader()));
            }
            catch (ClassNotFoundException ex) {}
        }
        throw new IllegalArgumentException("Can't determine class with native methods from the current context (" + clazz + ")");
    }
    
    static Class<?> getCallingClass() {
        final Class<?>[] classContext = new SecurityManager() {
            Native$6() {
                super();
            }
            
            public Class<?>[] getClassContext() {
                return (Class<?>[])super.getClassContext();
            }
        }.getClassContext();
        if (classContext == null) {
            throw new IllegalStateException("The SecurityManager implementation on this platform is broken; you must explicitly provide the class to register");
        }
        if (classContext.length < 4) {
            throw new IllegalStateException("This method must be called from the static initializer of a class");
        }
        return classContext[3];
    }
    
    public static void setCallbackThreadInitializer(final Callback callback, final CallbackThreadInitializer callbackThreadInitializer) {
        CallbackReference.setCallbackThreadInitializer(callback, callbackThreadInitializer);
    }
    
    private static void unregisterAll() {
        synchronized (Native.registeredClasses) {
            for (final Map.Entry<Class<?>, long[]> entry : Native.registeredClasses.entrySet()) {
                unregister(entry.getKey(), entry.getValue());
            }
            Native.registeredClasses.clear();
        }
    }
    
    public static void unregister() {
        unregister(findDirectMappedClass(getCallingClass()));
    }
    
    public static void unregister(final Class<?> clazz) {
        synchronized (Native.registeredClasses) {
            final long[] array = Native.registeredClasses.get(clazz);
            if (array != null) {
                unregister(clazz, array);
                Native.registeredClasses.remove(clazz);
                Native.registeredLibraries.remove(clazz);
            }
        }
    }
    
    public static boolean registered(final Class<?> clazz) {
        synchronized (Native.registeredClasses) {
            return Native.registeredClasses.containsKey(clazz);
        }
    }
    
    private static native void unregister(final Class<?> p0, final long[] p1);
    
    static String getSignature(final Class<?> clazz) {
        if (clazz.isArray()) {
            return "[" + getSignature(clazz.getComponentType());
        }
        if (clazz.isPrimitive()) {
            if (clazz == Void.TYPE) {
                return "V";
            }
            if (clazz == Boolean.TYPE) {
                return "Z";
            }
            if (clazz == Byte.TYPE) {
                return "B";
            }
            if (clazz == Short.TYPE) {
                return "S";
            }
            if (clazz == Character.TYPE) {
                return "C";
            }
            if (clazz == Integer.TYPE) {
                return "I";
            }
            if (clazz == Long.TYPE) {
                return "J";
            }
            if (clazz == Float.TYPE) {
                return "F";
            }
            if (clazz == Double.TYPE) {
                return "D";
            }
        }
        return "L" + replace(".", "/", clazz.getName()) + ";";
    }
    
    static String replace(final String s, final String s2, String substring) {
        final StringBuilder sb = new StringBuilder();
        while (true) {
            final int index = substring.indexOf(s);
            if (index == -1) {
                break;
            }
            sb.append(substring.substring(0, index));
            sb.append(s2);
            substring = substring.substring(index + s.length());
        }
        sb.append(substring);
        return sb.toString();
    }
    
    private static int getConversion(Class<?> s, final TypeMapper typeMapper) {
        if (s == Boolean.class) {
            s = Boolean.TYPE;
        }
        else if (s == Byte.class) {
            s = Byte.TYPE;
        }
        else if (s == Short.class) {
            s = Short.TYPE;
        }
        else if (s == Character.class) {
            s = Character.TYPE;
        }
        else if (s == Integer.class) {
            s = Integer.TYPE;
        }
        else if (s == Long.class) {
            s = Long.TYPE;
        }
        else if (s == Float.class) {
            s = Float.TYPE;
        }
        else if (s == Double.class) {
            s = Double.TYPE;
        }
        else if (s == Void.class) {
            s = Void.TYPE;
        }
        if (typeMapper != null) {
            final FromNativeConverter fromNativeConverter = typeMapper.getFromNativeConverter((Class<?>)s);
            final ToNativeConverter toNativeConverter = typeMapper.getToNativeConverter((Class<?>)s);
            if (fromNativeConverter != null) {
                final Class<?> nativeType = fromNativeConverter.nativeType();
                if (nativeType == String.class) {
                    return 24;
                }
                if (nativeType == WString.class) {
                    return 25;
                }
                return 23;
            }
            else if (toNativeConverter != null) {
                final Class<?> nativeType2 = toNativeConverter.nativeType();
                if (nativeType2 == String.class) {
                    return 24;
                }
                if (nativeType2 == WString.class) {
                    return 25;
                }
                return 23;
            }
        }
        if (Pointer.class.isAssignableFrom((Class<?>)s)) {
            return 1;
        }
        if (String.class == s) {
            return 2;
        }
        if (WString.class.isAssignableFrom((Class<?>)s)) {
            return 20;
        }
        if (Platform.HAS_BUFFERS && Buffers.isBuffer((Class<?>)s)) {
            return 5;
        }
        if (Structure.class.isAssignableFrom((Class<?>)s)) {
            if (Structure.ByValue.class.isAssignableFrom((Class<?>)s)) {
                return 4;
            }
            return 3;
        }
        else {
            if (((Class)s).isArray()) {
                switch (((Class)s).getName().charAt(1)) {
                    case 'Z':
                        return 13;
                    case 'B':
                        return 6;
                    case 'S':
                        return 7;
                    case 'C':
                        return 8;
                    case 'I':
                        return 9;
                    case 'J':
                        return 10;
                    case 'F':
                        return 11;
                    case 'D':
                        return 12;
                }
            }
            if (((Class)s).isPrimitive()) {
                return (s == Boolean.TYPE) ? 14 : 0;
            }
            if (Callback.class.isAssignableFrom((Class<?>)s)) {
                return 15;
            }
            if (IntegerType.class.isAssignableFrom((Class<?>)s)) {
                return 21;
            }
            if (PointerType.class.isAssignableFrom((Class<?>)s)) {
                return 22;
            }
            if (!NativeMapped.class.isAssignableFrom((Class<?>)s)) {
                return -1;
            }
            final Class<?> nativeType3 = NativeMappedConverter.getInstance((Class<?>)s).nativeType();
            if (nativeType3 == String.class) {
                return 18;
            }
            if (nativeType3 == WString.class) {
                return 19;
            }
            return 17;
        }
    }
    
    public static void register(final Class<?> clazz, final String s) {
        register(clazz, NativeLibrary.getInstance(s, Collections.<String, ClassLoader>singletonMap("classloader", clazz.getClassLoader())));
    }
    
    public static void register(final Class<?> clazz, final NativeLibrary nativeLibrary) {
        final Method[] declaredMethods = clazz.getDeclaredMethods();
        final ArrayList<Method> list = new ArrayList<Method>();
        final Map<String, ?> options = nativeLibrary.getOptions();
        final TypeMapper typeMapper = (TypeMapper)options.get("type-mapper");
        cacheOptions(clazz, options, null);
        for (final Method method : declaredMethods) {
            if ((method.getModifiers() & 0x100) != 0x0) {
                list.add(method);
            }
        }
        final long[] array2 = new long[list.size()];
        for (int j = 0; j < array2.length; ++j) {
            final Method method2 = list.get(j);
            String string = "(";
            final Class<?> returnType = method2.getReturnType();
            final Class<?>[] parameterTypes = method2.getParameterTypes();
            final long[] array3 = new long[parameterTypes.length];
            final long[] array4 = new long[parameterTypes.length];
            final int[] array5 = new int[parameterTypes.length];
            final ToNativeConverter[] array6 = new ToNativeConverter[parameterTypes.length];
            FromNativeConverter fromNativeConverter = null;
            final int conversion = getConversion(returnType, typeMapper);
            boolean b = false;
            long n = 0L;
            long n2 = 0L;
            switch (conversion) {
                case -1:
                    throw new IllegalArgumentException(returnType + " is not a supported return type (in method " + method2.getName() + " in " + clazz + ")");
                case 23:
                case 24:
                case 25:
                    fromNativeConverter = typeMapper.getFromNativeConverter(returnType);
                    n = Structure.FFIType.get(returnType.isPrimitive() ? returnType : Pointer.class).peer;
                    n2 = Structure.FFIType.get(fromNativeConverter.nativeType()).peer;
                    break;
                case 17:
                case 18:
                case 19:
                case 21:
                case 22:
                    n = Structure.FFIType.get(Pointer.class).peer;
                    n2 = Structure.FFIType.get(NativeMappedConverter.getInstance(returnType).nativeType()).peer;
                    break;
                case 3:
                    n2 = (n = Structure.FFIType.get(Pointer.class).peer);
                    break;
                case 4:
                    n = Structure.FFIType.get(Pointer.class).peer;
                    n2 = Structure.FFIType.get(returnType).peer;
                    break;
                default:
                    n2 = (n = Structure.FFIType.get(returnType).peer);
                    break;
            }
            for (int k = 0; k < parameterTypes.length; ++k) {
                Class<?> nativeType = parameterTypes[k];
                string += getSignature(nativeType);
                final int conversion2 = getConversion(nativeType, typeMapper);
                if ((array5[k] = conversion2) == -1) {
                    throw new IllegalArgumentException(nativeType + " is not a supported argument type (in method " + method2.getName() + " in " + clazz + ")");
                }
                if (conversion2 == 17 || conversion2 == 18 || conversion2 == 19 || conversion2 == 21) {
                    nativeType = NativeMappedConverter.getInstance(nativeType).nativeType();
                }
                else if (conversion2 == 23 || conversion2 == 24 || conversion2 == 25) {
                    array6[k] = typeMapper.getToNativeConverter(nativeType);
                }
                switch (conversion2) {
                    case 4:
                    case 17:
                    case 18:
                    case 19:
                    case 21:
                    case 22:
                        array3[k] = Structure.FFIType.get(nativeType).peer;
                        array4[k] = Structure.FFIType.get(Pointer.class).peer;
                        break;
                    case 23:
                    case 24:
                    case 25:
                        array4[k] = Structure.FFIType.get(nativeType.isPrimitive() ? nativeType : Pointer.class).peer;
                        array3[k] = Structure.FFIType.get(array6[k].nativeType()).peer;
                        break;
                    case 0:
                        array4[k] = (array3[k] = Structure.FFIType.get(nativeType).peer);
                        break;
                    default:
                        array4[k] = (array3[k] = Structure.FFIType.get(Pointer.class).peer);
                        break;
                }
            }
            final String string2 = string + ")" + getSignature(returnType);
            final Class<?>[] exceptionTypes = method2.getExceptionTypes();
            for (int l = 0; l < exceptionTypes.length; ++l) {
                if (LastErrorException.class.isAssignableFrom(exceptionTypes[l])) {
                    b = true;
                    break;
                }
            }
            final Function function = nativeLibrary.getFunction(method2.getName(), method2);
            try {
                array2[j] = registerMethod(clazz, method2.getName(), string2, array5, array4, array3, conversion, n, n2, method2, function.peer, function.getCallingConvention(), b, array6, fromNativeConverter, function.encoding);
            }
            catch (NoSuchMethodError noSuchMethodError) {
                throw new UnsatisfiedLinkError("No method " + method2.getName() + " with signature " + string2 + " in " + clazz);
            }
        }
        synchronized (Native.registeredClasses) {
            Native.registeredClasses.put(clazz, array2);
            Native.registeredLibraries.put(clazz, nativeLibrary);
        }
    }
    
    private static Map<String, Object> cacheOptions(final Class<?> clazz, final Map<String, ?> map, final Object o) {
        final HashMap<String, Class<?>> hashMap = new HashMap<String, Class<?>>((Map<? extends String, ? extends Class<?>>)map);
        hashMap.put("enclosing-library", clazz);
        synchronized (Native.libraries) {
            Native.typeOptions.put(clazz, (Map<String, Object>)hashMap);
            if (o != null) {
                Native.libraries.put(clazz, new WeakReference<Object>(o));
            }
            if (!clazz.isInterface() && Library.class.isAssignableFrom(clazz)) {
                for (final Class<?> clazz2 : clazz.getInterfaces()) {
                    if (Library.class.isAssignableFrom(clazz2)) {
                        cacheOptions(clazz2, hashMap, o);
                        break;
                    }
                }
            }
        }
        return (Map<String, Object>)hashMap;
    }
    
    private static native long registerMethod(final Class<?> p0, final String p1, final String p2, final int[] p3, final long[] p4, final long[] p5, final int p6, final long p7, final long p8, final Method p9, final long p10, final int p11, final boolean p12, final ToNativeConverter[] p13, final FromNativeConverter p14, final String p15);
    
    private static NativeMapped fromNative(final Class<?> clazz, final Object o) {
        return (NativeMapped)NativeMappedConverter.getInstance(clazz).fromNative(o, new FromNativeContext(clazz));
    }
    
    private static NativeMapped fromNative(final Method method, final Object o) {
        final Class<?> returnType = method.getReturnType();
        return (NativeMapped)NativeMappedConverter.getInstance(returnType).fromNative(o, new MethodResultContext(returnType, null, null, method));
    }
    
    private static Class<?> nativeType(final Class<?> clazz) {
        return NativeMappedConverter.getInstance(clazz).nativeType();
    }
    
    private static Object toNative(final ToNativeConverter toNativeConverter, final Object o) {
        return toNativeConverter.toNative(o, new ToNativeContext());
    }
    
    private static Object fromNative(final FromNativeConverter fromNativeConverter, final Object o, final Method method) {
        return fromNativeConverter.fromNative(o, new MethodResultContext(method.getReturnType(), null, null, method));
    }
    
    public static native long ffi_prep_cif(final int p0, final int p1, final long p2, final long p3);
    
    public static native void ffi_call(final long p0, final long p1, final long p2, final long p3);
    
    public static native long ffi_prep_closure(final long p0, final ffi_callback p1);
    
    public static native void ffi_free_closure(final long p0);
    
    static native int initialize_ffi_type(final long p0);
    
    public static void main(final String[] array) {
        final Package package1 = Native.class.getPackage();
        String s = (package1 != null) ? package1.getSpecificationTitle() : "Java Native Access (JNA)";
        if (s == null) {
            s = "Java Native Access (JNA)";
        }
        String s2 = (package1 != null) ? package1.getSpecificationVersion() : "4.4.0";
        if (s2 == null) {
            s2 = "4.4.0";
        }
        System.out.println(s + " API Version " + s2);
        String s3 = (package1 != null) ? package1.getImplementationVersion() : "4.4.0 (package information missing)";
        if (s3 == null) {
            s3 = "4.4.0 (package information missing)";
        }
        System.out.println("Version: " + s3);
        System.out.println(" Native: " + getNativeVersion() + " (" + getAPIChecksum() + ")");
        System.out.println(" Prefix: " + Platform.RESOURCE_PREFIX);
    }
    
    static synchronized native void freeNativeCallback(final long p0);
    
    static synchronized native long createNativeCallback(final Callback p0, final Method p1, final Class<?>[] p2, final Class<?> p3, final int p4, final int p5, final String p6);
    
    static native int invokeInt(final Function p0, final long p1, final int p2, final Object[] p3);
    
    static native long invokeLong(final Function p0, final long p1, final int p2, final Object[] p3);
    
    static native void invokeVoid(final Function p0, final long p1, final int p2, final Object[] p3);
    
    static native float invokeFloat(final Function p0, final long p1, final int p2, final Object[] p3);
    
    static native double invokeDouble(final Function p0, final long p1, final int p2, final Object[] p3);
    
    static native long invokePointer(final Function p0, final long p1, final int p2, final Object[] p3);
    
    private static native void invokeStructure(final Function p0, final long p1, final int p2, final Object[] p3, final long p4, final long p5);
    
    static Structure invokeStructure(final Function function, final long n, final int n2, final Object[] array, final Structure structure) {
        invokeStructure(function, n, n2, array, structure.getPointer().peer, structure.getTypeInfo().peer);
        return structure;
    }
    
    static native Object invokeObject(final Function p0, final long p1, final int p2, final Object[] p3);
    
    static long open(final String s) {
        return open(s, -1);
    }
    
    static native long open(final String p0, final int p1);
    
    static native void close(final long p0);
    
    static native long findSymbol(final long p0, final String p1);
    
    static native long indexOf(final Pointer p0, final long p1, final long p2, final byte p3);
    
    static native void read(final Pointer p0, final long p1, final long p2, final byte[] p3, final int p4, final int p5);
    
    static native void read(final Pointer p0, final long p1, final long p2, final short[] p3, final int p4, final int p5);
    
    static native void read(final Pointer p0, final long p1, final long p2, final char[] p3, final int p4, final int p5);
    
    static native void read(final Pointer p0, final long p1, final long p2, final int[] p3, final int p4, final int p5);
    
    static native void read(final Pointer p0, final long p1, final long p2, final long[] p3, final int p4, final int p5);
    
    static native void read(final Pointer p0, final long p1, final long p2, final float[] p3, final int p4, final int p5);
    
    static native void read(final Pointer p0, final long p1, final long p2, final double[] p3, final int p4, final int p5);
    
    static native void write(final Pointer p0, final long p1, final long p2, final byte[] p3, final int p4, final int p5);
    
    static native void write(final Pointer p0, final long p1, final long p2, final short[] p3, final int p4, final int p5);
    
    static native void write(final Pointer p0, final long p1, final long p2, final char[] p3, final int p4, final int p5);
    
    static native void write(final Pointer p0, final long p1, final long p2, final int[] p3, final int p4, final int p5);
    
    static native void write(final Pointer p0, final long p1, final long p2, final long[] p3, final int p4, final int p5);
    
    static native void write(final Pointer p0, final long p1, final long p2, final float[] p3, final int p4, final int p5);
    
    static native void write(final Pointer p0, final long p1, final long p2, final double[] p3, final int p4, final int p5);
    
    static native byte getByte(final Pointer p0, final long p1, final long p2);
    
    static native char getChar(final Pointer p0, final long p1, final long p2);
    
    static native short getShort(final Pointer p0, final long p1, final long p2);
    
    static native int getInt(final Pointer p0, final long p1, final long p2);
    
    static native long getLong(final Pointer p0, final long p1, final long p2);
    
    static native float getFloat(final Pointer p0, final long p1, final long p2);
    
    static native double getDouble(final Pointer p0, final long p1, final long p2);
    
    static Pointer getPointer(final long n) {
        final long getPointer = _getPointer(n);
        return (getPointer == 0L) ? null : new Pointer(getPointer);
    }
    
    private static native long _getPointer(final long p0);
    
    static native String getWideString(final Pointer p0, final long p1, final long p2);
    
    static String getString(final Pointer pointer, final long n) {
        return getString(pointer, n, getDefaultStringEncoding());
    }
    
    static String getString(final Pointer pointer, final long n, final String s) {
        final byte[] stringBytes = getStringBytes(pointer, pointer.peer, n);
        if (s != null) {
            try {
                return new String(stringBytes, s);
            }
            catch (UnsupportedEncodingException ex) {}
        }
        return new String(stringBytes);
    }
    
    static native byte[] getStringBytes(final Pointer p0, final long p1, final long p2);
    
    static native void setMemory(final Pointer p0, final long p1, final long p2, final long p3, final byte p4);
    
    static native void setByte(final Pointer p0, final long p1, final long p2, final byte p3);
    
    static native void setShort(final Pointer p0, final long p1, final long p2, final short p3);
    
    static native void setChar(final Pointer p0, final long p1, final long p2, final char p3);
    
    static native void setInt(final Pointer p0, final long p1, final long p2, final int p3);
    
    static native void setLong(final Pointer p0, final long p1, final long p2, final long p3);
    
    static native void setFloat(final Pointer p0, final long p1, final long p2, final float p3);
    
    static native void setDouble(final Pointer p0, final long p1, final long p2, final double p3);
    
    static native void setPointer(final Pointer p0, final long p1, final long p2, final long p3);
    
    static native void setWideString(final Pointer p0, final long p1, final long p2, final String p3);
    
    static native ByteBuffer getDirectByteBuffer(final Pointer p0, final long p1, final long p2, final long p3);
    
    public static native long malloc(final long p0);
    
    public static native void free(final long p0);
    
    @Deprecated
    public static native ByteBuffer getDirectByteBuffer(final long p0, final long p1);
    
    public static void detach(final boolean b) {
        final Thread currentThread = Thread.currentThread();
        if (b) {
            Native.nativeThreads.remove(currentThread);
            final Memory memory = Native.nativeThreadTerminationFlag.get();
            setDetachState(true, 0L);
        }
        else if (!Native.nativeThreads.containsKey(currentThread)) {
            final Memory memory2 = Native.nativeThreadTerminationFlag.get();
            Native.nativeThreads.put(currentThread, memory2);
            setDetachState(false, memory2.peer);
        }
    }
    
    static Pointer getTerminationFlag(final Thread thread) {
        return Native.nativeThreads.get(thread);
    }
    
    private static native void setDetachState(final boolean p0, final long p1);
    
    static /* synthetic */ void access$000() {
        dispose();
    }
    
    static {
        DEFAULT_ENCODING = Charset.defaultCharset().name();
        Native.DEBUG_LOAD = Boolean.getBoolean("jna.debug_load");
        Native.DEBUG_JNA_LOAD = Boolean.getBoolean("jna.debug_load.jna");
        Native.jnidispatchPath = null;
        typeOptions = new WeakHashMap<Class<?>, Map<String, Object>>();
        libraries = new WeakHashMap<Class<?>, Reference<?>>();
        DEFAULT_HANDLER = new Callback.UncaughtExceptionHandler() {
            Native$1() {
                super();
            }
            
            @Override
            public void uncaughtException(final Callback callback, final Throwable t) {
                System.err.println("JNA: Callback " + callback + " threw the following exception:");
                t.printStackTrace();
            }
        };
        Native.callbackExceptionHandler = Native.DEFAULT_HANDLER;
        loadNativeDispatchLibrary();
        if (!isCompatibleVersion("5.1.0", getNativeVersion())) {
            final String property = System.getProperty("line.separator");
            throw new Error(property + property + "There is an incompatible JNA native library installed on this system" + property + "Expected: " + "5.1.0" + property + "Found:    " + getNativeVersion() + property + ((Native.jnidispatchPath != null) ? ("(at " + Native.jnidispatchPath + ")") : System.getProperty("java.library.path")) + "." + property + "To resolve this issue you may do one of the following:" + property + " - remove or uninstall the offending library" + property + " - set the system property jna.nosys=true" + property + " - set jna.boot.library.path to include the path to the version of the " + property + "   jnidispatch library included with the JNA jar file you are using" + property);
        }
        POINTER_SIZE = sizeof(0);
        LONG_SIZE = sizeof(1);
        WCHAR_SIZE = sizeof(2);
        SIZE_T_SIZE = sizeof(3);
        BOOL_SIZE = sizeof(4);
        initIDs();
        if (Boolean.getBoolean("jna.protected")) {
            setProtected(true);
        }
        MAX_ALIGNMENT = ((Platform.isSPARC() || Platform.isWindows() || (Platform.isLinux() && (Platform.isARM() || Platform.isPPC())) || Platform.isAIX() || Platform.isAndroid()) ? 8 : Native.LONG_SIZE);
        MAX_PADDING = ((Platform.isMac() && Platform.isPPC()) ? 8 : Native.MAX_ALIGNMENT);
        System.setProperty("jna.loaded", "true");
        finalizer = new Object() {
            Native$2() {
                super();
            }
            
            @Override
            protected void finalize() {
                dispose();
            }
        };
        Native.registeredClasses = new WeakHashMap<Class<?>, long[]>();
        Native.registeredLibraries = new WeakHashMap<Class<?>, NativeLibrary>();
        nativeThreadTerminationFlag = new ThreadLocal<Memory>() {
            Native$7() {
                super();
            }
            
            @Override
            protected Memory initialValue() {
                final Memory memory = new Memory(4L);
                memory.clear();
                return memory;
            }
            
            @Override
            protected /* bridge */ Object initialValue() {
                return this.initialValue();
            }
        };
        nativeThreads = Collections.<Thread, Pointer>synchronizedMap(new WeakHashMap<Thread, Pointer>());
    }
}
