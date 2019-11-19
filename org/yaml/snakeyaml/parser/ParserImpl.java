package org.yaml.snakeyaml.parser;

import org.yaml.snakeyaml.events.MappingEndEvent;
import org.yaml.snakeyaml.events.SequenceEndEvent;
import org.yaml.snakeyaml.tokens.BlockEntryToken;
import org.yaml.snakeyaml.events.DocumentEndEvent;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.events.StreamEndEvent;
import org.yaml.snakeyaml.tokens.StreamEndToken;
import org.yaml.snakeyaml.events.DocumentStartEvent;
import org.yaml.snakeyaml.events.StreamStartEvent;
import org.yaml.snakeyaml.tokens.StreamStartToken;
import org.yaml.snakeyaml.tokens.TagTuple;
import org.yaml.snakeyaml.events.MappingStartEvent;
import org.yaml.snakeyaml.events.ScalarEvent;
import org.yaml.snakeyaml.events.ImplicitTuple;
import org.yaml.snakeyaml.tokens.ScalarToken;
import org.yaml.snakeyaml.events.SequenceStartEvent;
import org.yaml.snakeyaml.tokens.TagToken;
import org.yaml.snakeyaml.tokens.AnchorToken;
import org.yaml.snakeyaml.events.AliasEvent;
import org.yaml.snakeyaml.tokens.AliasToken;
import java.util.Iterator;
import java.util.List;
import org.yaml.snakeyaml.tokens.DirectiveToken;
import org.yaml.snakeyaml.tokens.Token;
import org.yaml.snakeyaml.DumperOptions;
import java.util.HashMap;
import org.yaml.snakeyaml.scanner.ScannerImpl;
import org.yaml.snakeyaml.reader.StreamReader;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.util.ArrayStack;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.scanner.Scanner;
import java.util.Map;

public class ParserImpl implements Parser
{
    private static final Map<String, String> DEFAULT_TAGS;
    protected final Scanner scanner;
    private Event currentEvent;
    private final ArrayStack<Production> states;
    private final ArrayStack<Mark> marks;
    private Production state;
    private VersionTagsTuple directives;
    
    public ParserImpl(final StreamReader reader) {
        this(new ScannerImpl(reader));
    }
    
    public ParserImpl(final Scanner scanner) {
        super();
        this.scanner = scanner;
        this.currentEvent = null;
        this.directives = new VersionTagsTuple(null, new HashMap<String, String>(ParserImpl.DEFAULT_TAGS));
        this.states = new ArrayStack<Production>(100);
        this.marks = new ArrayStack<Mark>(10);
        this.state = new ParseStreamStart(this);
    }
    
    @Override
    public boolean checkEvent(final Event.ID choice) {
        this.peekEvent();
        return this.currentEvent != null && this.currentEvent.is(choice);
    }
    
    @Override
    public Event peekEvent() {
        if (this.currentEvent == null && this.state != null) {
            this.currentEvent = this.state.produce();
        }
        return this.currentEvent;
    }
    
    @Override
    public Event getEvent() {
        this.peekEvent();
        final Event value = this.currentEvent;
        this.currentEvent = null;
        return value;
    }
    
    private VersionTagsTuple processDirectives() {
        DumperOptions.Version yamlVersion = null;
        final HashMap<String, String> tagHandles = new HashMap<String, String>();
        while (this.scanner.checkToken(Token.ID.Directive)) {
            final DirectiveToken token = (DirectiveToken)this.scanner.getToken();
            if (token.getName().equals("YAML")) {
                if (yamlVersion != null) {
                    throw new ParserException(null, null, "found duplicate YAML directive", token.getStartMark());
                }
                final List<Integer> value = token.getValue();
                final Integer major = value.get(0);
                if (major != 1) {
                    throw new ParserException(null, null, "found incompatible YAML document (version 1.* is required)", token.getStartMark());
                }
                final Integer minor = value.get(1);
                switch (minor) {
                    case 0:
                        yamlVersion = DumperOptions.Version.V1_0;
                        continue;
                    default:
                        yamlVersion = DumperOptions.Version.V1_1;
                        continue;
                }
            }
            else {
                if (!token.getName().equals("TAG")) {
                    continue;
                }
                final List<String> value2 = token.getValue();
                final String handle = value2.get(0);
                final String prefix = value2.get(1);
                if (tagHandles.containsKey(handle)) {
                    throw new ParserException(null, null, "duplicate tag handle " + handle, token.getStartMark());
                }
                tagHandles.put(handle, prefix);
            }
        }
        if (yamlVersion != null || !tagHandles.isEmpty()) {
            for (final String key : ParserImpl.DEFAULT_TAGS.keySet()) {
                if (!tagHandles.containsKey(key)) {
                    tagHandles.put(key, ParserImpl.DEFAULT_TAGS.get(key));
                }
            }
            this.directives = new VersionTagsTuple(yamlVersion, tagHandles);
        }
        return this.directives;
    }
    
    private Event parseFlowNode() {
        return this.parseNode(false, false);
    }
    
    private Event parseBlockNodeOrIndentlessSequence() {
        return this.parseNode(true, true);
    }
    
    private Event parseNode(final boolean block, final boolean indentlessSequence) {
        Mark startMark = null;
        Mark endMark = null;
        Mark tagMark = null;
        Event event;
        if (this.scanner.checkToken(Token.ID.Alias)) {
            final AliasToken token = (AliasToken)this.scanner.getToken();
            event = new AliasEvent(token.getValue(), token.getStartMark(), token.getEndMark());
            this.state = this.states.pop();
        }
        else {
            String anchor = null;
            TagTuple tagTokenTag = null;
            if (this.scanner.checkToken(Token.ID.Anchor)) {
                final AnchorToken token2 = (AnchorToken)this.scanner.getToken();
                startMark = token2.getStartMark();
                endMark = token2.getEndMark();
                anchor = token2.getValue();
                if (this.scanner.checkToken(Token.ID.Tag)) {
                    final TagToken tagToken = (TagToken)this.scanner.getToken();
                    tagMark = tagToken.getStartMark();
                    endMark = tagToken.getEndMark();
                    tagTokenTag = tagToken.getValue();
                }
            }
            else if (this.scanner.checkToken(Token.ID.Tag)) {
                final TagToken tagToken2 = (TagToken)this.scanner.getToken();
                startMark = (tagMark = tagToken2.getStartMark());
                endMark = tagToken2.getEndMark();
                tagTokenTag = tagToken2.getValue();
                if (this.scanner.checkToken(Token.ID.Anchor)) {
                    final AnchorToken token3 = (AnchorToken)this.scanner.getToken();
                    endMark = token3.getEndMark();
                    anchor = token3.getValue();
                }
            }
            String tag = null;
            if (tagTokenTag != null) {
                final String handle = tagTokenTag.getHandle();
                final String suffix = tagTokenTag.getSuffix();
                if (handle != null) {
                    if (!this.directives.getTags().containsKey(handle)) {
                        throw new ParserException("while parsing a node", startMark, "found undefined tag handle " + handle, tagMark);
                    }
                    tag = this.directives.getTags().get(handle) + suffix;
                }
                else {
                    tag = suffix;
                }
            }
            if (startMark == null) {
                startMark = (endMark = this.scanner.peekToken().getStartMark());
            }
            event = null;
            final boolean implicit = tag == null || tag.equals("!");
            if (indentlessSequence && this.scanner.checkToken(Token.ID.BlockEntry)) {
                endMark = this.scanner.peekToken().getEndMark();
                event = new SequenceStartEvent(anchor, tag, implicit, startMark, endMark, Boolean.FALSE);
                this.state = new ParseIndentlessSequenceEntry(this);
            }
            else if (this.scanner.checkToken(Token.ID.Scalar)) {
                final ScalarToken token4 = (ScalarToken)this.scanner.getToken();
                endMark = token4.getEndMark();
                ImplicitTuple implicitValues;
                if ((token4.getPlain() && tag == null) || "!".equals(tag)) {
                    implicitValues = new ImplicitTuple(true, false);
                }
                else if (tag == null) {
                    implicitValues = new ImplicitTuple(false, true);
                }
                else {
                    implicitValues = new ImplicitTuple(false, false);
                }
                event = new ScalarEvent(anchor, tag, implicitValues, token4.getValue(), startMark, endMark, token4.getStyle());
                this.state = this.states.pop();
            }
            else if (this.scanner.checkToken(Token.ID.FlowSequenceStart)) {
                endMark = this.scanner.peekToken().getEndMark();
                event = new SequenceStartEvent(anchor, tag, implicit, startMark, endMark, Boolean.TRUE);
                this.state = new ParseFlowSequenceFirstEntry(this);
            }
            else if (this.scanner.checkToken(Token.ID.FlowMappingStart)) {
                endMark = this.scanner.peekToken().getEndMark();
                event = new MappingStartEvent(anchor, tag, implicit, startMark, endMark, Boolean.TRUE);
                this.state = new ParseFlowMappingFirstKey(this);
            }
            else if (block && this.scanner.checkToken(Token.ID.BlockSequenceStart)) {
                endMark = this.scanner.peekToken().getStartMark();
                event = new SequenceStartEvent(anchor, tag, implicit, startMark, endMark, Boolean.FALSE);
                this.state = new ParseBlockSequenceFirstEntry(this);
            }
            else if (block && this.scanner.checkToken(Token.ID.BlockMappingStart)) {
                endMark = this.scanner.peekToken().getStartMark();
                event = new MappingStartEvent(anchor, tag, implicit, startMark, endMark, Boolean.FALSE);
                this.state = new ParseBlockMappingFirstKey(this);
            }
            else {
                if (anchor == null && tag == null) {
                    String node;
                    if (block) {
                        node = "block";
                    }
                    else {
                        node = "flow";
                    }
                    final Token token5 = this.scanner.peekToken();
                    throw new ParserException("while parsing a " + node + " node", startMark, "expected the node content, but found " + token5.getTokenId(), token5.getStartMark());
                }
                event = new ScalarEvent(anchor, tag, new ImplicitTuple(implicit, false), "", startMark, endMark, '\0');
                this.state = this.states.pop();
            }
        }
        return event;
    }
    
    private Event processEmptyScalar(final Mark mark) {
        return new ScalarEvent(null, null, new ImplicitTuple(true, false), "", mark, mark, '\0');
    }
    
    static /* synthetic */ Production access$102(final ParserImpl x0, final Production x1) {
        return x0.state = x1;
    }
    
    static /* synthetic */ VersionTagsTuple access$302(final ParserImpl x0, final VersionTagsTuple x1) {
        return x0.directives = x1;
    }
    
    static /* synthetic */ Map access$400() {
        return ParserImpl.DEFAULT_TAGS;
    }
    
    static /* synthetic */ ArrayStack access$600(final ParserImpl x0) {
        return x0.states;
    }
    
    static /* synthetic */ VersionTagsTuple access$900(final ParserImpl x0) {
        return x0.processDirectives();
    }
    
    static /* synthetic */ ArrayStack access$1100(final ParserImpl x0) {
        return x0.marks;
    }
    
    static /* synthetic */ Event access$1200(final ParserImpl x0, final Mark x1) {
        return x0.processEmptyScalar(x1);
    }
    
    static /* synthetic */ Event access$1300(final ParserImpl x0, final boolean x1, final boolean x2) {
        return x0.parseNode(x1, x2);
    }
    
    static /* synthetic */ Event access$2200(final ParserImpl x0) {
        return x0.parseBlockNodeOrIndentlessSequence();
    }
    
    static /* synthetic */ Event access$2400(final ParserImpl x0) {
        return x0.parseFlowNode();
    }
    
    static {
        (DEFAULT_TAGS = new HashMap<String, String>()).put("!", "!");
        ParserImpl.DEFAULT_TAGS.put("!!", "tag:yaml.org,2002:");
    }
}
