package javassist.util.proxy;

import java.util.Map;
import java.util.Comparator;

static final class ProxyFactory$3 implements Comparator {
    ProxyFactory$3() {
        super();
    }
    
    @Override
    public int compare(final Object o, final Object o2) {
        return ((Map.Entry)o).getKey().compareTo((String)((Map.Entry)o2).getKey());
    }
}