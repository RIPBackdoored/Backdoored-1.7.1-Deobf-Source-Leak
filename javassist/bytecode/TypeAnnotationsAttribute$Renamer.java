package javassist.bytecode;

import java.util.Map;

static class Renamer extends AnnotationsAttribute.Renamer
{
    SubWalker sub;
    
    Renamer(final byte[] array, final ConstPool constPool, final Map map) {
        super(array, constPool, map);
        this.sub = new SubWalker(array);
    }
    
    @Override
    int annotationArray(int n, final int n2) throws Exception {
        for (int i = 0; i < n2; ++i) {
            n = this.sub.targetInfo(n + 1, this.info[n] & 0xFF);
            n = this.sub.typePath(n);
            n = this.annotation(n);
        }
        return n;
    }
}
