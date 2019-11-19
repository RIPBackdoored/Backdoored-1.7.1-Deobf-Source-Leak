package r.k.d.m.ui;

import org.lwjgl.opengl.GL11;
import java.time.LocalTime;
import r.k.d.o.h.i.s;
import r.k.d.o.h.i.x;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Time", description = "Display time", category = y.UI)
@g$i(name = "Time", description = "Display time", category = y.UI)
public class Time extends g
{
    private final m<Boolean> sag;
    private final m<Integer> sau;
    private final m<Integer> saz;
    private final m<Integer> sac;
    private final m<Double> saa;
    public static final boolean sam;
    public static final int sah;
    public static final boolean smd;
    private final m<Boolean> sag;
    private final m<Integer> sau;
    private final m<Integer> saz;
    private final m<Integer> sac;
    private final m<Double> saa;
    public static final boolean sam;
    public static final int sah;
    public static final boolean smd;
    
    public Time() {
        super();
        this.sau = (m<Integer>)new x("Radius", this, 1, 0, Time.mc.displayWidth);
        this.saz = (m<Integer>)new x("x", this, 1, 0, (int)Math.round(Time.mc.displayWidth * 1.2));
        this.sac = (m<Integer>)new x("y", this, 1, 0, (int)Math.round(Time.mc.displayHeight * 1.2));
        this.saa = (m<Double>)new s("Circle Accuracy", this, 1.0, 0.5, 2.0);
    }
    
    private static LocalTime x(final boolean b) {
        if (b) {
            final int n;
            return LocalTime.of(n, (int)(60L * (Time.mc.world.getWorldTime() % 24000L % 1000L) / 1000L));
        }
        return LocalTime.now();
    }
    
    public void a() {
        if (!this.lo()) {
            return;
        }
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GL11.glDisable(3553);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPushMatrix();
        GL11.glTranslated((double)this.saz.yv(), (double)this.sac.yv(), 0.0);
        GL11.glLineWidth(1.0f);
        final LocalTime x = x(this.sag.yv());
        final double n = x.getSecond() + System.currentTimeMillis() % 1000L / 1000.0;
        final double n2 = x.getMinute() + n / 60.0;
        final double n3 = x.getHour() + n2 / 60.0;
        if (!this.sag.yv()) {
            d(1.0f, n / 60.0 * 100.0, this.sau.yv());
        }
        d(4.0f, n2 / 60.0 * 100.0, this.sau.yv() * 0.75);
        d(6.0f, n3 % 12.0 / 12.0 * 100.0, this.sau.yv() * 0.5);
        GL11.glEnable(2848);
        GL11.glLineWidth(1.0f);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
        d(this.sau.yv(), false, this.saa.yv());
        GL11.glDisable(2848);
        GL11.glPopMatrix();
        GL11.glEnable(3553);
    }
    
    private static void d(final float n, final double n2, final double n3) {
        final double x = x(n2);
        GL11.glLineWidth(n);
        GL11.glBegin(1);
        GL11.glVertex2d(0.0, 0.0);
        GL11.glVertex2d(-n3 * Math.sin(x), n3 * Math.cos(x));
        GL11.glEnd();
    }
    
    private static double x(final double n) {
        return n / 100.0 * 3.141592653589793 * 2.0 + 3.141592653589793;
    }
    
    private static void d(final double p0, final boolean p1, final double p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: ldc2_w          6.283185307179586
        //     9: dstore          5
        //    11: ldc2_w          15.0
        //    14: dload_0        
        //    15: dload           5
        //    17: dmul           
        //    18: ldc2_w          15.0
        //    21: ddiv           
        //    22: invokestatic    java/lang/Math.max:(DD)D
        //    25: d2i            
        //    26: istore          7
        //    28: iload           7
        //    30: i2d            
        //    31: dload_3        
        //    32: dmul           
        //    33: d2i            
        //    34: istore          7
        //    36: iload_2        
        //    37: ifeq            42
        //    40: iconst_4       
        //    41: nop            
        //    42: iconst_2       
        //    43: invokestatic    org/lwjgl/opengl/GL11.glBegin:(I)V
        //    46: iconst_0       
        //    47: istore          8
        //    49: iload           8
        //    51: iload           7
        //    53: if_icmpgt       102
        //    56: iload_2        
        //    57: ifeq            65
        //    60: dconst_0       
        //    61: dconst_0       
        //    62: invokestatic    org/lwjgl/opengl/GL11.glVertex2d:(DD)V
        //    65: dload_0        
        //    66: iload           8
        //    68: i2d            
        //    69: dload           5
        //    71: dmul           
        //    72: iload           7
        //    74: i2d            
        //    75: ddiv           
        //    76: invokestatic    java/lang/Math.cos:(D)D
        //    79: dmul           
        //    80: dload_0        
        //    81: iload           8
        //    83: i2d            
        //    84: dload           5
        //    86: dmul           
        //    87: iload           7
        //    89: i2d            
        //    90: ddiv           
        //    91: invokestatic    java/lang/Math.sin:(D)D
        //    94: dmul           
        //    95: invokestatic    org/lwjgl/opengl/GL11.glVertex2d:(DD)V
        //    98: iinc            8, 1
        //   101: nop            
        //   102: invokestatic    org/lwjgl/opengl/GL11.glEnd:()V
        //   105: return         
        //    StackMapTable: 00 07 FF 00 04 00 0A 03 01 03 00 00 00 00 00 00 01 00 00 FF 00 00 00 0A 03 01 03 00 00 00 00 00 01 01 00 00 FF 00 24 00 09 03 01 03 03 01 00 01 01 01 00 00 40 01 FF 00 05 00 09 03 01 03 03 01 01 01 01 01 00 00 0F 24
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static void k(final double p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: nop            
        //     7: ldc2_w          6.283185307179586
        //    10: dstore_2       
        //    11: ldc2_w          15.0
        //    14: dload_0        
        //    15: dload_2        
        //    16: dmul           
        //    17: ldc2_w          15.0
        //    20: ddiv           
        //    21: invokestatic    java/lang/Math.max:(DD)D
        //    24: d2i            
        //    25: istore          4
        //    27: iload           4
        //    29: iconst_2       
        //    30: imul           
        //    31: istore          4
        //    33: iconst_4       
        //    34: invokestatic    org/lwjgl/opengl/GL11.glBegin:(I)V
        //    StackMapTable: 00 03 FF 00 04 00 08 03 00 00 00 00 00 00 01 00 00 00 FF 00 1F 00 07 03 03 01 01 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Time() {
        super();
        this.sau = (m<Integer>)new x("Radius", this, 1, 0, Time.mc.displayWidth);
        this.saz = (m<Integer>)new x("x", this, 1, 0, (int)Math.round(Time.mc.displayWidth * 1.2));
        this.sac = (m<Integer>)new x("y", this, 1, 0, (int)Math.round(Time.mc.displayHeight * 1.2));
        this.saa = (m<Double>)new s("Circle Accuracy", this, 1.0, 0.5, 2.0);
    }
    
    private static LocalTime x(final boolean b) {
        if (b) {
            final int n;
            return LocalTime.of(n, (int)(60L * (Time.mc.world.getWorldTime() % 24000L % 1000L) / 1000L));
        }
        return LocalTime.now();
    }
    
    public void a() {
        if (!this.lo()) {
            return;
        }
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GL11.glDisable(3553);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPushMatrix();
        GL11.glTranslated((double)this.saz.yv(), (double)this.sac.yv(), 0.0);
        GL11.glLineWidth(1.0f);
        final LocalTime x = x(this.sag.yv());
        final double n = x.getSecond() + System.currentTimeMillis() % 1000L / 1000.0;
        final double n2 = x.getMinute() + n / 60.0;
        final double n3 = x.getHour() + n2 / 60.0;
        if (!this.sag.yv()) {
            d(1.0f, n / 60.0 * 100.0, this.sau.yv());
        }
        d(4.0f, n2 / 60.0 * 100.0, this.sau.yv() * 0.75);
        d(6.0f, n3 % 12.0 / 12.0 * 100.0, this.sau.yv() * 0.5);
        GL11.glEnable(2848);
        GL11.glLineWidth(1.0f);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
        d(this.sau.yv(), false, this.saa.yv());
        GL11.glDisable(2848);
        GL11.glPopMatrix();
        GL11.glEnable(3553);
    }
    
    private static void d(final float n, final double n2, final double n3) {
        final double x = x(n2);
        GL11.glLineWidth(n);
        GL11.glBegin(1);
        GL11.glVertex2d(0.0, 0.0);
        GL11.glVertex2d(-n3 * Math.sin(x), n3 * Math.cos(x));
        GL11.glEnd();
    }
    
    private static double x(final double n) {
        return n / 100.0 * 3.141592653589793 * 2.0 + 3.141592653589793;
    }
    
    private static void d(final double p0, final boolean p1, final double p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: ldc2_w          6.283185307179586
        //     9: dstore          5
        //    11: ldc2_w          15.0
        //    14: dload_0        
        //    15: dload           5
        //    17: dmul           
        //    18: ldc2_w          15.0
        //    21: ddiv           
        //    22: invokestatic    java/lang/Math.max:(DD)D
        //    25: d2i            
        //    26: istore          7
        //    28: iload           7
        //    30: i2d            
        //    31: dload_3        
        //    32: dmul           
        //    33: d2i            
        //    34: istore          7
        //    36: iload_2        
        //    37: ifeq            42
        //    40: iconst_4       
        //    41: nop            
        //    42: iconst_2       
        //    43: invokestatic    org/lwjgl/opengl/GL11.glBegin:(I)V
        //    46: iconst_0       
        //    47: istore          8
        //    49: iload           8
        //    51: iload           7
        //    53: if_icmpgt       102
        //    56: iload_2        
        //    57: ifeq            65
        //    60: dconst_0       
        //    61: dconst_0       
        //    62: invokestatic    org/lwjgl/opengl/GL11.glVertex2d:(DD)V
        //    65: dload_0        
        //    66: iload           8
        //    68: i2d            
        //    69: dload           5
        //    71: dmul           
        //    72: iload           7
        //    74: i2d            
        //    75: ddiv           
        //    76: invokestatic    java/lang/Math.cos:(D)D
        //    79: dmul           
        //    80: dload_0        
        //    81: iload           8
        //    83: i2d            
        //    84: dload           5
        //    86: dmul           
        //    87: iload           7
        //    89: i2d            
        //    90: ddiv           
        //    91: invokestatic    java/lang/Math.sin:(D)D
        //    94: dmul           
        //    95: invokestatic    org/lwjgl/opengl/GL11.glVertex2d:(DD)V
        //    98: iinc            8, 1
        //   101: nop            
        //   102: invokestatic    org/lwjgl/opengl/GL11.glEnd:()V
        //   105: return         
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static void k(final double n) {
        final double n2 = 6.283185307179586;
        final int n3 = (int)Math.max(15.0, n * n2 / 15.0) * 2;
        GL11.glBegin(4);
        int n4 = 0;
        if (n4 <= n3) {
            GL11.glVertex2d(0.0, 0.0);
            GL11.glVertex2d(n * Math.cos(n4 * n2 / n3), n * Math.sin(n4 * n2 / n3));
            ++n4;
        }
        GL11.glEnd();
    }
}
