package javassist.bytecode;

import java.util.HashMap;
import java.io.OutputStream;
import javassist.bytecode.annotation.AnnotationsWriter;
import java.io.ByteArrayOutputStream;
import javassist.bytecode.annotation.Annotation;
import java.util.Map;
import java.io.IOException;
import java.io.DataInputStream;

public class ParameterAnnotationsAttribute extends AttributeInfo
{
    public static final String visibleTag = "RuntimeVisibleParameterAnnotations";
    public static final String invisibleTag = "RuntimeInvisibleParameterAnnotations";
    
    public ParameterAnnotationsAttribute(final ConstPool constPool, final String s, final byte[] array) {
        super(constPool, s, array);
    }
    
    public ParameterAnnotationsAttribute(final ConstPool constPool, final String s) {
        this(constPool, s, new byte[] { 0 });
    }
    
    ParameterAnnotationsAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) throws IOException {
        super(constPool, n, dataInputStream);
    }
    
    public int numParameters() {
        return this.info[0] & 0xFF;
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map map) {
        final AnnotationsAttribute.Copier copier = new AnnotationsAttribute.Copier(this.info, this.constPool, constPool, map);
        try {
            copier.parameters();
            return new ParameterAnnotationsAttribute(constPool, this.getName(), copier.close());
        }
        catch (Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }
    
    public Annotation[][] getAnnotations() {
        try {
            return new AnnotationsAttribute.Parser(this.info, this.constPool).parseParameters();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }
    
    public void setAnnotations(final Annotation[][] array) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final AnnotationsWriter annotationsWriter = new AnnotationsWriter(byteArrayOutputStream, this.constPool);
        try {
            final int length = array.length;
            annotationsWriter.numParameters(length);
            for (final Annotation[] array2 : array) {
                annotationsWriter.numAnnotations(array2.length);
                for (int j = 0; j < array2.length; ++j) {
                    array2[j].write(annotationsWriter);
                }
            }
            annotationsWriter.close();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.set(byteArrayOutputStream.toByteArray());
    }
    
    @Override
    void renameClass(final String s, final String s2) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(s, s2);
        this.renameClass(hashMap);
    }
    
    @Override
    void renameClass(final Map map) {
        final AnnotationsAttribute.Renamer renamer = new AnnotationsAttribute.Renamer(this.info, this.getConstPool(), map);
        try {
            renamer.parameters();
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
        final Annotation[][] annotations = this.getAnnotations();
        final StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < annotations.length) {
            final Annotation[] array = annotations[i++];
            int j = 0;
            while (j < array.length) {
                sb.append(array[j++].toString());
                if (j != array.length) {
                    sb.append(" ");
                }
            }
            if (i != annotations.length) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
