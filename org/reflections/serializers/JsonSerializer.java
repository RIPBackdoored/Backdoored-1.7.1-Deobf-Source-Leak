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
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import java.lang.reflect.Type;
import com.google.common.collect.Multimap;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import com.google.common.io.Files;
import java.nio.charset.Charset;
import org.reflections.util.Utils;
import java.io.File;
import java.io.Reader;
import java.io.InputStreamReader;
import org.reflections.Reflections;
import java.io.InputStream;
import com.google.gson.Gson;

public class JsonSerializer implements Serializer
{
    private Gson gson;
    
    public JsonSerializer() {
        super();
    }
    
    @Override
    public Reflections read(final InputStream inputStream) {
        return this.getGson().<Reflections>fromJson(new InputStreamReader(inputStream), Reflections.class);
    }
    
    @Override
    public File save(final Reflections reflections, final String s) {
        try {
            final File prepareFile = Utils.prepareFile(s);
            Files.write((CharSequence)this.toString(reflections), prepareFile, Charset.defaultCharset());
            return prepareFile;
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public String toString(final Reflections src) {
        return this.getGson().toJson(src);
    }
    
    private Gson getGson() {
        if (this.gson == null) {
            this.gson = new GsonBuilder().registerTypeAdapter(Multimap.class, new com.google.gson.JsonSerializer<Multimap>() {
                final /* synthetic */ JsonSerializer this$0;
                
                JsonSerializer$2() {
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
            }).registerTypeAdapter(Multimap.class, new JsonDeserializer<Multimap>() {
                final /* synthetic */ JsonSerializer this$0;
                
                JsonSerializer$1() {
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
            }).setPrettyPrinting().create();
        }
        return this.gson;
    }
}
