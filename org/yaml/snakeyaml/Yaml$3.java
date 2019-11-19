package org.yaml.snakeyaml;

import org.yaml.snakeyaml.parser.Parser;
import org.yaml.snakeyaml.events.Event;
import java.util.Iterator;

class Yaml$3 implements Iterator<Event> {
    final /* synthetic */ Parser val$parser;
    final /* synthetic */ Yaml this$0;
    
    Yaml$3(final Yaml this$0, final Parser val$parser) {
        this.this$0 = this$0;
        this.val$parser = val$parser;
        super();
    }
    
    @Override
    public boolean hasNext() {
        return this.val$parser.peekEvent() != null;
    }
    
    @Override
    public Event next() {
        return this.val$parser.getEvent();
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public /* bridge */ Object next() {
        return this.next();
    }
}