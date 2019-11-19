package org.yaml.snakeyaml.parser;

import org.yaml.snakeyaml.tokens.Token;
import org.yaml.snakeyaml.events.Event;

private class ParseBlockSequenceFirstEntry implements Production
{
    final /* synthetic */ ParserImpl this$0;
    
    private ParseBlockSequenceFirstEntry(final ParserImpl this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public Event produce() {
        final Token token = this.this$0.scanner.getToken();
        ParserImpl.access$1100(this.this$0).push(token.getStartMark());
        return this.this$0.new ParseBlockSequenceEntry().produce();
    }
    
    ParseBlockSequenceFirstEntry(final ParserImpl x0, final ParserImpl$1 x1) {
        this(x0);
    }
}
