package org.spongepowered.asm.mixin.injection.points;

import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import java.util.Collection;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.mixin.injection.InjectionPoint;

@AtCode("INVOKE_ASSIGN")
public class AfterInvoke extends BeforeInvoke
{
    public AfterInvoke(final InjectionPointData injectionPointData) {
        super(injectionPointData);
    }
    
    @Override
    protected boolean addInsn(final InsnList list, final Collection<AbstractInsnNode> collection, AbstractInsnNode abstractInsnNode) {
        if (Type.getReturnType(((MethodInsnNode)abstractInsnNode).desc) == Type.VOID_TYPE) {
            return false;
        }
        abstractInsnNode = InjectionPoint.nextNode(list, abstractInsnNode);
        if (abstractInsnNode instanceof VarInsnNode && abstractInsnNode.getOpcode() >= 54) {
            abstractInsnNode = InjectionPoint.nextNode(list, abstractInsnNode);
        }
        collection.add(abstractInsnNode);
        return true;
    }
}
