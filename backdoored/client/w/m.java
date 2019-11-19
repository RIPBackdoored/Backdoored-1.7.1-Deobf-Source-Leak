package r.k.w;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import r.k.n.i;
import r.k.p.c.h;
import r.k.p.c.y;
import r.k.u;
import r.k.d.o.j;
import r.k.d.m.g;
import r.k.d.m.o;
import org.json.JSONObject;
import java.io.File;

public class m
{
    public static final boolean sv;
    public static final int sj;
    public static final boolean se;
    
    public m() {
        super();
    }
    
    static void d(final File file) throws IOException {
        final JSONObject jsonObject = new JSONObject();
        final JSONObject jsonObject2 = new JSONObject();
        final Iterator<g> iterator = o.lc().iterator();
        if (iterator.hasNext()) {
            final g g = iterator.next();
            final JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("Enabled", g.ln());
            final ArrayList<r.k.d.o.m> s = j.s(g);
            if (s != null) {
                final Iterator<r.k.d.o.m<Enum>> iterator2 = s.iterator();
                if (iterator2.hasNext()) {
                    final r.k.d.o.m<Enum> m = iterator2.next();
                    if (m.yv() instanceof Enum) {
                        jsonObject3.put(m.o(), m.yv().name());
                    }
                    jsonObject3.put(m.o(), m.yv());
                }
            }
            jsonObject2.put(g.cm, jsonObject3);
        }
        jsonObject.put("Hacks", jsonObject2);
        final JSONObject jsonObject4 = new JSONObject();
        final Iterator<y> iterator3 = u.lsp.llz.iterator();
        if (iterator3.hasNext()) {
            final y y = iterator3.next();
            final JSONObject jsonObject5 = new JSONObject();
            jsonObject5.put("Visible", y.u());
            if (y instanceof h) {
                jsonObject5.put("x", y.z().slo);
                jsonObject5.put("y", y.z().slt);
            }
            jsonObject4.put(y.o(), jsonObject5);
        }
        jsonObject.put("Hud", jsonObject4);
        final JSONObject jsonObject6 = new JSONObject();
        jsonObject6.put("Prefix", i.re);
        jsonObject.put("Commands", jsonObject6);
        final PrintWriter printWriter = new PrintWriter(new FileWriter(file));
        printWriter.print(jsonObject.toString(4));
        printWriter.close();
    }
}
