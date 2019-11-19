package r.k.d.m.player;

import net.minecraft.client.Minecraft;
import java.awt.event.ActionEvent;
import java.util.Random;
import net.minecraft.util.ChatAllowedCharacters;
import java.nio.file.Files;
import java.nio.file.Paths;
import r.k.d.o.h.i.x;
import r.k.d.o.h.u;
import javax.swing.Timer;
import r.k.d.o.m;
import java.io.File;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Spammer", description = "Spam the chat", category = y.PLAYER)
@g$i(name = "Spammer", description = "Spam the chat", category = y.PLAYER)
public class Spammer extends g
{
    private static final char[] yd;
    private static final File ys;
    private p$s yl;
    private m<p$s> yy;
    private m<Integer> yx;
    private Timer yk;
    private Timer yq;
    private Timer yv;
    public static final boolean yj;
    public static final int ye;
    public static final boolean yo;
    private static final char[] yd;
    private static final File ys;
    private p$s yl;
    private m<p$s> yy;
    private m<Integer> yx;
    private Timer yk;
    private Timer yq;
    private Timer yv;
    public static final boolean yj;
    public static final int ye;
    public static final boolean yo;
    
    public Spammer() {
        super();
        this.yy = (m<p$s>)new u("Mode", this, p$s.ea);
        this.yx = (m<Integer>)new x("Delay", this, 2, 1, 60);
        try {
            Spammer.ys.createNewFile();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void v() {
        // 
        // This method could not be decompiled.
        // 
        // Could not show original bytecode, likely due to the same error.
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Spammer() {
        super();
        this.yy = (m<p$s>)new u("Mode", this, p$s.ea);
        this.yx = (m<Integer>)new x("Delay", this, 2, 1, 60);
        try {
            Spammer.ys.createNewFile();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void v() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: nop            
        //     7: aload_0        
        //     8: getfield        r/k/d/m/player/Spammer.yx:Lr/k/d/o/m;
        //    11: ifnonnull       32
        //    14: aload_0        
        //    15: new             Lr/k/d/o/h/i/x;
        //    18: dup            
        //    19: ldc             "Delay"
        //    21: aload_0        
        //    22: iconst_2       
        //    23: iconst_1       
        //    24: bipush          60
        //    26: invokespecial   r/k/d/o/h/i/x.<init>:(Ljava/lang/String;Lr/k/d/m/g;III)V
        //    29: putfield        r/k/d/m/player/Spammer.yx:Lr/k/d/o/m;
        //    32: getstatic       r/k/d/m/p/p$t.nj:[I
        //    35: aload_0        
        //    36: getfield        r/k/d/m/player/Spammer.yy:Lr/k/d/o/m;
        //    39: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //    42: checkcast       Lr/k/d/m/p/p$s;
        //    45: invokevirtual   r/k/d/m/p/p$s.ordinal:()I
        //    48: iaload         
        //    49: tableswitch {
        //                2: 76
        //                3: 154
        //                4: 196
        //          default: 196
        //        }
        //    76: new             Ljava/lang/String;
        //    79: dup            
        //    80: getstatic       r/k/d/m/player/Spammer.ys:Ljava/io/File;
        //    83: invokevirtual   java/io/File.getCanonicalPath:()Ljava/lang/String;
        //    86: iconst_0       
        //    87: anewarray       Ljava/lang/String;
        //    90: invokestatic    java/nio/file/Paths.get:(Ljava/lang/String;[Ljava/lang/String;)Ljava/nio/file/Path;
        //    93: invokestatic    java/nio/file/Files.readAllBytes:(Ljava/nio/file/Path;)[B
        //    96: invokespecial   java/lang/String.<init>:([B)V
        //    99: ldc             "\n"
        //   101: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //   104: astore_1       
        //   105: aload_1        
        //   106: arraylength    
        //   107: istore_2       
        //   108: aload_0        
        //   109: new             Ljavax/swing/Timer;
        //   112: dup            
        //   113: aload_0        
        //   114: getfield        r/k/d/m/player/Spammer.yx:Lr/k/d/o/m;
        //   117: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   120: checkcast       Ljava/lang/Integer;
        //   123: invokevirtual   java/lang/Integer.intValue:()I
        //   126: sipush          1000
        //   129: imul           
        //   130: new             Lr/k/d/m/p/p$x;
        //   133: dup            
        //   134: aload_0        
        //   135: iload_2        
        //   136: aload_1        
        //   137: invokespecial   r/k/d/m/p/p$x.<init>:(Lr/k/d/m/p/p;I[Ljava/lang/String;)V
        //   140: invokespecial   javax/swing/Timer.<init>:(ILjava/awt/event/ActionListener;)V
        //   143: putfield        r/k/d/m/player/Spammer.yk:Ljavax/swing/Timer;
        //   146: aload_0        
        //   147: getfield        r/k/d/m/player/Spammer.yk:Ljavax/swing/Timer;
        //   150: invokevirtual   javax/swing/Timer.start:()V
        //   153: nop            
        //   154: aload_0        
        //   155: new             Ljavax/swing/Timer;
        //   158: dup            
        //   159: aload_0        
        //   160: getfield        r/k/d/m/player/Spammer.yx:Lr/k/d/o/m;
        //   163: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   166: checkcast       Ljava/lang/Integer;
        //   169: invokevirtual   java/lang/Integer.intValue:()I
        //   172: sipush          1000
        //   175: imul           
        //   176: aload_0        
        //   177: invokedynamic   BootstrapMethod #0, actionPerformed:(Lr/k/d/m/p/p;)Ljava/awt/event/ActionListener;
        //   182: invokespecial   javax/swing/Timer.<init>:(ILjava/awt/event/ActionListener;)V
        //   185: putfield        r/k/d/m/player/Spammer.yq:Ljavax/swing/Timer;
        //   188: aload_0        
        //   189: getfield        r/k/d/m/player/Spammer.yq:Ljavax/swing/Timer;
        //   192: invokevirtual   javax/swing/Timer.start:()V
        //   195: nop            
        //   196: aload_0        
        //   197: new             Ljavax/swing/Timer;
        //   200: dup            
        //   201: aload_0        
        //   202: getfield        r/k/d/m/player/Spammer.yx:Lr/k/d/o/m;
        //   205: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   208: checkcast       Ljava/lang/Integer;
        //   211: invokevirtual   java/lang/Integer.intValue:()I
        //   214: sipush          1000
        //   217: imul           
        //   218: invokedynamic   BootstrapMethod #1, actionPerformed:()Ljava/awt/event/ActionListener;
        //   223: invokespecial   javax/swing/Timer.<init>:(ILjava/awt/event/ActionListener;)V
        //   226: putfield        r/k/d/m/player/Spammer.yv:Ljavax/swing/Timer;
        //   229: nop            
        //   230: astore_1       
        //   231: new             Ljava/lang/StringBuilder;
        //   234: dup            
        //   235: invokespecial   java/lang/StringBuilder.<init>:()V
        //   238: ldc             "Disabled spammer due to error: "
        //   240: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   243: aload_1        
        //   244: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   247: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   250: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   253: ldc             "red"
        //   255: invokestatic    r/k/b/c/h.o:(Ljava/lang/String;Ljava/lang/String;)V
        //   258: aload_1        
        //   259: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   262: aload_0        
        //   263: iconst_0       
        //   264: invokevirtual   r/k/d/m/player/Spammer.s:(Z)V
        //   267: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  32     76     230    268    Ljava/lang/Exception;
        //  76     108    230    268    Ljava/lang/Exception;
        //  108    154    230    268    Ljava/lang/Exception;
        //  154    196    230    268    Ljava/lang/Exception;
        //  196    229    230    268    Ljava/lang/Exception;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void j() {
        if (this.lo()) {
            this.yl = this.yy.yv();
        }
    }
    
    public void t() {
        final Timer[] array = { this.yk, this.yq, this.yv };
        final int length = array.length;
        int n = 0;
        if (n < length) {
            final Timer timer = array[n];
            try {
                timer.stop();
            }
            catch (Exception ex) {
                ++n;
            }
        }
    }
    
    private String n() {
        int n = 0;
        final StringBuilder sb;
        if (n < 256) {
            char c = ' ';
            if (c == ' ' || !ChatAllowedCharacters.isAllowedCharacter(c)) {
                c = Spammer.yd[new Random().nextInt(Spammer.yd.length)];
            }
            sb.append(c);
            ++n;
        }
        return sb.toString();
    }
    
    private static /* synthetic */ void d(final ActionEvent actionEvent) {
    }
    
    private /* synthetic */ void s(final ActionEvent actionEvent) {
        Spammer.mc.player.sendChatMessage(this.n());
    }
    
    static /* synthetic */ Minecraft i() {
        return Spammer.mc;
    }
    
    static {
        yd = "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{|}~\u00e2\u0152\u201a\u00c3\u2021\u00c3¼\u00c3©\u00c3¢\u00c3¤\u00c3 \u00c3¥\u00c3§\u00c3ª\u00c3«\u00c3¨\u00c3¯\u00c3®\u00c3¬\u00c3\u201e\u00c3\u2026\u00c3\u2030\u00c3¦\u00c3\u2020\u00c3´\u00c3¶\u00c3²\u00c3»\u00c3¹\u00c3¿\u00c3\u2013\u00c3\u0153\u00c3¸\u00c2£\u00c3\u02dc\u00c3\u2014\u00c6\u2019\u00c3¡\u00c3\u00ad\u00c3³\u00c3º\u00c3±\u00c3\u2018\u00c2ª\u00c2º".toCharArray();
        ys = new File("Backdoored/spammer.txt");
    }
}
