package r.k.s.m;

import net.minecraftforge.fml.common.FMLLog;
import java.util.HashSet;
import java.util.Set;

public class h
{
    private Class[] slm;
    private Set<Class> slh;
    public static final boolean syd;
    public static final int sys;
    public static final boolean syl;
    
    public h() {
    }
    
    public h d(final Class[] slm) {
        this.slm = slm;
        this.slh = new HashSet<Class>(slm.length);
        return this;
    }
    
    public h yi() {
        final Class[] slm = this.slm;
        final int length = slm.length;
        int n = 0;
        if (n < length) {
            final Class clazz = slm[n];
            try {
                clazz.newInstance();
            }
            catch (Exception ex) {
                this.slh.add(clazz);
                FMLLog.log.info("Error initialising class " + clazz.getName());
                ex.printStackTrace();
                ++n;
            }
        }
        return this;
    }
    
    public Set<Class> yp() {
        return this.slh;
    }
}
