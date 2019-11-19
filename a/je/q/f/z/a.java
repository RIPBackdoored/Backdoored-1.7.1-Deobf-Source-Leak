package a.je.q.f.z;

import net.minecraft.client.renderer.OpenGlHelper;
import java.awt.TrayIcon;
import net.minecraft.util.EnumFacing$AxisDirection;
import java.nio.file.StandardOpenOption;
import net.minecraft.util.SoundCategory;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.world.World;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.world.GameType;
import net.minecraft.util.MovementInput;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.text.ChatType;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
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
import net.minecraft.init.MobEffects;
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
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult$Type;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.event.ClickEvent$Action;
import net.minecraft.item.ItemDye;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ClickType;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event$Result;
import net.minecraft.network.play.server.SPacketPlayerListItem$Action;
import java.awt.Color;
import net.minecraft.client.util.ITooltipFlag$TooltipFlags;
import java.awt.Dimension;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Logger;
import java.nio.charset.StandardCharsets;
import net.minecraft.util.EnumHand;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import java.io.PrintStream;
import com.google.common.base.Charsets;
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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.tileentity.TileEntitySign;

public class a implements r
{
    public a() {
        super();
    }
    
    @Override
    public ITextComponent[] d(final TileEntitySign tileEntitySign) {
        return tileEntitySign.signText;
    }
    
    @Override
    public int s(final GuiButton guiButton) {
        return guiButton.id;
    }
    
    @Override
    public Block l() {
        return Blocks.STANDING_SIGN;
    }
    
    @Override
    public void y(final TileEntitySign tileEntitySign, final int lineBeingEdited) {
        tileEntitySign.lineBeingEdited = lineBeingEdited;
    }
    
    @Override
    public TileEntityRendererDispatcher x() {
        return TileEntityRendererDispatcher.instance;
    }
    
    @Override
    public EventBus k() {
        return MinecraftForge.EVENT_BUS;
    }
    
    @Override
    public TickEvent$Phase q(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        return tickEvent$ClientTickEvent.phase;
    }
    
    @Override
    public TickEvent$Phase v() {
        return TickEvent$Phase.END;
    }
    
    @Override
    public FontRenderer j(final Minecraft minecraft) {
        return minecraft.fontRenderer;
    }
    
    @Override
    public int e(final FontRenderer fontRenderer) {
        return fontRenderer.FONT_HEIGHT;
    }
    
    @Override
    public EntityPlayerSP o(final Minecraft minecraft) {
        return minecraft.player;
    }
    
    @Override
    public InventoryPlayer t(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.inventory;
    }
    
    @Override
    public NonNullList n(final InventoryPlayer inventoryPlayer) {
        return inventoryPlayer.mainInventory;
    }
    
    @Override
    public WorldClient i(final Minecraft minecraft) {
        return minecraft.world;
    }
    
    @Override
    public List p(final WorldClient worldClient) {
        return worldClient.loadedTileEntityList;
    }
    
    @Override
    public void r(final InventoryPlayer inventoryPlayer, final int currentItem) {
        inventoryPlayer.currentItem = currentItem;
    }
    
    @Override
    public NetHandlerPlayClient f(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.connection;
    }
    
    @Override
    public CPacketPlayerDigging$Action b() {
        return CPacketPlayerDigging$Action.START_DESTROY_BLOCK;
    }
    
    @Override
    public EnumFacing w() {
        return EnumFacing.UP;
    }
    
    @Override
    public CPacketPlayerDigging$Action g() {
        return CPacketPlayerDigging$Action.STOP_DESTROY_BLOCK;
    }
    
    @Override
    public int u(final InventoryPlayer inventoryPlayer) {
        return inventoryPlayer.currentItem;
    }
    
    @Override
    public RayTraceResult z(final Minecraft minecraft) {
        return minecraft.objectMouseOver;
    }
    
    @Override
    public EnumFacing c(final RayTraceResult rayTraceResult) {
        return rayTraceResult.sideHit;
    }
    
    @Override
    public Charset a() {
        return Charsets.UTF_8;
    }
    
    @Override
    public PrintStream m() {
        return System.out;
    }
    
    @Override
    public boolean h(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.isDead;
    }
    
    @Override
    public List sd(final WorldClient worldClient) {
        return worldClient.loadedEntityList;
    }
    
    @Override
    public PlayerControllerMP ss(final Minecraft minecraft) {
        return minecraft.playerController;
    }
    
    @Override
    public EnumHand sl() {
        return EnumHand.MAIN_HAND;
    }
    
    @Override
    public Charset sy() {
        return StandardCharsets.UTF_8;
    }
    
    @Override
    public Logger sx() {
        return FMLLog.log;
    }
    
    @Override
    public int sk(final Dimension dimension) {
        return dimension.width;
    }
    
    @Override
    public int sq(final Dimension dimension) {
        return dimension.height;
    }
    
    @Override
    public ITooltipFlag$TooltipFlags sv() {
        return ITooltipFlag$TooltipFlags.ADVANCED;
    }
    
    @Override
    public int sj(final Minecraft minecraft) {
        return minecraft.displayWidth;
    }
    
    @Override
    public int se(final Minecraft minecraft) {
        return minecraft.displayHeight;
    }
    
    @Override
    public Color so() {
        return Color.WHITE;
    }
    
    @Override
    public Color st() {
        return Color.LIGHT_GRAY;
    }
    
    @Override
    public double sn(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.posX;
    }
    
    @Override
    public double si(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.posY;
    }
    
    @Override
    public double sp(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.posZ;
    }
    
    @Override
    public Color sr() {
        return Color.ORANGE;
    }
    
    @Override
    public SPacketPlayerListItem$Action sf() {
        return SPacketPlayerListItem$Action.ADD_PLAYER;
    }
    
    @Override
    public Event$Result sb() {
        return Event$Result.DENY;
    }
    
    @Override
    public List sw(final WorldClient worldClient) {
        return worldClient.playerEntities;
    }
    
    @Override
    public Block sg() {
        return Blocks.WEB;
    }
    
    @Override
    public double su(final EntityPlayer entityPlayer) {
        return entityPlayer.posX;
    }
    
    @Override
    public double sz(final EntityPlayer entityPlayer) {
        return entityPlayer.posY;
    }
    
    @Override
    public double sc(final EntityPlayer entityPlayer) {
        return entityPlayer.posZ;
    }
    
    @Override
    public void sa(final GuiButton guiButton, final boolean enabled) {
        guiButton.enabled = enabled;
    }
    
    @Override
    public void sm(final EntityPlayerSP entityPlayerSP, final boolean isDead) {
        entityPlayerSP.isDead = isDead;
    }
    
    @Override
    public boolean sh(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.onGround;
    }
    
    @Override
    public Block ld() {
        return Blocks.OBSIDIAN;
    }
    
    @Override
    public int ls(final Framebuffer framebuffer) {
        return framebuffer.framebufferTextureWidth;
    }
    
    @Override
    public int ll(final Framebuffer framebuffer) {
        return framebuffer.framebufferTextureHeight;
    }
    
    @Override
    public int ly(final Framebuffer framebuffer) {
        return framebuffer.framebufferTexture;
    }
    
    @Override
    public Item lx() {
        return Items.TOTEM_OF_UNDYING;
    }
    
    @Override
    public ClickType lk() {
        return ClickType.PICKUP_ALL;
    }
    
    @Override
    public ClickType lq() {
        return ClickType.PICKUP;
    }
    
    @Override
    public Container lv(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.inventoryContainer;
    }
    
    @Override
    public TextureManager lj(final Minecraft minecraft) {
        return minecraft.renderEngine;
    }
    
    @Override
    public Capability le() {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }
    
    @Override
    public int[] lo() {
        return ItemDye.DYE_COLORS;
    }
    
    @Override
    public Block lt() {
        return Blocks.WHITE_SHULKER_BOX;
    }
    
    @Override
    public Block ln() {
        return Blocks.ORANGE_SHULKER_BOX;
    }
    
    @Override
    public Block li() {
        return Blocks.MAGENTA_SHULKER_BOX;
    }
    
    @Override
    public Block lp() {
        return Blocks.LIGHT_BLUE_SHULKER_BOX;
    }
    
    @Override
    public Block lr() {
        return Blocks.YELLOW_SHULKER_BOX;
    }
    
    @Override
    public Block lf() {
        return Blocks.LIME_SHULKER_BOX;
    }
    
    @Override
    public Block lb() {
        return Blocks.PINK_SHULKER_BOX;
    }
    
    @Override
    public Block lw() {
        return Blocks.GRAY_SHULKER_BOX;
    }
    
    @Override
    public Block lg() {
        return Blocks.SILVER_SHULKER_BOX;
    }
    
    @Override
    public Block lu() {
        return Blocks.CYAN_SHULKER_BOX;
    }
    
    @Override
    public Block lz() {
        return Blocks.PURPLE_SHULKER_BOX;
    }
    
    @Override
    public Block lc() {
        return Blocks.BLUE_SHULKER_BOX;
    }
    
    @Override
    public Block la() {
        return Blocks.BROWN_SHULKER_BOX;
    }
    
    @Override
    public Block lm() {
        return Blocks.GREEN_SHULKER_BOX;
    }
    
    @Override
    public Block lh() {
        return Blocks.RED_SHULKER_BOX;
    }
    
    @Override
    public Block yd() {
        return Blocks.BLACK_SHULKER_BOX;
    }
    
    @Override
    public int ys(final Framebuffer framebuffer) {
        return framebuffer.framebufferWidth;
    }
    
    @Override
    public int yl(final Framebuffer framebuffer) {
        return framebuffer.framebufferHeight;
    }
    
    @Override
    public ClickEvent$Action yy() {
        return ClickEvent$Action.OPEN_FILE;
    }
    
    @Override
    public Entity yx(final RayTraceResult rayTraceResult) {
        return rayTraceResult.entityHit;
    }
    
    @Override
    public BlockChest yk() {
        return Blocks.CHEST;
    }
    
    @Override
    public Block yq() {
        return Blocks.ENDER_CHEST;
    }
    
    @Override
    public Block yv() {
        return Blocks.TRAPPED_CHEST;
    }
    
    @Override
    public LaunchClassLoader yj() {
        return Launch.classLoader;
    }
    
    @Override
    public Block ye() {
        return Blocks.BEDROCK;
    }
    
    @Override
    public double yo(final AxisAlignedBB axisAlignedBB) {
        return axisAlignedBB.minX;
    }
    
    @Override
    public double yt(final AxisAlignedBB axisAlignedBB) {
        return axisAlignedBB.minY;
    }
    
    @Override
    public double yn(final AxisAlignedBB axisAlignedBB) {
        return axisAlignedBB.minZ;
    }
    
    @Override
    public double yi(final AxisAlignedBB axisAlignedBB) {
        return axisAlignedBB.maxX;
    }
    
    @Override
    public double yp(final AxisAlignedBB axisAlignedBB) {
        return axisAlignedBB.maxZ;
    }
    
    @Override
    public void yr(final Entity entity, final boolean isDead) {
        entity.isDead = isDead;
    }
    
    @Override
    public void yf(final Entity entity, final double posX) {
        entity.posX = posX;
    }
    
    @Override
    public void yb(final Entity entity, final double posY) {
        entity.posY = posY;
    }
    
    @Override
    public void yw(final Entity entity, final double posZ) {
        entity.posZ = posZ;
    }
    
    @Override
    public double yg(final Vec3d vec3d) {
        return vec3d.x;
    }
    
    @Override
    public double yu(final Vec3d vec3d) {
        return vec3d.y;
    }
    
    @Override
    public double yz(final Vec3d vec3d) {
        return vec3d.z;
    }
    
    @Override
    public double yc(final RenderManager renderManager) {
        return renderManager.viewerPosX;
    }
    
    @Override
    public double ya(final RenderManager renderManager) {
        return renderManager.viewerPosY;
    }
    
    @Override
    public double ym(final RenderManager renderManager) {
        return renderManager.viewerPosZ;
    }
    
    @Override
    public float yh(final RenderManager renderManager) {
        return renderManager.playerViewY;
    }
    
    @Override
    public float xd(final RenderManager renderManager) {
        return renderManager.playerViewX;
    }
    
    @Override
    public int xs(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.dimension;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType xl() {
        return RenderGameOverlayEvent$ElementType.CROSSHAIRS;
    }
    
    @Override
    public GuiIngame xy(final Minecraft minecraft) {
        return minecraft.ingameGUI;
    }
    
    @Override
    public GameSettings xx(final Minecraft minecraft) {
        return minecraft.gameSettings;
    }
    
    @Override
    public int xk(final GameSettings gameSettings) {
        return gameSettings.thirdPersonView;
    }
    
    @Override
    public Entity xq(final Minecraft minecraft) {
        return minecraft.pointedEntity;
    }
    
    @Override
    public RayTraceResult$Type xv(final RayTraceResult rayTraceResult) {
        return rayTraceResult.typeOfHit;
    }
    
    @Override
    public RayTraceResult$Type xj() {
        return RayTraceResult$Type.BLOCK;
    }
    
    @Override
    public boolean xe(final GameSettings gameSettings) {
        return gameSettings.hideGUI;
    }
    
    @Override
    public float xo(final Entity entity) {
        return entity.prevRotationPitch;
    }
    
    @Override
    public float xt(final Entity entity) {
        return entity.rotationPitch;
    }
    
    @Override
    public float xn(final Entity entity) {
        return entity.prevRotationYaw;
    }
    
    @Override
    public float xi(final Entity entity) {
        return entity.rotationYaw;
    }
    
    @Override
    public SoundEvent xp() {
        return SoundEvents.ENTITY_PIG_STEP;
    }
    
    @Override
    public SoundEvent xr() {
        return SoundEvents.ENTITY_HORSE_STEP;
    }
    
    @Override
    public SoundEvent xf() {
        return SoundEvents.ENTITY_HORSE_STEP_WOOD;
    }
    
    @Override
    public SoundEvent xb() {
        return SoundEvents.ENTITY_LLAMA_STEP;
    }
    
    @Override
    public RenderBlockOverlayEvent$OverlayType xw() {
        return RenderBlockOverlayEvent$OverlayType.FIRE;
    }
    
    @Override
    public RenderBlockOverlayEvent$OverlayType xg() {
        return RenderBlockOverlayEvent$OverlayType.BLOCK;
    }
    
    @Override
    public RenderBlockOverlayEvent$OverlayType xu() {
        return RenderBlockOverlayEvent$OverlayType.WATER;
    }
    
    @Override
    public int xz(final ChunkPos chunkPos) {
        return chunkPos.x;
    }
    
    @Override
    public int xc(final ChunkPos chunkPos) {
        return chunkPos.z;
    }
    
    @Override
    public int xa(final Chunk chunk) {
        return chunk.x;
    }
    
    @Override
    public int xm(final Chunk chunk) {
        return chunk.z;
    }
    
    @Override
    public String xh(final ServerData serverData) {
        return serverData.serverIP;
    }
    
    @Override
    public CPacketUseEntity$Action kd() {
        return CPacketUseEntity$Action.ATTACK;
    }
    
    @Override
    public int ks(final Container container) {
        return container.windowId;
    }
    
    @Override
    public ClickType kl() {
        return ClickType.SWAP;
    }
    
    @Override
    public GuiScreen ky(final Minecraft minecraft) {
        return minecraft.currentScreen;
    }
    
    @Override
    public EnumHand kx() {
        return EnumHand.OFF_HAND;
    }
    
    @Override
    public Item kk() {
        return Items.WRITABLE_BOOK;
    }
    
    @Override
    public Instant kq() {
        return Instant.EPOCH;
    }
    
    @Override
    public float kv(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.rotationYaw;
    }
    
    @Override
    public float kj(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.moveForward;
    }
    
    @Override
    public float ke(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.moveStrafing;
    }
    
    @Override
    public String ko(final ServerData serverData) {
        return serverData.populationInfo;
    }
    
    @Override
    public boolean kt(final WorldClient worldClient) {
        return worldClient.isRemote;
    }
    
    @Override
    public int kn(final EntityItem entityItem) {
        return entityItem.lifespan;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType ki() {
        return RenderGameOverlayEvent$ElementType.EXPERIENCE;
    }
    
    @Override
    public GlStateManager$SourceFactor kp() {
        return GlStateManager$SourceFactor.SRC_ALPHA;
    }
    
    @Override
    public GlStateManager$DestFactor kr() {
        return GlStateManager$DestFactor.ONE_MINUS_SRC_ALPHA;
    }
    
    @Override
    public GlStateManager$SourceFactor kf() {
        return GlStateManager$SourceFactor.ONE;
    }
    
    @Override
    public GlStateManager$DestFactor kb() {
        return GlStateManager$DestFactor.ZERO;
    }
    
    @Override
    public Material kw() {
        return Material.AIR;
    }
    
    @Override
    public double kg(final EntityPlayer entityPlayer) {
        return entityPlayer.lastTickPosX;
    }
    
    @Override
    public double ku(final EntityPlayer entityPlayer) {
        return entityPlayer.lastTickPosY;
    }
    
    @Override
    public double kz(final EntityPlayer entityPlayer) {
        return entityPlayer.lastTickPosZ;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType kc() {
        return RenderGameOverlayEvent$ElementType.HOTBAR;
    }
    
    @Override
    public float ka(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.rotationPitch;
    }
    
    @Override
    public void km(final EntityPlayerSP entityPlayerSP, final float rotationPitch) {
        entityPlayerSP.rotationPitch = rotationPitch;
    }
    
    @Override
    public void kh(final EntityPlayerSP entityPlayerSP, final float rotationYaw) {
        entityPlayerSP.rotationYaw = rotationYaw;
    }
    
    @Override
    public Container qd(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.openContainer;
    }
    
    @Override
    public Item qs() {
        return Items.AIR;
    }
    
    @Override
    public Potion ql() {
        return MobEffects.SPEED;
    }
    
    @Override
    public ItemEmptyMap qy() {
        return Items.MAP;
    }
    
    @Override
    public ItemMap qx() {
        return Items.FILLED_MAP;
    }
    
    @Override
    public ClickType qk() {
        return ClickType.THROW;
    }
    
    @Override
    public String qq(final GuiButton guiButton) {
        return guiButton.displayString;
    }
    
    @Override
    public Color qv() {
        return Color.GREEN;
    }
    
    @Override
    public Color qj() {
        return Color.RED;
    }
    
    @Override
    public PlayerCapabilities qe(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.capabilities;
    }
    
    @Override
    public boolean qo(final PlayerCapabilities playerCapabilities) {
        return playerCapabilities.isFlying;
    }
    
    @Override
    public void qt(final EntityPlayerSP entityPlayerSP, final double motionY) {
        entityPlayerSP.motionY = motionY;
    }
    
    @Override
    public EnumFacing$Axis qn() {
        return EnumFacing$Axis.X;
    }
    
    @Override
    public EnumFacing$Axis qi() {
        return EnumFacing$Axis.Z;
    }
    
    @Override
    public List qp(final Container container) {
        return container.inventorySlots;
    }
    
    @Override
    public ClickType qr() {
        return ClickType.QUICK_MOVE;
    }
    
    @Override
    public CPacketPlayerDigging$Action qf() {
        return CPacketPlayerDigging$Action.SWAP_HELD_ITEMS;
    }
    
    @Override
    public CPacketEntityAction$Action qb() {
        return CPacketEntityAction$Action.START_SNEAKING;
    }
    
    @Override
    public CPacketEntityAction$Action qw() {
        return CPacketEntityAction$Action.STOP_SNEAKING;
    }
    
    @Override
    public void qg(final FontRenderer fontRenderer, final int font_HEIGHT) {
        fontRenderer.FONT_HEIGHT = font_HEIGHT;
    }
    
    @Override
    public Proxy qu() {
        return Proxy.NO_PROXY;
    }
    
    @Override
    public Agent qz() {
        return Agent.MINECRAFT;
    }
    
    @Override
    public Block qc() {
        return Blocks.SOUL_SAND;
    }
    
    @Override
    public BlockSkull qa() {
        return Blocks.SKULL;
    }
    
    @Override
    public EnumFacing qm() {
        return EnumFacing.EAST;
    }
    
    @Override
    public EnumFacing qh() {
        return EnumFacing.WEST;
    }
    
    @Override
    public void vd(final PlayerCapabilities playerCapabilities, final boolean isFlying) {
        playerCapabilities.isFlying = isFlying;
    }
    
    @Override
    public CPacketEntityAction$Action vs() {
        return CPacketEntityAction$Action.START_FALL_FLYING;
    }
    
    @Override
    public KeyBinding vl(final GameSettings gameSettings) {
        return gameSettings.keyBindForward;
    }
    
    @Override
    public double vy(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.motionX;
    }
    
    @Override
    public void vx(final EntityPlayerSP entityPlayerSP, final double motionX) {
        entityPlayerSP.motionX = motionX;
    }
    
    @Override
    public double vk(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.motionZ;
    }
    
    @Override
    public void vq(final EntityPlayerSP entityPlayerSP, final double motionZ) {
        entityPlayerSP.motionZ = motionZ;
    }
    
    @Override
    public KeyBinding vv(final GameSettings gameSettings) {
        return gameSettings.keyBindBack;
    }
    
    @Override
    public void vj(final EntityPlayerSP entityPlayerSP, final float jumpMovementFactor) {
        entityPlayerSP.jumpMovementFactor = jumpMovementFactor;
    }
    
    @Override
    public KeyBinding ve(final GameSettings gameSettings) {
        return gameSettings.keyBindJump;
    }
    
    @Override
    public double vo(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.motionY;
    }
    
    @Override
    public KeyBinding vt(final GameSettings gameSettings) {
        return gameSettings.keyBindSneak;
    }
    
    @Override
    public KeyBinding vn(final GameSettings gameSettings) {
        return gameSettings.keyBindLeft;
    }
    
    @Override
    public KeyBinding vi(final GameSettings gameSettings) {
        return gameSettings.keyBindRight;
    }
    
    @Override
    public Color vp() {
        return Color.BLACK;
    }
    
    @Override
    public Color vr() {
        return Color.GRAY;
    }
    
    @Override
    public VertexFormat vf() {
        return DefaultVertexFormats.POSITION_TEX;
    }
    
    @Override
    public EntityRenderer vb(final Minecraft minecraft) {
        return minecraft.entityRenderer;
    }
    
    @Override
    public void vw(final Entity entity, final double motionX) {
        entity.motionX = motionX;
    }
    
    @Override
    public void vg(final Entity entity, final double motionZ) {
        entity.motionZ = motionZ;
    }
    
    @Override
    public void vu(final Entity entity, final double motionY) {
        entity.motionY = motionY;
    }
    
    @Override
    public double vz(final Entity entity) {
        return entity.posX;
    }
    
    @Override
    public double vc(final Entity entity) {
        return entity.motionX;
    }
    
    @Override
    public double va(final Entity entity) {
        return entity.posY;
    }
    
    @Override
    public double vm(final Entity entity) {
        return entity.posZ;
    }
    
    @Override
    public double vh(final Entity entity) {
        return entity.motionZ;
    }
    
    @Override
    public KeyBinding jd(final GameSettings gameSettings) {
        return gameSettings.keyBindSprint;
    }
    
    @Override
    public ItemStack js() {
        return ItemStack.EMPTY;
    }
    
    @Override
    public ChatType jl() {
        return ChatType.CHAT;
    }
    
    @Override
    public ChatType jy() {
        return ChatType.GAME_INFO;
    }
    
    @Override
    public float jx(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.fallDistance;
    }
    
    @Override
    public TileEntityChest jk(final TileEntityChest tileEntityChest) {
        return tileEntityChest.adjacentChestXPos;
    }
    
    @Override
    public TileEntityChest jq(final TileEntityChest tileEntityChest) {
        return tileEntityChest.adjacentChestZPos;
    }
    
    @Override
    public TileEntityChest jv(final TileEntityChest tileEntityChest) {
        return tileEntityChest.adjacentChestXNeg;
    }
    
    @Override
    public TileEntityChest jj(final TileEntityChest tileEntityChest) {
        return tileEntityChest.adjacentChestZNeg;
    }
    
    @Override
    public Potion je() {
        return MobEffects.STRENGTH;
    }
    
    @Override
    public Item jo() {
        return Items.BED;
    }
    
    @Override
    public MovementInput jt(final EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.movementInput;
    }
    
    @Override
    public float jn(final MovementInput movementInput) {
        return movementInput.moveForward;
    }
    
    @Override
    public float ji(final MovementInput movementInput) {
        return movementInput.moveStrafe;
    }
    
    @Override
    public double jp(final AxisAlignedBB axisAlignedBB) {
        return axisAlignedBB.maxY;
    }
    
    @Override
    public VertexFormat jr() {
        return DefaultVertexFormats.POSITION;
    }
    
    @Override
    public VertexFormat jf() {
        return DefaultVertexFormats.POSITION_COLOR;
    }
    
    @Override
    public GameType jb() {
        return GameType.CREATIVE;
    }
    
    @Override
    public GameType jw() {
        return GameType.SURVIVAL;
    }
    
    @Override
    public Color jg() {
        return Color.DARK_GRAY;
    }
    
    @Override
    public Item ju() {
        return Items.GOLDEN_APPLE;
    }
    
    @Override
    public Item jz() {
        return Items.DIAMOND_PICKAXE;
    }
    
    @Override
    public boolean jc(final EntityPlayer entityPlayer) {
        return entityPlayer.isDead;
    }
    
    @Override
    public Item ja() {
        return Items.END_CRYSTAL;
    }
    
    @Override
    public double jm(final EntityEnderCrystal entityEnderCrystal) {
        return entityEnderCrystal.posX;
    }
    
    @Override
    public double jh(final EntityEnderCrystal entityEnderCrystal) {
        return entityEnderCrystal.posY;
    }
    
    @Override
    public double ed(final EntityEnderCrystal entityEnderCrystal) {
        return entityEnderCrystal.posZ;
    }
    
    @Override
    public Block es() {
        return Blocks.AIR;
    }
    
    @Override
    public World el(final Entity entity) {
        return entity.world;
    }
    
    @Override
    public IAttribute ey() {
        return SharedMonsterAttributes.ARMOR_TOUGHNESS;
    }
    
    @Override
    public SPacketPlayerListItem$Action ex() {
        return SPacketPlayerListItem$Action.UPDATE_LATENCY;
    }
    
    @Override
    public SoundCategory ek() {
        return SoundCategory.BLOCKS;
    }
    
    @Override
    public SoundEvent eq() {
        return SoundEvents.ENTITY_GENERIC_EXPLODE;
    }
    
    @Override
    public StandardOpenOption ev() {
        return StandardOpenOption.APPEND;
    }
    
    @Override
    public EnumFacing$AxisDirection ej() {
        return EnumFacing$AxisDirection.POSITIVE;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType ee() {
        return RenderGameOverlayEvent$ElementType.HELMET;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType eo() {
        return RenderGameOverlayEvent$ElementType.PORTAL;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType et() {
        return RenderGameOverlayEvent$ElementType.BOSSHEALTH;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType en() {
        return RenderGameOverlayEvent$ElementType.BOSSINFO;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType ei() {
        return RenderGameOverlayEvent$ElementType.ARMOR;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType ep() {
        return RenderGameOverlayEvent$ElementType.HEALTH;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType er() {
        return RenderGameOverlayEvent$ElementType.FOOD;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType ef() {
        return RenderGameOverlayEvent$ElementType.AIR;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType eb() {
        return RenderGameOverlayEvent$ElementType.TEXT;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType ew() {
        return RenderGameOverlayEvent$ElementType.HEALTHMOUNT;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType eg() {
        return RenderGameOverlayEvent$ElementType.JUMPBAR;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType eu() {
        return RenderGameOverlayEvent$ElementType.CHAT;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType ez() {
        return RenderGameOverlayEvent$ElementType.PLAYER_LIST;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType ec() {
        return RenderGameOverlayEvent$ElementType.POTION_ICONS;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType ea() {
        return RenderGameOverlayEvent$ElementType.SUBTITLES;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType em() {
        return RenderGameOverlayEvent$ElementType.FPS_GRAPH;
    }
    
    @Override
    public RenderGameOverlayEvent$ElementType eh() {
        return RenderGameOverlayEvent$ElementType.VIGNETTE;
    }
    
    @Override
    public boolean od(final GameSettings gameSettings) {
        return gameSettings.showDebugInfo;
    }
    
    @Override
    public void os(final EntityPlayerSP entityPlayerSP, final double posX) {
        entityPlayerSP.posX = posX;
    }
    
    @Override
    public void ol(final EntityPlayerSP entityPlayerSP, final double posZ) {
        entityPlayerSP.posZ = posZ;
    }
    
    @Override
    public ChatType oy() {
        return ChatType.SYSTEM;
    }
    
    @Override
    public TrayIcon.MessageType ox() {
        return TrayIcon.MessageType.INFO;
    }
    
    @Override
    public Event$Result ok() {
        return Event$Result.ALLOW;
    }
    
    @Override
    public int oq() {
        return OpenGlHelper.lightmapTexUnit;
    }
    
    @Override
    public int ov() {
        return OpenGlHelper.defaultTexUnit;
    }
}
