package javassist.bytecode;

import java.io.OutputStream;
import javassist.bytecode.annotation.TypeAnnotationsWriter;
import java.util.Map;

static class Copier extends AnnotationsAttribute.Copier
{
    SubCopier sub;
    
    Copier(final byte[] array, final ConstPool constPool, final ConstPool constPool2, final Map map) {
        super(array, constPool, constPool2, map, false);
        final TypeAnnotationsWriter writer = new TypeAnnotationsWriter(this.output, constPool2);
        this.writer = writer;
        this.sub = new SubCopier(array, constPool, constPool2, map, writer);
    }
    
    @Override
    int annotationArray(int n, final int n2) throws Exception {
        this.writer.numAnnotations(n2);
        for (int i = 0; i < n2; ++i) {
            n = this.sub.targetInfo(n + 1, this.info[n] & 0xFF);
            n = this.sub.typePath(n);
            n = this.annotation(n);
        }
        return n;
    }
}
