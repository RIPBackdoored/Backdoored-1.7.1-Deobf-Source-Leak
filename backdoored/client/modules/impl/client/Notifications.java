package r.k.d.m.client;

import r.k.a;
import net.minecraftforge.fml.common.FMLLog;
import r.k.u;
import com.google.common.hash.Hashing;
import r.k.b.c.h;
import org.apache.commons.io.FileUtils;
import java.nio.charset.StandardCharsets;
import java.io.File;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.server.SPacketChat;
import r.k.b.t;
import net.minecraft.entity.player.EntityPlayer;
import r.k.d.o.h.p;
import r.k.d.o.m;
import r.k.d.m.b.b;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Notifications", description = "Toast Notifications", category = y.CLIENT)
public class Notifications extends g
{
    public static b sqw;
    private String sqg;
    private m<Boolean> squ;
    private m<Boolean> sqz;
    private m<Boolean> sqc;
    public static final boolean sqa;
    public static final int sqm;
    public static final boolean sqh;
    
    public Notifications() {
        this.sqg = null;
        this.squ = (m<Boolean>)new p("Discord Webhook", this, false);
        this.sqz = (m<Boolean>)new p("Visual Range", this, true);
        this.sqc = (m<Boolean>)new p("Queue", this, true);
    }
    
    public void s(final EntityPlayer entityPlayer) {
        if (!this.lo() || !this.sqz.yv()) {
            return;
        }
        t.d("Visual Range", entityPlayer.getDisplayNameString() + " entered your visual range");
        r();
    }
    
    @SubscribeEvent
    public void l(final r.k.r.h.p p) {
        if (this.lo() && this.sqc.yv() && p.packet instanceof SPacketChat) {
            final SPacketChat sPacketChat = (SPacketChat)p.packet;
            sPacketChat.getChatComponent().getUnformattedText().toLowerCase();
            t.se(sPacketChat.getChatComponent().getUnformattedText());
        }
    }
    
    public void v() {
        try {
            final File file = new File("Backdoored/discordwebhook.txt");
            if (!file.exists()) {
                file.createNewFile();
                throw new RuntimeException("discordwebhook.txt did not exist");
            }
            this.sqg = FileUtils.readFileToString(file, StandardCharsets.UTF_8).trim();
            if (this.sqg.isEmpty()) {
                throw new RuntimeException("discordwebhook.txt was empty");
            }
            h.sj("Set discord webhook to: " + this.sqg);
        }
        catch (Exception ex) {
            h.sj("Couldnt get discord webhook: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    private void ss(final String s) {
        if (this.squ.yv()) {}
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
