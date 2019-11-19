package org.yaml.snakeyaml.representer;

import org.yaml.snakeyaml.nodes.Tag;
import java.util.Iterator;
import org.yaml.snakeyaml.nodes.Node;

protected class RepresentIterator implements Represent
{
    final /* synthetic */ SafeRepresenter this$0;
    
    protected RepresentIterator(final SafeRepresenter this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public Node representData(final Object data) {
        final Iterator<Object> iter = (Iterator<Object>)data;
        return this.this$0.representSequence(this.this$0.getTag(data.getClass(), Tag.SEQ), new IteratorWrapper(iter), null);
    }
}
