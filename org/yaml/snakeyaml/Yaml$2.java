package org.yaml.snakeyaml;

import org.yaml.snakeyaml.composer.Composer;
import org.yaml.snakeyaml.nodes.Node;
import java.util.Iterator;

class Yaml$2 implements Iterator<Node> {
    final /* synthetic */ Composer val$composer;
    final /* synthetic */ Yaml this$0;
    
    Yaml$2(final Yaml this$0, final Composer val$composer) {
        this.this$0 = this$0;
        this.val$composer = val$composer;
        super();
    }
    
    @Override
    public boolean hasNext() {
        return this.val$composer.checkNode();
    }
    
    @Override
    public Node next() {
        return this.val$composer.getNode();
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public /* bridge */ Object next() {
        return this.next();
    }
}