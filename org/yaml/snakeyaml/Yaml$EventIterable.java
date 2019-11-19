package org.yaml.snakeyaml;

import java.util.Iterator;
import org.yaml.snakeyaml.events.Event;

private static class EventIterable implements Iterable<Event>
{
    private Iterator<Event> iterator;
    
    public EventIterable(final Iterator<Event> iterator) {
        super();
        this.iterator = iterator;
    }
    
    @Override
    public Iterator<Event> iterator() {
        return this.iterator;
    }
}
