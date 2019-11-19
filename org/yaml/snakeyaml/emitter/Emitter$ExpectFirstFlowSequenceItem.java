package org.yaml.snakeyaml.emitter;

import java.io.IOException;
import org.yaml.snakeyaml.events.SequenceEndEvent;

private class ExpectFirstFlowSequenceItem implements EmitterState
{
    final /* synthetic */ Emitter this$0;
    
    private ExpectFirstFlowSequenceItem(final Emitter this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public void expect() throws IOException {
        if (Emitter.access$100(this.this$0) instanceof SequenceEndEvent) {
            Emitter.access$1802(this.this$0, Emitter.access$1900(this.this$0).pop());
            Emitter.access$2010(this.this$0);
            this.this$0.writeIndicator("]", false, false, false);
            Emitter.access$202(this.this$0, Emitter.access$1500(this.this$0).pop());
        }
        else {
            if (Emitter.access$1000(this.this$0) || (Emitter.access$2100(this.this$0) > Emitter.access$2200(this.this$0) && Emitter.access$2300(this.this$0)) || Emitter.access$2400(this.this$0)) {
                this.this$0.writeIndent();
            }
            Emitter.access$1500(this.this$0).push(this.this$0.new ExpectFlowSequenceItem());
            Emitter.access$1600(this.this$0, false, false, false);
        }
    }
    
    ExpectFirstFlowSequenceItem(final Emitter x0, final Emitter$1 x1) {
        this(x0);
    }
}
