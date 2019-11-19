package org.yaml.snakeyaml;

import java.io.IOException;
import java.util.ArrayList;
import org.yaml.snakeyaml.events.Event;
import java.util.List;
import org.yaml.snakeyaml.emitter.Emitable;

private static class SilentEmitter implements Emitable
{
    private List<Event> events;
    
    private SilentEmitter() {
        super();
        this.events = new ArrayList<Event>(100);
    }
    
    public List<Event> getEvents() {
        return this.events;
    }
    
    @Override
    public void emit(final Event event) throws IOException {
        this.events.add(event);
    }
    
    SilentEmitter(final Yaml$1 iterator) {
        this();
    }
}
