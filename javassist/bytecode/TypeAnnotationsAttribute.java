package javassist.bytecode;

import java.io.OutputStream;
import javassist.bytecode.annotation.TypeAnnotationsWriter;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.DataInputStream;

public class TypeAnnotationsAttribute extends AttributeInfo
{
    public static final String visibleTag = "RuntimeVisibleTypeAnnotations";
    public static final String invisibleTag = "RuntimeInvisibleTypeAnnotations";
    
    public TypeAnnotationsAttribute(final ConstPool constPool, final String s, final byte[] array) {
        super(constPool, s, array);
    }
    
    TypeAnnotationsAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) throws IOException {
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
            return new TypeAnnotationsAttribute(constPool, this.getName(), copier.close());
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
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
}
