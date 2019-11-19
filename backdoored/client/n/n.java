package r.k.n;

import org.json.JSONObject;
import org.apache.commons.io.FileUtils;
import java.nio.charset.Charset;
import java.io.File;
import r.k.b.c.h;
import r.k.b.i.p;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;

public class n extends c
{
    public static final boolean vn;
    public static final int vi;
    public static final boolean vp;
    
    public n() {
        super(new String[] { "import", "importfriends" });
    }
    
    @Override
    public boolean d(final String[] array) {
        if (array.length == 1) {
            try {
                if (array[0].equalsIgnoreCase("impact")) {
                    final BufferedReader bufferedReader = new BufferedReader(new FileReader("Impact/friends.cfg"));
                    final String line;
                    if ((line = bufferedReader.readLine()) != null) {
                        final String s = line.split(":")[0];
                        if (!p.si(s)) {
                            p.st(s);
                            h.o("Added '" + s + "' to your friends!", "green");
                        }
                        h.o("'" + s + "' was already a friend", "red");
                    }
                    bufferedReader.close();
                    System.out.println("Successfully imported friends");
                }
                if (array[0].equalsIgnoreCase("wwe")) {
                    final BufferedReader bufferedReader2 = new BufferedReader(new FileReader("WWE/friends.txt"));
                    final String line2;
                    if ((line2 = bufferedReader2.readLine()) != null) {
                        final String s2 = line2.split(" ")[0];
                        if (!p.si(s2)) {
                            p.st(s2);
                            h.o("Added '" + s2 + "' to your friends!", "green");
                        }
                        h.o("'" + s2 + "' was already a friend", "red");
                    }
                    bufferedReader2.close();
                    System.out.println("Successfully imported friends");
                }
                if (array[0].equalsIgnoreCase("future")) {
                    final Object[] array2 = new JSONObject(FileUtils.readFileToString(new File(System.getProperty("user.home") + "/Future/friends.json"), Charset.defaultCharset())).getJSONObject("friend-label").keySet().toArray();
                    final int length = array2.length;
                    int n = 0;
                    if (n < length) {
                        final String string = array2[n].toString();
                        if (!p.si(string)) {
                            p.st(string);
                            h.o("Added '" + string + "' to your friends!", "green");
                        }
                        h.o("'" + string + "' was already a friend", "red");
                        ++n;
                    }
                    System.out.println("Successfully imported friends");
                }
            }
            catch (Exception ex) {
                System.out.println("Could not import to friends.txt: " + ex.toString());
                ex.printStackTrace();
                System.out.println(p.kb());
            }
        }
        return false;
    }
    
    @Override
    public String k() {
        return "-import <Impact/WWE only ones supported now>";
    }
}
