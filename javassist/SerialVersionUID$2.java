package javassist;

import java.util.Comparator;

static final class SerialVersionUID$2 implements Comparator {
    SerialVersionUID$2() {
        super();
    }
    
    @Override
    public int compare(final Object o, final Object o2) {
        return ((CtConstructor)o).getMethodInfo2().getDescriptor().compareTo(((CtConstructor)o2).getMethodInfo2().getDescriptor());
    }
}