package r.k.d.m.combat;

import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.CombatRules;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.Explosion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import net.minecraft.util.NonNullList;
import java.util.List;
import r.k.d.o.h.i.s;
import r.k.d.o.h.p;
import r.k.d.o.h.i.x;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Auto Crystal", description = "Auto Place Crystals, Modified Kami paste", category = y.COMBAT)
public class AutoCrystal extends g
{
    private m<Integer> szs;
    private m<Boolean> szl;
    private m<Boolean> szy;
    private m<Boolean> szx;
    private m<Boolean> szk;
    private m<Boolean> szq;
    private m<Double> szv;
    private m<Double> szj;
    private m<Double> sze;
    private m<Double> szo;
    private BlockPos blockPos;
    private Entity entity;
    private long szi;
    public static final boolean szp;
    public static final int szr;
    public static final boolean szf;
    
    public AutoCrystal() {
        super();
        this.szs = (m<Integer>)new x("Place Per Tick", this, 1, 0, 6);
        this.szl = (m<Boolean>)new p("Place", this, true);
        this.szy = (m<Boolean>)new p("Break", this, true);
        this.szx = (m<Boolean>)new p("Switch", this, true);
        this.szq = (m<Boolean>)new p("Dont cancel mining", this, true);
        this.szv = (m<Double>)new s("Place Range", this, 4.0, 0.0, 10.0);
        this.szj = (m<Double>)new s("Break Range", this, 4.0, 0.0, 10.0);
        this.sze = (m<Double>)new s("Raytrace Place Range", this, 3.0, 0.0, 10.0);
        this.szo = (m<Double>)new s("Min Damage", this, 4.0, 0.0, 20.0);
        this.szi = -1L;
    }
    
    public void j() {
        if (!this.lo()) {
            return;
        }
        if (this.szy.yv()) {
            this.xg();
        }
        if (this.szl.yv() && 0 < this.szs.yv()) {
            this.xw();
        }
    }
    
    private void xw() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: nop            
        //     7: aload_0        
        //     8: aconst_null    
        //     9: putfield        r/k/d/m/combat/AutoCrystal.blockPos:Lnet/minecraft/util/math/BlockPos;
        //    12: aload_0        
        //    13: aconst_null    
        //    14: putfield        r/k/d/m/combat/AutoCrystal.entity:Lnet/minecraft/entity/Entity;
        //    17: aload_0        
        //    18: getfield        r/k/d/m/combat/AutoCrystal.szk:Lr/k/d/o/m;
        //    21: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //    24: checkcast       Ljava/lang/Boolean;
        //    27: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    30: ifeq            51
        //    33: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //    36: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    39: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getActiveItemStack:()Lnet/minecraft/item/ItemStack;
        //    42: invokevirtual   net/minecraft/item/ItemStack.getItem:()Lnet/minecraft/item/Item;
        //    45: getstatic       net/minecraft/init/Items.GOLDEN_APPLE:Lnet/minecraft/item/Item;
        //    48: if_acmpeq       53
        //    51: iconst_1       
        //    52: nop            
        //    53: iconst_0       
        //    54: istore_1       
        //    55: aload_0        
        //    56: getfield        r/k/d/m/combat/AutoCrystal.szq:Lr/k/d/o/m;
        //    59: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //    62: checkcast       Ljava/lang/Boolean;
        //    65: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    68: ifeq            89
        //    71: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //    74: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    77: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getActiveItemStack:()Lnet/minecraft/item/ItemStack;
        //    80: invokevirtual   net/minecraft/item/ItemStack.getItem:()Lnet/minecraft/item/Item;
        //    83: getstatic       net/minecraft/init/Items.DIAMOND_PICKAXE:Lnet/minecraft/item/Item;
        //    86: if_acmpeq       91
        //    89: iconst_1       
        //    90: nop            
        //    91: iconst_0       
        //    92: istore_2       
        //    93: iload_1        
        //    94: ifeq            97
        //    97: return         
        //    98: aload_0        
        //    99: invokespecial   r/k/d/m/combat/AutoCrystal.xu:()Ljava/util/List;
        //   102: astore          4
        //   104: new             Ljava/util/ArrayList;
        //   107: dup            
        //   108: invokespecial   java/util/ArrayList.<init>:()V
        //   111: astore_3       
        //   112: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   115: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   118: getfield        net/minecraft/client/multiplayer/WorldClient.playerEntities:Ljava/util/List;
        //   121: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   126: astore          5
        //   128: aload           5
        //   130: invokeinterface java/util/Iterator.hasNext:()Z
        //   135: ifeq            168
        //   138: aload           5
        //   140: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   145: checkcast       Lnet/minecraft/entity/player/EntityPlayer;
        //   148: astore          6
        //   150: aload           6
        //   152: invokestatic    r/k/b/i/p.l:(Lnet/minecraft/entity/player/EntityPlayer;)Z
        //   155: ifne            167
        //   158: aload_3        
        //   159: aload           6
        //   161: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   166: pop            
        //   167: nop            
        //   168: ldc2_w          0.1
        //   171: dstore          5
        //   173: ldc2_w          1000.0
        //   176: dstore          7
        //   178: aconst_null    
        //   179: astore          9
        //   181: aload_3        
        //   182: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   187: astore          10
        //   189: aload           10
        //   191: invokeinterface java/util/Iterator.hasNext:()Z
        //   196: ifeq            688
        //   199: aload           10
        //   201: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   206: checkcast       Lnet/minecraft/entity/player/EntityPlayer;
        //   209: astore          11
        //   211: aload           11
        //   213: invokevirtual   net/minecraft/entity/player/EntityPlayer.getUniqueID:()Ljava/util/UUID;
        //   216: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   219: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   222: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getUniqueID:()Ljava/util/UUID;
        //   225: invokevirtual   java/util/UUID.equals:(Ljava/lang/Object;)Z
        //   228: ifne            189
        //   231: aload           11
        //   233: getfield        net/minecraft/entity/player/EntityPlayer.isDead:Z
        //   236: ifeq            240
        //   239: nop            
        //   240: aload           4
        //   242: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   247: astore          12
        //   249: aload           12
        //   251: invokeinterface java/util/Iterator.hasNext:()Z
        //   256: ifeq            687
        //   259: aload           12
        //   261: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   266: checkcast       Lnet/minecraft/util/math/BlockPos;
        //   269: astore          13
        //   271: aload           11
        //   273: aload           13
        //   275: invokevirtual   net/minecraft/entity/player/EntityPlayer.getDistanceSq:(Lnet/minecraft/util/math/BlockPos;)D
        //   278: ldc2_w          169.0
        //   281: dcmpl          
        //   282: iflt            286
        //   285: nop            
        //   286: aload           13
        //   288: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //   291: i2d            
        //   292: ldc2_w          0.5
        //   295: dadd           
        //   296: aload           13
        //   298: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //   301: iconst_1       
        //   302: iadd           
        //   303: i2d            
        //   304: aload           13
        //   306: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //   309: i2d            
        //   310: ldc2_w          0.5
        //   313: dadd           
        //   314: aload           11
        //   316: invokestatic    r/k/d/m/combat/AutoCrystal.d:(DDDLnet/minecraft/entity/Entity;)F
        //   319: ldc_w           10.0
        //   322: fdiv           
        //   323: f2d            
        //   324: dstore          14
        //   326: aload           13
        //   328: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //   331: i2d            
        //   332: ldc2_w          0.5
        //   335: dadd           
        //   336: aload           13
        //   338: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //   341: iconst_1       
        //   342: iadd           
        //   343: i2d            
        //   344: aload           13
        //   346: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //   349: i2d            
        //   350: ldc2_w          0.5
        //   353: dadd           
        //   354: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   357: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   360: invokestatic    r/k/d/m/combat/AutoCrystal.d:(DDDLnet/minecraft/entity/Entity;)F
        //   363: ldc_w           10.0
        //   366: fdiv           
        //   367: f2d            
        //   368: dstore          16
        //   370: dload           14
        //   372: aload_0        
        //   373: getfield        r/k/d/m/combat/AutoCrystal.szo:Lr/k/d/o/m;
        //   376: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   379: checkcast       Ljava/lang/Double;
        //   382: invokevirtual   java/lang/Double.doubleValue:()D
        //   385: dcmpl          
        //   386: iflt            686
        //   389: iconst_1       
        //   390: istore          18
        //   392: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   395: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   398: new             Lnet/minecraft/util/math/Vec3d;
        //   401: dup            
        //   402: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   405: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   408: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   411: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   414: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   417: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   420: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   423: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   426: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getEyeHeight:()F
        //   429: f2d            
        //   430: dadd           
        //   431: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   434: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   437: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   440: invokespecial   net/minecraft/util/math/Vec3d.<init>:(DDD)V
        //   443: new             Lnet/minecraft/util/math/Vec3d;
        //   446: dup            
        //   447: aload           13
        //   449: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //   452: i2d            
        //   453: ldc2_w          0.5
        //   456: dadd           
        //   457: aload           13
        //   459: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //   462: i2d            
        //   463: ldc2_w          0.5
        //   466: dsub           
        //   467: aload           13
        //   469: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //   472: i2d            
        //   473: ldc2_w          0.5
        //   476: dadd           
        //   477: invokespecial   net/minecraft/util/math/Vec3d.<init>:(DDD)V
        //   480: invokevirtual   net/minecraft/client/multiplayer/WorldClient.rayTraceBlocks:(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;
        //   483: astore          19
        //   485: aload           19
        //   487: ifnull          505
        //   490: aload           19
        //   492: getfield        net/minecraft/util/math/RayTraceResult.typeOfHit:Lnet/minecraft/util/math/RayTraceResult$Type;
        //   495: getstatic       net/minecraft/util/math/RayTraceResult$Type.BLOCK:Lnet/minecraft/util/math/RayTraceResult$Type;
        //   498: if_acmpne       505
        //   501: iconst_1       
        //   502: istore          18
        //   504: nop            
        //   505: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   508: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   511: aload           13
        //   513: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //   516: i2d            
        //   517: aload           13
        //   519: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //   522: i2d            
        //   523: aload           13
        //   525: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //   528: i2d            
        //   529: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(DDD)D
        //   532: aload_0        
        //   533: getfield        r/k/d/m/combat/AutoCrystal.sze:Lr/k/d/o/m;
        //   536: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   539: checkcast       Ljava/lang/Double;
        //   542: invokevirtual   java/lang/Double.doubleValue:()D
        //   545: dcmpg          
        //   546: ifgt            551
        //   549: iconst_1       
        //   550: nop            
        //   551: iconst_0       
        //   552: istore          18
        //   554: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   557: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   560: aload           13
        //   562: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //   565: i2d            
        //   566: aload           13
        //   568: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //   571: i2d            
        //   572: aload           13
        //   574: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //   577: i2d            
        //   578: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(DDD)D
        //   581: aload_0        
        //   582: getfield        r/k/d/m/combat/AutoCrystal.szv:Lr/k/d/o/m;
        //   585: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   588: checkcast       Ljava/lang/Double;
        //   591: invokevirtual   java/lang/Double.doubleValue:()D
        //   594: dcmpg          
        //   595: ifgt            600
        //   598: iconst_1       
        //   599: nop            
        //   600: iconst_0       
        //   601: istore          19
        //   603: iload           19
        //   605: ifeq            686
        //   608: iload           18
        //   610: ifeq            686
        //   613: dload           14
        //   615: dload           5
        //   617: dcmpl          
        //   618: ifle            646
        //   621: dload           14
        //   623: dstore          5
        //   625: dload           16
        //   627: dstore          7
        //   629: aload           13
        //   631: astore          9
        //   633: aload_0        
        //   634: aload           11
        //   636: putfield        r/k/d/m/combat/AutoCrystal.entity:Lnet/minecraft/entity/Entity;
        //   639: aload_0        
        //   640: aload           13
        //   642: putfield        r/k/d/m/combat/AutoCrystal.blockPos:Lnet/minecraft/util/math/BlockPos;
        //   645: nop            
        //   646: dload           14
        //   648: dload           5
        //   650: dcmpl          
        //   651: ifne            686
        //   654: dload           16
        //   656: dload           7
        //   658: dcmpg          
        //   659: ifge            686
        //   662: dload           14
        //   664: dstore          5
        //   666: dload           16
        //   668: dstore          7
        //   670: aload           13
        //   672: astore          9
        //   674: aload_0        
        //   675: aload           11
        //   677: putfield        r/k/d/m/combat/AutoCrystal.entity:Lnet/minecraft/entity/Entity;
        //   680: aload_0        
        //   681: aload           13
        //   683: putfield        r/k/d/m/combat/AutoCrystal.blockPos:Lnet/minecraft/util/math/BlockPos;
        //   686: nop            
        //   687: nop            
        //   688: dload           5
        //   690: aload_0        
        //   691: getfield        r/k/d/m/combat/AutoCrystal.szo:Lr/k/d/o/m;
        //   694: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   697: checkcast       Ljava/lang/Double;
        //   700: invokevirtual   java/lang/Double.doubleValue:()D
        //   703: dcmpg          
        //   704: iflt            712
        //   707: aload           9
        //   709: ifnonnull       713
        //   712: return         
        //   713: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   716: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   719: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHeldItemMainhand:()Lnet/minecraft/item/ItemStack;
        //   722: invokevirtual   net/minecraft/item/ItemStack.getItem:()Lnet/minecraft/item/Item;
        //   725: getstatic       net/minecraft/init/Items.END_CRYSTAL:Lnet/minecraft/item/Item;
        //   728: if_acmpeq       749
        //   731: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   734: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   737: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHeldItemOffhand:()Lnet/minecraft/item/ItemStack;
        //   740: invokevirtual   net/minecraft/item/ItemStack.getItem:()Lnet/minecraft/item/Item;
        //   743: getstatic       net/minecraft/init/Items.END_CRYSTAL:Lnet/minecraft/item/Item;
        //   746: if_acmpne       751
        //   749: iconst_1       
        //   750: nop            
        //   751: iconst_0       
        //   752: istore          10
        //   754: aload_0        
        //   755: getfield        r/k/d/m/combat/AutoCrystal.szx:Lr/k/d/o/m;
        //   758: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   761: checkcast       Ljava/lang/Boolean;
        //   764: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   767: ifne            771
        //   770: return         
        //   771: getstatic       net/minecraft/init/Items.END_CRYSTAL:Lnet/minecraft/item/Item;
        //   774: invokestatic    r/k/b/b.s:(Lnet/minecraft/item/Item;)I
        //   777: istore          11
        //   779: iload           11
        //   781: iflt            799
        //   784: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   787: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   790: getfield        net/minecraft/client/entity/EntityPlayerSP.inventory:Lnet/minecraft/entity/player/InventoryPlayer;
        //   793: iload           11
        //   795: putfield        net/minecraft/entity/player/InventoryPlayer.currentItem:I
        //   798: return         
        //   799: return         
        //   800: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   803: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   806: new             Lnet/minecraft/util/math/Vec3d;
        //   809: dup            
        //   810: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   813: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   816: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   819: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   822: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   825: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   828: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   831: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   834: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getEyeHeight:()F
        //   837: f2d            
        //   838: dadd           
        //   839: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   842: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   845: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   848: invokespecial   net/minecraft/util/math/Vec3d.<init>:(DDD)V
        //   851: new             Lnet/minecraft/util/math/Vec3d;
        //   854: dup            
        //   855: aload           9
        //   857: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //   860: i2d            
        //   861: ldc2_w          0.5
        //   864: dadd           
        //   865: aload           9
        //   867: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //   870: i2d            
        //   871: ldc2_w          0.5
        //   874: dsub           
        //   875: aload           9
        //   877: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //   880: i2d            
        //   881: ldc2_w          0.5
        //   884: dadd           
        //   885: invokespecial   net/minecraft/util/math/Vec3d.<init>:(DDD)V
        //   888: invokevirtual   net/minecraft/client/multiplayer/WorldClient.rayTraceBlocks:(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;
        //   891: astore          10
        //   893: aload           10
        //   895: ifnull          906
        //   898: aload           10
        //   900: getfield        net/minecraft/util/math/RayTraceResult.sideHit:Lnet/minecraft/util/EnumFacing;
        //   903: ifnonnull       912
        //   906: getstatic       net/minecraft/util/EnumFacing.UP:Lnet/minecraft/util/EnumFacing;
        //   909: astore          11
        //   911: nop            
        //   912: aload           10
        //   914: getfield        net/minecraft/util/math/RayTraceResult.sideHit:Lnet/minecraft/util/EnumFacing;
        //   917: astore          11
        //   919: getstatic       net/minecraft/util/EnumHand.MAIN_HAND:Lnet/minecraft/util/EnumHand;
        //   922: astore          12
        //   924: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   927: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   930: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHeldItemOffhand:()Lnet/minecraft/item/ItemStack;
        //   933: invokevirtual   net/minecraft/item/ItemStack.getItem:()Lnet/minecraft/item/Item;
        //   936: getstatic       net/minecraft/init/Items.END_CRYSTAL:Lnet/minecraft/item/Item;
        //   939: if_acmpne       947
        //   942: getstatic       net/minecraft/util/EnumHand.OFF_HAND:Lnet/minecraft/util/EnumHand;
        //   945: astore          12
        //   947: new             Lnet/minecraft/util/math/Vec3d;
        //   950: dup            
        //   951: aload           9
        //   953: invokespecial   net/minecraft/util/math/Vec3d.<init>:(Lnet/minecraft/util/math/Vec3i;)V
        //   956: ldc2_w          0.5
        //   959: ldc2_w          0.5
        //   962: ldc2_w          0.5
        //   965: invokevirtual   net/minecraft/util/math/Vec3d.add:(DDD)Lnet/minecraft/util/math/Vec3d;
        //   968: new             Lnet/minecraft/util/math/Vec3d;
        //   971: dup            
        //   972: aload           11
        //   974: invokevirtual   net/minecraft/util/EnumFacing.getDirectionVec:()Lnet/minecraft/util/math/Vec3i;
        //   977: invokespecial   net/minecraft/util/math/Vec3d.<init>:(Lnet/minecraft/util/math/Vec3i;)V
        //   980: ldc2_w          0.5
        //   983: invokevirtual   net/minecraft/util/math/Vec3d.scale:(D)Lnet/minecraft/util/math/Vec3d;
        //   986: invokevirtual   net/minecraft/util/math/Vec3d.add:(Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/Vec3d;
        //   989: astore          13
        //   991: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   994: getfield        net/minecraft/client/Minecraft.playerController:Lnet/minecraft/client/multiplayer/PlayerControllerMP;
        //   997: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //  1000: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1003: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //  1006: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //  1009: aload           9
        //  1011: aload           11
        //  1013: aload           13
        //  1015: aload           12
        //  1017: invokevirtual   net/minecraft/client/multiplayer/PlayerControllerMP.processRightClickBlock:(Lnet/minecraft/client/entity/EntityPlayerSP;Lnet/minecraft/client/multiplayer/WorldClient;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/EnumHand;)Lnet/minecraft/util/EnumActionResult;
        //  1020: pop            
        //  1021: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //  1024: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1027: aload           12
        //  1029: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.swingArm:(Lnet/minecraft/util/EnumHand;)V
        //  1032: return         
        //    StackMapTable: 00 28 FF 00 04 00 17 07 00 11 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 FF 00 01 00 17 07 00 11 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 2B 01 40 01 FF 00 22 00 17 07 00 11 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 01 40 01 FF 00 04 00 17 07 00 11 01 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 00 FF 00 1D 00 17 07 00 11 01 01 07 00 C6 07 00 D3 07 00 D9 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 26 00 17 07 00 11 01 01 07 00 C6 07 00 D3 07 00 D9 07 00 E1 00 00 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 00 00 17 07 00 11 01 01 07 00 C6 07 00 D3 07 00 D9 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 14 00 15 07 00 11 01 01 07 00 C6 07 00 D3 03 03 07 00 F1 07 00 D9 00 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 32 00 15 07 00 11 01 01 07 00 C6 07 00 D3 03 03 07 00 F1 07 00 D9 07 00 E1 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 08 00 15 07 00 11 01 01 07 00 C6 07 00 D3 03 03 07 00 F1 07 00 D9 07 00 E1 07 00 D9 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 24 00 15 07 00 11 01 01 07 00 C6 07 00 D3 03 03 07 00 F1 07 00 D9 07 00 E1 07 00 D9 07 00 F1 00 00 00 00 00 00 01 01 01 00 00 FF 00 DA 00 13 07 00 11 01 01 07 00 C6 07 00 D3 03 03 07 00 F1 07 00 D9 07 00 E1 07 00 D9 07 00 F1 03 03 01 07 00 1B 01 01 01 00 00 2D 40 01 01 2D 40 01 FF 00 2C 00 13 07 00 11 01 01 07 00 C6 07 00 D3 03 03 07 00 F1 07 00 D9 07 00 E1 07 00 D9 07 00 F1 03 03 01 01 01 01 01 00 00 FF 00 27 00 13 07 00 11 01 01 07 00 C6 07 00 D3 03 03 07 00 F1 07 00 D9 07 00 E1 07 00 D9 07 00 F1 03 03 00 00 01 01 01 00 00 FF 00 00 00 15 07 00 11 01 01 07 00 C6 07 00 D3 03 03 07 00 F1 07 00 D9 07 00 E1 07 00 D9 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 00 00 15 07 00 11 01 01 07 00 C6 07 00 D3 03 03 07 00 F1 07 00 D9 00 00 00 00 00 00 00 00 00 01 01 01 00 00 17 00 23 01 40 01 FF 00 12 00 15 07 00 11 01 01 07 00 C6 07 00 D3 03 03 07 00 F1 01 00 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 1B 00 15 07 00 11 01 01 07 00 C6 07 00 D3 03 03 07 00 F1 01 01 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 00 00 15 07 00 11 01 01 07 00 C6 07 00 D3 03 03 07 00 F1 01 00 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 69 00 15 07 00 11 01 01 07 00 C6 07 00 D3 03 03 07 00 F1 07 00 1B 00 00 00 00 00 00 00 00 00 01 01 01 00 00 05 FF 00 06 00 15 07 00 11 01 01 07 00 C6 07 00 D3 03 03 07 00 F1 07 00 1B 07 01 5A 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 1B 00 15 07 00 11 01 01 07 00 C6 07 00 D3 03 03 07 00 F1 07 00 1B 07 01 5A 07 01 5F 00 00 00 00 00 00 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void xg() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: nop            
        //     7: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //    10: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    13: getfield        net/minecraft/client/multiplayer/WorldClient.loadedEntityList:Ljava/util/List;
        //    16: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //    21: invokedynamic   BootstrapMethod #0, test:()Ljava/util/function/Predicate;
        //    26: invokeinterface java/util/stream/Stream.filter:(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
        //    31: invokedynamic   BootstrapMethod #1, apply:()Ljava/util/function/Function;
        //    36: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
        //    41: invokedynamic   BootstrapMethod #2, apply:()Ljava/util/function/Function;
        //    46: invokestatic    java/util/Comparator.comparing:(Ljava/util/function/Function;)Ljava/util/Comparator;
        //    49: invokeinterface java/util/stream/Stream.min:(Ljava/util/Comparator;)Ljava/util/Optional;
        //    54: aconst_null    
        //    55: invokevirtual   java/util/Optional.orElse:(Ljava/lang/Object;)Ljava/lang/Object;
        //    58: checkcast       Lnet/minecraft/entity/item/EntityEnderCrystal;
        //    61: astore_1       
        //    62: aload_1        
        //    63: ifnull          293
        //    66: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //    69: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    72: aload_1        
        //    73: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(Lnet/minecraft/entity/Entity;)F
        //    76: f2d            
        //    77: dstore_2       
        //    78: iconst_1       
        //    79: istore          4
        //    81: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //    84: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    87: new             Lnet/minecraft/util/math/Vec3d;
        //    90: dup            
        //    91: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //    94: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    97: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   100: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   103: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   106: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   109: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   112: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   115: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getEyeHeight:()F
        //   118: f2d            
        //   119: dadd           
        //   120: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   123: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   126: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   129: invokespecial   net/minecraft/util/math/Vec3d.<init>:(DDD)V
        //   132: new             Lnet/minecraft/util/math/Vec3d;
        //   135: dup            
        //   136: aload_1        
        //   137: getfield        net/minecraft/entity/item/EntityEnderCrystal.posX:D
        //   140: ldc2_w          0.5
        //   143: dadd           
        //   144: aload_1        
        //   145: getfield        net/minecraft/entity/item/EntityEnderCrystal.posY:D
        //   148: ldc2_w          0.5
        //   151: dsub           
        //   152: aload_1        
        //   153: getfield        net/minecraft/entity/item/EntityEnderCrystal.posZ:D
        //   156: ldc2_w          0.5
        //   159: dadd           
        //   160: invokespecial   net/minecraft/util/math/Vec3d.<init>:(DDD)V
        //   163: invokevirtual   net/minecraft/client/multiplayer/WorldClient.rayTraceBlocks:(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;
        //   166: astore          5
        //   168: aload           5
        //   170: getfield        net/minecraft/util/math/RayTraceResult.typeOfHit:Lnet/minecraft/util/math/RayTraceResult$Type;
        //   173: getstatic       net/minecraft/util/math/RayTraceResult$Type.BLOCK:Lnet/minecraft/util/math/RayTraceResult$Type;
        //   176: if_acmpne       202
        //   179: dload_2        
        //   180: aload_0        
        //   181: getfield        r/k/d/m/combat/AutoCrystal.sze:Lr/k/d/o/m;
        //   184: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   187: checkcast       Ljava/lang/Double;
        //   190: invokevirtual   java/lang/Double.doubleValue:()D
        //   193: dcmpg          
        //   194: ifgt            199
        //   197: iconst_1       
        //   198: nop            
        //   199: iconst_0       
        //   200: istore          4
        //   202: dload_2        
        //   203: aload_0        
        //   204: getfield        r/k/d/m/combat/AutoCrystal.szv:Lr/k/d/o/m;
        //   207: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   210: checkcast       Ljava/lang/Double;
        //   213: invokevirtual   java/lang/Double.doubleValue:()D
        //   216: dcmpg          
        //   217: ifgt            222
        //   220: iconst_1       
        //   221: nop            
        //   222: iconst_0       
        //   223: istore          5
        //   225: iload           5
        //   227: ifeq            293
        //   230: iload           4
        //   232: ifeq            293
        //   235: invokestatic    java/lang/System.nanoTime:()J
        //   238: ldc2_w          1000000
        //   241: ldiv           
        //   242: aload_0        
        //   243: getfield        r/k/d/m/combat/AutoCrystal.szi:J
        //   246: lsub           
        //   247: ldc2_w          250
        //   250: lcmp           
        //   251: iflt            293
        //   254: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   257: getfield        net/minecraft/client/Minecraft.playerController:Lnet/minecraft/client/multiplayer/PlayerControllerMP;
        //   260: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   263: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   266: aload_1        
        //   267: invokevirtual   net/minecraft/client/multiplayer/PlayerControllerMP.attackEntity:(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/entity/Entity;)V
        //   270: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //   273: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   276: getstatic       net/minecraft/util/EnumHand.MAIN_HAND:Lnet/minecraft/util/EnumHand;
        //   279: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.swingArm:(Lnet/minecraft/util/EnumHand;)V
        //   282: aload_0        
        //   283: invokestatic    java/lang/System.nanoTime:()J
        //   286: ldc2_w          1000000
        //   289: ldiv           
        //   290: putfield        r/k/d/m/combat/AutoCrystal.szi:J
        //   293: return         
        //    StackMapTable: 00 09 FF 00 04 00 09 07 00 11 00 00 00 00 00 00 00 01 00 00 00 FF 00 A2 00 08 07 00 11 07 01 C9 03 01 07 00 1B 01 01 01 00 00 1E 40 01 01 13 40 01 FF 00 45 00 09 07 00 11 07 01 C9 00 00 00 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void m() {
        r.k.b.u.p.d(255.0f, 255.0f, 255.0f, 1.0f);
        if (this.blockPos != null) {
            r.k.b.u.p.l(r.k.b.u.p.k(this.blockPos));
        }
        if (this.entity != null) {
            r.k.b.u.p.l(this.entity.getEntityBoundingBox());
        }
        r.k.b.u.p.xn();
    }
    
    public void a() {
    }
    
    public void t() {
        this.blockPos = null;
        this.entity = null;
    }
    
    private List<BlockPos> xu() {
        final double doubleValue = this.szv.yv();
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)d(new BlockPos(Math.floor(AutoCrystal.mc.player.posX), Math.floor(AutoCrystal.mc.player.posY), Math.floor(AutoCrystal.mc.player.posZ)), (float)doubleValue, (int)Math.round(this.szv.yv()), false, true, 0).stream().filter((Predicate<? super Object>)this::q).<List<? super Object>, ?>collect((Collector<? super Object, ?, List<? super Object>>)Collectors.<Object>toList()));
        return (List<BlockPos>)create;
    }
    
    public static List<BlockPos> d(final BlockPos p0, final float p1, final int p2, final boolean p3, final boolean p4, final int p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: aconst_null    
        //     5: areturn        
        //     6: nop            
        //     7: nop            
        //     8: new             Ljava/util/ArrayList;
        //    11: dup            
        //    12: invokespecial   java/util/ArrayList.<init>:()V
        //    15: astore          6
        //    17: aload_0        
        //    18: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //    21: istore          7
        //    23: aload_0        
        //    24: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //    27: istore          8
        //    29: aload_0        
        //    30: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //    33: istore          9
        //    35: iload           7
        //    37: fload_1        
        //    38: f2i            
        //    39: isub           
        //    40: istore          10
        //    42: iload           10
        //    44: i2f            
        //    45: iload           7
        //    47: i2f            
        //    48: fload_1        
        //    49: fadd           
        //    50: fcmpg          
        //    51: ifgt            224
        //    54: iload           9
        //    56: fload_1        
        //    57: f2i            
        //    58: isub           
        //    59: istore          11
        //    61: iload           11
        //    63: i2f            
        //    64: iload           9
        //    66: i2f            
        //    67: fload_1        
        //    68: fadd           
        //    69: fcmpg          
        //    70: ifgt            220
        //    73: iload           4
        //    75: ifeq            84
        //    78: iload           8
        //    80: fload_1        
        //    81: f2i            
        //    82: isub           
        //    83: nop            
        //    84: iload           8
        //    86: istore          12
        //    88: iload           12
        //    90: i2f            
        //    91: iload           4
        //    93: ifeq            102
        //    96: iload           8
        //    98: i2f            
        //    99: fload_1        
        //   100: fadd           
        //   101: nop            
        //   102: iload           8
        //   104: iload_2        
        //   105: iadd           
        //   106: i2f            
        //   107: fcmpg          
        //   108: ifge            216
        //   111: iload           7
        //   113: iload           10
        //   115: isub           
        //   116: iload           7
        //   118: iload           10
        //   120: isub           
        //   121: imul           
        //   122: iload           9
        //   124: iload           11
        //   126: isub           
        //   127: iload           9
        //   129: iload           11
        //   131: isub           
        //   132: imul           
        //   133: iadd           
        //   134: iload           4
        //   136: ifeq            151
        //   139: iload           8
        //   141: iload           12
        //   143: isub           
        //   144: iload           8
        //   146: iload           12
        //   148: isub           
        //   149: imul           
        //   150: nop            
        //   151: iconst_0       
        //   152: iadd           
        //   153: i2d            
        //   154: dstore          13
        //   156: dload           13
        //   158: fload_1        
        //   159: fload_1        
        //   160: fmul           
        //   161: f2d            
        //   162: dcmpg          
        //   163: ifge            212
        //   166: iload_3        
        //   167: ifeq            184
        //   170: dload           13
        //   172: fload_1        
        //   173: fconst_1       
        //   174: fsub           
        //   175: fload_1        
        //   176: fconst_1       
        //   177: fsub           
        //   178: fmul           
        //   179: f2d            
        //   180: dcmpg          
        //   181: iflt            212
        //   184: new             Lnet/minecraft/util/math/BlockPos;
        //   187: dup            
        //   188: iload           10
        //   190: iload           12
        //   192: iload           5
        //   194: iadd           
        //   195: iload           11
        //   197: invokespecial   net/minecraft/util/math/BlockPos.<init>:(III)V
        //   200: astore          15
        //   202: aload           6
        //   204: aload           15
        //   206: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   211: pop            
        //   212: iinc            12, 1
        //   215: nop            
        //   216: iinc            11, 1
        //   219: nop            
        //   220: iinc            10, 1
        //   223: nop            
        //   224: aload           6
        //   226: areturn        
        //    Signature:
        //  (Lnet/minecraft/util/math/BlockPos;FIZZI)Ljava/util/List<Lnet/minecraft/util/math/BlockPos;>;
        //    StackMapTable: 00 11 FF 00 04 00 13 07 00 F1 02 01 01 01 01 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 01 FF 00 01 00 13 07 00 F1 02 01 01 01 01 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 21 00 13 07 00 F1 02 01 01 01 01 07 00 C6 01 01 01 01 00 00 00 00 00 01 01 01 00 00 FF 00 12 00 13 07 00 F1 02 01 01 01 01 07 00 C6 01 01 01 01 01 00 00 00 00 01 01 01 00 00 16 41 01 FF 00 01 00 13 07 00 F1 02 01 01 01 01 07 00 C6 01 01 01 01 01 01 00 00 00 01 01 01 00 00 4D 02 FF 00 04 00 13 07 00 F1 02 01 01 01 01 07 00 C6 01 01 01 01 01 01 00 00 00 01 01 01 00 02 02 02 6B 01 FF 00 00 00 13 07 00 F1 02 01 01 01 01 07 00 C6 01 01 01 01 01 01 00 00 00 01 01 01 00 02 01 01 FF 00 1F 00 12 07 00 F1 02 01 01 01 01 07 00 C6 01 01 01 01 01 01 03 00 01 01 01 00 00 1B FF 00 03 00 13 07 00 F1 02 01 01 01 01 07 00 C6 01 01 01 01 01 01 00 00 00 01 01 01 00 00 FF 00 03 00 13 07 00 F1 02 01 01 01 01 07 00 C6 01 01 01 01 01 00 00 00 00 01 01 01 00 00 FF 00 03 00 13 07 00 F1 02 01 01 01 01 07 00 C6 01 01 01 01 00 00 00 00 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private boolean q(final BlockPos p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: iconst_1       
        //     5: ireturn        
        //     6: nop            
        //     7: nop            
        //     8: aload_1        
        //     9: iconst_0       
        //    10: iconst_1       
        //    11: iconst_0       
        //    12: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //    15: astore_2       
        //    16: aload_1        
        //    17: iconst_0       
        //    18: iconst_2       
        //    19: iconst_0       
        //    20: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //    23: astore_3       
        //    24: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //    27: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    30: aload_1        
        //    31: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    34: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //    39: getstatic       net/minecraft/init/Blocks.BEDROCK:Lnet/minecraft/block/Block;
        //    42: if_acmpeq       66
        //    45: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //    48: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    51: aload_1        
        //    52: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    55: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //    60: getstatic       net/minecraft/init/Blocks.OBSIDIAN:Lnet/minecraft/block/Block;
        //    63: if_acmpne       122
        //    66: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //    69: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    72: aload_2        
        //    73: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //    76: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    79: aload_3        
        //    80: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    83: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //    88: getstatic       net/minecraft/init/Blocks.AIR:Lnet/minecraft/block/Block;
        //    91: if_acmpne       122
        //    94: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //    97: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   100: ldc_w           Lnet/minecraft/entity/Entity;.class
        //   103: new             Lnet/minecraft/util/math/AxisAlignedBB;
        //   106: dup            
        //   107: aload_2        
        //   108: invokespecial   net/minecraft/util/math/AxisAlignedBB.<init>:(Lnet/minecraft/util/math/BlockPos;)V
        //   111: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getEntitiesWithinAABB:(Ljava/lang/Class;Lnet/minecraft/util/math/AxisAlignedBB;)Ljava/util/List;
        //   114: invokeinterface java/util/List.isEmpty:()Z
        //   119: ifne            124
        //   122: iconst_0       
        //   123: ireturn        
        //   124: iconst_1       
        //   125: ireturn        
        //    StackMapTable: 00 06 FF 00 04 00 07 07 00 11 07 00 F1 00 00 00 00 01 00 00 01 FF 00 3B 00 07 07 00 11 07 00 F1 07 00 F1 07 00 F1 01 01 01 00 00 06 30 01
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static float d(final double n, final double n2, final double n3, final Entity entity) {
        final double n4 = (1.0 - entity.getDistance(n, n2, n3) / 12.0) * entity.world.getBlockDensity(new Vec3d(n, n2, n3), entity.getEntityBoundingBox());
        final float n5 = (float)(int)((n4 * n4 + n4) / 2.0 * 7.0 * 12.0 + 1.0);
        double n6 = 1.0;
        if (entity instanceof EntityLivingBase) {
            n6 = d((EntityLivingBase)entity, l(n5), new Explosion((World)AutoCrystal.mc.world, (Entity)null, n, n2, n3, 6.0f, false, true));
        }
        return (float)n6;
    }
    
    private static float d(final EntityLivingBase entityLivingBase, float n, final Explosion explosion) {
        if (entityLivingBase instanceof EntityPlayer) {
            final EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
            n = CombatRules.getDamageAfterAbsorb(n, (float)entityPlayer.getTotalArmorValue(), (float)entityPlayer.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
            return n;
        }
        n = CombatRules.getDamageAfterAbsorb(n, (float)entityLivingBase.getTotalArmorValue(), (float)entityLivingBase.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
        return n;
    }
    
    private static float l(final float p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: fconst_0       
        //     5: freturn        
        //     6: nop            
        //     7: getstatic       r/k/d/m/combat/AutoCrystal.mc:Lnet/minecraft/client/Minecraft;
        //    10: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    13: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getDifficulty:()Lnet/minecraft/world/EnumDifficulty;
        //    16: invokevirtual   net/minecraft/world/EnumDifficulty.getId:()I
        //    19: istore_1       
        //    20: fload_0        
        //    21: fconst_0       
        //    22: nop            
        //    23: iload_1        
        //    24: iconst_2       
        //    25: if_icmpne       30
        //    28: fconst_1       
        //    29: nop            
        //    30: iload_1        
        //    31: iconst_1       
        //    32: if_icmpne       39
        //    35: ldc_w           0.5
        //    38: nop            
        //    39: ldc_w           1.5
        //    42: fmul           
        //    43: freturn        
        //    StackMapTable: 00 06 FF 00 04 00 05 02 00 00 00 01 00 00 FF 00 01 00 05 02 00 00 01 01 00 00 FF 00 10 00 05 02 01 01 01 01 00 01 02 46 02 48 02 FF 00 02 00 05 02 01 01 01 01 00 02 02 02
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static /* synthetic */ Float d(final EntityEnderCrystal entityEnderCrystal) {
        return AutoCrystal.mc.player.getDistance((Entity)entityEnderCrystal);
    }
    
    private static /* synthetic */ EntityEnderCrystal d(final Entity entity) {
        return (EntityEnderCrystal)entity;
    }
    
    private static /* synthetic */ boolean s(final Entity entity) {
        return entity instanceof EntityEnderCrystal;
    }
}
