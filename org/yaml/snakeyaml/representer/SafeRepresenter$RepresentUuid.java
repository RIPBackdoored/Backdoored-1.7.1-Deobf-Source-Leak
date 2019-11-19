package org.yaml.snakeyaml.representer;

import org.yaml.snakeyaml.nodes.Tag;
import java.util.UUID;
import org.yaml.snakeyaml.nodes.Node;

protected class RepresentUuid implements Represent
{
    final /* synthetic */ SafeRepresenter this$0;
    
    protected RepresentUuid(final SafeRepresenter this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public Node representData(final Object data) {
        return this.this$0.representScalar(this.this$0.getTag(data.getClass(), new Tag(UUID.class)), data.toString());
    }
}
