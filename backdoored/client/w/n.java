package r.k.w;

import java.io.File;

public class n
{
    static final File stv;
    public static final boolean stj;
    public static final int ste;
    public static final boolean sto;
    
    public n() {
        super();
    }
    
    public static void sc() {
        try {
            m.d(n.stv);
        }
        catch (Exception ex) {
            System.out.println("Error saving config");
            ex.printStackTrace();
        }
    }
    
    public static void sz() {
        try {
            c.s(n.stv);
        }
        catch (Exception ex) {
            System.out.println("Error reading config");
            ex.printStackTrace();
        }
    }
    
    static {
        stv = new File("Backdoored/config.json");
        try {
            if (!n.stv.exists()) {
                n.stv.createNewFile();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
