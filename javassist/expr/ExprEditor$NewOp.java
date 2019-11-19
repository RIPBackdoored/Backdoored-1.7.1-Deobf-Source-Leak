package javassist.expr;

static final class NewOp
{
    NewOp next;
    int pos;
    String type;
    
    NewOp(final NewOp next, final int pos, final String type) {
        super();
        this.next = next;
        this.pos = pos;
        this.type = type;
    }
}
