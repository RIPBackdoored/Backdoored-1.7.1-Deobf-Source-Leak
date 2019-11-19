package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.lib.tree.InsnList;
import java.util.ListIterator;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import java.util.Collection;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.mixin.injection.InjectionPoint;

@AtCode("LOAD")
public class BeforeLoadLocal extends ModifyVariableInjector.ContextualInjectionPoint
{
    private final Type returnType;
    private final LocalVariableDiscriminator discriminator;
    private final int opcode;
    private final int ordinal;
    private boolean opcodeAfter;
    
    protected BeforeLoadLocal(final InjectionPointData injectionPointData) {
        this(injectionPointData, 21, false);
    }
    
    protected BeforeLoadLocal(final InjectionPointData injectionPointData, final int n, final boolean opcodeAfter) {
        super(injectionPointData.getContext());
        this.returnType = injectionPointData.getMethodReturnType();
        this.discriminator = injectionPointData.getLocalVariableDiscriminator();
        this.opcode = injectionPointData.getOpcode(this.returnType.getOpcode(n));
        this.ordinal = injectionPointData.getOrdinal();
        this.opcodeAfter = opcodeAfter;
    }
    
    @Override
    boolean find(final Target target, final Collection<AbstractInsnNode> collection) {
        final SearchState searchState = new SearchState(this.ordinal, this.discriminator.printLVT());
        for (final AbstractInsnNode abstractInsnNode : target.method.instructions) {
            if (searchState.isPendingCheck()) {
                searchState.check(collection, abstractInsnNode, this.discriminator.findLocal(this.returnType, this.discriminator.isArgsOnly(), target, abstractInsnNode));
            }
            else {
                if (!(abstractInsnNode instanceof VarInsnNode) || abstractInsnNode.getOpcode() != this.opcode || (this.ordinal != -1 && searchState.success())) {
                    continue;
                }
                searchState.register((VarInsnNode)abstractInsnNode);
                if (this.opcodeAfter) {
                    searchState.setPendingCheck();
                }
                else {
                    searchState.check(collection, abstractInsnNode, this.discriminator.findLocal(this.returnType, this.discriminator.isArgsOnly(), target, abstractInsnNode));
                }
            }
        }
        return searchState.success();
    }
    
    @Override
    public /* bridge */ boolean find(final String s, final InsnList list, final Collection collection) {
        return super.find(s, list, collection);
    }
}
