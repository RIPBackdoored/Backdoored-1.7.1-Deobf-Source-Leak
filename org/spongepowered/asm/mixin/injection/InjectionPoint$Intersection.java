package org.spongepowered.asm.mixin.injection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import java.util.Collection;
import org.spongepowered.asm.lib.tree.InsnList;

static final class Intersection extends CompositeInjectionPoint
{
    public Intersection(final InjectionPoint... array) {
        super(array);
    }
    
    @Override
    public boolean find(final String s, final InsnList list, final Collection<AbstractInsnNode> collection) {
        final boolean b = false;
        final ArrayList[] array = (ArrayList[])Array.newInstance(ArrayList.class, this.components.length);
        for (int i = 0; i < this.components.length; ++i) {
            array[i] = new ArrayList();
            this.components[i].find(s, list, array[i]);
        }
        final ArrayList list2 = array[0];
        for (int j = 0; j < list2.size(); ++j) {
            final AbstractInsnNode abstractInsnNode = list2.get(j);
            for (int n = 1; n < array.length && array[n].contains(abstractInsnNode); ++n) {}
        }
        return b;
    }
}
