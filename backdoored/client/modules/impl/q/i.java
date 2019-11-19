package r.k.d.m.q;

import org.apache.logging.log4j.LogManager;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.script.ScriptException;
import org.apache.logging.log4j.Logger;

public class i
{
    static final String mv;
    u mj;
    static final Logger me;
    public static final boolean mo;
    public static final int mt;
    public static final boolean mn;
    
    i() throws Exception {
        this.mj = new u().sy(g("Backdoored/chatbot.js")).d(i.me);
    }
    
    String k(final String s, final String s2) throws ScriptException, NoSuchMethodException, IllegalStateException {
        return (String)this.mj.s("onChatRecieved", s, s2);
    }
    
    public static String g(final String s) throws IOException {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(s));
            final String line;
            final FileNotFoundException ex;
            if ((line = bufferedReader.readLine()) != null) {
                ((StringBuilder)ex).append(line);
            }
            bufferedReader.close();
            i.me.info("Successfully read chatbot script");
            return ((StringBuilder)ex).toString();
        }
        catch (FileNotFoundException ex) {
            final File file = new File(s);
            try {
                file.createNewFile();
            }
            catch (Exception ex2) {
                throw new IllegalStateException("Could not find chatbot script file");
            }
        }
    }
    
    static {
        mv = "Backdoored/chatbot.js";
        me = LogManager.getLogger("BackdooredChatBot");
    }
}
