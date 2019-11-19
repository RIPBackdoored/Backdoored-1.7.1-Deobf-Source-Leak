package r.k.e;

import net.minecraft.client.gui.GuiScreen;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import javax.annotation.Nullable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.client.gui.GuiGameOver;

public class o extends GuiGameOver
{
    public static final boolean jk;
    public static final int jq;
    public static final boolean jv;
    public static final boolean jk;
    public static final int jq;
    public static final boolean jv;
    
    public o(@Nullable final ITextComponent textComponent) {
        super(textComponent);
    }
    
    public void func_73866_w_() {
        super.initGui();
        this.buttonList.add(new GuiButton(420, this.width / 2 - 100, this.height / 4 + 120, "Hide Death Screen"));
    }
    
    protected void func_146284_a(final GuiButton p0) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Could not show original bytecode, likely due to the same error.
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public o(@Nullable final ITextComponent textComponent) {
        super(textComponent);
    }
    
    public void func_73866_w_() {
        super.initGui();
        this.buttonList.add(new GuiButton(420, this.width / 2 - 100, this.height / 4 + 120, "Hide Death Screen"));
    }
    
    protected void func_146284_a(final GuiButton guiButton) throws IOException {
        if (guiButton.id == 420) {
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.player.isDead = false;
        }
        super.actionPerformed(guiButton);
    }
    
    public void func_73876_c() {
        if (this.mc.player != null && !this.mc.player.isDead && this.mc.player.getHealth() > 0.0f) {
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
        }
        super.updateScreen();
    }
}
