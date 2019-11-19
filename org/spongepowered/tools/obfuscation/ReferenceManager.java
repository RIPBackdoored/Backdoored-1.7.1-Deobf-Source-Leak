package org.spongepowered.tools.obfuscation;

import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import java.util.Iterator;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.lang.model.element.Element;
import javax.tools.StandardLocation;
import javax.tools.Diagnostic;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import org.spongepowered.asm.mixin.refmap.ReferenceMapper;
import java.util.List;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IReferenceManager;

public class ReferenceManager implements IReferenceManager
{
    private final IMixinAnnotationProcessor ap;
    private final String outRefMapFileName;
    private final List<ObfuscationEnvironment> environments;
    private final ReferenceMapper refMapper;
    private boolean allowConflicts;
    
    public ReferenceManager(final IMixinAnnotationProcessor ap, final List<ObfuscationEnvironment> environments) {
        super();
        this.refMapper = new ReferenceMapper();
        this.ap = ap;
        this.environments = environments;
        this.outRefMapFileName = this.ap.getOption("outRefMapFile");
    }
    
    @Override
    public boolean getAllowConflicts() {
        return this.allowConflicts;
    }
    
    @Override
    public void setAllowConflicts(final boolean allowConflicts) {
        this.allowConflicts = allowConflicts;
    }
    
    @Override
    public void write() {
        if (this.outRefMapFileName == null) {
            return;
        }
        Appendable writer = null;
        try {
            writer = this.newWriter(this.outRefMapFileName, "refmap");
            this.refMapper.write(writer);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (writer != null) {
                try {
                    ((PrintWriter)writer).close();
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    private PrintWriter newWriter(final String s, final String s2) throws IOException {
        if (s.matches("^.*[\\\\/:].*$")) {
            final File file = new File(s);
            file.getParentFile().mkdirs();
            this.ap.printMessage(Diagnostic.Kind.NOTE, "Writing " + s2 + " to " + file.getAbsolutePath());
            return new PrintWriter(file);
        }
        final FileObject resource = this.ap.getProcessingEnvironment().getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", s, new Element[0]);
        this.ap.printMessage(Diagnostic.Kind.NOTE, "Writing " + s2 + " to " + new File(resource.toUri()).getAbsolutePath());
        return new PrintWriter(resource.openWriter());
    }
    
    @Override
    public ReferenceMapper getMapper() {
        return this.refMapper;
    }
    
    @Override
    public void addMethodMapping(final String s, final String s2, final ObfuscationData<MappingMethod> obfuscationData) {
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            final MappingMethod mappingMethod = obfuscationData.get(obfuscationEnvironment.getType());
            if (mappingMethod != null) {
                this.addMapping(obfuscationEnvironment.getType(), s, s2, new MemberInfo(mappingMethod).toString());
            }
        }
    }
    
    @Override
    public void addMethodMapping(final String s, final String s2, final MemberInfo memberInfo, final ObfuscationData<MappingMethod> obfuscationData) {
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            final MappingMethod mappingMethod = obfuscationData.get(obfuscationEnvironment.getType());
            if (mappingMethod != null) {
                this.addMapping(obfuscationEnvironment.getType(), s, s2, memberInfo.remapUsing(mappingMethod, true).toString());
            }
        }
    }
    
    @Override
    public void addFieldMapping(final String s, final String s2, final MemberInfo memberInfo, final ObfuscationData<MappingField> obfuscationData) {
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            final MappingField mappingField = obfuscationData.get(obfuscationEnvironment.getType());
            if (mappingField != null) {
                this.addMapping(obfuscationEnvironment.getType(), s, s2, MemberInfo.fromMapping(mappingField.transform(obfuscationEnvironment.remapDescriptor(memberInfo.desc))).toString());
            }
        }
    }
    
    @Override
    public void addClassMapping(final String s, final String s2, final ObfuscationData<String> obfuscationData) {
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            final String s3 = obfuscationData.get(obfuscationEnvironment.getType());
            if (s3 != null) {
                this.addMapping(obfuscationEnvironment.getType(), s, s2, s3);
            }
        }
    }
    
    protected void addMapping(final ObfuscationType obfuscationType, final String s, final String s2, final String s3) {
        final String addMapping = this.refMapper.addMapping(obfuscationType.getKey(), s, s2, s3);
        if (obfuscationType.isDefault()) {
            this.refMapper.addMapping(null, s, s2, s3);
        }
        if (!this.allowConflicts && addMapping != null && !addMapping.equals(s3)) {
            throw new ReferenceConflictException(addMapping, s3);
        }
    }
}
