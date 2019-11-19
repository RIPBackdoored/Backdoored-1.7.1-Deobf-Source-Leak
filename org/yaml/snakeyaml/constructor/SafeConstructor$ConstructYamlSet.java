package org.yaml.snakeyaml.constructor;

import org.yaml.snakeyaml.error.YAMLException;
import java.util.Set;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;

public class ConstructYamlSet implements Construct
{
    final /* synthetic */ SafeConstructor this$0;
    
    public ConstructYamlSet(final SafeConstructor this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public Object construct(final Node node) {
        if (node.isTwoStepsConstruction()) {
            return this.this$0.createDefaultSet();
        }
        return this.this$0.constructSet((MappingNode)node);
    }
    
    @Override
    public void construct2ndStep(final Node node, final Object object) {
        if (node.isTwoStepsConstruction()) {
            this.this$0.constructSet2ndStep((MappingNode)node, (Set<Object>)object);
            return;
        }
        throw new YAMLException("Unexpected recursive set structure. Node: " + node);
    }
}
