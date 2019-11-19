package j.v.bj.y.k;

import j.v.bj.y.k.d.u;
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

public class j
{
    public static r \ud83e\udd14;
    
    public static Enum d(final Class clazz, final String s) {
        return j.\ud83e\udd14.d(clazz, s);
    }
    
    public static Iterator s(final ArrayList list) {
        return j.\ud83e\udd14.s(list);
    }
    
    public static boolean l(final Iterator iterator) {
        return j.\ud83e\udd14.l(iterator);
    }
    
    public static Object y(final Iterator iterator) {
        return j.\ud83e\udd14.y(iterator);
    }
    
    public static Object x(final Map map, final Object o, final Object o2) {
        return j.\ud83e\udd14.x(map, o, o2);
    }
    
    public static boolean k(final String s, final Object o) {
        return j.\ud83e\udd14.k(s, o);
    }
    
    public static boolean q(final ArrayList list, final Object o) {
        return j.\ud83e\udd14.q(list, o);
    }
    
    public static boolean v(final ArrayList list, final Collection collection) {
        return j.\ud83e\udd14.v(list, collection);
    }
    
    public static void j(final List list) {
        j.\ud83e\udd14.j(list);
    }
    
    public static void e(final boolean b) {
        j.\ud83e\udd14.e(b);
    }
    
    public static void o(final GuiTextField guiTextField, final Predicate predicate) {
        j.\ud83e\udd14.o(guiTextField, predicate);
    }
    
    public static void t(final GuiTextField guiTextField, final int n) {
        j.\ud83e\udd14.t(guiTextField, n);
    }
    
    public static String n(final ITextComponent textComponent) {
        return j.\ud83e\udd14.n(textComponent);
    }
    
    public static void i(final GuiTextField guiTextField, final String s) {
        j.\ud83e\udd14.i(guiTextField, s);
    }
    
    public static boolean p(final List list, final Object o) {
        return j.\ud83e\udd14.p(list, o);
    }
    
    public static Object r(final List list, final int n) {
        return j.\ud83e\udd14.r(list, n);
    }
    
    public static void f(final GuiTextField guiTextField, final boolean b) {
        j.\ud83e\udd14.f(guiTextField, b);
    }
    
    public static void b(final TileEntitySign tileEntitySign, final boolean b) {
        j.\ud83e\udd14.b(tileEntitySign, b);
    }
    
    public static void w(final List list, final Consumer consumer) {
        j.\ud83e\udd14.w(list, consumer);
    }
    
    public static boolean g(final GuiTextField guiTextField) {
        return j.\ud83e\udd14.g(guiTextField);
    }
    
    public static String u(final GuiTextField guiTextField) {
        return j.\ud83e\udd14.u(guiTextField);
    }
    
    public static String z(final String s, final Object[] array) {
        return j.\ud83e\udd14.z(s, array);
    }
    
    public static void c(final float n, final float n2, final float n3, final float n4) {
        j.\ud83e\udd14.c(n, n2, n3, n4);
    }
    
    public static void a() {
        j.\ud83e\udd14.a();
    }
    
    public static void m(final float n, final float n2, final float n3) {
        j.\ud83e\udd14.m(n, n2, n3);
    }
    
    public static void h(final float n, final float n2, final float n3) {
        j.\ud83e\udd14.h(n, n2, n3);
    }
    
    public static void sd(final float n, final float n2, final float n3, final float n4) {
        j.\ud83e\udd14.sd(n, n2, n3, n4);
    }
    
    public static Block ss(final TileEntitySign tileEntitySign) {
        return j.\ud83e\udd14.ss(tileEntitySign);
    }
    
    public static int sl(final TileEntitySign tileEntitySign) {
        return j.\ud83e\udd14.sl(tileEntitySign);
    }
    
    public static void sy(final TileEntityRendererDispatcher tileEntityRendererDispatcher, final TileEntity tileEntity, final double n, final double n2, final double n3, final float n4) {
        j.\ud83e\udd14.sy(tileEntityRendererDispatcher, tileEntity, n, n2, n3, n4);
    }
    
    public static void sx() {
        j.\ud83e\udd14.sx();
    }
    
    public static void sk(final TileEntitySign tileEntitySign) {
        j.\ud83e\udd14.sk(tileEntitySign);
    }
    
    public static void sq(final Minecraft minecraft, final GuiScreen guiScreen) {
        j.\ud83e\udd14.sq(minecraft, guiScreen);
    }
    
    public static NetHandlerPlayClient sv(final Minecraft minecraft) {
        return j.\ud83e\udd14.sv(minecraft);
    }
    
    public static BlockPos sj(final TileEntitySign tileEntitySign) {
        return j.\ud83e\udd14.sj(tileEntitySign);
    }
    
    public static void se(final NetHandlerPlayClient netHandlerPlayClient, final Packet packet) {
        j.\ud83e\udd14.se(netHandlerPlayClient, packet);
    }
    
    public static int so(final FontRenderer fontRenderer, final String s) {
        return j.\ud83e\udd14.so(fontRenderer, s);
    }
    
    public static char[] st(final String s) {
        return j.\ud83e\udd14.st(s);
    }
    
    public static boolean sn(final char c) {
        return j.\ud83e\udd14.sn(c);
    }
    
    public static int si(final GuiTextField guiTextField) {
        return j.\ud83e\udd14.si(guiTextField);
    }
    
    public static boolean sp(final GuiTextField guiTextField, final char c, final int n) {
        return j.\ud83e\udd14.sp(guiTextField, c, n);
    }
    
    public static boolean sr(final GuiTextField guiTextField, final int n, final int n2, final int n3) {
        return j.\ud83e\udd14.sr(guiTextField, n, n2, n3);
    }
    
    public static void sf(final EventBus eventBus, final Object o) {
        j.\ud83e\udd14.sf(eventBus, o);
    }
    
    public static ListenableFuture sb(final Minecraft minecraft, final Runnable runnable) {
        return j.\ud83e\udd14.sb(minecraft, runnable);
    }
    
    public static String sw(final SimpleDateFormat simpleDateFormat, final Date date) {
        return j.\ud83e\udd14.sw(simpleDateFormat, date);
    }
    
    public static Iterator sg(final List list) {
        return j.\ud83e\udd14.sg(list);
    }
    
    public static String su(final Enum enum1) {
        return j.\ud83e\udd14.su(enum1);
    }
    
    public static void sz(final PrintWriter printWriter, final String s) {
        j.\ud83e\udd14.sz(printWriter, s);
    }
    
    public static void sc(final PrintWriter printWriter) {
        j.\ud83e\udd14.sc(printWriter);
    }
    
    public static int sa(final Integer n) {
        return j.\ud83e\udd14.sa(n);
    }
    
    public static int sm(final ItemStack itemStack) {
        return j.\ud83e\udd14.sm(itemStack);
    }
    
    public static int sh(final ItemStack itemStack) {
        return j.\ud83e\udd14.sh(itemStack);
    }
    
    public static double ld(final double n) {
        return j.\ud83e\udd14.ld(n);
    }
    
    public static int ls() {
        return j.\ud83e\udd14.ls();
    }
    
    public static List ll(final NonNullList list, final int n, final int n2) {
        return j.\ud83e\udd14.ll(list, n, n2);
    }
    
    public static Minecraft ly() {
        return j.\ud83e\udd14.ly();
    }
    
    public static List lx(final Object[] array) {
        return j.\ud83e\udd14.lx(array);
    }
    
    public static StringBuilder lk(final StringBuilder sb, final String s) {
        return j.\ud83e\udd14.lk(sb, s);
    }
    
    public static String lq(final StringBuilder sb) {
        return j.\ud83e\udd14.lq(sb);
    }
    
    public static String[] lv(final String s, final String s2) {
        return j.\ud83e\udd14.lv(s, s2);
    }
    
    public static String lj(final String s) {
        return j.\ud83e\udd14.lj(s);
    }
    
    public static Object le(final Map map, final Object o, final Object o2) {
        return j.\ud83e\udd14.le(map, o, o2);
    }
    
    public static Stream lo(final List list) {
        return j.\ud83e\udd14.lo(list);
    }
    
    public static Stream lt(final Stream stream, final java.util.function.Predicate predicate) {
        return j.\ud83e\udd14.lt(stream, predicate);
    }
    
    public static Collector ln() {
        return j.\ud83e\udd14.ln();
    }
    
    public static Object li(final Stream stream, final Collector collector) {
        return j.\ud83e\udd14.li(stream, collector);
    }
    
    public static int lp(final List list) {
        return j.\ud83e\udd14.lp(list);
    }
    
    public static BlockPos lr(final TileEntity tileEntity) {
        return j.\ud83e\udd14.lr(tileEntity);
    }
    
    public static boolean lf(final Boolean b) {
        return j.\ud83e\udd14.lf(b);
    }
    
    public static boolean lb(final Set set, final Object o) {
        return j.\ud83e\udd14.lb(set, o);
    }
    
    public static int lw(final BlockPos blockPos) {
        return j.\ud83e\udd14.lw(blockPos);
    }
    
    public static int lg(final BlockPos blockPos) {
        return j.\ud83e\udd14.lg(blockPos);
    }
    
    public static int lu(final BlockPos blockPos) {
        return j.\ud83e\udd14.lu(blockPos);
    }
    
    public static double lz(final EntityPlayerSP entityPlayerSP, final double n, final double n2, final double n3) {
        return j.\ud83e\udd14.lz(entityPlayerSP, n, n2, n3);
    }
    
    public static double lc(final Double n) {
        return j.\ud83e\udd14.lc(n);
    }
    
    public static Item la(final int n) {
        return j.\ud83e\udd14.la(n);
    }
    
    public static void lm(final Set set) {
        j.\ud83e\udd14.lm(set);
    }
    
    public static ItemStack lh(final InventoryPlayer inventoryPlayer, final int n) {
        return j.\ud83e\udd14.lh(inventoryPlayer, n);
    }
    
    public static Item yd(final ItemStack itemStack) {
        return j.\ud83e\udd14.yd(itemStack);
    }
    
    public static boolean ys(final Object o, final Object o2) {
        return j.\ud83e\udd14.ys(o, o2);
    }
    
    public static BlockPos yl(final RayTraceResult rayTraceResult) {
        return j.\ud83e\udd14.yl(rayTraceResult);
    }
    
    public static BlockPos yy(final BlockPos blockPos, final EnumFacing enumFacing) {
        return j.\ud83e\udd14.yy(blockPos, enumFacing);
    }
    
    public static boolean yx(final Set set, final Object o) {
        return j.\ud83e\udd14.yx(set, o);
    }
    
    public static CharSource yk(final File file, final Charset charset) {
        return j.\ud83e\udd14.yk(file, charset);
    }
    
    public static String yq(final CharSource charSource) {
        return j.\ud83e\udd14.yq(charSource);
    }
    
    public static boolean yv(final String s) {
        return j.\ud83e\udd14.yv(s);
    }
    
    public static String yj(final String s) {
        return j.\ud83e\udd14.yj(s);
    }
    
    public static void ye(final PrintStream printStream, final String s) {
        j.\ud83e\udd14.ye(printStream, s);
    }
    
    public static void yo(final AuthenticationException ex) {
        j.\ud83e\udd14.yo(ex);
    }
    
    public static String yt(final AuthenticationException ex) {
        return j.\ud83e\udd14.yt(ex);
    }
    
    public static void yn(final Exception ex) {
        j.\ud83e\udd14.yn(ex);
    }
    
    public static void yi(final GuiTextField guiTextField) {
        j.\ud83e\udd14.yi(guiTextField);
    }
    
    public static void yp(final GuiTextField guiTextField) {
        j.\ud83e\udd14.yp(guiTextField);
    }
    
    public static boolean yr(final File file) {
        return j.\ud83e\udd14.yr(file);
    }
    
    public static String yf(final File file) {
        return j.\ud83e\udd14.yf(file);
    }
    
    public static Path yb(final String s, final String[] array) {
        return j.\ud83e\udd14.yb(s, array);
    }
    
    public static byte[] yw(final Path path) {
        return j.\ud83e\udd14.yw(path);
    }
    
    public static void yg(final Timer timer) {
        j.\ud83e\udd14.yg(timer);
    }
    
    public static String yu(final Exception ex) {
        return j.\ud83e\udd14.yu(ex);
    }
    
    public static void yz(final Timer timer) {
        j.\ud83e\udd14.yz(timer);
    }
    
    public static int yc(final Random random, final int n) {
        return j.\ud83e\udd14.yc(random, n);
    }
    
    public static StringBuilder ya(final StringBuilder sb, final char c) {
        return j.\ud83e\udd14.ya(sb, c);
    }
    
    public static String ym(final char c, final int n) {
        return j.\ud83e\udd14.ym(c, n);
    }
    
    public static void yh(final EntityPlayerSP entityPlayerSP, final String s) {
        j.\ud83e\udd14.yh(entityPlayerSP, s);
    }
    
    public static float xd(final EntityPlayerSP entityPlayerSP, final Entity entity) {
        return j.\ud83e\udd14.xd(entityPlayerSP, entity);
    }
    
    public static float xs(final EntityLivingBase entityLivingBase) {
        return j.\ud83e\udd14.xs(entityLivingBase);
    }
    
    public static ItemStack xl(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.xl(entityPlayerSP);
    }
    
    public static void xy(final PlayerControllerMP playerControllerMP, final EntityPlayer entityPlayer, final Entity entity) {
        j.\ud83e\udd14.xy(playerControllerMP, entityPlayer, entity);
    }
    
    public static void xx(final EntityPlayerSP entityPlayerSP, final EnumHand enumHand) {
        j.\ud83e\udd14.xx(entityPlayerSP, enumHand);
    }
    
    public static String xk(final String s) {
        return j.\ud83e\udd14.xk(s);
    }
    
    public static String xq(final String s) {
        return j.\ud83e\udd14.xq(s);
    }
    
    public static int xv(final String s) {
        return j.\ud83e\udd14.xv(s);
    }
    
    public static String xj(final int n) {
        return j.\ud83e\udd14.xj(n);
    }
    
    public static HashFunction xe() {
        return j.\ud83e\udd14.xe();
    }
    
    public static HashCode xo(final HashFunction hashFunction, final CharSequence charSequence, final Charset charset) {
        return j.\ud83e\udd14.xo(hashFunction, charSequence, charset);
    }
    
    public static String xt(final HashCode hashCode) {
        return j.\ud83e\udd14.xt(hashCode);
    }
    
    public static HashFunction xn() {
        return j.\ud83e\udd14.xn();
    }
    
    public static boolean xi(final String s, final String s2) {
        return j.\ud83e\udd14.xi(s, s2);
    }
    
    public static boolean xp(final Random random) {
        return j.\ud83e\udd14.xp(random);
    }
    
    public static void xr(final Logger logger, final String s) {
        j.\ud83e\udd14.xr(logger, s);
    }
    
    public static UUID xf(final String s) {
        return j.\ud83e\udd14.xf(s);
    }
    
    public static String xb(final UUID uuid) {
        return j.\ud83e\udd14.xb(uuid);
    }
    
    public static void xw(final PrintStream printStream, final String s) {
        j.\ud83e\udd14.xw(printStream, s);
    }
    
    public static void xg(final EntityOtherPlayerMP entityOtherPlayerMP, final Entity entity) {
        j.\ud83e\udd14.xg(entityOtherPlayerMP, entity);
    }
    
    public static NBTTagCompound xu(final EntityPlayerSP entityPlayerSP, final NBTTagCompound nbtTagCompound) {
        return j.\ud83e\udd14.xu(entityPlayerSP, nbtTagCompound);
    }
    
    public static void xz(final EntityOtherPlayerMP entityOtherPlayerMP, final NBTTagCompound nbtTagCompound) {
        j.\ud83e\udd14.xz(entityOtherPlayerMP, nbtTagCompound);
    }
    
    public static Entity xc(final WorldClient worldClient, final int n) {
        return j.\ud83e\udd14.xc(worldClient, n);
    }
    
    public static void xa(final WorldClient worldClient, final int n, final Entity entity) {
        j.\ud83e\udd14.xa(worldClient, n, entity);
    }
    
    public static String xm(final Exception ex) {
        return j.\ud83e\udd14.xm(ex);
    }
    
    public static Toolkit xh() {
        return j.\ud83e\udd14.xh();
    }
    
    public static Dimension kd(final Toolkit toolkit) {
        return j.\ud83e\udd14.kd(toolkit);
    }
    
    public static void ks(final JFrame frame, final int n, final int n2) {
        j.\ud83e\udd14.ks(frame, n, n2);
    }
    
    public static void kl(final JFrame frame, final Container container) {
        j.\ud83e\udd14.kl(frame, container);
    }
    
    public static void ky(final JFrame frame) {
        j.\ud83e\udd14.ky(frame);
    }
    
    public static void kx(final JFrame frame, final boolean b) {
        j.\ud83e\udd14.kx(frame, b);
    }
    
    public static void kk(final JTextField textField, final ActionListener actionListener) {
        j.\ud83e\udd14.kk(textField, actionListener);
    }
    
    public static ITextComponent kq(final ClientChatReceivedEvent clientChatReceivedEvent) {
        return j.\ud83e\udd14.kq(clientChatReceivedEvent);
    }
    
    public static void kv(final JTextArea textArea, final String s) {
        j.\ud83e\udd14.kv(textArea, s);
    }
    
    public static JScrollBar kj(final JScrollPane scrollPane) {
        return j.\ud83e\udd14.kj(scrollPane);
    }
    
    public static int ke(final JScrollBar scrollBar) {
        return j.\ud83e\udd14.ke(scrollBar);
    }
    
    public static void ko(final JScrollBar scrollBar, final int n) {
        j.\ud83e\udd14.ko(scrollBar, n);
    }
    
    public static void kt(final JTextArea textArea, final String s) {
        j.\ud83e\udd14.kt(textArea, s);
    }
    
    public static void kn(final JFrame frame) {
        j.\ud83e\udd14.kn(frame);
    }
    
    public static void ki(final JPanel panel, final LayoutManager layoutManager) {
        j.\ud83e\udd14.ki(panel, layoutManager);
    }
    
    public static Border kp() {
        return j.\ud83e\udd14.kp();
    }
    
    public static TitledBorder kr(final Border border, final String s) {
        return j.\ud83e\udd14.kr(border, s);
    }
    
    public static void kf(final JPanel panel, final Border border) {
        j.\ud83e\udd14.kf(panel, border);
    }
    
    public static void kb(final JPanel panel, final Component component, final Object o) {
        j.\ud83e\udd14.kb(panel, component, o);
    }
    
    public static void kw(final JSplitPane splitPane, final Component component) {
        j.\ud83e\udd14.kw(splitPane, component);
    }
    
    public static void kg(final JScrollPane scrollPane, final int n) {
        j.\ud83e\udd14.kg(scrollPane, n);
    }
    
    public static void ku(final JScrollPane scrollPane, final int n) {
        j.\ud83e\udd14.ku(scrollPane, n);
    }
    
    public static void kz(final JTextArea textArea, final boolean b) {
        j.\ud83e\udd14.kz(textArea, b);
    }
    
    public static void kc(final JScrollPane scrollPane, final Component component) {
        j.\ud83e\udd14.kc(scrollPane, component);
    }
    
    public static void ka(final JSplitPane splitPane, final Component component) {
        j.\ud83e\udd14.ka(splitPane, component);
    }
    
    public static String km(final JTextField textField) {
        return j.\ud83e\udd14.km(textField);
    }
    
    public static boolean kh(final String s, final String s2) {
        return j.\ud83e\udd14.kh(s, s2);
    }
    
    public static void qd(final JTextField textField, final String s) {
        j.\ud83e\udd14.qd(textField, s);
    }
    
    public static long qs(final double n) {
        return j.\ud83e\udd14.qs(n);
    }
    
    public static ItemStack ql(final InventoryPlayer inventoryPlayer) {
        return j.\ud83e\udd14.ql(inventoryPlayer);
    }
    
    public static boolean qy(final ItemStack itemStack) {
        return j.\ud83e\udd14.qy(itemStack);
    }
    
    public static List qx(final ItemStack itemStack, final EntityPlayer entityPlayer, final ITooltipFlag tooltipFlag) {
        return j.\ud83e\udd14.qx(itemStack, entityPlayer, tooltipFlag);
    }
    
    public static NBTTagCompound qk(final ItemStack itemStack) {
        return j.\ud83e\udd14.qk(itemStack);
    }
    
    public static Set qq(final NBTTagCompound nbtTagCompound) {
        return j.\ud83e\udd14.qq(nbtTagCompound);
    }
    
    public static Iterator qv(final Set set) {
        return j.\ud83e\udd14.qv(set);
    }
    
    public static NBTBase qj(final NBTTagCompound nbtTagCompound, final String s) {
        return j.\ud83e\udd14.qj(nbtTagCompound, s);
    }
    
    public static String qe(final NBTBase nbtBase) {
        return j.\ud83e\udd14.qe(nbtBase);
    }
    
    public static int qo(final Color color) {
        return j.\ud83e\udd14.qo(color);
    }
    
    public static int qt(final FontRenderer fontRenderer, final String s, final int n, final int n2, final int n3) {
        return j.\ud83e\udd14.qt(fontRenderer, s, n, n2, n3);
    }
    
    public static Class qn(final Object o) {
        return j.\ud83e\udd14.qn(o);
    }
    
    public static Object[] qi(final Class clazz) {
        return j.\ud83e\udd14.qi(clazz);
    }
    
    public static Object qp(final Class clazz, final Object o) {
        return j.\ud83e\udd14.qp(clazz, o);
    }
    
    public static BlockPos qr(final TileEntityHopper tileEntityHopper) {
        return j.\ud83e\udd14.qr(tileEntityHopper);
    }
    
    public static double qf(final BlockPos blockPos, final int n, final int n2, final int n3) {
        return j.\ud83e\udd14.qf(blockPos, n, n2, n3);
    }
    
    public static BlockPos qb(final BlockPos blockPos, final int n, final int n2, final int n3) {
        return j.\ud83e\udd14.qb(blockPos, n, n2, n3);
    }
    
    public static void qw(final PrintStream printStream, final Object o) {
        j.\ud83e\udd14.qw(printStream, o);
    }
    
    public static Boolean qg(final boolean b) {
        return j.\ud83e\udd14.qg(b);
    }
    
    public static SPacketPlayerListItem$Action qu(final SPacketPlayerListItem sPacketPlayerListItem) {
        return j.\ud83e\udd14.qu(sPacketPlayerListItem);
    }
    
    public static Collection qz(final NetHandlerPlayClient netHandlerPlayClient) {
        return j.\ud83e\udd14.qz(netHandlerPlayClient);
    }
    
    public static int qc(final Collection collection) {
        return j.\ud83e\udd14.qc(collection);
    }
    
    public static List qa(final SPacketPlayerListItem sPacketPlayerListItem) {
        return j.\ud83e\udd14.qa(sPacketPlayerListItem);
    }
    
    public static ITextComponent qm(final SPacketPlayerListItem$AddPlayerData sPacketPlayerListItem$AddPlayerData) {
        return j.\ud83e\udd14.qm(sPacketPlayerListItem$AddPlayerData);
    }
    
    public static StringBuilder qh(final StringBuilder sb, final Object o) {
        return j.\ud83e\udd14.qh(sb, o);
    }
    
    public static String vd(final BufferedReader bufferedReader) {
        return j.\ud83e\udd14.vd(bufferedReader);
    }
    
    public static void vs(final BufferedReader bufferedReader) {
        j.\ud83e\udd14.vs(bufferedReader);
    }
    
    public static Charset vl() {
        return j.\ud83e\udd14.vl();
    }
    
    public static Object[] vy(final Set set) {
        return j.\ud83e\udd14.vy(set);
    }
    
    public static String vx(final Object o) {
        return j.\ud83e\udd14.vx(o);
    }
    
    public static String vk(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.vk(entityPlayer);
    }
    
    public static IBlockState vq(final WorldClient worldClient, final BlockPos blockPos) {
        return j.\ud83e\udd14.vq(worldClient, blockPos);
    }
    
    public static Material vv(final IBlockState blockState) {
        return j.\ud83e\udd14.vv(blockState);
    }
    
    public static boolean vj(final Material material) {
        return j.\ud83e\udd14.vj(material);
    }
    
    public static boolean ve(final EntityPlayerSP entityPlayerSP, final Object o) {
        return j.\ud83e\udd14.ve(entityPlayerSP, o);
    }
    
    public static void vo(final EntityPlayerSP entityPlayerSP, final float n) {
        j.\ud83e\udd14.vo(entityPlayerSP, n);
    }
    
    public static float vt(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.vt(entityPlayerSP);
    }
    
    public static void vn(final Minecraft minecraft) {
        j.\ud83e\udd14.vn(minecraft);
    }
    
    public static BlockPos vi(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.vi(entityPlayerSP);
    }
    
    public static Chunk vp(final WorldClient worldClient, final BlockPos blockPos) {
        return j.\ud83e\udd14.vp(worldClient, blockPos);
    }
    
    public static BiomeProvider vr(final WorldClient worldClient) {
        return j.\ud83e\udd14.vr(worldClient);
    }
    
    public static Biome vf(final Chunk chunk, final BlockPos blockPos, final BiomeProvider biomeProvider) {
        return j.\ud83e\udd14.vf(chunk, blockPos, biomeProvider);
    }
    
    public static String vb(final Biome biome) {
        return j.\ud83e\udd14.vb(biome);
    }
    
    public static Vec3d vw(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.vw(entityPlayerSP);
    }
    
    public static boolean vg(final BlockPos blockPos, final Object o) {
        return j.\ud83e\udd14.vg(blockPos, o);
    }
    
    public static List vu(final WorldClient worldClient, final Entity entity, final AxisAlignedBB axisAlignedBB) {
        return j.\ud83e\udd14.vu(worldClient, entity, axisAlignedBB);
    }
    
    public static boolean vz(final List list) {
        return j.\ud83e\udd14.vz(list);
    }
    
    public static boolean vc(final File file) {
        return j.\ud83e\udd14.vc(file);
    }
    
    public static boolean va() {
        return j.\ud83e\udd14.va();
    }
    
    public static int vm(final IntBuffer intBuffer) {
        return j.\ud83e\udd14.vm(intBuffer);
    }
    
    public static IntBuffer vh(final int n) {
        return j.\ud83e\udd14.vh(n);
    }
    
    public static void jd(final int n, final int n2) {
        j.\ud83e\udd14.jd(n, n2);
    }
    
    public static Buffer js(final IntBuffer intBuffer) {
        return j.\ud83e\udd14.js(intBuffer);
    }
    
    public static void jl(final int n) {
        j.\ud83e\udd14.jl(n);
    }
    
    public static void jy(final int n, final int n2, final int n3, final int n4, final IntBuffer intBuffer) {
        j.\ud83e\udd14.jy(n, n2, n3, n4, intBuffer);
    }
    
    public static void jx(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final IntBuffer intBuffer) {
        j.\ud83e\udd14.jx(n, n2, n3, n4, n5, n6, intBuffer);
    }
    
    public static IntBuffer jk(final IntBuffer intBuffer, final int[] array) {
        return j.\ud83e\udd14.jk(intBuffer, array);
    }
    
    public static Framebuffer jq(final Minecraft minecraft) {
        return j.\ud83e\udd14.jq(minecraft);
    }
    
    public static void jv(final Thread thread) {
        j.\ud83e\udd14.jv(thread);
    }
    
    public static ItemStack jj(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.jj(entityPlayerSP);
    }
    
    public static ItemStack je(final PlayerControllerMP playerControllerMP, final int n, final int n2, final int n3, final ClickType clickType, final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.je(playerControllerMP, n, n2, n3, clickType, entityPlayer);
    }
    
    public static Slot jo(final net.minecraft.inventory.Container container, final int n) {
        return j.\ud83e\udd14.jo(container, n);
    }
    
    public static ItemStack jt(final Slot slot) {
        return j.\ud83e\udd14.jt(slot);
    }
    
    public static void jn(final TextureManager textureManager, final ResourceLocation resourceLocation) {
        j.\ud83e\udd14.jn(textureManager, resourceLocation);
    }
    
    public static void ji(final int n) {
        j.\ud83e\udd14.ji(n);
    }
    
    public static void jp() {
        j.\ud83e\udd14.jp();
    }
    
    public static void jr(final float n, final float n2, final float n3) {
        j.\ud83e\udd14.jr(n, n2, n3);
    }
    
    public static void jf(final float n, final float n2, final float n3, final float n4) {
        j.\ud83e\udd14.jf(n, n2, n3, n4);
    }
    
    public static List jb(final FontRenderer fontRenderer, final String s, final int n) {
        return j.\ud83e\udd14.jb(fontRenderer, s, n);
    }
    
    public static void jw() {
        j.\ud83e\udd14.jw();
    }
    
    public static void jg() {
        j.\ud83e\udd14.jg();
    }
    
    public static int ju(final String s, final int n) {
        return j.\ud83e\udd14.ju(s, n);
    }
    
    public static void jz(final FontRenderer fontRenderer, final String s, final int n, final int n2, final int n3, final int n4) {
        j.\ud83e\udd14.jz(fontRenderer, s, n, n2, n3, n4);
    }
    
    public static Instant jc() {
        return j.\ud83e\udd14.jc();
    }
    
    public static Duration ja(final Temporal temporal, final Temporal temporal2) {
        return j.\ud83e\udd14.ja(temporal, temporal2);
    }
    
    public static long jm(final Duration duration) {
        return j.\ud83e\udd14.jm(duration);
    }
    
    public static String jh(final String s, final CharSequence charSequence, final CharSequence charSequence2) {
        return j.\ud83e\udd14.jh(s, charSequence, charSequence2);
    }
    
    public static boolean ed(final List list, final Collection collection) {
        return j.\ud83e\udd14.ed(list, collection);
    }
    
    public static boolean es(final List list, final Object o) {
        return j.\ud83e\udd14.es(list, o);
    }
    
    public static Vec3d el(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.el(entityPlayer);
    }
    
    public static ItemStack ey(final ItemTooltipEvent itemTooltipEvent) {
        return j.\ud83e\udd14.ey(itemTooltipEvent);
    }
    
    public static boolean ex(final ItemStack itemStack) {
        return j.\ud83e\udd14.ex(itemStack);
    }
    
    public static boolean ek(final NBTTagCompound nbtTagCompound, final String s, final int n) {
        return j.\ud83e\udd14.ek(nbtTagCompound, s, n);
    }
    
    public static NBTTagCompound eq(final NBTTagCompound nbtTagCompound) {
        return j.\ud83e\udd14.eq(nbtTagCompound);
    }
    
    public static void ev(final NBTTagCompound nbtTagCompound, final String s, final String s2) {
        j.\ud83e\udd14.ev(nbtTagCompound, s, s2);
    }
    
    public static TileEntity ej(final World world, final NBTTagCompound nbtTagCompound) {
        return j.\ud83e\udd14.ej(world, nbtTagCompound);
    }
    
    public static boolean ee(final TileEntity tileEntity, final Capability capability, final EnumFacing enumFacing) {
        return j.\ud83e\udd14.ee(tileEntity, capability, enumFacing);
    }
    
    public static List eo(final ItemTooltipEvent itemTooltipEvent) {
        return j.\ud83e\udd14.eo(itemTooltipEvent);
    }
    
    public static boolean et(final List list, final Object o) {
        return j.\ud83e\udd14.et(list, o);
    }
    
    public static ItemStack en(final RenderTooltipEvent$PostText renderTooltipEvent$PostText) {
        return j.\ud83e\udd14.en(renderTooltipEvent$PostText);
    }
    
    public static int ei(final RenderTooltipEvent$PostText renderTooltipEvent$PostText) {
        return j.\ud83e\udd14.ei(renderTooltipEvent$PostText);
    }
    
    public static int ep(final RenderTooltipEvent$PostText renderTooltipEvent$PostText) {
        return j.\ud83e\udd14.ep(renderTooltipEvent$PostText);
    }
    
    public static Object er(final TileEntity tileEntity, final Capability capability, final EnumFacing enumFacing) {
        return j.\ud83e\udd14.er(tileEntity, capability, enumFacing);
    }
    
    public static int ef(final IItemHandler itemHandler) {
        return j.\ud83e\udd14.ef(itemHandler);
    }
    
    public static int eb(final int n, final int n2) {
        return j.\ud83e\udd14.eb(n, n2);
    }
    
    public static int ew(final int n, final int n2) {
        return j.\ud83e\udd14.ew(n, n2);
    }
    
    public static List eg(final RenderTooltipEvent$PostText renderTooltipEvent$PostText) {
        return j.\ud83e\udd14.eg(renderTooltipEvent$PostText);
    }
    
    public static int eu(final ScaledResolution scaledResolution) {
        return j.\ud83e\udd14.eu(scaledResolution);
    }
    
    public static void ez() {
        j.\ud83e\udd14.ez();
    }
    
    public static void ec() {
        j.\ud83e\udd14.ec();
    }
    
    public static void ea(final float n, final float n2, final float n3) {
        j.\ud83e\udd14.ea(n, n2, n3);
    }
    
    public static TextureManager em(final Minecraft minecraft) {
        return j.\ud83e\udd14.em(minecraft);
    }
    
    public static void eh() {
        j.\ud83e\udd14.eh();
    }
    
    public static Block od(final ItemBlock itemBlock) {
        return j.\ud83e\udd14.od(itemBlock);
    }
    
    public static EnumDyeColor os(final BlockShulkerBox blockShulkerBox) {
        return j.\ud83e\udd14.os(blockShulkerBox);
    }
    
    public static int ol(final EnumDyeColor enumDyeColor) {
        return j.\ud83e\udd14.ol(enumDyeColor);
    }
    
    public static RenderItem oy(final Minecraft minecraft) {
        return j.\ud83e\udd14.oy(minecraft);
    }
    
    public static void ox() {
        j.\ud83e\udd14.ox();
    }
    
    public static void ok() {
        j.\ud83e\udd14.ok();
    }
    
    public static ItemStack oq(final IItemHandler itemHandler, final int n) {
        return j.\ud83e\udd14.oq(itemHandler, n);
    }
    
    public static void ov(final RenderItem renderItem, final ItemStack itemStack, final int n, final int n2) {
        j.\ud83e\udd14.ov(renderItem, itemStack, n, n2);
    }
    
    public static void oj(final RenderItem renderItem, final FontRenderer fontRenderer, final ItemStack itemStack, final int n, final int n2) {
        j.\ud83e\udd14.oj(renderItem, fontRenderer, itemStack, n, n2);
    }
    
    public static void oe() {
        j.\ud83e\udd14.oe();
    }
    
    public static void oo() {
        j.\ud83e\udd14.oo();
    }
    
    public static void ot(final int n, final int n2, final float n3, final float n4, final int n5, final int n6, final float n7, final float n8) {
        j.\ud83e\udd14.ot(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public static ResourceLocation on(final Item item) {
        return j.\ud83e\udd14.on(item);
    }
    
    public static boolean oi(final Class clazz) {
        return j.\ud83e\udd14.oi(clazz);
    }
    
    public static ImmutableSet op(final Object o, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6, final Object[] array) {
        return j.\ud83e\udd14.op(o, o2, o3, o4, o5, o6, array);
    }
    
    public static Stream or(final ImmutableSet set) {
        return j.\ud83e\udd14.or(set);
    }
    
    public static Stream of(final Stream stream, final Function function) {
        return j.\ud83e\udd14.of(stream, function);
    }
    
    public static Object[] ob(final Stream stream, final IntFunction intFunction) {
        return j.\ud83e\udd14.ob(stream, intFunction);
    }
    
    public static Stream ow(final Object[] array) {
        return j.\ud83e\udd14.ow(array);
    }
    
    public static void og(final BufferedImage bufferedImage, final int n, final int n2, final int n3) {
        j.\ud83e\udd14.og(bufferedImage, n, n2, n3);
    }
    
    public static void ou(final BufferedImage bufferedImage, final int n, final int n2, final int n3, final int n4, final int[] array, final int n5, final int n6) {
        j.\ud83e\udd14.ou(bufferedImage, n, n2, n3, n4, array, n5, n6);
    }
    
    public static boolean oz(final RenderedImage renderedImage, final String s, final File file) {
        return j.\ud83e\udd14.oz(renderedImage, s, file);
    }
    
    public static String oc(final File file) {
        return j.\ud83e\udd14.oc(file);
    }
    
    public static Style oa(final ITextComponent textComponent) {
        return j.\ud83e\udd14.oa(textComponent);
    }
    
    public static String om(final File file) {
        return j.\ud83e\udd14.om(file);
    }
    
    public static Style oh(final Style style, final ClickEvent clickEvent) {
        return j.\ud83e\udd14.oh(style, clickEvent);
    }
    
    public static Style td(final Style style, final Boolean b) {
        return j.\ud83e\udd14.td(style, b);
    }
    
    public static void ts(final Object o, final int n, final Object o2, final int n2, final int n3) {
        j.\ud83e\udd14.ts(o, n, o2, n2, n3);
    }
    
    public static StringBuilder tl(final StringBuilder sb, final int n) {
        return j.\ud83e\udd14.tl(sb, n);
    }
    
    public static boolean ty(final File file) {
        return j.\ud83e\udd14.ty(file);
    }
    
    public static int tx(final MouseEvent mouseEvent) {
        return j.\ud83e\udd14.tx(mouseEvent);
    }
    
    public static void tk(final NetworkManager networkManager, final Packet packet) {
        j.\ud83e\udd14.tk(networkManager, packet);
    }
    
    public static void tq(final MouseEvent mouseEvent, final boolean b) {
        j.\ud83e\udd14.tq(mouseEvent, b);
    }
    
    public static Block tv(final IBlockState blockState) {
        return j.\ud83e\udd14.tv(blockState);
    }
    
    public static Block tj(final int n) {
        return j.\ud83e\udd14.tj(n);
    }
    
    public static void te(final TileEntityShulkerBox tileEntityShulkerBox, final World world) {
        j.\ud83e\udd14.te(tileEntityShulkerBox, world);
    }
    
    public static TileEntity to(final WorldClient worldClient, final BlockPos blockPos) {
        return j.\ud83e\udd14.to(worldClient, blockPos);
    }
    
    public static NBTTagCompound tt(final TileEntity tileEntity) {
        return j.\ud83e\udd14.tt(tileEntity);
    }
    
    public static NBTTagCompound tn(final NBTTagCompound nbtTagCompound, final String s) {
        return j.\ud83e\udd14.tn(nbtTagCompound, s);
    }
    
    public static void ti(final TileEntityShulkerBox tileEntityShulkerBox, final NBTTagCompound nbtTagCompound) {
        j.\ud83e\udd14.ti(tileEntityShulkerBox, nbtTagCompound);
    }
    
    public static String tp(final NBTTagCompound nbtTagCompound) {
        return j.\ud83e\udd14.tp(nbtTagCompound);
    }
    
    public static void tr(final LaunchClassLoader launchClassLoader, final URL url) {
        j.\ud83e\udd14.tr(launchClassLoader, url);
    }
    
    public static Class tf(final LaunchClassLoader launchClassLoader, final String s) {
        return j.\ud83e\udd14.tf(launchClassLoader, s);
    }
    
    public static void tb(final HashMap hashMap) {
        j.\ud83e\udd14.tb(hashMap);
    }
    
    public static Iterable tw(final BlockPos blockPos, final BlockPos blockPos2) {
        return j.\ud83e\udd14.tw(blockPos, blockPos2);
    }
    
    public static Iterator tg(final Iterable iterable) {
        return j.\ud83e\udd14.tg(iterable);
    }
    
    public static Object tu(final HashMap hashMap, final Object o, final Object o2) {
        return j.\ud83e\udd14.tu(hashMap, o, o2);
    }
    
    public static boolean tz(final Material material) {
        return j.\ud83e\udd14.tz(material);
    }
    
    public static boolean tc(final Material material) {
        return j.\ud83e\udd14.tc(material);
    }
    
    public static Set ta(final HashMap hashMap) {
        return j.\ud83e\udd14.ta(hashMap);
    }
    
    public static Object tm(final Map.Entry entry) {
        return j.\ud83e\udd14.tm(entry);
    }
    
    public static void th(final float n) {
        j.\ud83e\udd14.th(n);
    }
    
    public static Object nd(final Map.Entry entry) {
        return j.\ud83e\udd14.nd(entry);
    }
    
    public static void ns(final AxisAlignedBB axisAlignedBB, final float n, final float n2, final float n3, final float n4) {
        j.\ud83e\udd14.ns(axisAlignedBB, n, n2, n3, n4);
    }
    
    public static void nl(final AxisAlignedBB axisAlignedBB, final float n, final float n2, final float n3, final float n4) {
        j.\ud83e\udd14.nl(axisAlignedBB, n, n2, n3, n4);
    }
    
    public static SoundHandler ny(final Minecraft minecraft) {
        return j.\ud83e\udd14.ny(minecraft);
    }
    
    public static Object nx(final Class clazz, final Object o, final String[] array) {
        return j.\ud83e\udd14.nx(clazz, o, array);
    }
    
    public static void nk(final SoundManager soundManager) {
        j.\ud83e\udd14.nk(soundManager);
    }
    
    public static SoundEvent nq(final SPacketSoundEffect sPacketSoundEffect) {
        return j.\ud83e\udd14.nq(sPacketSoundEffect);
    }
    
    public static Entity nv(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.nv(entityPlayerSP);
    }
    
    public static void nj(final EntityPlayerSP entityPlayerSP) {
        j.\ud83e\udd14.nj(entityPlayerSP);
    }
    
    public static void ne(final WorldClient worldClient, final Entity entity) {
        j.\ud83e\udd14.ne(worldClient, entity);
    }
    
    public static boolean no(final WorldClient worldClient, final Entity entity) {
        return j.\ud83e\udd14.no(worldClient, entity);
    }
    
    public static boolean nt(final EntityPlayerSP entityPlayerSP, final Entity entity) {
        return j.\ud83e\udd14.nt(entityPlayerSP, entity);
    }
    
    public static GuiScreen nn(final GuiOpenEvent guiOpenEvent) {
        return j.\ud83e\udd14.nn(guiOpenEvent);
    }
    
    public static void ni(final GuiOpenEvent guiOpenEvent, final GuiScreen guiScreen) {
        j.\ud83e\udd14.ni(guiOpenEvent, guiScreen);
    }
    
    public static Double np(final double n) {
        return j.\ud83e\udd14.np(n);
    }
    
    public static UUID nr(final GameProfile gameProfile) {
        return j.\ud83e\udd14.nr(gameProfile);
    }
    
    public static EntityPlayer nf(final WorldClient worldClient, final UUID uuid) {
        return j.\ud83e\udd14.nf(worldClient, uuid);
    }
    
    public static AxisAlignedBB nb(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.nb(entityPlayer);
    }
    
    public static Object nw(final HashMap hashMap, final Object o) {
        return j.\ud83e\udd14.nw(hashMap, o);
    }
    
    public static Object ng(final HashMap hashMap, final Object o) {
        return j.\ud83e\udd14.ng(hashMap, o);
    }
    
    public static String nu(final String s, final Object[] array) {
        return j.\ud83e\udd14.nu(s, array);
    }
    
    public static int nz(final HashMap hashMap) {
        return j.\ud83e\udd14.nz(hashMap);
    }
    
    public static void nc(final HashMap hashMap, final BiConsumer biConsumer) {
        j.\ud83e\udd14.nc(hashMap, biConsumer);
    }
    
    public static Vec3d na(final AxisAlignedBB axisAlignedBB) {
        return j.\ud83e\udd14.na(axisAlignedBB);
    }
    
    public static double nm(final EntityPlayerSP entityPlayerSP, final double n, final double n2, final double n3) {
        return j.\ud83e\udd14.nm(entityPlayerSP, n, n2, n3);
    }
    
    public static RenderManager nh(final Minecraft minecraft) {
        return j.\ud83e\udd14.nh(minecraft);
    }
    
    public static Vec3d id(final Vec3d vec3d, final Vec3d vec3d2) {
        return j.\ud83e\udd14.id(vec3d, vec3d2);
    }
    
    public static Vec3d is(final Vec3d vec3d) {
        return j.\ud83e\udd14.is(vec3d);
    }
    
    public static double il(final double n, final double n2) {
        return j.\ud83e\udd14.il(n, n2);
    }
    
    public static void iy(final double n, final double n2, final double n3) {
        j.\ud83e\udd14.iy(n, n2, n3);
    }
    
    public static void ix(final float n, final float n2, final float n3) {
        j.\ud83e\udd14.ix(n, n2, n3);
    }
    
    public static void ik(final float n, final float n2, final float n3, final float n4) {
        j.\ud83e\udd14.ik(n, n2, n3, n4);
    }
    
    public static void iq(final double n, final double n2, final double n3) {
        j.\ud83e\udd14.iq(n, n2, n3);
    }
    
    public static void iv(final int n) {
        j.\ud83e\udd14.iv(n);
    }
    
    public static void ij(final int n) {
        j.\ud83e\udd14.ij(n);
    }
    
    public static void ie(final int n, final int n2) {
        j.\ud83e\udd14.ie(n, n2);
    }
    
    public static StringBuilder io(final StringBuilder sb, final double n) {
        return j.\ud83e\udd14.io(sb, n);
    }
    
    public static int it(final FontRenderer fontRenderer, final String s, final float n, final float n2, final int n3) {
        return j.\ud83e\udd14.it(fontRenderer, s, n, n2, n3);
    }
    
    public static int in(final Color color) {
        return j.\ud83e\udd14.in(color);
    }
    
    public static int ii(final Color color) {
        return j.\ud83e\udd14.ii(color);
    }
    
    public static int ip(final Color color) {
        return j.\ud83e\udd14.ip(color);
    }
    
    public static void ir(final ItemStack itemStack, final NBTTagCompound nbtTagCompound) {
        j.\ud83e\udd14.ir(itemStack, nbtTagCompound);
    }
    
    public static void if(final NBTTagCompound nbtTagCompound, final String s, final boolean b) {
        j.\ud83e\udd14.if(nbtTagCompound, s, b);
    }
    
    public static void ib(final NBTTagCompound nbtTagCompound, final String s, final byte b) {
        j.\ud83e\udd14.ib(nbtTagCompound, s, b);
    }
    
    public static void iw(final NBTTagCompound nbtTagCompound, final String s, final short n) {
        j.\ud83e\udd14.iw(nbtTagCompound, s, n);
    }
    
    public static void ig(final NBTTagCompound nbtTagCompound, final String s, final int n) {
        j.\ud83e\udd14.ig(nbtTagCompound, s, n);
    }
    
    public static void iu(final NBTTagCompound nbtTagCompound, final String s, final long n) {
        j.\ud83e\udd14.iu(nbtTagCompound, s, n);
    }
    
    public static void iz(final NBTTagCompound nbtTagCompound, final String s, final float n) {
        j.\ud83e\udd14.iz(nbtTagCompound, s, n);
    }
    
    public static void ic(final NBTTagCompound nbtTagCompound, final String s, final double n) {
        j.\ud83e\udd14.ic(nbtTagCompound, s, n);
    }
    
    public static void ia(final NBTTagCompound nbtTagCompound, final String s, final NBTBase nbtBase) {
        j.\ud83e\udd14.ia(nbtTagCompound, s, nbtBase);
    }
    
    public static boolean im(final NBTTagCompound nbtTagCompound, final String s) {
        return j.\ud83e\udd14.im(nbtTagCompound, s);
    }
    
    public static boolean ih(final NBTTagCompound nbtTagCompound, final String s) {
        return j.\ud83e\udd14.ih(nbtTagCompound, s);
    }
    
    public static byte pd(final NBTTagCompound nbtTagCompound, final String s) {
        return j.\ud83e\udd14.pd(nbtTagCompound, s);
    }
    
    public static short ps(final NBTTagCompound nbtTagCompound, final String s) {
        return j.\ud83e\udd14.ps(nbtTagCompound, s);
    }
    
    public static int pl(final NBTTagCompound nbtTagCompound, final String s) {
        return j.\ud83e\udd14.pl(nbtTagCompound, s);
    }
    
    public static long py(final NBTTagCompound nbtTagCompound, final String s) {
        return j.\ud83e\udd14.py(nbtTagCompound, s);
    }
    
    public static float px(final NBTTagCompound nbtTagCompound, final String s) {
        return j.\ud83e\udd14.px(nbtTagCompound, s);
    }
    
    public static double pk(final NBTTagCompound nbtTagCompound, final String s) {
        return j.\ud83e\udd14.pk(nbtTagCompound, s);
    }
    
    public static String pq(final NBTTagCompound nbtTagCompound, final String s) {
        return j.\ud83e\udd14.pq(nbtTagCompound, s);
    }
    
    public static NBTTagList pv(final NBTTagCompound nbtTagCompound, final String s, final int n) {
        return j.\ud83e\udd14.pv(nbtTagCompound, s, n);
    }
    
    public static InputStream pj(final Class clazz, final String s) {
        return j.\ud83e\udd14.pj(clazz, s);
    }
    
    public static Set pe(final Object o) {
        return j.\ud83e\udd14.pe(o);
    }
    
    public static long po() {
        return j.\ud83e\udd14.po();
    }
    
    public static void pt(final GuiOpenEvent guiOpenEvent, final boolean b) {
        j.\ud83e\udd14.pt(guiOpenEvent, b);
    }
    
    public static String[] pn(final CPacketUpdateSign cPacketUpdateSign) {
        return j.\ud83e\udd14.pn(cPacketUpdateSign);
    }
    
    public static String pi(final String s, final int n, final int n2) {
        return j.\ud83e\udd14.pi(s, n, n2);
    }
    
    public static void pp(final Class clazz, final Object o, final Object o2, final String[] array) {
        j.\ud83e\udd14.pp(clazz, o, o2, array);
    }
    
    public static IntStream pr(final Random random, final int n, final int n2) {
        return j.\ud83e\udd14.pr(random, n, n2);
    }
    
    public static IntStream pf(final IntStream intStream, final IntUnaryOperator intUnaryOperator) {
        return j.\ud83e\udd14.pf(intStream, intUnaryOperator);
    }
    
    public static IntStream pb(final IntStream intStream, final long n) {
        return j.\ud83e\udd14.pb(intStream, n);
    }
    
    public static Stream pw(final IntStream intStream, final IntFunction intFunction) {
        return j.\ud83e\udd14.pw(intStream, intFunction);
    }
    
    public static Collector pg() {
        return j.\ud83e\udd14.pg();
    }
    
    public static String pu(final char c) {
        return j.\ud83e\udd14.pu(c);
    }
    
    public static RenderGameOverlayEvent$ElementType pz(final RenderGameOverlayEvent renderGameOverlayEvent) {
        return j.\ud83e\udd14.pz(renderGameOverlayEvent);
    }
    
    public static void pc(final RenderGameOverlayEvent renderGameOverlayEvent, final boolean b) {
        j.\ud83e\udd14.pc(renderGameOverlayEvent, b);
    }
    
    public static int pa(final ScaledResolution scaledResolution) {
        return j.\ud83e\udd14.pa(scaledResolution);
    }
    
    public static float pm(final Float n) {
        return j.\ud83e\udd14.pm(n);
    }
    
    public static float ph(final RenderGameOverlayEvent renderGameOverlayEvent) {
        return j.\ud83e\udd14.ph(renderGameOverlayEvent);
    }
    
    public static boolean rd(final PlayerControllerMP playerControllerMP) {
        return j.\ud83e\udd14.rd(playerControllerMP);
    }
    
    public static boolean rs(final Block block, final IBlockState blockState) {
        return j.\ud83e\udd14.rs(block, blockState);
    }
    
    public static Entity rl(final Minecraft minecraft) {
        return j.\ud83e\udd14.rl(minecraft);
    }
    
    public static void ry(final int n) {
        j.\ud83e\udd14.ry(n);
    }
    
    public static Entity rx(final PlaySoundAtEntityEvent playSoundAtEntityEvent) {
        return j.\ud83e\udd14.rx(playSoundAtEntityEvent);
    }
    
    public static SoundEvent rk(final PlaySoundAtEntityEvent playSoundAtEntityEvent) {
        return j.\ud83e\udd14.rk(playSoundAtEntityEvent);
    }
    
    public static Vec3d rq(final Entity entity) {
        return j.\ud83e\udd14.rq(entity);
    }
    
    public static String rv(final ClientChatEvent clientChatEvent) {
        return j.\ud83e\udd14.rv(clientChatEvent);
    }
    
    public static List rj(final List list, final int n, final int n2) {
        return j.\ud83e\udd14.rj(list, n, n2);
    }
    
    public static Object[] re(final ArrayList list, final Object[] array) {
        return j.\ud83e\udd14.re(list, array);
    }
    
    public static void ro(final ClientChatEvent clientChatEvent, final boolean b) {
        j.\ud83e\udd14.ro(clientChatEvent, b);
    }
    
    public static String rt(final CPacketChatMessage cPacketChatMessage) {
        return j.\ud83e\udd14.rt(cPacketChatMessage);
    }
    
    public static RenderBlockOverlayEvent$OverlayType[] rn() {
        return j.\ud83e\udd14.rn();
    }
    
    public static int ri(final RenderBlockOverlayEvent$OverlayType renderBlockOverlayEvent$OverlayType) {
        return j.\ud83e\udd14.ri(renderBlockOverlayEvent$OverlayType);
    }
    
    public static ImmutableSetMultimap rp(final WorldClient worldClient) {
        return j.\ud83e\udd14.rp(worldClient);
    }
    
    public static ImmutableMultiset rr(final ImmutableSetMultimap immutableSetMultimap) {
        return j.\ud83e\udd14.rr(immutableSetMultimap);
    }
    
    public static UnmodifiableIterator rf(final ImmutableMultiset immutableMultiset) {
        return j.\ud83e\udd14.rf(immutableMultiset);
    }
    
    public static Chunk rb(final WorldClient worldClient, final int n, final int n2) {
        return j.\ud83e\udd14.rb(worldClient, n, n2);
    }
    
    public static Map rw(final Chunk chunk) {
        return j.\ud83e\udd14.rw(chunk);
    }
    
    public static Collection rg(final Map map) {
        return j.\ud83e\udd14.rg(map);
    }
    
    public static int ru(final Map map) {
        return j.\ud83e\udd14.ru(map);
    }
    
    public static void rz(final PrintStream printStream, final int n) {
        j.\ud83e\udd14.rz(printStream, n);
    }
    
    public static Iterator rc(final Collection collection) {
        return j.\ud83e\udd14.rc(collection);
    }
    
    public static void ra(final PrintStream printStream, final boolean b) {
        j.\ud83e\udd14.ra(printStream, b);
    }
    
    public static Integer rm(final int n) {
        return j.\ud83e\udd14.rm(n);
    }
    
    public static String rh(final boolean b) {
        return j.\ud83e\udd14.rh(b);
    }
    
    public static PrintStream fd(final PrintStream printStream, final String s, final Object[] array) {
        return j.\ud83e\udd14.fd(printStream, s, array);
    }
    
    public static ServerData fs(final Minecraft minecraft) {
        return j.\ud83e\udd14.fs(minecraft);
    }
    
    public static void fl(final BufferedWriter bufferedWriter, final String s) {
        j.\ud83e\udd14.fl(bufferedWriter, s);
    }
    
    public static void fy(final BufferedWriter bufferedWriter) {
        j.\ud83e\udd14.fy(bufferedWriter);
    }
    
    public static void fx(final FileWriter fileWriter) {
        j.\ud83e\udd14.fx(fileWriter);
    }
    
    public static BlockPos fk(final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock) {
        return j.\ud83e\udd14.fk(cPacketPlayerTryUseItemOnBlock);
    }
    
    public static EnumFacing fq(final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock) {
        return j.\ud83e\udd14.fq(cPacketPlayerTryUseItemOnBlock);
    }
    
    public static EnumHand fv(final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock) {
        return j.\ud83e\udd14.fv(cPacketPlayerTryUseItemOnBlock);
    }
    
    public static float fj(final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock) {
        return j.\ud83e\udd14.fj(cPacketPlayerTryUseItemOnBlock);
    }
    
    public static float fe(final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock) {
        return j.\ud83e\udd14.fe(cPacketPlayerTryUseItemOnBlock);
    }
    
    public static float fo(final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock) {
        return j.\ud83e\udd14.fo(cPacketPlayerTryUseItemOnBlock);
    }
    
    public static EnumHand ft(final CPacketPlayerTryUseItem cPacketPlayerTryUseItem) {
        return j.\ud83e\udd14.ft(cPacketPlayerTryUseItem);
    }
    
    public static CPacketUseEntity$Action fn(final CPacketUseEntity cPacketUseEntity) {
        return j.\ud83e\udd14.fn(cPacketUseEntity);
    }
    
    public static Entity fi(final CPacketUseEntity cPacketUseEntity, final World world) {
        return j.\ud83e\udd14.fi(cPacketUseEntity, world);
    }
    
    public static boolean fp(final HashBiMap hashBiMap, final Object o) {
        return j.\ud83e\udd14.fp(hashBiMap, o);
    }
    
    public static Object fr(final HashBiMap hashBiMap, final Object o) {
        return j.\ud83e\udd14.fr(hashBiMap, o);
    }
    
    public static boolean ff(final HashBiMap hashBiMap, final Object o) {
        return j.\ud83e\udd14.ff(hashBiMap, o);
    }
    
    public static BiMap fb(final HashBiMap hashBiMap) {
        return j.\ud83e\udd14.fb(hashBiMap);
    }
    
    public static Object fw(final BiMap biMap, final Object o) {
        return j.\ud83e\udd14.fw(biMap, o);
    }
    
    public static InputStream fg(final URL url) {
        return j.\ud83e\udd14.fg(url);
    }
    
    public static Object fu(final HashBiMap hashBiMap, final Object o, final Object o2) {
        return j.\ud83e\udd14.fu(hashBiMap, o, o2);
    }
    
    public static StringBuilder fz(final StringBuilder sb, final int n, final char c) {
        return j.\ud83e\udd14.fz(sb, n, c);
    }
    
    public static URLConnection fc(final URL url) {
        return j.\ud83e\udd14.fc(url);
    }
    
    public static void fa(final HttpsURLConnection httpsURLConnection) {
        j.\ud83e\udd14.fa(httpsURLConnection);
    }
    
    public static void fm(final HttpsURLConnection httpsURLConnection) {
        j.\ud83e\udd14.fm(httpsURLConnection);
    }
    
    public static void fh(final IOException ex) {
        j.\ud83e\udd14.fh(ex);
    }
    
    public static void bd(final long n) {
        j.\ud83e\udd14.bd(n);
    }
    
    public static HashBiMap bs() {
        return j.\ud83e\udd14.bs();
    }
    
    public static void bl(final RenderSpecificHandEvent renderSpecificHandEvent, final boolean b) {
        j.\ud83e\udd14.bl(renderSpecificHandEvent, b);
    }
    
    public static EnumHand by(final RenderSpecificHandEvent renderSpecificHandEvent) {
        return j.\ud83e\udd14.by(renderSpecificHandEvent);
    }
    
    public static float bx(final Double n) {
        return j.\ud83e\udd14.bx(n);
    }
    
    public static ITextComponent bk(final TextComponentString textComponentString, final ITextComponent textComponent) {
        return j.\ud83e\udd14.bk(textComponentString, textComponent);
    }
    
    public static void bq(final ClientChatReceivedEvent clientChatReceivedEvent, final ITextComponent textComponent) {
        j.\ud83e\udd14.bq(clientChatReceivedEvent, textComponent);
    }
    
    public static ITextComponent bv(final String s) {
        return j.\ud83e\udd14.bv(s);
    }
    
    public static Session bj(final Minecraft minecraft) {
        return j.\ud83e\udd14.bj(minecraft);
    }
    
    public static String be(final Session session) {
        return j.\ud83e\udd14.be(session);
    }
    
    public static void bo(final NBTTagList list, final NBTBase nbtBase) {
        j.\ud83e\udd14.bo(list, nbtBase);
    }
    
    public static void bt(final ItemStack itemStack, final String s, final NBTBase nbtBase) {
        j.\ud83e\udd14.bt(itemStack, s, nbtBase);
    }
    
    public static long bn(final Long n) {
        return j.\ud83e\udd14.bn(n);
    }
    
    public static Item bi(final Block block) {
        return j.\ud83e\udd14.bi(block);
    }
    
    public static boolean bp(final Object[] array, final Object o) {
        return j.\ud83e\udd14.bp(array, o);
    }
    
    public static double br(final double n) {
        return j.\ud83e\udd14.br(n);
    }
    
    public static long bf() {
        return j.\ud83e\udd14.bf();
    }
    
    public static int bb(final float n, final float n2, final float n3) {
        return j.\ud83e\udd14.bb(n, n2, n3);
    }
    
    public static String bw(final int n) {
        return j.\ud83e\udd14.bw(n);
    }
    
    public static long bg(final String s, final int n) {
        return j.\ud83e\udd14.bg(s, n);
    }
    
    public static int bu(final Color color) {
        return j.\ud83e\udd14.bu(color);
    }
    
    public static double bz(final double n, final double n2) {
        return j.\ud83e\udd14.bz(n, n2);
    }
    
    public static Clipboard bc(final Toolkit toolkit) {
        return j.\ud83e\udd14.bc(toolkit);
    }
    
    public static void ba(final Clipboard clipboard, final Transferable transferable, final ClipboardOwner clipboardOwner) {
        j.\ud83e\udd14.ba(clipboard, transferable, clipboardOwner);
    }
    
    public static float bm(final Minecraft minecraft) {
        return j.\ud83e\udd14.bm(minecraft);
    }
    
    public static Vec3d bh(final EntityPlayerSP entityPlayerSP, final float n) {
        return j.\ud83e\udd14.bh(entityPlayerSP, n);
    }
    
    public static Vec3d wd(final Entity entity, final double n) {
        return j.\ud83e\udd14.wd(entity, n);
    }
    
    public static void ws(final Minecraft minecraft, final Entity entity) {
        j.\ud83e\udd14.ws(minecraft, entity);
    }
    
    public static Object wl(final Map map, final Object o) {
        return j.\ud83e\udd14.wl(map, o);
    }
    
    public static Thread wy() {
        return j.\ud83e\udd14.wy();
    }
    
    public static boolean wx(final Thread thread) {
        return j.\ud83e\udd14.wx(thread);
    }
    
    public static boolean wk(final Minecraft minecraft) {
        return j.\ud83e\udd14.wk(minecraft);
    }
    
    public static Integer wq(final String s) {
        return j.\ud83e\udd14.wq(s);
    }
    
    public static boolean wv(final String s, final CharSequence charSequence) {
        return j.\ud83e\udd14.wv(s, charSequence);
    }
    
    public static String wj(final String s, final int n) {
        return j.\ud83e\udd14.wj(s, n);
    }
    
    public static int we(final String s) {
        return j.\ud83e\udd14.we(s);
    }
    
    public static void wo(final Throwable t) {
        j.\ud83e\udd14.wo(t);
    }
    
    public static void wt(final InterruptedException ex) {
        j.\ud83e\udd14.wt(ex);
    }
    
    public static boolean wn() {
        return j.\ud83e\udd14.wn();
    }
    
    public static int wi() {
        return j.\ud83e\udd14.wi();
    }
    
    public static String wp(final int n) {
        return j.\ud83e\udd14.wp(n);
    }
    
    public static UUID wr(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.wr(entityPlayer);
    }
    
    public static NetworkPlayerInfo wf(final NetHandlerPlayClient netHandlerPlayClient, final UUID uuid) {
        return j.\ud83e\udd14.wf(netHandlerPlayClient, uuid);
    }
    
    public static int wb(final NetworkPlayerInfo networkPlayerInfo) {
        return j.\ud83e\udd14.wb(networkPlayerInfo);
    }
    
    public static boolean ww(final File file, final File file2) {
        return j.\ud83e\udd14.ww(file, file2);
    }
    
    public static void wg(final Entity entity, final boolean b) {
        j.\ud83e\udd14.wg(entity, b);
    }
    
    public static int wu(final EntityItem entityItem) {
        return j.\ud83e\udd14.wu(entityItem);
    }
    
    public static String wz(final double n) {
        return j.\ud83e\udd14.wz(n);
    }
    
    public static void wc(final Entity entity, final String s) {
        j.\ud83e\udd14.wc(entity, s);
    }
    
    public static FloatBuffer wa(final int n) {
        return j.\ud83e\udd14.wa(n);
    }
    
    public static float wm(final FloatBuffer floatBuffer, final int n) {
        return j.\ud83e\udd14.wm(floatBuffer, n);
    }
    
    public static double wh(final double n) {
        return j.\ud83e\udd14.wh(n);
    }
    
    public static double gd(final double n) {
        return j.\ud83e\udd14.gd(n);
    }
    
    public static int gs(final IntBuffer intBuffer, final int n) {
        return j.\ud83e\udd14.gs(intBuffer, n);
    }
    
    public static double gl(final double n) {
        return j.\ud83e\udd14.gl(n);
    }
    
    public static double gy(final double n) {
        return j.\ud83e\udd14.gy(n);
    }
    
    public static double gx(final double n, final double n2) {
        return j.\ud83e\udd14.gx(n, n2);
    }
    
    public static FloatBuffer gk(final FloatBuffer floatBuffer) {
        return j.\ud83e\udd14.gk(floatBuffer);
    }
    
    public static double gq(final double n) {
        return j.\ud83e\udd14.gq(n);
    }
    
    public static double gv(final double n) {
        return j.\ud83e\udd14.gv(n);
    }
    
    public static double gj(final double n) {
        return j.\ud83e\udd14.gj(n);
    }
    
    public static double ge(final double n) {
        return j.\ud83e\udd14.ge(n);
    }
    
    public static float go(final EntityViewRenderEvent$FOVModifier entityViewRenderEvent$FOVModifier) {
        return j.\ud83e\udd14.go(entityViewRenderEvent$FOVModifier);
    }
    
    public static float gt(final float n, final float n2) {
        return j.\ud83e\udd14.gt(n, n2);
    }
    
    public static void gn(final EntityViewRenderEvent$FOVModifier entityViewRenderEvent$FOVModifier, final float n) {
        j.\ud83e\udd14.gn(entityViewRenderEvent$FOVModifier, n);
    }
    
    public static String gi(final HashMap hashMap) {
        return j.\ud83e\udd14.gi(hashMap);
    }
    
    public static Object gp(final HashMap hashMap, final Object o, final Object o2) {
        return j.\ud83e\udd14.gp(hashMap, o, o2);
    }
    
    public static GameProfile gr(final NetworkPlayerInfo networkPlayerInfo) {
        return j.\ud83e\udd14.gr(networkPlayerInfo);
    }
    
    public static String gf(final GameProfile gameProfile) {
        return j.\ud83e\udd14.gf(gameProfile);
    }
    
    public static double gb(final SPacketEntityTeleport sPacketEntityTeleport) {
        return j.\ud83e\udd14.gb(sPacketEntityTeleport);
    }
    
    public static double gw(final SPacketEntityTeleport sPacketEntityTeleport) {
        return j.\ud83e\udd14.gw(sPacketEntityTeleport);
    }
    
    public static double gg(final SPacketEntityTeleport sPacketEntityTeleport) {
        return j.\ud83e\udd14.gg(sPacketEntityTeleport);
    }
    
    public static double gu(final EntityPlayerSP entityPlayerSP, final BlockPos blockPos) {
        return j.\ud83e\udd14.gu(entityPlayerSP, blockPos);
    }
    
    public static int gz(final SPacketEntityTeleport sPacketEntityTeleport) {
        return j.\ud83e\udd14.gz(sPacketEntityTeleport);
    }
    
    public static Object gc(final Object o) {
        return j.\ud83e\udd14.gc(o);
    }
    
    public static Annotation ga(final Class clazz, final Class clazz2) {
        return j.\ud83e\udd14.ga(clazz, clazz2);
    }
    
    public static void gm(final EventBus eventBus, final Object o) {
        j.\ud83e\udd14.gm(eventBus, o);
    }
    
    public static boolean gh(final EventBus eventBus, final Event event) {
        return j.\ud83e\udd14.gh(eventBus, event);
    }
    
    public static String ud(final Throwable t) {
        return j.\ud83e\udd14.ud(t);
    }
    
    public static RenderGameOverlayEvent$ElementType us(final RenderGameOverlayEvent$Post renderGameOverlayEvent$Post) {
        return j.\ud83e\udd14.us(renderGameOverlayEvent$Post);
    }
    
    public static StringBuilder ul(final StringBuilder sb, final boolean b) {
        return j.\ud83e\udd14.ul(sb, b);
    }
    
    public static float uy(final DrawBlockHighlightEvent drawBlockHighlightEvent) {
        return j.\ud83e\udd14.uy(drawBlockHighlightEvent);
    }
    
    public static EntityPlayer ux(final DrawBlockHighlightEvent drawBlockHighlightEvent) {
        return j.\ud83e\udd14.ux(drawBlockHighlightEvent);
    }
    
    public static RayTraceResult uk(final DrawBlockHighlightEvent drawBlockHighlightEvent) {
        return j.\ud83e\udd14.uk(drawBlockHighlightEvent);
    }
    
    public static void uq() {
        j.\ud83e\udd14.uq();
    }
    
    public static void uv(final GlStateManager$SourceFactor glStateManager$SourceFactor, final GlStateManager$DestFactor glStateManager$DestFactor, final GlStateManager$SourceFactor glStateManager$SourceFactor2, final GlStateManager$DestFactor glStateManager$DestFactor2) {
        j.\ud83e\udd14.uv(glStateManager$SourceFactor, glStateManager$DestFactor, glStateManager$SourceFactor2, glStateManager$DestFactor2);
    }
    
    public static void uj(final float n) {
        j.\ud83e\udd14.uj(n);
    }
    
    public static void ue() {
        j.\ud83e\udd14.ue();
    }
    
    public static void uo(final boolean b) {
        j.\ud83e\udd14.uo(b);
    }
    
    public static WorldBorder ut(final WorldClient worldClient) {
        return j.\ud83e\udd14.ut(worldClient);
    }
    
    public static boolean un(final WorldBorder worldBorder, final BlockPos blockPos) {
        return j.\ud83e\udd14.un(worldBorder, blockPos);
    }
    
    public static AxisAlignedBB ui(final IBlockState blockState, final World world, final BlockPos blockPos) {
        return j.\ud83e\udd14.ui(blockState, world, blockPos);
    }
    
    public static AxisAlignedBB up(final AxisAlignedBB axisAlignedBB, final double n) {
        return j.\ud83e\udd14.up(axisAlignedBB, n);
    }
    
    public static AxisAlignedBB ur(final AxisAlignedBB axisAlignedBB, final double n, final double n2, final double n3) {
        return j.\ud83e\udd14.ur(axisAlignedBB, n, n2, n3);
    }
    
    public static int uf(final int n) {
        return j.\ud83e\udd14.uf(n);
    }
    
    public static void ub() {
        j.\ud83e\udd14.ub();
    }
    
    public static void uw() {
        j.\ud83e\udd14.uw();
    }
    
    public static void ug(final DrawBlockHighlightEvent drawBlockHighlightEvent, final boolean b) {
        j.\ud83e\udd14.ug(drawBlockHighlightEvent, b);
    }
    
    public static boolean uu(final int n) {
        return j.\ud83e\udd14.uu(n);
    }
    
    public static Logger uz(final String s) {
        return j.\ud83e\udd14.uz(s);
    }
    
    public static Object uc(final Field field, final Object o) {
        return j.\ud83e\udd14.uc(field, o);
    }
    
    public static Object[] ua(final Vector vector, final Object[] array) {
        return j.\ud83e\udd14.ua(vector, array);
    }
    
    public static ClassLoader um() {
        return j.\ud83e\udd14.um();
    }
    
    public static URL uh(final ClassLoader classLoader, final String s) {
        return j.\ud83e\udd14.uh(classLoader, s);
    }
    
    public static String zd(final URL url) {
        return j.\ud83e\udd14.zd(url);
    }
    
    public static File zs(final String s, final String s2) {
        return j.\ud83e\udd14.zs(s, s2);
    }
    
    public static int zl(final InputStream inputStream, final byte[] array) {
        return j.\ud83e\udd14.zl(inputStream, array);
    }
    
    public static void zy(final FileOutputStream fileOutputStream, final byte[] array, final int n, final int n2) {
        j.\ud83e\udd14.zy(fileOutputStream, array, n, n2);
    }
    
    public static void zx(final FileOutputStream fileOutputStream) {
        j.\ud83e\udd14.zx(fileOutputStream);
    }
    
    public static void zk(final InputStream inputStream) {
        j.\ud83e\udd14.zk(inputStream);
    }
    
    public static void zq(final String s) {
        j.\ud83e\udd14.zq(s);
    }
    
    public static Field zv(final Class clazz, final String s) {
        return j.\ud83e\udd14.zv(clazz, s);
    }
    
    public static void zj(final Field field, final boolean b) {
        j.\ud83e\udd14.zj(field, b);
    }
    
    public static int ze(final NonNullList list) {
        return j.\ud83e\udd14.ze(list);
    }
    
    public static Object zo(final NonNullList list, final int n) {
        return j.\ud83e\udd14.zo(list, n);
    }
    
    public static NonNullList zt(final net.minecraft.inventory.Container container) {
        return j.\ud83e\udd14.zt(container);
    }
    
    public static PotionEffect zn(final EntityPlayerSP entityPlayerSP, final Potion potion) {
        return j.\ud83e\udd14.zn(entityPlayerSP, potion);
    }
    
    public static boolean zi(final EntityPlayerSP entityPlayerSP, final Potion potion) {
        return j.\ud83e\udd14.zi(entityPlayerSP, potion);
    }
    
    public static int zp(final PotionEffect potionEffect) {
        return j.\ud83e\udd14.zp(potionEffect);
    }
    
    public static void zr(final WorldClient worldClient) {
        j.\ud83e\udd14.zr(worldClient);
    }
    
    public static void zf(final Minecraft minecraft, final WorldClient worldClient) {
        j.\ud83e\udd14.zf(minecraft, worldClient);
    }
    
    public static boolean zb(final Minecraft minecraft) {
        return j.\ud83e\udd14.zb(minecraft);
    }
    
    public static void zw(final RealmsBridge realmsBridge, final GuiScreen guiScreen) {
        j.\ud83e\udd14.zw(realmsBridge, guiScreen);
    }
    
    public static int zg(final FontRenderer fontRenderer, final String s, final float n, final float n2, final int n3, final boolean b) {
        return j.\ud83e\udd14.zg(fontRenderer, s, n, n2, n3, b);
    }
    
    public static int zu(final FontRenderer fontRenderer, final char c) {
        return j.\ud83e\udd14.zu(fontRenderer, c);
    }
    
    public static String zz(final Class clazz) {
        return j.\ud83e\udd14.zz(clazz);
    }
    
    public static boolean zc(final HashMap hashMap, final Object o) {
        return j.\ud83e\udd14.zc(hashMap, o);
    }
    
    public static void za(final List list, final Comparator comparator) {
        j.\ud83e\udd14.za(list, comparator);
    }
    
    public static String zm(final Session session) {
        return j.\ud83e\udd14.zm(session);
    }
    
    public static GameProfile zh(final Session session) {
        return j.\ud83e\udd14.zh(session);
    }
    
    public static String cd(final String s, final String s2) {
        return j.\ud83e\udd14.cd(s, s2);
    }
    
    public static long cs(final Duration duration) {
        return j.\ud83e\udd14.cs(duration);
    }
    
    public static ItemStack cl(final InventoryPlayer inventoryPlayer, final int n) {
        return j.\ud83e\udd14.cl(inventoryPlayer, n);
    }
    
    public static double cy(final Vec3d vec3d, final Vec3d vec3d2) {
        return j.\ud83e\udd14.cy(vec3d, vec3d2);
    }
    
    public static ItemStack cx(final EntityPlayerSP entityPlayerSP, final EnumHand enumHand) {
        return j.\ud83e\udd14.cx(entityPlayerSP, enumHand);
    }
    
    public static String ck(final ItemStack itemStack) {
        return j.\ud83e\udd14.ck(itemStack);
    }
    
    public static EntityPlayer cq(final BlockEvent$BreakEvent blockEvent$BreakEvent) {
        return j.\ud83e\udd14.cq(blockEvent$BreakEvent);
    }
    
    public static boolean cv(final EntityPlayer entityPlayer, final Object o) {
        return j.\ud83e\udd14.cv(entityPlayer, o);
    }
    
    public static IBlockState cj(final BlockEvent$BreakEvent blockEvent$BreakEvent) {
        return j.\ud83e\udd14.cj(blockEvent$BreakEvent);
    }
    
    public static String ce(final Block block) {
        return j.\ud83e\udd14.ce(block);
    }
    
    public static Entity co(final AttackEntityEvent attackEntityEvent) {
        return j.\ud83e\udd14.co(attackEntityEvent);
    }
    
    public static ITextComponent ct(final Entity entity) {
        return j.\ud83e\udd14.ct(entity);
    }
    
    public static byte[] cn(final String s, final Charset charset) {
        return j.\ud83e\udd14.cn(s, charset);
    }
    
    public static void ci(final HttpURLConnection httpURLConnection, final String s) {
        j.\ud83e\udd14.ci(httpURLConnection, s);
    }
    
    public static void cp(final HttpURLConnection httpURLConnection, final String s, final String s2) {
        j.\ud83e\udd14.cp(httpURLConnection, s, s2);
    }
    
    public static void cr(final HttpURLConnection httpURLConnection, final int n) {
        j.\ud83e\udd14.cr(httpURLConnection, n);
    }
    
    public static void cf(final HttpURLConnection httpURLConnection, final boolean b) {
        j.\ud83e\udd14.cf(httpURLConnection, b);
    }
    
    public static void cb(final HttpURLConnection httpURLConnection, final boolean b) {
        j.\ud83e\udd14.cb(httpURLConnection, b);
    }
    
    public static void cw(final HttpURLConnection httpURLConnection) {
        j.\ud83e\udd14.cw(httpURLConnection);
    }
    
    public static OutputStream cg(final HttpURLConnection httpURLConnection) {
        return j.\ud83e\udd14.cg(httpURLConnection);
    }
    
    public static void cu(final OutputStream outputStream, final byte[] array) {
        j.\ud83e\udd14.cu(outputStream, array);
    }
    
    public static InputStream cz(final HttpURLConnection httpURLConnection) {
        return j.\ud83e\udd14.cz(httpURLConnection);
    }
    
    public static Object cc(final Gson gson, final Reader reader, final Class clazz) {
        return j.\ud83e\udd14.cc(gson, reader, clazz);
    }
    
    public static void ca(final HttpURLConnection httpURLConnection) {
        j.\ud83e\udd14.ca(httpURLConnection);
    }
    
    public static JsonElement cm(final JsonObject jsonObject, final String s) {
        return j.\ud83e\udd14.cm(jsonObject, s);
    }
    
    public static String ch(final JsonElement jsonElement) {
        return j.\ud83e\udd14.ch(jsonElement);
    }
    
    public static boolean ad(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.ad(entityPlayerSP);
    }
    
    public static boolean as(final WorldClient worldClient, final BlockPos blockPos) {
        return j.\ud83e\udd14.as(worldClient, blockPos);
    }
    
    public static EntityLivingBase al(final LivingEvent$LivingJumpEvent livingEvent$LivingJumpEvent) {
        return j.\ud83e\udd14.al(livingEvent$LivingJumpEvent);
    }
    
    public static boolean ay(final EntityLivingBase entityLivingBase, final Object o) {
        return j.\ud83e\udd14.ay(entityLivingBase, o);
    }
    
    public static Object ax(final Class clazz) {
        return j.\ud83e\udd14.ax(clazz);
    }
    
    public static EnumFacing$Axis[] ak() {
        return j.\ud83e\udd14.ak();
    }
    
    public static int aq(final EnumFacing$Axis enumFacing$Axis) {
        return j.\ud83e\udd14.aq(enumFacing$Axis);
    }
    
    public static void av(final EntityPlayerSP entityPlayerSP) {
        j.\ud83e\udd14.av(entityPlayerSP);
    }
    
    public static int aj(final double n) {
        return j.\ud83e\udd14.aj(n);
    }
    
    public static NBTTagList ae(final ItemStack itemStack) {
        return j.\ud83e\udd14.ae(itemStack);
    }
    
    public static int ao(final NBTTagList list) {
        return j.\ud83e\udd14.ao(list);
    }
    
    public static int at(final NBTTagList list) {
        return j.\ud83e\udd14.at(list);
    }
    
    public static NBTTagCompound an(final NBTTagList list, final int n) {
        return j.\ud83e\udd14.an(list, n);
    }
    
    public static EnumFacing ai(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.ai(entityPlayerSP);
    }
    
    public static EnumHand ap(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.ap(entityPlayerSP);
    }
    
    public static int ar(final RenderGameOverlayEvent$ElementType renderGameOverlayEvent$ElementType) {
        return j.\ud83e\udd14.ar(renderGameOverlayEvent$ElementType);
    }
    
    public static boolean af(final char[] array, final char c) {
        return j.\ud83e\udd14.af(array, c);
    }
    
    public static char[] ab(final int n) {
        return j.\ud83e\udd14.ab(n);
    }
    
    public static StringBuilder aw(final StringBuilder sb, final char[] array) {
        return j.\ud83e\udd14.aw(sb, array);
    }
    
    public static int ag(final ArrayList list) {
        return j.\ud83e\udd14.ag(list);
    }
    
    public static Object au(final ArrayList list, final int n) {
        return j.\ud83e\udd14.au(list, n);
    }
    
    public static int az(final Number n) {
        return j.\ud83e\udd14.az(n);
    }
    
    public static int ac(final String s, final String s2) {
        return j.\ud83e\udd14.ac(s, s2);
    }
    
    public static ITextComponent aa(final SPacketChat sPacketChat) {
        return j.\ud83e\udd14.aa(sPacketChat);
    }
    
    public static int am(final String s) {
        return j.\ud83e\udd14.am(s);
    }
    
    public static UserAuthentication ah(final YggdrasilAuthenticationService yggdrasilAuthenticationService, final Agent agent) {
        return j.\ud83e\udd14.ah(yggdrasilAuthenticationService, agent);
    }
    
    public static void md(final YggdrasilUserAuthentication yggdrasilUserAuthentication, final String s) {
        j.\ud83e\udd14.md(yggdrasilUserAuthentication, s);
    }
    
    public static void ms(final YggdrasilUserAuthentication yggdrasilUserAuthentication, final String s) {
        j.\ud83e\udd14.ms(yggdrasilUserAuthentication, s);
    }
    
    public static void ml(final YggdrasilUserAuthentication yggdrasilUserAuthentication) {
        j.\ud83e\udd14.ml(yggdrasilUserAuthentication);
    }
    
    public static GameProfile my(final YggdrasilUserAuthentication yggdrasilUserAuthentication) {
        return j.\ud83e\udd14.my(yggdrasilUserAuthentication);
    }
    
    public static String mx(final YggdrasilUserAuthentication yggdrasilUserAuthentication) {
        return j.\ud83e\udd14.mx(yggdrasilUserAuthentication);
    }
    
    public static String mk(final Item item, final ItemStack itemStack) {
        return j.\ud83e\udd14.mk(item, itemStack);
    }
    
    public static String mq(final long n) {
        return j.\ud83e\udd14.mq(n);
    }
    
    public static byte[] mv(final String s) {
        return j.\ud83e\udd14.mv(s);
    }
    
    public static void mj(final Random random, final byte[] array) {
        j.\ud83e\udd14.mj(random, array);
    }
    
    public static Charset me(final String s) {
        return j.\ud83e\udd14.me(s);
    }
    
    public static MessageDigest mo(final String s) {
        return j.\ud83e\udd14.mo(s);
    }
    
    public static void mt(final MessageDigest messageDigest, final byte[] array) {
        j.\ud83e\udd14.mt(messageDigest, array);
    }
    
    public static byte[] mn(final MessageDigest messageDigest) {
        return j.\ud83e\udd14.mn(messageDigest);
    }
    
    public static long mi(final Stream stream) {
        return j.\ud83e\udd14.mi(stream);
    }
    
    public static StringBuilder mp(final StringBuilder sb, final long n) {
        return j.\ud83e\udd14.mp(sb, n);
    }
    
    public static boolean mr(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.mr(entityPlayerSP);
    }
    
    public static boolean mf(final KeyBinding keyBinding) {
        return j.\ud83e\udd14.mf(keyBinding);
    }
    
    public static float mb(final float n) {
        return j.\ud83e\udd14.mb(n);
    }
    
    public static float mw(final float n) {
        return j.\ud83e\udd14.mw(n);
    }
    
    public static void mg(final PlayerCapabilities playerCapabilities, final float n) {
        j.\ud83e\udd14.mg(playerCapabilities, n);
    }
    
    public static UUID mu(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.mu(entityPlayerSP);
    }
    
    public static boolean mz(final UUID uuid, final Object o) {
        return j.\ud83e\udd14.mz(uuid, o);
    }
    
    public static UUID mc(final Entity entity) {
        return j.\ud83e\udd14.mc(entity);
    }
    
    public static ScriptEngine ma(final ScriptEngineManager scriptEngineManager, final String s) {
        return j.\ud83e\udd14.ma(scriptEngineManager, s);
    }
    
    public static Object mm(final ScriptEngine scriptEngine, final String s) {
        return j.\ud83e\udd14.mm(scriptEngine, s);
    }
    
    public static void mh(final ScriptEngine scriptEngine, final String s, final Object o) {
        j.\ud83e\udd14.mh(scriptEngine, s, o);
    }
    
    public static Object hd(final Invocable invocable, final String s, final Object[] array) {
        return j.\ud83e\udd14.hd(invocable, s, array);
    }
    
    public static void hs(final ItemStack itemStack, final int n) {
        j.\ud83e\udd14.hs(itemStack, n);
    }
    
    public static MapData hl(final ItemMap itemMap, final ItemStack itemStack, final World world) {
        return j.\ud83e\udd14.hl(itemMap, itemStack, world);
    }
    
    public static Tessellator hy() {
        return j.\ud83e\udd14.hy();
    }
    
    public static BufferBuilder hx(final Tessellator tessellator) {
        return j.\ud83e\udd14.hx(tessellator);
    }
    
    public static void hk(final BufferBuilder bufferBuilder, final int n, final VertexFormat vertexFormat) {
        j.\ud83e\udd14.hk(bufferBuilder, n, vertexFormat);
    }
    
    public static BufferBuilder hq(final BufferBuilder bufferBuilder, final double n, final double n2, final double n3) {
        return j.\ud83e\udd14.hq(bufferBuilder, n, n2, n3);
    }
    
    public static BufferBuilder hv(final BufferBuilder bufferBuilder, final double n, final double n2) {
        return j.\ud83e\udd14.hv(bufferBuilder, n, n2);
    }
    
    public static void hj(final BufferBuilder bufferBuilder) {
        j.\ud83e\udd14.hj(bufferBuilder);
    }
    
    public static void he(final Tessellator tessellator) {
        j.\ud83e\udd14.he(tessellator);
    }
    
    public static MapItemRenderer ho(final EntityRenderer entityRenderer) {
        return j.\ud83e\udd14.ho(entityRenderer);
    }
    
    public static void ht(final MapItemRenderer mapItemRenderer, final MapData mapData, final boolean b) {
        j.\ud83e\udd14.ht(mapItemRenderer, mapData, b);
    }
    
    public static void hn() {
        j.\ud83e\udd14.hn();
    }
    
    public static String hi(final ArrayList list) {
        return j.\ud83e\udd14.hi(list);
    }
    
    public static boolean hp(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.hp(entityPlayerSP);
    }
    
    public static float hr(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.hr(entityPlayerSP);
    }
    
    public static EnumFacing[] hf() {
        return j.\ud83e\udd14.hf();
    }
    
    public static EnumFacing hb(final EnumFacing enumFacing) {
        return j.\ud83e\udd14.hb(enumFacing);
    }
    
    public static boolean hw(final Block block, final IBlockState blockState, final boolean b) {
        return j.\ud83e\udd14.hw(block, blockState, b);
    }
    
    public static Vec3d hg(final Vec3d vec3d, final double n, final double n2, final double n3) {
        return j.\ud83e\udd14.hg(vec3d, n, n2, n3);
    }
    
    public static Vec3i hu(final EnumFacing enumFacing) {
        return j.\ud83e\udd14.hu(enumFacing);
    }
    
    public static Vec3d hz(final Vec3d vec3d, final double n) {
        return j.\ud83e\udd14.hz(vec3d, n);
    }
    
    public static Vec3d hc(final Vec3d vec3d, final Vec3d vec3d2) {
        return j.\ud83e\udd14.hc(vec3d, vec3d2);
    }
    
    public static double ha(final Vec3d vec3d, final Vec3d vec3d2) {
        return j.\ud83e\udd14.ha(vec3d, vec3d2);
    }
    
    public static float hm(final float n) {
        return j.\ud83e\udd14.hm(n);
    }
    
    public static EnumActionResult hh(final PlayerControllerMP playerControllerMP, final EntityPlayerSP entityPlayerSP, final WorldClient worldClient, final BlockPos blockPos, final EnumFacing enumFacing, final Vec3d vec3d, final EnumHand enumHand) {
        return j.\ud83e\udd14.hh(playerControllerMP, entityPlayerSP, worldClient, blockPos, enumFacing, vec3d, enumHand);
    }
    
    public static double sdd(final double n) {
        return j.\ud83e\udd14.sdd(n);
    }
    
    public static Block sds(final Item item) {
        return j.\ud83e\udd14.sds(item);
    }
    
    public static IBlockState sdl(final Block block) {
        return j.\ud83e\udd14.sdl(block);
    }
    
    public static boolean sdy(final IBlockState blockState) {
        return j.\ud83e\udd14.sdy(blockState);
    }
    
    public static RenderBlockOverlayEvent$OverlayType sdx(final RenderBlockOverlayEvent renderBlockOverlayEvent) {
        return j.\ud83e\udd14.sdx(renderBlockOverlayEvent);
    }
    
    public static void sdk(final RenderBlockOverlayEvent renderBlockOverlayEvent, final boolean b) {
        j.\ud83e\udd14.sdk(renderBlockOverlayEvent, b);
    }
    
    public static BlockPos sdq(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.sdq(entityPlayer);
    }
    
    public static BlockPos sdv(final BlockPos blockPos, final double n, final double n2, final double n3) {
        return j.\ud83e\udd14.sdv(blockPos, n, n2, n3);
    }
    
    public static List sdj(final WorldClient worldClient, final Class clazz, final AxisAlignedBB axisAlignedBB) {
        return j.\ud83e\udd14.sdj(worldClient, clazz, axisAlignedBB);
    }
    
    public static Float sde(final float n) {
        return j.\ud83e\udd14.sde(n);
    }
    
    public static ChatType sdo(final SPacketChat sPacketChat) {
        return j.\ud83e\udd14.sdo(sPacketChat);
    }
    
    public static String sdt(final ChatType chatType) {
        return j.\ud83e\udd14.sdt(chatType);
    }
    
    public static String sdn(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.sdn(entityPlayerSP);
    }
    
    public static String sdi(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.sdi(entityPlayerSP);
    }
    
    public static Runtime sdp() {
        return j.\ud83e\udd14.sdp();
    }
    
    public static int sdr(final Runtime runtime) {
        return j.\ud83e\udd14.sdr(runtime);
    }
    
    public static byte[] sdf(final MessageDigest messageDigest, final byte[] array) {
        return j.\ud83e\udd14.sdf(messageDigest, array);
    }
    
    public static char sdb(final String s, final int n) {
        return j.\ud83e\udd14.sdb(s, n);
    }
    
    public static int sdw(final char c, final int n) {
        return j.\ud83e\udd14.sdw(c, n);
    }
    
    public static void sdg(final GuiIngame guiIngame, final ChatType chatType, final ITextComponent textComponent) {
        j.\ud83e\udd14.sdg(guiIngame, chatType, textComponent);
    }
    
    public static void sdu(final ClientChatReceivedEvent clientChatReceivedEvent, final boolean b) {
        j.\ud83e\udd14.sdu(clientChatReceivedEvent, b);
    }
    
    public static String sdz(final String s) {
        return j.\ud83e\udd14.sdz(s);
    }
    
    public static ChatType sdc(final ClientChatReceivedEvent clientChatReceivedEvent) {
        return j.\ud83e\udd14.sdc(clientChatReceivedEvent);
    }
    
    public static boolean sda(final ClientChatReceivedEvent clientChatReceivedEvent) {
        return j.\ud83e\udd14.sda(clientChatReceivedEvent);
    }
    
    public static void sdm(final PlayerEvent$NameFormat playerEvent$NameFormat, final String s) {
        j.\ud83e\udd14.sdm(playerEvent$NameFormat, s);
    }
    
    public static int sdh() {
        return j.\ud83e\udd14.sdh();
    }
    
    public static void ssd(final Stream stream, final Consumer consumer) {
        j.\ud83e\udd14.ssd(stream, consumer);
    }
    
    public static int sss(final SPacketEffect sPacketEffect) {
        return j.\ud83e\udd14.sss(sPacketEffect);
    }
    
    public static BlockPos ssl(final SPacketEffect sPacketEffect) {
        return j.\ud83e\udd14.ssl(sPacketEffect);
    }
    
    public static int ssy(final SPacketPlayerPosLook sPacketPlayerPosLook) {
        return j.\ud83e\udd14.ssy(sPacketPlayerPosLook);
    }
    
    public static String ssx(final DecimalFormat decimalFormat, final long n) {
        return j.\ud83e\udd14.ssx(decimalFormat, n);
    }
    
    public static void ssk(final JButton button, final ActionListener actionListener) {
        j.\ud83e\udd14.ssk(button, actionListener);
    }
    
    public static Component ssq(final JPanel panel, final Component component) {
        return j.\ud83e\udd14.ssq(panel, component);
    }
    
    public static Component ssv(final JFrame frame, final Component component) {
        return j.\ud83e\udd14.ssv(frame, component);
    }
    
    public static void ssj(final JFrame frame, final boolean b) {
        j.\ud83e\udd14.ssj(frame, b);
    }
    
    public static String sse(final ActionEvent actionEvent) {
        return j.\ud83e\udd14.sse(actionEvent);
    }
    
    public static void sso(final Consumer consumer, final Object o) {
        j.\ud83e\udd14.sso(consumer, o);
    }
    
    public static void sst(final JFrame frame, final AWTEvent awtEvent) {
        j.\ud83e\udd14.sst(frame, awtEvent);
    }
    
    public static void ssn(final ArrayList list, final Consumer consumer) {
        j.\ud83e\udd14.ssn(list, consumer);
    }
    
    public static int ssi(final int n, final int n2) {
        return j.\ud83e\udd14.ssi(n, n2);
    }
    
    public static BlockPos ssp(final TileEntityChest tileEntityChest) {
        return j.\ud83e\udd14.ssp(tileEntityChest);
    }
    
    public static AxisAlignedBB ssr(final AxisAlignedBB axisAlignedBB, final AxisAlignedBB axisAlignedBB2) {
        return j.\ud83e\udd14.ssr(axisAlignedBB, axisAlignedBB2);
    }
    
    public static Set ssf(final Map map) {
        return j.\ud83e\udd14.ssf(map);
    }
    
    public static boolean ssb(final EntityPlayer entityPlayer, final Potion potion) {
        return j.\ud83e\udd14.ssb(entityPlayer, potion);
    }
    
    public static boolean ssw(final Set set, final Object o) {
        return j.\ud83e\udd14.ssw(set, o);
    }
    
    public static FMLClientHandler ssg() {
        return j.\ud83e\udd14.ssg();
    }
    
    public static NetworkManager ssu(final FMLClientHandler fmlClientHandler) {
        return j.\ud83e\udd14.ssu(fmlClientHandler);
    }
    
    public static void ssz(final EntityPlayerSP entityPlayerSP, final boolean b) {
        j.\ud83e\udd14.ssz(entityPlayerSP, b);
    }
    
    public static void ssc(final boolean b) {
        j.\ud83e\udd14.ssc(b);
    }
    
    public static void ssa(final double n, final double n2, final double n3, final double n4) {
        j.\ud83e\udd14.ssa(n, n2, n3, n4);
    }
    
    public static void ssm(final int n) {
        j.\ud83e\udd14.ssm(n);
    }
    
    public static void ssh(final double n, final double n2, final double n3) {
        j.\ud83e\udd14.ssh(n, n2, n3);
    }
    
    public static void sld() {
        j.\ud83e\udd14.sld();
    }
    
    public static BufferBuilder sls(final BufferBuilder bufferBuilder, final float n, final float n2, final float n3, final float n4) {
        return j.\ud83e\udd14.sls(bufferBuilder, n, n2, n3, n4);
    }
    
    public static AxisAlignedBB sll(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos blockPos) {
        return j.\ud83e\udd14.sll(blockState, blockAccess, blockPos);
    }
    
    public static AxisAlignedBB sly(final AxisAlignedBB axisAlignedBB, final BlockPos blockPos) {
        return j.\ud83e\udd14.sly(axisAlignedBB, blockPos);
    }
    
    public static void slx(final int n, final int n2, final int n3, final int n4) {
        j.\ud83e\udd14.slx(n, n2, n3, n4);
    }
    
    public static String slk(final String s, final String s2, final String s3) {
        return j.\ud83e\udd14.slk(s, s2, s3);
    }
    
    public static char slq(final char c) {
        return j.\ud83e\udd14.slq(c);
    }
    
    public static void slv(final double n, final double n2) {
        j.\ud83e\udd14.slv(n, n2);
    }
    
    public static boolean slj(final EntityTameable entityTameable) {
        return j.\ud83e\udd14.slj(entityTameable);
    }
    
    public static EntityLivingBase sle(final EntityTameable entityTameable) {
        return j.\ud83e\udd14.sle(entityTameable);
    }
    
    public static void slo(final EntityTameable entityTameable, final boolean b) {
        j.\ud83e\udd14.slo(entityTameable, b);
    }
    
    public static ITextComponent slt(final EntityLivingBase entityLivingBase) {
        return j.\ud83e\udd14.slt(entityLivingBase);
    }
    
    public static String sln(final ITextComponent textComponent) {
        return j.\ud83e\udd14.sln(textComponent);
    }
    
    public static void sli(final EntityTameable entityTameable, final String s) {
        j.\ud83e\udd14.sli(entityTameable, s);
    }
    
    public static boolean slp(final AbstractHorse abstractHorse) {
        return j.\ud83e\udd14.slp(abstractHorse);
    }
    
    public static UUID slr(final AbstractHorse abstractHorse) {
        return j.\ud83e\udd14.slr(abstractHorse);
    }
    
    public static void slf(final AbstractHorse abstractHorse, final boolean b) {
        j.\ud83e\udd14.slf(abstractHorse, b);
    }
    
    public static void slb(final AbstractHorse abstractHorse, final String s) {
        j.\ud83e\udd14.slb(abstractHorse, s);
    }
    
    public static void slw(final PlayerControllerMP playerControllerMP, final GameType gameType) {
        j.\ud83e\udd14.slw(playerControllerMP, gameType);
    }
    
    public static double slg(final Number n) {
        return j.\ud83e\udd14.slg(n);
    }
    
    public static boolean slu(final Enum enum1, final Object o) {
        return j.\ud83e\udd14.slu(enum1, o);
    }
    
    public static boolean slz(final int n) {
        return j.\ud83e\udd14.slz(n);
    }
    
    public static float slc(final Number n) {
        return j.\ud83e\udd14.slc(n);
    }
    
    public static String sla(final String s) {
        return j.\ud83e\udd14.sla(s);
    }
    
    public static ItemStack slm(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.slm(entityPlayerSP);
    }
    
    public static double slh(final EntityPlayer entityPlayer, final BlockPos blockPos) {
        return j.\ud83e\udd14.slh(entityPlayer, blockPos);
    }
    
    public static RayTraceResult syd(final WorldClient worldClient, final Vec3d vec3d, final Vec3d vec3d2) {
        return j.\ud83e\udd14.syd(worldClient, vec3d, vec3d2);
    }
    
    public static Comparator sys(final Function function) {
        return j.\ud83e\udd14.sys(function);
    }
    
    public static Optional syl(final Stream stream, final Comparator comparator) {
        return j.\ud83e\udd14.syl(stream, comparator);
    }
    
    public static Object syy(final Optional optional, final Object o) {
        return j.\ud83e\udd14.syy(optional, o);
    }
    
    public static AxisAlignedBB syx(final Entity entity) {
        return j.\ud83e\udd14.syx(entity);
    }
    
    public static NonNullList syk() {
        return j.\ud83e\udd14.syk();
    }
    
    public static boolean syq(final NonNullList list, final Collection collection) {
        return j.\ud83e\udd14.syq(list, collection);
    }
    
    public static double syv(final Entity entity, final double n, final double n2, final double n3) {
        return j.\ud83e\udd14.syv(entity, n, n2, n3);
    }
    
    public static float syj(final World world, final Vec3d vec3d, final AxisAlignedBB axisAlignedBB) {
        return j.\ud83e\udd14.syj(world, vec3d, axisAlignedBB);
    }
    
    public static int sye(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.sye(entityPlayer);
    }
    
    public static IAttributeInstance syo(final EntityPlayer entityPlayer, final IAttribute attribute) {
        return j.\ud83e\udd14.syo(entityPlayer, attribute);
    }
    
    public static double syt(final IAttributeInstance attributeInstance) {
        return j.\ud83e\udd14.syt(attributeInstance);
    }
    
    public static float syn(final float n, final float n2, final float n3) {
        return j.\ud83e\udd14.syn(n, n2, n3);
    }
    
    public static int syi(final EntityLivingBase entityLivingBase) {
        return j.\ud83e\udd14.syi(entityLivingBase);
    }
    
    public static IAttributeInstance syp(final EntityLivingBase entityLivingBase, final IAttribute attribute) {
        return j.\ud83e\udd14.syp(entityLivingBase, attribute);
    }
    
    public static EnumDifficulty syr(final WorldClient worldClient) {
        return j.\ud83e\udd14.syr(worldClient);
    }
    
    public static int syf(final EnumDifficulty enumDifficulty) {
        return j.\ud83e\udd14.syf(enumDifficulty);
    }
    
    public static boolean syb(final ArrayList list, final Object o) {
        return j.\ud83e\udd14.syb(list, o);
    }
    
    public static GameProfile syw(final SPacketPlayerListItem$AddPlayerData sPacketPlayerListItem$AddPlayerData) {
        return j.\ud83e\udd14.syw(sPacketPlayerListItem$AddPlayerData);
    }
    
    public static SoundCategory syg(final SPacketSoundEffect sPacketSoundEffect) {
        return j.\ud83e\udd14.syg(sPacketSoundEffect);
    }
    
    public static double syu(final SPacketSoundEffect sPacketSoundEffect) {
        return j.\ud83e\udd14.syu(sPacketSoundEffect);
    }
    
    public static double syz(final SPacketSoundEffect sPacketSoundEffect) {
        return j.\ud83e\udd14.syz(sPacketSoundEffect);
    }
    
    public static double syc(final SPacketSoundEffect sPacketSoundEffect) {
        return j.\ud83e\udd14.syc(sPacketSoundEffect);
    }
    
    public static void sya(final Entity entity) {
        j.\ud83e\udd14.sya(entity);
    }
    
    public static List sym(final SPacketChunkData sPacketChunkData) {
        return j.\ud83e\udd14.sym(sPacketChunkData);
    }
    
    public static int syh(final SPacketChunkData sPacketChunkData) {
        return j.\ud83e\udd14.syh(sPacketChunkData);
    }
    
    public static int sxd(final SPacketChunkData sPacketChunkData) {
        return j.\ud83e\udd14.sxd(sPacketChunkData);
    }
    
    public static Path sxs(final Path path, final byte[] array, final OpenOption[] array2) {
        return j.\ud83e\udd14.sxs(path, array, array2);
    }
    
    public static Map sxl(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.sxl(entityPlayer);
    }
    
    public static Set sxy(final Map map) {
        return j.\ud83e\udd14.sxy(map);
    }
    
    public static String sxx(final Potion potion) {
        return j.\ud83e\udd14.sxx(potion);
    }
    
    public static int sxk(final PotionEffect potionEffect) {
        return j.\ud83e\udd14.sxk(potionEffect);
    }
    
    public static Object[] sxq(final Object[] array, final int n) {
        return j.\ud83e\udd14.sxq(array, n);
    }
    
    public static EnumFacing$Axis sxv(final EnumFacing enumFacing) {
        return j.\ud83e\udd14.sxv(enumFacing);
    }
    
    public static EnumFacing$AxisDirection sxj(final EnumFacing enumFacing) {
        return j.\ud83e\udd14.sxj(enumFacing);
    }
    
    public static void sxe(final int n) {
        j.\ud83e\udd14.sxe(n);
    }
    
    public static long sxo(final WorldClient worldClient) {
        return j.\ud83e\udd14.sxo(worldClient);
    }
    
    public static LocalTime sxt(final int n, final int n2) {
        return j.\ud83e\udd14.sxt(n, n2);
    }
    
    public static LocalTime sxn() {
        return j.\ud83e\udd14.sxn();
    }
    
    public static void sxi(final float n, final float n2, final float n3, final float n4) {
        j.\ud83e\udd14.sxi(n, n2, n3, n4);
    }
    
    public static int sxp(final LocalTime localTime) {
        return j.\ud83e\udd14.sxp(localTime);
    }
    
    public static int sxr(final LocalTime localTime) {
        return j.\ud83e\udd14.sxr(localTime);
    }
    
    public static int sxf(final LocalTime localTime) {
        return j.\ud83e\udd14.sxf(localTime);
    }
    
    public static RenderGameOverlayEvent$ElementType[] sxb() {
        return j.\ud83e\udd14.sxb();
    }
    
    public static StringBuilder sxw(final StringBuilder sb) {
        return j.\ud83e\udd14.sxw(sb);
    }
    
    public static int sxg() {
        return j.\ud83e\udd14.sxg();
    }
    
    public static int sxu() {
        return j.\ud83e\udd14.sxu();
    }
    
    public static EntityPlayer sxz(final AttackEntityEvent attackEntityEvent) {
        return j.\ud83e\udd14.sxz(attackEntityEvent);
    }
    
    public static float sxc(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.sxc(entityPlayer);
    }
    
    public static double sxa(final double n) {
        return j.\ud83e\udd14.sxa(n);
    }
    
    public static EntityLivingBase sxm(final LivingEvent$LivingUpdateEvent livingEvent$LivingUpdateEvent) {
        return j.\ud83e\udd14.sxm(livingEvent$LivingUpdateEvent);
    }
    
    public static boolean sxh(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.sxh(entityPlayer);
    }
    
    public static double skd(final WorldBorder worldBorder) {
        return j.\ud83e\udd14.skd(worldBorder);
    }
    
    public static double sks(final WorldBorder worldBorder) {
        return j.\ud83e\udd14.sks(worldBorder);
    }
    
    public static double skl(final WorldBorder worldBorder) {
        return j.\ud83e\udd14.skl(worldBorder);
    }
    
    public static double sky(final WorldBorder worldBorder) {
        return j.\ud83e\udd14.sky(worldBorder);
    }
    
    public static void skx(final Runtime runtime, final Thread thread) {
        j.\ud83e\udd14.skx(runtime, thread);
    }
    
    public static void skk(final String s) {
        j.\ud83e\udd14.skk(s);
    }
    
    public static int skq(final Set set) {
        return j.\ud83e\udd14.skq(set);
    }
    
    public static void skv(final ArrayList list) {
        j.\ud83e\udd14.skv(list);
    }
    
    public static Set skj(final Map map) {
        return j.\ud83e\udd14.skj(map);
    }
    
    public static boolean ske(final Map map, final Object o) {
        return j.\ud83e\udd14.ske(map, o);
    }
    
    public static String sko(final Vec3d vec3d) {
        return j.\ud83e\udd14.sko(vec3d);
    }
    
    public static SystemTray skt() {
        return j.\ud83e\udd14.skt();
    }
    
    public static void skn(final TrayIcon trayIcon, final boolean b) {
        j.\ud83e\udd14.skn(trayIcon, b);
    }
    
    public static void ski(final TrayIcon trayIcon, final String s) {
        j.\ud83e\udd14.ski(trayIcon, s);
    }
    
    public static void skp(final SystemTray systemTray, final TrayIcon trayIcon) {
        j.\ud83e\udd14.skp(systemTray, trayIcon);
    }
    
    public static void skr(final TrayIcon trayIcon, final String s, final String s2, final TrayIcon.MessageType messageType) {
        j.\ud83e\udd14.skr(trayIcon, s, s2, messageType);
    }
    
    public static void skf(final Frame frame, final boolean b) {
        j.\ud83e\udd14.skf(frame, b);
    }
    
    public static void skb(final Frame frame, final int n) {
        j.\ud83e\udd14.skb(frame, n);
    }
    
    public static Icon skw(final Object o) {
        return j.\ud83e\udd14.skw(o);
    }
    
    public static Object skg(final Component component, final Object o, final String s, final int n, final Icon icon, final Object[] array, final Object o2) {
        return j.\ud83e\udd14.skg(component, o, s, n, icon, array, o2);
    }
    
    public static void sku(final FileWriter fileWriter, final String s) {
        j.\ud83e\udd14.sku(fileWriter, s);
    }
    
    public static void skz(final float n, final float n2, final float n3) {
        j.\ud83e\udd14.skz(n, n2, n3);
    }
    
    public static void skc(final EntityRenderer entityRenderer) {
        j.\ud83e\udd14.skc(entityRenderer);
    }
    
    public static void ska(final ClientChatEvent clientChatEvent, final String s) {
        j.\ud83e\udd14.ska(clientChatEvent, s);
    }
    
    public static void skm(final BufferedWriter bufferedWriter) {
        j.\ud83e\udd14.skm(bufferedWriter);
    }
    
    public static void skh(final BufferedWriter bufferedWriter) {
        j.\ud83e\udd14.skh(bufferedWriter);
    }
    
    public static boolean sqd(final ArrayList list, final Object o) {
        return j.\ud83e\udd14.sqd(list, o);
    }
    
    public static String sqs(final EntityPlayer entityPlayer) {
        return j.\ud83e\udd14.sqs(entityPlayer);
    }
    
    public static void sql(final EntityPlayerSP entityPlayerSP, final float n) {
        j.\ud83e\udd14.sql(entityPlayerSP, n);
    }
    
    public static void sqy() {
        j.\ud83e\udd14.sqy();
    }
    
    public static void sqx(final int n) {
        j.\ud83e\udd14.sqx(n);
    }
    
    public static void sqk() {
        j.\ud83e\udd14.sqk();
    }
    
    public static void sqq(final RenderManager renderManager, final float n) {
        j.\ud83e\udd14.sqq(renderManager, n);
    }
    
    public static void sqv(final RenderManager renderManager, final boolean b) {
        j.\ud83e\udd14.sqv(renderManager, b);
    }
    
    public static void sqj(final RenderManager renderManager, final Entity entity, final double n, final double n2, final double n3, final float n4, final float n5, final boolean b) {
        j.\ud83e\udd14.sqj(renderManager, entity, n, n2, n3, n4, n5, b);
    }
    
    public static void sqe(final int n) {
        j.\ud83e\udd14.sqe(n);
    }
    
    public static void sqo(final int n) {
        j.\ud83e\udd14.sqo(n);
    }
    
    public static void sqt() {
        j.\ud83e\udd14.sqt();
    }
    
    public static void sqn(final EntityPlayerSP entityPlayerSP) {
        j.\ud83e\udd14.sqn(entityPlayerSP);
    }
    
    public static FoodStats sqi(final EntityPlayerSP entityPlayerSP) {
        return j.\ud83e\udd14.sqi(entityPlayerSP);
    }
    
    public static void sqp(final FoodStats foodStats, final int n) {
        j.\ud83e\udd14.sqp(foodStats, n);
    }
    
    static {
        j.\ud83e\udd14 = (r)new u(j.class.getClassLoader(), "j/v/bj/y/k/a.class", 110141).createClass("?").newInstance();
    }
}
