package org.spongepowered.asm.util;

import org.spongepowered.asm.lib.signature.SignatureWriter;
import org.spongepowered.asm.lib.tree.ClassNode;
import java.util.Set;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Iterator;
import org.spongepowered.asm.lib.signature.SignatureVisitor;
import org.spongepowered.asm.lib.signature.SignatureReader;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public class ClassSignature
{
    protected static final String OBJECT = "java/lang/Object";
    private final Map<TypeVar, TokenHandle> types;
    private Token superClass;
    private final List<Token> interfaces;
    private final Deque<String> rawInterfaces;
    
    ClassSignature() {
        super();
        this.types = new LinkedHashMap<TypeVar, TokenHandle>();
        this.superClass = new Token("java/lang/Object");
        this.interfaces = new ArrayList<Token>();
        this.rawInterfaces = new LinkedList<String>();
    }
    
    private ClassSignature read(final String s) {
        if (s != null) {
            try {
                new SignatureReader(s).accept(new SignatureParser());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return this;
    }
    
    protected TypeVar getTypeVar(final String s) {
        for (final TypeVar typeVar : this.types.keySet()) {
            if (typeVar.matches(s)) {
                return typeVar;
            }
        }
        return null;
    }
    
    protected TokenHandle getType(final String s) {
        for (final TypeVar typeVar : this.types.keySet()) {
            if (typeVar.matches(s)) {
                return this.types.get(typeVar);
            }
        }
        final TokenHandle tokenHandle = new TokenHandle();
        this.types.put(new TypeVar(s), tokenHandle);
        return tokenHandle;
    }
    
    protected String getTypeVar(final TokenHandle tokenHandle) {
        for (final Map.Entry<TypeVar, TokenHandle> entry : this.types.entrySet()) {
            final TypeVar typeVar = entry.getKey();
            final TokenHandle tokenHandle2 = entry.getValue();
            if (tokenHandle == tokenHandle2 || tokenHandle.asToken() == tokenHandle2.asToken()) {
                return "T" + typeVar + ";";
            }
        }
        return tokenHandle.token.asType();
    }
    
    protected void addTypeVar(final TypeVar typeVar, final TokenHandle tokenHandle) throws IllegalArgumentException {
        if (this.types.containsKey(typeVar)) {
            throw new IllegalArgumentException("TypeVar " + typeVar + " is already present on " + this);
        }
        this.types.put(typeVar, tokenHandle);
    }
    
    protected void setSuperClass(final Token superClass) {
        this.superClass = superClass;
    }
    
    public String getSuperClass() {
        return this.superClass.asType(true);
    }
    
    protected void addInterface(final Token token) {
        if (!token.isRaw()) {
            final String type = token.asType(true);
            final ListIterator<Token> listIterator = this.interfaces.listIterator();
            while (listIterator.hasNext()) {
                final Token token2 = listIterator.next();
                if (token2.isRaw() && token2.asType(true).equals(type)) {
                    listIterator.set(token);
                    return;
                }
            }
        }
        this.interfaces.add(token);
    }
    
    public void addInterface(final String s) {
        this.rawInterfaces.add(s);
    }
    
    protected void addRawInterface(final String s) {
        final Token token = new Token(s);
        final String type = token.asType(true);
        final Iterator<Token> iterator = this.interfaces.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().asType(true).equals(type)) {
                return;
            }
        }
        this.interfaces.add(token);
    }
    
    public void merge(final ClassSignature classSignature) {
        try {
            final HashSet<String> set = new HashSet<String>();
            final Iterator<TypeVar> iterator = this.types.keySet().iterator();
            while (iterator.hasNext()) {
                set.add(iterator.next().toString());
            }
            classSignature.conform(set);
        }
        catch (IllegalStateException ex) {
            ex.printStackTrace();
            return;
        }
        for (final Map.Entry<TypeVar, TokenHandle> entry : classSignature.types.entrySet()) {
            this.addTypeVar(entry.getKey(), entry.getValue());
        }
        final Iterator<Token> iterator3 = classSignature.interfaces.iterator();
        while (iterator3.hasNext()) {
            this.addInterface(iterator3.next());
        }
    }
    
    private void conform(final Set<String> set) {
        for (final TypeVar typeVar : this.types.keySet()) {
            final String uniqueName = this.findUniqueName(typeVar.getOriginalName(), set);
            typeVar.rename(uniqueName);
            set.add(uniqueName);
        }
    }
    
    private String findUniqueName(final String s, final Set<String> set) {
        if (!set.contains(s)) {
            return s;
        }
        if (s.length() == 1) {
            final String offsetName = this.findOffsetName(s.charAt(0), set);
            if (offsetName != null) {
                return offsetName;
            }
        }
        final String offsetName2 = this.findOffsetName('T', set, "", s);
        if (offsetName2 != null) {
            return offsetName2;
        }
        final String offsetName3 = this.findOffsetName('T', set, s, "");
        if (offsetName3 != null) {
            return offsetName3;
        }
        final String offsetName4 = this.findOffsetName('T', set, "T", s);
        if (offsetName4 != null) {
            return offsetName4;
        }
        final String offsetName5 = this.findOffsetName('T', set, "", s + "Type");
        if (offsetName5 != null) {
            return offsetName5;
        }
        throw new IllegalStateException("Failed to conform type var: " + s);
    }
    
    private String findOffsetName(final char c, final Set<String> set) {
        return this.findOffsetName(c, set, "", "");
    }
    
    private String findOffsetName(final char c, final Set<String> set, final String s, final String s2) {
        final String format = String.format("%s%s%s", s, c, s2);
        if (!set.contains(format)) {
            return format;
        }
        if (c > '@' && c < '[') {
            for (int n = c - '@'; n + 65 != c; n = ++n % 26) {
                final String format2 = String.format("%s%s%s", s, (char)(n + 65), s2);
                if (!set.contains(format2)) {
                    return format2;
                }
            }
        }
        return null;
    }
    
    public SignatureVisitor getRemapper() {
        return new SignatureRemapper();
    }
    
    @Override
    public String toString() {
        while (this.rawInterfaces.size() > 0) {
            this.addRawInterface(this.rawInterfaces.remove());
        }
        final StringBuilder sb = new StringBuilder();
        if (this.types.size() > 0) {
            boolean b = false;
            final StringBuilder sb2 = new StringBuilder();
            for (final Map.Entry<TypeVar, TokenHandle> entry : this.types.entrySet()) {
                final String bound = entry.getValue().asBound();
                if (!bound.isEmpty()) {
                    sb2.append(entry.getKey()).append(':').append(bound);
                    b = true;
                }
            }
            if (b) {
                sb.append('<').append((CharSequence)sb2).append('>');
            }
        }
        sb.append(this.superClass.asType());
        final Iterator<Token> iterator2 = this.interfaces.iterator();
        while (iterator2.hasNext()) {
            sb.append(iterator2.next().asType());
        }
        return sb.toString();
    }
    
    public ClassSignature wake() {
        return this;
    }
    
    public static ClassSignature of(final String s) {
        return new ClassSignature().read(s);
    }
    
    public static ClassSignature of(final ClassNode classNode) {
        if (classNode.signature != null) {
            return of(classNode.signature);
        }
        return generate(classNode);
    }
    
    public static ClassSignature ofLazy(final ClassNode classNode) {
        if (classNode.signature != null) {
            return new Lazy(classNode.signature);
        }
        return generate(classNode);
    }
    
    private static ClassSignature generate(final ClassNode classNode) {
        final ClassSignature classSignature = new ClassSignature();
        classSignature.setSuperClass(new Token((classNode.superName != null) ? classNode.superName : "java/lang/Object"));
        final Iterator<String> iterator = classNode.interfaces.iterator();
        while (iterator.hasNext()) {
            classSignature.addInterface(new Token(iterator.next()));
        }
        return classSignature;
    }
}
