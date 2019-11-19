package com.google.api.client.util.store;

import java.util.Iterator;
import java.io.IOException;

public final class DataStoreUtils
{
    public static String toString(final DataStore<?> dataStore) {
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append('{');
            int n = 1;
            for (final String s : dataStore.keySet()) {
                if (n != 0) {
                    n = 0;
                }
                else {
                    sb.append(", ");
                }
                sb.append(s).append('=').append(dataStore.get(s));
            }
            return sb.append('}').toString();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private DataStoreUtils() {
        super();
    }
}
