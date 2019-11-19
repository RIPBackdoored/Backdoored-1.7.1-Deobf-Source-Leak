package javassist;

import java.util.Comparator;

static final class SerialVersionUID$3 implements Comparator {
    SerialVersionUID$3() {
        super();
    }
    
    @Override
    public int compare(final Object o, final Object o2) {
        final CtMethod ctMethod = (CtMethod)o;
        final CtMethod ctMethod2 = (CtMethod)o2;
        ctMethod.getName().compareTo(ctMethod2.getName());
        return ctMethod.getMethodInfo2().getDescriptor().compareTo(ctMethod2.getMethodInfo2().getDescriptor());
    }
}