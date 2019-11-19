package org.spongepowered.tools.obfuscation.struct;

import javax.annotation.processing.Messager;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;

public class InjectorRemap
{
    private final boolean remap;
    private Message message;
    private int remappedCount;
    
    public InjectorRemap(final boolean remap) {
        super();
        this.remap = remap;
    }
    
    public boolean shouldRemap() {
        return this.remap;
    }
    
    public void notifyRemapped() {
        ++this.remappedCount;
        this.clearMessage();
    }
    
    public void addMessage(final Diagnostic.Kind kind, final CharSequence charSequence, final Element element, final AnnotationHandle annotationHandle) {
        this.message = new Message(kind, charSequence, element, annotationHandle);
    }
    
    public void clearMessage() {
        this.message = null;
    }
    
    public void dispatchPendingMessages(final Messager messager) {
        if (this.remappedCount == 0 && this.message != null) {
            this.message.sendTo(messager);
        }
    }
}
