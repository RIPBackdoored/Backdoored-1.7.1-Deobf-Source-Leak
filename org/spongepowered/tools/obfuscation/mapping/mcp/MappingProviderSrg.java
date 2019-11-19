package org.spongepowered.tools.obfuscation.mapping.mcp;

import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import com.google.common.io.Files;
import java.io.IOException;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.obfuscation.mapping.mcp.MappingFieldSrg;
import com.google.common.base.Strings;
import com.google.common.collect.BiMap;
import com.google.common.io.LineProcessor;
import java.nio.charset.Charset;
import java.io.File;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import org.spongepowered.tools.obfuscation.mapping.common.MappingProvider;

public class MappingProviderSrg extends MappingProvider
{
    public MappingProviderSrg(final Messager messager, final Filer filer) {
        super(messager, filer);
    }
    
    @Override
    public void read(final File file) throws IOException {
        Files.readLines(file, Charset.defaultCharset(), (LineProcessor)new LineProcessor<String>() {
            final /* synthetic */ BiMap val$packageMap = MappingProviderSrg.this.packageMap;
            final /* synthetic */ BiMap val$classMap = MappingProviderSrg.this.classMap;
            final /* synthetic */ BiMap val$fieldMap = MappingProviderSrg.this.fieldMap;
            final /* synthetic */ BiMap val$methodMap = MappingProviderSrg.this.methodMap;
            final /* synthetic */ File val$input;
            final /* synthetic */ MappingProviderSrg this$0;
            
            MappingProviderSrg$1() {
                this.this$0 = this$0;
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
                        throw new MixinException("Invalid SRG file: " + file);
                    }
                    this.val$methodMap.forcePut((Object)new MappingMethod(split[0], split[1]), (Object)new MappingMethod(split[2], split[3]));
                }
                return true;
            }
            
            public /* bridge */ Object getResult() {
                return this.getResult();
            }
        });
    }
    
    @Override
    public MappingField getFieldMapping(MappingField mappingField) {
        if (mappingField.getDesc() != null) {
            mappingField = new MappingFieldSrg(mappingField);
        }
        return this.fieldMap.get(mappingField);
    }
}
