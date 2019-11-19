package r.k.p.m;

import r.k.p.c.h;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import \u0000r.\u0000k.\u0000p.\u0000m.\u0000m;
import r.k.p.c.y;
import r.k.u;
import net.minecraft.client.gui.GuiScreen;

public class m extends GuiScreen
{
    public static final boolean suw;
    public static final int sug;
    public static final boolean suu;
    
    public m() {
        super();
    }
    
    public void func_73866_w_() {
    }
    
    public void func_73863_a(final int n, final int n2, final float n3) {
        int n4 = 0;
        if (n4 < u.lsp.llz.size()) {
            u.lsp.llz.get(n4).d(n, n2, n3);
            ++n4;
        }
    }
    
    protected void func_73864_a(final int n, final int n2, final int n3) {
        int n4 = 0;
        u.lsp.llz.get(n4).d(n, n2, n3);
        ++n4;
    }
    
    protected void func_146286_b(final int n, final int n2, final int n3) {
        int n4 = 0;
        if (n4 < u.lsp.llz.size()) {
            u.lsp.llz.get(n4).s(n, n2, n3);
            ++n4;
        }
    }
    
    public void func_146273_a(final int n, final int n2, final int n3, final long n4) {
        int n5 = 0;
        if (n5 < u.lsp.llz.size()) {
            u.lsp.llz.get(n5).l(n, n2, n3);
            ++n5;
        }
    }
    
    public boolean func_73868_f() {
        return false;
    }
    
    public void func_146281_b() {
        u.lsp.llz.stream().filter((Predicate<? super Object>)\u0000m::y).<Object>map((Function<? super Object, ?>)\u0000m::l).forEach((Consumer<? super Object>)\u0000m::d);
    }
    
    private static /* synthetic */ void d(final h h) {
        h.y(false);
    }
    
    private static /* synthetic */ h l(final y y) {
        return (h)y;
    }
    
    private static /* synthetic */ boolean y(final y y) {
        return y instanceof h;
    }
}
