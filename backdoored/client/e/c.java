package r.k.e;

import java.io.IOException;
import com.mojang.authlib.exceptions.AuthenticationException;
import r.k.s.k;
import com.google.common.io.Files;
import com.google.common.base.Charsets;
import java.io.File;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiScreen;

public class c extends GuiScreen
{
    private GuiScreen guiScreen;
    private GuiTextField guiTextField;
    private GuiTextField guiTextField;
    private String lc;
    public static final boolean la;
    public static final int lm;
    public static final boolean lh;
    
    public c(final GuiScreen guiScreen) {
        super();
        this.lc = "";
        this.guiScreen = guiScreen;
    }
    
    public void func_73866_w_() {
        Keyboard.enableRepeatEvents(true);
        this.guiTextField = new GuiTextField(0, this.fontRenderer, this.width / 2 - 100, this.height / 4 + 60 + 0, 202, 20);
        this.guiTextField = new GuiTextField(2, this.fontRenderer, this.width / 2 - 100, this.height / 4 + 60 + 26, 202, 20);
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 60 + 52, "Login"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 60 + 76, "Cancel"));
        this.guiTextField.setMaxStringLength(500);
        this.guiTextField.setMaxStringLength(500);
        try {
            final String read = Files.asCharSource(new File("Backdoored/accounts.txt"), Charsets.UTF_8).read();
            if (!read.isEmpty()) {
                final String[] split = read.split(":");
                try {
                    if (!k.v(split[0].trim(), split[1].trim())) {
                        System.out.println("Could not log in");
                        this.lc = "Could not log in";
                    }
                }
                catch (AuthenticationException ex) {
                    ex.printStackTrace();
                    System.out.println("Could not log in: " + ex.toString());
                    this.lc = "Could not log in: " + ex.toString();
                }
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    public void func_146281_b() {
        Keyboard.enableRepeatEvents(false);
    }
    
    public void func_73876_c() {
        this.guiTextField.updateCursorCounter();
        this.guiTextField.updateCursorCounter();
    }
    
    public void func_73864_a(final int n, final int n2, final int n3) throws IOException {
        this.guiTextField.mouseClicked(n, n2, n3);
        this.guiTextField.mouseClicked(n, n2, n3);
        super.mouseClicked(n, n2, n3);
    }
    
    public void func_146284_a(final GuiButton guiButton) {
        if (guiButton.id == 1) {
            System.out.println("Attempting subguis, username: " + this.guiTextField.getText().trim());
            try {
                if (!k.v(this.guiTextField.getText().trim(), this.guiTextField.getText().trim())) {
                    System.out.println("Could not log in");
                    this.lc = "Could not log in";
                    return;
                }
            }
            catch (AuthenticationException ex) {
                ex.printStackTrace();
                System.out.println("Could not log in: " + ex.toString());
                this.lc = "Could not log in: " + ex.toString();
                return;
            }
        }
        if (guiButton.id == 2) {
            this.mc.displayGuiScreen(this.guiScreen);
        }
    }
    
    protected void func_73869_a(final char c, final int n) {
        this.guiTextField.textboxKeyTyped(c, n);
        this.guiTextField.textboxKeyTyped(c, n);
        if (c == '\t') {
            if (this.guiTextField.isFocused()) {
                this.guiTextField.setFocused(false);
                this.guiTextField.setFocused(true);
            }
            if (this.guiTextField.isFocused()) {
                this.guiTextField.setFocused(false);
                this.guiTextField.setFocused(false);
            }
        }
        if (c == '\r') {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }
    }
    
    public void func_73863_a(final int n, final int n2, final float n3) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "Backdoored Client: Login to Minecraft", this.width / 2, 2, 16777215);
        this.drawString(this.fontRenderer, "Email", this.width / 2 - 100 - 50, this.height / 4 + 60 + 0 + 6, 16777215);
        this.drawString(this.fontRenderer, "Password", this.width / 2 - 100 - 50, this.height / 4 + 60 + 26 + 6, 16777215);
        this.drawCenteredString(this.fontRenderer, this.lc, this.width / 2, this.height / 4 + 60 + 100, 16711680);
        try {
            this.guiTextField.drawTextBox();
            this.guiTextField.drawTextBox();
            throw null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            super.drawScreen(n, n2, n3);
        }
    }
}
