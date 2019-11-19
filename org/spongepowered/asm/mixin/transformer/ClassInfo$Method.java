package org.spongepowered.asm.mixin.transformer;

import java.util.ListIterator;
import org.spongepowered.asm.lib.tree.FrameNode;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import java.util.ArrayList;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.gen.Accessor;
import java.lang.annotation.Annotation;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.lib.tree.MethodNode;
import java.util.List;

public class Method extends Member
{
    private final List<FrameData> frames;
    private boolean isAccessor;
    final /* synthetic */ ClassInfo this$0;
    
    public Method(final ClassInfo this$0, final Member member) {
        this.this$0 = this$0;
        super(member);
        this.frames = ((member instanceof Method) ? ((Method)member).frames : null);
    }
    
    public Method(final ClassInfo classInfo, final MethodNode methodNode) {
        this(classInfo, methodNode, false);
        this.setUnique(Annotations.getVisible(methodNode, Unique.class) != null);
        this.isAccessor = (Annotations.getSingleVisible(methodNode, Accessor.class, Invoker.class) != null);
    }
    
    public Method(final ClassInfo this$0, final MethodNode methodNode, final boolean b) {
        this.this$0 = this$0;
        super(Type.METHOD, methodNode.name, methodNode.desc, methodNode.access, b);
        this.frames = this.gatherFrames(methodNode);
        this.setUnique(Annotations.getVisible(methodNode, Unique.class) != null);
        this.isAccessor = (Annotations.getSingleVisible(methodNode, Accessor.class, Invoker.class) != null);
    }
    
    public Method(final ClassInfo this$0, final String s, final String s2) {
        this.this$0 = this$0;
        super(Type.METHOD, s, s2, 1, false);
        this.frames = null;
    }
    
    public Method(final ClassInfo this$0, final String s, final String s2, final int n) {
        this.this$0 = this$0;
        super(Type.METHOD, s, s2, n, false);
        this.frames = null;
    }
    
    public Method(final ClassInfo this$0, final String s, final String s2, final int n, final boolean b) {
        this.this$0 = this$0;
        super(Type.METHOD, s, s2, n, b);
        this.frames = null;
    }
    
    private List<FrameData> gatherFrames(final MethodNode methodNode) {
        final ArrayList<FrameData> list = new ArrayList<FrameData>();
        for (final AbstractInsnNode abstractInsnNode : methodNode.instructions) {
            if (abstractInsnNode instanceof FrameNode) {
                list.add(new FrameData(methodNode.instructions.indexOf(abstractInsnNode), (FrameNode)abstractInsnNode));
            }
        }
        return list;
    }
    
    public List<FrameData> getFrames() {
        return this.frames;
    }
    
    @Override
    public ClassInfo getOwner() {
        return this.this$0;
    }
    
    public boolean isAccessor() {
        return this.isAccessor;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof Method && super.equals(o);
    }
    
    @Override
    public /* bridge */ String toString() {
        return super.toString();
    }
    
    @Override
    public /* bridge */ int hashCode() {
        return super.hashCode();
    }
    
    @Override
    public /* bridge */ boolean equals(final String s, final String s2) {
        return super.equals(s, s2);
    }
    
    @Override
    public /* bridge */ String remapTo(final String s) {
        return super.remapTo(s);
    }
    
    @Override
    public /* bridge */ String renameTo(final String s) {
        return super.renameTo(s);
    }
    
    @Override
    public /* bridge */ int getAccess() {
        return super.getAccess();
    }
    
    @Override
    public /* bridge */ ClassInfo getImplementor() {
        return super.getImplementor();
    }
    
    @Override
    public /* bridge */ boolean matchesFlags(final int n) {
        return super.matchesFlags(n);
    }
    
    @Override
    public /* bridge */ void setDecoratedFinal(final boolean b, final boolean b2) {
        super.setDecoratedFinal(b, b2);
    }
    
    @Override
    public /* bridge */ boolean isDecoratedMutable() {
        return super.isDecoratedMutable();
    }
    
    @Override
    public /* bridge */ boolean isDecoratedFinal() {
        return super.isDecoratedFinal();
    }
    
    @Override
    public /* bridge */ void setUnique(final boolean unique) {
        super.setUnique(unique);
    }
    
    @Override
    public /* bridge */ boolean isUnique() {
        return super.isUnique();
    }
    
    @Override
    public /* bridge */ boolean isSynthetic() {
        return super.isSynthetic();
    }
    
    @Override
    public /* bridge */ boolean isFinal() {
        return super.isFinal();
    }
    
    @Override
    public /* bridge */ boolean isAbstract() {
        return super.isAbstract();
    }
    
    @Override
    public /* bridge */ boolean isStatic() {
        return super.isStatic();
    }
    
    @Override
    public /* bridge */ boolean isPrivate() {
        return super.isPrivate();
    }
    
    @Override
    public /* bridge */ boolean isRemapped() {
        return super.isRemapped();
    }
    
    @Override
    public /* bridge */ boolean isRenamed() {
        return super.isRenamed();
    }
    
    @Override
    public /* bridge */ boolean isInjected() {
        return super.isInjected();
    }
    
    @Override
    public /* bridge */ String getDesc() {
        return super.getDesc();
    }
    
    @Override
    public /* bridge */ String getOriginalDesc() {
        return super.getOriginalDesc();
    }
    
    @Override
    public /* bridge */ String getName() {
        return super.getName();
    }
    
    @Override
    public /* bridge */ String getOriginalName() {
        return super.getOriginalName();
    }
}
