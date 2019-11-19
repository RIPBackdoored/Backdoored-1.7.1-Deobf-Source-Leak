package r.k.n;

import net.minecraftforge.common.MinecraftForge;
import java.util.Arrays;
import net.minecraft.client.Minecraft;
import java.util.List;
import java.util.ArrayList;

public abstract class c implements v
{
    public static ArrayList<c> sc;
    protected List<String> sa;
    public Minecraft mc;
    public static final boolean sh;
    public static final int ld;
    public static final boolean ls;
    
    private c() {
        super();
        this.mc = Minecraft.getMinecraft();
    }
    
    c(final String s) {
        this(new String[] { s });
    }
    
    c(final String... array) {
        this(Arrays.<String>asList(array));
    }
    
    c(final List<String> sa) {
        super();
        this.mc = Minecraft.getMinecraft();
        this.sa = sa;
        c.sc.add(this);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @Override
    public abstract boolean d(final String[] p0);
    
    @Override
    public void d(final String[] array, final String s) {
    }
    
    @Override
    public boolean d(final String[] array, final String[] array2, final String s) {
        if (array[0].equals("")) {
            this.d(array2, s);
            return false;
        }
        int n = 0;
        if (n <= array2.length) {
            if (array2[n].equals(array[0])) {
                return true;
            }
            if (!array2[n].equals(array[0]) && n == array2.length - 1) {
                this.d(array2, s);
                return false;
            }
            ++n;
        }
        return true;
    }
    
    @Override
    public abstract String k();
    
    static {
        c.sc = new ArrayList<c>();
    }
}
