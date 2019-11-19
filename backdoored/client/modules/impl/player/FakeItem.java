package r.k.d.m.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import r.k.r.h.p;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Fake Item", description = "Always be holding your first item", category = y.PLAYER)
public class FakeItem extends g
{
    private boolean fy;
    public static final int fx;
    public static final boolean fk;
    
    public FakeItem() {
        super();
    }
    
    @SubscribeEvent
    public void s(final p p) {
        if (this.lo()) {
            if (p.packet instanceof CPacketPlayerTryUseItemOnBlock) {
                if (this.fy) {
                    this.fy = false;
                    return;
                }
                p.setCanceled(true);
                final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock = (CPacketPlayerTryUseItemOnBlock)p.packet;
                final BlockPos pos = cPacketPlayerTryUseItemOnBlock.getPos();
                final EnumFacing direction = cPacketPlayerTryUseItemOnBlock.getDirection();
                final EnumHand hand = cPacketPlayerTryUseItemOnBlock.getHand();
                final float facingX = cPacketPlayerTryUseItemOnBlock.getFacingX();
                final float facingY = cPacketPlayerTryUseItemOnBlock.getFacingY();
                final float facingZ = cPacketPlayerTryUseItemOnBlock.getFacingZ();
                se();
                this.fy = true;
                FakeItem.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(pos, direction, hand, facingX, facingY, facingZ));
                se();
            }
            if (p.packet instanceof CPacketPlayerTryUseItem) {
                if (this.fy) {
                    this.fy = false;
                    return;
                }
                final EnumHand hand2 = ((CPacketPlayerTryUseItem)p.packet).getHand();
                p.setCanceled(true);
                se();
                this.fy = true;
                FakeItem.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(hand2));
                se();
            }
            if (p.packet instanceof CPacketUseEntity) {
                if (this.fy) {
                    this.fy = false;
                    return;
                }
                final CPacketUseEntity cPacketUseEntity = (CPacketUseEntity)p.packet;
                if (cPacketUseEntity.getAction() == CPacketUseEntity.Action.ATTACK) {
                    final Entity entityFromWorld = cPacketUseEntity.getEntityFromWorld((World)FakeItem.mc.world);
                    if (entityFromWorld != null) {
                        p.setCanceled(true);
                        se();
                        this.fy = true;
                        FakeItem.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entityFromWorld));
                        se();
                    }
                }
            }
        }
    }
    
    private static void se() {
        FakeItem.mc.playerController.windowClick(FakeItem.mc.player.inventoryContainer.windowId, 9, FakeItem.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)FakeItem.mc.player);
    }
}
