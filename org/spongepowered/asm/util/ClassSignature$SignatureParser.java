package org.spongepowered.asm.util;

import org.spongepowered.asm.lib.signature.SignatureVisitor;

class SignatureParser extends SignatureVisitor
{
    private FormalParamElement param;
    final /* synthetic */ ClassSignature this$0;
    
    SignatureParser(final ClassSignature this$0) {
        this.this$0 = this$0;
        super(327680);
    }
    
    @Override
    public void visitFormalTypeParameter(final String s) {
        this.param = new FormalParamElement(s);
    }
    
    @Override
    public SignatureVisitor visitClassBound() {
        return this.param.visitClassBound();
    }
    
    @Override
    public SignatureVisitor visitInterfaceBound() {
        return this.param.visitInterfaceBound();
    }
    
    @Override
    public SignatureVisitor visitSuperclass() {
        return new SuperClassElement();
    }
    
    @Override
    public SignatureVisitor visitInterface() {
        return new InterfaceElement();
    }
}
