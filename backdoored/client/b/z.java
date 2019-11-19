package r.k.b;

import net.minecraft.util.Session;

public class z
{
    public q hx;
    public String hk;
    public String hq;
    private Session session;
    public static final boolean hj;
    public static final int he;
    public static final boolean ho;
    
    public z(final String hk, final String s) {
        super();
        this.hx = new q();
        this.hk = hk;
    }
    
    public boolean lf() {
        if (this.hq != null && !this.hq.equals("")) {
            final q hx = this.hx;
            final Session j = q.j(this.hk, this.hq);
            if (j != null) {
                this.session = j;
                return true;
            }
        }
        final q hx2 = this.hx;
        this.session = q.sl(this.hk);
        return true;
    }
    
    public Session lb() {
        return this.session;
    }
}
