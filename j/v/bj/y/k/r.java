package j.v.bj.y.k;

import net.minecraft.util.FoodStats;
import javax.swing.Icon;
import java.awt.Frame;
import java.awt.TrayIcon;
import java.awt.SystemTray;
import net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent;
import java.time.LocalTime;
import net.minecraft.util.EnumFacing$AxisDirection;
import java.nio.file.OpenOption;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.IAttribute;
import java.util.Optional;
import net.minecraft.world.GameType;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraft.tileentity.TileEntityChest;
import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.text.DecimalFormat;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.network.play.server.SPacketEffect;
import net.minecraftforge.event.entity.player.PlayerEvent$NameFormat;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.text.ChatType;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.Vec3i;
import net.minecraft.client.gui.MapItemRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.storage.MapData;
import net.minecraft.item.ItemMap;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.client.settings.KeyBinding;
import java.security.MessageDigest;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.EnumFacing$Axis;
import net.minecraftforge.event.entity.living.LivingEvent$LivingJumpEvent;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.Reader;
import com.google.gson.Gson;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.BlockEvent$BreakEvent;
import java.util.Comparator;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.Potion;
import java.io.FileOutputStream;
import java.util.Vector;
import java.lang.reflect.Field;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.client.renderer.GlStateManager$DestFactor;
import net.minecraft.client.renderer.GlStateManager$SourceFactor;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Post;
import net.minecraftforge.fml.common.eventhandler.Event;
import java.lang.annotation.Annotation;
import net.minecraft.network.play.server.SPacketEntityTeleport;
import net.minecraftforge.client.event.EntityViewRenderEvent$FOVModifier;
import java.nio.FloatBuffer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.client.network.NetworkPlayerInfo;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.Clipboard;
import net.minecraft.util.Session;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import java.io.IOException;
import javax.net.ssl.HttpsURLConnection;
import java.net.URLConnection;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.network.play.client.CPacketUseEntity$Action;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import java.io.FileWriter;
import java.io.BufferedWriter;
import net.minecraft.client.multiplayer.ServerData;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSetMultimap;
import net.minecraftforge.client.event.RenderBlockOverlayEvent$OverlayType;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import net.minecraft.network.play.client.CPacketUpdateSign;
import java.io.InputStream;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.client.renderer.entity.RenderManager;
import java.util.function.BiConsumer;
import com.mojang.authlib.GameProfile;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraft.util.SoundEvent;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundHandler;
import java.util.HashMap;
import java.net.URL;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.network.NetworkManager;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.Style;
import java.awt.image.RenderedImage;
import java.awt.image.BufferedImage;
import java.util.function.IntFunction;
import java.util.function.Function;
import com.google.common.collect.ImmutableSet;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.item.ItemBlock;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.client.event.RenderTooltipEvent$PostText;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import java.time.Duration;
import java.time.temporal.Temporal;
import java.time.Instant;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.ClickType;
import net.minecraft.client.shader.Framebuffer;
import java.nio.Buffer;
import java.nio.IntBuffer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import java.io.BufferedReader;
import net.minecraft.network.play.server.SPacketPlayerListItem$AddPlayerData;
import net.minecraft.network.play.server.SPacketPlayerListItem$Action;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraft.tileentity.TileEntityHopper;
import java.awt.Color;
import net.minecraft.nbt.NBTBase;
import net.minecraft.client.util.ITooltipFlag;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import java.awt.Container;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import java.util.UUID;
import org.apache.logging.log4j.Logger;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import java.util.Random;
import javax.swing.Timer;
import java.nio.file.Path;
import com.mojang.authlib.exceptions.AuthenticationException;
import java.io.PrintStream;
import com.google.common.io.CharSource;
import java.nio.charset.Charset;
import java.io.File;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.client.entity.EntityPlayerSP;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Stream;
import net.minecraft.util.NonNullList;
import net.minecraft.item.ItemStack;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.google.common.util.concurrent.ListenableFuture;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.block.Block;
import java.util.function.Consumer;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.text.ITextComponent;
import com.google.common.base.Predicate;
import net.minecraft.client.gui.GuiTextField;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;

public interface r
{
    Enum d(final Class p0, final String p1);
    
    Iterator s(final ArrayList p0);
    
    boolean l(final Iterator p0);
    
    Object y(final Iterator p0);
    
    Object x(final Map p0, final Object p1, final Object p2);
    
    boolean k(final String p0, final Object p1);
    
    boolean q(final ArrayList p0, final Object p1);
    
    boolean v(final ArrayList p0, final Collection p1);
    
    void j(final List p0);
    
    void e(final boolean p0);
    
    void o(final GuiTextField p0, final Predicate p1);
    
    void t(final GuiTextField p0, final int p1);
    
    String n(final ITextComponent p0);
    
    void i(final GuiTextField p0, final String p1);
    
    boolean p(final List p0, final Object p1);
    
    Object r(final List p0, final int p1);
    
    void f(final GuiTextField p0, final boolean p1);
    
    void b(final TileEntitySign p0, final boolean p1);
    
    void w(final List p0, final Consumer p1);
    
    boolean g(final GuiTextField p0);
    
    String u(final GuiTextField p0);
    
    String z(final String p0, final Object[] p1);
    
    void c(final float p0, final float p1, final float p2, final float p3);
    
    void a();
    
    void m(final float p0, final float p1, final float p2);
    
    void h(final float p0, final float p1, final float p2);
    
    void sd(final float p0, final float p1, final float p2, final float p3);
    
    Block ss(final TileEntitySign p0);
    
    int sl(final TileEntitySign p0);
    
    void sy(final TileEntityRendererDispatcher p0, final TileEntity p1, final double p2, final double p3, final double p4, final float p5);
    
    void sx();
    
    void sk(final TileEntitySign p0);
    
    void sq(final Minecraft p0, final GuiScreen p1);
    
    NetHandlerPlayClient sv(final Minecraft p0);
    
    BlockPos sj(final TileEntitySign p0);
    
    void se(final NetHandlerPlayClient p0, final Packet p1);
    
    int so(final FontRenderer p0, final String p1);
    
    char[] st(final String p0);
    
    boolean sn(final char p0);
    
    int si(final GuiTextField p0);
    
    boolean sp(final GuiTextField p0, final char p1, final int p2);
    
    boolean sr(final GuiTextField p0, final int p1, final int p2, final int p3);
    
    void sf(final EventBus p0, final Object p1);
    
    ListenableFuture sb(final Minecraft p0, final Runnable p1);
    
    String sw(final SimpleDateFormat p0, final Date p1);
    
    Iterator sg(final List p0);
    
    String su(final Enum p0);
    
    void sz(final PrintWriter p0, final String p1);
    
    void sc(final PrintWriter p0);
    
    int sa(final Integer p0);
    
    int sm(final ItemStack p0);
    
    int sh(final ItemStack p0);
    
    double ld(final double p0);
    
    int ls();
    
    List ll(final NonNullList p0, final int p1, final int p2);
    
    Minecraft ly();
    
    List lx(final Object[] p0);
    
    StringBuilder lk(final StringBuilder p0, final String p1);
    
    String lq(final StringBuilder p0);
    
    String[] lv(final String p0, final String p1);
    
    String lj(final String p0);
    
    Object le(final Map p0, final Object p1, final Object p2);
    
    Stream lo(final List p0);
    
    Stream lt(final Stream p0, final java.util.function.Predicate p1);
    
    Collector ln();
    
    Object li(final Stream p0, final Collector p1);
    
    int lp(final List p0);
    
    BlockPos lr(final TileEntity p0);
    
    boolean lf(final Boolean p0);
    
    boolean lb(final Set p0, final Object p1);
    
    int lw(final BlockPos p0);
    
    int lg(final BlockPos p0);
    
    int lu(final BlockPos p0);
    
    double lz(final EntityPlayerSP p0, final double p1, final double p2, final double p3);
    
    double lc(final Double p0);
    
    Item la(final int p0);
    
    void lm(final Set p0);
    
    ItemStack lh(final InventoryPlayer p0, final int p1);
    
    Item yd(final ItemStack p0);
    
    boolean ys(final Object p0, final Object p1);
    
    BlockPos yl(final RayTraceResult p0);
    
    BlockPos yy(final BlockPos p0, final EnumFacing p1);
    
    boolean yx(final Set p0, final Object p1);
    
    CharSource yk(final File p0, final Charset p1);
    
    String yq(final CharSource p0);
    
    boolean yv(final String p0);
    
    String yj(final String p0);
    
    void ye(final PrintStream p0, final String p1);
    
    void yo(final AuthenticationException p0);
    
    String yt(final AuthenticationException p0);
    
    void yn(final Exception p0);
    
    void yi(final GuiTextField p0);
    
    void yp(final GuiTextField p0);
    
    boolean yr(final File p0);
    
    String yf(final File p0);
    
    Path yb(final String p0, final String[] p1);
    
    byte[] yw(final Path p0);
    
    void yg(final Timer p0);
    
    String yu(final Exception p0);
    
    void yz(final Timer p0);
    
    int yc(final Random p0, final int p1);
    
    StringBuilder ya(final StringBuilder p0, final char p1);
    
    String ym(final char p0, final int p1);
    
    void yh(final EntityPlayerSP p0, final String p1);
    
    float xd(final EntityPlayerSP p0, final Entity p1);
    
    float xs(final EntityLivingBase p0);
    
    ItemStack xl(final EntityPlayerSP p0);
    
    void xy(final PlayerControllerMP p0, final EntityPlayer p1, final Entity p2);
    
    void xx(final EntityPlayerSP p0, final EnumHand p1);
    
    String xk(final String p0);
    
    String xq(final String p0);
    
    int xv(final String p0);
    
    String xj(final int p0);
    
    HashFunction xe();
    
    HashCode xo(final HashFunction p0, final CharSequence p1, final Charset p2);
    
    String xt(final HashCode p0);
    
    HashFunction xn();
    
    boolean xi(final String p0, final String p1);
    
    boolean xp(final Random p0);
    
    void xr(final Logger p0, final String p1);
    
    UUID xf(final String p0);
    
    String xb(final UUID p0);
    
    void xw(final PrintStream p0, final String p1);
    
    void xg(final EntityOtherPlayerMP p0, final Entity p1);
    
    NBTTagCompound xu(final EntityPlayerSP p0, final NBTTagCompound p1);
    
    void xz(final EntityOtherPlayerMP p0, final NBTTagCompound p1);
    
    Entity xc(final WorldClient p0, final int p1);
    
    void xa(final WorldClient p0, final int p1, final Entity p2);
    
    String xm(final Exception p0);
    
    Toolkit xh();
    
    Dimension kd(final Toolkit p0);
    
    void ks(final JFrame p0, final int p1, final int p2);
    
    void kl(final JFrame p0, final Container p1);
    
    void ky(final JFrame p0);
    
    void kx(final JFrame p0, final boolean p1);
    
    void kk(final JTextField p0, final ActionListener p1);
    
    ITextComponent kq(final ClientChatReceivedEvent p0);
    
    void kv(final JTextArea p0, final String p1);
    
    JScrollBar kj(final JScrollPane p0);
    
    int ke(final JScrollBar p0);
    
    void ko(final JScrollBar p0, final int p1);
    
    void kt(final JTextArea p0, final String p1);
    
    void kn(final JFrame p0);
    
    void ki(final JPanel p0, final LayoutManager p1);
    
    Border kp();
    
    TitledBorder kr(final Border p0, final String p1);
    
    void kf(final JPanel p0, final Border p1);
    
    void kb(final JPanel p0, final Component p1, final Object p2);
    
    void kw(final JSplitPane p0, final Component p1);
    
    void kg(final JScrollPane p0, final int p1);
    
    void ku(final JScrollPane p0, final int p1);
    
    void kz(final JTextArea p0, final boolean p1);
    
    void kc(final JScrollPane p0, final Component p1);
    
    void ka(final JSplitPane p0, final Component p1);
    
    String km(final JTextField p0);
    
    boolean kh(final String p0, final String p1);
    
    void qd(final JTextField p0, final String p1);
    
    long qs(final double p0);
    
    ItemStack ql(final InventoryPlayer p0);
    
    boolean qy(final ItemStack p0);
    
    List qx(final ItemStack p0, final EntityPlayer p1, final ITooltipFlag p2);
    
    NBTTagCompound qk(final ItemStack p0);
    
    Set qq(final NBTTagCompound p0);
    
    Iterator qv(final Set p0);
    
    NBTBase qj(final NBTTagCompound p0, final String p1);
    
    String qe(final NBTBase p0);
    
    int qo(final Color p0);
    
    int qt(final FontRenderer p0, final String p1, final int p2, final int p3, final int p4);
    
    Class qn(final Object p0);
    
    Object[] qi(final Class p0);
    
    Object qp(final Class p0, final Object p1);
    
    BlockPos qr(final TileEntityHopper p0);
    
    double qf(final BlockPos p0, final int p1, final int p2, final int p3);
    
    BlockPos qb(final BlockPos p0, final int p1, final int p2, final int p3);
    
    void qw(final PrintStream p0, final Object p1);
    
    Boolean qg(final boolean p0);
    
    SPacketPlayerListItem$Action qu(final SPacketPlayerListItem p0);
    
    Collection qz(final NetHandlerPlayClient p0);
    
    int qc(final Collection p0);
    
    List qa(final SPacketPlayerListItem p0);
    
    ITextComponent qm(final SPacketPlayerListItem$AddPlayerData p0);
    
    StringBuilder qh(final StringBuilder p0, final Object p1);
    
    String vd(final BufferedReader p0);
    
    void vs(final BufferedReader p0);
    
    Charset vl();
    
    Object[] vy(final Set p0);
    
    String vx(final Object p0);
    
    String vk(final EntityPlayer p0);
    
    IBlockState vq(final WorldClient p0, final BlockPos p1);
    
    Material vv(final IBlockState p0);
    
    boolean vj(final Material p0);
    
    boolean ve(final EntityPlayerSP p0, final Object p1);
    
    void vo(final EntityPlayerSP p0, final float p1);
    
    float vt(final EntityPlayerSP p0);
    
    void vn(final Minecraft p0);
    
    BlockPos vi(final EntityPlayerSP p0);
    
    Chunk vp(final WorldClient p0, final BlockPos p1);
    
    BiomeProvider vr(final WorldClient p0);
    
    Biome vf(final Chunk p0, final BlockPos p1, final BiomeProvider p2);
    
    String vb(final Biome p0);
    
    Vec3d vw(final EntityPlayerSP p0);
    
    boolean vg(final BlockPos p0, final Object p1);
    
    List vu(final WorldClient p0, final Entity p1, final AxisAlignedBB p2);
    
    boolean vz(final List p0);
    
    boolean vc(final File p0);
    
    boolean va();
    
    int vm(final IntBuffer p0);
    
    IntBuffer vh(final int p0);
    
    void jd(final int p0, final int p1);
    
    Buffer js(final IntBuffer p0);
    
    void jl(final int p0);
    
    void jy(final int p0, final int p1, final int p2, final int p3, final IntBuffer p4);
    
    void jx(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final IntBuffer p6);
    
    IntBuffer jk(final IntBuffer p0, final int[] p1);
    
    Framebuffer jq(final Minecraft p0);
    
    void jv(final Thread p0);
    
    ItemStack jj(final EntityPlayerSP p0);
    
    ItemStack je(final PlayerControllerMP p0, final int p1, final int p2, final int p3, final ClickType p4, final EntityPlayer p5);
    
    Slot jo(final net.minecraft.inventory.Container p0, final int p1);
    
    ItemStack jt(final Slot p0);
    
    void jn(final TextureManager p0, final ResourceLocation p1);
    
    void ji(final int p0);
    
    void jp();
    
    void jr(final float p0, final float p1, final float p2);
    
    void jf(final float p0, final float p1, final float p2, final float p3);
    
    List jb(final FontRenderer p0, final String p1, final int p2);
    
    void jw();
    
    void jg();
    
    int ju(final String p0, final int p1);
    
    void jz(final FontRenderer p0, final String p1, final int p2, final int p3, final int p4, final int p5);
    
    Instant jc();
    
    Duration ja(final Temporal p0, final Temporal p1);
    
    long jm(final Duration p0);
    
    String jh(final String p0, final CharSequence p1, final CharSequence p2);
    
    boolean ed(final List p0, final Collection p1);
    
    boolean es(final List p0, final Object p1);
    
    Vec3d el(final EntityPlayer p0);
    
    ItemStack ey(final ItemTooltipEvent p0);
    
    boolean ex(final ItemStack p0);
    
    boolean ek(final NBTTagCompound p0, final String p1, final int p2);
    
    NBTTagCompound eq(final NBTTagCompound p0);
    
    void ev(final NBTTagCompound p0, final String p1, final String p2);
    
    TileEntity ej(final World p0, final NBTTagCompound p1);
    
    boolean ee(final TileEntity p0, final Capability p1, final EnumFacing p2);
    
    List eo(final ItemTooltipEvent p0);
    
    boolean et(final List p0, final Object p1);
    
    ItemStack en(final RenderTooltipEvent$PostText p0);
    
    int ei(final RenderTooltipEvent$PostText p0);
    
    int ep(final RenderTooltipEvent$PostText p0);
    
    Object er(final TileEntity p0, final Capability p1, final EnumFacing p2);
    
    int ef(final IItemHandler p0);
    
    int eb(final int p0, final int p1);
    
    int ew(final int p0, final int p1);
    
    List eg(final RenderTooltipEvent$PostText p0);
    
    int eu(final ScaledResolution p0);
    
    void ez();
    
    void ec();
    
    void ea(final float p0, final float p1, final float p2);
    
    TextureManager em(final Minecraft p0);
    
    void eh();
    
    Block od(final ItemBlock p0);
    
    EnumDyeColor os(final BlockShulkerBox p0);
    
    int ol(final EnumDyeColor p0);
    
    RenderItem oy(final Minecraft p0);
    
    void ox();
    
    void ok();
    
    ItemStack oq(final IItemHandler p0, final int p1);
    
    void ov(final RenderItem p0, final ItemStack p1, final int p2, final int p3);
    
    void oj(final RenderItem p0, final FontRenderer p1, final ItemStack p2, final int p3, final int p4);
    
    void oe();
    
    void oo();
    
    void ot(final int p0, final int p1, final float p2, final float p3, final int p4, final int p5, final float p6, final float p7);
    
    ResourceLocation on(final Item p0);
    
    boolean oi(final Class p0);
    
    ImmutableSet op(final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object[] p6);
    
    Stream or(final ImmutableSet p0);
    
    Stream of(final Stream p0, final Function p1);
    
    Object[] ob(final Stream p0, final IntFunction p1);
    
    Stream ow(final Object[] p0);
    
    void og(final BufferedImage p0, final int p1, final int p2, final int p3);
    
    void ou(final BufferedImage p0, final int p1, final int p2, final int p3, final int p4, final int[] p5, final int p6, final int p7);
    
    boolean oz(final RenderedImage p0, final String p1, final File p2);
    
    String oc(final File p0);
    
    Style oa(final ITextComponent p0);
    
    String om(final File p0);
    
    Style oh(final Style p0, final ClickEvent p1);
    
    Style td(final Style p0, final Boolean p1);
    
    void ts(final Object p0, final int p1, final Object p2, final int p3, final int p4);
    
    StringBuilder tl(final StringBuilder p0, final int p1);
    
    boolean ty(final File p0);
    
    int tx(final MouseEvent p0);
    
    void tk(final NetworkManager p0, final Packet p1);
    
    void tq(final MouseEvent p0, final boolean p1);
    
    Block tv(final IBlockState p0);
    
    Block tj(final int p0);
    
    void te(final TileEntityShulkerBox p0, final World p1);
    
    TileEntity to(final WorldClient p0, final BlockPos p1);
    
    NBTTagCompound tt(final TileEntity p0);
    
    NBTTagCompound tn(final NBTTagCompound p0, final String p1);
    
    void ti(final TileEntityShulkerBox p0, final NBTTagCompound p1);
    
    String tp(final NBTTagCompound p0);
    
    void tr(final LaunchClassLoader p0, final URL p1);
    
    Class tf(final LaunchClassLoader p0, final String p1);
    
    void tb(final HashMap p0);
    
    Iterable tw(final BlockPos p0, final BlockPos p1);
    
    Iterator tg(final Iterable p0);
    
    Object tu(final HashMap p0, final Object p1, final Object p2);
    
    boolean tz(final Material p0);
    
    boolean tc(final Material p0);
    
    Set ta(final HashMap p0);
    
    Object tm(final Map.Entry p0);
    
    void th(final float p0);
    
    Object nd(final Map.Entry p0);
    
    void ns(final AxisAlignedBB p0, final float p1, final float p2, final float p3, final float p4);
    
    void nl(final AxisAlignedBB p0, final float p1, final float p2, final float p3, final float p4);
    
    SoundHandler ny(final Minecraft p0);
    
    Object nx(final Class p0, final Object p1, final String[] p2);
    
    void nk(final SoundManager p0);
    
    SoundEvent nq(final SPacketSoundEffect p0);
    
    Entity nv(final EntityPlayerSP p0);
    
    void nj(final EntityPlayerSP p0);
    
    void ne(final WorldClient p0, final Entity p1);
    
    boolean no(final WorldClient p0, final Entity p1);
    
    boolean nt(final EntityPlayerSP p0, final Entity p1);
    
    GuiScreen nn(final GuiOpenEvent p0);
    
    void ni(final GuiOpenEvent p0, final GuiScreen p1);
    
    Double np(final double p0);
    
    UUID nr(final GameProfile p0);
    
    EntityPlayer nf(final WorldClient p0, final UUID p1);
    
    AxisAlignedBB nb(final EntityPlayer p0);
    
    Object nw(final HashMap p0, final Object p1);
    
    Object ng(final HashMap p0, final Object p1);
    
    String nu(final String p0, final Object[] p1);
    
    int nz(final HashMap p0);
    
    void nc(final HashMap p0, final BiConsumer p1);
    
    Vec3d na(final AxisAlignedBB p0);
    
    double nm(final EntityPlayerSP p0, final double p1, final double p2, final double p3);
    
    RenderManager nh(final Minecraft p0);
    
    Vec3d id(final Vec3d p0, final Vec3d p1);
    
    Vec3d is(final Vec3d p0);
    
    double il(final double p0, final double p1);
    
    void iy(final double p0, final double p1, final double p2);
    
    void ix(final float p0, final float p1, final float p2);
    
    void ik(final float p0, final float p1, final float p2, final float p3);
    
    void iq(final double p0, final double p1, final double p2);
    
    void iv(final int p0);
    
    void ij(final int p0);
    
    void ie(final int p0, final int p1);
    
    StringBuilder io(final StringBuilder p0, final double p1);
    
    int it(final FontRenderer p0, final String p1, final float p2, final float p3, final int p4);
    
    int in(final Color p0);
    
    int ii(final Color p0);
    
    int ip(final Color p0);
    
    void ir(final ItemStack p0, final NBTTagCompound p1);
    
    void if(final NBTTagCompound p0, final String p1, final boolean p2);
    
    void ib(final NBTTagCompound p0, final String p1, final byte p2);
    
    void iw(final NBTTagCompound p0, final String p1, final short p2);
    
    void ig(final NBTTagCompound p0, final String p1, final int p2);
    
    void iu(final NBTTagCompound p0, final String p1, final long p2);
    
    void iz(final NBTTagCompound p0, final String p1, final float p2);
    
    void ic(final NBTTagCompound p0, final String p1, final double p2);
    
    void ia(final NBTTagCompound p0, final String p1, final NBTBase p2);
    
    boolean im(final NBTTagCompound p0, final String p1);
    
    boolean ih(final NBTTagCompound p0, final String p1);
    
    byte pd(final NBTTagCompound p0, final String p1);
    
    short ps(final NBTTagCompound p0, final String p1);
    
    int pl(final NBTTagCompound p0, final String p1);
    
    long py(final NBTTagCompound p0, final String p1);
    
    float px(final NBTTagCompound p0, final String p1);
    
    double pk(final NBTTagCompound p0, final String p1);
    
    String pq(final NBTTagCompound p0, final String p1);
    
    NBTTagList pv(final NBTTagCompound p0, final String p1, final int p2);
    
    InputStream pj(final Class p0, final String p1);
    
    Set pe(final Object p0);
    
    long po();
    
    void pt(final GuiOpenEvent p0, final boolean p1);
    
    String[] pn(final CPacketUpdateSign p0);
    
    String pi(final String p0, final int p1, final int p2);
    
    void pp(final Class p0, final Object p1, final Object p2, final String[] p3);
    
    IntStream pr(final Random p0, final int p1, final int p2);
    
    IntStream pf(final IntStream p0, final IntUnaryOperator p1);
    
    IntStream pb(final IntStream p0, final long p1);
    
    Stream pw(final IntStream p0, final IntFunction p1);
    
    Collector pg();
    
    String pu(final char p0);
    
    RenderGameOverlayEvent$ElementType pz(final RenderGameOverlayEvent p0);
    
    void pc(final RenderGameOverlayEvent p0, final boolean p1);
    
    int pa(final ScaledResolution p0);
    
    float pm(final Float p0);
    
    float ph(final RenderGameOverlayEvent p0);
    
    boolean rd(final PlayerControllerMP p0);
    
    boolean rs(final Block p0, final IBlockState p1);
    
    Entity rl(final Minecraft p0);
    
    void ry(final int p0);
    
    Entity rx(final PlaySoundAtEntityEvent p0);
    
    SoundEvent rk(final PlaySoundAtEntityEvent p0);
    
    Vec3d rq(final Entity p0);
    
    String rv(final ClientChatEvent p0);
    
    List rj(final List p0, final int p1, final int p2);
    
    Object[] re(final ArrayList p0, final Object[] p1);
    
    void ro(final ClientChatEvent p0, final boolean p1);
    
    String rt(final CPacketChatMessage p0);
    
    RenderBlockOverlayEvent$OverlayType[] rn();
    
    int ri(final RenderBlockOverlayEvent$OverlayType p0);
    
    ImmutableSetMultimap rp(final WorldClient p0);
    
    ImmutableMultiset rr(final ImmutableSetMultimap p0);
    
    UnmodifiableIterator rf(final ImmutableMultiset p0);
    
    Chunk rb(final WorldClient p0, final int p1, final int p2);
    
    Map rw(final Chunk p0);
    
    Collection rg(final Map p0);
    
    int ru(final Map p0);
    
    void rz(final PrintStream p0, final int p1);
    
    Iterator rc(final Collection p0);
    
    void ra(final PrintStream p0, final boolean p1);
    
    Integer rm(final int p0);
    
    String rh(final boolean p0);
    
    PrintStream fd(final PrintStream p0, final String p1, final Object[] p2);
    
    ServerData fs(final Minecraft p0);
    
    void fl(final BufferedWriter p0, final String p1);
    
    void fy(final BufferedWriter p0);
    
    void fx(final FileWriter p0);
    
    BlockPos fk(final CPacketPlayerTryUseItemOnBlock p0);
    
    EnumFacing fq(final CPacketPlayerTryUseItemOnBlock p0);
    
    EnumHand fv(final CPacketPlayerTryUseItemOnBlock p0);
    
    float fj(final CPacketPlayerTryUseItemOnBlock p0);
    
    float fe(final CPacketPlayerTryUseItemOnBlock p0);
    
    float fo(final CPacketPlayerTryUseItemOnBlock p0);
    
    EnumHand ft(final CPacketPlayerTryUseItem p0);
    
    CPacketUseEntity$Action fn(final CPacketUseEntity p0);
    
    Entity fi(final CPacketUseEntity p0, final World p1);
    
    boolean fp(final HashBiMap p0, final Object p1);
    
    Object fr(final HashBiMap p0, final Object p1);
    
    boolean ff(final HashBiMap p0, final Object p1);
    
    BiMap fb(final HashBiMap p0);
    
    Object fw(final BiMap p0, final Object p1);
    
    InputStream fg(final URL p0);
    
    Object fu(final HashBiMap p0, final Object p1, final Object p2);
    
    StringBuilder fz(final StringBuilder p0, final int p1, final char p2);
    
    URLConnection fc(final URL p0);
    
    void fa(final HttpsURLConnection p0);
    
    void fm(final HttpsURLConnection p0);
    
    void fh(final IOException p0);
    
    void bd(final long p0);
    
    HashBiMap bs();
    
    void bl(final RenderSpecificHandEvent p0, final boolean p1);
    
    EnumHand by(final RenderSpecificHandEvent p0);
    
    float bx(final Double p0);
    
    ITextComponent bk(final TextComponentString p0, final ITextComponent p1);
    
    void bq(final ClientChatReceivedEvent p0, final ITextComponent p1);
    
    ITextComponent bv(final String p0);
    
    Session bj(final Minecraft p0);
    
    String be(final Session p0);
    
    void bo(final NBTTagList p0, final NBTBase p1);
    
    void bt(final ItemStack p0, final String p1, final NBTBase p2);
    
    long bn(final Long p0);
    
    Item bi(final Block p0);
    
    boolean bp(final Object[] p0, final Object p1);
    
    double br(final double p0);
    
    long bf();
    
    int bb(final float p0, final float p1, final float p2);
    
    String bw(final int p0);
    
    long bg(final String p0, final int p1);
    
    int bu(final Color p0);
    
    double bz(final double p0, final double p1);
    
    Clipboard bc(final Toolkit p0);
    
    void ba(final Clipboard p0, final Transferable p1, final ClipboardOwner p2);
    
    float bm(final Minecraft p0);
    
    Vec3d bh(final EntityPlayerSP p0, final float p1);
    
    Vec3d wd(final Entity p0, final double p1);
    
    void ws(final Minecraft p0, final Entity p1);
    
    Object wl(final Map p0, final Object p1);
    
    Thread wy();
    
    boolean wx(final Thread p0);
    
    boolean wk(final Minecraft p0);
    
    Integer wq(final String p0);
    
    boolean wv(final String p0, final CharSequence p1);
    
    String wj(final String p0, final int p1);
    
    int we(final String p0);
    
    void wo(final Throwable p0);
    
    void wt(final InterruptedException p0);
    
    boolean wn();
    
    int wi();
    
    String wp(final int p0);
    
    UUID wr(final EntityPlayer p0);
    
    NetworkPlayerInfo wf(final NetHandlerPlayClient p0, final UUID p1);
    
    int wb(final NetworkPlayerInfo p0);
    
    boolean ww(final File p0, final File p1);
    
    void wg(final Entity p0, final boolean p1);
    
    int wu(final EntityItem p0);
    
    String wz(final double p0);
    
    void wc(final Entity p0, final String p1);
    
    FloatBuffer wa(final int p0);
    
    float wm(final FloatBuffer p0, final int p1);
    
    double wh(final double p0);
    
    double gd(final double p0);
    
    int gs(final IntBuffer p0, final int p1);
    
    double gl(final double p0);
    
    double gy(final double p0);
    
    double gx(final double p0, final double p1);
    
    FloatBuffer gk(final FloatBuffer p0);
    
    double gq(final double p0);
    
    double gv(final double p0);
    
    double gj(final double p0);
    
    double ge(final double p0);
    
    float go(final EntityViewRenderEvent$FOVModifier p0);
    
    float gt(final float p0, final float p1);
    
    void gn(final EntityViewRenderEvent$FOVModifier p0, final float p1);
    
    String gi(final HashMap p0);
    
    Object gp(final HashMap p0, final Object p1, final Object p2);
    
    GameProfile gr(final NetworkPlayerInfo p0);
    
    String gf(final GameProfile p0);
    
    double gb(final SPacketEntityTeleport p0);
    
    double gw(final SPacketEntityTeleport p0);
    
    double gg(final SPacketEntityTeleport p0);
    
    double gu(final EntityPlayerSP p0, final BlockPos p1);
    
    int gz(final SPacketEntityTeleport p0);
    
    Object gc(final Object p0);
    
    Annotation ga(final Class p0, final Class p1);
    
    void gm(final EventBus p0, final Object p1);
    
    boolean gh(final EventBus p0, final Event p1);
    
    String ud(final Throwable p0);
    
    RenderGameOverlayEvent$ElementType us(final RenderGameOverlayEvent$Post p0);
    
    StringBuilder ul(final StringBuilder p0, final boolean p1);
    
    float uy(final DrawBlockHighlightEvent p0);
    
    EntityPlayer ux(final DrawBlockHighlightEvent p0);
    
    RayTraceResult uk(final DrawBlockHighlightEvent p0);
    
    void uq();
    
    void uv(final GlStateManager$SourceFactor p0, final GlStateManager$DestFactor p1, final GlStateManager$SourceFactor p2, final GlStateManager$DestFactor p3);
    
    void uj(final float p0);
    
    void ue();
    
    void uo(final boolean p0);
    
    WorldBorder ut(final WorldClient p0);
    
    boolean un(final WorldBorder p0, final BlockPos p1);
    
    AxisAlignedBB ui(final IBlockState p0, final World p1, final BlockPos p2);
    
    AxisAlignedBB up(final AxisAlignedBB p0, final double p1);
    
    AxisAlignedBB ur(final AxisAlignedBB p0, final double p1, final double p2, final double p3);
    
    int uf(final int p0);
    
    void ub();
    
    void uw();
    
    void ug(final DrawBlockHighlightEvent p0, final boolean p1);
    
    boolean uu(final int p0);
    
    Logger uz(final String p0);
    
    Object uc(final Field p0, final Object p1);
    
    Object[] ua(final Vector p0, final Object[] p1);
    
    ClassLoader um();
    
    URL uh(final ClassLoader p0, final String p1);
    
    String zd(final URL p0);
    
    File zs(final String p0, final String p1);
    
    int zl(final InputStream p0, final byte[] p1);
    
    void zy(final FileOutputStream p0, final byte[] p1, final int p2, final int p3);
    
    void zx(final FileOutputStream p0);
    
    void zk(final InputStream p0);
    
    void zq(final String p0);
    
    Field zv(final Class p0, final String p1);
    
    void zj(final Field p0, final boolean p1);
    
    int ze(final NonNullList p0);
    
    Object zo(final NonNullList p0, final int p1);
    
    NonNullList zt(final net.minecraft.inventory.Container p0);
    
    PotionEffect zn(final EntityPlayerSP p0, final Potion p1);
    
    boolean zi(final EntityPlayerSP p0, final Potion p1);
    
    int zp(final PotionEffect p0);
    
    void zr(final WorldClient p0);
    
    void zf(final Minecraft p0, final WorldClient p1);
    
    boolean zb(final Minecraft p0);
    
    void zw(final RealmsBridge p0, final GuiScreen p1);
    
    int zg(final FontRenderer p0, final String p1, final float p2, final float p3, final int p4, final boolean p5);
    
    int zu(final FontRenderer p0, final char p1);
    
    String zz(final Class p0);
    
    boolean zc(final HashMap p0, final Object p1);
    
    void za(final List p0, final Comparator p1);
    
    String zm(final Session p0);
    
    GameProfile zh(final Session p0);
    
    String cd(final String p0, final String p1);
    
    long cs(final Duration p0);
    
    ItemStack cl(final InventoryPlayer p0, final int p1);
    
    double cy(final Vec3d p0, final Vec3d p1);
    
    ItemStack cx(final EntityPlayerSP p0, final EnumHand p1);
    
    String ck(final ItemStack p0);
    
    EntityPlayer cq(final BlockEvent$BreakEvent p0);
    
    boolean cv(final EntityPlayer p0, final Object p1);
    
    IBlockState cj(final BlockEvent$BreakEvent p0);
    
    String ce(final Block p0);
    
    Entity co(final AttackEntityEvent p0);
    
    ITextComponent ct(final Entity p0);
    
    byte[] cn(final String p0, final Charset p1);
    
    void ci(final HttpURLConnection p0, final String p1);
    
    void cp(final HttpURLConnection p0, final String p1, final String p2);
    
    void cr(final HttpURLConnection p0, final int p1);
    
    void cf(final HttpURLConnection p0, final boolean p1);
    
    void cb(final HttpURLConnection p0, final boolean p1);
    
    void cw(final HttpURLConnection p0);
    
    OutputStream cg(final HttpURLConnection p0);
    
    void cu(final OutputStream p0, final byte[] p1);
    
    InputStream cz(final HttpURLConnection p0);
    
    Object cc(final Gson p0, final Reader p1, final Class p2);
    
    void ca(final HttpURLConnection p0);
    
    JsonElement cm(final JsonObject p0, final String p1);
    
    String ch(final JsonElement p0);
    
    boolean ad(final EntityPlayerSP p0);
    
    boolean as(final WorldClient p0, final BlockPos p1);
    
    EntityLivingBase al(final LivingEvent$LivingJumpEvent p0);
    
    boolean ay(final EntityLivingBase p0, final Object p1);
    
    Object ax(final Class p0);
    
    EnumFacing$Axis[] ak();
    
    int aq(final EnumFacing$Axis p0);
    
    void av(final EntityPlayerSP p0);
    
    int aj(final double p0);
    
    NBTTagList ae(final ItemStack p0);
    
    int ao(final NBTTagList p0);
    
    int at(final NBTTagList p0);
    
    NBTTagCompound an(final NBTTagList p0, final int p1);
    
    EnumFacing ai(final EntityPlayerSP p0);
    
    EnumHand ap(final EntityPlayerSP p0);
    
    int ar(final RenderGameOverlayEvent$ElementType p0);
    
    boolean af(final char[] p0, final char p1);
    
    char[] ab(final int p0);
    
    StringBuilder aw(final StringBuilder p0, final char[] p1);
    
    int ag(final ArrayList p0);
    
    Object au(final ArrayList p0, final int p1);
    
    int az(final Number p0);
    
    int ac(final String p0, final String p1);
    
    ITextComponent aa(final SPacketChat p0);
    
    int am(final String p0);
    
    UserAuthentication ah(final YggdrasilAuthenticationService p0, final Agent p1);
    
    void md(final YggdrasilUserAuthentication p0, final String p1);
    
    void ms(final YggdrasilUserAuthentication p0, final String p1);
    
    void ml(final YggdrasilUserAuthentication p0);
    
    GameProfile my(final YggdrasilUserAuthentication p0);
    
    String mx(final YggdrasilUserAuthentication p0);
    
    String mk(final Item p0, final ItemStack p1);
    
    String mq(final long p0);
    
    byte[] mv(final String p0);
    
    void mj(final Random p0, final byte[] p1);
    
    Charset me(final String p0);
    
    MessageDigest mo(final String p0);
    
    void mt(final MessageDigest p0, final byte[] p1);
    
    byte[] mn(final MessageDigest p0);
    
    long mi(final Stream p0);
    
    StringBuilder mp(final StringBuilder p0, final long p1);
    
    boolean mr(final EntityPlayerSP p0);
    
    boolean mf(final KeyBinding p0);
    
    float mb(final float p0);
    
    float mw(final float p0);
    
    void mg(final PlayerCapabilities p0, final float p1);
    
    UUID mu(final EntityPlayerSP p0);
    
    boolean mz(final UUID p0, final Object p1);
    
    UUID mc(final Entity p0);
    
    ScriptEngine ma(final ScriptEngineManager p0, final String p1);
    
    Object mm(final ScriptEngine p0, final String p1);
    
    void mh(final ScriptEngine p0, final String p1, final Object p2);
    
    Object hd(final Invocable p0, final String p1, final Object[] p2);
    
    void hs(final ItemStack p0, final int p1);
    
    MapData hl(final ItemMap p0, final ItemStack p1, final World p2);
    
    Tessellator hy();
    
    BufferBuilder hx(final Tessellator p0);
    
    void hk(final BufferBuilder p0, final int p1, final VertexFormat p2);
    
    BufferBuilder hq(final BufferBuilder p0, final double p1, final double p2, final double p3);
    
    BufferBuilder hv(final BufferBuilder p0, final double p1, final double p2);
    
    void hj(final BufferBuilder p0);
    
    void he(final Tessellator p0);
    
    MapItemRenderer ho(final EntityRenderer p0);
    
    void ht(final MapItemRenderer p0, final MapData p1, final boolean p2);
    
    void hn();
    
    String hi(final ArrayList p0);
    
    boolean hp(final EntityPlayerSP p0);
    
    float hr(final EntityPlayerSP p0);
    
    EnumFacing[] hf();
    
    EnumFacing hb(final EnumFacing p0);
    
    boolean hw(final Block p0, final IBlockState p1, final boolean p2);
    
    Vec3d hg(final Vec3d p0, final double p1, final double p2, final double p3);
    
    Vec3i hu(final EnumFacing p0);
    
    Vec3d hz(final Vec3d p0, final double p1);
    
    Vec3d hc(final Vec3d p0, final Vec3d p1);
    
    double ha(final Vec3d p0, final Vec3d p1);
    
    float hm(final float p0);
    
    EnumActionResult hh(final PlayerControllerMP p0, final EntityPlayerSP p1, final WorldClient p2, final BlockPos p3, final EnumFacing p4, final Vec3d p5, final EnumHand p6);
    
    double sdd(final double p0);
    
    Block sds(final Item p0);
    
    IBlockState sdl(final Block p0);
    
    boolean sdy(final IBlockState p0);
    
    RenderBlockOverlayEvent$OverlayType sdx(final RenderBlockOverlayEvent p0);
    
    void sdk(final RenderBlockOverlayEvent p0, final boolean p1);
    
    BlockPos sdq(final EntityPlayer p0);
    
    BlockPos sdv(final BlockPos p0, final double p1, final double p2, final double p3);
    
    List sdj(final WorldClient p0, final Class p1, final AxisAlignedBB p2);
    
    Float sde(final float p0);
    
    ChatType sdo(final SPacketChat p0);
    
    String sdt(final ChatType p0);
    
    String sdn(final EntityPlayerSP p0);
    
    String sdi(final EntityPlayerSP p0);
    
    Runtime sdp();
    
    int sdr(final Runtime p0);
    
    byte[] sdf(final MessageDigest p0, final byte[] p1);
    
    char sdb(final String p0, final int p1);
    
    int sdw(final char p0, final int p1);
    
    void sdg(final GuiIngame p0, final ChatType p1, final ITextComponent p2);
    
    void sdu(final ClientChatReceivedEvent p0, final boolean p1);
    
    String sdz(final String p0);
    
    ChatType sdc(final ClientChatReceivedEvent p0);
    
    boolean sda(final ClientChatReceivedEvent p0);
    
    void sdm(final PlayerEvent$NameFormat p0, final String p1);
    
    int sdh();
    
    void ssd(final Stream p0, final Consumer p1);
    
    int sss(final SPacketEffect p0);
    
    BlockPos ssl(final SPacketEffect p0);
    
    int ssy(final SPacketPlayerPosLook p0);
    
    String ssx(final DecimalFormat p0, final long p1);
    
    void ssk(final JButton p0, final ActionListener p1);
    
    Component ssq(final JPanel p0, final Component p1);
    
    Component ssv(final JFrame p0, final Component p1);
    
    void ssj(final JFrame p0, final boolean p1);
    
    String sse(final ActionEvent p0);
    
    void sso(final Consumer p0, final Object p1);
    
    void sst(final JFrame p0, final AWTEvent p1);
    
    void ssn(final ArrayList p0, final Consumer p1);
    
    int ssi(final int p0, final int p1);
    
    BlockPos ssp(final TileEntityChest p0);
    
    AxisAlignedBB ssr(final AxisAlignedBB p0, final AxisAlignedBB p1);
    
    Set ssf(final Map p0);
    
    boolean ssb(final EntityPlayer p0, final Potion p1);
    
    boolean ssw(final Set p0, final Object p1);
    
    FMLClientHandler ssg();
    
    NetworkManager ssu(final FMLClientHandler p0);
    
    void ssz(final EntityPlayerSP p0, final boolean p1);
    
    void ssc(final boolean p0);
    
    void ssa(final double p0, final double p1, final double p2, final double p3);
    
    void ssm(final int p0);
    
    void ssh(final double p0, final double p1, final double p2);
    
    void sld();
    
    BufferBuilder sls(final BufferBuilder p0, final float p1, final float p2, final float p3, final float p4);
    
    AxisAlignedBB sll(final IBlockState p0, final IBlockAccess p1, final BlockPos p2);
    
    AxisAlignedBB sly(final AxisAlignedBB p0, final BlockPos p1);
    
    void slx(final int p0, final int p1, final int p2, final int p3);
    
    String slk(final String p0, final String p1, final String p2);
    
    char slq(final char p0);
    
    void slv(final double p0, final double p1);
    
    boolean slj(final EntityTameable p0);
    
    EntityLivingBase sle(final EntityTameable p0);
    
    void slo(final EntityTameable p0, final boolean p1);
    
    ITextComponent slt(final EntityLivingBase p0);
    
    String sln(final ITextComponent p0);
    
    void sli(final EntityTameable p0, final String p1);
    
    boolean slp(final AbstractHorse p0);
    
    UUID slr(final AbstractHorse p0);
    
    void slf(final AbstractHorse p0, final boolean p1);
    
    void slb(final AbstractHorse p0, final String p1);
    
    void slw(final PlayerControllerMP p0, final GameType p1);
    
    double slg(final Number p0);
    
    boolean slu(final Enum p0, final Object p1);
    
    boolean slz(final int p0);
    
    float slc(final Number p0);
    
    String sla(final String p0);
    
    ItemStack slm(final EntityPlayerSP p0);
    
    double slh(final EntityPlayer p0, final BlockPos p1);
    
    RayTraceResult syd(final WorldClient p0, final Vec3d p1, final Vec3d p2);
    
    Comparator sys(final Function p0);
    
    Optional syl(final Stream p0, final Comparator p1);
    
    Object syy(final Optional p0, final Object p1);
    
    AxisAlignedBB syx(final Entity p0);
    
    NonNullList syk();
    
    boolean syq(final NonNullList p0, final Collection p1);
    
    double syv(final Entity p0, final double p1, final double p2, final double p3);
    
    float syj(final World p0, final Vec3d p1, final AxisAlignedBB p2);
    
    int sye(final EntityPlayer p0);
    
    IAttributeInstance syo(final EntityPlayer p0, final IAttribute p1);
    
    double syt(final IAttributeInstance p0);
    
    float syn(final float p0, final float p1, final float p2);
    
    int syi(final EntityLivingBase p0);
    
    IAttributeInstance syp(final EntityLivingBase p0, final IAttribute p1);
    
    EnumDifficulty syr(final WorldClient p0);
    
    int syf(final EnumDifficulty p0);
    
    boolean syb(final ArrayList p0, final Object p1);
    
    GameProfile syw(final SPacketPlayerListItem$AddPlayerData p0);
    
    SoundCategory syg(final SPacketSoundEffect p0);
    
    double syu(final SPacketSoundEffect p0);
    
    double syz(final SPacketSoundEffect p0);
    
    double syc(final SPacketSoundEffect p0);
    
    void sya(final Entity p0);
    
    List sym(final SPacketChunkData p0);
    
    int syh(final SPacketChunkData p0);
    
    int sxd(final SPacketChunkData p0);
    
    Path sxs(final Path p0, final byte[] p1, final OpenOption[] p2);
    
    Map sxl(final EntityPlayer p0);
    
    Set sxy(final Map p0);
    
    String sxx(final Potion p0);
    
    int sxk(final PotionEffect p0);
    
    Object[] sxq(final Object[] p0, final int p1);
    
    EnumFacing$Axis sxv(final EnumFacing p0);
    
    EnumFacing$AxisDirection sxj(final EnumFacing p0);
    
    void sxe(final int p0);
    
    long sxo(final WorldClient p0);
    
    LocalTime sxt(final int p0, final int p1);
    
    LocalTime sxn();
    
    void sxi(final float p0, final float p1, final float p2, final float p3);
    
    int sxp(final LocalTime p0);
    
    int sxr(final LocalTime p0);
    
    int sxf(final LocalTime p0);
    
    RenderGameOverlayEvent$ElementType[] sxb();
    
    StringBuilder sxw(final StringBuilder p0);
    
    int sxg();
    
    int sxu();
    
    EntityPlayer sxz(final AttackEntityEvent p0);
    
    float sxc(final EntityPlayer p0);
    
    double sxa(final double p0);
    
    EntityLivingBase sxm(final LivingEvent$LivingUpdateEvent p0);
    
    boolean sxh(final EntityPlayer p0);
    
    double skd(final WorldBorder p0);
    
    double sks(final WorldBorder p0);
    
    double skl(final WorldBorder p0);
    
    double sky(final WorldBorder p0);
    
    void skx(final Runtime p0, final Thread p1);
    
    void skk(final String p0);
    
    int skq(final Set p0);
    
    void skv(final ArrayList p0);
    
    Set skj(final Map p0);
    
    boolean ske(final Map p0, final Object p1);
    
    String sko(final Vec3d p0);
    
    SystemTray skt();
    
    void skn(final TrayIcon p0, final boolean p1);
    
    void ski(final TrayIcon p0, final String p1);
    
    void skp(final SystemTray p0, final TrayIcon p1);
    
    void skr(final TrayIcon p0, final String p1, final String p2, final TrayIcon.MessageType p3);
    
    void skf(final Frame p0, final boolean p1);
    
    void skb(final Frame p0, final int p1);
    
    Icon skw(final Object p0);
    
    Object skg(final Component p0, final Object p1, final String p2, final int p3, final Icon p4, final Object[] p5, final Object p6);
    
    void sku(final FileWriter p0, final String p1);
    
    void skz(final float p0, final float p1, final float p2);
    
    void skc(final EntityRenderer p0);
    
    void ska(final ClientChatEvent p0, final String p1);
    
    void skm(final BufferedWriter p0);
    
    void skh(final BufferedWriter p0);
    
    boolean sqd(final ArrayList p0, final Object p1);
    
    String sqs(final EntityPlayer p0);
    
    void sql(final EntityPlayerSP p0, final float p1);
    
    void sqy();
    
    void sqx(final int p0);
    
    void sqk();
    
    void sqq(final RenderManager p0, final float p1);
    
    void sqv(final RenderManager p0, final boolean p1);
    
    void sqj(final RenderManager p0, final Entity p1, final double p2, final double p3, final double p4, final float p5, final float p6, final boolean p7);
    
    void sqe(final int p0);
    
    void sqo(final int p0);
    
    void sqt();
    
    void sqn(final EntityPlayerSP p0);
    
    FoodStats sqi(final EntityPlayerSP p0);
    
    void sqp(final FoodStats p0, final int p1);
}
