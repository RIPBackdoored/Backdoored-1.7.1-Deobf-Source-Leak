package org.yaml.snakeyaml.representer;

import java.util.List;
import org.yaml.snakeyaml.nodes.Tag;
import java.util.Arrays;
import org.yaml.snakeyaml.nodes.Node;

protected class RepresentArray implements Represent
{
    final /* synthetic */ SafeRepresenter this$0;
    
    protected RepresentArray(final SafeRepresenter this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public Node representData(final Object data) {
        final Object[] array = (Object[])data;
        final List<Object> list = Arrays.<Object>asList(array);
        return this.this$0.representSequence(Tag.SEQ, list, null);
    }
}
