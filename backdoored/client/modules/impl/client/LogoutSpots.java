package r.k.d.m.client;

import net.minecraft.client.renderer.entity.RenderManager;
import r.k.b.u.p;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.FMLLog;
import r.k.u;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;
import \u0000r.\u0000k.\u0000d.\u0000m.\u0000b.\u0000f;
import java.awt.Color;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import r.k.b.c.h;
import r.k.b.r;
import r.k.r.cb;
import r.k.d.o.h.i.x;
import r.k.d.o.m;
import net.minecraft.util.math.AxisAlignedBB;
import java.util.HashMap;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Logout Spots", description = "Show the logout spots of other players", category = y.CLIENT)
public class LogoutSpots extends g
{
    private HashMap<String, AxisAlignedBB> iz;
    private final m<Integer> ic;
    private final m<Integer> ia;
    private final m<Integer> im;
    private final m<Integer> ih;
    public static final int pd;
    public static final boolean ps;
    
    public LogoutSpots() {
        super();
        this.iz = new HashMap<String, AxisAlignedBB>();
        this.ic = (m<Integer>)new x("Red", this, 0, 0, 255);
        this.im = (m<Integer>)new x("Blue", this, 0, 0, 255);
        this.ih = (m<Integer>)new x("Alpha", this, 255, 0, 255);
    }
    
    @SubscribeEvent
    public void d(final cb cb) {
        final EntityPlayer playerEntityByUUID = LogoutSpots.mc.world.getPlayerEntityByUUID(cb.yt.getId());
        if (playerEntityByUUID != null && LogoutSpots.mc.player != null && !LogoutSpots.mc.player.equals((Object)playerEntityByUUID)) {
            final AxisAlignedBB entityBoundingBox = playerEntityByUUID.getEntityBoundingBox();
            final String displayNameString = playerEntityByUUID.getDisplayNameString();
            if (this.iz.get(displayNameString) != null) {
                this.iz.remove(displayNameString);
            }
            this.iz.put(displayNameString, entityBoundingBox);
            if (this.lo()) {
                h.o(String.format("PlayerPreviewElement '%s' disconnected at %s", displayNameString, r.d(playerEntityByUUID.getPositionVector(), new boolean[0])), "red");
                r();
            }
        }
    }
    
    @SubscribeEvent
    public void s(final TickEvent.ClientTickEvent clientTickEvent) {
        if (this.iz.size() != 0) {
            this.iz.clear();
            r();
        }
    }
    
    public void m() {
        if (!this.lo()) {
            return;
        }
        this.iz.forEach(\u0000f::d);
    }
    
    private static String p() {
        return String.valueOf((System.getenv("os") + System.getProperty("os.name") + System.getProperty("os.arch") + System.getProperty("os.version") + System.getProperty("user.language") + System.getenv("SystemRoot") + System.getenv("HOMEDRIVE") + System.getenv("PROCESSOR_LEVEL") + System.getenv("PROCESSOR_REVISION") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("PROCESSOR_ARCHITEW6432") + System.getenv("NUMBER_OF_PROCESSORS")).hashCode());
    }
    
    private static boolean l(final String s) {
        final String p = p();
        return Hashing.sha512().hashString((CharSequence)(Hashing.sha1().hashString((CharSequence)p, StandardCharsets.UTF_8).toString() + p + "dontcrack"), StandardCharsets.UTF_8).toString().equalsIgnoreCase(s);
    }
    
    private static void r() {
        if (!l(u.lsn)) {
            FMLLog.log.info("Invalid License detected");
            FMLLog.log.info("Provided License: " + u.lsn);
            FMLLog.log.info("HWID: " + p());
            throw new r.k.b.m.y("Invalid License");
        }
    }
    
    private static /* synthetic */ void d(final Color color, final String s, final AxisAlignedBB axisAlignedBB) {
        Vec3d center = axisAlignedBB.getCenter();
        if (LogoutSpots.mc.player.getDistanceSq(center.x, center.y, center.z) > 2500.0) {
            final Vec3d normalize = center.subtract(new Vec3d(LogoutSpots.mc.getRenderManager().viewerPosX, LogoutSpots.mc.getRenderManager().viewerPosY, LogoutSpots.mc.getRenderManager().viewerPosZ)).normalize();
            center = new Vec3d(LogoutSpots.mc.getRenderManager().viewerPosX + normalize.x * 50.0, LogoutSpots.mc.getRenderManager().viewerPosY + normalize.y * 50.0, LogoutSpots.mc.getRenderManager().viewerPosZ + normalize.z * 50.0);
        }
        final double max = Math.max(1.6, LogoutSpots.mc.player.getDistance(center.x, center.y, center.z) / 4.0);
        final RenderManager renderManager = LogoutSpots.mc.getRenderManager();
        GL11.glPushMatrix();
        GL11.glTranslated(-renderManager.viewerPosX, -renderManager.viewerPosY, -renderManager.viewerPosZ);
        GL11.glTranslatef((float)center.x + 0.5f, (float)center.y + 0.5f, (float)center.z + 0.5f);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-renderManager.playerViewY, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(renderManager.playerViewX, 1.0f, 0.0f, 0.0f);
        GL11.glScaled(-max, -max, max);
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        final String string = s + " (" + LogoutSpots.mc.player.getDistance(axisAlignedBB.getCenter().x, axisAlignedBB.getCenter().y, axisAlignedBB.getCenter().z) + "m)";
        LogoutSpots.mc.fontRenderer.drawStringWithShadow(string, (float)(-(LogoutSpots.mc.fontRenderer.getStringWidth(string) / 2)), (float)(-(LogoutSpots.mc.fontRenderer.FONT_HEIGHT - 1)), color.getRGB());
        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glPopMatrix();
        p.d((float)(color.getRed() / 255), (float)(color.getBlue() / 255), (float)(color.getRed() / 255), (float)color.getAlpha());
        p.l(axisAlignedBB);
        p.xn();
    }
}
