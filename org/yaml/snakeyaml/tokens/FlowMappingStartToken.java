package org.yaml.snakeyaml.tokens;

import org.yaml.snakeyaml.error.Mark;

public final class FlowMappingStartToken extends Token
{
    public FlowMappingStartToken(final Mark startMark, final Mark endMark) {
        super(startMark, endMark);
    }
    
    @Override
    public ID getTokenId() {
        return ID.FlowMappingStart;
    }
}
