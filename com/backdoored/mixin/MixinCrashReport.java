package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.Inject;
import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.google.common.base.Throwables;
import \u0000r.\u0000k.\u0000s.\u0000n;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import java.awt.Frame;
import \u0000r.\u0000k.\u0000h;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.io.File;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import \u0000r.\u0000k.\u0000a;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import net.minecraft.crash.CrashReport;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ CrashReport.class })
public class MixinCrashReport
{
    @Final
    @Shadow
    private Throwable field_71511_b;
    
    public MixinCrashReport() {
        super();
    }
    
    @Redirect(method = { "getCompleteReport" }, at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;toString()Ljava/lang/String;"))
    public String interceptReport(final StringBuilder sb) {
        try {
            return \u0000a.d(sb);
        }
        catch (Throwable t) {
            return sb.toString();
        }
    }
    
    @Inject(method = { "saveToFile" }, at = { @At("RETURN") })
    private void showDialog(final File file, final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (\u0000h.sbb.func_71372_G()) {
            \u0000h.sbb.func_71352_k();
        }
        final Frame frame = new Frame();
        frame.setAlwaysOnTop(true);
        frame.setState(1);
        final JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        final String func_71502_e = ((CrashReport)this).func_71502_e();
        String s;
        try {
            s = \u0000n.d("http://paste.dimdev.org", "mccrash", func_71502_e);
        }
        catch (Exception ex) {
            s = ex.getMessage();
        }
        final JTextArea textArea = new JTextArea("Uploaded crash report: " + s + "\n" + Throwables.getStackTraceAsString(this.field_71511_b));
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea, 22, 32));
        StackTraceElement stackTraceElement;
        if (this.field_71511_b.getStackTrace().length > 0) {
            stackTraceElement = this.field_71511_b.getStackTrace()[0];
        }
        else {
            stackTraceElement = new StackTraceElement("", "", "", -1);
        }
        JOptionPane.showMessageDialog(frame, panel, "ERROR encountered at " + stackTraceElement.toString(), 0);
        frame.requestFocus();
    }
}
