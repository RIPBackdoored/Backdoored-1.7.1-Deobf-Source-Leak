package javassist.bytecode.analysis;

import javassist.bytecode.stackmap.BasicBlock;

public static class Node
{
    private Block block;
    private Node parent;
    private Node[] children;
    
    Node(final Block block) {
        super();
        this.block = block;
        this.parent = null;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Node[pos=").append(this.block().position());
        sb.append(", parent=");
        sb.append((this.parent == null) ? "*" : Integer.toString(this.parent.block().position()));
        sb.append(", children{");
        for (int i = 0; i < this.children.length; ++i) {
            sb.append(this.children[i].block().position()).append(", ");
        }
        sb.append("}]");
        return sb.toString();
    }
    
    public Block block() {
        return this.block;
    }
    
    public Node parent() {
        return this.parent;
    }
    
    public int children() {
        return this.children.length;
    }
    
    public Node child(final int n) {
        return this.children[n];
    }
    
    int makeDepth1stTree(final Node parent, final boolean[] array, int depth1stTree, final int[] array2, final Access access) {
        final int index = this.block.index;
        if (array[index]) {
            return depth1stTree;
        }
        array[index] = true;
        this.parent = parent;
        final BasicBlock[] exits = access.exits(this);
        if (exits != null) {
            for (int i = 0; i < exits.length; ++i) {
                depth1stTree = access.node(exits[i]).makeDepth1stTree(this, array, depth1stTree, array2, access);
            }
        }
        array2[index] = depth1stTree++;
        return depth1stTree;
    }
    
    boolean makeDominatorTree(final boolean[] array, final int[] array2, final Access access) {
        final int index = this.block.index;
        if (array[index]) {
            return false;
        }
        array[index] = true;
        boolean b = false;
        final BasicBlock[] exits = access.exits(this);
        if (exits != null) {
            for (int i = 0; i < exits.length; ++i) {
                if (access.node(exits[i]).makeDominatorTree(array, array2, access)) {
                    b = true;
                }
            }
        }
        final BasicBlock[] entrances = access.entrances(this);
        if (entrances != null) {
            for (int j = 0; j < entrances.length; ++j) {
                if (this.parent != null) {
                    final Node ancestor = getAncestor(this.parent, access.node(entrances[j]), array2);
                    if (ancestor != this.parent) {
                        this.parent = ancestor;
                        b = true;
                    }
                }
            }
        }
        return b;
    }
    
    private static Node getAncestor(Node parent, Node parent2, final int[] array) {
        while (parent != parent2) {
            if (array[parent.block.index] < array[parent2.block.index]) {
                parent = parent.parent;
            }
            else {
                parent2 = parent2.parent;
            }
            if (parent == null || parent2 == null) {
                return null;
            }
        }
        return parent;
    }
    
    private static void setChildren(final Node[] array) {
        final int length = array.length;
        final int[] array2 = new int[length];
        for (int i = 0; i < length; ++i) {
            array2[i] = 0;
        }
        for (int j = 0; j < length; ++j) {
            final Node parent = array[j].parent;
            if (parent != null) {
                final int[] array3 = array2;
                final int index = parent.block.index;
                ++array3[index];
            }
        }
        for (int k = 0; k < length; ++k) {
            array[k].children = new Node[array2[k]];
        }
        for (int l = 0; l < length; ++l) {
            array2[l] = 0;
        }
        for (final Node node : array) {
            final Node parent2 = node.parent;
            if (parent2 != null) {
                parent2.children[array2[parent2.block.index]++] = node;
            }
        }
    }
    
    static /* synthetic */ Block access$200(final Node node) {
        return node.block;
    }
    
    static /* synthetic */ void access$300(final Node[] children) {
        setChildren(children);
    }
}
