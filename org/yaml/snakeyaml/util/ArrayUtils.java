package org.yaml.snakeyaml.util;

import java.util.AbstractList;
import java.util.Collections;
import java.util.List;

public class ArrayUtils
{
    private ArrayUtils() {
        super();
    }
    
    public static <E> List<E> toUnmodifiableList(final E... array) {
        return (array.length == 0) ? Collections.<E>emptyList() : new UnmodifiableArrayList<E>(array);
    }
    
    public static <E> List<E> toUnmodifiableCompositeList(final E[] array, final E[] array2) {
        List<E> list;
        if (array.length == 0) {
            list = ArrayUtils.<E>toUnmodifiableList(array2);
        }
        else if (array2.length == 0) {
            list = ArrayUtils.<E>toUnmodifiableList(array);
        }
        else {
            list = new CompositeUnmodifiableArrayList<E>(array, array2);
        }
        return list;
    }
}
