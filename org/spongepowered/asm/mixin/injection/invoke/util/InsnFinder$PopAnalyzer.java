package org.spongepowered.asm.mixin.injection.invoke.util;

import org.spongepowered.asm.lib.tree.analysis.Value;
import org.spongepowered.asm.lib.tree.analysis.AnalyzerException;
import org.spongepowered.asm.lib.tree.analysis.Frame;
import org.spongepowered.asm.lib.tree.analysis.Interpreter;
import org.spongepowered.asm.lib.tree.analysis.BasicInterpreter;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.analysis.BasicValue;
import org.spongepowered.asm.lib.tree.analysis.Analyzer;

static class PopAnalyzer extends Analyzer<BasicValue>
{
    protected final AbstractInsnNode node;
    
    public PopAnalyzer(final AbstractInsnNode node) {
        super(new BasicInterpreter());
        this.node = node;
    }
    
    @Override
    protected Frame<BasicValue> newFrame(final int n, final int n2) {
        return new PopFrame(n, n2);
    }
}
