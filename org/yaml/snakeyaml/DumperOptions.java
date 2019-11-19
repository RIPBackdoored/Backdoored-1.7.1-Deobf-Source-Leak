package org.yaml.snakeyaml;

import org.yaml.snakeyaml.serializer.NumberAnchorGenerator;
import org.yaml.snakeyaml.serializer.AnchorGenerator;
import java.util.Map;
import java.util.TimeZone;

public class DumperOptions
{
    private ScalarStyle defaultStyle;
    private FlowStyle defaultFlowStyle;
    private boolean canonical;
    private boolean allowUnicode;
    private boolean allowReadOnlyProperties;
    private int indent;
    private int indicatorIndent;
    private int bestWidth;
    private boolean splitLines;
    private LineBreak lineBreak;
    private boolean explicitStart;
    private boolean explicitEnd;
    private TimeZone timeZone;
    private Version version;
    private Map<String, String> tags;
    private Boolean prettyFlow;
    private AnchorGenerator anchorGenerator;
    
    public DumperOptions() {
        super();
        this.defaultStyle = ScalarStyle.PLAIN;
        this.defaultFlowStyle = FlowStyle.AUTO;
        this.canonical = false;
        this.allowUnicode = true;
        this.allowReadOnlyProperties = false;
        this.indent = 2;
        this.indicatorIndent = 0;
        this.bestWidth = 80;
        this.splitLines = true;
        this.lineBreak = LineBreak.UNIX;
        this.explicitStart = false;
        this.explicitEnd = false;
        this.timeZone = null;
        this.version = null;
        this.tags = null;
        this.prettyFlow = false;
        this.anchorGenerator = new NumberAnchorGenerator(0);
    }
    
    public boolean isAllowUnicode() {
        return this.allowUnicode;
    }
    
    public ScalarStyle getDefaultScalarStyle() {
        return this.defaultStyle;
    }
    
    public int getIndent() {
        return this.indent;
    }
    
    public int getIndicatorIndent() {
        return this.indicatorIndent;
    }
    
    public Version getVersion() {
        return this.version;
    }
    
    public boolean isCanonical() {
        return this.canonical;
    }
    
    public boolean isPrettyFlow() {
        return this.prettyFlow;
    }
    
    public int getWidth() {
        return this.bestWidth;
    }
    
    public boolean getSplitLines() {
        return this.splitLines;
    }
    
    public LineBreak getLineBreak() {
        return this.lineBreak;
    }
    
    public void setDefaultFlowStyle(final FlowStyle defaultFlowStyle) {
        if (defaultFlowStyle == null) {
            throw new NullPointerException("Use FlowStyle enum.");
        }
        this.defaultFlowStyle = defaultFlowStyle;
    }
    
    public FlowStyle getDefaultFlowStyle() {
        return this.defaultFlowStyle;
    }
    
    public boolean isExplicitStart() {
        return this.explicitStart;
    }
    
    public boolean isExplicitEnd() {
        return this.explicitEnd;
    }
    
    public Map<String, String> getTags() {
        return this.tags;
    }
    
    public boolean isAllowReadOnlyProperties() {
        return this.allowReadOnlyProperties;
    }
    
    public TimeZone getTimeZone() {
        return this.timeZone;
    }
    
    public AnchorGenerator getAnchorGenerator() {
        return this.anchorGenerator;
    }
}
