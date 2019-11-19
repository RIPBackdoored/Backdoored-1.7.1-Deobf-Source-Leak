package r.k.b;

import net.minecraft.init.Blocks;
import r.k.b.m.y;
import r.k.a;
import net.minecraftforge.fml.common.FMLLog;
import r.k.u;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import java.nio.FloatBuffer;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;
import net.minecraft.client.renderer.ActiveRenderInfo;
import r.k.b.u.h;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.Color;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.apache.commons.lang3.ArrayUtils;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;

public class r
{
    private static Minecraft mc;
    public static final Block[] block;
    private static final Random wo;
    public static final boolean wt;
    public static final int wn;
    public static final boolean wi;
    
    public r() {
        super();
    }
    
    public static boolean d(final Block objectToFind) {
        return ArrayUtils.contains(r.block, objectToFind);
    }
    
    public static String d(final Vec3d p0, final boolean... p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: aconst_null    
        //     5: areturn        
        //     6: nop            
        //     7: aload_1        
        //     8: arraylength    
        //     9: ifle            16
        //    12: aload_1        
        //    13: iconst_0       
        //    14: baload         
        //    15: nop            
        //    16: iconst_1       
        //    17: istore_2       
        //    18: new             Ljava/lang/StringBuilder;
        //    21: dup            
        //    22: invokespecial   java/lang/StringBuilder.<init>:()V
        //    25: astore_3       
        //    26: aload_3        
        //    27: bipush          40
        //    29: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    32: pop            
        //    33: aload_3        
        //    34: aload_0        
        //    35: getfield        net/minecraft/util/math/Vec3d.x:D
        //    38: invokestatic    java/lang/Math.floor:(D)D
        //    41: d2i            
        //    42: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    45: pop            
        //    46: aload_3        
        //    47: ldc             ", "
        //    49: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    52: pop            
        //    53: iload_2        
        //    54: ifeq            77
        //    57: aload_3        
        //    58: aload_0        
        //    59: getfield        net/minecraft/util/math/Vec3d.y:D
        //    62: invokestatic    java/lang/Math.floor:(D)D
        //    65: d2i            
        //    66: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    69: pop            
        //    70: aload_3        
        //    71: ldc             ", "
        //    73: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    76: pop            
        //    77: aload_3        
        //    78: aload_0        
        //    79: getfield        net/minecraft/util/math/Vec3d.z:D
        //    82: invokestatic    java/lang/Math.floor:(D)D
        //    85: d2i            
        //    86: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    89: pop            
        //    90: aload_3        
        //    91: ldc             ")"
        //    93: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    96: pop            
        //    97: aload_3        
        //    98: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   101: areturn        
        //    StackMapTable: 00 05 FF 00 04 00 07 07 00 23 07 00 25 00 00 00 00 01 00 00 01 FF 00 09 00 07 07 00 23 07 00 25 00 00 01 00 01 00 00 40 01 FF 00 3B 00 07 07 00 23 07 00 25 01 07 00 27 01 00 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static String s(final BlockPos blockPos) {
        return d(new Vec3d((Vec3i)blockPos), new boolean[0]);
    }
    
    public static void d(final String s, final int n, final int n2, final float n3) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n4 = 0;
        if (n4 < length) {
            String.valueOf(charArray[n4]);
            ++n4;
        }
    }
    
    public static Color d(final long n, final float n2) {
        final Color color = new Color((int)Long.parseLong(Integer.toHexString(Color.HSBtoRGB((System.nanoTime() + n) / 1.0E10f % 1.0f, 1.0f, 1.0f)), 16));
        return new Color(color.getRed() / 255.0f * n2, color.getGreen() / 255.0f * n2, color.getBlue() / 255.0f * n2, color.getAlpha() / 255.0f);
    }
    
    public static double d(final double n, final int n2) {
        final double pow = Math.pow(10.0, n2);
        return Math.round(n * pow) / pow;
    }
    
    public static void r(final String s) {
        final StringSelection stringSelection = new StringSelection(s);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
    }
    
    public static boolean s(final String s, final String s2) {
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(s));
            bufferedWriter.write(s2);
            bufferedWriter.close();
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static h d(final Vec3d vec3d) {
        final Entity renderViewEntity = r.mc.getRenderViewEntity();
        if (renderViewEntity == null) {
            return new h(0.0, 0.0, false);
        }
        final ActiveRenderInfo activeRenderInfo = new ActiveRenderInfo();
        final Vec3d positionEyes = r.mc.player.getPositionEyes(r.mc.getRenderPartialTicks());
        final Vec3d projectViewFromEntity = ActiveRenderInfo.projectViewFromEntity(renderViewEntity, (double)r.mc.getRenderPartialTicks());
        final Vector4f vector4f = new Vector4f((float)(positionEyes.x + projectViewFromEntity.x - (float)vec3d.x), (float)(positionEyes.y + projectViewFromEntity.y - (float)vec3d.y), (float)(positionEyes.z + projectViewFromEntity.z - (float)vec3d.z), 1.0f);
        final Matrix4f matrix4f = new Matrix4f();
        matrix4f.load((FloatBuffer)ObfuscationReflectionHelper.getPrivateValue((Class)ActiveRenderInfo.class, (Object)new ActiveRenderInfo(), new String[] { "MODELVIEW", "field_178812_b" }));
        final Matrix4f matrix4f2 = new Matrix4f();
        matrix4f2.load((FloatBuffer)ObfuscationReflectionHelper.getPrivateValue((Class)ActiveRenderInfo.class, (Object)new ActiveRenderInfo(), new String[] { "PROJECTION", "field_178813_c" }));
        d(vector4f, matrix4f);
        d(vector4f, matrix4f2);
        if (vector4f.w > 0.0f) {
            final Vector4f vector4f2 = vector4f;
            vector4f2.x *= -100000.0f;
            final Vector4f vector4f3 = vector4f;
            vector4f3.y *= -100000.0f;
        }
        final float n = 1.0f / vector4f.w;
        final Vector4f vector4f4 = vector4f;
        vector4f4.x *= n;
        final Vector4f vector4f5 = vector4f;
        vector4f5.y *= n;
        final ScaledResolution scaledResolution = new ScaledResolution(r.mc);
        final float n2 = scaledResolution.getScaledWidth() / 2.0f;
        final float n3 = scaledResolution.getScaledHeight() / 2.0f;
        vector4f.x = n2 + (0.5f * vector4f.x * scaledResolution.getScaledWidth() + 0.5f);
        vector4f.y = n3 - (0.5f * vector4f.y * scaledResolution.getScaledHeight() + 0.5f);
        boolean b = true;
        if (vector4f.x < 0.0f || vector4f.y < 0.0f || vector4f.x > scaledResolution.getScaledWidth() || vector4f.y > scaledResolution.getScaledHeight()) {
            b = false;
        }
        return new h(vector4f.x, vector4f.y, b);
    }
    
    private static void d(final Vector4f vector4f, final Matrix4f matrix4f) {
        final float x = vector4f.x;
        final float y = vector4f.y;
        final float z = vector4f.z;
        vector4f.x = x * matrix4f.m00 + y * matrix4f.m10 + z * matrix4f.m20 + matrix4f.m30;
        vector4f.y = x * matrix4f.m01 + y * matrix4f.m11 + z * matrix4f.m21 + matrix4f.m31;
        vector4f.z = x * matrix4f.m02 + y * matrix4f.m12 + z * matrix4f.m22 + matrix4f.m32;
        vector4f.w = x * matrix4f.m03 + y * matrix4f.m13 + z * matrix4f.m23 + matrix4f.m33;
    }
    
    public static float sf() {
        float rotationYaw = r.mc.player.rotationYaw;
        if (r.mc.player.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float n = 1.0f;
        if (r.mc.player.moveForward < 0.0f) {
            n = -0.5f;
        }
        if (r.mc.player.moveForward > 0.0f) {
            n = 0.5f;
        }
        if (r.mc.player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (r.mc.player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return rotationYaw * 0.017453292f;
    }
    
    private static String p() {
        return String.valueOf((System.getenv("os") + System.getProperty("os.name") + System.getProperty("os.arch") + System.getProperty("os.version") + System.getProperty("user.language") + System.getenv("SystemRoot") + System.getenv("HOMEDRIVE") + System.getenv("PROCESSOR_LEVEL") + System.getenv("PROCESSOR_REVISION") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("PROCESSOR_ARCHITEW6432") + System.getenv("NUMBER_OF_PROCESSORS")).hashCode());
    }
    
    private static boolean l(final String s) {
        final String s2;
        return Hashing.sha512().hashString((CharSequence)(s2 + p() + "dontcrack"), StandardCharsets.UTF_8).toString().equalsIgnoreCase(s);
    }
    
    private static void r() {
        if (r.wo.nextBoolean() && !l(u.lsn)) {
            FMLLog.log.info("Invalid License detected");
            FMLLog.log.info("Provided License: " + u.lsn);
            FMLLog.log.info("HWID: " + p());
            a.llp = true;
            throw new y("Invalid License");
        }
    }
    
    static {
        r.mc = r.k.h.mc;
        block = new Block[] { Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX };
        wo = new Random();
    }
}
