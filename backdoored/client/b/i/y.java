package r.k.b.i;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.r.ck;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.util.ResourceLocation;
import java.util.HashMap;

public class y
{
    private static HashMap<String, y$p> cq;
    private static ResourceLocation resourceLocation;
    private static ResourceLocation resourceLocation;
    private static ResourceLocation resourceLocation;
    public static final boolean co;
    public static final int ct;
    public static final boolean cn;
    private static HashMap<String, y$p> cq;
    private static ResourceLocation resourceLocation;
    private static ResourceLocation resourceLocation;
    private static ResourceLocation resourceLocation;
    public static final boolean co;
    public static final int ct;
    public static final boolean cn;
    
    public y() {
        super();
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.lq();
        this.lv();
        this.lj();
    }
    
    private boolean lq() {
        return this.d(y.cq, "http://pastebin.com/raw/g4wjzg5U", y$p.svx);
    }
    
    private boolean lv() {
        return this.d(y.cq, "http://pastebin.com/raw/ZMZcF3nJ", y$p.svk);
    }
    
    private boolean lj() {
        return this.d(y.cq, "http://pastebin.com/raw/drFrFW5r", y$p.svq);
    }
    
    private boolean d(final HashMap<String, y$p> hashMap, final String s, final y$p y$p) {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(s).openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    hashMap.put(line.trim().toLowerCase(), y$p);
                    break;
                }
            }
            bufferedReader.close();
            System.out.println("Gave " + y$p.name() + " capes to: " + hashMap.toString());
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static y$p b(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Could not show original bytecode, likely due to the same error.
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public y() {
        super();
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.lq();
        this.lv();
        this.lj();
    }
    
    private boolean lq() {
        return this.d(y.cq, "http://pastebin.com/raw/g4wjzg5U", y$p.svx);
    }
    
    private boolean lv() {
        return this.d(y.cq, "http://pastebin.com/raw/ZMZcF3nJ", y$p.svk);
    }
    
    private boolean lj() {
        return this.d(y.cq, "http://pastebin.com/raw/drFrFW5r", y$p.svq);
    }
    
    private boolean d(final HashMap<String, y$p> hashMap, final String s, final y$p y$p) {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(s).openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    hashMap.put(line.trim().toLowerCase(), y$p);
                    break;
                }
            }
            bufferedReader.close();
            System.out.println("Gave " + y$p.name() + " capes to: " + hashMap.toString());
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static y$p b(final String s) {
        if (y.cq == null) {}
        return y.cq.getOrDefault(s.trim().toLowerCase(), y$p.svv);
    }
    
    @SubscribeEvent
    public void d(final ck ck) {
        switch (y$i.mb[b(ck.networkPlayerInfo.getGameProfile().getName()).ordinal()]) {
            case 1:
                ck.resourceLocation = y.resourceLocation;
            case 2:
                ck.resourceLocation = y.resourceLocation;
            case 3:
                ck.resourceLocation = y.resourceLocation;
                break;
        }
    }
    
    static {
        y.cq = null;
        y.resourceLocation = new ResourceLocation("backdoored", "textures/cape_backdoored_dev.png");
        y.resourceLocation = new ResourceLocation("backdoored", "textures/cape_backdoored.png");
        y.resourceLocation = new ResourceLocation("backdoored", "textures/popbob.png");
    }
}
