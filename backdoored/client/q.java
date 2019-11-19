package r.k;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.Objects;
import java.net.URL;
import java.util.Vector;
import java.lang.reflect.Field;

public class q
{
    private static Field mi;
    public static final boolean mp;
    public static final int mr;
    public static final boolean mf;
    
    public q() {
        super();
    }
    
    public static String[] d(final ClassLoader classLoader) {
        try {
            return ((Vector)q.mi.get(classLoader)).<String>toArray(new String[0]);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return new String[0];
        }
    }
    
    public static void u(final String s) throws IOException {
        final File file = new File(Objects.<URL>requireNonNull(ClassLoader.getSystemClassLoader().getResource(s)).getFile());
        System.out.println(file.getAbsolutePath());
        final FileInputStream fileInputStream = new FileInputStream(file);
        final byte[] array = new byte[1024];
        final File tempFile = File.createTempFile(s, "");
        final FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
        final int read;
        if ((read = fileInputStream.read(array)) != -1) {
            fileOutputStream.write(array, 0, read);
        }
        fileOutputStream.close();
        fileInputStream.close();
        System.out.println(tempFile.getAbsolutePath());
        System.load(tempFile.getAbsolutePath());
    }
    
    public static void lp() {
        try {
            u("com_backdoored_DllManager.dll");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static String z(final String s) {
        return q(s, u.lsn);
    }
    
    public static native String c(final String p0);
    
    public static native String q(final String p0, final String p1);
    
    public static native String lr();
    
    static {
        try {
            (q.mi = ClassLoader.class.getDeclaredField("loadedLibraryNames")).setAccessible(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            q.mi = null;
        }
    }
}
