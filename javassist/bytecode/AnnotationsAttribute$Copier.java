package javassist.bytecode;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import javassist.bytecode.annotation.AnnotationsWriter;
import java.io.ByteArrayOutputStream;

static class Copier extends Walker
{
    ByteArrayOutputStream output;
    AnnotationsWriter writer;
    ConstPool srcPool;
    ConstPool destPool;
    Map classnames;
    
    Copier(final byte[] array, final ConstPool constPool, final ConstPool constPool2, final Map map) {
        this(array, constPool, constPool2, map, true);
    }
    
    Copier(final byte[] array, final ConstPool srcPool, final ConstPool destPool, final Map classnames, final boolean b) {
        super(array);
        this.output = new ByteArrayOutputStream();
        if (b) {
            this.writer = new AnnotationsWriter(this.output, destPool);
        }
        this.srcPool = srcPool;
        this.destPool = destPool;
        this.classnames = classnames;
    }
    
    byte[] close() throws IOException {
        this.writer.close();
        return this.output.toByteArray();
    }
    
    @Override
    void parameters(final int n, final int n2) throws Exception {
        this.writer.numParameters(n);
        super.parameters(n, n2);
    }
    
    @Override
    int annotationArray(final int n, final int n2) throws Exception {
        this.writer.numAnnotations(n2);
        return super.annotationArray(n, n2);
    }
    
    @Override
    int annotation(final int n, final int n2, final int n3) throws Exception {
        this.writer.annotation(this.copyType(n2), n3);
        return super.annotation(n, n2, n3);
    }
    
    @Override
    int memberValuePair(final int n, final int n2) throws Exception {
        this.writer.memberValuePair(this.copy(n2));
        return super.memberValuePair(n, n2);
    }
    
    @Override
    void constValueMember(final int n, final int n2) throws Exception {
        this.writer.constValueIndex(n, this.copy(n2));
        super.constValueMember(n, n2);
    }
    
    @Override
    void enumMemberValue(final int n, final int n2, final int n3) throws Exception {
        this.writer.enumConstValue(this.copyType(n2), this.copy(n3));
        super.enumMemberValue(n, n2, n3);
    }
    
    @Override
    void classMemberValue(final int n, final int n2) throws Exception {
        this.writer.classInfoIndex(this.copyType(n2));
        super.classMemberValue(n, n2);
    }
    
    @Override
    int annotationMemberValue(final int n) throws Exception {
        this.writer.annotationValue();
        return super.annotationMemberValue(n);
    }
    
    @Override
    int arrayMemberValue(final int n, final int n2) throws Exception {
        this.writer.arrayValue(n2);
        return super.arrayMemberValue(n, n2);
    }
    
    int copy(final int n) {
        return this.srcPool.copy(n, this.destPool, this.classnames);
    }
    
    int copyType(final int n) {
        return this.destPool.addUtf8Info(Descriptor.rename(this.srcPool.getUtf8Info(n), this.classnames));
    }
}
