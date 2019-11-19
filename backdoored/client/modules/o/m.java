package r.k.d.o;

import r.k.d.m.g;

public abstract class m<T>
{
    private String sdr;
    private g sdf;
    private y sdb;
    private T sdw;
    private final T sdg;
    public static final int sdu;
    public static final boolean sdz;
    
    public m(final String sdr, final g sdf, final y sdb, final T t) {
        super();
        this.sdr = sdr;
        this.sdf = sdf;
        this.sdb = sdb;
        this.sdw = t;
        this.sdg = t;
        sdb.vq.add(this);
        j.sgi.add(this);
    }
    
    public String o() {
        return this.sdr;
    }
    
    public g yk() {
        return this.sdf;
    }
    
    public y yq() {
        return this.sdb;
    }
    
    public T yv() {
        return this.sdw;
    }
    
    public void d(final T sdw) {
        this.sdw = sdw;
    }
    
    public T yj() {
        return this.sdg;
    }
    
    public abstract Class<?> sy();
    
    public Number ye() {
        return null;
    }
    
    public Number yo() {
        return null;
    }
    
    public Enum[] yt() {
        return null;
    }
}
