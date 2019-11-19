package j.v.bj.y.k;

import net.minecraft.util.FoodStats;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.Icon;
import java.awt.Frame;
import java.awt.TrayIcon;
import java.awt.SystemTray;
import org.lwjgl.opengl.Display;
import net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent;
import java.time.LocalTime;
import net.minecraft.util.EnumFacing$AxisDirection;
import java.nio.file.OpenOption;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.util.CombatRules;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.IAttribute;
import java.util.Optional;
import org.lwjgl.input.Mouse;
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
import java.util.regex.Pattern;
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
import net.minecraft.util.math.MathHelper;
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
import org.apache.logging.log4j.LogManager;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.client.renderer.GlStateManager$DestFactor;
import net.minecraft.client.renderer.GlStateManager$SourceFactor;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Post;
import net.minecraftforge.fml.common.eventhandler.Event;
import java.lang.annotation.Annotation;
import java.util.Objects;
import net.minecraft.network.play.server.SPacketEntityTeleport;
import net.minecraftforge.client.event.EntityViewRenderEvent$FOVModifier;
import java.nio.FloatBuffer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.ActiveRenderInfo;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.Clipboard;
import org.apache.commons.lang3.ArrayUtils;
import net.minecraft.util.Session;
import net.minecraft.util.text.ITextComponent$Serializer;
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
import java.util.Collections;
import java.io.InputStream;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.client.renderer.entity.RenderManager;
import java.util.function.BiConsumer;
import com.mojang.authlib.GameProfile;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraft.util.SoundEvent;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.RenderGlobal;
import java.util.HashMap;
import java.net.URL;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.network.NetworkManager;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.Style;
import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.awt.image.BufferedImage;
import java.util.function.IntFunction;
import java.util.function.Function;
import com.google.common.collect.ImmutableSet;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.item.ItemBlock;
import net.minecraft.client.renderer.RenderHelper;
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
import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import java.nio.IntBuffer;
import net.minecraft.client.renderer.OpenGlHelper;
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
import javax.swing.BorderFactory;
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
import com.google.common.hash.Hashing;
import com.google.common.hash.HashFunction;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import org.apache.commons.lang3.StringUtils;
import java.util.Random;
import javax.swing.Timer;
import java.nio.file.Paths;
import java.nio.file.Path;
import com.mojang.authlib.exceptions.AuthenticationException;
import java.io.PrintStream;
import com.google.common.io.Files;
import com.google.common.io.CharSource;
import java.nio.charset.Charset;
import java.io.File;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.item.Item;
import net.minecraft.client.entity.EntityPlayerSP;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.Arrays;
import net.minecraft.util.NonNullList;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.google.common.util.concurrent.ListenableFuture;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import java.util.function.Consumer;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.text.ITextComponent;
import com.google.common.base.Predicate;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;

public class a implements r
{
    public a() {
        super();
    }
    
    @Override
    public Enum d(final Class clazz, final String s) {
        return Enum.<Enum>valueOf((Class<Enum>)clazz, s);
    }
    
    @Override
    public Iterator s(final ArrayList list) {
        return list.iterator();
    }
    
    @Override
    public boolean l(final Iterator iterator) {
        return iterator.hasNext();
    }
    
    @Override
    public Object y(final Iterator iterator) {
        return iterator.next();
    }
    
    @Override
    public Object x(final Map map, final Object o, final Object o2) {
        return map.put(o, o2);
    }
    
    @Override
    public boolean k(final String s, final Object o) {
        return s.equals(o);
    }
    
    @Override
    public boolean q(final ArrayList list, final Object o) {
        return list.add(o);
    }
    
    @Override
    public boolean v(final ArrayList list, final Collection collection) {
        return list.addAll(collection);
    }
    
    @Override
    public void j(final List list) {
        list.clear();
    }
    
    @Override
    public void e(final boolean b) {
        Keyboard.enableRepeatEvents(b);
    }
    
    @Override
    public void o(final GuiTextField guiTextField, final Predicate validator) {
        guiTextField.setValidator(validator);
    }
    
    @Override
    public void t(final GuiTextField guiTextField, final int maxStringLength) {
        guiTextField.setMaxStringLength(maxStringLength);
    }
    
    @Override
    public String n(final ITextComponent textComponent) {
        return textComponent.getUnformattedText();
    }
    
    @Override
    public void i(final GuiTextField guiTextField, final String text) {
        guiTextField.setText(text);
    }
    
    @Override
    public boolean p(final List list, final Object o) {
        return list.add(o);
    }
    
    @Override
    public Object r(final List list, final int n) {
        return list.get(n);
    }
    
    @Override
    public void f(final GuiTextField guiTextField, final boolean focused) {
        guiTextField.setFocused(focused);
    }
    
    @Override
    public void b(final TileEntitySign tileEntitySign, final boolean editable) {
        tileEntitySign.setEditable(editable);
    }
    
    @Override
    public void w(final List list, final Consumer consumer) {
        list.forEach(consumer);
    }
    
    @Override
    public boolean g(final GuiTextField guiTextField) {
        return guiTextField.isFocused();
    }
    
    @Override
    public String u(final GuiTextField guiTextField) {
        return guiTextField.getText();
    }
    
    @Override
    public String z(final String s, final Object[] array) {
        return I18n.format(s, array);
    }
    
    @Override
    public void c(final float n, final float n2, final float n3, final float n4) {
        GlStateManager.color(n, n2, n3, n4);
    }
    
    @Override
    public void a() {
        GlStateManager.pushMatrix();
    }
    
    @Override
    public void m(final float n, final float n2, final float n3) {
        GlStateManager.translate(n, n2, n3);
    }
    
    @Override
    public void h(final float n, final float n2, final float n3) {
        GlStateManager.scale(n, n2, n3);
    }
    
    @Override
    public void sd(final float n, final float n2, final float n3, final float n4) {
        GlStateManager.rotate(n, n2, n3, n4);
    }
    
    @Override
    public Block ss(final TileEntitySign tileEntitySign) {
        return tileEntitySign.getBlockType();
    }
    
    @Override
    public int sl(final TileEntitySign tileEntitySign) {
        return tileEntitySign.getBlockMetadata();
    }
    
    @Override
    public void sy(final TileEntityRendererDispatcher tileEntityRendererDispatcher, final TileEntity tileEntity, final double n, final double n2, final double n3, final float n4) {
        tileEntityRendererDispatcher.render(tileEntity, n, n2, n3, n4);
    }
    
    @Override
    public void sx() {
        GlStateManager.popMatrix();
    }
    
    @Override
    public void sk(final TileEntitySign tileEntitySign) {
        tileEntitySign.markDirty();
    }
    
    @Override
    public void sq(final Minecraft minecraft, final GuiScreen guiScreen) {
        minecraft.displayGuiScreen(guiScreen);
    }
    
    @Override
    public NetHandlerPlayClient sv(final Minecraft minecraft) {
        return minecraft.getConnection();
    }
    
    @Override
    public BlockPos sj(final TileEntitySign tileEntitySign) {
        return tileEntitySign.getPos();
    }
    
    @Override
    public void se(final NetHandlerPlayClient netHandlerPlayClient, final Packet packet) {
        netHandlerPlayClient.sendPacket(packet);
    }
    
    @Override
    public int so(final FontRenderer fontRenderer, final String s) {
        return fontRenderer.getStringWidth(s);
    }
    
    @Override
    public char[] st(final String s) {
        return s.toCharArray();
    }
    
    @Override
    public boolean sn(final char c) {
        return ChatAllowedCharacters.isAllowedCharacter(c);
    }
    
    @Override
    public int si(final GuiTextField guiTextField) {
        return guiTextField.getId();
    }
    
    @Override
    public boolean sp(final GuiTextField guiTextField, final char c, final int n) {
        return guiTextField.textboxKeyTyped(c, n);
    }
    
    @Override
    public boolean sr(final GuiTextField guiTextField, final int n, final int n2, final int n3) {
        return guiTextField.mouseClicked(n, n2, n3);
    }
    
    @Override
    public void sf(final EventBus eventBus, final Object o) {
        eventBus.register(o);
    }
    
    @Override
    public ListenableFuture sb(final Minecraft minecraft, final Runnable runnable) {
        return minecraft.addScheduledTask(runnable);
    }
    
    @Override
    public String sw(final SimpleDateFormat simpleDateFormat, final Date date) {
        return simpleDateFormat.format(date);
    }
    
    @Override
    public Iterator sg(final List list) {
        return list.iterator();
    }
    
    @Override
    public String su(final Enum enum1) {
        return enum1.name();
    }
    
    @Override
    public void sz(final PrintWriter printWriter, final String s) {
        printWriter.print(s);
    }
    
    @Override
    public void sc(final PrintWriter printWriter) {
        printWriter.close();
    }
    
    @Override
    public int sa(final Integer n) {
        return n;
    }
    
    @Override
    public int sm(final ItemStack itemStack) {
        return itemStack.getCount();
    }
    
    @Override
    public int sh(final ItemStack itemStack) {
        return itemStack.getMaxStackSize();
    }
    
    @Override
    public double ld(final double n) {
        return Math.ceil(n);
    }
    
    @Override
    public int ls() {
        return InventoryPlayer.getHotbarSize();
    }
    
    @Override
    public List ll(final NonNullList list, final int n, final int n2) {
        return list.subList(n, n2);
    }
    
    @Override
    public Minecraft ly() {
        return Minecraft.getMinecraft();
    }
    
    @Override
    public List lx(final Object[] array) {
        return Arrays.<Object>asList(array);
    }
    
    @Override
    public StringBuilder lk(final StringBuilder sb, final String s) {
        return sb.append(s);
    }
    
    @Override
    public String lq(final StringBuilder sb) {
        return sb.toString();
    }
    
    @Override
    public String[] lv(final String s, final String s2) {
        return s.split(s2);
    }
    
    @Override
    public String lj(final String s) {
        return s.toLowerCase();
    }
    
    @Override
    public Object le(final Map map, final Object o, final Object o2) {
        return map.getOrDefault(o, o2);
    }
    
    @Override
    public Stream lo(final List list) {
        return list.stream();
    }
    
    @Override
    public Stream lt(final Stream stream, final java.util.function.Predicate predicate) {
        return stream.filter(predicate);
    }
    
    @Override
    public Collector ln() {
        return Collectors.<Object>toList();
    }
    
    @Override
    public Object li(final Stream stream, final Collector collector) {
        return stream.<Object, Object>collect(collector);
    }
    
    @Override
    public int lp(final List list) {
        return list.size();
    }
    
    @Override
    public BlockPos lr(final TileEntity tileEntity) {
        return tileEntity.getPos();
    }
    
    @Override
    public boolean lf(final Boolean b) {
        return b;
    }
    
    @Override
    public boolean lb(final Set set, final Object o) {
        return set.contains(o);
    }
    
    @Override
    public int lw(final BlockPos blockPos) {
        return blockPos.getX();
    }
    
    @Override
    public int lg(final BlockPos blockPos) {
        return blockPos.getY();
    }
    
    @Override
    public int lu(final BlockPos blockPos) {
        return blockPos.getZ();
    }
    
    @Override
    public double lz(final EntityPlayerSP entityPlayerSP, final double n, final double n2, final double n3) {
        return entityPlayerSP.getDistance(n, n2, n3);
    }
    
    @Override
    public double lc(final Double n) {
        return n;
    }
    
    @Override
    public Item la(final int n) {
        return Item.getItemById(n);
    }
    
    @Override
    public void lm(final Set set) {
        set.clear();
    }
    
    @Override
    public ItemStack lh(final InventoryPlayer inventoryPlayer, final int n) {
        return inventoryPlayer.getStackInSlot(n);
    }
    
    @Override
    public Item yd(final ItemStack itemStack) {
        return itemStack.getItem();
    }
    
    @Override
    public boolean ys(final Object o, final Object o2) {
        return o.equals(o2);
    }
    
    @Override
    public BlockPos yl(final RayTraceResult rayTraceResult) {
        return rayTraceResult.getBlockPos();
    }
    
    @Override
    public BlockPos yy(final BlockPos blockPos, final EnumFacing enumFacing) {
        return blockPos.offset(enumFacing);
    }
    
    @Override
    public boolean yx(final Set set, final Object o) {
        return set.add(o);
    }
    
    @Override
    public CharSource yk(final File file, final Charset charset) {
        return Files.asCharSource(file, charset);
    }
    
    @Override
    public String yq(final CharSource charSource) {
        return charSource.read();
    }
    
    @Override
    public boolean yv(final String s) {
        return s.isEmpty();
    }
    
    @Override
    public String yj(final String s) {
        return s.trim();
    }
    
    @Override
    public void ye(final PrintStream printStream, final String s) {
        printStream.println(s);
    }
    
    @Override
    public void yo(final AuthenticationException ex) {
        ex.printStackTrace();
    }
    
    @Override
    public String yt(final AuthenticationException ex) {
        return ex.toString();
    }
    
    @Override
    public void yn(final Exception ex) {
        ex.printStackTrace();
    }
    
    @Override
    public void yi(final GuiTextField guiTextField) {
        guiTextField.updateCursorCounter();
    }
    
    @Override
    public void yp(final GuiTextField guiTextField) {
        guiTextField.drawTextBox();
    }
    
    @Override
    public boolean yr(final File file) {
        return file.createNewFile();
    }
    
    @Override
    public String yf(final File file) {
        return file.getCanonicalPath();
    }
    
    @Override
    public Path yb(final String s, final String[] array) {
        return Paths.get(s, array);
    }
    
    @Override
    public byte[] yw(final Path path) {
        return java.nio.file.Files.readAllBytes(path);
    }
    
    @Override
    public void yg(final Timer timer) {
        timer.start();
    }
    
    @Override
    public String yu(final Exception ex) {
        return ex.toString();
    }
    
    @Override
    public void yz(final Timer timer) {
        timer.stop();
    }
    
    @Override
    public int yc(final Random random, final int n) {
        return random.nextInt(n);
    }
    
    @Override
    public StringBuilder ya(final StringBuilder sb, final char c) {
        return sb.append(c);
    }
    
    @Override
    public String ym(final char ch, final int repeat) {
        return StringUtils.repeat(ch, repeat);
    }
    
    @Override
    public void yh(final EntityPlayerSP entityPlayerSP, final String s) {
        entityPlayerSP.sendChatMessage(s);
    }
    
    @Override
    public float xd(final EntityPlayerSP entityPlayerSP, final Entity entity) {
        return entityPlayerSP.getDistance(entity);
    }
    
    @Override
    public float xs(final EntityLivingBase entityLivingBase) {
        return entityLivingBase.getHealth();
    }
    
    @Override
    public ItemStack xl(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.getHeldItemMainhand();
    }
    
    @Override
    public void xy(final PlayerControllerMP playerControllerMP, final EntityPlayer entityPlayer, final Entity entity) {
        playerControllerMP.attackEntity(entityPlayer, entity);
    }
    
    @Override
    public void xx(final EntityPlayerSP entityPlayerSP, final EnumHand enumHand) {
        entityPlayerSP.swingArm(enumHand);
    }
    
    @Override
    public String xk(final String s) {
        return System.getenv(s);
    }
    
    @Override
    public String xq(final String s) {
        return System.getProperty(s);
    }
    
    @Override
    public int xv(final String s) {
        return s.hashCode();
    }
    
    @Override
    public String xj(final int n) {
        return String.valueOf(n);
    }
    
    @Override
    public HashFunction xe() {
        return Hashing.sha1();
    }
    
    @Override
    public HashCode xo(final HashFunction hashFunction, final CharSequence charSequence, final Charset charset) {
        return hashFunction.hashString(charSequence, charset);
    }
    
    @Override
    public String xt(final HashCode hashCode) {
        return hashCode.toString();
    }
    
    @Override
    public HashFunction xn() {
        return Hashing.sha512();
    }
    
    @Override
    public boolean xi(final String s, final String s2) {
        return s.equalsIgnoreCase(s2);
    }
    
    @Override
    public boolean xp(final Random random) {
        return random.nextBoolean();
    }
    
    @Override
    public void xr(final Logger logger, final String s) {
        logger.info(s);
    }
    
    @Override
    public UUID xf(final String s) {
        return UUID.fromString(s);
    }
    
    @Override
    public String xb(final UUID uuid) {
        return uuid.toString();
    }
    
    @Override
    public void xw(final PrintStream printStream, final String s) {
        printStream.print(s);
    }
    
    @Override
    public void xg(final EntityOtherPlayerMP entityOtherPlayerMP, final Entity entity) {
        entityOtherPlayerMP.copyLocationAndAnglesFrom(entity);
    }
    
    @Override
    public NBTTagCompound xu(final EntityPlayerSP entityPlayerSP, final NBTTagCompound nbtTagCompound) {
        return entityPlayerSP.writeToNBT(nbtTagCompound);
    }
    
    @Override
    public void xz(final EntityOtherPlayerMP entityOtherPlayerMP, final NBTTagCompound nbtTagCompound) {
        entityOtherPlayerMP.readFromNBT(nbtTagCompound);
    }
    
    @Override
    public Entity xc(final WorldClient worldClient, final int n) {
        return worldClient.getEntityByID(n);
    }
    
    @Override
    public void xa(final WorldClient worldClient, final int n, final Entity entity) {
        worldClient.addEntityToWorld(n, entity);
    }
    
    @Override
    public String xm(final Exception ex) {
        return ex.getMessage();
    }
    
    @Override
    public Toolkit xh() {
        return Toolkit.getDefaultToolkit();
    }
    
    @Override
    public Dimension kd(final Toolkit toolkit) {
        return toolkit.getScreenSize();
    }
    
    @Override
    public void ks(final JFrame frame, final int n, final int n2) {
        frame.setSize(n, n2);
    }
    
    @Override
    public void kl(final JFrame frame, final Container contentPane) {
        frame.setContentPane(contentPane);
    }
    
    @Override
    public void ky(final JFrame frame) {
        frame.pack();
    }
    
    @Override
    public void kx(final JFrame frame, final boolean visible) {
        frame.setVisible(visible);
    }
    
    @Override
    public void kk(final JTextField textField, final ActionListener actionListener) {
        textField.addActionListener(actionListener);
    }
    
    @Override
    public ITextComponent kq(final ClientChatReceivedEvent clientChatReceivedEvent) {
        return clientChatReceivedEvent.getMessage();
    }
    
    @Override
    public void kv(final JTextArea textArea, final String s) {
        textArea.append(s);
    }
    
    @Override
    public JScrollBar kj(final JScrollPane scrollPane) {
        return scrollPane.getVerticalScrollBar();
    }
    
    @Override
    public int ke(final JScrollBar scrollBar) {
        return scrollBar.getMaximum();
    }
    
    @Override
    public void ko(final JScrollBar scrollBar, final int value) {
        scrollBar.setValue(value);
    }
    
    @Override
    public void kt(final JTextArea textArea, final String text) {
        textArea.setText(text);
    }
    
    @Override
    public void kn(final JFrame frame) {
        frame.dispose();
    }
    
    @Override
    public void ki(final JPanel panel, final LayoutManager layout) {
        panel.setLayout(layout);
    }
    
    @Override
    public Border kp() {
        return BorderFactory.createEtchedBorder();
    }
    
    @Override
    public TitledBorder kr(final Border border, final String s) {
        return BorderFactory.createTitledBorder(border, s);
    }
    
    @Override
    public void kf(final JPanel panel, final Border border) {
        panel.setBorder(border);
    }
    
    @Override
    public void kb(final JPanel panel, final Component component, final Object o) {
        panel.add(component, o);
    }
    
    @Override
    public void kw(final JSplitPane splitPane, final Component leftComponent) {
        splitPane.setLeftComponent(leftComponent);
    }
    
    @Override
    public void kg(final JScrollPane scrollPane, final int horizontalScrollBarPolicy) {
        scrollPane.setHorizontalScrollBarPolicy(horizontalScrollBarPolicy);
    }
    
    @Override
    public void ku(final JScrollPane scrollPane, final int verticalScrollBarPolicy) {
        scrollPane.setVerticalScrollBarPolicy(verticalScrollBarPolicy);
    }
    
    @Override
    public void kz(final JTextArea textArea, final boolean lineWrap) {
        textArea.setLineWrap(lineWrap);
    }
    
    @Override
    public void kc(final JScrollPane scrollPane, final Component viewportView) {
        scrollPane.setViewportView(viewportView);
    }
    
    @Override
    public void ka(final JSplitPane splitPane, final Component rightComponent) {
        splitPane.setRightComponent(rightComponent);
    }
    
    @Override
    public String km(final JTextField textField) {
        return textField.getText();
    }
    
    @Override
    public boolean kh(final String s, final String s2) {
        return s.startsWith(s2);
    }
    
    @Override
    public void qd(final JTextField textField, final String text) {
        textField.setText(text);
    }
    
    @Override
    public long qs(final double n) {
        return Math.round(n);
    }
    
    @Override
    public ItemStack ql(final InventoryPlayer inventoryPlayer) {
        return inventoryPlayer.getCurrentItem();
    }
    
    @Override
    public boolean qy(final ItemStack itemStack) {
        return itemStack.isEmpty();
    }
    
    @Override
    public List qx(final ItemStack itemStack, final EntityPlayer entityPlayer, final ITooltipFlag tooltipFlag) {
        return itemStack.getTooltip(entityPlayer, tooltipFlag);
    }
    
    @Override
    public NBTTagCompound qk(final ItemStack itemStack) {
        return itemStack.getTagCompound();
    }
    
    @Override
    public Set qq(final NBTTagCompound nbtTagCompound) {
        return nbtTagCompound.getKeySet();
    }
    
    @Override
    public Iterator qv(final Set set) {
        return set.iterator();
    }
    
    @Override
    public NBTBase qj(final NBTTagCompound nbtTagCompound, final String s) {
        return nbtTagCompound.getTag(s);
    }
    
    @Override
    public String qe(final NBTBase nbtBase) {
        return nbtBase.toString();
    }
    
    @Override
    public int qo(final Color color) {
        return color.getRGB();
    }
    
    @Override
    public int qt(final FontRenderer fontRenderer, final String s, final int n, final int n2, final int n3) {
        return fontRenderer.drawString(s, n, n2, n3);
    }
    
    @Override
    public Class qn(final Object o) {
        return o.getClass();
    }
    
    @Override
    public Object[] qi(final Class clazz) {
        return clazz.getEnumConstants();
    }
    
    @Override
    public Object qp(final Class clazz, final Object o) {
        return clazz.cast(o);
    }
    
    @Override
    public BlockPos qr(final TileEntityHopper tileEntityHopper) {
        return tileEntityHopper.getPos();
    }
    
    @Override
    public double qf(final BlockPos blockPos, final int n, final int n2, final int n3) {
        return blockPos.getDistance(n, n2, n3);
    }
    
    @Override
    public BlockPos qb(final BlockPos blockPos, final int n, final int n2, final int n3) {
        return blockPos.add(n, n2, n3);
    }
    
    @Override
    public void qw(final PrintStream printStream, final Object o) {
        printStream.println(o);
    }
    
    @Override
    public Boolean qg(final boolean b) {
        return b;
    }
    
    @Override
    public SPacketPlayerListItem$Action qu(final SPacketPlayerListItem sPacketPlayerListItem) {
        return sPacketPlayerListItem.getAction();
    }
    
    @Override
    public Collection qz(final NetHandlerPlayClient netHandlerPlayClient) {
        return netHandlerPlayClient.getPlayerInfoMap();
    }
    
    @Override
    public int qc(final Collection collection) {
        return collection.size();
    }
    
    @Override
    public List qa(final SPacketPlayerListItem sPacketPlayerListItem) {
        return sPacketPlayerListItem.getEntries();
    }
    
    @Override
    public ITextComponent qm(final SPacketPlayerListItem$AddPlayerData sPacketPlayerListItem$AddPlayerData) {
        return sPacketPlayerListItem$AddPlayerData.getDisplayName();
    }
    
    @Override
    public StringBuilder qh(final StringBuilder sb, final Object o) {
        return sb.append(o);
    }
    
    @Override
    public String vd(final BufferedReader bufferedReader) {
        return bufferedReader.readLine();
    }
    
    @Override
    public void vs(final BufferedReader bufferedReader) {
        bufferedReader.close();
    }
    
    @Override
    public Charset vl() {
        return Charset.defaultCharset();
    }
    
    @Override
    public Object[] vy(final Set set) {
        return set.toArray();
    }
    
    @Override
    public String vx(final Object o) {
        return o.toString();
    }
    
    @Override
    public String vk(final EntityPlayer entityPlayer) {
        return entityPlayer.getDisplayNameString();
    }
    
    @Override
    public IBlockState vq(final WorldClient worldClient, final BlockPos blockPos) {
        return worldClient.getBlockState(blockPos);
    }
    
    @Override
    public Material vv(final IBlockState blockState) {
        return blockState.getMaterial();
    }
    
    @Override
    public boolean vj(final Material material) {
        return material.isReplaceable();
    }
    
    @Override
    public boolean ve(final EntityPlayerSP entityPlayerSP, final Object o) {
        return entityPlayerSP.equals(o);
    }
    
    @Override
    public void vo(final EntityPlayerSP entityPlayerSP, final float health) {
        entityPlayerSP.setHealth(health);
    }
    
    @Override
    public float vt(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.getHealth();
    }
    
    @Override
    public void vn(final Minecraft minecraft) {
        minecraft.setIngameFocus();
    }
    
    @Override
    public BlockPos vi(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.getPosition();
    }
    
    @Override
    public Chunk vp(final WorldClient worldClient, final BlockPos blockPos) {
        return worldClient.getChunk(blockPos);
    }
    
    @Override
    public BiomeProvider vr(final WorldClient worldClient) {
        return worldClient.getBiomeProvider();
    }
    
    @Override
    public Biome vf(final Chunk chunk, final BlockPos blockPos, final BiomeProvider biomeProvider) {
        return chunk.getBiome(blockPos, biomeProvider);
    }
    
    @Override
    public String vb(final Biome biome) {
        return biome.getBiomeName();
    }
    
    @Override
    public Vec3d vw(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.getPositionVector();
    }
    
    @Override
    public boolean vg(final BlockPos blockPos, final Object o) {
        return blockPos.equals(o);
    }
    
    @Override
    public List vu(final WorldClient worldClient, final Entity entity, final AxisAlignedBB axisAlignedBB) {
        return worldClient.getEntitiesWithinAABBExcludingEntity(entity, axisAlignedBB);
    }
    
    @Override
    public boolean vz(final List list) {
        return list.isEmpty();
    }
    
    @Override
    public boolean vc(final File file) {
        return file.mkdir();
    }
    
    @Override
    public boolean va() {
        return OpenGlHelper.isFramebufferEnabled();
    }
    
    @Override
    public int vm(final IntBuffer intBuffer) {
        return intBuffer.capacity();
    }
    
    @Override
    public IntBuffer vh(final int n) {
        return BufferUtils.createIntBuffer(n);
    }
    
    @Override
    public void jd(final int n, final int n2) {
        GL11.glPixelStorei(n, n2);
    }
    
    @Override
    public Buffer js(final IntBuffer intBuffer) {
        return intBuffer.clear();
    }
    
    @Override
    public void jl(final int n) {
        GlStateManager.bindTexture(n);
    }
    
    @Override
    public void jy(final int n, final int n2, final int n3, final int n4, final IntBuffer intBuffer) {
        GL11.glGetTexImage(n, n2, n3, n4, intBuffer);
    }
    
    @Override
    public void jx(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final IntBuffer intBuffer) {
        GL11.glReadPixels(n, n2, n3, n4, n5, n6, intBuffer);
    }
    
    @Override
    public IntBuffer jk(final IntBuffer intBuffer, final int[] array) {
        return intBuffer.get(array);
    }
    
    @Override
    public Framebuffer jq(final Minecraft minecraft) {
        return minecraft.getFramebuffer();
    }
    
    @Override
    public void jv(final Thread thread) {
        thread.start();
    }
    
    @Override
    public ItemStack jj(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.getHeldItemOffhand();
    }
    
    @Override
    public ItemStack je(final PlayerControllerMP playerControllerMP, final int n, final int n2, final int n3, final ClickType clickType, final EntityPlayer entityPlayer) {
        return playerControllerMP.windowClick(n, n2, n3, clickType, entityPlayer);
    }
    
    @Override
    public Slot jo(final net.minecraft.inventory.Container container, final int n) {
        return container.getSlot(n);
    }
    
    @Override
    public ItemStack jt(final Slot slot) {
        return slot.getStack();
    }
    
    @Override
    public void jn(final TextureManager textureManager, final ResourceLocation resourceLocation) {
        textureManager.bindTexture(resourceLocation);
    }
    
    @Override
    public void ji(final int n) {
        GL11.glPushAttrib(n);
    }
    
    @Override
    public void jp() {
        GL11.glPushMatrix();
    }
    
    @Override
    public void jr(final float n, final float n2, final float n3) {
        GL11.glTranslatef(n, n2, n3);
    }
    
    @Override
    public void jf(final float n, final float n2, final float n3, final float n4) {
        GL11.glColor4f(n, n2, n3, n4);
    }
    
    @Override
    public List jb(final FontRenderer fontRenderer, final String s, final int n) {
        return fontRenderer.listFormattedStringToWidth(s, n);
    }
    
    @Override
    public void jw() {
        GL11.glPopMatrix();
    }
    
    @Override
    public void jg() {
        GL11.glPopAttrib();
    }
    
    @Override
    public int ju(final String s, final int n) {
        return Integer.parseInt(s, n);
    }
    
    @Override
    public void jz(final FontRenderer fontRenderer, final String s, final int n, final int n2, final int n3, final int n4) {
        fontRenderer.drawSplitString(s, n, n2, n3, n4);
    }
    
    @Override
    public Instant jc() {
        return Instant.now();
    }
    
    @Override
    public Duration ja(final Temporal temporal, final Temporal temporal2) {
        return Duration.between(temporal, temporal2);
    }
    
    @Override
    public long jm(final Duration duration) {
        return duration.getSeconds();
    }
    
    @Override
    public String jh(final String s, final CharSequence charSequence, final CharSequence charSequence2) {
        return s.replace(charSequence, charSequence2);
    }
    
    @Override
    public boolean ed(final List list, final Collection collection) {
        return list.removeAll(collection);
    }
    
    @Override
    public boolean es(final List list, final Object o) {
        return list.contains(o);
    }
    
    @Override
    public Vec3d el(final EntityPlayer entityPlayer) {
        return entityPlayer.getPositionVector();
    }
    
    @Override
    public ItemStack ey(final ItemTooltipEvent itemTooltipEvent) {
        return itemTooltipEvent.getItemStack();
    }
    
    @Override
    public boolean ex(final ItemStack itemStack) {
        return itemStack.hasTagCompound();
    }
    
    @Override
    public boolean ek(final NBTTagCompound nbtTagCompound, final String s, final int n) {
        return nbtTagCompound.hasKey(s, n);
    }
    
    @Override
    public NBTTagCompound eq(final NBTTagCompound nbtTagCompound) {
        return nbtTagCompound.copy();
    }
    
    @Override
    public void ev(final NBTTagCompound nbtTagCompound, final String s, final String s2) {
        nbtTagCompound.setString(s, s2);
    }
    
    @Override
    public TileEntity ej(final World world, final NBTTagCompound nbtTagCompound) {
        return TileEntity.create(world, nbtTagCompound);
    }
    
    @Override
    public boolean ee(final TileEntity tileEntity, final Capability capability, final EnumFacing enumFacing) {
        return tileEntity.hasCapability(capability, enumFacing);
    }
    
    @Override
    public List eo(final ItemTooltipEvent itemTooltipEvent) {
        return itemTooltipEvent.getToolTip();
    }
    
    @Override
    public boolean et(final List list, final Object o) {
        return list.remove(o);
    }
    
    @Override
    public ItemStack en(final RenderTooltipEvent$PostText renderTooltipEvent$PostText) {
        return renderTooltipEvent$PostText.getStack();
    }
    
    @Override
    public int ei(final RenderTooltipEvent$PostText renderTooltipEvent$PostText) {
        return renderTooltipEvent$PostText.getX();
    }
    
    @Override
    public int ep(final RenderTooltipEvent$PostText renderTooltipEvent$PostText) {
        return renderTooltipEvent$PostText.getY();
    }
    
    @Override
    public Object er(final TileEntity tileEntity, final Capability capability, final EnumFacing enumFacing) {
        return tileEntity.getCapability(capability, enumFacing);
    }
    
    @Override
    public int ef(final IItemHandler itemHandler) {
        return itemHandler.getSlots();
    }
    
    @Override
    public int eb(final int n, final int n2) {
        return Math.min(n, n2);
    }
    
    @Override
    public int ew(final int n, final int n2) {
        return Math.max(n, n2);
    }
    
    @Override
    public List eg(final RenderTooltipEvent$PostText renderTooltipEvent$PostText) {
        return renderTooltipEvent$PostText.getLines();
    }
    
    @Override
    public int eu(final ScaledResolution scaledResolution) {
        return scaledResolution.getScaledWidth();
    }
    
    @Override
    public void ez() {
        RenderHelper.enableStandardItemLighting();
    }
    
    @Override
    public void ec() {
        GlStateManager.enableRescaleNormal();
    }
    
    @Override
    public void ea(final float n, final float n2, final float n3) {
        GlStateManager.color(n, n2, n3);
    }
    
    @Override
    public TextureManager em(final Minecraft minecraft) {
        return minecraft.getTextureManager();
    }
    
    @Override
    public void eh() {
        RenderHelper.disableStandardItemLighting();
    }
    
    @Override
    public Block od(final ItemBlock itemBlock) {
        return itemBlock.getBlock();
    }
    
    @Override
    public EnumDyeColor os(final BlockShulkerBox blockShulkerBox) {
        return blockShulkerBox.getColor();
    }
    
    @Override
    public int ol(final EnumDyeColor enumDyeColor) {
        return enumDyeColor.getDyeDamage();
    }
    
    @Override
    public RenderItem oy(final Minecraft minecraft) {
        return minecraft.getRenderItem();
    }
    
    @Override
    public void ox() {
        RenderHelper.enableGUIStandardItemLighting();
    }
    
    @Override
    public void ok() {
        GlStateManager.enableDepth();
    }
    
    @Override
    public ItemStack oq(final IItemHandler itemHandler, final int n) {
        return itemHandler.getStackInSlot(n);
    }
    
    @Override
    public void ov(final RenderItem renderItem, final ItemStack itemStack, final int n, final int n2) {
        renderItem.renderItemAndEffectIntoGUI(itemStack, n, n2);
    }
    
    @Override
    public void oj(final RenderItem renderItem, final FontRenderer fontRenderer, final ItemStack itemStack, final int n, final int n2) {
        renderItem.renderItemOverlays(fontRenderer, itemStack, n, n2);
    }
    
    @Override
    public void oe() {
        GlStateManager.disableDepth();
    }
    
    @Override
    public void oo() {
        GlStateManager.disableRescaleNormal();
    }
    
    @Override
    public void ot(final int n, final int n2, final float n3, final float n4, final int n5, final int n6, final float n7, final float n8) {
        Gui.drawModalRectWithCustomSizedTexture(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    @Override
    public ResourceLocation on(final Item item) {
        return item.getRegistryName();
    }
    
    @Override
    public boolean oi(final Class clazz) {
        return clazz.desiredAssertionStatus();
    }
    
    @Override
    public ImmutableSet op(final Object o, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6, final Object[] array) {
        return ImmutableSet.of(o, o2, o3, o4, o5, o6, array);
    }
    
    @Override
    public Stream or(final ImmutableSet set) {
        return set.stream();
    }
    
    @Override
    public Stream of(final Stream stream, final Function function) {
        return stream.map(function);
    }
    
    @Override
    public Object[] ob(final Stream stream, final IntFunction intFunction) {
        return stream.<Object>toArray(intFunction);
    }
    
    @Override
    public Stream ow(final Object[] array) {
        return Arrays.<Object>stream(array);
    }
    
    @Override
    public void og(final BufferedImage bufferedImage, final int n, final int n2, final int n3) {
        bufferedImage.setRGB(n, n2, n3);
    }
    
    @Override
    public void ou(final BufferedImage bufferedImage, final int n, final int n2, final int n3, final int n4, final int[] array, final int n5, final int n6) {
        bufferedImage.setRGB(n, n2, n3, n4, array, n5, n6);
    }
    
    @Override
    public boolean oz(final RenderedImage renderedImage, final String s, final File file) {
        return ImageIO.write(renderedImage, s, file);
    }
    
    @Override
    public String oc(final File file) {
        return file.getName();
    }
    
    @Override
    public Style oa(final ITextComponent textComponent) {
        return textComponent.getStyle();
    }
    
    @Override
    public String om(final File file) {
        return file.getAbsolutePath();
    }
    
    @Override
    public Style oh(final Style style, final ClickEvent clickEvent) {
        return style.setClickEvent(clickEvent);
    }
    
    @Override
    public Style td(final Style style, final Boolean underlined) {
        return style.setUnderlined(underlined);
    }
    
    @Override
    public void ts(final Object o, final int n, final Object o2, final int n2, final int n3) {
        System.arraycopy(o, n, o2, n2, n3);
    }
    
    @Override
    public StringBuilder tl(final StringBuilder sb, final int n) {
        return sb.append(n);
    }
    
    @Override
    public boolean ty(final File file) {
        return file.exists();
    }
    
    @Override
    public int tx(final MouseEvent mouseEvent) {
        return mouseEvent.getButton();
    }
    
    @Override
    public void tk(final NetworkManager networkManager, final Packet packet) {
        networkManager.sendPacket(packet);
    }
    
    @Override
    public void tq(final MouseEvent mouseEvent, final boolean canceled) {
        mouseEvent.setCanceled(canceled);
    }
    
    @Override
    public Block tv(final IBlockState blockState) {
        return blockState.getBlock();
    }
    
    @Override
    public Block tj(final int n) {
        return Block.getBlockById(n);
    }
    
    @Override
    public void te(final TileEntityShulkerBox tileEntityShulkerBox, final World world) {
        tileEntityShulkerBox.setWorld(world);
    }
    
    @Override
    public TileEntity to(final WorldClient worldClient, final BlockPos blockPos) {
        return worldClient.getTileEntity(blockPos);
    }
    
    @Override
    public NBTTagCompound tt(final TileEntity tileEntity) {
        return tileEntity.getTileData();
    }
    
    @Override
    public NBTTagCompound tn(final NBTTagCompound nbtTagCompound, final String s) {
        return nbtTagCompound.getCompoundTag(s);
    }
    
    @Override
    public void ti(final TileEntityShulkerBox tileEntityShulkerBox, final NBTTagCompound nbtTagCompound) {
        tileEntityShulkerBox.readFromNBT(nbtTagCompound);
    }
    
    @Override
    public String tp(final NBTTagCompound nbtTagCompound) {
        return nbtTagCompound.toString();
    }
    
    @Override
    public void tr(final LaunchClassLoader launchClassLoader, final URL url) {
        launchClassLoader.addURL(url);
    }
    
    @Override
    public Class tf(final LaunchClassLoader launchClassLoader, final String s) {
        return launchClassLoader.loadClass(s);
    }
    
    @Override
    public void tb(final HashMap hashMap) {
        hashMap.clear();
    }
    
    @Override
    public Iterable tw(final BlockPos blockPos, final BlockPos blockPos2) {
        return BlockPos.getAllInBox(blockPos, blockPos2);
    }
    
    @Override
    public Iterator tg(final Iterable iterable) {
        return iterable.iterator();
    }
    
    @Override
    public Object tu(final HashMap hashMap, final Object o, final Object o2) {
        return hashMap.put(o, o2);
    }
    
    @Override
    public boolean tz(final Material material) {
        return material.blocksMovement();
    }
    
    @Override
    public boolean tc(final Material material) {
        return material.isSolid();
    }
    
    @Override
    public Set ta(final HashMap hashMap) {
        return hashMap.entrySet();
    }
    
    @Override
    public Object tm(final Map.Entry entry) {
        return entry.getValue();
    }
    
    @Override
    public void th(final float n) {
        GL11.glLineWidth(n);
    }
    
    @Override
    public Object nd(final Map.Entry entry) {
        return entry.getKey();
    }
    
    @Override
    public void ns(final AxisAlignedBB axisAlignedBB, final float n, final float n2, final float n3, final float n4) {
        RenderGlobal.renderFilledBox(axisAlignedBB, n, n2, n3, n4);
    }
    
    @Override
    public void nl(final AxisAlignedBB axisAlignedBB, final float n, final float n2, final float n3, final float n4) {
        RenderGlobal.drawSelectionBoundingBox(axisAlignedBB, n, n2, n3, n4);
    }
    
    @Override
    public SoundHandler ny(final Minecraft minecraft) {
        return minecraft.getSoundHandler();
    }
    
    @Override
    public Object nx(final Class clazz, final Object o, final String[] array) {
        return ObfuscationReflectionHelper.getPrivateValue(clazz, o, array);
    }
    
    @Override
    public void nk(final SoundManager soundManager) {
        soundManager.reloadSoundSystem();
    }
    
    @Override
    public SoundEvent nq(final SPacketSoundEffect sPacketSoundEffect) {
        return sPacketSoundEffect.getSound();
    }
    
    @Override
    public Entity nv(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.getRidingEntity();
    }
    
    @Override
    public void nj(final EntityPlayerSP entityPlayerSP) {
        entityPlayerSP.dismountRidingEntity();
    }
    
    @Override
    public void ne(final WorldClient worldClient, final Entity entity) {
        worldClient.removeEntity(entity);
    }
    
    @Override
    public boolean no(final WorldClient worldClient, final Entity entity) {
        return worldClient.spawnEntity(entity);
    }
    
    @Override
    public boolean nt(final EntityPlayerSP entityPlayerSP, final Entity entity) {
        return entityPlayerSP.startRiding(entity);
    }
    
    @Override
    public GuiScreen nn(final GuiOpenEvent guiOpenEvent) {
        return guiOpenEvent.getGui();
    }
    
    @Override
    public void ni(final GuiOpenEvent guiOpenEvent, final GuiScreen gui) {
        guiOpenEvent.setGui(gui);
    }
    
    @Override
    public Double np(final double n) {
        return n;
    }
    
    @Override
    public UUID nr(final GameProfile gameProfile) {
        return gameProfile.getId();
    }
    
    @Override
    public EntityPlayer nf(final WorldClient worldClient, final UUID uuid) {
        return worldClient.getPlayerEntityByUUID(uuid);
    }
    
    @Override
    public AxisAlignedBB nb(final EntityPlayer entityPlayer) {
        return entityPlayer.getEntityBoundingBox();
    }
    
    @Override
    public Object nw(final HashMap hashMap, final Object o) {
        return hashMap.get(o);
    }
    
    @Override
    public Object ng(final HashMap hashMap, final Object o) {
        return hashMap.remove(o);
    }
    
    @Override
    public String nu(final String s, final Object[] array) {
        return String.format(s, array);
    }
    
    @Override
    public int nz(final HashMap hashMap) {
        return hashMap.size();
    }
    
    @Override
    public void nc(final HashMap hashMap, final BiConsumer biConsumer) {
        hashMap.forEach(biConsumer);
    }
    
    @Override
    public Vec3d na(final AxisAlignedBB axisAlignedBB) {
        return axisAlignedBB.getCenter();
    }
    
    @Override
    public double nm(final EntityPlayerSP entityPlayerSP, final double n, final double n2, final double n3) {
        return entityPlayerSP.getDistanceSq(n, n2, n3);
    }
    
    @Override
    public RenderManager nh(final Minecraft minecraft) {
        return minecraft.getRenderManager();
    }
    
    @Override
    public Vec3d id(final Vec3d vec3d, final Vec3d vec3d2) {
        return vec3d.subtract(vec3d2);
    }
    
    @Override
    public Vec3d is(final Vec3d vec3d) {
        return vec3d.normalize();
    }
    
    @Override
    public double il(final double n, final double n2) {
        return Math.max(n, n2);
    }
    
    @Override
    public void iy(final double n, final double n2, final double n3) {
        GL11.glTranslated(n, n2, n3);
    }
    
    @Override
    public void ix(final float n, final float n2, final float n3) {
        GL11.glNormal3f(n, n2, n3);
    }
    
    @Override
    public void ik(final float n, final float n2, final float n3, final float n4) {
        GL11.glRotatef(n, n2, n3, n4);
    }
    
    @Override
    public void iq(final double n, final double n2, final double n3) {
        GL11.glScaled(n, n2, n3);
    }
    
    @Override
    public void iv(final int n) {
        GL11.glDisable(n);
    }
    
    @Override
    public void ij(final int n) {
        GL11.glEnable(n);
    }
    
    @Override
    public void ie(final int n, final int n2) {
        GL11.glBlendFunc(n, n2);
    }
    
    @Override
    public StringBuilder io(final StringBuilder sb, final double n) {
        return sb.append(n);
    }
    
    @Override
    public int it(final FontRenderer fontRenderer, final String s, final float n, final float n2, final int n3) {
        return fontRenderer.drawStringWithShadow(s, n, n2, n3);
    }
    
    @Override
    public int in(final Color color) {
        return color.getRed();
    }
    
    @Override
    public int ii(final Color color) {
        return color.getBlue();
    }
    
    @Override
    public int ip(final Color color) {
        return color.getAlpha();
    }
    
    @Override
    public void ir(final ItemStack itemStack, final NBTTagCompound tagCompound) {
        itemStack.setTagCompound(tagCompound);
    }
    
    @Override
    public void if(final NBTTagCompound nbtTagCompound, final String s, final boolean b) {
        nbtTagCompound.setBoolean(s, b);
    }
    
    @Override
    public void ib(final NBTTagCompound nbtTagCompound, final String s, final byte b) {
        nbtTagCompound.setByte(s, b);
    }
    
    @Override
    public void iw(final NBTTagCompound nbtTagCompound, final String s, final short n) {
        nbtTagCompound.setShort(s, n);
    }
    
    @Override
    public void ig(final NBTTagCompound nbtTagCompound, final String s, final int n) {
        nbtTagCompound.setInteger(s, n);
    }
    
    @Override
    public void iu(final NBTTagCompound nbtTagCompound, final String s, final long n) {
        nbtTagCompound.setLong(s, n);
    }
    
    @Override
    public void iz(final NBTTagCompound nbtTagCompound, final String s, final float n) {
        nbtTagCompound.setFloat(s, n);
    }
    
    @Override
    public void ic(final NBTTagCompound nbtTagCompound, final String s, final double n) {
        nbtTagCompound.setDouble(s, n);
    }
    
    @Override
    public void ia(final NBTTagCompound nbtTagCompound, final String s, final NBTBase nbtBase) {
        nbtTagCompound.setTag(s, nbtBase);
    }
    
    @Override
    public boolean im(final NBTTagCompound nbtTagCompound, final String s) {
        return nbtTagCompound.hasKey(s);
    }
    
    @Override
    public boolean ih(final NBTTagCompound nbtTagCompound, final String s) {
        return nbtTagCompound.getBoolean(s);
    }
    
    @Override
    public byte pd(final NBTTagCompound nbtTagCompound, final String s) {
        return nbtTagCompound.getByte(s);
    }
    
    @Override
    public short ps(final NBTTagCompound nbtTagCompound, final String s) {
        return nbtTagCompound.getShort(s);
    }
    
    @Override
    public int pl(final NBTTagCompound nbtTagCompound, final String s) {
        return nbtTagCompound.getInteger(s);
    }
    
    @Override
    public long py(final NBTTagCompound nbtTagCompound, final String s) {
        return nbtTagCompound.getLong(s);
    }
    
    @Override
    public float px(final NBTTagCompound nbtTagCompound, final String s) {
        return nbtTagCompound.getFloat(s);
    }
    
    @Override
    public double pk(final NBTTagCompound nbtTagCompound, final String s) {
        return nbtTagCompound.getDouble(s);
    }
    
    @Override
    public String pq(final NBTTagCompound nbtTagCompound, final String s) {
        return nbtTagCompound.getString(s);
    }
    
    @Override
    public NBTTagList pv(final NBTTagCompound nbtTagCompound, final String s, final int n) {
        return nbtTagCompound.getTagList(s, n);
    }
    
    @Override
    public InputStream pj(final Class clazz, final String s) {
        return clazz.getResourceAsStream(s);
    }
    
    @Override
    public Set pe(final Object o) {
        return Collections.<Object>singleton(o);
    }
    
    @Override
    public long po() {
        return System.currentTimeMillis();
    }
    
    @Override
    public void pt(final GuiOpenEvent guiOpenEvent, final boolean canceled) {
        guiOpenEvent.setCanceled(canceled);
    }
    
    @Override
    public String[] pn(final CPacketUpdateSign cPacketUpdateSign) {
        return cPacketUpdateSign.getLines();
    }
    
    @Override
    public String pi(final String s, final int n, final int n2) {
        return s.substring(n, n2);
    }
    
    @Override
    public void pp(final Class clazz, final Object o, final Object o2, final String[] array) {
        ObfuscationReflectionHelper.setPrivateValue(clazz, o, o2, array);
    }
    
    @Override
    public IntStream pr(final Random random, final int n, final int n2) {
        return random.ints(n, n2);
    }
    
    @Override
    public IntStream pf(final IntStream intStream, final IntUnaryOperator intUnaryOperator) {
        return intStream.map(intUnaryOperator);
    }
    
    @Override
    public IntStream pb(final IntStream intStream, final long n) {
        return intStream.limit(n);
    }
    
    @Override
    public Stream pw(final IntStream intStream, final IntFunction intFunction) {
        return intStream.<Object>mapToObj((IntFunction<?>)intFunction);
    }
    
    @Override
    public Collector pg() {
        return Collectors.joining();
    }
    
    @Override
    public String pu(final char c) {
        return String.valueOf(c);
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType pz(final RenderGameOverlayEvent renderGameOverlayEvent) {
        return renderGameOverlayEvent.getType();
    }
    
    @Override
    public void pc(final RenderGameOverlayEvent renderGameOverlayEvent, final boolean canceled) {
        renderGameOverlayEvent.setCanceled(canceled);
    }
    
    @Override
    public int pa(final ScaledResolution scaledResolution) {
        return scaledResolution.getScaledHeight();
    }
    
    @Override
    public float pm(final Float n) {
        return n;
    }
    
    @Override
    public float ph(final RenderGameOverlayEvent renderGameOverlayEvent) {
        return renderGameOverlayEvent.getPartialTicks();
    }
    
    @Override
    public boolean rd(final PlayerControllerMP playerControllerMP) {
        return playerControllerMP.isSpectator();
    }
    
    @Override
    public boolean rs(final Block block, final IBlockState blockState) {
        return block.hasTileEntity(blockState);
    }
    
    @Override
    public Entity rl(final Minecraft minecraft) {
        return minecraft.getRenderViewEntity();
    }
    
    @Override
    public void ry(final int n) {
        OpenGlHelper.renderDirections(n);
    }
    
    @Override
    public Entity rx(final PlaySoundAtEntityEvent playSoundAtEntityEvent) {
        return playSoundAtEntityEvent.getEntity();
    }
    
    @Override
    public SoundEvent rk(final PlaySoundAtEntityEvent playSoundAtEntityEvent) {
        return playSoundAtEntityEvent.getSound();
    }
    
    @Override
    public Vec3d rq(final Entity entity) {
        return entity.getPositionVector();
    }
    
    @Override
    public String rv(final ClientChatEvent clientChatEvent) {
        return clientChatEvent.getMessage();
    }
    
    @Override
    public List rj(final List list, final int n, final int n2) {
        return list.subList(n, n2);
    }
    
    @Override
    public Object[] re(final ArrayList list, final Object[] array) {
        return list.<Object>toArray(array);
    }
    
    @Override
    public void ro(final ClientChatEvent clientChatEvent, final boolean canceled) {
        clientChatEvent.setCanceled(canceled);
    }
    
    @Override
    public String rt(final CPacketChatMessage cPacketChatMessage) {
        return cPacketChatMessage.getMessage();
    }
    
    @Override
    public RenderBlockOverlayEvent$OverlayType[] rn() {
        return RenderBlockOverlayEvent$OverlayType.values();
    }
    
    @Override
    public int ri(final RenderBlockOverlayEvent$OverlayType renderBlockOverlayEvent$OverlayType) {
        return renderBlockOverlayEvent$OverlayType.ordinal();
    }
    
    @Override
    public ImmutableSetMultimap rp(final WorldClient worldClient) {
        return worldClient.getPersistentChunks();
    }
    
    @Override
    public ImmutableMultiset rr(final ImmutableSetMultimap immutableSetMultimap) {
        return immutableSetMultimap.keys();
    }
    
    @Override
    public UnmodifiableIterator rf(final ImmutableMultiset immutableMultiset) {
        return immutableMultiset.iterator();
    }
    
    @Override
    public Chunk rb(final WorldClient worldClient, final int n, final int n2) {
        return worldClient.getChunk(n, n2);
    }
    
    @Override
    public Map rw(final Chunk chunk) {
        return chunk.getTileEntityMap();
    }
    
    @Override
    public Collection rg(final Map map) {
        return map.values();
    }
    
    @Override
    public int ru(final Map map) {
        return map.size();
    }
    
    @Override
    public void rz(final PrintStream printStream, final int n) {
        printStream.println(n);
    }
    
    @Override
    public Iterator rc(final Collection collection) {
        return collection.iterator();
    }
    
    @Override
    public void ra(final PrintStream printStream, final boolean b) {
        printStream.println(b);
    }
    
    @Override
    public Integer rm(final int n) {
        return n;
    }
    
    @Override
    public String rh(final boolean b) {
        return String.valueOf(b);
    }
    
    @Override
    public PrintStream fd(final PrintStream printStream, final String s, final Object[] array) {
        return printStream.printf(s, array);
    }
    
    @Override
    public ServerData fs(final Minecraft minecraft) {
        return minecraft.getCurrentServerData();
    }
    
    @Override
    public void fl(final BufferedWriter bufferedWriter, final String s) {
        bufferedWriter.write(s);
    }
    
    @Override
    public void fy(final BufferedWriter bufferedWriter) {
        bufferedWriter.close();
    }
    
    @Override
    public void fx(final FileWriter fileWriter) {
        fileWriter.close();
    }
    
    @Override
    public BlockPos fk(final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock) {
        return cPacketPlayerTryUseItemOnBlock.getPos();
    }
    
    @Override
    public EnumFacing fq(final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock) {
        return cPacketPlayerTryUseItemOnBlock.getDirection();
    }
    
    @Override
    public EnumHand fv(final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock) {
        return cPacketPlayerTryUseItemOnBlock.getHand();
    }
    
    @Override
    public float fj(final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock) {
        return cPacketPlayerTryUseItemOnBlock.getFacingX();
    }
    
    @Override
    public float fe(final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock) {
        return cPacketPlayerTryUseItemOnBlock.getFacingY();
    }
    
    @Override
    public float fo(final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock) {
        return cPacketPlayerTryUseItemOnBlock.getFacingZ();
    }
    
    @Override
    public EnumHand ft(final CPacketPlayerTryUseItem cPacketPlayerTryUseItem) {
        return cPacketPlayerTryUseItem.getHand();
    }
    
    @Override
    public CPacketUseEntity$Action fn(final CPacketUseEntity cPacketUseEntity) {
        return cPacketUseEntity.getAction();
    }
    
    @Override
    public Entity fi(final CPacketUseEntity cPacketUseEntity, final World world) {
        return cPacketUseEntity.getEntityFromWorld(world);
    }
    
    @Override
    public boolean fp(final HashBiMap hashBiMap, final Object o) {
        return hashBiMap.containsKey(o);
    }
    
    @Override
    public Object fr(final HashBiMap hashBiMap, final Object o) {
        return hashBiMap.get(o);
    }
    
    @Override
    public boolean ff(final HashBiMap hashBiMap, final Object o) {
        return hashBiMap.containsValue(o);
    }
    
    @Override
    public BiMap fb(final HashBiMap hashBiMap) {
        return hashBiMap.inverse();
    }
    
    @Override
    public Object fw(final BiMap biMap, final Object o) {
        return biMap.get(o);
    }
    
    @Override
    public InputStream fg(final URL url) {
        return url.openStream();
    }
    
    @Override
    public Object fu(final HashBiMap hashBiMap, final Object o, final Object o2) {
        return hashBiMap.put(o, o2);
    }
    
    @Override
    public StringBuilder fz(final StringBuilder sb, final int n, final char c) {
        return sb.insert(n, c);
    }
    
    @Override
    public URLConnection fc(final URL url) {
        return url.openConnection();
    }
    
    @Override
    public void fa(final HttpsURLConnection httpsURLConnection) {
        httpsURLConnection.connect();
    }
    
    @Override
    public void fm(final HttpsURLConnection httpsURLConnection) {
        httpsURLConnection.disconnect();
    }
    
    @Override
    public void fh(final IOException ex) {
        ex.printStackTrace();
    }
    
    @Override
    public void bd(final long n) {
        Thread.sleep(n);
    }
    
    @Override
    public HashBiMap bs() {
        return HashBiMap.create();
    }
    
    @Override
    public void bl(final RenderSpecificHandEvent renderSpecificHandEvent, final boolean canceled) {
        renderSpecificHandEvent.setCanceled(canceled);
    }
    
    @Override
    public EnumHand by(final RenderSpecificHandEvent renderSpecificHandEvent) {
        return renderSpecificHandEvent.getHand();
    }
    
    @Override
    public float bx(final Double n) {
        return n.floatValue();
    }
    
    @Override
    public ITextComponent bk(final TextComponentString textComponentString, final ITextComponent textComponent) {
        return textComponentString.appendSibling(textComponent);
    }
    
    @Override
    public void bq(final ClientChatReceivedEvent clientChatReceivedEvent, final ITextComponent message) {
        clientChatReceivedEvent.setMessage(message);
    }
    
    @Override
    public ITextComponent bv(final String s) {
        return ITextComponent$Serializer.jsonToComponent(s);
    }
    
    @Override
    public Session bj(final Minecraft minecraft) {
        return minecraft.getSession();
    }
    
    @Override
    public String be(final Session session) {
        return session.getUsername();
    }
    
    @Override
    public void bo(final NBTTagList list, final NBTBase nbtBase) {
        list.appendTag(nbtBase);
    }
    
    @Override
    public void bt(final ItemStack itemStack, final String s, final NBTBase nbtBase) {
        itemStack.setTagInfo(s, nbtBase);
    }
    
    @Override
    public long bn(final Long n) {
        return n;
    }
    
    @Override
    public Item bi(final Block block) {
        return Item.getItemFromBlock(block);
    }
    
    @Override
    public boolean bp(final Object[] array, final Object objectToFind) {
        return ArrayUtils.contains(array, objectToFind);
    }
    
    @Override
    public double br(final double n) {
        return Math.floor(n);
    }
    
    @Override
    public long bf() {
        return System.nanoTime();
    }
    
    @Override
    public int bb(final float n, final float n2, final float n3) {
        return Color.HSBtoRGB(n, n2, n3);
    }
    
    @Override
    public String bw(final int n) {
        return Integer.toHexString(n);
    }
    
    @Override
    public long bg(final String s, final int n) {
        return Long.parseLong(s, n);
    }
    
    @Override
    public int bu(final Color color) {
        return color.getGreen();
    }
    
    @Override
    public double bz(final double n, final double n2) {
        return Math.pow(n, n2);
    }
    
    @Override
    public Clipboard bc(final Toolkit toolkit) {
        return toolkit.getSystemClipboard();
    }
    
    @Override
    public void ba(final Clipboard clipboard, final Transferable transferable, final ClipboardOwner clipboardOwner) {
        clipboard.setContents(transferable, clipboardOwner);
    }
    
    @Override
    public float bm(final Minecraft minecraft) {
        return minecraft.getRenderPartialTicks();
    }
    
    @Override
    public Vec3d bh(final EntityPlayerSP entityPlayerSP, final float n) {
        return entityPlayerSP.getPositionEyes(n);
    }
    
    @Override
    public Vec3d wd(final Entity entity, final double n) {
        return ActiveRenderInfo.projectViewFromEntity(entity, n);
    }
    
    @Override
    public void ws(final Minecraft minecraft, final Entity renderViewEntity) {
        minecraft.setRenderViewEntity(renderViewEntity);
    }
    
    @Override
    public Object wl(final Map map, final Object o) {
        return map.get(o);
    }
    
    @Override
    public Thread wy() {
        return Thread.currentThread();
    }
    
    @Override
    public boolean wx(final Thread thread) {
        return thread.isInterrupted();
    }
    
    @Override
    public boolean wk(final Minecraft minecraft) {
        return minecraft.isIntegratedServerRunning();
    }
    
    @Override
    public Integer wq(final String s) {
        return Integer.valueOf(s);
    }
    
    @Override
    public boolean wv(final String s, final CharSequence charSequence) {
        return s.contains(charSequence);
    }
    
    @Override
    public String wj(final String s, final int n) {
        return s.substring(n);
    }
    
    @Override
    public int we(final String s) {
        return Integer.parseInt(s);
    }
    
    @Override
    public void wo(final Throwable t) {
        t.printStackTrace();
    }
    
    @Override
    public void wt(final InterruptedException ex) {
        ex.printStackTrace();
    }
    
    @Override
    public boolean wn() {
        return Keyboard.getEventKeyState();
    }
    
    @Override
    public int wi() {
        return Keyboard.getEventKey();
    }
    
    @Override
    public String wp(final int n) {
        return Keyboard.getKeyName(n);
    }
    
    @Override
    public UUID wr(final EntityPlayer entityPlayer) {
        return entityPlayer.getUniqueID();
    }
    
    @Override
    public NetworkPlayerInfo wf(final NetHandlerPlayClient netHandlerPlayClient, final UUID uuid) {
        return netHandlerPlayClient.getPlayerInfo(uuid);
    }
    
    @Override
    public int wb(final NetworkPlayerInfo networkPlayerInfo) {
        return networkPlayerInfo.getResponseTime();
    }
    
    @Override
    public boolean ww(final File file, final File file2) {
        return file.renameTo(file2);
    }
    
    @Override
    public void wg(final Entity entity, final boolean alwaysRenderNameTag) {
        entity.setAlwaysRenderNameTag(alwaysRenderNameTag);
    }
    
    @Override
    public int wu(final EntityItem entityItem) {
        return entityItem.getAge();
    }
    
    @Override
    public String wz(final double n) {
        return String.valueOf(n);
    }
    
    @Override
    public void wc(final Entity entity, final String customNameTag) {
        entity.setCustomNameTag(customNameTag);
    }
    
    @Override
    public FloatBuffer wa(final int n) {
        return BufferUtils.createFloatBuffer(n);
    }
    
    @Override
    public float wm(final FloatBuffer floatBuffer, final int n) {
        return floatBuffer.get(n);
    }
    
    @Override
    public double wh(final double n) {
        return Math.atan(n);
    }
    
    @Override
    public double gd(final double n) {
        return Math.toDegrees(n);
    }
    
    @Override
    public int gs(final IntBuffer intBuffer, final int n) {
        return intBuffer.get(n);
    }
    
    @Override
    public double gl(final double n) {
        return Math.toRadians(n);
    }
    
    @Override
    public double gy(final double n) {
        return Math.tan(n);
    }
    
    @Override
    public double gx(final double n, final double n2) {
        return Math.atan2(n, n2);
    }
    
    @Override
    public FloatBuffer gk(final FloatBuffer floatBuffer) {
        return floatBuffer.asReadOnlyBuffer();
    }
    
    @Override
    public double gq(final double n) {
        return Math.sqrt(n);
    }
    
    @Override
    public double gv(final double n) {
        return Math.acos(n);
    }
    
    @Override
    public double gj(final double n) {
        return Math.cos(n);
    }
    
    @Override
    public double ge(final double n) {
        return Math.sin(n);
    }
    
    @Override
    public float go(final EntityViewRenderEvent$FOVModifier entityViewRenderEvent$FOVModifier) {
        return entityViewRenderEvent$FOVModifier.getFOV();
    }
    
    @Override
    public float gt(final float n, final float n2) {
        return Math.min(n, n2);
    }
    
    @Override
    public void gn(final EntityViewRenderEvent$FOVModifier entityViewRenderEvent$FOVModifier, final float fov) {
        entityViewRenderEvent$FOVModifier.setFOV(fov);
    }
    
    @Override
    public String gi(final HashMap hashMap) {
        return hashMap.toString();
    }
    
    @Override
    public Object gp(final HashMap hashMap, final Object o, final Object o2) {
        return hashMap.getOrDefault(o, o2);
    }
    
    @Override
    public GameProfile gr(final NetworkPlayerInfo networkPlayerInfo) {
        return networkPlayerInfo.getGameProfile();
    }
    
    @Override
    public String gf(final GameProfile gameProfile) {
        return gameProfile.getName();
    }
    
    @Override
    public double gb(final SPacketEntityTeleport sPacketEntityTeleport) {
        return sPacketEntityTeleport.getX();
    }
    
    @Override
    public double gw(final SPacketEntityTeleport sPacketEntityTeleport) {
        return sPacketEntityTeleport.getY();
    }
    
    @Override
    public double gg(final SPacketEntityTeleport sPacketEntityTeleport) {
        return sPacketEntityTeleport.getZ();
    }
    
    @Override
    public double gu(final EntityPlayerSP entityPlayerSP, final BlockPos blockPos) {
        return entityPlayerSP.getDistanceSqToCenter(blockPos);
    }
    
    @Override
    public int gz(final SPacketEntityTeleport sPacketEntityTeleport) {
        return sPacketEntityTeleport.getEntityId();
    }
    
    @Override
    public Object gc(final Object o) {
        return Objects.<Object>requireNonNull(o);
    }
    
    @Override
    public Annotation ga(final Class clazz, final Class clazz2) {
        return clazz.<Annotation>getAnnotation(clazz2);
    }
    
    @Override
    public void gm(final EventBus eventBus, final Object o) {
        eventBus.unregister(o);
    }
    
    @Override
    public boolean gh(final EventBus eventBus, final Event event) {
        return eventBus.post(event);
    }
    
    @Override
    public String ud(final Throwable t) {
        return t.getMessage();
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType us(final RenderGameOverlayEvent$Post renderGameOverlayEvent$Post) {
        return renderGameOverlayEvent$Post.getType();
    }
    
    @Override
    public StringBuilder ul(final StringBuilder sb, final boolean b) {
        return sb.append(b);
    }
    
    @Override
    public float uy(final DrawBlockHighlightEvent drawBlockHighlightEvent) {
        return drawBlockHighlightEvent.getPartialTicks();
    }
    
    @Override
    public EntityPlayer ux(final DrawBlockHighlightEvent drawBlockHighlightEvent) {
        return drawBlockHighlightEvent.getPlayer();
    }
    
    @Override
    public RayTraceResult uk(final DrawBlockHighlightEvent drawBlockHighlightEvent) {
        return drawBlockHighlightEvent.getTarget();
    }
    
    @Override
    public void uq() {
        GlStateManager.enableBlend();
    }
    
    @Override
    public void uv(final GlStateManager$SourceFactor glStateManager$SourceFactor, final GlStateManager$DestFactor glStateManager$DestFactor, final GlStateManager$SourceFactor glStateManager$SourceFactor2, final GlStateManager$DestFactor glStateManager$DestFactor2) {
        GlStateManager.tryBlendFuncSeparate(glStateManager$SourceFactor, glStateManager$DestFactor, glStateManager$SourceFactor2, glStateManager$DestFactor2);
    }
    
    @Override
    public void uj(final float n) {
        GlStateManager.glLineWidth(n);
    }
    
    @Override
    public void ue() {
        GlStateManager.disableTexture2D();
    }
    
    @Override
    public void uo(final boolean b) {
        GlStateManager.depthMask(b);
    }
    
    @Override
    public WorldBorder ut(final WorldClient worldClient) {
        return worldClient.getWorldBorder();
    }
    
    @Override
    public boolean un(final WorldBorder worldBorder, final BlockPos blockPos) {
        return worldBorder.contains(blockPos);
    }
    
    @Override
    public AxisAlignedBB ui(final IBlockState blockState, final World world, final BlockPos blockPos) {
        return blockState.getSelectedBoundingBox(world, blockPos);
    }
    
    @Override
    public AxisAlignedBB up(final AxisAlignedBB axisAlignedBB, final double n) {
        return axisAlignedBB.grow(n);
    }
    
    @Override
    public AxisAlignedBB ur(final AxisAlignedBB axisAlignedBB, final double n, final double n2, final double n3) {
        return axisAlignedBB.offset(n, n2, n3);
    }
    
    @Override
    public int uf(final int n) {
        return Math.abs(n);
    }
    
    @Override
    public void ub() {
        GlStateManager.enableTexture2D();
    }
    
    @Override
    public void uw() {
        GlStateManager.disableBlend();
    }
    
    @Override
    public void ug(final DrawBlockHighlightEvent drawBlockHighlightEvent, final boolean canceled) {
        drawBlockHighlightEvent.setCanceled(canceled);
    }
    
    @Override
    public boolean uu(final int n) {
        return Keyboard.isKeyDown(n);
    }
    
    @Override
    public Logger uz(final String s) {
        return LogManager.getLogger(s);
    }
    
    @Override
    public Object uc(final Field field, final Object o) {
        return field.get(o);
    }
    
    @Override
    public Object[] ua(final Vector vector, final Object[] array) {
        return vector.<Object>toArray(array);
    }
    
    @Override
    public ClassLoader um() {
        return ClassLoader.getSystemClassLoader();
    }
    
    @Override
    public URL uh(final ClassLoader classLoader, final String s) {
        return classLoader.getResource(s);
    }
    
    @Override
    public String zd(final URL url) {
        return url.getFile();
    }
    
    @Override
    public File zs(final String s, final String s2) {
        return File.createTempFile(s, s2);
    }
    
    @Override
    public int zl(final InputStream inputStream, final byte[] array) {
        return inputStream.read(array);
    }
    
    @Override
    public void zy(final FileOutputStream fileOutputStream, final byte[] array, final int n, final int n2) {
        fileOutputStream.write(array, n, n2);
    }
    
    @Override
    public void zx(final FileOutputStream fileOutputStream) {
        fileOutputStream.close();
    }
    
    @Override
    public void zk(final InputStream inputStream) {
        inputStream.close();
    }
    
    @Override
    public void zq(final String s) {
        System.load(s);
    }
    
    @Override
    public Field zv(final Class clazz, final String s) {
        return clazz.getDeclaredField(s);
    }
    
    @Override
    public void zj(final Field field, final boolean accessible) {
        field.setAccessible(accessible);
    }
    
    @Override
    public int ze(final NonNullList list) {
        return list.size();
    }
    
    @Override
    public Object zo(final NonNullList list, final int n) {
        return list.get(n);
    }
    
    @Override
    public NonNullList zt(final net.minecraft.inventory.Container container) {
        return container.getInventory();
    }
    
    @Override
    public PotionEffect zn(final EntityPlayerSP entityPlayerSP, final Potion potion) {
        return entityPlayerSP.getActivePotionEffect(potion);
    }
    
    @Override
    public boolean zi(final EntityPlayerSP entityPlayerSP, final Potion potion) {
        return entityPlayerSP.isPotionActive(potion);
    }
    
    @Override
    public int zp(final PotionEffect potionEffect) {
        return potionEffect.getAmplifier();
    }
    
    @Override
    public void zr(final WorldClient worldClient) {
        worldClient.sendQuittingDisconnectingPacket();
    }
    
    @Override
    public void zf(final Minecraft minecraft, final WorldClient worldClient) {
        minecraft.loadWorld(worldClient);
    }
    
    @Override
    public boolean zb(final Minecraft minecraft) {
        return minecraft.isConnectedToRealms();
    }
    
    @Override
    public void zw(final RealmsBridge realmsBridge, final GuiScreen guiScreen) {
        realmsBridge.switchToRealms(guiScreen);
    }
    
    @Override
    public int zg(final FontRenderer fontRenderer, final String s, final float n, final float n2, final int n3, final boolean b) {
        return fontRenderer.drawString(s, n, n2, n3, b);
    }
    
    @Override
    public int zu(final FontRenderer fontRenderer, final char c) {
        return fontRenderer.getCharWidth(c);
    }
    
    @Override
    public String zz(final Class clazz) {
        return clazz.getName();
    }
    
    @Override
    public boolean zc(final HashMap hashMap, final Object o) {
        return hashMap.containsKey(o);
    }
    
    @Override
    public void za(final List list, final Comparator comparator) {
        list.sort(comparator);
    }
    
    @Override
    public String zm(final Session session) {
        return session.getSessionID();
    }
    
    @Override
    public GameProfile zh(final Session session) {
        return session.getProfile();
    }
    
    @Override
    public String cd(final String s, final String s2) {
        return s.concat(s2);
    }
    
    @Override
    public long cs(final Duration duration) {
        return duration.toMillis();
    }
    
    @Override
    public ItemStack cl(final InventoryPlayer inventoryPlayer, final int n) {
        return inventoryPlayer.removeStackFromSlot(n);
    }
    
    @Override
    public double cy(final Vec3d vec3d, final Vec3d vec3d2) {
        return vec3d.distanceTo(vec3d2);
    }
    
    @Override
    public ItemStack cx(final EntityPlayerSP entityPlayerSP, final EnumHand enumHand) {
        return entityPlayerSP.getHeldItem(enumHand);
    }
    
    @Override
    public String ck(final ItemStack itemStack) {
        return itemStack.getDisplayName();
    }
    
    @Override
    public EntityPlayer cq(final BlockEvent$BreakEvent blockEvent$BreakEvent) {
        return blockEvent$BreakEvent.getPlayer();
    }
    
    @Override
    public boolean cv(final EntityPlayer entityPlayer, final Object o) {
        return entityPlayer.equals(o);
    }
    
    @Override
    public IBlockState cj(final BlockEvent$BreakEvent blockEvent$BreakEvent) {
        return blockEvent$BreakEvent.getState();
    }
    
    @Override
    public String ce(final Block block) {
        return block.getLocalizedName();
    }
    
    @Override
    public Entity co(final AttackEntityEvent attackEntityEvent) {
        return attackEntityEvent.getTarget();
    }
    
    @Override
    public ITextComponent ct(final Entity entity) {
        return entity.getDisplayName();
    }
    
    @Override
    public byte[] cn(final String s, final Charset charset) {
        return s.getBytes(charset);
    }
    
    @Override
    public void ci(final HttpURLConnection httpURLConnection, final String requestMethod) {
        httpURLConnection.setRequestMethod(requestMethod);
    }
    
    @Override
    public void cp(final HttpURLConnection httpURLConnection, final String s, final String s2) {
        httpURLConnection.setRequestProperty(s, s2);
    }
    
    @Override
    public void cr(final HttpURLConnection httpURLConnection, final int fixedLengthStreamingMode) {
        httpURLConnection.setFixedLengthStreamingMode(fixedLengthStreamingMode);
    }
    
    @Override
    public void cf(final HttpURLConnection httpURLConnection, final boolean doInput) {
        httpURLConnection.setDoInput(doInput);
    }
    
    @Override
    public void cb(final HttpURLConnection httpURLConnection, final boolean doOutput) {
        httpURLConnection.setDoOutput(doOutput);
    }
    
    @Override
    public void cw(final HttpURLConnection httpURLConnection) {
        httpURLConnection.connect();
    }
    
    @Override
    public OutputStream cg(final HttpURLConnection httpURLConnection) {
        return httpURLConnection.getOutputStream();
    }
    
    @Override
    public void cu(final OutputStream outputStream, final byte[] array) {
        outputStream.write(array);
    }
    
    @Override
    public InputStream cz(final HttpURLConnection httpURLConnection) {
        return httpURLConnection.getInputStream();
    }
    
    @Override
    public Object cc(final Gson gson, final Reader json, final Class classOfT) {
        return gson.<Object>fromJson(json, (Class<Object>)classOfT);
    }
    
    @Override
    public void ca(final HttpURLConnection httpURLConnection) {
        httpURLConnection.disconnect();
    }
    
    @Override
    public JsonElement cm(final JsonObject jsonObject, final String memberName) {
        return jsonObject.get(memberName);
    }
    
    @Override
    public String ch(final JsonElement jsonElement) {
        return jsonElement.getAsString();
    }
    
    @Override
    public boolean ad(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.isElytraFlying();
    }
    
    @Override
    public boolean as(final WorldClient worldClient, final BlockPos blockPos) {
        return worldClient.isAirBlock(blockPos);
    }
    
    @Override
    public EntityLivingBase al(final LivingEvent$LivingJumpEvent livingEvent$LivingJumpEvent) {
        return livingEvent$LivingJumpEvent.getEntityLiving();
    }
    
    @Override
    public boolean ay(final EntityLivingBase entityLivingBase, final Object o) {
        return entityLivingBase.equals(o);
    }
    
    @Override
    public Object ax(final Class clazz) {
        return clazz.newInstance();
    }
    
    @Override
    public EnumFacing$Axis[] ak() {
        return EnumFacing$Axis.values();
    }
    
    @Override
    public int aq(final EnumFacing$Axis enumFacing$Axis) {
        return enumFacing$Axis.ordinal();
    }
    
    @Override
    public void av(final EntityPlayerSP entityPlayerSP) {
        entityPlayerSP.closeScreen();
    }
    
    @Override
    public int aj(final double n) {
        return MathHelper.floor(n);
    }
    
    @Override
    public NBTTagList ae(final ItemStack itemStack) {
        return itemStack.getEnchantmentTagList();
    }
    
    @Override
    public int ao(final NBTTagList list) {
        return list.getTagType();
    }
    
    @Override
    public int at(final NBTTagList list) {
        return list.tagCount();
    }
    
    @Override
    public NBTTagCompound an(final NBTTagList list, final int n) {
        return list.getCompoundTagAt(n);
    }
    
    @Override
    public EnumFacing ai(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.getHorizontalFacing();
    }
    
    @Override
    public EnumHand ap(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.getActiveHand();
    }
    
    @Override
    public int ar(final RenderGameOverlayEvent$ElementType renderGameOverlayEvent$ElementType) {
        return renderGameOverlayEvent$ElementType.ordinal();
    }
    
    @Override
    public boolean af(final char[] array, final char valueToFind) {
        return ArrayUtils.contains(array, valueToFind);
    }
    
    @Override
    public char[] ab(final int n) {
        return Character.toChars(n);
    }
    
    @Override
    public StringBuilder aw(final StringBuilder sb, final char[] array) {
        return sb.append(array);
    }
    
    @Override
    public int ag(final ArrayList list) {
        return list.size();
    }
    
    @Override
    public Object au(final ArrayList list, final int n) {
        return list.get(n);
    }
    
    @Override
    public int az(final Number n) {
        return n.intValue();
    }
    
    @Override
    public int ac(final String s, final String s2) {
        return s.compareTo(s2);
    }
    
    @Override
    public ITextComponent aa(final SPacketChat sPacketChat) {
        return sPacketChat.getChatComponent();
    }
    
    @Override
    public int am(final String s) {
        return s.length();
    }
    
    @Override
    public UserAuthentication ah(final YggdrasilAuthenticationService yggdrasilAuthenticationService, final Agent agent) {
        return yggdrasilAuthenticationService.createUserAuthentication(agent);
    }
    
    @Override
    public void md(final YggdrasilUserAuthentication yggdrasilUserAuthentication, final String username) {
        yggdrasilUserAuthentication.setUsername(username);
    }
    
    @Override
    public void ms(final YggdrasilUserAuthentication yggdrasilUserAuthentication, final String password) {
        yggdrasilUserAuthentication.setPassword(password);
    }
    
    @Override
    public void ml(final YggdrasilUserAuthentication yggdrasilUserAuthentication) {
        yggdrasilUserAuthentication.logIn();
    }
    
    @Override
    public GameProfile my(final YggdrasilUserAuthentication yggdrasilUserAuthentication) {
        return yggdrasilUserAuthentication.getSelectedProfile();
    }
    
    @Override
    public String mx(final YggdrasilUserAuthentication yggdrasilUserAuthentication) {
        return yggdrasilUserAuthentication.getAuthenticatedToken();
    }
    
    @Override
    public String mk(final Item item, final ItemStack itemStack) {
        return item.getItemStackDisplayName(itemStack);
    }
    
    @Override
    public String mq(final long n) {
        return Long.toString(n);
    }
    
    @Override
    public byte[] mv(final String s) {
        return s.getBytes();
    }
    
    @Override
    public void mj(final Random random, final byte[] array) {
        random.nextBytes(array);
    }
    
    @Override
    public Charset me(final String s) {
        return Charset.forName(s);
    }
    
    @Override
    public MessageDigest mo(final String s) {
        return MessageDigest.getInstance(s);
    }
    
    @Override
    public void mt(final MessageDigest messageDigest, final byte[] array) {
        messageDigest.update(array);
    }
    
    @Override
    public byte[] mn(final MessageDigest messageDigest) {
        return messageDigest.digest();
    }
    
    @Override
    public long mi(final Stream stream) {
        return stream.count();
    }
    
    @Override
    public StringBuilder mp(final StringBuilder sb, final long n) {
        return sb.append(n);
    }
    
    @Override
    public boolean mr(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.isInWater();
    }
    
    @Override
    public boolean mf(final KeyBinding keyBinding) {
        return keyBinding.isKeyDown();
    }
    
    @Override
    public float mb(final float n) {
        return MathHelper.sin(n);
    }
    
    @Override
    public float mw(final float n) {
        return MathHelper.cos(n);
    }
    
    @Override
    public void mg(final PlayerCapabilities playerCapabilities, final float flySpeed) {
        playerCapabilities.setFlySpeed(flySpeed);
    }
    
    @Override
    public UUID mu(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.getUniqueID();
    }
    
    @Override
    public boolean mz(final UUID uuid, final Object o) {
        return uuid.equals(o);
    }
    
    @Override
    public UUID mc(final Entity entity) {
        return entity.getUniqueID();
    }
    
    @Override
    public ScriptEngine ma(final ScriptEngineManager scriptEngineManager, final String s) {
        return scriptEngineManager.getEngineByName(s);
    }
    
    @Override
    public Object mm(final ScriptEngine scriptEngine, final String s) {
        return scriptEngine.eval(s);
    }
    
    @Override
    public void mh(final ScriptEngine scriptEngine, final String s, final Object o) {
        scriptEngine.put(s, o);
    }
    
    @Override
    public Object hd(final Invocable invocable, final String s, final Object[] array) {
        return invocable.invokeFunction(s, array);
    }
    
    @Override
    public void hs(final ItemStack itemStack, final int count) {
        itemStack.setCount(count);
    }
    
    @Override
    public MapData hl(final ItemMap itemMap, final ItemStack itemStack, final World world) {
        return itemMap.getMapData(itemStack, world);
    }
    
    @Override
    public Tessellator hy() {
        return Tessellator.getInstance();
    }
    
    @Override
    public BufferBuilder hx(final Tessellator tessellator) {
        return tessellator.getBuffer();
    }
    
    @Override
    public void hk(final BufferBuilder bufferBuilder, final int n, final VertexFormat vertexFormat) {
        bufferBuilder.begin(n, vertexFormat);
    }
    
    @Override
    public BufferBuilder hq(final BufferBuilder bufferBuilder, final double n, final double n2, final double n3) {
        return bufferBuilder.pos(n, n2, n3);
    }
    
    @Override
    public BufferBuilder hv(final BufferBuilder bufferBuilder, final double n, final double n2) {
        return bufferBuilder.tex(n, n2);
    }
    
    @Override
    public void hj(final BufferBuilder bufferBuilder) {
        bufferBuilder.endVertex();
    }
    
    @Override
    public void he(final Tessellator tessellator) {
        tessellator.draw();
    }
    
    @Override
    public MapItemRenderer ho(final EntityRenderer entityRenderer) {
        return entityRenderer.getMapItemRenderer();
    }
    
    @Override
    public void ht(final MapItemRenderer mapItemRenderer, final MapData mapData, final boolean b) {
        mapItemRenderer.renderMap(mapData, b);
    }
    
    @Override
    public void hn() {
        GlStateManager.enableLighting();
    }
    
    @Override
    public String hi(final ArrayList list) {
        return list.toString();
    }
    
    @Override
    public boolean hp(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.isRiding();
    }
    
    @Override
    public float hr(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.getEyeHeight();
    }
    
    @Override
    public EnumFacing[] hf() {
        return EnumFacing.values();
    }
    
    @Override
    public EnumFacing hb(final EnumFacing enumFacing) {
        return enumFacing.getOpposite();
    }
    
    @Override
    public boolean hw(final Block block, final IBlockState blockState, final boolean b) {
        return block.canCollideCheck(blockState, b);
    }
    
    @Override
    public Vec3d hg(final Vec3d vec3d, final double n, final double n2, final double n3) {
        return vec3d.add(n, n2, n3);
    }
    
    @Override
    public Vec3i hu(final EnumFacing enumFacing) {
        return enumFacing.getDirectionVec();
    }
    
    @Override
    public Vec3d hz(final Vec3d vec3d, final double n) {
        return vec3d.scale(n);
    }
    
    @Override
    public Vec3d hc(final Vec3d vec3d, final Vec3d vec3d2) {
        return vec3d.add(vec3d2);
    }
    
    @Override
    public double ha(final Vec3d vec3d, final Vec3d vec3d2) {
        return vec3d.squareDistanceTo(vec3d2);
    }
    
    @Override
    public float hm(final float n) {
        return MathHelper.wrapDegrees(n);
    }
    
    @Override
    public EnumActionResult hh(final PlayerControllerMP playerControllerMP, final EntityPlayerSP entityPlayerSP, final WorldClient worldClient, final BlockPos blockPos, final EnumFacing enumFacing, final Vec3d vec3d, final EnumHand enumHand) {
        return playerControllerMP.processRightClickBlock(entityPlayerSP, worldClient, blockPos, enumFacing, vec3d, enumHand);
    }
    
    @Override
    public double sdd(final double n) {
        return Math.asin(n);
    }
    
    @Override
    public Block sds(final Item item) {
        return Block.getBlockFromItem(item);
    }
    
    @Override
    public IBlockState sdl(final Block block) {
        return block.getDefaultState();
    }
    
    @Override
    public boolean sdy(final IBlockState blockState) {
        return blockState.isFullBlock();
    }
    
    @Override
    public RenderBlockOverlayEvent$OverlayType sdx(final RenderBlockOverlayEvent renderBlockOverlayEvent) {
        return renderBlockOverlayEvent.getOverlayType();
    }
    
    @Override
    public void sdk(final RenderBlockOverlayEvent renderBlockOverlayEvent, final boolean canceled) {
        renderBlockOverlayEvent.setCanceled(canceled);
    }
    
    @Override
    public BlockPos sdq(final EntityPlayer entityPlayer) {
        return entityPlayer.getPosition();
    }
    
    @Override
    public BlockPos sdv(final BlockPos blockPos, final double n, final double n2, final double n3) {
        return blockPos.add(n, n2, n3);
    }
    
    @Override
    public List sdj(final WorldClient worldClient, final Class clazz, final AxisAlignedBB axisAlignedBB) {
        return worldClient.getEntitiesWithinAABB(clazz, axisAlignedBB);
    }
    
    @Override
    public Float sde(final float n) {
        return n;
    }
    
    @Override
    public ChatType sdo(final SPacketChat sPacketChat) {
        return sPacketChat.getType();
    }
    
    @Override
    public String sdt(final ChatType chatType) {
        return chatType.name();
    }
    
    @Override
    public String sdn(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.getName();
    }
    
    @Override
    public String sdi(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.getDisplayNameString();
    }
    
    @Override
    public Runtime sdp() {
        return Runtime.getRuntime();
    }
    
    @Override
    public int sdr(final Runtime runtime) {
        return runtime.availableProcessors();
    }
    
    @Override
    public byte[] sdf(final MessageDigest messageDigest, final byte[] array) {
        return messageDigest.digest(array);
    }
    
    @Override
    public char sdb(final String s, final int n) {
        return s.charAt(n);
    }
    
    @Override
    public int sdw(final char c, final int n) {
        return Character.digit(c, n);
    }
    
    @Override
    public void sdg(final GuiIngame guiIngame, final ChatType chatType, final ITextComponent textComponent) {
        guiIngame.addChatMessage(chatType, textComponent);
    }
    
    @Override
    public void sdu(final ClientChatReceivedEvent clientChatReceivedEvent, final boolean canceled) {
        clientChatReceivedEvent.setCanceled(canceled);
    }
    
    @Override
    public String sdz(final String s) {
        return Pattern.quote(s);
    }
    
    @Override
    public ChatType sdc(final ClientChatReceivedEvent clientChatReceivedEvent) {
        return clientChatReceivedEvent.getType();
    }
    
    @Override
    public boolean sda(final ClientChatReceivedEvent clientChatReceivedEvent) {
        return clientChatReceivedEvent.isCanceled();
    }
    
    @Override
    public void sdm(final PlayerEvent$NameFormat playerEvent$NameFormat, final String displayname) {
        playerEvent$NameFormat.setDisplayname(displayname);
    }
    
    @Override
    public int sdh() {
        return Minecraft.getDebugFPS();
    }
    
    @Override
    public void ssd(final Stream stream, final Consumer consumer) {
        stream.forEach(consumer);
    }
    
    @Override
    public int sss(final SPacketEffect sPacketEffect) {
        return sPacketEffect.getSoundType();
    }
    
    @Override
    public BlockPos ssl(final SPacketEffect sPacketEffect) {
        return sPacketEffect.getSoundPos();
    }
    
    @Override
    public int ssy(final SPacketPlayerPosLook sPacketPlayerPosLook) {
        return sPacketPlayerPosLook.getTeleportId();
    }
    
    @Override
    public String ssx(final DecimalFormat decimalFormat, final long n) {
        return decimalFormat.format(n);
    }
    
    @Override
    public void ssk(final JButton button, final ActionListener actionListener) {
        button.addActionListener(actionListener);
    }
    
    @Override
    public Component ssq(final JPanel panel, final Component component) {
        return panel.add(component);
    }
    
    @Override
    public Component ssv(final JFrame frame, final Component component) {
        return frame.add(component);
    }
    
    @Override
    public void ssj(final JFrame frame, final boolean alwaysOnTop) {
        frame.setAlwaysOnTop(alwaysOnTop);
    }
    
    @Override
    public String sse(final ActionEvent actionEvent) {
        return actionEvent.getActionCommand();
    }
    
    @Override
    public void sso(final Consumer consumer, final Object o) {
        consumer.accept(o);
    }
    
    @Override
    public void sst(final JFrame frame, final AWTEvent awtEvent) {
        frame.dispatchEvent(awtEvent);
    }
    
    @Override
    public void ssn(final ArrayList list, final Consumer consumer) {
        list.forEach(consumer);
    }
    
    @Override
    public int ssi(final int n, final int n2) {
        return Integer.compare(n, n2);
    }
    
    @Override
    public BlockPos ssp(final TileEntityChest tileEntityChest) {
        return tileEntityChest.getPos();
    }
    
    @Override
    public AxisAlignedBB ssr(final AxisAlignedBB axisAlignedBB, final AxisAlignedBB axisAlignedBB2) {
        return axisAlignedBB.union(axisAlignedBB2);
    }
    
    @Override
    public Set ssf(final Map map) {
        return Collections.<Object>newSetFromMap((Map<Object, Boolean>)map);
    }
    
    @Override
    public boolean ssb(final EntityPlayer entityPlayer, final Potion potion) {
        return entityPlayer.isPotionActive(potion);
    }
    
    @Override
    public boolean ssw(final Set set, final Object o) {
        return set.remove(o);
    }
    
    @Override
    public FMLClientHandler ssg() {
        return FMLClientHandler.instance();
    }
    
    @Override
    public NetworkManager ssu(final FMLClientHandler fmlClientHandler) {
        return fmlClientHandler.getClientToServerNetworkManager();
    }
    
    @Override
    public void ssz(final EntityPlayerSP entityPlayerSP, final boolean sprinting) {
        entityPlayerSP.setSprinting(sprinting);
    }
    
    @Override
    public void ssc(final boolean b) {
        GL11.glDepthMask(b);
    }
    
    @Override
    public void ssa(final double n, final double n2, final double n3, final double n4) {
        GL11.glColor4d(n, n2, n3, n4);
    }
    
    @Override
    public void ssm(final int n) {
        GL11.glBegin(n);
    }
    
    @Override
    public void ssh(final double n, final double n2, final double n3) {
        GL11.glVertex3d(n, n2, n3);
    }
    
    @Override
    public void sld() {
        GL11.glEnd();
    }
    
    @Override
    public BufferBuilder sls(final BufferBuilder bufferBuilder, final float n, final float n2, final float n3, final float n4) {
        return bufferBuilder.color(n, n2, n3, n4);
    }
    
    @Override
    public AxisAlignedBB sll(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos blockPos) {
        return blockState.getBoundingBox(blockAccess, blockPos);
    }
    
    @Override
    public AxisAlignedBB sly(final AxisAlignedBB axisAlignedBB, final BlockPos blockPos) {
        return axisAlignedBB.offset(blockPos);
    }
    
    @Override
    public void slx(final int n, final int n2, final int n3, final int n4) {
        GlStateManager.tryBlendFuncSeparate(n, n2, n3, n4);
    }
    
    @Override
    public String slk(final String s, final String s2, final String s3) {
        return s.replaceAll(s2, s3);
    }
    
    @Override
    public char slq(final char c) {
        return Character.toUpperCase(c);
    }
    
    @Override
    public void slv(final double n, final double n2) {
        GL11.glVertex2d(n, n2);
    }
    
    @Override
    public boolean slj(final EntityTameable entityTameable) {
        return entityTameable.isTamed();
    }
    
    @Override
    public EntityLivingBase sle(final EntityTameable entityTameable) {
        return entityTameable.getOwner();
    }
    
    @Override
    public void slo(final EntityTameable entityTameable, final boolean alwaysRenderNameTag) {
        entityTameable.setAlwaysRenderNameTag(alwaysRenderNameTag);
    }
    
    @Override
    public ITextComponent slt(final EntityLivingBase entityLivingBase) {
        return entityLivingBase.getDisplayName();
    }
    
    @Override
    public String sln(final ITextComponent textComponent) {
        return textComponent.getFormattedText();
    }
    
    @Override
    public void sli(final EntityTameable entityTameable, final String customNameTag) {
        entityTameable.setCustomNameTag(customNameTag);
    }
    
    @Override
    public boolean slp(final AbstractHorse abstractHorse) {
        return abstractHorse.isTame();
    }
    
    @Override
    public UUID slr(final AbstractHorse abstractHorse) {
        return abstractHorse.getOwnerUniqueId();
    }
    
    @Override
    public void slf(final AbstractHorse abstractHorse, final boolean alwaysRenderNameTag) {
        abstractHorse.setAlwaysRenderNameTag(alwaysRenderNameTag);
    }
    
    @Override
    public void slb(final AbstractHorse abstractHorse, final String customNameTag) {
        abstractHorse.setCustomNameTag(customNameTag);
    }
    
    @Override
    public void slw(final PlayerControllerMP playerControllerMP, final GameType gameType) {
        playerControllerMP.setGameType(gameType);
    }
    
    @Override
    public double slg(final Number n) {
        return n.doubleValue();
    }
    
    @Override
    public boolean slu(final Enum enum1, final Object o) {
        return enum1.equals(o);
    }
    
    @Override
    public boolean slz(final int n) {
        return Mouse.isButtonDown(n);
    }
    
    @Override
    public float slc(final Number n) {
        return n.floatValue();
    }
    
    @Override
    public String sla(final String s) {
        return s.toUpperCase();
    }
    
    @Override
    public ItemStack slm(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.getActiveItemStack();
    }
    
    @Override
    public double slh(final EntityPlayer entityPlayer, final BlockPos blockPos) {
        return entityPlayer.getDistanceSq(blockPos);
    }
    
    @Override
    public RayTraceResult syd(final WorldClient worldClient, final Vec3d vec3d, final Vec3d vec3d2) {
        return worldClient.rayTraceBlocks(vec3d, vec3d2);
    }
    
    @Override
    public Comparator sys(final Function function) {
        return Comparator.<Object, Comparable>comparing((Function<? super Object, ? extends Comparable>)function);
    }
    
    @Override
    public Optional syl(final Stream stream, final Comparator comparator) {
        return stream.min(comparator);
    }
    
    @Override
    public Object syy(final Optional optional, final Object o) {
        return optional.orElse(o);
    }
    
    @Override
    public AxisAlignedBB syx(final Entity entity) {
        return entity.getEntityBoundingBox();
    }
    
    @Override
    public NonNullList syk() {
        return NonNullList.create();
    }
    
    @Override
    public boolean syq(final NonNullList list, final Collection collection) {
        return list.addAll(collection);
    }
    
    @Override
    public double syv(final Entity entity, final double n, final double n2, final double n3) {
        return entity.getDistance(n, n2, n3);
    }
    
    @Override
    public float syj(final World world, final Vec3d vec3d, final AxisAlignedBB axisAlignedBB) {
        return world.getBlockDensity(vec3d, axisAlignedBB);
    }
    
    @Override
    public int sye(final EntityPlayer entityPlayer) {
        return entityPlayer.getTotalArmorValue();
    }
    
    @Override
    public IAttributeInstance syo(final EntityPlayer entityPlayer, final IAttribute attribute) {
        return entityPlayer.getEntityAttribute(attribute);
    }
    
    @Override
    public double syt(final IAttributeInstance attributeInstance) {
        return attributeInstance.getAttributeValue();
    }
    
    @Override
    public float syn(final float n, final float n2, final float n3) {
        return CombatRules.getDamageAfterAbsorb(n, n2, n3);
    }
    
    @Override
    public int syi(final EntityLivingBase entityLivingBase) {
        return entityLivingBase.getTotalArmorValue();
    }
    
    @Override
    public IAttributeInstance syp(final EntityLivingBase entityLivingBase, final IAttribute attribute) {
        return entityLivingBase.getEntityAttribute(attribute);
    }
    
    @Override
    public EnumDifficulty syr(final WorldClient worldClient) {
        return worldClient.getDifficulty();
    }
    
    @Override
    public int syf(final EnumDifficulty enumDifficulty) {
        return enumDifficulty.getId();
    }
    
    @Override
    public boolean syb(final ArrayList list, final Object o) {
        return list.remove(o);
    }
    
    @Override
    public GameProfile syw(final SPacketPlayerListItem$AddPlayerData sPacketPlayerListItem$AddPlayerData) {
        return sPacketPlayerListItem$AddPlayerData.getProfile();
    }
    
    @Override
    public SoundCategory syg(final SPacketSoundEffect sPacketSoundEffect) {
        return sPacketSoundEffect.getCategory();
    }
    
    @Override
    public double syu(final SPacketSoundEffect sPacketSoundEffect) {
        return sPacketSoundEffect.getX();
    }
    
    @Override
    public double syz(final SPacketSoundEffect sPacketSoundEffect) {
        return sPacketSoundEffect.getY();
    }
    
    @Override
    public double syc(final SPacketSoundEffect sPacketSoundEffect) {
        return sPacketSoundEffect.getZ();
    }
    
    @Override
    public void sya(final Entity entity) {
        entity.setDead();
    }
    
    @Override
    public List sym(final SPacketChunkData sPacketChunkData) {
        return sPacketChunkData.getTileEntityTags();
    }
    
    @Override
    public int syh(final SPacketChunkData sPacketChunkData) {
        return sPacketChunkData.getChunkX();
    }
    
    @Override
    public int sxd(final SPacketChunkData sPacketChunkData) {
        return sPacketChunkData.getChunkZ();
    }
    
    @Override
    public Path sxs(final Path path, final byte[] array, final OpenOption[] array2) {
        return java.nio.file.Files.write(path, array, array2);
    }
    
    @Override
    public Map sxl(final EntityPlayer entityPlayer) {
        return entityPlayer.getActivePotionMap();
    }
    
    @Override
    public Set sxy(final Map map) {
        return map.entrySet();
    }
    
    @Override
    public String sxx(final Potion potion) {
        return potion.getName();
    }
    
    @Override
    public int sxk(final PotionEffect potionEffect) {
        return potionEffect.getDuration();
    }
    
    @Override
    public Object[] sxq(final Object[] array, final int n) {
        return Arrays.<Object>copyOf(array, n);
    }
    
    @Override
    public EnumFacing$Axis sxv(final EnumFacing enumFacing) {
        return enumFacing.getAxis();
    }
    
    @Override
    public EnumFacing$AxisDirection sxj(final EnumFacing enumFacing) {
        return enumFacing.getAxisDirection();
    }
    
    @Override
    public void sxe(final int n) {
        System.exit(n);
    }
    
    @Override
    public long sxo(final WorldClient worldClient) {
        return worldClient.getWorldTime();
    }
    
    @Override
    public LocalTime sxt(final int n, final int n2) {
        return LocalTime.of(n, n2);
    }
    
    @Override
    public LocalTime sxn() {
        return LocalTime.now();
    }
    
    @Override
    public void sxi(final float n, final float n2, final float n3, final float n4) {
        GL11.glClearColor(n, n2, n3, n4);
    }
    
    @Override
    public int sxp(final LocalTime localTime) {
        return localTime.getSecond();
    }
    
    @Override
    public int sxr(final LocalTime localTime) {
        return localTime.getMinute();
    }
    
    @Override
    public int sxf(final LocalTime localTime) {
        return localTime.getHour();
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType[] sxb() {
        return RenderGameOverlayEvent$ElementType.values();
    }
    
    @Override
    public StringBuilder sxw(final StringBuilder sb) {
        return sb.reverse();
    }
    
    @Override
    public int sxg() {
        return Mouse.getX();
    }
    
    @Override
    public int sxu() {
        return Mouse.getY();
    }
    
    @Override
    public EntityPlayer sxz(final AttackEntityEvent attackEntityEvent) {
        return attackEntityEvent.getEntityPlayer();
    }
    
    @Override
    public float sxc(final EntityPlayer entityPlayer) {
        return entityPlayer.getHealth();
    }
    
    @Override
    public double sxa(final double n) {
        return Math.abs(n);
    }
    
    @Override
    public EntityLivingBase sxm(final LivingEvent$LivingUpdateEvent livingEvent$LivingUpdateEvent) {
        return livingEvent$LivingUpdateEvent.getEntityLiving();
    }
    
    @Override
    public boolean sxh(final EntityPlayer entityPlayer) {
        return entityPlayer.isSneaking();
    }
    
    @Override
    public double skd(final WorldBorder worldBorder) {
        return worldBorder.maxX();
    }
    
    @Override
    public double sks(final WorldBorder worldBorder) {
        return worldBorder.maxZ();
    }
    
    @Override
    public double skl(final WorldBorder worldBorder) {
        return worldBorder.minX();
    }
    
    @Override
    public double sky(final WorldBorder worldBorder) {
        return worldBorder.minZ();
    }
    
    @Override
    public void skx(final Runtime runtime, final Thread thread) {
        runtime.addShutdownHook(thread);
    }
    
    @Override
    public void skk(final String title) {
        Display.setTitle(title);
    }
    
    @Override
    public int skq(final Set set) {
        return set.size();
    }
    
    @Override
    public void skv(final ArrayList list) {
        list.clear();
    }
    
    @Override
    public Set skj(final Map map) {
        return map.keySet();
    }
    
    @Override
    public boolean ske(final Map map, final Object o) {
        return map.containsKey(o);
    }
    
    @Override
    public String sko(final Vec3d vec3d) {
        return vec3d.toString();
    }
    
    @Override
    public SystemTray skt() {
        return SystemTray.getSystemTray();
    }
    
    @Override
    public void skn(final TrayIcon trayIcon, final boolean imageAutoSize) {
        trayIcon.setImageAutoSize(imageAutoSize);
    }
    
    @Override
    public void ski(final TrayIcon trayIcon, final String toolTip) {
        trayIcon.setToolTip(toolTip);
    }
    
    @Override
    public void skp(final SystemTray systemTray, final TrayIcon trayIcon) {
        systemTray.add(trayIcon);
    }
    
    @Override
    public void skr(final TrayIcon trayIcon, final String s, final String s2, final TrayIcon.MessageType messageType) {
        trayIcon.displayMessage(s, s2, messageType);
    }
    
    @Override
    public void skf(final Frame frame, final boolean alwaysOnTop) {
        frame.setAlwaysOnTop(alwaysOnTop);
    }
    
    @Override
    public void skb(final Frame frame, final int state) {
        frame.setState(state);
    }
    
    @Override
    public Icon skw(final Object o) {
        return UIManager.getIcon(o);
    }
    
    @Override
    public Object skg(final Component component, final Object o, final String s, final int n, final Icon icon, final Object[] array, final Object o2) {
        return JOptionPane.showInputDialog(component, o, s, n, icon, array, o2);
    }
    
    @Override
    public void sku(final FileWriter fileWriter, final String s) {
        fileWriter.write(s);
    }
    
    @Override
    public void skz(final float n, final float n2, final float n3) {
        GL11.glScalef(n, n2, n3);
    }
    
    @Override
    public void skc(final EntityRenderer entityRenderer) {
        entityRenderer.disableLightmap();
    }
    
    @Override
    public void ska(final ClientChatEvent clientChatEvent, final String message) {
        clientChatEvent.setMessage(message);
    }
    
    @Override
    public void skm(final BufferedWriter bufferedWriter) {
        bufferedWriter.newLine();
    }
    
    @Override
    public void skh(final BufferedWriter bufferedWriter) {
        bufferedWriter.flush();
    }
    
    @Override
    public boolean sqd(final ArrayList list, final Object o) {
        return list.contains(o);
    }
    
    @Override
    public String sqs(final EntityPlayer entityPlayer) {
        return entityPlayer.getName();
    }
    
    @Override
    public void sql(final EntityPlayerSP entityPlayerSP, final float rotationYawHead) {
        entityPlayerSP.setRotationYawHead(rotationYawHead);
    }
    
    @Override
    public void sqy() {
        GlStateManager.enableAlpha();
    }
    
    @Override
    public void sqx(final int n) {
        GlStateManager.shadeModel(n);
    }
    
    @Override
    public void sqk() {
        GlStateManager.enableColorMaterial();
    }
    
    @Override
    public void sqq(final RenderManager renderManager, final float playerViewY) {
        renderManager.setPlayerViewY(playerViewY);
    }
    
    @Override
    public void sqv(final RenderManager renderManager, final boolean renderShadow) {
        renderManager.setRenderShadow(renderShadow);
    }
    
    @Override
    public void sqj(final RenderManager renderManager, final Entity entity, final double n, final double n2, final double n3, final float n4, final float n5, final boolean b) {
        renderManager.renderEntity(entity, n, n2, n3, n4, n5, b);
    }
    
    @Override
    public void sqe(final int activeTexture) {
        GlStateManager.setActiveTexture(activeTexture);
    }
    
    @Override
    public void sqo(final int n) {
        GlStateManager.depthFunc(n);
    }
    
    @Override
    public void sqt() {
        GlStateManager.resetColor();
    }
    
    @Override
    public void sqn(final EntityPlayerSP entityPlayerSP) {
        entityPlayerSP.respawnPlayer();
    }
    
    @Override
    public FoodStats sqi(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.getFoodStats();
    }
    
    @Override
    public void sqp(final FoodStats foodStats, final int foodLevel) {
        foodStats.setFoodLevel(foodLevel);
    }
}
