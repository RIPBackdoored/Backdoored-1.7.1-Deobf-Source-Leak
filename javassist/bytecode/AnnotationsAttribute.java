package javassist.bytecode;

import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.AnnotationMemberValue;
import javassist.bytecode.annotation.ClassMemberValue;
import javassist.bytecode.annotation.EnumMemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.ShortMemberValue;
import javassist.bytecode.annotation.LongMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.FloatMemberValue;
import javassist.bytecode.annotation.DoubleMemberValue;
import javassist.bytecode.annotation.CharMemberValue;
import javassist.bytecode.annotation.ByteMemberValue;
import javassist.bytecode.annotation.MemberValue;
import java.util.HashMap;
import java.io.OutputStream;
import javassist.bytecode.annotation.AnnotationsWriter;
import java.io.ByteArrayOutputStream;
import javassist.bytecode.annotation.Annotation;
import java.util.Map;
import java.io.IOException;
import java.io.DataInputStream;

public class AnnotationsAttribute extends AttributeInfo
{
    public static final String visibleTag = "RuntimeVisibleAnnotations";
    public static final String invisibleTag = "RuntimeInvisibleAnnotations";
    
    public AnnotationsAttribute(final ConstPool constPool, final String s, final byte[] array) {
        super(constPool, s, array);
    }
    
    public AnnotationsAttribute(final ConstPool constPool, final String s) {
        this(constPool, s, new byte[] { 0, 0 });
    }
    
    AnnotationsAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) throws IOException {
        super(constPool, n, dataInputStream);
    }
    
    public int numAnnotations() {
        return ByteArray.readU16bit(this.info, 0);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map map) {
        final Copier copier = new Copier(this.info, this.constPool, constPool, map);
        try {
            copier.annotationArray();
            return new AnnotationsAttribute(constPool, this.getName(), copier.close());
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public Annotation getAnnotation(final String s) {
        final Annotation[] annotations = this.getAnnotations();
        for (int i = 0; i < annotations.length; ++i) {
            if (annotations[i].getTypeName().equals(s)) {
                return annotations[i];
            }
        }
        return null;
    }
    
    public void addAnnotation(final Annotation annotation) {
        final String typeName = annotation.getTypeName();
        final Annotation[] annotations = this.getAnnotations();
        for (int i = 0; i < annotations.length; ++i) {
            if (annotations[i].getTypeName().equals(typeName)) {
                annotations[i] = annotation;
                this.setAnnotations(annotations);
                return;
            }
        }
        final Annotation[] annotations2 = new Annotation[annotations.length + 1];
        System.arraycopy(annotations, 0, annotations2, 0, annotations.length);
        annotations2[annotations.length] = annotation;
        this.setAnnotations(annotations2);
    }
    
    public boolean removeAnnotation(final String s) {
        final Annotation[] annotations = this.getAnnotations();
        for (int i = 0; i < annotations.length; ++i) {
            if (annotations[i].getTypeName().equals(s)) {
                final Annotation[] annotations2 = new Annotation[annotations.length - 1];
                System.arraycopy(annotations, 0, annotations2, 0, i);
                if (i < annotations.length - 1) {
                    System.arraycopy(annotations, i + 1, annotations2, i, annotations.length - i - 1);
                }
                this.setAnnotations(annotations2);
                return true;
            }
        }
        return false;
    }
    
    public Annotation[] getAnnotations() {
        try {
            return new Parser(this.info, this.constPool).parseAnnotations();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void setAnnotations(final Annotation[] array) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final AnnotationsWriter annotationsWriter = new AnnotationsWriter(byteArrayOutputStream, this.constPool);
        try {
            final int length = array.length;
            annotationsWriter.numAnnotations(length);
            for (int i = 0; i < length; ++i) {
                array[i].write(annotationsWriter);
            }
            annotationsWriter.close();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.set(byteArrayOutputStream.toByteArray());
    }
    
    public void setAnnotation(final Annotation annotation) {
        this.setAnnotations(new Annotation[] { annotation });
    }
    
    @Override
    void renameClass(final String s, final String s2) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(s, s2);
        this.renameClass(hashMap);
    }
    
    @Override
    void renameClass(final Map map) {
        final Renamer renamer = new Renamer(this.info, this.getConstPool(), map);
        try {
            renamer.annotationArray();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    void getRefClasses(final Map map) {
        this.renameClass(map);
    }
    
    @Override
    public String toString() {
        final Annotation[] annotations = this.getAnnotations();
        final StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < annotations.length) {
            sb.append(annotations[i++].toString());
            if (i != annotations.length) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
