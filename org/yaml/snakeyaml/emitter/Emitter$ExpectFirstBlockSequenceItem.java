package org.yaml.snakeyaml.emitter;

import java.io.IOException;

private class ExpectFirstBlockSequenceItem implements EmitterState
{
    final /* synthetic */ Emitter this$0;
    
    private ExpectFirstBlockSequenceItem(final Emitter this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public void expect() throws IOException {
        this.this$0.new ExpectBlockSequenceItem(true).expect();
    }
    
    ExpectFirstBlockSequenceItem(final Emitter x0, final Emitter$1 x1) {
        this(x0);
    }
}
