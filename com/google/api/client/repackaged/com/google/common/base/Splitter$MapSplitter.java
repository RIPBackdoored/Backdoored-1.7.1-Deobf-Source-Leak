package com.google.api.client.repackaged.com.google.common.base;

import java.util.Iterator;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import com.google.api.client.repackaged.com.google.common.annotations.Beta;

@Beta
public static final class MapSplitter
{
    private static final String INVALID_ENTRY_MESSAGE = "Chunk [%s] is not a valid entry";
    private final Splitter outerSplitter;
    private final Splitter entrySplitter;
    
    private MapSplitter(final Splitter outerSplitter, final Splitter splitter) {
        super();
        this.outerSplitter = outerSplitter;
        this.entrySplitter = Preconditions.<Splitter>checkNotNull(splitter);
    }
    
    public Map<String, String> split(final CharSequence charSequence) {
        final LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        for (final String s : this.outerSplitter.split(charSequence)) {
            final Iterator access$000 = Splitter.access$000(this.entrySplitter, s);
            Preconditions.checkArgument(access$000.hasNext(), "Chunk [%s] is not a valid entry", s);
            final String s2 = access$000.next();
            Preconditions.checkArgument(!linkedHashMap.containsKey(s2), "Duplicate key [%s] found.", s2);
            Preconditions.checkArgument(access$000.hasNext(), "Chunk [%s] is not a valid entry", s);
            linkedHashMap.put(s2, access$000.next());
            Preconditions.checkArgument(!access$000.hasNext(), "Chunk [%s] is not a valid entry", s);
        }
        return (Map<String, String>)Collections.<Object, Object>unmodifiableMap((Map<?, ?>)linkedHashMap);
    }
    
    MapSplitter(final Splitter splitter, final Splitter splitter2, final Splitter$1 strategy) {
        this(splitter, splitter2);
    }
}
