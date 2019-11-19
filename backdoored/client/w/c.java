package r.k.w;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import r.k.n.i;
import r.k.p.c.h;
import r.k.p.c.y;
import r.k.d.o.h.u;
import r.k.d.o.m;
import r.k.d.o.j;
import r.k.d.m.g;
import r.k.d.m.o;
import org.json.JSONObject;
import java.io.InputStream;
import org.json.JSONTokener;
import java.io.FileInputStream;
import java.io.File;

public class c
{
    public static final boolean km;
    public static final int kh;
    public static final boolean qd;
    
    public c() {
        super();
    }
    
    static void s(final File file) throws FileNotFoundException {
        final JSONObject jsonObject = new JSONObject(new JSONTokener(new FileInputStream(file)));
        try {
            final JSONObject jsonObject2 = jsonObject.getJSONObject("Hacks");
            final Iterator<g> iterator = o.lc().iterator();
            if (iterator.hasNext()) {
                final g g = iterator.next();
                try {
                    final JSONObject jsonObject3 = jsonObject2.getJSONObject(g.cm);
                    g.s(jsonObject3.getBoolean("Enabled"));
                    final ArrayList<m> s = j.s(g);
                    if (s != null) {
                        final Iterator<m<Enum<?>>> iterator2 = s.iterator();
                        if (iterator2.hasNext()) {
                            final m<Enum<?>> m = iterator2.next();
                            try {
                                if (m instanceof u) {
                                    final Enum[] array = (Enum[])m.yv().getClass().getEnumConstants();
                                    final int length = array.length;
                                    int n = 0;
                                    if (n < length) {
                                        final Enum enum1 = array[n];
                                        if (enum1.name().equalsIgnoreCase(jsonObject3.getString(m.o()))) {
                                            m.d(enum1);
                                        }
                                        ++n;
                                    }
                                }
                                m.d((Enum<?>)m.sy().cast(jsonObject3.get(m.o())));
                            }
                            catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        catch (Exception ex6) {
            try {
                final JSONObject jsonObject4 = jsonObject.getJSONObject("Hud");
                final Iterator<y> iterator3 = r.k.u.lsp.llz.iterator();
                if (iterator3.hasNext()) {
                    final y y = iterator3.next();
                    try {
                        final JSONObject jsonObject5 = jsonObject4.getJSONObject(y.o());
                        y.d(jsonObject5.getBoolean("Visible"));
                        if (y instanceof h) {
                            y.z().slo = jsonObject5.getInt("x");
                            y.z().slt = jsonObject5.getInt("y");
                        }
                    }
                    catch (Exception ex3) {
                        ex3.printStackTrace();
                    }
                }
            }
            catch (Exception ex4) {
                ex4.printStackTrace();
                try {
                    i.re = jsonObject.getJSONObject("Commands").getString("Prefix");
                }
                catch (Exception ex5) {
                    ex5.printStackTrace();
                }
            }
        }
    }
}
