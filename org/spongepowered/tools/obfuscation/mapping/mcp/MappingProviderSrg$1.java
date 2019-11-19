package org.spongepowered.tools.obfuscation.mapping.mcp;

import java.io.IOException;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.obfuscation.mapping.mcp.MappingFieldSrg;
import com.google.common.base.Strings;
import java.io.File;
import com.google.common.collect.BiMap;
import com.google.common.io.LineProcessor;

class MappingProviderSrg$1 implements LineProcessor<String> {
    final /* synthetic */ BiMap val$packageMap;
    final /* synthetic */ BiMap val$classMap;
    final /* synthetic */ BiMap val$fieldMap;
    final /* synthetic */ BiMap val$methodMap;
    final /* synthetic */ File val$input;
    final /* synthetic */ MappingProviderSrg this$0;
    
    MappingProviderSrg$1(final MappingProviderSrg this$0, final BiMap val$packageMap, final BiMap val$classMap, final BiMap val$fieldMap, final BiMap val$methodMap, final File val$input) {
        this.this$0 = this$0;
        this.val$packageMap = val$packageMap;
        this.val$classMap = val$classMap;
        this.val$fieldMap = val$fieldMap;
        this.val$methodMap = val$methodMap;
        this.val$input = val$input;
        super();
    }
    
    public String getResult() {
        return null;
    }
    
    public boolean processLine(final String string) throws IOException {
        if (Strings.isNullOrEmpty(string) || string.startsWith("#")) {
            return true;
        }
        final String substring = string.substring(0, 2);
        final String[] split = string.substring(4).split(" ");
        if (substring.equals("PK")) {
            this.val$packageMap.forcePut((Object)split[0], (Object)split[1]);
        }
        else if (substring.equals("CL")) {
            this.val$classMap.forcePut((Object)split[0], (Object)split[1]);
        }
        else if (substring.equals("FD")) {
            this.val$fieldMap.forcePut((Object)new MappingFieldSrg(split[0]).copy(), (Object)new MappingFieldSrg(split[1]).copy());
        }
        else {
            if (!substring.equals("MD")) {
                throw new MixinException("Invalid SRG file: " + this.val$input);
            }
            this.val$methodMap.forcePut((Object)new MappingMethod(split[0], split[1]), (Object)new MappingMethod(split[2], split[3]));
        }
        return true;
    }
    
    public /* bridge */ Object getResult() {
        return this.getResult();
    }
}