package org.spongepowered.asm.mixin.struct;

import org.spongepowered.asm.mixin.transformer.throwables.MixinTransformerError;
import org.spongepowered.asm.lib.Handle;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.util.Bytecode;

public abstract class MemberRef
{
    private static final int[] H_OPCODES;
    
    public MemberRef() {
        super();
    }
    
    public abstract boolean isField();
    
    public abstract int getOpcode();
    
    public abstract void setOpcode(final int p0);
    
    public abstract String getOwner();
    
    public abstract void setOwner(final String p0);
    
    public abstract String getName();
    
    public abstract void setName(final String p0);
    
    public abstract String getDesc();
    
    public abstract void setDesc(final String p0);
    
    @Override
    public String toString() {
        return String.format("%s for %s.%s%s%s", Bytecode.getOpcodeName(this.getOpcode()), this.getOwner(), this.getName(), this.isField() ? ":" : "", this.getDesc());
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof MemberRef)) {
            return false;
        }
        final MemberRef memberRef = (MemberRef)o;
        return this.getOpcode() == memberRef.getOpcode() && this.getOwner().equals(memberRef.getOwner()) && this.getName().equals(memberRef.getName()) && this.getDesc().equals(memberRef.getDesc());
    }
    
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
    
    static int opcodeFromTag(final int n) {
        return (n >= 0 && n < MemberRef.H_OPCODES.length) ? MemberRef.H_OPCODES[n] : 0;
    }
    
    static int tagFromOpcode(final int n) {
        for (int i = 1; i < MemberRef.H_OPCODES.length; ++i) {
            if (MemberRef.H_OPCODES[i] == n) {
                return i;
            }
        }
        return 0;
    }
    
    static {
        H_OPCODES = new int[] { 0, 180, 178, 181, 179, 182, 184, 183, 183, 185 };
    }
}
