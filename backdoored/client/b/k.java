package r.k.b;

import java.awt.Color;
import java.util.Map;
import r.k.h;

public class k implements h
{
    public static final Map<String, String> hw;
    public static final boolean hg;
    public static final int hu;
    public static final boolean hz;
    
    public k() {
        super();
    }
    
    public static Color lz() {
        final Color color = new Color((int)Long.parseLong(Integer.toHexString(Color.HSBtoRGB((System.nanoTime() + 10L) / 1.0E10f % 1.0f, 1.0f, 1.0f)), 16));
        return new Color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    public static void d(final String s, final int n, final int n2) {
        d(s, n, n2, false);
    }
    
    public static void d(final String s, final int n, final int n2, final boolean b) {
        final float n3 = System.currentTimeMillis() % 11520L / 11520.0f;
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n4 = 0;
        if (n4 < length) {
            final char c = charArray[n4];
            k.mc.fontRenderer.drawString(String.valueOf(c), (float)n, (float)n2, Color.HSBtoRGB(n3 + n2 / (float)k.mc.displayHeight + n / (float)k.mc.displayWidth, 1.0f, 1.0f), b);
            final int n5 = n + k.mc.fontRenderer.getCharWidth(c);
            ++n4;
        }
    }
    
    public static String a(final String s) {
        return k.hw.getOrDefault(s.replace(" ", "_").trim().toLowerCase(), "§d");
    }
    
    static {
        hw = new k$y();
    }
}
