package b.k;

import java.security.MessageDigest;
import java.nio.charset.Charset;
import java.util.Random;

public class c
{
    public final String svb;
    private final long svw;
    private Random svg;
    private static final String[] svu;
    
    public c(final long svw) {
        super();
        this.svw = svw;
        this.svg = new Random(this.svw);
        this.svb = this.lr();
    }
    
    public c(final long svw, final String svb) {
        super();
        this.svw = svw;
        this.svg = new Random(this.svw);
        this.svb = svb;
    }
    
    private String lr() {
        return e.d(e.xk());
    }
    
    public String xd() {
        try {
            String s = Long.toString(this.svw) + this.svb;
            for (int nextInt = this.svg.nextInt(20), i = 1; i <= nextInt; ++i) {
                s = this.d(s.getBytes(), this.xs());
                if (this.svg.nextBoolean()) {
                    final byte[] array = new byte[7];
                    this.svg.nextBytes(array);
                    s += new String(array, Charset.forName("UTF-8"));
                }
            }
            for (int nextInt2 = this.svg.nextInt(3), j = 1; j <= nextInt2; ++j) {
                if (this.svg.nextBoolean()) {
                    s += this.d(s.getBytes(), this.xs());
                }
                else {
                    s = this.d(s.getBytes(), this.xs()) + s;
                }
            }
            return this.d((s + s + s).getBytes(), this.xs());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private String d(final byte[] array, final String s) {
        try {
            final MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(array);
            return e.d(instance.digest());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    private String xs() {
        return c.svu[this.svg.nextInt(c.svu.length)];
    }
    
    static {
        svu = new String[] { "MD2", "MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512" };
    }
}
