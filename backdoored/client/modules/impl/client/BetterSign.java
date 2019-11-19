package r.k.d.m.client;

import r.k.a;
import net.minecraftforge.fml.common.FMLLog;
import r.k.u;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.b.c.h;
import net.minecraft.client.gui.GuiScreen;
import r.k.e.m;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraftforge.client.event.GuiOpenEvent;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Better Sign", description = "Better Sign GUI", category = y.CLIENT)
public class BetterSign extends g
{
    public static final boolean if;
    public static final int ib;
    public static final boolean iw;
    
    public BetterSign() {
        super();
    }
    
    @SubscribeEvent
    public void d(final GuiOpenEvent guiOpenEvent) {
        try {
            guiOpenEvent.setGui((GuiScreen)new m((TileEntitySign)ObfuscationReflectionHelper.getPrivateValue((Class)GuiEditSign.class, (Object)guiOpenEvent.getGui(), new String[] { "tileSign", "field_146848_f" })));
        }
        catch (Exception ex) {
            h.sj("Disabled Secret Close due to an error: " + ex.toString());
            this.s(false);
            r();
        }
    }
    
    private static String p() {
        new StringBuilder().append(System.getenv("os")).append(System.getProperty("os.name")).append(System.getProperty("os.arch")).append(System.getProperty("os.version")).append(System.getProperty("user.language")).append(System.getenv("SystemRoot")).append(System.getenv("HOMEDRIVE")).append(System.getenv("PROCESSOR_LEVEL")).append(System.getenv("PROCESSOR_REVISION")).append(System.getenv("PROCESSOR_IDENTIFIER")).append(System.getenv("PROCESSOR_ARCHITECTURE")).append(System.getenv("PROCESSOR_ARCHITEW6432"));
        "NUMBER_OF_PROCESSORS";
        final String s;
        return String.valueOf(s.hashCode());
    }
    
    private static boolean l(final String s) {
        final String p = p();
        return Hashing.sha512().hashString((CharSequence)(Hashing.sha1().hashString((CharSequence)p, StandardCharsets.UTF_8).toString() + p + "dontcrack"), StandardCharsets.UTF_8).toString().equalsIgnoreCase(s);
    }
    
    private static void r() {
        if (!l(u.lsn)) {
            FMLLog.log.info("Invalid License detected");
            FMLLog.log.info("Provided License: " + u.lsn);
            FMLLog.log.info("HWID: " + p());
            a.llp = true;
            throw new r.k.b.m.y("Invalid License");
        }
    }
}
