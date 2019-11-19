package r.k.c.o;

import r.k.c.c.y;
import java.util.Iterator;
import java.util.function.Consumer;
import \u0000r.\u0000k.\u0000c.\u0000o.\u0000m;
import r.k.c.c.j;
import r.k.c.n;
import org.lwjgl.input.Mouse;

public class m extends h
{
    private r.k.c.c.m szb;
    public static final boolean szw;
    public static final int szg;
    public static final boolean szu;
    
    public m() {
        super();
    }
    
    @Override
    public void li() {
        if (Mouse.isButtonDown(0) || Mouse.isButtonDown(1)) {
            final Iterator<r.k.c.c.m> iterator = r.k.c.c.m.kd().iterator();
            if (iterator.hasNext() && iterator.next().sme) {
                goto Label_0055;
            }
            final Iterator<r.k.c.c.m> iterator2 = r.k.c.c.m.kd().iterator();
            if (iterator2.hasNext()) {
                final r.k.c.c.m m = iterator2.next();
                if (n.sht > m.en && n.shn > m.ei && n.sht < m.en + 100 && n.shn < m.ei + 20) {
                    this.d(m);
                }
                final Iterator<j> iterator3 = m.xh().iterator();
                if (iterator3.hasNext()) {
                    final j j = iterator3.next();
                    if (n.sht > j.en && n.shn > j.ei && n.sht < j.en + j.ep && n.shn < j.ei + j.er) {
                        this.d(m);
                    }
                    final Iterator<r.k.c.c.h> iterator4 = j.kl().iterator();
                    if (iterator4.hasNext()) {
                        final r.k.c.c.h h = iterator4.next();
                        if (n.sht > h.en && n.shn > h.ei && n.sht < h.en + h.ep && n.shn < h.ei + h.er) {
                            this.d(m);
                        }
                    }
                }
            }
        }
        this.xz();
        final Iterator<r.k.c.c.m> iterator5 = r.k.c.c.m.kd().iterator();
        if (iterator5.hasNext()) {
            final r.k.c.c.m i = iterator5.next();
            if (Mouse.isButtonDown(0) && n.sht > i.en && n.shn > i.ei && n.sht < i.en + 100 && n.shn < i.ei + 20 && !i.sme) {
                i.smo = n.sht - i.en;
                i.smt = n.shn - i.ei;
                i.sme = true;
            }
            if (i.sme) {
                i.en = n.sht - i.smo;
                i.ei = n.shn - i.smt;
            }
            if (!Mouse.isButtonDown(0)) {
                i.sme = false;
            }
            if (!i.sme || Mouse.isButtonDown(0)) {}
        }
        final Iterator<r.k.c.c.m> iterator6 = r.k.c.c.m.kd().iterator();
        if (iterator6.hasNext()) {
            final r.k.c.c.m k = iterator6.next();
            if (!k.et) {
                final Iterator<j> iterator7 = k.xh().iterator();
                if (iterator7.hasNext()) {
                    final j l = iterator7.next();
                    l.eb = false;
                    l.kl().forEach((Consumer<? super r.k.c.c.h>)\u0000m::s);
                }
            }
            k.xh().forEach((Consumer<? super j>)\u0000m::d);
            final y y = null;
            final Iterator<j> iterator8 = k.xh().iterator();
            if (iterator8.hasNext()) {
                final j j2 = iterator8.next();
                j2.en = k.en;
                if (y != null) {
                    j2.ei = y.ei + y.er;
                }
                j2.ei = k.ei + 20;
            }
        }
    }
    
    private void d(final r.k.c.c.m szb) {
        this.szb = szb;
    }
    
    private void xz() {
        if (this.szb != null) {
            r.k.c.c.m.h().remove(this.szb);
            r.k.c.c.m.h().add(this.szb);
        }
    }
    
    private static /* synthetic */ void d(final j j) {
        j.eb = true;
    }
    
    private static /* synthetic */ void s(final r.k.c.c.h h) {
        h.eb = false;
    }
}
