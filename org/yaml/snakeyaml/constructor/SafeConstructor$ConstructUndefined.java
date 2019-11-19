package org.yaml.snakeyaml.constructor;

import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.nodes.Node;

public static final class ConstructUndefined extends AbstractConstruct
{
    public ConstructUndefined() {
        super();
    }
    
    @Override
    public Object construct(final Node node) {
        throw new ConstructorException(null, null, "could not determine a constructor for the tag " + node.getTag(), node.getStartMark());
    }
}
