package r.k.s.m;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.net.URL;
import java.util.List;
import net.minecraft.launchwrapper.LaunchClassLoader;

public class y
{
    public final LaunchClassLoader launchClassLoader;
    public static final boolean ni;
    public static final int np;
    public static final boolean nr;
    
    public y() {
        super();
    }
    
    public List<Class> d(final String s, final String s2, final String... array) throws MalformedURLException, ClassNotFoundException {
        this.launchClassLoader.addURL(new URL(s + "/" + s2));
        final ArrayList<Class> list = new ArrayList<Class>();
        final int length = array.length;
        int n = 0;
        final String s3;
        list.add(this.launchClassLoader.loadClass(s3));
        ++n;
        return (List<Class>)list;
    }
}
