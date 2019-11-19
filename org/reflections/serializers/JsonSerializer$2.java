package org.reflections.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import java.lang.reflect.Type;
import com.google.common.collect.Multimap;
import com.google.gson.JsonSerializer;

class JsonSerializer$2 implements JsonSerializer<Multimap> {
    final /* synthetic */ JsonSerializer this$0;
    
    JsonSerializer$2(final JsonSerializer this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public JsonElement serialize(final Multimap multimap, final Type type, final JsonSerializationContext jsonSerializationContext) {
        return jsonSerializationContext.serialize(multimap.asMap());
    }
    
    @Override
    public /* bridge */ JsonElement serialize(final Object o, final Type type, final JsonSerializationContext jsonSerializationContext) {
        return this.serialize((Multimap)o, type, jsonSerializationContext);
    }
}