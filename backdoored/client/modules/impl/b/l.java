package r.k.d.m.b;

import net.minecraftforge.fml.common.FMLLog;
import net.minecraft.util.text.ITextComponent;
import r.k.b.c.h;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.TextComponentString;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import net.minecraft.client.renderer.OpenGlHelper;
import java.io.File;
import net.minecraft.client.shader.Framebuffer;
import java.text.SimpleDateFormat;

class l implements Runnable
{
    private static final SimpleDateFormat td;
    private int ts;
    private int tl;
    private int[] ty;
    private Framebuffer framebuffer;
    private File tk;
    public static final boolean tq;
    public static final int tv;
    public static final boolean tj;
    
    l(final int ts, final int tl, final int[] ty, final Framebuffer framebuffer, final File tk) {
        super();
        this.ts = ts;
        this.tl = tl;
        this.ty = ty;
        this.framebuffer = framebuffer;
        this.tk = tk;
    }
    
    @Override
    public void run() {
        d(this.ty, this.ts, this.tl);
        BufferedImage bufferedImage = null;
        try {
            if (OpenGlHelper.isFramebufferEnabled()) {
                bufferedImage = new BufferedImage(this.framebuffer.framebufferWidth, this.framebuffer.framebufferHeight, 1);
                final int n2;
                int n = n2 = this.framebuffer.framebufferTextureHeight - this.framebuffer.framebufferHeight;
                if (n < this.framebuffer.framebufferTextureHeight) {
                    int n3 = 0;
                    if (n3 < this.framebuffer.framebufferWidth) {
                        bufferedImage.setRGB(n3, n - n2, this.ty[n * this.framebuffer.framebufferTextureWidth + n3]);
                        ++n3;
                    }
                    ++n;
                }
            }
            bufferedImage.setRGB(0, 0, this.ts, this.tl, this.ty, 0, this.ts);
            final File l = l(this.tk);
            ImageIO.write(bufferedImage, "png", l);
            final TextComponentString textComponentString = new TextComponentString(l.getName());
            ((ITextComponent)textComponentString).getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, l.getAbsolutePath()));
            ((ITextComponent)textComponentString).getStyle().setUnderlined(Boolean.valueOf(true));
            h.d((ITextComponent)new TextComponentTranslation("screenshot.success", new Object[] { textComponentString }));
        }
        catch (Exception ex) {
            FMLLog.log.info("Failed to save screenshot");
            ex.printStackTrace();
            h.d((ITextComponent)new TextComponentTranslation("screenshot.failure", new Object[] { ex.getMessage() }));
        }
    }
    
    private static void d(final int[] array, final int n, final int n2) {
        final int[] array2 = new int[n];
        final int n3 = n2 / 2;
        int n4 = 0;
        if (n4 < n3) {
            System.arraycopy(array, n4 * n, array2, 0, n);
            System.arraycopy(array, (n2 - 1 - n4) * n, array, n4 * n, n);
            System.arraycopy(array2, 0, array, (n2 - 1 - n4) * n, n);
            ++n4;
        }
    }
    
    private static File l(final File p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: aconst_null    
        //     5: areturn        
        //     6: nop            
        //     7: nop            
        //     8: getstatic       r/k/d/m/b/l.td:Ljava/text/SimpleDateFormat;
        //    11: new             Ljava/util/Date;
        //    14: dup            
        //    15: invokespecial   java/util/Date.<init>:()V
        //    18: invokevirtual   java/text/SimpleDateFormat.format:(Ljava/util/Date;)Ljava/lang/String;
        //    21: astore_1       
        //    22: iconst_1       
        //    23: istore_2       
        //    24: new             Ljava/io/File;
        //    27: dup            
        //    28: aload_0        
        //    29: new             Ljava/lang/StringBuilder;
        //    32: dup            
        //    33: invokespecial   java/lang/StringBuilder.<init>:()V
        //    36: aload_1        
        //    37: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    40: iload_2        
        //    41: iconst_1       
        //    42: if_icmpne       48
        //    45: ldc             ""
        //    47: nop            
        //    48: new             Ljava/lang/StringBuilder;
        //    51: dup            
        //    52: invokespecial   java/lang/StringBuilder.<init>:()V
        //    55: ldc             "_"
        //    57: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    60: iload_2        
        //    61: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    64: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    67: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    70: ldc             ".png"
        //    72: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    75: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    78: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    81: astore_3       
        //    82: aload_3        
        //    83: invokevirtual   java/io/File.exists:()Z
        //    86: ifne            91
        //    89: aload_3        
        //    90: areturn        
        //    91: iinc            2, 1
        //    94: nop            
        //    StackMapTable: 00 07 FF 00 04 00 07 07 00 24 00 00 00 00 00 01 00 00 01 FF 00 01 00 07 07 00 24 00 00 00 01 01 01 00 00 FF 00 0F 00 07 07 00 24 07 00 C0 01 00 01 01 01 00 00 FF 00 17 00 07 07 00 24 07 00 C0 01 00 01 01 01 00 04 08 00 18 08 00 18 07 00 24 07 00 C2 FF 00 12 00 07 07 00 24 07 00 C0 01 00 01 01 01 00 05 08 00 18 08 00 18 07 00 24 07 00 C2 07 00 C0 FF 00 17 00 07 07 00 24 07 00 C0 01 07 00 24 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        td = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
    }
}
