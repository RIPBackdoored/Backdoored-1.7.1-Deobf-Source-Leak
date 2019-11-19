package org.yaml.snakeyaml;

import java.util.Iterator;
import org.yaml.snakeyaml.nodes.Node;

private static class NodeIterable implements Iterable<Node>
{
    private Iterator<Node> iterator;
    
    public NodeIterable(final Iterator<Node> iterator) {
        super();
        this.iterator = iterator;
    }
    
    @Override
    public Iterator<Node> iterator() {
        return this.iterator;
    }
}
