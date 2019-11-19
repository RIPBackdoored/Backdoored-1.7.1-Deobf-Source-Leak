package b.k;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public class e
{
    private static final char[] sis;
    
    public e() {
        super();
    }
    
    public static byte[] xk() {
        try {
            return MessageDigest.getInstance("MD5").digest((System.getProperty("os.name") + System.getProperty("os.arch") + System.getProperty("os.version") + Runtime.getRuntime().availableProcessors() + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("PROCESSOR_ARCHITEW6432") + System.getenv("NUMBER_OF_PROCESSORS")).getBytes());
        }
        catch (NoSuchAlgorithmException ex) {
            throw new Error("Algorithm wasn't found.", ex);
        }
    }
    
    public static byte[] sx(final String s) {
        final int length = s.length();
        final byte[] array = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            array[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return array;
    }
    
    public static String d(final byte[] array) {
        final char[] array2 = new char[array.length * 2];
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i] & 0xFF;
            array2[i * 2] = e.sis[n >>> 4];
            array2[i * 2 + 1] = e.sis[n & 0xF];
        }
        return new String(array2);
    }
    
    static {
        sis = "0123456789ABCDEF".toCharArray();
    }
}
