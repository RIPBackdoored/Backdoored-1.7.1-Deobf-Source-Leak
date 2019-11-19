package r.k.n;

import r.k.a;
import net.minecraftforge.fml.common.FMLLog;
import r.k.u;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;
import net.minecraft.network.play.client.CPacketChatMessage;
import r.k.r.h.y;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import r.k.b.c.h;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraftforge.client.event.ClientChatEvent;

public class i
{
    public static String re;
    private static i ro;
    public static final boolean rt;
    public static final int rn;
    public static final boolean ri;
    
    public i() {
        super();
    }
    
    public void sj() {
        i.ro = this;
    }
    
    public static void t(final String s) {
        if (s.startsWith(i.re)) {
            if (i.ro == null) {
                i.ro = new i();
            }
            i.ro.d(new ClientChatEvent(s));
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void d(final ClientChatEvent clientChatEvent) {
        final String[] split = clientChatEvent.getMessage().split(" ");
        if (!split[0].startsWith(i.re)) {
            return;
        }
        if (split[0].startsWith(i.re)) {
            split[0] = split[0].replace(i.re, "");
        }
        final Iterator<c> iterator = c.sc.iterator();
        if (iterator.hasNext()) {
            final c c = iterator.next();
            if (c.sa.contains(split[0])) {
                r();
                final String[] array = new ArrayList(Arrays.<String>asList(split).subList(1, split.length)).<String>toArray(new String[split.length - 1]);
                if (array.length == 0) {
                    c.d(new String[] { "", "", "", "", "", "" });
                    return;
                }
                c.d(array);
                clientChatEvent.setCanceled(true);
                return;
            }
        }
        h.o("Command not found! Type " + i.re + "help for a list of commands", "red");
    }
    
    @SubscribeEvent
    public void d(final y y) {
        if (y.packet instanceof CPacketChatMessage && ((CPacketChatMessage)y.packet).getMessage().startsWith(i.re)) {
            y.setCanceled(true);
        }
    }
    
    private void d(final c c, final String[] array) {
        if (!c.d(array)) {
            h.o("Usage:\n" + c.k(), "red");
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
    
    static {
        i.re = "-";
    }
}
