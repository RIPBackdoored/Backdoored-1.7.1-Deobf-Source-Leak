package org.spongepowered.tools.obfuscation.mapping.common;

import java.io.IOException;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.lang.model.element.Element;
import javax.tools.StandardLocation;
import javax.tools.Diagnostic;
import java.io.File;
import java.io.PrintWriter;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import org.spongepowered.tools.obfuscation.mapping.IMappingWriter;

public abstract class MappingWriter implements IMappingWriter
{
    private final Messager messager;
    private final Filer filer;
    
    public MappingWriter(final Messager messager, final Filer filer) {
        super();
        this.messager = messager;
        this.filer = filer;
    }
    
    protected PrintWriter openFileWriter(final String s, final String s2) throws IOException {
        if (s.matches("^.*[\\\\/:].*$")) {
            final File file = new File(s);
            file.getParentFile().mkdirs();
            this.messager.printMessage(Diagnostic.Kind.NOTE, "Writing " + s2 + " to " + file.getAbsolutePath());
            return new PrintWriter(file);
        }
        final FileObject resource = this.filer.createResource(StandardLocation.CLASS_OUTPUT, "", s, new Element[0]);
        this.messager.printMessage(Diagnostic.Kind.NOTE, "Writing " + s2 + " to " + new File(resource.toUri()).getAbsolutePath());
        return new PrintWriter(resource.openWriter());
    }
}
