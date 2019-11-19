package r.k.s;

import \u0000r.\u0000k.\u0000s.\u0000o;
import javax.net.ssl.HttpsURLConnection;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.net.MalformedURLException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import com.google.common.collect.HashBiMap;

public class o
{
    public static HashBiMap<String, String> fo;
    private static final Boolean[] ft;
    public static final boolean fn;
    public static final int fi;
    public static final boolean fp;
    
    public o() {
        super();
    }
    
    public static String n(final String s) {
        if (o.fo.containsKey((Object)s)) {
            System.out.println("Grabbing username from hash map");
            return (String)o.fo.get((Object)s);
        }
        System.out.println("Grabbing username from Mojang Web Api");
        return d(s, true);
    }
    
    public static String i(final String s) {
        if (o.fo.containsValue((Object)s)) {
            System.out.println("Grabbing UUID from hash map");
            return o.fo.inverse().get(s);
        }
        System.out.println("Grabbing UUID from Mojang Web Api");
        return d(s, false);
    }
    
    private static String d(final String s, final Boolean b) {
        if (b) {
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL("https://api.mojang.com/user/profiles/" + s.replace("-", "") + "/names").openStream()));
                String string = "Popbob";
                final String line = bufferedReader.readLine();
                System.out.println(line);
                bufferedReader.close();
                if (line != null) {
                    final JSONArray jsonArray = (JSONArray)new JSONParser().parse(line);
                    string = jsonArray.get(jsonArray.size() - 1).get("name").toString();
                }
                bufferedReader.close();
                o.fo.put((Object)s, (Object)string);
                return string;
            }
            catch (MalformedURLException ex) {
                System.out.println("MALIGNED URL, CARBOLEMONS IS DUMB IF YOU ARE READING THIS, BECAUSE, WHAT, IMPOSSIBLE... LITCHERALLLY...");
                return "";
            }
            catch (IOException ex2) {
                System.out.println("uh, something went horribly wrong if you are seeing this in your log.");
                return "";
            }
            catch (ParseException ex3) {
                System.out.println("JSON userdata was parsed wrong, shit.");
                return "";
            }
        }
        try {
            final BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new URL("https://api.mojang.com/users/profiles/minecraft/" + s).openStream()));
            String string2 = "00000000-0000-0000-0000-000000000000";
            final String line2 = bufferedReader2.readLine();
            bufferedReader2.close();
            if (line2 != null) {
                final StringBuilder sb = new StringBuilder(new String(((JSONObject)new JSONParser().parse(line2)).get("id").toString()));
                sb.insert(8, '-');
                sb.insert(13, '-');
                sb.insert(18, '-');
                sb.insert(23, '-');
                string2 = sb.toString();
            }
            o.fo.put((Object)string2, (Object)s);
            return string2;
        }
        catch (MalformedURLException ex4) {
            System.out.println("MALIGNED URL, CARBOLEMONS IS DUMB IF YOU ARE READING THIS, BECAUSE, WHAT, IMPOSSIBLE... LITCHERALLLY...");
            return "";
        }
        catch (IOException ex5) {
            System.out.println("uh, something went horribly wrong if you are seeing this in your log.");
            return "";
        }
        catch (ParseException ex6) {
            System.out.println("JSON userdata was parsed wrong, shit.");
            return "";
        }
    }
    
    private static boolean so() {
        return p("https://authserver.mojang.com/authenticate");
    }
    
    private static boolean st() {
        return p("https://sessionserver.mojang.com/");
    }
    
    public static boolean sn() {
        // monitorenter(ft = o.ft)
        try {
            // monitorexit(ft)
            return o.ft[0];
        }
        finally {
            // monitorexit(loadexception(java.lang.Throwable.class))
            throw;
        }
    }
    
    public static boolean si() {
        synchronized (o.ft) {
            return o.ft[1];
        }
    }
    
    private static boolean p(final String s) {
        try {
            final HttpsURLConnection httpsURLConnection = (HttpsURLConnection)new URL(s).openConnection();
            httpsURLConnection.connect();
            httpsURLConnection.disconnect();
            return true;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private static /* synthetic */ void sp() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: nop            
        //     7: getstatic       r/k/h.mc:Lnet/minecraft/client/Minecraft;
        //    10: getfield        net/minecraft/client/Minecraft.currentScreen:Lnet/minecraft/client/gui/GuiScreen;
        //    13: instanceof      Lr/k/e/k;
        //    16: ifeq            59
        //    19: invokestatic    r/k/s/o.so:()Z
        //    22: istore_0       
        //    23: invokestatic    r/k/s/o.st:()Z
        //    26: istore_1       
        //    27: getstatic       r/k/s/o.ft:[Ljava/lang/Boolean;
        //    30: dup            
        //    31: astore_2       
        //    32: monitorenter   
        //    33: getstatic       r/k/s/o.ft:[Ljava/lang/Boolean;
        //    36: iconst_0       
        //    37: iload_0        
        //    38: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    41: aastore        
        //    42: getstatic       r/k/s/o.ft:[Ljava/lang/Boolean;
        //    45: iconst_1       
        //    46: iload_1        
        //    47: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    50: aastore        
        //    51: aload_2        
        //    52: monitorexit    
        //    53: nop            
        //    54: astore_3       
        //    55: aload_2        
        //    56: monitorexit    
        //    57: aload_3        
        //    58: athrow         
        //    59: ldc2_w          7500
        //    62: invokestatic    java/lang/Thread.sleep:(J)V
        //    65: nop            
        //    66: astore_0       
        //    67: aload_0        
        //    68: invokevirtual   java/lang/Exception.printStackTrace:()V
        //    71: nop            
        //    StackMapTable: 00 07 FF 00 04 00 07 00 00 00 00 00 00 01 00 00 00 FF 00 01 00 07 00 00 00 00 01 01 01 00 00 FF 00 2B 00 07 01 01 07 00 C9 00 01 01 01 00 00 42 07 00 18 FF 00 04 00 07 00 00 00 00 01 01 01 00 00 46 07 00 DC
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  33     51     54     59     Any
        //  51     53     54     59     Any
        //  54     57     54     59     Any
        //  7      51     66     72     Ljava/lang/Exception;
        //  51     59     66     72     Ljava/lang/Exception;
        //  59     65     66     72     Ljava/lang/Exception;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        o.fo = (HashBiMap<String, String>)HashBiMap.create();
        ft = new Boolean[] { true, true };
        new Thread(\u0000o::sp, "Status checker").start();
    }
}
