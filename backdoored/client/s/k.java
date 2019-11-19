package r.k.s;

import com.mojang.authlib.exceptions.AuthenticationException;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraft.client.Minecraft;
import r.k.b.z;
import r.k.g.c;
import r.k.h;

public class k implements h
{
    public static final boolean sdx;
    public static final int sdk;
    public static final boolean sdq;
    
    public k() {
        super();
    }
    
    public static boolean v(final String s, final String s2) throws AuthenticationException {
        System.out.println(c.v(13) + s);
        final z z = new z(s, s2);
        if (z.lf()) {
            try {
                ObfuscationReflectionHelper.setPrivateValue((Class)Minecraft.class, (Object)h.mc, (Object)z.lb(), new String[] { c.v(14), c.v(15) });
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }
    
    public static String lm() {
        return h.mc.getSession().getUsername();
    }
    
    public static String lh() {
        return h.mc.getSession().getProfile().getId().toString();
    }
}
