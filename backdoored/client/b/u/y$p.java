package r.k.b.u;

public class y$p
{
    public y$u sqi;
    public y$u sqp;
    public static final int sqr;
    public static final boolean sqf;
    
    public y$p(final double swv, final double n, final double swe, final double swv2, final double swj, final double swe2) {
        super();
        this.sqi = new y$u(0.0, 0.0, 0.0);
        this.sqp = new y$u(0.0, 0.0, 0.0);
        this.sqi.swv = swv;
        this.sqi.swe = swe;
        this.sqp.swv = swv2;
        this.sqp.swj = swj;
        this.sqp.swe = swe2;
    }
    
    public y$u d(final y$p y$p) {
        final double swv = this.sqi.swv;
        final double swv2 = this.sqp.swv;
        final double swv3 = y$p.sqi.swv;
        final double swv4 = y$p.sqp.swv;
        final double swj = this.sqi.swj;
        final double swj2 = this.sqp.swj;
        final double swj3 = y$p.sqi.swj;
        final double swj4 = y$p.sqp.swj;
        final double n = -(swv * swj4 - swv3 * swj4 - swv4 * (swj - swj3));
        final double n2 = swv2 * swj4 - swv4 * swj2;
        if (n2 == 0.0) {
            return this.s(y$p);
        }
        final double n3 = n / n2;
        final y$u y$u = new y$u(0.0, 0.0, 0.0);
        y$u.swv = this.sqi.swv + this.sqp.swv * n3;
        y$u.swj = this.sqi.swj + this.sqp.swj * n3;
        y$u.swe = this.sqi.swe + this.sqp.swe * n3;
        return y$u;
    }
    
    private y$u s(final y$p y$p) {
        final double swv = this.sqi.swv;
        final double swv2 = this.sqp.swv;
        final double swv3 = y$p.sqi.swv;
        final double swv4 = y$p.sqp.swv;
        final double swe = this.sqi.swe;
        final double swe2 = this.sqp.swe;
        final double swe3 = y$p.sqi.swe;
        final double swe4 = y$p.sqp.swe;
        final double n = -(swv * swe4 - swv3 * swe4 - swv4 * (swe - swe3));
        final double n2 = swv2 * swe4 - swv4 * swe2;
        if (n2 == 0.0) {
            return this.l(y$p);
        }
        final double n3 = n / n2;
        final y$u y$u = new y$u(0.0, 0.0, 0.0);
        y$u.swv = this.sqi.swv + this.sqp.swv * n3;
        y$u.swj = this.sqi.swj + this.sqp.swj * n3;
        y$u.swe = this.sqi.swe + this.sqp.swe * n3;
        return y$u;
    }
    
    private y$u l(final y$p y$p) {
        final double swj = this.sqi.swj;
        final double swj2 = this.sqp.swj;
        final double swj3 = y$p.sqi.swj;
        final double swj4 = y$p.sqp.swj;
        final double swe = this.sqi.swe;
        final double swe2 = this.sqp.swe;
        final double swe3 = y$p.sqi.swe;
        final double swe4 = y$p.sqp.swe;
        final double n = -(swj * swe4 - swj3 * swe4 - swj4 * (swe - swe3));
        final double n2 = swj2 * swe4 - swj4 * swe2;
        if (n2 == 0.0) {
            return null;
        }
        final double n3 = n / n2;
        final y$u y$u = new y$u(0.0, 0.0, 0.0);
        y$u.swv = this.sqi.swv + this.sqp.swv * n3;
        y$u.swj = this.sqi.swj + this.sqp.swj * n3;
        y$u.swe = this.sqi.swe + this.sqp.swe * n3;
        return y$u;
    }
    
    public y$u d(final y$u y$u, final y$u y$u2) {
        final y$u y$u3 = new y$u(this.sqi.swv, this.sqi.swj, this.sqi.swe);
        y$u3.x(this.sqp.d(y$u.s(this.sqi).l(y$u2) / this.sqp.l(y$u2)));
        if (this.sqp.l(y$u2) == 0.0) {
            return null;
        }
        return y$u3;
    }
}
