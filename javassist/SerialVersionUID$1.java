package javassist;

import java.util.Comparator;

static final class SerialVersionUID$1 implements Comparator {
    SerialVersionUID$1() {
        super();
    }
    
    @Override
    public int compare(final Object o, final Object o2) {
        return ((CtField)o).getName().compareTo(((CtField)o2).getName());
    }
}