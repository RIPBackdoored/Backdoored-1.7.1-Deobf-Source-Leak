package r.k;

import net.minecraft.client.multiplayer.ServerData;
import \u0000r.\u0000k.\u0000t;
import club.minnced.discord.rpc.DiscordEventHandlers;
import net.minecraftforge.fml.common.FMLLog;
import club.minnced.discord.rpc.DiscordRichPresence;
import club.minnced.discord.rpc.DiscordRPC;

public class t
{
    private static final String gs;
    private static final DiscordRPC gl;
    private static DiscordRichPresence gy;
    private static boolean gx;
    public static final boolean gk;
    public static final int gq;
    public static final boolean gv;
    
    public t() {
        super();
    }
    
    public static boolean sg() {
        FMLLog.log.info("Starting Discord RPC");
        if (t.gx) {
            return false;
        }
        t.gx = true;
        final DiscordEventHandlers discordEventHandlers = new DiscordEventHandlers();
        discordEventHandlers.disconnected = \u0000t::d;
        t.gl.Discord_Initialize("640251431257112576", discordEventHandlers, true, "");
        t.gy.startTimestamp = System.currentTimeMillis() / 1000L;
        t.gy.details = "Main Menu";
        t.gy.state = "discord.gg/pdMhDwN";
        t.gy.largeImageKey = "backdoored_logo";
        t.gl.Discord_UpdatePresence(t.gy);
        new Thread(\u0000t::su, "Discord-RPC-Callback-Handler").start();
        FMLLog.log.info("Discord RPC initialised succesfully");
        return true;
    }
    
    private static /* synthetic */ void su() {
        if (!Thread.currentThread().isInterrupted()) {
            try {
                t.gl.Discord_RunCallbacks();
                int intValue = 0;
                int intValue2 = 0;
                if (h.mc.isIntegratedServerRunning()) {}
                if (h.mc.getCurrentServerData() != null) {
                    final ServerData currentServerData = h.mc.getCurrentServerData();
                    if (!currentServerData.serverIP.equals("")) {
                        final String serverIP = currentServerData.serverIP;
                        if (currentServerData.populationInfo != null) {
                            final String[] split = currentServerData.populationInfo.split("/");
                            if (split.length >= 2) {
                                intValue = Integer.valueOf(split[0]);
                                intValue2 = Integer.valueOf(split[1]);
                            }
                        }
                        if (serverIP.contains("2b2t.org")) {
                            try {
                                if (u.lsr.startsWith("Position in queue: ")) {
                                    new StringBuilder().append(serverIP).append(" ").append(Integer.parseInt(u.lsr.substring(19))).append(" in queue").toString();
                                }
                            }
                            catch (Throwable t) {
                                t.printStackTrace();
                            }
                        }
                    }
                }
                final String details = "Main Menu";
                final String state = "discord.gg/ncQkFKU";
                if (!details.equals(t.gy.details) || !state.equals(t.gy.state)) {
                    t.gy.startTimestamp = System.currentTimeMillis() / 1000L;
                }
                t.gy.details = details;
                t.gy.state = state;
                t.gy.partySize = intValue;
                t.gy.partyMax = intValue2;
                t.gl.Discord_UpdatePresence(t.gy);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                try {
                    Thread.sleep(5000L);
                }
                catch (InterruptedException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
    
    private static /* synthetic */ void d(final int n, final String s) {
        System.out.println("Discord RPC disconnected, var1: " + String.valueOf(n) + ", var2: " + s);
    }
    
    static {
        gs = "640251431257112576";
        gl = DiscordRPC.INSTANCE;
        t.gy = new DiscordRichPresence();
        t.gx = false;
    }
}
