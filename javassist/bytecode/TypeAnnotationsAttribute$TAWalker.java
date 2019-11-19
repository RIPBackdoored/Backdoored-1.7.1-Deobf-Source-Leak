package javassist.bytecode;

static class TAWalker extends AnnotationsAttribute.Walker
{
    SubWalker subWalker;
    
    TAWalker(final byte[] array) {
        super(array);
        this.subWalker = new SubWalker(array);
    }
    
    @Override
    int annotationArray(int n, final int n2) throws Exception {
        for (int i = 0; i < n2; ++i) {
            n = this.subWalker.targetInfo(n + 1, this.info[n] & 0xFF);
            n = this.subWalker.typePath(n);
            n = this.annotation(n);
        }
        return n;
    }
}
