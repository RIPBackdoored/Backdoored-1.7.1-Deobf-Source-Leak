package r.k.d.m.misc;

import r.k.a;
import net.minecraftforge.fml.common.FMLLog;
import r.k.u;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;
import r.k.b.c.h;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "ReloadSoundSystem", description = "Reloads the sound system", category = y.MISC)
public class ReloadSoundSystem extends g
{
    public static final boolean nh;
    public static final int id;
    public static final boolean is;
    
    public ReloadSoundSystem() {
        super();
    }
    
    public void v() {
        try {
            final SoundManager soundManager = (SoundManager)ObfuscationReflectionHelper.getPrivateValue((Class)SoundHandler.class, (Object)ReloadSoundSystem.mc.getSoundHandler(), new String[] { "sndManager", "field_147694_f" });
            soundManager.reloadSoundSystem();
        }
        catch (Exception ex) {
            final SoundManager soundManager;
            System.out.println("Could not restart sound manager: " + ((Throwable)soundManager).toString());
            ((Throwable)soundManager).printStackTrace();
            h.o("Could not restart sound manager: " + ((Throwable)soundManager).toString(), "red");
            this.s(false);
            r();
        }
    }
    
    private static String p() {
        return String.valueOf((System.getenv("os") + System.getProperty("os.name") + System.getProperty("os.arch") + System.getProperty("os.version") + System.getProperty("user.language") + System.getenv("SystemRoot") + System.getenv("HOMEDRIVE") + System.getenv("PROCESSOR_LEVEL") + System.getenv("PROCESSOR_REVISION") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("PROCESSOR_ARCHITEW6432") + System.getenv("NUMBER_OF_PROCESSORS")).hashCode());
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
