package r.k.p.c;

import net.minecraft.client.gui.ScaledResolution;
import r.k.u;
import r.k.p.m.m;
import java.awt.Color;

public abstract class h extends y
{
    private boolean sun;
    protected r.k.b.i.h sui;
    private static final Color sup;
    public static final boolean sur;
    public static final int suf;
    public static final boolean sub;
    private boolean sun;
    protected r.k.b.i.h sui;
    private static final Color sup;
    public static final boolean sur;
    public static final int suf;
    public static final boolean sub;
    
    public h(final String s) {
        super();
        this.sun = false;
        this.sui = new r.k.b.i.h(0, 0);
        this.v(s);
        this.d(true);
    }
    
    @Override
    public void d(final int p0, final int p1, final float p2) {
        // 
        // This method could not be decompiled.
        // 
        // Could not show original bytecode, likely due to the same error.
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public h(final String s) {
        super();
        this.sun = false;
        this.sui = new r.k.b.i.h(0, 0);
        this.v(s);
        this.d(true);
    }
    
    @Override
    public void d(final int n, final int n2, final float n3) {
        if (!this.xb()) {
            return;
        }
        Label_0368: {
            if (h.mc.currentScreen instanceof m) {
                if (u.lsp.k(n, n2) == this || this.sun) {
                    if (!this.u()) {
                        r.k.b.u.m.l(this.z().slo - 1, this.z().slt - 1, this.z().slo + this.c().slo + 1, this.z().slt + this.c().slt + 1, h.sup.getRGB());
                    }
                    if (this.sun) {
                        break Label_0368;
                    }
                    final int n4 = h.mc.fontRenderer.getStringWidth(this.o()) + 1;
                    final int n5 = h.mc.fontRenderer.FONT_HEIGHT + 1;
                    if (!this.u()) {
                        r.k.b.u.m.l(n + 5, n2 + 5, n + 5 + n4, n2 + 5 + n5, h.sup.getRGB());
                    }
                    r.k.b.u.m.l(n + 5, n2 + 5, n + 5 + n4, n2 + 5 + n5, Color.WHITE.getRGB());
                    h.mc.fontRenderer.drawString(this.o(), n + 6, n2 + 6, 0);
                }
                if (!this.u()) {
                    r.k.b.u.m.l(this.z().slo - 1, this.z().slt - 1, this.z().slo + this.c().slo + 1, this.z().slt + this.c().slt + 1, h.sup.getRGB());
                }
                r.k.b.u.m.l(this.z().slo - 1, this.z().slt - 1, this.z().slo + this.c().slo + 1, this.z().slt + this.c().slt + 1, Color.GRAY.getRGB());
            }
        }
        this.xf();
    }
    
    @Override
    public void d(final int p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: getstatic       r/k/u.lsp:Lr/k/p/o;
        //     9: iload_1        
        //    10: iload_2        
        //    11: invokevirtual   r/k/p/o.k:(II)Lr/k/p/c/y;
        //    14: aload_0        
        //    15: if_acmpne       20
        //    18: iconst_1       
        //    19: nop            
        //    20: iconst_0       
        //    21: istore          4
        //    23: iload_3        
        //    24: iconst_1       
        //    25: if_icmpne       47
        //    28: iload           4
        //    30: ifeq            47
        //    33: aload_0        
        //    34: aload_0        
        //    35: invokevirtual   r/k/p/c/h.u:()Z
        //    38: ifne            43
        //    41: iconst_1       
        //    42: nop            
        //    43: iconst_0       
        //    44: invokevirtual   r/k/p/c/h.d:(Z)V
        //    47: iload           4
        //    49: ifeq            96
        //    52: aload_0        
        //    53: iconst_1       
        //    54: putfield        r/k/p/c/h.sun:Z
        //    57: aload_0        
        //    58: getfield        r/k/p/c/h.sui:Lr/k/b/i/h;
        //    61: iload_1        
        //    62: aload_0        
        //    63: invokevirtual   r/k/p/c/h.z:()Lr/k/b/i/h;
        //    66: getfield        r/k/b/i/h.slo:I
        //    69: isub           
        //    70: putfield        r/k/b/i/h.slo:I
        //    73: aload_0        
        //    74: getfield        r/k/p/c/h.sui:Lr/k/b/i/h;
        //    77: iload_2        
        //    78: aload_0        
        //    79: invokevirtual   r/k/p/c/h.z:()Lr/k/b/i/h;
        //    82: getfield        r/k/b/i/h.slt:I
        //    85: isub           
        //    86: putfield        r/k/b/i/h.slt:I
        //    89: getstatic       r/k/u.lsp:Lr/k/p/o;
        //    92: aload_0        
        //    93: invokevirtual   r/k/p/o.x:(Lr/k/p/c/y;)V
        //    96: return         
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void s(final int n, final int n2, final int n3) {
        this.sun = false;
    }
    
    @Override
    public void l(final int n, final int n2, final int n3) {
        if (this.sun) {
            this.z().slo = n - this.sui.slo;
            this.z().slt = n2 - this.sui.slt;
            this.xf();
        }
    }
    
    protected void xf() {
        if (this.z().slo < 0) {
            this.z().slo = 0;
        }
        if (this.z().slt < 0) {}
        final ScaledResolution scaledResolution = new ScaledResolution(h.mc);
        if (this.z().slo + this.c().slo > scaledResolution.getScaledWidth()) {
            this.z().slo = scaledResolution.getScaledWidth() - this.c().slo;
        }
        if (this.z().slt + this.c().slt > scaledResolution.getScaledHeight()) {
            this.z().slt = scaledResolution.getScaledHeight() - this.c().slt;
        }
    }
    
    public void y(final boolean sun) {
        this.sun = sun;
    }
    
    public boolean xb() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: iconst_1       
        //     5: ireturn        
        //     6: nop            
        //     7: aload_0        
        //     8: invokevirtual   r/k/p/c/h.u:()Z
        //    11: ifne            26
        //    14: getstatic       r/k/p/c/h.mc:Lnet/minecraft/client/Minecraft;
        //    17: getfield        net/minecraft/client/Minecraft.currentScreen:Lnet/minecraft/client/gui/GuiScreen;
        //    20: instanceof      Lr/k/p/m/m;
        //    23: ifeq            28
        //    26: iconst_1       
        //    27: nop            
        //    28: iconst_0       
        //    29: ireturn        
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        sup = new Color(255, 0, 0, 127);
    }
}
