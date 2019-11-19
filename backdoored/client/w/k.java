package r.k.w;

import java.util.ArrayList;
import net.minecraftforge.fml.common.FMLLog;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Iterator;
import r.k.p.c.y;
import r.k.u;
import r.k.n.i;
import r.k.d.o.m;
import r.k.d.o.j;
import r.k.d.m.g;
import r.k.d.m.o;
import org.apache.commons.io.FileUtils;
import java.nio.charset.Charset;
import org.json.JSONObject;
import java.io.File;

@Deprecated
@Deprecated
public class k
{
    private static final File uv;
    private static JSONObject uj;
    private static JSONObject ue;
    private static JSONObject uo;
    private static JSONObject ut;
    private static JSONObject un;
    public static final boolean ui;
    public static final int up;
    public static final boolean ur;
    private static final File uv;
    private static JSONObject uj;
    private static JSONObject ue;
    private static JSONObject uo;
    private static JSONObject ut;
    private static JSONObject un;
    public static final boolean ui;
    public static final int up;
    public static final boolean ur;
    
    public k() {
        super();
    }
    
    public static void sz() {
        s(k.uv);
    }
    
    public static void s(final File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            String s = FileUtils.readFileToString(file, Charset.defaultCharset());
            if (s.trim().isEmpty() && file.renameTo(file)) {
                sc();
                s = FileUtils.readFileToString(file, Charset.defaultCharset());
            }
            k.uj = new JSONObject(s);
            k.ue = k.uj.getJSONObject("Hacks");
            k.uo = k.uj.getJSONObject("Category");
            k.ut = k.uj.getJSONObject("Commands");
            k.un = k.uj.getJSONObject("Hud");
            final Iterator<g> iterator = o.lc().iterator();
            if (iterator.hasNext()) {
                final g g = iterator.next();
                if (d(g, "Enabled") != null) {
                    try {
                        g.s((boolean)d(g, "Enabled"));
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                final Iterator<m> iterator2 = (Iterator<m>)j.s(g).iterator();
                if (iterator2.hasNext()) {
                    final m<Object> m = iterator2.next();
                    try {
                        if (d(g, m.o()) != null) {
                            final Object d = d(g, m.o());
                            if (d != null && m.sy() != null) {
                                final Object cast = m.sy().cast(d);
                                if (cast != null) {
                                    m.d(cast);
                                }
                            }
                        }
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                }
            }
            final Iterator<r.k.c.c.m> iterator3 = r.k.c.c.m.h().iterator();
            if (iterator3.hasNext()) {
                final r.k.c.c.m i = iterator3.next();
                if (d(i, "x") != null) {
                    i.en = (int)d(i, "x");
                }
                if (d(i, "y") != null) {
                    i.ei = (int)d(i, "y");
                }
                if (d(i, "open") != null) {
                    i.et = (boolean)d(i, "open");
                }
            }
            if (f("prefix") != null) {
                i.re = (String)f("prefix");
            }
            final Iterator<y> iterator4 = u.lsp.llz.iterator();
            if (iterator4.hasNext()) {
                final y y = iterator4.next();
                final Object x = x(y.o(), "Visible");
                if (x instanceof Boolean) {
                    y.d((boolean)x);
                }
                final Object x2 = x(y.o(), "x");
                if (x2 instanceof Integer) {
                    y.z().slo = (int)x2;
                }
                final Object x3 = x(y.o(), "y");
                if (x3 instanceof Integer) {
                    y.z().slt = (int)x3;
                }
            }
        }
        catch (Exception ex3) {
            System.out.println("ERROR READING BACKDOORED CONFIG FILE!!!");
            ex3.printStackTrace();
        }
    }
    
    private static Object d(final g g, final String s) {
        return l(g.cm, s);
    }
    
    private static Object l(final String s, final String s2) {
        try {
            return k.ue.getJSONObject(s).get(s2);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Object d(final r.k.c.c.m m, final String s) {
        return y(m.ef, s);
    }
    
    private static Object y(final String s, final String s2) {
        try {
            return k.uo.getJSONObject(s).get(s2);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Object f(final String s) {
        try {
            return k.ut.get(s);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Object x(final String s, final String s2) {
        try {
            return k.un.getJSONObject(s).get(s2);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static void sc() {
        d(k.uv);
    }
    
    public static void d(final File file) {
        k.uj.put("Hacks", sa());
        k.uj.put("Category", sm());
        k.uj.put("Commands", sh());
        k.uj.put("Hud", ld());
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.renameTo(file)) {
                final PrintWriter printWriter = new PrintWriter(new FileWriter(file));
                printWriter.print(k.uj.toString(4));
                printWriter.close();
                FMLLog.log.info(k.uj.toString());
            }
            System.out.println("Failed to save, file already in use");
        }
        catch (Exception ex) {
            System.out.println("ERROR SAVING BACKDOORED CONFIG FILE!!!");
            ex.printStackTrace();
        }
    }
    
    private static JSONObject sa() {
        final Iterator<g> iterator = o.lc().iterator();
        if (iterator.hasNext()) {
            final g g = iterator.next();
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("Enabled", g.lo());
            final ArrayList<m> s = j.s(g);
            if (s != null) {
                final Iterator<m<Object>> iterator2 = s.iterator();
                if (iterator2.hasNext()) {
                    final m<Object> m = iterator2.next();
                    jsonObject.put(m.o(), m.yv());
                }
            }
            k.ue.put(g.cm, jsonObject);
        }
        return k.ue;
    }
    
    private static JSONObject sm() {
        r.k.c.c.m.h().iterator();
        final JSONObject jsonObject = new JSONObject();
        final r.k.c.c.m m;
        jsonObject.put("x", m.en);
        jsonObject.put("y", m.ei);
        jsonObject.put("open", m.et);
        k.uo.put(m.ef, jsonObject);
        return k.uo;
    }
    
    private static JSONObject sh() {
        return k.ut;
    }
    
    private static JSONObject ld() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: aconst_null    
        //     5: areturn        
        //     6: nop            
        //     7: nop            
        //    StackMapTable: 00 03 FF 00 04 00 06 00 00 00 00 00 01 00 00 01 FF 00 01 00 06 07 00 70 00 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public k() {
        super();
    }
    
    public static void sz() {
        s(k.uv);
    }
    
    public static void s(final File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            String s = FileUtils.readFileToString(file, Charset.defaultCharset());
            if (s.trim().isEmpty() && file.renameTo(file)) {
                sc();
                s = FileUtils.readFileToString(file, Charset.defaultCharset());
            }
            k.uj = new JSONObject(s);
            k.ue = k.uj.getJSONObject("Hacks");
            k.uo = k.uj.getJSONObject("Category");
            k.ut = k.uj.getJSONObject("Commands");
            k.un = k.uj.getJSONObject("Hud");
            final Iterator<g> iterator = o.lc().iterator();
            if (iterator.hasNext()) {
                final g g = iterator.next();
                if (d(g, "Enabled") != null) {
                    try {
                        g.s((boolean)d(g, "Enabled"));
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                final Iterator<m> iterator2 = (Iterator<m>)j.s(g).iterator();
                if (iterator2.hasNext()) {
                    final m<Object> m = iterator2.next();
                    try {
                        if (d(g, m.o()) != null) {
                            final Object d = d(g, m.o());
                            if (d != null && m.sy() != null) {
                                final Object cast = m.sy().cast(d);
                                if (cast != null) {
                                    m.d(cast);
                                }
                            }
                        }
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                }
            }
            final Iterator<r.k.c.c.m> iterator3 = r.k.c.c.m.h().iterator();
            if (iterator3.hasNext()) {
                final r.k.c.c.m i = iterator3.next();
                if (d(i, "x") != null) {
                    i.en = (int)d(i, "x");
                }
                if (d(i, "y") != null) {
                    i.ei = (int)d(i, "y");
                }
                if (d(i, "open") != null) {
                    i.et = (boolean)d(i, "open");
                }
            }
            if (f("prefix") != null) {
                i.re = (String)f("prefix");
            }
            final Iterator<y> iterator4 = u.lsp.llz.iterator();
            if (iterator4.hasNext()) {
                final y y = iterator4.next();
                final Object x = x(y.o(), "Visible");
                if (x instanceof Boolean) {
                    y.d((boolean)x);
                }
                final Object x2 = x(y.o(), "x");
                if (x2 instanceof Integer) {
                    y.z().slo = (int)x2;
                }
                final Object x3 = x(y.o(), "y");
                if (x3 instanceof Integer) {
                    y.z().slt = (int)x3;
                }
            }
        }
        catch (Exception ex3) {
            System.out.println("ERROR READING BACKDOORED CONFIG FILE!!!");
            ex3.printStackTrace();
        }
    }
    
    private static Object d(final g g, final String s) {
        return l(g.cm, s);
    }
    
    private static Object l(final String s, final String s2) {
        try {
            return k.ue.getJSONObject(s).get(s2);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Object d(final r.k.c.c.m m, final String s) {
        return y(m.ef, s);
    }
    
    private static Object y(final String s, final String s2) {
        try {
            return k.uo.getJSONObject(s).get(s2);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Object f(final String s) {
        try {
            return k.ut.get(s);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Object x(final String s, final String s2) {
        try {
            return k.un.getJSONObject(s).get(s2);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static void sc() {
        d(k.uv);
    }
    
    public static void d(final File file) {
        k.uj.put("Hacks", sa());
        k.uj.put("Category", sm());
        k.uj.put("Commands", sh());
        k.uj.put("Hud", ld());
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.renameTo(file)) {
                final PrintWriter printWriter = new PrintWriter(new FileWriter(file));
                printWriter.print(k.uj.toString(4));
                printWriter.close();
                FMLLog.log.info(k.uj.toString());
            }
            System.out.println("Failed to save, file already in use");
        }
        catch (Exception ex) {
            System.out.println("ERROR SAVING BACKDOORED CONFIG FILE!!!");
            ex.printStackTrace();
        }
    }
    
    private static JSONObject sa() {
        final Iterator<g> iterator = o.lc().iterator();
        if (iterator.hasNext()) {
            final g g = iterator.next();
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("Enabled", g.lo());
            final ArrayList<m> s = j.s(g);
            if (s != null) {
                final Iterator<m<Object>> iterator2 = s.iterator();
                if (iterator2.hasNext()) {
                    final m<Object> m = iterator2.next();
                    jsonObject.put(m.o(), m.yv());
                }
            }
            k.ue.put(g.cm, jsonObject);
        }
        return k.ue;
    }
    
    private static JSONObject sm() {
        r.k.c.c.m.h().iterator();
        final JSONObject jsonObject = new JSONObject();
        final r.k.c.c.m m;
        jsonObject.put("x", m.en);
        jsonObject.put("y", m.ei);
        jsonObject.put("open", m.et);
        k.uo.put(m.ef, jsonObject);
        return k.uo;
    }
    
    private static JSONObject sh() {
        return k.ut;
    }
    
    private static JSONObject ld() {
        final Iterator<y> iterator;
        if (iterator.hasNext()) {
            final y y = iterator.next();
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("Visible", (Object)y.u());
            jsonObject.put("x", (Object)y.z().slo);
            jsonObject.put("y", (Object)y.z().slt);
            k.un.put(y.o(), jsonObject);
        }
        return k.un;
    }
    
    static {
        uv = new File("Backdoored/config.json");
        k.uj = new JSONObject();
        k.ue = new JSONObject();
        k.uo = new JSONObject();
        k.ut = new JSONObject();
        k.un = new JSONObject();
    }
}
