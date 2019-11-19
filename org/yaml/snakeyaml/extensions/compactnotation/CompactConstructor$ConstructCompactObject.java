package org.yaml.snakeyaml.extensions.compactnotation;

import org.yaml.snakeyaml.nodes.ScalarNode;
import java.util.List;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.constructor.Constructor;

public class ConstructCompactObject extends ConstructMapping
{
    final /* synthetic */ CompactConstructor this$0;
    
    public ConstructCompactObject(final CompactConstructor compactConstructor) {
        this.this$0 = compactConstructor;
        compactConstructor.super();
    }
    
    @Override
    public void construct2ndStep(final Node node, final Object object) {
        final Node valueNode = ((MappingNode)node).getValue().iterator().next().getValueNode();
        if (valueNode instanceof MappingNode) {
            valueNode.setType(object.getClass());
            this.constructJavaBean2ndStep((MappingNode)valueNode, object);
        }
        else {
            this.this$0.applySequence(object, CompactConstructor.access$000(this.this$0, (SequenceNode)valueNode));
        }
    }
    
    @Override
    public Object construct(final Node node) {
        ScalarNode scalarNode;
        if (node instanceof MappingNode) {
            final NodeTuple nodeTuple = ((MappingNode)node).getValue().iterator().next();
            node.setTwoStepsConstruction(true);
            scalarNode = (ScalarNode)nodeTuple.getKeyNode();
        }
        else {
            scalarNode = (ScalarNode)node;
        }
        final CompactData compactData = this.this$0.getCompactData(scalarNode.getValue());
        if (compactData == null) {
            return CompactConstructor.access$100(this.this$0, scalarNode);
        }
        return this.this$0.constructCompactFormat(scalarNode, compactData);
    }
}
