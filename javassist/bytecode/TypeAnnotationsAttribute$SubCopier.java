package javassist.bytecode;

import javassist.bytecode.annotation.TypeAnnotationsWriter;
import java.util.Map;

static class SubCopier extends SubWalker
{
    ConstPool srcPool;
    ConstPool destPool;
    Map classnames;
    TypeAnnotationsWriter writer;
    
    SubCopier(final byte[] array, final ConstPool srcPool, final ConstPool destPool, final Map classnames, final TypeAnnotationsWriter writer) {
        super(array);
        this.srcPool = srcPool;
        this.destPool = destPool;
        this.classnames = classnames;
        this.writer = writer;
    }
    
    @Override
    void typeParameterTarget(final int n, final int n2, final int n3) throws Exception {
        this.writer.typeParameterTarget(n2, n3);
    }
    
    @Override
    void supertypeTarget(final int n, final int n2) throws Exception {
        this.writer.supertypeTarget(n2);
    }
    
    @Override
    void typeParameterBoundTarget(final int n, final int n2, final int n3, final int n4) throws Exception {
        this.writer.typeParameterBoundTarget(n2, n3, n4);
    }
    
    @Override
    void emptyTarget(final int n, final int n2) throws Exception {
        this.writer.emptyTarget(n2);
    }
    
    @Override
    void formalParameterTarget(final int n, final int n2) throws Exception {
        this.writer.formalParameterTarget(n2);
    }
    
    @Override
    void throwsTarget(final int n, final int n2) throws Exception {
        this.writer.throwsTarget(n2);
    }
    
    @Override
    int localvarTarget(final int n, final int n2, final int n3) throws Exception {
        this.writer.localVarTarget(n2, n3);
        return super.localvarTarget(n, n2, n3);
    }
    
    @Override
    void localvarTarget(final int n, final int n2, final int n3, final int n4, final int n5) throws Exception {
        this.writer.localVarTargetTable(n3, n4, n5);
    }
    
    @Override
    void catchTarget(final int n, final int n2) throws Exception {
        this.writer.catchTarget(n2);
    }
    
    @Override
    void offsetTarget(final int n, final int n2, final int n3) throws Exception {
        this.writer.offsetTarget(n2, n3);
    }
    
    @Override
    void typeArgumentTarget(final int n, final int n2, final int n3, final int n4) throws Exception {
        this.writer.typeArgumentTarget(n2, n3, n4);
    }
    
    @Override
    int typePath(final int n, final int n2) throws Exception {
        this.writer.typePath(n2);
        return super.typePath(n, n2);
    }
    
    @Override
    void typePath(final int n, final int n2, final int n3) throws Exception {
        this.writer.typePathPath(n2, n3);
    }
}
