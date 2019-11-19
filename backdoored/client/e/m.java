package r.k.e;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.ChatAllowedCharacters;
import java.util.function.Consumer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.init.Blocks;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import java.io.IOException;
import \u0000r.\u0000k.\u0000e.\u0000m;
import net.minecraft.client.gui.GuiButton;
import com.google.common.base.Predicate;
import java.util.LinkedList;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.gui.GuiTextField;
import java.util.List;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.client.gui.GuiScreen;

public class m extends GuiScreen
{
    public final TileEntitySign tileEntitySign;
    private static int p;
    private List<GuiTextField> r;
    private String[] f;
    public static final boolean b;
    public static final int w;
    public static final boolean g;
    public final TileEntitySign tileEntitySign;
    private static int p;
    private List<GuiTextField> r;
    private String[] f;
    public static final boolean b;
    public static final int w;
    public static final boolean g;
    
    public m(final TileEntitySign tileEntitySign) {
        super();
        this.tileEntitySign = tileEntitySign;
    }
    
    public void func_73866_w_() {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        this.r = new LinkedList<GuiTextField>();
        this.f = new String[4];
        int n = 0;
        if (n < 4) {
            final GuiTextField guiTextField = new GuiTextField(n, this.fontRenderer, this.width / 2 + 4, 75 + n * 24, 120, 20);
            guiTextField.setValidator((Predicate)this::d);
            guiTextField.setMaxStringLength(100);
            final String s;
            guiTextField.setText(this.f[n] = s);
            this.r.add(guiTextField);
            ++n;
        }
        this.r.get(m.p).setFocused(true);
        this.addButton(new GuiButton(4, this.width / 2 + 5, this.height / 4 + 120, 120, 20, "Done"));
        this.addButton(new GuiButton(5, this.width / 2 - 125, this.height / 4 + 120, 120, 20, "Cancel"));
        this.addButton(new GuiButton(6, this.width / 2 - 41, 147, 40, 20, "Shift"));
        this.addButton(new GuiButton(7, this.width / 2 - 41, 123, 40, 20, "Clear"));
        this.tileEntitySign.setEditable(false);
    }
    
    protected void func_73864_a(final int n, final int n2, final int n3) throws IOException {
        super.mouseClicked(n, n2, n3);
        this.r.forEach(\u0000m::d);
        this.y();
        final int n4;
        if (m.p == n4 && !this.r.get(m.p).isFocused()) {
            this.r.get(m.p).setFocused(true);
        }
    }
    
    protected void func_73869_a(final char p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: nop            
        //     7: iload_2        
        //     8: lookupswitch {
        //                1: 68
        //               15: 69
        //               28: 91
        //              156: 91
        //              200: 85
        //              208: 91
        //          default: 96
        //        }
        //    68: nop            
        //    69: invokestatic    r/k/e/m.isShiftKeyDown:()Z
        //    72: ifeq            77
        //    75: iconst_m1      
        //    76: nop            
        //    77: iconst_1       
        //    78: istore_3       
        //    79: aload_0        
        //    80: iload_3        
        //    81: invokevirtual   r/k/e/m.d:(I)V
        //    84: nop            
        //    85: aload_0        
        //    86: iconst_m1      
        //    87: invokevirtual   r/k/e/m.d:(I)V
        //    90: nop            
        //    91: aload_0        
        //    92: iconst_1       
        //    93: invokevirtual   r/k/e/m.d:(I)V
        //    96: aload_0        
        //    97: getfield        r/k/e/m.r:Ljava/util/List;
        //   100: iload_1        
        //   101: iload_2        
        //   102: invokedynamic   BootstrapMethod #2, accept:(CI)Ljava/util/function/Consumer;
        //   107: invokeinterface java/util/List.forEach:(Ljava/util/function/Consumer;)V
        //   112: aload_0        
        //   113: getfield        r/k/e/m.tileEntitySign:Lnet/minecraft/tileentity/TileEntitySign;
        //   116: getfield        net/minecraft/tileentity/TileEntitySign.signText:[Lnet/minecraft/util/text/ITextComponent;
        //   119: getstatic       r/k/e/m.p:I
        //   122: new             Lnet/minecraft/util/text/TextComponentString;
        //   125: dup            
        //   126: aload_0        
        //   127: getfield        r/k/e/m.r:Ljava/util/List;
        //   130: getstatic       r/k/e/m.p:I
        //   133: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   138: checkcast       Lnet/minecraft/client/gui/GuiTextField;
        //   141: invokevirtual   net/minecraft/client/gui/GuiTextField.getText:()Ljava/lang/String;
        //   144: invokespecial   net/minecraft/util/text/TextComponentString.<init>:(Ljava/lang/String;)V
        //   147: aastore        
        //   148: return         
        //    StackMapTable: 00 0A FF 00 04 00 07 07 00 24 01 01 00 00 00 01 00 00 00 FF 00 3E 00 07 07 00 24 01 01 00 01 01 01 00 00 00 07 40 01 06 05 04 33
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void func_146284_a(final GuiButton p0) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: aload_0        
        //     6: aload_1        
        //     7: invokespecial   net/minecraft/client/gui/GuiScreen.actionPerformed:(Lnet/minecraft/client/gui/GuiButton;)V
        //    10: aload_1        
        //    11: getfield        net/minecraft/client/gui/GuiButton.id:I
        //    14: tableswitch {
        //                8: 77
        //                9: 44
        //               10: 82
        //               11: 154
        //          default: 169
        //        }
        //    44: iconst_0       
        //    45: istore_2       
        //    46: iload_2        
        //    47: iconst_4       
        //    48: if_icmpge       77
        //    51: aload_0        
        //    52: getfield        r/k/e/m.tileEntitySign:Lnet/minecraft/tileentity/TileEntitySign;
        //    55: getfield        net/minecraft/tileentity/TileEntitySign.signText:[Lnet/minecraft/util/text/ITextComponent;
        //    58: iload_2        
        //    59: new             Lnet/minecraft/util/text/TextComponentString;
        //    62: dup            
        //    63: aload_0        
        //    64: getfield        r/k/e/m.f:[Ljava/lang/String;
        //    67: iload_2        
        //    68: aaload         
        //    69: invokespecial   net/minecraft/util/text/TextComponentString.<init>:(Ljava/lang/String;)V
        //    72: aastore        
        //    73: iinc            2, 1
        //    76: nop            
        //    77: aload_0        
        //    78: invokevirtual   r/k/e/m.x:()V
        //    81: nop            
        //    82: iconst_4       
        //    83: anewarray       Ljava/lang/String;
        //    86: astore_2       
        //    87: iconst_0       
        //    88: istore_3       
        //    89: iload_3        
        //    90: iconst_4       
        //    91: if_icmpge       137
        //    94: invokestatic    r/k/e/m.isShiftKeyDown:()Z
        //    97: ifeq            102
        //   100: iconst_1       
        //   101: nop            
        //   102: iconst_m1      
        //   103: istore          4
        //   105: aload_0        
        //   106: iload_3        
        //   107: iload           4
        //   109: iadd           
        //   110: invokevirtual   r/k/e/m.s:(I)I
        //   113: istore          5
        //   115: aload_2        
        //   116: iload_3        
        //   117: aload_0        
        //   118: getfield        r/k/e/m.tileEntitySign:Lnet/minecraft/tileentity/TileEntitySign;
        //   121: getfield        net/minecraft/tileentity/TileEntitySign.signText:[Lnet/minecraft/util/text/ITextComponent;
        //   124: iload           5
        //   126: aaload         
        //   127: invokeinterface net/minecraft/util/text/ITextComponent.getUnformattedText:()Ljava/lang/String;
        //   132: aastore        
        //   133: iinc            3, 1
        //   136: nop            
        //   137: aload_0        
        //   138: getfield        r/k/e/m.r:Ljava/util/List;
        //   141: aload_0        
        //   142: aload_2        
        //   143: invokedynamic   BootstrapMethod #3, accept:(Lr/k/e/m;[Ljava/lang/String;)Ljava/util/function/Consumer;
        //   148: invokeinterface java/util/List.forEach:(Ljava/util/function/Consumer;)V
        //   153: nop            
        //   154: aload_0        
        //   155: getfield        r/k/e/m.r:Ljava/util/List;
        //   158: aload_0        
        //   159: invokedynamic   BootstrapMethod #4, accept:(Lr/k/e/m;)Ljava/util/function/Consumer;
        //   164: invokeinterface java/util/List.forEach:(Ljava/util/function/Consumer;)V
        //   169: return         
        //    Exceptions:
        //  throws java.io.IOException
        //    StackMapTable: 00 0C FF 00 04 00 09 07 00 24 07 00 75 00 00 00 00 00 00 01 00 00 FF 00 00 00 09 07 00 24 07 00 75 00 00 00 00 01 01 01 00 00 26 FF 00 01 00 09 07 00 24 07 00 75 01 00 00 00 01 01 01 00 00 FF 00 1E 00 09 07 00 24 07 00 75 00 00 00 00 01 01 01 00 00 04 FF 00 06 00 09 07 00 24 07 00 75 07 00 CE 01 00 00 01 01 01 00 00 0C 40 01 21 FF 00 10 00 09 07 00 24 07 00 75 00 00 00 00 01 01 01 00 00 0E
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void func_73863_a(final int n, final int n2, final float n3) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, I18n.format("sign.edit", new Object[0]), this.width / 2, 40, 16777215);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.width / 2 - 63.0f, 0.0f, 50.0f);
        GlStateManager.scale(-93.75f, -93.75f, -93.75f);
        GlStateManager.rotate(180.0f, 0.0f, 1.0f, 0.0f);
        if (this.tileEntitySign.getBlockType() == Blocks.STANDING_SIGN) {
            GlStateManager.rotate(this.tileEntitySign.getBlockMetadata() * 360 / 16.0f, 0.0f, 1.0f, 0.0f);
            GlStateManager.translate(0.0f, -1.0625f, 0.0f);
        }
        final int blockMetadata = this.tileEntitySign.getBlockMetadata();
        float n4 = 0.0f;
        if (blockMetadata == 2) {
            n4 = 180.0f;
        }
        if (blockMetadata == 4) {
            n4 = 90.0f;
        }
        if (blockMetadata == 5) {
            n4 = -90.0f;
        }
        GlStateManager.rotate(n4, 0.0f, 1.0f, 0.0f);
        GlStateManager.translate(0.0f, -0.7625f, 0.0f);
        this.tileEntitySign.lineBeingEdited = -1;
        TileEntityRendererDispatcher.instance.render((TileEntity)this.tileEntitySign, -0.5, -0.75, -0.5, 0.0f);
        GlStateManager.popMatrix();
        this.r.forEach(GuiTextField::func_146194_f);
        super.drawScreen(n, n2, n3);
    }
    
    void y() {
        this.r.forEach(\u0000m::d);
    }
    
    void x() {
        this.mc.displayGuiScreen((GuiScreen)null);
    }
    
    public void func_146281_b() {
        // 
        // This method could not be decompiled.
        // 
        // Could not show original bytecode, likely due to the same error.
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public m(final TileEntitySign tileEntitySign) {
        super();
        this.tileEntitySign = tileEntitySign;
    }
    
    public void func_73866_w_() {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        this.r = new LinkedList<GuiTextField>();
        this.f = new String[4];
        int n = 0;
        if (n < 4) {
            final GuiTextField guiTextField = new GuiTextField(n, this.fontRenderer, this.width / 2 + 4, 75 + n * 24, 120, 20);
            guiTextField.setValidator((Predicate)this::d);
            guiTextField.setMaxStringLength(100);
            final String s;
            guiTextField.setText(this.f[n] = s);
            this.r.add(guiTextField);
            ++n;
        }
        this.r.get(m.p).setFocused(true);
        this.addButton(new GuiButton(4, this.width / 2 + 5, this.height / 4 + 120, 120, 20, "Done"));
        this.addButton(new GuiButton(5, this.width / 2 - 125, this.height / 4 + 120, 120, 20, "Cancel"));
        this.addButton(new GuiButton(6, this.width / 2 - 41, 147, 40, 20, "Shift"));
        this.addButton(new GuiButton(7, this.width / 2 - 41, 123, 40, 20, "Clear"));
        this.tileEntitySign.setEditable(false);
    }
    
    protected void func_73864_a(final int n, final int n2, final int n3) throws IOException {
        super.mouseClicked(n, n2, n3);
        this.r.forEach(\u0000m::d);
        this.y();
        final int n4;
        if (m.p == n4 && !this.r.get(m.p).isFocused()) {
            this.r.get(m.p).setFocused(true);
        }
    }
    
    protected void func_73869_a(final char p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: nop            
        //     7: iload_2        
        //     8: lookupswitch {
        //                1: 68
        //               15: 69
        //               28: 91
        //              156: 91
        //              200: 85
        //              208: 91
        //          default: 96
        //        }
        //    68: nop            
        //    69: invokestatic    r/k/e/m.isShiftKeyDown:()Z
        //    72: ifeq            77
        //    75: iconst_m1      
        //    76: nop            
        //    77: iconst_1       
        //    78: istore_3       
        //    79: aload_0        
        //    80: iload_3        
        //    81: invokevirtual   r/k/e/m.d:(I)V
        //    84: nop            
        //    85: aload_0        
        //    86: iconst_m1      
        //    87: invokevirtual   r/k/e/m.d:(I)V
        //    90: nop            
        //    91: aload_0        
        //    92: iconst_1       
        //    93: invokevirtual   r/k/e/m.d:(I)V
        //    96: aload_0        
        //    97: getfield        r/k/e/m.r:Ljava/util/List;
        //   100: iload_1        
        //   101: iload_2        
        //   102: invokedynamic   BootstrapMethod #2, accept:(CI)Ljava/util/function/Consumer;
        //   107: invokeinterface java/util/List.forEach:(Ljava/util/function/Consumer;)V
        //   112: aload_0        
        //   113: getfield        r/k/e/m.tileEntitySign:Lnet/minecraft/tileentity/TileEntitySign;
        //   116: getfield        net/minecraft/tileentity/TileEntitySign.signText:[Lnet/minecraft/util/text/ITextComponent;
        //   119: getstatic       r/k/e/m.p:I
        //   122: new             Lnet/minecraft/util/text/TextComponentString;
        //   125: dup            
        //   126: aload_0        
        //   127: getfield        r/k/e/m.r:Ljava/util/List;
        //   130: getstatic       r/k/e/m.p:I
        //   133: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   138: checkcast       Lnet/minecraft/client/gui/GuiTextField;
        //   141: invokevirtual   net/minecraft/client/gui/GuiTextField.getText:()Ljava/lang/String;
        //   144: invokespecial   net/minecraft/util/text/TextComponentString.<init>:(Ljava/lang/String;)V
        //   147: aastore        
        //   148: return         
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void func_146284_a(final GuiButton p0) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: aload_0        
        //     6: aload_1        
        //     7: invokespecial   net/minecraft/client/gui/GuiScreen.actionPerformed:(Lnet/minecraft/client/gui/GuiButton;)V
        //    10: aload_1        
        //    11: getfield        net/minecraft/client/gui/GuiButton.id:I
        //    14: tableswitch {
        //                8: 77
        //                9: 44
        //               10: 82
        //               11: 154
        //          default: 169
        //        }
        //    44: iconst_0       
        //    45: istore_2       
        //    46: iload_2        
        //    47: iconst_4       
        //    48: if_icmpge       77
        //    51: aload_0        
        //    52: getfield        r/k/e/m.tileEntitySign:Lnet/minecraft/tileentity/TileEntitySign;
        //    55: getfield        net/minecraft/tileentity/TileEntitySign.signText:[Lnet/minecraft/util/text/ITextComponent;
        //    58: iload_2        
        //    59: new             Lnet/minecraft/util/text/TextComponentString;
        //    62: dup            
        //    63: aload_0        
        //    64: getfield        r/k/e/m.f:[Ljava/lang/String;
        //    67: iload_2        
        //    68: aaload         
        //    69: invokespecial   net/minecraft/util/text/TextComponentString.<init>:(Ljava/lang/String;)V
        //    72: aastore        
        //    73: iinc            2, 1
        //    76: nop            
        //    77: aload_0        
        //    78: invokevirtual   r/k/e/m.x:()V
        //    81: nop            
        //    82: iconst_4       
        //    83: anewarray       Ljava/lang/String;
        //    86: astore_2       
        //    87: iconst_0       
        //    88: istore_3       
        //    89: iload_3        
        //    90: iconst_4       
        //    91: if_icmpge       137
        //    94: invokestatic    r/k/e/m.isShiftKeyDown:()Z
        //    97: ifeq            102
        //   100: iconst_1       
        //   101: nop            
        //   102: iconst_m1      
        //   103: istore          4
        //   105: aload_0        
        //   106: iload_3        
        //   107: iload           4
        //   109: iadd           
        //   110: invokevirtual   r/k/e/m.s:(I)I
        //   113: istore          5
        //   115: aload_2        
        //   116: iload_3        
        //   117: aload_0        
        //   118: getfield        r/k/e/m.tileEntitySign:Lnet/minecraft/tileentity/TileEntitySign;
        //   121: getfield        net/minecraft/tileentity/TileEntitySign.signText:[Lnet/minecraft/util/text/ITextComponent;
        //   124: iload           5
        //   126: aaload         
        //   127: invokeinterface net/minecraft/util/text/ITextComponent.getUnformattedText:()Ljava/lang/String;
        //   132: aastore        
        //   133: iinc            3, 1
        //   136: nop            
        //   137: aload_0        
        //   138: getfield        r/k/e/m.r:Ljava/util/List;
        //   141: aload_0        
        //   142: aload_2        
        //   143: invokedynamic   BootstrapMethod #3, accept:(Lr/k/e/m;[Ljava/lang/String;)Ljava/util/function/Consumer;
        //   148: invokeinterface java/util/List.forEach:(Ljava/util/function/Consumer;)V
        //   153: nop            
        //   154: aload_0        
        //   155: getfield        r/k/e/m.r:Ljava/util/List;
        //   158: aload_0        
        //   159: invokedynamic   BootstrapMethod #4, accept:(Lr/k/e/m;)Ljava/util/function/Consumer;
        //   164: invokeinterface java/util/List.forEach:(Ljava/util/function/Consumer;)V
        //   169: return         
        //    Exceptions:
        //  throws java.io.IOException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void func_73863_a(final int n, final int n2, final float n3) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, I18n.format("sign.edit", new Object[0]), this.width / 2, 40, 16777215);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.width / 2 - 63.0f, 0.0f, 50.0f);
        GlStateManager.scale(-93.75f, -93.75f, -93.75f);
        GlStateManager.rotate(180.0f, 0.0f, 1.0f, 0.0f);
        if (this.tileEntitySign.getBlockType() == Blocks.STANDING_SIGN) {
            GlStateManager.rotate(this.tileEntitySign.getBlockMetadata() * 360 / 16.0f, 0.0f, 1.0f, 0.0f);
            GlStateManager.translate(0.0f, -1.0625f, 0.0f);
        }
        final int blockMetadata = this.tileEntitySign.getBlockMetadata();
        float n4 = 0.0f;
        if (blockMetadata == 2) {
            n4 = 180.0f;
        }
        if (blockMetadata == 4) {
            n4 = 90.0f;
        }
        if (blockMetadata == 5) {
            n4 = -90.0f;
        }
        GlStateManager.rotate(n4, 0.0f, 1.0f, 0.0f);
        GlStateManager.translate(0.0f, -0.7625f, 0.0f);
        this.tileEntitySign.lineBeingEdited = -1;
        TileEntityRendererDispatcher.instance.render((TileEntity)this.tileEntitySign, -0.5, -0.75, -0.5, 0.0f);
        GlStateManager.popMatrix();
        this.r.forEach(GuiTextField::func_146194_f);
        super.drawScreen(n, n2, n3);
    }
    
    void y() {
        this.r.forEach(\u0000m::d);
    }
    
    void x() {
        this.mc.displayGuiScreen((GuiScreen)null);
    }
    
    public void func_146281_b() {
        Keyboard.enableRepeatEvents(false);
        if (this.mc.getConnection() != null) {}
        this.tileEntitySign.setEditable(true);
    }
    
    void d(final int n) {
        this.r.get(m.p).setFocused(false);
        m.p = this.s(m.p + n);
        this.r.get(m.p).setFocused(true);
    }
    
    int s(final int n) {
        if (n > 3) {
            return 0;
        }
        if (n < 0) {
            return 3;
        }
        return n;
    }
    
    boolean d(final String s) {
        if (this.fontRenderer.getStringWidth(s) > 90) {
            return false;
        }
        s.toCharArray();
        final Object o;
        final int length = o.length;
        int n = 0;
        if (n < length) {
            if (!ChatAllowedCharacters.isAllowedCharacter(o[n])) {
                return false;
            }
            ++n;
        }
        return true;
    }
    
    private static /* synthetic */ void d(final GuiTextField guiTextField) {
        if (guiTextField.isFocused()) {
            m.p = guiTextField.getId();
        }
    }
    
    private /* synthetic */ void s(final GuiTextField guiTextField) {
        final int id = guiTextField.getId();
        guiTextField.setText("");
        this.tileEntitySign.signText[id] = (ITextComponent)new TextComponentString("");
    }
    
    private /* synthetic */ void d(final String[] array, final GuiTextField guiTextField) {
        final int id = guiTextField.getId();
        guiTextField.setText(array[id]);
        this.tileEntitySign.signText[id] = (ITextComponent)new TextComponentString(array[id]);
    }
    
    private static /* synthetic */ void d(final char c, final int n, final GuiTextField guiTextField) {
        guiTextField.textboxKeyTyped(c, n);
    }
    
    private static /* synthetic */ void d(final int n, final int n2, final int n3, final GuiTextField guiTextField) {
        guiTextField.mouseClicked(n, n2, n3);
    }
    
    static {
        m.p = 0;
    }
}
