package r.k;

import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;
import java.io.FileWriter;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Frame;
import r.k.b.r;
import com.google.common.io.Files;
import com.google.common.base.Charsets;
import java.io.File;
import net.minecraftforge.fml.common.FMLLog;

public class a
{
    public static boolean llp;
    public static final boolean llr;
    public static final int llf;
    public static final boolean llb;
    
    public a() {
        super();
    }
    
    public static boolean kp() {
        FMLLog.log.info("Your hwid: " + p());
        try {
            FMLLog.log.info("Reading license from file");
            final String read = Files.asCharSource(new File("Backdoored/license.txt"), Charsets.UTF_8).read();
            FMLLog.log.info("License Retrieved from file: " + read);
            if (read != null && !read.trim().isEmpty() && l(read.trim())) {
                u.lsn = read.trim();
                return true;
            }
        }
        catch (Exception ex) {
            FMLLog.log.info("Error while reading license from file " + ex.getMessage());
            ex.printStackTrace();
            if (u.lsn == null || u.lsn.trim().isEmpty() || !l(u.lsn.trim())) {
                r.r(p());
            }
            if (u.lsn == null || u.lsn.trim().isEmpty() || !l(u.lsn.trim())) {
                FMLLog.log.info("Hwid is '" + p() + "'");
                final Frame frame = new Frame();
                frame.setAlwaysOnTop(true);
                frame.setState(1);
                final String s = (String)JOptionPane.showInputDialog(frame, "HWID is " + p() + " (copied to clipboard)", "Please enter license", 3, UIManager.getIcon("OptionPane.warningIcon"), null, null);
                if (s == null) {
                    return false;
                }
                final String trim = s.trim();
                if (l(trim)) {
                    FMLLog.log.info("Provided valid license: " + trim);
                    so(u.lsn = trim);
                    FMLLog.log.info("Saved license " + u.lsn);
                    return true;
                }
                FMLLog.log.info("User inputted incorrect license: " + u.lsn);
            }
            return false;
        }
    }
    
    public static void so(final String s) {
        try {
            final File file = new File("Backdoored/license.txt");
            final FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(s);
            fileWriter.close();
        }
        catch (Exception ex) {
            final File file;
            ((Throwable)file).printStackTrace();
        }
    }
    
    public static String d(final StringBuilder sb) {
        System.out.println("Requested crash report");
        return sb.toString();
    }
    
    private static String p() {
        return String.valueOf((System.getenv("os") + System.getProperty("os.name") + System.getProperty("os.arch") + System.getProperty("os.version") + System.getProperty("user.language") + System.getenv("SystemRoot") + System.getenv("HOMEDRIVE") + System.getenv("PROCESSOR_LEVEL") + System.getenv("PROCESSOR_REVISION") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("PROCESSOR_ARCHITEW6432") + System.getenv("NUMBER_OF_PROCESSORS")).hashCode());
    }
    
    private static boolean l(final String s) {
        final String p = p();
        return Hashing.sha512().hashString((CharSequence)(Hashing.sha1().hashString((CharSequence)p, StandardCharsets.UTF_8).toString() + p + "dontcrack"), StandardCharsets.UTF_8).toString().equalsIgnoreCase(s);
    }
    
    static {
        a.llp = false;
    }
}
