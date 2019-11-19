package r.k.c;

import r.k.d.m.g;
import org.lwjgl.opengl.GL11;
import r.k.h;
import java.util.Iterator;
import r.k.c.c.y;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;

public class n extends GuiScreen
{
    public static int sht;
    public static int shn;
    public static final boolean shi;
    public static final int shp;
    public static final boolean shr;
    
    public n() {
        super();
        this.allowUserInput = true;
    }
    
    public void func_73866_w_() {
        super.initGui();
    }
    
    protected void func_73869_a(final char c, final int n) throws IOException {
        super.keyTyped(c, n);
    }
    
    public void func_73863_a(final int sht, final int shn, final float n) {
        n.sht = sht;
        n.shn = shn;
        final Iterator<y> iterator = m.s().iterator();
        if (iterator.hasNext()) {
            final y y = iterator.next();
            if (y.eb) {
                y.l(sht, shn);
            }
        }
        this.x(sht, shn);
    }
    
    private void x(final int n, final int n2) {
        final y d = m.d(n, n2);
        if (d != null) {
            final g d2 = m.d(d);
            if (d2 != null) {
                h.mc.renderEngine.bindTexture(m.resourceLocation);
                GL11.glPushAttrib(1048575);
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                GL11.glColor4f(255.0f, 255.0f, 255.0f, 1.0f);
                final int n3 = this.fontRenderer.getStringWidth(d2.ad) + 1;
                final int n4 = this.fontRenderer.FONT_HEIGHT + 1;
                this.drawTexturedModalRect(n + 5, n2 + 5, n3, n4, n3, n4);
                GL11.glPopMatrix();
                GL11.glPopAttrib();
                this.fontRenderer.drawString(d2.ad, n + 6, n2 + 6, 0);
            }
        }
    }
    
    protected void func_73864_a(final int p0, final int p1, final int p2) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: nop            
        //     7: invokestatic    r/k/c/m.s:()Ljava/util/ArrayList;
        //    10: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //    13: astore          4
        //    15: aload           5
        //    17: getfield        r/k/c/c/y.eb:Z
        //    20: ifne            24
        //    23: nop            
        //    24: iload_1        
        //    25: aload           5
        //    27: getfield        r/k/c/c/y.en:I
        //    30: if_icmplt       159
        //    33: iload_1        
        //    34: aload           5
        //    36: getfield        r/k/c/c/y.en:I
        //    39: aload           5
        //    41: getfield        r/k/c/c/y.ep:I
        //    44: iadd           
        //    45: if_icmpgt       159
        //    48: iload_2        
        //    49: aload           5
        //    51: getfield        r/k/c/c/y.ei:I
        //    54: if_icmplt       159
        //    57: iload_2        
        //    58: aload           5
        //    60: getfield        r/k/c/c/y.ei:I
        //    63: aload           5
        //    65: getfield        r/k/c/c/y.er:I
        //    68: iadd           
        //    69: if_icmpgt       159
        //    72: invokestatic    r/k/c/c/j.h:()Ljava/util/ArrayList;
        //    75: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //    78: astore          6
        //    80: aload           6
        //    82: invokeinterface java/util/Iterator.hasNext:()Z
        //    87: ifeq            142
        //    90: aload           6
        //    92: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    97: checkcast       Lr/k/c/c/j;
        //   100: astore          7
        //   102: aload           5
        //   104: getfield        r/k/c/c/y.ef:Ljava/lang/String;
        //   107: aload           7
        //   109: getfield        r/k/c/c/j.ef:Ljava/lang/String;
        //   112: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   115: ifeq            141
        //   118: aload           7
        //   120: invokevirtual   r/k/c/c/j.yk:()Lr/k/d/m/g;
        //   123: aload           7
        //   125: invokevirtual   r/k/c/c/j.yk:()Lr/k/d/m/g;
        //   128: invokevirtual   r/k/d/m/g.lo:()Z
        //   131: ifne            136
        //   134: iconst_1       
        //   135: nop            
        //   136: iconst_0       
        //   137: invokevirtual   r/k/d/m/g.s:(Z)V
        //   140: return         
        //   141: nop            
        //   142: aload           5
        //   144: aload           5
        //   146: getfield        r/k/c/c/y.eo:Z
        //   149: ifne            154
        //   152: iconst_1       
        //   153: nop            
        //   154: iconst_0       
        //   155: putfield        r/k/c/c/y.eo:Z
        //   158: return         
        //   159: iload_1        
        //   160: aload           5
        //   162: getfield        r/k/c/c/y.en:I
        //   165: if_icmplt       229
        //   168: iload_1        
        //   169: aload           5
        //   171: getfield        r/k/c/c/y.en:I
        //   174: aload           5
        //   176: getfield        r/k/c/c/y.ep:I
        //   179: iadd           
        //   180: if_icmpgt       229
        //   183: iload_2        
        //   184: aload           5
        //   186: getfield        r/k/c/c/y.ei:I
        //   189: if_icmplt       229
        //   192: iload_2        
        //   193: aload           5
        //   195: getfield        r/k/c/c/y.ei:I
        //   198: aload           5
        //   200: getfield        r/k/c/c/y.er:I
        //   203: iadd           
        //   204: if_icmpgt       229
        //   207: iload_3        
        //   208: iconst_1       
        //   209: if_icmpne       229
        //   212: aload           5
        //   214: aload           5
        //   216: getfield        r/k/c/c/y.et:Z
        //   219: ifne            224
        //   222: iconst_1       
        //   223: nop            
        //   224: iconst_0       
        //   225: putfield        r/k/c/c/y.et:Z
        //   228: return         
        //   229: nop            
        //   230: aload_0        
        //   231: iload_1        
        //   232: iload_2        
        //   233: iload_3        
        //   234: invokespecial   net/minecraft/client/gui/GuiScreen.mouseClicked:(III)V
        //   237: return         
        //    Exceptions:
        //  throws java.io.IOException
        //    StackMapTable: 00 10 FF 00 04 00 0B 07 00 18 01 01 01 00 00 00 00 00 00 01 00 00 00 FF 00 09 00 0B 07 00 18 01 01 01 07 00 36 07 00 49 00 00 01 01 01 00 00 08 FF 00 37 00 0B 07 00 18 01 01 01 07 00 36 07 00 49 07 00 36 00 01 01 01 00 00 FF 00 37 00 0B 07 00 18 01 01 01 07 00 36 07 00 49 07 00 36 07 00 C5 01 01 01 00 01 07 00 95 FF 00 00 00 0B 07 00 18 01 01 01 07 00 36 07 00 49 07 00 36 07 00 C5 01 01 01 00 02 07 00 95 01 03 FF 00 00 00 0B 07 00 18 01 01 01 07 00 36 07 00 49 07 00 36 00 01 01 01 00 00 4B 07 00 49 FF 00 00 00 0B 07 00 18 01 01 01 07 00 36 07 00 49 07 00 36 00 01 01 01 00 02 07 00 49 01 FF 00 03 00 0B 07 00 18 01 01 01 07 00 36 07 00 49 00 00 01 01 01 00 00 F7 00 40 07 00 49 FF 00 00 00 0B 07 00 18 01 01 01 07 00 36 07 00 49 00 00 01 01 01 00 02 07 00 49 01 03 FF 00 00 00 0B 07 00 18 01 01 01 07 00 36 00 00 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean func_73868_f() {
        return false;
    }
    
    static {
        n.sht = 0;
        n.shn = 0;
    }
}
