package org.reflections.serializers;

import com.google.gson.JsonParseException;
import java.util.Iterator;
import com.google.common.collect.SetMultimap;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.Map;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;
import java.util.Set;
import com.google.common.base.Supplier;
import java.util.HashMap;
import com.google.gson.JsonDeserializationContext;
import java.lang.reflect.Type;
import com.google.gson.JsonElement;
import com.google.common.collect.Multimap;
import com.google.gson.JsonDeserializer;

class JsonSerializer$1 implements JsonDeserializer<Multimap> {
    final /* synthetic */ JsonSerializer this$0;
    
    JsonSerializer$1(final JsonSerializer this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public Multimap deserialize(final JsonElement jsonElement, final Type type, final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final SetMultimap setMultimap = Multimaps.newSetMultimap((Map)new HashMap(), (Supplier)new Supplier<Set<String>>() {
            final /* synthetic */ JsonSerializer$1 this$1;
            
            JsonSerializer$1$1() {
                this.this$1 = this$1;
                super();
            }
            
            @Override
            public Set<String> get() {
                return (Set<String>)Sets.<Object>newHashSet();
            }
            
            @Override
            public /* bridge */ Object get() {
                return this.get();
            }
        });
        for (final Map.Entry<String, JsonElement> entry : ((JsonObject)jsonElement).entrySet()) {
            final Iterator<JsonElement> iterator2 = entry.getValue().iterator();
            while (iterator2.hasNext()) {
                setMultimap.get((Object)entry.getKey()).add(iterator2.next().getAsString());
            }
        }
        return (Multimap)setMultimap;
    }
    
    @Override
    public /* bridge */ Object deserialize(final JsonElement jsonElement, final Type type, final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return this.deserialize(jsonElement, type, jsonDeserializationContext);
    }
}