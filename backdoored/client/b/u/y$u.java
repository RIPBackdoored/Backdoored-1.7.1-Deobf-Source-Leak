package r.k.b.u;

public class y$u
{
    public double swv;
    public double swj;
    public double swe;
    public static final int swo;
    public static final boolean swt;
    
    public y$u(final double swv, final double swj, final double swe) {
        super();
        this.swv = swv;
        this.swj = swj;
        this.swe = swe;
    }
    
    public y$u d(final y$u y$u) {
        return new y$u(this.swv + y$u.swv, this.swj + y$u.swj, this.swe + y$u.swe);
    }
    
    public y$u d(final double n, final double n2, final double n3) {
        return new y$u(this.swv + n, this.swj + n2, this.swe + n3);
    }
    
    public y$u s(final y$u y$u) {
        return new y$u(this.swv - y$u.swv, this.swj - y$u.swj, this.swe - y$u.swe);
    }
    
    public y$u s(final double n, final double n2, final double n3) {
        return new y$u(this.swv - n, this.swj - n2, this.swe - n3);
    }
    
    public y$u xj() {
        final double swv = this.swv;
        final Object o2;
        final Object o = o2;
        final double n = (double)(swv / o);
        final double swj = this.swj;
        final Object o3 = o2;
        final double n2 = (double)(swj / o3);
        final double swe = this.swe;
        final Object o4 = o2;
        return new y$u(n, n2, (double)(swe / o4));
    }
    
    public double l(final y$u y$u) {
        return this.swv * y$u.swv + this.swj * y$u.swj + this.swe * y$u.swe;
    }
    
    public y$u y(final y$u y$u) {
        return new y$u(this.swj * y$u.swe - this.swe * y$u.swj, this.swe * y$u.swv - this.swv * y$u.swe, this.swv * y$u.swj - this.swj * y$u.swv);
    }
    
    public y$u d(final double n) {
        return new y$u(this.swv * n, this.swj * n, this.swe * n);
    }
    
    public y$u s(final double n) {
        return new y$u(this.swv / n, this.swj / n, this.swe / n);
    }
    
    public double xe() {
        return Math.sqrt(this.swv * this.swv + this.swj * this.swj + this.swe * this.swe);
    }
    
    public y$u x(final y$u y$u) {
        this.swv += y$u.swv;
        this.swj += y$u.swj;
        this.swe += y$u.swe;
        return this;
    }
    
    public y$u l(final double n, final double n2, final double n3) {
        this.swv += n;
        this.swj += n2;
        this.swe += n3;
        return this;
    }
    
    public y$u k(final y$u y$u) {
        this.swv -= y$u.swv;
        this.swj -= y$u.swj;
        return this;
    }
    
    public y$u y(final double n, final double n2, final double n3) {
        this.swv -= n;
        this.swj -= n2;
        this.swe -= n3;
        return this;
    }
    
    public y$u xo() {
        final double swv = this.swv;
        final Object o2;
        final Object o = o2;
        this.swv = (double)(swv / o);
        final double swj = this.swj;
        final Object o3 = o2;
        this.swj = (double)(swj / o3);
        final double swe = this.swe;
        final Object o4 = o2;
        this.swe = (double)(swe / o4);
        return this;
    }
    
    public y$u q(final y$u y$u) {
        this.swv = this.swj * y$u.swe - this.swe * y$u.swj;
        this.swj = this.swe * y$u.swv - this.swv * y$u.swe;
        this.swe = this.swv * y$u.swj - this.swj * y$u.swv;
        return this;
    }
    
    public y$u l(final double n) {
        this.swv *= n;
        this.swj *= n;
        this.swe *= n;
        return this;
    }
    
    public y$u y(final double n) {
        this.swv /= n;
        this.swj /= n;
        return this;
    }
    
    @Override
    public String toString() {
        return "(X: " + this.swv + " Y: " + this.swj + " Z: " + this.swe + ")";
    }
}
