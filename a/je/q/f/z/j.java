package a.je.q.f.z;

import a.je.q.f.z.d.u;
import java.awt.TrayIcon;
import net.minecraft.util.EnumFacing$AxisDirection;
import java.nio.file.StandardOpenOption;
import net.minecraft.util.SoundCategory;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.world.World;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.world.GameType;
import net.minecraft.util.MovementInput;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.text.ChatType;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.block.BlockSkull;
import com.mojang.authlib.Agent;
import java.net.Proxy;
import net.minecraft.network.play.client.CPacketEntityAction$Action;
import net.minecraft.util.EnumFacing$Axis;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemEmptyMap;
import net.minecraft.potion.Potion;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.GlStateManager$DestFactor;
import net.minecraft.client.renderer.GlStateManager$SourceFactor;
import net.minecraft.entity.item.EntityItem;
import java.time.Instant;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.play.client.CPacketUseEntity$Action;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.client.event.RenderBlockOverlayEvent$OverlayType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult$Type;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.event.ClickEvent$Action;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event$Result;
import net.minecraft.network.play.server.SPacketPlayerListItem$Action;
import java.awt.Color;
import net.minecraft.client.util.ITooltipFlag$TooltipFlags;
import java.awt.Dimension;
import org.apache.logging.log4j.Logger;
import net.minecraft.util.EnumHand;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import java.io.PrintStream;
import java.nio.charset.Charset;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.network.play.client.CPacketPlayerDigging$Action;
import net.minecraft.client.network.NetHandlerPlayClient;
import java.util.List;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.NonNullList;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.tileentity.TileEntitySign;

public class j
{
    public static r \ud83e\udd14;
    
    public static ITextComponent[] d(final TileEntitySign tileEntitySign) {
        return j.\ud83e\udd14.d(tileEntitySign);
    }
    
    public static int s(final GuiButton guiButton) {
        return j.\ud83e\udd14.s(guiButton);
    }
    
    public static Block l() {
        return j.\ud83e\udd14.l();
    }
    
    public static void y(final TileEntitySign tileEntitySign, final int n) {
        j.\ud83e\udd14.y(tileEntitySign, n);
    }
    
    public static TileEntityRendererDispatcher x() {
        return j.\ud83e\udd14.x();
    }
    
    public static EventBus k() {
        return j.\ud83e\udd14.k();
    }
    
    public static TickEvent$Phase q(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        return j.\ud83e\udd14.q(tickEvent$ClientTickEvent);
    }
    
    public static TickEvent$Phase v() {
        return j.\ud83e\udd14.v();
    }
    
    public static FontRenderer j(final Minecraft minecraft) {
        return j.\ud83e\udd14.j(minecraft);
    }
    
    public static int e(final FontRenderer fontRenderer) {
        return j.\ud83e\udd14.e(fontRenderer);
    }
    
    public static EntityPlayerSP o(final Minecraft minecraft) {
        return j.\ud83e\udd14.o(minecraft);
    }
    
    public static InventoryPlayer t(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.t(entityPlayerSP);
    }
    
    public static NonNullList n(final InventoryPlayer inventoryPlayer) {
        return j.\ud83e\udd14.n(inventoryPlayer);
    }
    
    public static WorldClient i(final Minecraft minecraft) {
        return j.\ud83e\udd14.i(minecraft);
    }
    
    public static List p(final WorldClient worldClient) {
        return j.\ud83e\udd14.p(worldClient);
    }
    
    public static void r(final InventoryPlayer inventoryPlayer, final int n) {
        j.\ud83e\udd14.r(inventoryPlayer, n);
    }
    
    public static NetHandlerPlayClient f(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.f(entityPlayerSP);
    }
    
    public static CPacketPlayerDigging$Action b() {
        return j.\ud83e\udd14.b();
    }
    
    public static EnumFacing w() {
        return j.\ud83e\udd14.w();
    }
    
    public static CPacketPlayerDigging$Action g() {
        return j.\ud83e\udd14.g();
    }
    
    public static int u(final InventoryPlayer inventoryPlayer) {
        return j.\ud83e\udd14.u(inventoryPlayer);
    }
    
    public static RayTraceResult z(final Minecraft minecraft) {
        return j.\ud83e\udd14.z(minecraft);
    }
    
    public static EnumFacing c(final RayTraceResult rayTraceResult) {
        return j.\ud83e\udd14.c(rayTraceResult);
    }
    
    public static Charset a() {
        return j.\ud83e\udd14.a();
    }
    
    public static PrintStream m() {
        return j.\ud83e\udd14.m();
    }
    
    public static boolean h(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.h(entityPlayerSP);
    }
    
    public static List sd(final WorldClient worldClient) {
        return j.\ud83e\udd14.sd(worldClient);
    }
    
    public static PlayerControllerMP ss(final Minecraft minecraft) {
        return j.\ud83e\udd14.ss(minecraft);
    }
    
    public static EnumHand sl() {
        return j.\ud83e\udd14.sl();
    }
    
    public static Charset sy() {
        return j.\ud83e\udd14.sy();
    }
    
    public static Logger sx() {
        return j.\ud83e\udd14.sx();
    }
    
    public static int sk(final Dimension dimension) {
        return j.\ud83e\udd14.sk(dimension);
    }
    
    public static int sq(final Dimension dimension) {
        return j.\ud83e\udd14.sq(dimension);
    }
    
    public static ITooltipFlag$TooltipFlags sv() {
        return j.\ud83e\udd14.sv();
    }
    
    public static int sj(final Minecraft minecraft) {
        return j.\ud83e\udd14.sj(minecraft);
    }
    
    public static int se(final Minecraft minecraft) {
        return j.\ud83e\udd14.se(minecraft);
    }
    
    public static Color so() {
        return j.\ud83e\udd14.so();
    }
    
    public static Color st() {
        return j.\ud83e\udd14.st();
    }
    
    public static double sn(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.sn(entityPlayerSP);
    }
    
    public static double si(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.si(entityPlayerSP);
    }
    
    public static double sp(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.sp(entityPlayerSP);
    }
    
    public static Color sr() {
        return j.\ud83e\udd14.sr();
    }
    
    public static SPacketPlayerListItem$Action sf() {
        return j.\ud83e\udd14.sf();
    }
    
    public static Event$Result sb() {
        return j.\ud83e\udd14.sb();
    }
    
    public static List sw(final WorldClient worldClient) {
        return j.\ud83e\udd14.sw(worldClient);
    }
    
    public static Block sg() {
        return j.\ud83e\udd14.sg();
    }
    
    public static double su(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.su(entityPlayer);
    }
    
    public static double sz(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.sz(entityPlayer);
    }
    
    public static double sc(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.sc(entityPlayer);
    }
    
    public static void sa(final GuiButton guiButton, final boolean b) {
        j.\ud83e\udd14.sa(guiButton, b);
    }
    
    public static void sm(final EntityPlayerSP entityPlayerSP, final boolean b) {
        j.\ud83e\udd14.sm(entityPlayerSP, b);
    }
    
    public static boolean sh(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.sh(entityPlayerSP);
    }
    
    public static Block ld() {
        return j.\ud83e\udd14.ld();
    }
    
    public static int ls(final Framebuffer framebuffer) {
        return j.\ud83e\udd14.ls(framebuffer);
    }
    
    public static int ll(final Framebuffer framebuffer) {
        return j.\ud83e\udd14.ll(framebuffer);
    }
    
    public static int ly(final Framebuffer framebuffer) {
        return j.\ud83e\udd14.ly(framebuffer);
    }
    
    public static Item lx() {
        return j.\ud83e\udd14.lx();
    }
    
    public static ClickType lk() {
        return j.\ud83e\udd14.lk();
    }
    
    public static ClickType lq() {
        return j.\ud83e\udd14.lq();
    }
    
    public static Container lv(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.lv(entityPlayerSP);
    }
    
    public static TextureManager lj(final Minecraft minecraft) {
        return j.\ud83e\udd14.lj(minecraft);
    }
    
    public static Capability le() {
        return j.\ud83e\udd14.le();
    }
    
    public static int[] lo() {
        return j.\ud83e\udd14.lo();
    }
    
    public static Block lt() {
        return j.\ud83e\udd14.lt();
    }
    
    public static Block ln() {
        return j.\ud83e\udd14.ln();
    }
    
    public static Block li() {
        return j.\ud83e\udd14.li();
    }
    
    public static Block lp() {
        return j.\ud83e\udd14.lp();
    }
    
    public static Block lr() {
        return j.\ud83e\udd14.lr();
    }
    
    public static Block lf() {
        return j.\ud83e\udd14.lf();
    }
    
    public static Block lb() {
        return j.\ud83e\udd14.lb();
    }
    
    public static Block lw() {
        return j.\ud83e\udd14.lw();
    }
    
    public static Block lg() {
        return j.\ud83e\udd14.lg();
    }
    
    public static Block lu() {
        return j.\ud83e\udd14.lu();
    }
    
    public static Block lz() {
        return j.\ud83e\udd14.lz();
    }
    
    public static Block lc() {
        return j.\ud83e\udd14.lc();
    }
    
    public static Block la() {
        return j.\ud83e\udd14.la();
    }
    
    public static Block lm() {
        return j.\ud83e\udd14.lm();
    }
    
    public static Block lh() {
        return j.\ud83e\udd14.lh();
    }
    
    public static Block yd() {
        return j.\ud83e\udd14.yd();
    }
    
    public static int ys(final Framebuffer framebuffer) {
        return j.\ud83e\udd14.ys(framebuffer);
    }
    
    public static int yl(final Framebuffer framebuffer) {
        return j.\ud83e\udd14.yl(framebuffer);
    }
    
    public static ClickEvent$Action yy() {
        return j.\ud83e\udd14.yy();
    }
    
    public static Entity yx(final RayTraceResult rayTraceResult) {
        return j.\ud83e\udd14.yx(rayTraceResult);
    }
    
    public static BlockChest yk() {
        return j.\ud83e\udd14.yk();
    }
    
    public static Block yq() {
        return j.\ud83e\udd14.yq();
    }
    
    public static Block yv() {
        return j.\ud83e\udd14.yv();
    }
    
    public static LaunchClassLoader yj() {
        return j.\ud83e\udd14.yj();
    }
    
    public static Block ye() {
        return j.\ud83e\udd14.ye();
    }
    
    public static double yo(final AxisAlignedBB axisAlignedBB) {
        return j.\ud83e\udd14.yo(axisAlignedBB);
    }
    
    public static double yt(final AxisAlignedBB axisAlignedBB) {
        return j.\ud83e\udd14.yt(axisAlignedBB);
    }
    
    public static double yn(final AxisAlignedBB axisAlignedBB) {
        return j.\ud83e\udd14.yn(axisAlignedBB);
    }
    
    public static double yi(final AxisAlignedBB axisAlignedBB) {
        return j.\ud83e\udd14.yi(axisAlignedBB);
    }
    
    public static double yp(final AxisAlignedBB axisAlignedBB) {
        return j.\ud83e\udd14.yp(axisAlignedBB);
    }
    
    public static void yr(final Entity entity, final boolean b) {
        j.\ud83e\udd14.yr(entity, b);
    }
    
    public static void yf(final Entity entity, final double n) {
        j.\ud83e\udd14.yf(entity, n);
    }
    
    public static void yb(final Entity entity, final double n) {
        j.\ud83e\udd14.yb(entity, n);
    }
    
    public static void yw(final Entity entity, final double n) {
        j.\ud83e\udd14.yw(entity, n);
    }
    
    public static double yg(final Vec3d vec3d) {
        return j.\ud83e\udd14.yg(vec3d);
    }
    
    public static double yu(final Vec3d vec3d) {
        return j.\ud83e\udd14.yu(vec3d);
    }
    
    public static double yz(final Vec3d vec3d) {
        return j.\ud83e\udd14.yz(vec3d);
    }
    
    public static double yc(final RenderManager renderManager) {
        return j.\ud83e\udd14.yc(renderManager);
    }
    
    public static double ya(final RenderManager renderManager) {
        return j.\ud83e\udd14.ya(renderManager);
    }
    
    public static double ym(final RenderManager renderManager) {
        return j.\ud83e\udd14.ym(renderManager);
    }
    
    public static float yh(final RenderManager renderManager) {
        return j.\ud83e\udd14.yh(renderManager);
    }
    
    public static float xd(final RenderManager renderManager) {
        return j.\ud83e\udd14.xd(renderManager);
    }
    
    public static int xs(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.xs(entityPlayerSP);
    }
    
    public static RenderGameOverlayEvent$ElementType xl() {
        return j.\ud83e\udd14.xl();
    }
    
    public static GuiIngame xy(final Minecraft minecraft) {
        return j.\ud83e\udd14.xy(minecraft);
    }
    
    public static GameSettings xx(final Minecraft minecraft) {
        return j.\ud83e\udd14.xx(minecraft);
    }
    
    public static int xk(final GameSettings gameSettings) {
        return j.\ud83e\udd14.xk(gameSettings);
    }
    
    public static Entity xq(final Minecraft minecraft) {
        return j.\ud83e\udd14.xq(minecraft);
    }
    
    public static RayTraceResult$Type xv(final RayTraceResult rayTraceResult) {
        return j.\ud83e\udd14.xv(rayTraceResult);
    }
    
    public static RayTraceResult$Type xj() {
        return j.\ud83e\udd14.xj();
    }
    
    public static boolean xe(final GameSettings gameSettings) {
        return j.\ud83e\udd14.xe(gameSettings);
    }
    
    public static float xo(final Entity entity) {
        return j.\ud83e\udd14.xo(entity);
    }
    
    public static float xt(final Entity entity) {
        return j.\ud83e\udd14.xt(entity);
    }
    
    public static float xn(final Entity entity) {
        return j.\ud83e\udd14.xn(entity);
    }
    
    public static float xi(final Entity entity) {
        return j.\ud83e\udd14.xi(entity);
    }
    
    public static SoundEvent xp() {
        return j.\ud83e\udd14.xp();
    }
    
    public static SoundEvent xr() {
        return j.\ud83e\udd14.xr();
    }
    
    public static SoundEvent xf() {
        return j.\ud83e\udd14.xf();
    }
    
    public static SoundEvent xb() {
        return j.\ud83e\udd14.xb();
    }
    
    public static RenderBlockOverlayEvent$OverlayType xw() {
        return j.\ud83e\udd14.xw();
    }
    
    public static RenderBlockOverlayEvent$OverlayType xg() {
        return j.\ud83e\udd14.xg();
    }
    
    public static RenderBlockOverlayEvent$OverlayType xu() {
        return j.\ud83e\udd14.xu();
    }
    
    public static int xz(final ChunkPos chunkPos) {
        return j.\ud83e\udd14.xz(chunkPos);
    }
    
    public static int xc(final ChunkPos chunkPos) {
        return j.\ud83e\udd14.xc(chunkPos);
    }
    
    public static int xa(final Chunk chunk) {
        return j.\ud83e\udd14.xa(chunk);
    }
    
    public static int xm(final Chunk chunk) {
        return j.\ud83e\udd14.xm(chunk);
    }
    
    public static String xh(final ServerData serverData) {
        return j.\ud83e\udd14.xh(serverData);
    }
    
    public static CPacketUseEntity$Action kd() {
        return j.\ud83e\udd14.kd();
    }
    
    public static int ks(final Container container) {
        return j.\ud83e\udd14.ks(container);
    }
    
    public static ClickType kl() {
        return j.\ud83e\udd14.kl();
    }
    
    public static GuiScreen ky(final Minecraft minecraft) {
        return j.\ud83e\udd14.ky(minecraft);
    }
    
    public static EnumHand kx() {
        return j.\ud83e\udd14.kx();
    }
    
    public static Item kk() {
        return j.\ud83e\udd14.kk();
    }
    
    public static Instant kq() {
        return j.\ud83e\udd14.kq();
    }
    
    public static float kv(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.kv(entityPlayerSP);
    }
    
    public static float kj(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.kj(entityPlayerSP);
    }
    
    public static float ke(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.ke(entityPlayerSP);
    }
    
    public static String ko(final ServerData serverData) {
        return j.\ud83e\udd14.ko(serverData);
    }
    
    public static boolean kt(final WorldClient worldClient) {
        return j.\ud83e\udd14.kt(worldClient);
    }
    
    public static int kn(final EntityItem entityItem) {
        return j.\ud83e\udd14.kn(entityItem);
    }
    
    public static RenderGameOverlayEvent$ElementType ki() {
        return j.\ud83e\udd14.ki();
    }
    
    public static GlStateManager$SourceFactor kp() {
        return j.\ud83e\udd14.kp();
    }
    
    public static GlStateManager$DestFactor kr() {
        return j.\ud83e\udd14.kr();
    }
    
    public static GlStateManager$SourceFactor kf() {
        return j.\ud83e\udd14.kf();
    }
    
    public static GlStateManager$DestFactor kb() {
        return j.\ud83e\udd14.kb();
    }
    
    public static Material kw() {
        return j.\ud83e\udd14.kw();
    }
    
    public static double kg(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.kg(entityPlayer);
    }
    
    public static double ku(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.ku(entityPlayer);
    }
    
    public static double kz(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.kz(entityPlayer);
    }
    
    public static RenderGameOverlayEvent$ElementType kc() {
        return j.\ud83e\udd14.kc();
    }
    
    public static float ka(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.ka(entityPlayerSP);
    }
    
    public static void km(final EntityPlayerSP entityPlayerSP, final float n) {
        j.\ud83e\udd14.km(entityPlayerSP, n);
    }
    
    public static void kh(final EntityPlayerSP entityPlayerSP, final float n) {
        j.\ud83e\udd14.kh(entityPlayerSP, n);
    }
    
    public static Container qd(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.qd(entityPlayerSP);
    }
    
    public static Item qs() {
        return j.\ud83e\udd14.qs();
    }
    
    public static Potion ql() {
        return j.\ud83e\udd14.ql();
    }
    
    public static ItemEmptyMap qy() {
        return j.\ud83e\udd14.qy();
    }
    
    public static ItemMap qx() {
        return j.\ud83e\udd14.qx();
    }
    
    public static ClickType qk() {
        return j.\ud83e\udd14.qk();
    }
    
    public static String qq(final GuiButton guiButton) {
        return j.\ud83e\udd14.qq(guiButton);
    }
    
    public static Color qv() {
        return j.\ud83e\udd14.qv();
    }
    
    public static Color qj() {
        return j.\ud83e\udd14.qj();
    }
    
    public static PlayerCapabilities qe(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.qe(entityPlayerSP);
    }
    
    public static boolean qo(final PlayerCapabilities playerCapabilities) {
        return j.\ud83e\udd14.qo(playerCapabilities);
    }
    
    public static void qt(final EntityPlayerSP entityPlayerSP, final double n) {
        j.\ud83e\udd14.qt(entityPlayerSP, n);
    }
    
    public static EnumFacing$Axis qn() {
        return j.\ud83e\udd14.qn();
    }
    
    public static EnumFacing$Axis qi() {
        return j.\ud83e\udd14.qi();
    }
    
    public static List qp(final Container container) {
        return j.\ud83e\udd14.qp(container);
    }
    
    public static ClickType qr() {
        return j.\ud83e\udd14.qr();
    }
    
    public static CPacketPlayerDigging$Action qf() {
        return j.\ud83e\udd14.qf();
    }
    
    public static CPacketEntityAction$Action qb() {
        return j.\ud83e\udd14.qb();
    }
    
    public static CPacketEntityAction$Action qw() {
        return j.\ud83e\udd14.qw();
    }
    
    public static void qg(final FontRenderer fontRenderer, final int n) {
        j.\ud83e\udd14.qg(fontRenderer, n);
    }
    
    public static Proxy qu() {
        return j.\ud83e\udd14.qu();
    }
    
    public static Agent qz() {
        return j.\ud83e\udd14.qz();
    }
    
    public static Block qc() {
        return j.\ud83e\udd14.qc();
    }
    
    public static BlockSkull qa() {
        return j.\ud83e\udd14.qa();
    }
    
    public static EnumFacing qm() {
        return j.\ud83e\udd14.qm();
    }
    
    public static EnumFacing qh() {
        return j.\ud83e\udd14.qh();
    }
    
    public static void vd(final PlayerCapabilities playerCapabilities, final boolean b) {
        j.\ud83e\udd14.vd(playerCapabilities, b);
    }
    
    public static CPacketEntityAction$Action vs() {
        return j.\ud83e\udd14.vs();
    }
    
    public static KeyBinding vl(final GameSettings gameSettings) {
        return j.\ud83e\udd14.vl(gameSettings);
    }
    
    public static double vy(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.vy(entityPlayerSP);
    }
    
    public static void vx(final EntityPlayerSP entityPlayerSP, final double n) {
        j.\ud83e\udd14.vx(entityPlayerSP, n);
    }
    
    public static double vk(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.vk(entityPlayerSP);
    }
    
    public static void vq(final EntityPlayerSP entityPlayerSP, final double n) {
        j.\ud83e\udd14.vq(entityPlayerSP, n);
    }
    
    public static KeyBinding vv(final GameSettings gameSettings) {
        return j.\ud83e\udd14.vv(gameSettings);
    }
    
    public static void vj(final EntityPlayerSP entityPlayerSP, final float n) {
        j.\ud83e\udd14.vj(entityPlayerSP, n);
    }
    
    public static KeyBinding ve(final GameSettings gameSettings) {
        return j.\ud83e\udd14.ve(gameSettings);
    }
    
    public static double vo(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.vo(entityPlayerSP);
    }
    
    public static KeyBinding vt(final GameSettings gameSettings) {
        return j.\ud83e\udd14.vt(gameSettings);
    }
    
    public static KeyBinding vn(final GameSettings gameSettings) {
        return j.\ud83e\udd14.vn(gameSettings);
    }
    
    public static KeyBinding vi(final GameSettings gameSettings) {
        return j.\ud83e\udd14.vi(gameSettings);
    }
    
    public static Color vp() {
        return j.\ud83e\udd14.vp();
    }
    
    public static Color vr() {
        return j.\ud83e\udd14.vr();
    }
    
    public static VertexFormat vf() {
        return j.\ud83e\udd14.vf();
    }
    
    public static EntityRenderer vb(final Minecraft minecraft) {
        return j.\ud83e\udd14.vb(minecraft);
    }
    
    public static void vw(final Entity entity, final double n) {
        j.\ud83e\udd14.vw(entity, n);
    }
    
    public static void vg(final Entity entity, final double n) {
        j.\ud83e\udd14.vg(entity, n);
    }
    
    public static void vu(final Entity entity, final double n) {
        j.\ud83e\udd14.vu(entity, n);
    }
    
    public static double vz(final Entity entity) {
        return j.\ud83e\udd14.vz(entity);
    }
    
    public static double vc(final Entity entity) {
        return j.\ud83e\udd14.vc(entity);
    }
    
    public static double va(final Entity entity) {
        return j.\ud83e\udd14.va(entity);
    }
    
    public static double vm(final Entity entity) {
        return j.\ud83e\udd14.vm(entity);
    }
    
    public static double vh(final Entity entity) {
        return j.\ud83e\udd14.vh(entity);
    }
    
    public static KeyBinding jd(final GameSettings gameSettings) {
        return j.\ud83e\udd14.jd(gameSettings);
    }
    
    public static ItemStack js() {
        return j.\ud83e\udd14.js();
    }
    
    public static ChatType jl() {
        return j.\ud83e\udd14.jl();
    }
    
    public static ChatType jy() {
        return j.\ud83e\udd14.jy();
    }
    
    public static float jx(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.jx(entityPlayerSP);
    }
    
    public static TileEntityChest jk(final TileEntityChest tileEntityChest) {
        return j.\ud83e\udd14.jk(tileEntityChest);
    }
    
    public static TileEntityChest jq(final TileEntityChest tileEntityChest) {
        return j.\ud83e\udd14.jq(tileEntityChest);
    }
    
    public static TileEntityChest jv(final TileEntityChest tileEntityChest) {
        return j.\ud83e\udd14.jv(tileEntityChest);
    }
    
    public static TileEntityChest jj(final TileEntityChest tileEntityChest) {
        return j.\ud83e\udd14.jj(tileEntityChest);
    }
    
    public static Potion je() {
        return j.\ud83e\udd14.je();
    }
    
    public static Item jo() {
        return j.\ud83e\udd14.jo();
    }
    
    public static MovementInput jt(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.jt(entityPlayerSP);
    }
    
    public static float jn(final MovementInput movementInput) {
        return j.\ud83e\udd14.jn(movementInput);
    }
    
    public static float ji(final MovementInput movementInput) {
        return j.\ud83e\udd14.ji(movementInput);
    }
    
    public static double jp(final AxisAlignedBB axisAlignedBB) {
        return j.\ud83e\udd14.jp(axisAlignedBB);
    }
    
    public static VertexFormat jr() {
        return j.\ud83e\udd14.jr();
    }
    
    public static VertexFormat jf() {
        return j.\ud83e\udd14.jf();
    }
    
    public static GameType jb() {
        return j.\ud83e\udd14.jb();
    }
    
    public static GameType jw() {
        return j.\ud83e\udd14.jw();
    }
    
    public static Color jg() {
        return j.\ud83e\udd14.jg();
    }
    
    public static Item ju() {
        return j.\ud83e\udd14.ju();
    }
    
    public static Item jz() {
        return j.\ud83e\udd14.jz();
    }
    
    public static boolean jc(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.jc(entityPlayer);
    }
    
    public static Item ja() {
        return j.\ud83e\udd14.ja();
    }
    
    public static double jm(final EntityEnderCrystal entityEnderCrystal) {
        return j.\ud83e\udd14.jm(entityEnderCrystal);
    }
    
    public static double jh(final EntityEnderCrystal entityEnderCrystal) {
        return j.\ud83e\udd14.jh(entityEnderCrystal);
    }
    
    public static double ed(final EntityEnderCrystal entityEnderCrystal) {
        return j.\ud83e\udd14.ed(entityEnderCrystal);
    }
    
    public static Block es() {
        return j.\ud83e\udd14.es();
    }
    
    public static World el(final Entity entity) {
        return j.\ud83e\udd14.el(entity);
    }
    
    public static IAttribute ey() {
        return j.\ud83e\udd14.ey();
    }
    
    public static SPacketPlayerListItem$Action ex() {
        return j.\ud83e\udd14.ex();
    }
    
    public static SoundCategory ek() {
        return j.\ud83e\udd14.ek();
    }
    
    public static SoundEvent eq() {
        return j.\ud83e\udd14.eq();
    }
    
    public static StandardOpenOption ev() {
        return j.\ud83e\udd14.ev();
    }
    
    public static EnumFacing$AxisDirection ej() {
        return j.\ud83e\udd14.ej();
    }
    
    public static RenderGameOverlayEvent$ElementType ee() {
        return j.\ud83e\udd14.ee();
    }
    
    public static RenderGameOverlayEvent$ElementType eo() {
        return j.\ud83e\udd14.eo();
    }
    
    public static RenderGameOverlayEvent$ElementType et() {
        return j.\ud83e\udd14.et();
    }
    
    public static RenderGameOverlayEvent$ElementType en() {
        return j.\ud83e\udd14.en();
    }
    
    public static RenderGameOverlayEvent$ElementType ei() {
        return j.\ud83e\udd14.ei();
    }
    
    public static RenderGameOverlayEvent$ElementType ep() {
        return j.\ud83e\udd14.ep();
    }
    
    public static RenderGameOverlayEvent$ElementType er() {
        return j.\ud83e\udd14.er();
    }
    
    public static RenderGameOverlayEvent$ElementType ef() {
        return j.\ud83e\udd14.ef();
    }
    
    public static RenderGameOverlayEvent$ElementType eb() {
        return j.\ud83e\udd14.eb();
    }
    
    public static RenderGameOverlayEvent$ElementType ew() {
        return j.\ud83e\udd14.ew();
    }
    
    public static RenderGameOverlayEvent$ElementType eg() {
        return j.\ud83e\udd14.eg();
    }
    
    public static RenderGameOverlayEvent$ElementType eu() {
        return j.\ud83e\udd14.eu();
    }
    
    public static RenderGameOverlayEvent$ElementType ez() {
        return j.\ud83e\udd14.ez();
    }
    
    public static RenderGameOverlayEvent$ElementType ec() {
        return j.\ud83e\udd14.ec();
    }
    
    public static RenderGameOverlayEvent$ElementType ea() {
        return j.\ud83e\udd14.ea();
    }
    
    public static RenderGameOverlayEvent$ElementType em() {
        return j.\ud83e\udd14.em();
    }
    
    public static RenderGameOverlayEvent$ElementType eh() {
        return j.\ud83e\udd14.eh();
    }
    
    public static boolean od(final GameSettings gameSettings) {
        return j.\ud83e\udd14.od(gameSettings);
    }
    
    public static void os(final EntityPlayerSP entityPlayerSP, final double n) {
        j.\ud83e\udd14.os(entityPlayerSP, n);
    }
    
    public static void ol(final EntityPlayerSP entityPlayerSP, final double n) {
        j.\ud83e\udd14.ol(entityPlayerSP, n);
    }
    
    public static ChatType oy() {
        return j.\ud83e\udd14.oy();
    }
    
    public static TrayIcon.MessageType ox() {
        return j.\ud83e\udd14.ox();
    }
    
    public static Event$Result ok() {
        return j.\ud83e\udd14.ok();
    }
    
    public static int oq() {
        return j.\ud83e\udd14.oq();
    }
    
    public static int ov() {
        return j.\ud83e\udd14.ov();
    }
    
    static {
        j.\ud83e\udd14 = (r)new u(j.class.getClassLoader(), "a/je/q/f/z/a.class", 27910).createClass("?").newInstance();
    }
}
