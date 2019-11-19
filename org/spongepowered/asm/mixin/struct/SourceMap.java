package org.spongepowered.asm.mixin.struct;

import java.util.ListIterator;
import org.spongepowered.asm.lib.tree.LineNumberNode;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import java.util.Iterator;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.lib.tree.ClassNode;
import java.util.LinkedHashMap;
import java.util.Map;

public class SourceMap
{
    private static final String DEFAULT_STRATUM = "Mixin";
    private static final String NEWLINE = "\n";
    private final String sourceFile;
    private final Map<String, Stratum> strata;
    private int nextLineOffset;
    private String defaultStratum;
    
    public SourceMap(final String sourceFile) {
        super();
        this.strata = new LinkedHashMap<String, Stratum>();
        this.nextLineOffset = 1;
        this.defaultStratum = "Mixin";
        this.sourceFile = sourceFile;
    }
    
    public String getSourceFile() {
        return this.sourceFile;
    }
    
    public String getPseudoGeneratedSourceFile() {
        return this.sourceFile.replace(".java", "$mixin.java");
    }
    
    public File addFile(final ClassNode classNode) {
        return this.addFile(this.defaultStratum, classNode);
    }
    
    public File addFile(final String s, final ClassNode classNode) {
        return this.addFile(s, classNode.sourceFile, classNode.name + ".java", Bytecode.getMaxLineNumber(classNode, 500, 50));
    }
    
    public File addFile(final String s, final String s2, final int n) {
        return this.addFile(this.defaultStratum, s, s2, n);
    }
    
    public File addFile(final String s, final String s2, final String s3, final int n) {
        Stratum stratum = this.strata.get(s);
        if (stratum == null) {
            stratum = new Stratum(s);
            this.strata.put(s, stratum);
        }
        final File addFile = stratum.addFile(this.nextLineOffset, n, s2, s3);
        this.nextLineOffset += n;
        return addFile;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        this.appendTo(sb);
        return sb.toString();
    }
    
    private void appendTo(final StringBuilder sb) {
        sb.append("SMAP").append("\n");
        sb.append(this.getSourceFile()).append("\n");
        sb.append(this.defaultStratum).append("\n");
        final Iterator<Stratum> iterator = this.strata.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().appendTo(sb);
        }
        sb.append("*E").append("\n");
    }
}
