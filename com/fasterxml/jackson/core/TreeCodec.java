package com.fasterxml.jackson.core;

import java.io.IOException;

public abstract class TreeCodec
{
    public TreeCodec() {
        super();
    }
    
    public abstract <T extends TreeNode> T readTree(final JsonParser p0) throws IOException, JsonProcessingException;
    
    public abstract void writeTree(final JsonGenerator p0, final TreeNode p1) throws IOException, JsonProcessingException;
    
    public abstract TreeNode createArrayNode();
    
    public abstract TreeNode createObjectNode();
    
    public abstract JsonParser treeAsTokens(final TreeNode p0);
}
