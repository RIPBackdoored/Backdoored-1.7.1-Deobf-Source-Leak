package org.spongepowered.asm.mixin.transformer.debug;

import java.io.IOException;
import org.jetbrains.java.decompiler.util.InterpreterUtil;
import java.io.File;
import org.jetbrains.java.decompiler.main.extern.IBytecodeProvider;

class RuntimeDecompiler$1 implements IBytecodeProvider {
    private byte[] byteCode;
    final /* synthetic */ RuntimeDecompiler this$0;
    
    RuntimeDecompiler$1(final RuntimeDecompiler this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public byte[] getBytecode(final String s, final String s2) throws IOException {
        if (this.byteCode == null) {
            this.byteCode = InterpreterUtil.getBytes(new File(s));
        }
        return this.byteCode;
    }
}