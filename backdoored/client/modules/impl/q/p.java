package r.k.d.m.q;

import net.minecraft.client.gui.inventory.GuiInventory;
import java.util.Objects;

public class p extends i
{
    private static final String sdn;
    public static final int sdi;
    public static final boolean sdp;
    
    public p() throws Exception {
        super();
        this.mj = new u().sy(i.g("Backdoored/announcer.js")).d(p.me);
    }
    
    String h(final String s) {
        try {
            return Objects.<String>requireNonNull(this.mj.s("onSendMessage", s));
        }
        catch (Exception ex) {
            final Throwable t;
            t.printStackTrace();
            return s;
        }
    }
    
    String k(final int n) {
        return this.d("onMove", n);
    }
    
    String sd(final String s) {
        return this.d("onAttack", s);
    }
    
    String s(final int n, final String s) {
        return this.d("onBlocksBreak", n, s);
    }
    
    String l(final int n, final String s) {
        return this.d("onBlocksPlace", n, s);
    }
    
    String d(final GuiInventory guiInventory) {
        return this.d("onOpenInventory", new Object[0]);
    }
    
    String yd() {
        return this.d("onScreenshot", new Object[0]);
    }
    
    String ys() {
        return this.d("onModuleEnabled", new Object[0]);
    }
    
    String yl() {
        return this.d("onModuleDisabled", new Object[0]);
    }
    
    String yy() {
        return this.d("onPlayerJoin", new Object[0]);
    }
    
    String yx() {
        return this.d("onPlayerLeave", new Object[0]);
    }
    
    private String d(final String s, final Object... array) {
        try {
            return (String)this.mj.s(s, array);
        }
        catch (Exception ex) {
            final Throwable t;
            t.printStackTrace();
            return null;
        }
    }
    
    static {
        sdn = "Backdoored/announcer.js";
    }
}
