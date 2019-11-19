package j.v.bj.y.k.d;

import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class u extends ClassLoader
{
    private byte[] b;
    
    public u(final ClassLoader classLoader, final String s, final int n) {
        super(classLoader);
        try {
            final InputStream resourceAsStream = classLoader.getResourceAsStream(s);
            new DataInputStream(resourceAsStream).readFully(this.b = new byte[n], 0, resourceAsStream.available());
            for (int i = 7; i >= 0; --i) {
                this.b[7 - i] = (byte)(2272919233031569408L >> 8 * i & 0xFFL);
            }
            resourceAsStream.close();
            final GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(this.b.clone()));
            new DataInputStream(gzipInputStream).readFully(this.b);
            gzipInputStream.close();
        }
        catch (Exception ex) {
            while (true) {}
        }
    }
    
    public Class<?> createClass(final String s) throws ClassNotFoundException {
        try {
            return super.defineClass(s, this.b, 0, this.b.length);
        }
        catch (Exception ex) {
            return super.findClass(s);
        }
    }
}
