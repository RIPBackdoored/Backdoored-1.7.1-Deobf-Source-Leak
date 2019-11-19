package com.google.api.client.googleapis.testing;

import java.util.ArrayList;
import java.util.Iterator;
import java.net.URLDecoder;
import java.io.IOException;
import com.google.common.collect.Lists;
import com.google.common.base.Splitter;
import java.util.HashMap;
import java.util.Map;

public final class TestUtils
{
    private static final String UTF_8 = "UTF-8";
    
    public static Map<String, String> parseQuery(final String s) throws IOException {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final Iterator<String> iterator = Splitter.on('&').split((CharSequence)s).iterator();
        while (iterator.hasNext()) {
            final ArrayList<Object> arrayList = Lists.<Object>newArrayList((Iterable<?>)Splitter.on('=').split((CharSequence)iterator.next()));
            if (arrayList.size() != 2) {
                throw new IOException("Invalid Query String");
            }
            hashMap.put(URLDecoder.decode((String)arrayList.get(0), "UTF-8"), URLDecoder.decode((String)arrayList.get(1), "UTF-8"));
        }
        return hashMap;
    }
    
    private TestUtils() {
        super();
    }
}
