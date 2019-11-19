package org.spongepowered.asm.mixin.injection.points;

import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import java.util.Collection;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.mixin.injection.InjectionPoint;

@AtCode("HEAD")
public class MethodHead extends InjectionPoint
{
    public MethodHead(final InjectionPointData injectionPointData) {
        super(injectionPointData);
    }
    
    @Override
    public boolean checkPriority(final int n, final int n2) {
        return true;
    }
    
    @Override
    public boolean find(final String s, final InsnList list, final Collection<AbstractInsnNode> collection) {
        collection.add(list.getFirst());
        return true;
    }
}
