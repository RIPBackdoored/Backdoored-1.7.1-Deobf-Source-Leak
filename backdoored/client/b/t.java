package r.k.b;

import r.k.b.c.h;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.TrayIcon;
import java.awt.SystemTray;

public class t
{
    private static SystemTray lle;
    private static TrayIcon llo;
    public static final boolean llt;
    public static final int lln;
    public static final boolean lli;
    
    public t() {
        super();
        try {
            t.lle = SystemTray.getSystemTray();
            (t.llo = new TrayIcon(new BufferedImage(20, 20, 1), "Tray Demo")).setToolTip("Backdoored");
            t.lle.add(t.llo);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            h.sj("Could not send notification due to error: " + ex.toString());
        }
    }
    
    public static void se(final String s) {
        d("Backdoored", s);
    }
    
    public static void d(final String s, final String s2) {
        if (t.llo == null) {
            new t();
        }
        t.llo.displayMessage(s, s2, TrayIcon.MessageType.INFO);
    }
}
