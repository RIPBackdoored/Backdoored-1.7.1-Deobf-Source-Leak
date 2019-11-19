package r.k.n;

import r.k.b.c.h;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import r.k.s.o;

public class k extends c
{
    public static final boolean xg;
    public static final int xu;
    public static final boolean xz;
    
    public k() {
        super("fakeplayer");
    }
    
    @Override
    public boolean d(final String[] array) {
        try {
            if (array.length < 1) {
                return false;
            }
            final UUID fromString = UUID.fromString(o.i(array[0]));
            System.out.print("UUID LOCATED: " + fromString.toString());
            final EntityOtherPlayerMP entityOtherPlayerMP = new EntityOtherPlayerMP((World)this.mc.world, new GameProfile(fromString, array[0]));
            entityOtherPlayerMP.copyLocationAndAnglesFrom((Entity)this.mc.player);
            entityOtherPlayerMP.readFromNBT(this.mc.player.writeToNBT(new NBTTagCompound()));
            final int[] array2 = { -21, -69, -911, -420, -666, -2003 };
            final int length = array2.length;
            int n = 0;
            if (n < length) {
                final int n2 = array2[n];
                if (this.mc.world.getEntityByID(n2) == null) {
                    this.mc.world.addEntityToWorld(n2, (Entity)entityOtherPlayerMP);
                    return true;
                }
                ++n;
            }
            int n3 = -1;
            if (n3 > -400) {
                if (this.mc.world.getEntityByID(n3) == null) {
                    this.mc.world.addEntityToWorld(n3, (Entity)entityOtherPlayerMP);
                    return true;
                }
                --n3;
            }
            h.o("No entity ids available", "gold");
            return false;
        }
        catch (Exception ex) {
            h.o(ex.getMessage(), "gold");
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public String k() {
        return "-fakeplayer DanTDM";
    }
}
