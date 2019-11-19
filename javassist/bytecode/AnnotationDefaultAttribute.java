package javassist.bytecode;

import java.io.OutputStream;
import javassist.bytecode.annotation.AnnotationsWriter;
import java.io.ByteArrayOutputStream;
import javassist.bytecode.annotation.MemberValue;
import java.util.Map;
import java.io.IOException;
import java.io.DataInputStream;

public class AnnotationDefaultAttribute extends AttributeInfo
{
    public static final String tag = "AnnotationDefault";
    
    public AnnotationDefaultAttribute(final ConstPool constPool, final byte[] array) {
        super(constPool, "AnnotationDefault", array);
    }
    
    public AnnotationDefaultAttribute(final ConstPool constPool) {
        this(constPool, new byte[] { 0, 0 });
    }
    
    AnnotationDefaultAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) throws IOException {
        super(constPool, n, dataInputStream);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map map) {
        final AnnotationsAttribute.Copier copier = new AnnotationsAttribute.Copier(this.info, this.constPool, constPool, map);
        try {
            copier.memberValue(0);
            return new AnnotationDefaultAttribute(constPool, copier.close());
        }
        catch (Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }
    
    public MemberValue getDefaultValue() {
        try {
            return new AnnotationsAttribute.Parser(this.info, this.constPool).parseMemberValue();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }
    
    public void setDefaultValue(final MemberValue memberValue) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final AnnotationsWriter annotationsWriter = new AnnotationsWriter(byteArrayOutputStream, this.constPool);
        try {
            memberValue.write(annotationsWriter);
            annotationsWriter.close();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.set(byteArrayOutputStream.toByteArray());
    }
    
    @Override
    public String toString() {
        return this.getDefaultValue().toString();
    }
}
