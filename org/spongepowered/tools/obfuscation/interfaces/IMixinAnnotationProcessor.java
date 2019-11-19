package org.spongepowered.tools.obfuscation.interfaces;

import org.spongepowered.asm.util.ITokenProvider;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Messager;

public interface IMixinAnnotationProcessor extends Messager, IOptionProvider
{
    CompilerEnvironment getCompilerEnvironment();
    
    ProcessingEnvironment getProcessingEnvironment();
    
    IObfuscationManager getObfuscationManager();
    
    ITokenProvider getTokenProvider();
    
    ITypeHandleProvider getTypeProvider();
    
    IJavadocProvider getJavadocProvider();
}
