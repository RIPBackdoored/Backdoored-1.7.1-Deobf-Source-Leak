package a.je.q.f.z;

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

public interface r
{
    ITextComponent[] d(final TileEntitySign p0);
    
    int s(final GuiButton p0);
    
    Block l();
    
    void y(final TileEntitySign p0, final int p1);
    
    TileEntityRendererDispatcher x();
    
    EventBus k();
    
    TickEvent$Phase q(final TickEvent$ClientTickEvent p0);
    
    TickEvent$Phase v();
    
    FontRenderer j(final Minecraft p0);
    
    int e(final FontRenderer p0);
    
    EntityPlayerSP o(final Minecraft p0);
    
    InventoryPlayer t(final EntityPlayerSP p0);
    
    NonNullList n(final InventoryPlayer p0);
    
    WorldClient i(final Minecraft p0);
    
    List p(final WorldClient p0);
    
    void r(final InventoryPlayer p0, final int p1);
    
    NetHandlerPlayClient f(final EntityPlayerSP p0);
    
    CPacketPlayerDigging$Action b();
    
    EnumFacing w();
    
    CPacketPlayerDigging$Action g();
    
    int u(final InventoryPlayer p0);
    
    RayTraceResult z(final Minecraft p0);
    
    EnumFacing c(final RayTraceResult p0);
    
    Charset a();
    
    PrintStream m();
    
    boolean h(final EntityPlayerSP p0);
    
    List sd(final WorldClient p0);
    
    PlayerControllerMP ss(final Minecraft p0);
    
    EnumHand sl();
    
    Charset sy();
    
    Logger sx();
    
    int sk(final Dimension p0);
    
    int sq(final Dimension p0);
    
    ITooltipFlag$TooltipFlags sv();
    
    int sj(final Minecraft p0);
    
    int se(final Minecraft p0);
    
    Color so();
    
    Color st();
    
    double sn(final EntityPlayerSP p0);
    
    double si(final EntityPlayerSP p0);
    
    double sp(final EntityPlayerSP p0);
    
    Color sr();
    
    SPacketPlayerListItem$Action sf();
    
    Event$Result sb();
    
    List sw(final WorldClient p0);
    
    Block sg();
    
    double su(final EntityPlayer p0);
    
    double sz(final EntityPlayer p0);
    
    double sc(final EntityPlayer p0);
    
    void sa(final GuiButton p0, final boolean p1);
    
    void sm(final EntityPlayerSP p0, final boolean p1);
    
    boolean sh(final EntityPlayerSP p0);
    
    Block ld();
    
    int ls(final Framebuffer p0);
    
    int ll(final Framebuffer p0);
    
    int ly(final Framebuffer p0);
    
    Item lx();
    
    ClickType lk();
    
    ClickType lq();
    
    Container lv(final EntityPlayerSP p0);
    
    TextureManager lj(final Minecraft p0);
    
    Capability le();
    
    int[] lo();
    
    Block lt();
    
    Block ln();
    
    Block li();
    
    Block lp();
    
    Block lr();
    
    Block lf();
    
    Block lb();
    
    Block lw();
    
    Block lg();
    
    Block lu();
    
    Block lz();
    
    Block lc();
    
    Block la();
    
    Block lm();
    
    Block lh();
    
    Block yd();
    
    int ys(final Framebuffer p0);
    
    int yl(final Framebuffer p0);
    
    ClickEvent$Action yy();
    
    Entity yx(final RayTraceResult p0);
    
    BlockChest yk();
    
    Block yq();
    
    Block yv();
    
    LaunchClassLoader yj();
    
    Block ye();
    
    double yo(final AxisAlignedBB p0);
    
    double yt(final AxisAlignedBB p0);
    
    double yn(final AxisAlignedBB p0);
    
    double yi(final AxisAlignedBB p0);
    
    double yp(final AxisAlignedBB p0);
    
    void yr(final Entity p0, final boolean p1);
    
    void yf(final Entity p0, final double p1);
    
    void yb(final Entity p0, final double p1);
    
    void yw(final Entity p0, final double p1);
    
    double yg(final Vec3d p0);
    
    double yu(final Vec3d p0);
    
    double yz(final Vec3d p0);
    
    double yc(final RenderManager p0);
    
    double ya(final RenderManager p0);
    
    double ym(final RenderManager p0);
    
    float yh(final RenderManager p0);
    
    float xd(final RenderManager p0);
    
    int xs(final EntityPlayerSP p0);
    
    RenderGameOverlayEvent$ElementType xl();
    
    GuiIngame xy(final Minecraft p0);
    
    GameSettings xx(final Minecraft p0);
    
    int xk(final GameSettings p0);
    
    Entity xq(final Minecraft p0);
    
    RayTraceResult$Type xv(final RayTraceResult p0);
    
    RayTraceResult$Type xj();
    
    boolean xe(final GameSettings p0);
    
    float xo(final Entity p0);
    
    float xt(final Entity p0);
    
    float xn(final Entity p0);
    
    float xi(final Entity p0);
    
    SoundEvent xp();
    
    SoundEvent xr();
    
    SoundEvent xf();
    
    SoundEvent xb();
    
    RenderBlockOverlayEvent$OverlayType xw();
    
    RenderBlockOverlayEvent$OverlayType xg();
    
    RenderBlockOverlayEvent$OverlayType xu();
    
    int xz(final ChunkPos p0);
    
    int xc(final ChunkPos p0);
    
    int xa(final Chunk p0);
    
    int xm(final Chunk p0);
    
    String xh(final ServerData p0);
    
    CPacketUseEntity$Action kd();
    
    int ks(final Container p0);
    
    ClickType kl();
    
    GuiScreen ky(final Minecraft p0);
    
    EnumHand kx();
    
    Item kk();
    
    Instant kq();
    
    float kv(final EntityPlayerSP p0);
    
    float kj(final EntityPlayerSP p0);
    
    float ke(final EntityPlayerSP p0);
    
    String ko(final ServerData p0);
    
    boolean kt(final WorldClient p0);
    
    int kn(final EntityItem p0);
    
    RenderGameOverlayEvent$ElementType ki();
    
    GlStateManager$SourceFactor kp();
    
    GlStateManager$DestFactor kr();
    
    GlStateManager$SourceFactor kf();
    
    GlStateManager$DestFactor kb();
    
    Material kw();
    
    double kg(final EntityPlayer p0);
    
    double ku(final EntityPlayer p0);
    
    double kz(final EntityPlayer p0);
    
    RenderGameOverlayEvent$ElementType kc();
    
    float ka(final EntityPlayerSP p0);
    
    void km(final EntityPlayerSP p0, final float p1);
    
    void kh(final EntityPlayerSP p0, final float p1);
    
    Container qd(final EntityPlayerSP p0);
    
    Item qs();
    
    Potion ql();
    
    ItemEmptyMap qy();
    
    ItemMap qx();
    
    ClickType qk();
    
    String qq(final GuiButton p0);
    
    Color qv();
    
    Color qj();
    
    PlayerCapabilities qe(final EntityPlayerSP p0);
    
    boolean qo(final PlayerCapabilities p0);
    
    void qt(final EntityPlayerSP p0, final double p1);
    
    EnumFacing$Axis qn();
    
    EnumFacing$Axis qi();
    
    List qp(final Container p0);
    
    ClickType qr();
    
    CPacketPlayerDigging$Action qf();
    
    CPacketEntityAction$Action qb();
    
    CPacketEntityAction$Action qw();
    
    void qg(final FontRenderer p0, final int p1);
    
    Proxy qu();
    
    Agent qz();
    
    Block qc();
    
    BlockSkull qa();
    
    EnumFacing qm();
    
    EnumFacing qh();
    
    void vd(final PlayerCapabilities p0, final boolean p1);
    
    CPacketEntityAction$Action vs();
    
    KeyBinding vl(final GameSettings p0);
    
    double vy(final EntityPlayerSP p0);
    
    void vx(final EntityPlayerSP p0, final double p1);
    
    double vk(final EntityPlayerSP p0);
    
    void vq(final EntityPlayerSP p0, final double p1);
    
    KeyBinding vv(final GameSettings p0);
    
    void vj(final EntityPlayerSP p0, final float p1);
    
    KeyBinding ve(final GameSettings p0);
    
    double vo(final EntityPlayerSP p0);
    
    KeyBinding vt(final GameSettings p0);
    
    KeyBinding vn(final GameSettings p0);
    
    KeyBinding vi(final GameSettings p0);
    
    Color vp();
    
    Color vr();
    
    VertexFormat vf();
    
    EntityRenderer vb(final Minecraft p0);
    
    void vw(final Entity p0, final double p1);
    
    void vg(final Entity p0, final double p1);
    
    void vu(final Entity p0, final double p1);
    
    double vz(final Entity p0);
    
    double vc(final Entity p0);
    
    double va(final Entity p0);
    
    double vm(final Entity p0);
    
    double vh(final Entity p0);
    
    KeyBinding jd(final GameSettings p0);
    
    ItemStack js();
    
    ChatType jl();
    
    ChatType jy();
    
    float jx(final EntityPlayerSP p0);
    
    TileEntityChest jk(final TileEntityChest p0);
    
    TileEntityChest jq(final TileEntityChest p0);
    
    TileEntityChest jv(final TileEntityChest p0);
    
    TileEntityChest jj(final TileEntityChest p0);
    
    Potion je();
    
    Item jo();
    
    MovementInput jt(final EntityPlayerSP p0);
    
    float jn(final MovementInput p0);
    
    float ji(final MovementInput p0);
    
    double jp(final AxisAlignedBB p0);
    
    VertexFormat jr();
    
    VertexFormat jf();
    
    GameType jb();
    
    GameType jw();
    
    Color jg();
    
    Item ju();
    
    Item jz();
    
    boolean jc(final EntityPlayer p0);
    
    Item ja();
    
    double jm(final EntityEnderCrystal p0);
    
    double jh(final EntityEnderCrystal p0);
    
    double ed(final EntityEnderCrystal p0);
    
    Block es();
    
    World el(final Entity p0);
    
    IAttribute ey();
    
    SPacketPlayerListItem$Action ex();
    
    SoundCategory ek();
    
    SoundEvent eq();
    
    StandardOpenOption ev();
    
    EnumFacing$AxisDirection ej();
    
    RenderGameOverlayEvent$ElementType ee();
    
    RenderGameOverlayEvent$ElementType eo();
    
    RenderGameOverlayEvent$ElementType et();
    
    RenderGameOverlayEvent$ElementType en();
    
    RenderGameOverlayEvent$ElementType ei();
    
    RenderGameOverlayEvent$ElementType ep();
    
    RenderGameOverlayEvent$ElementType er();
    
    RenderGameOverlayEvent$ElementType ef();
    
    RenderGameOverlayEvent$ElementType eb();
    
    RenderGameOverlayEvent$ElementType ew();
    
    RenderGameOverlayEvent$ElementType eg();
    
    RenderGameOverlayEvent$ElementType eu();
    
    RenderGameOverlayEvent$ElementType ez();
    
    RenderGameOverlayEvent$ElementType ec();
    
    RenderGameOverlayEvent$ElementType ea();
    
    RenderGameOverlayEvent$ElementType em();
    
    RenderGameOverlayEvent$ElementType eh();
    
    boolean od(final GameSettings p0);
    
    void os(final EntityPlayerSP p0, final double p1);
    
    void ol(final EntityPlayerSP p0, final double p1);
    
    ChatType oy();
    
    TrayIcon.MessageType ox();
    
    Event$Result ok();
    
    int oq();
    
    int ov();
}
