package javassist.bytecode.analysis;

public class Frame
{
    private Type[] locals;
    private Type[] stack;
    private int top;
    private boolean jsrMerged;
    private boolean retMerged;
    
    public Frame(final int n, final int n2) {
        super();
        this.locals = new Type[n];
        this.stack = new Type[n2];
    }
    
    public Type getLocal(final int n) {
        return this.locals[n];
    }
    
    public void setLocal(final int n, final Type type) {
        this.locals[n] = type;
    }
    
    public Type getStack(final int n) {
        return this.stack[n];
    }
    
    public void setStack(final int n, final Type type) {
        this.stack[n] = type;
    }
    
    public void clearStack() {
        this.top = 0;
    }
    
    public int getTopIndex() {
        return this.top - 1;
    }
    
    public int localsLength() {
        return this.locals.length;
    }
    
    public Type peek() {
        if (this.top < 1) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        return this.stack[this.top - 1];
    }
    
    public Type pop() {
        if (this.top < 1) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        final Type[] stack = this.stack;
        final int top = this.top - 1;
        this.top = top;
        return stack[top];
    }
    
    public void push(final Type type) {
        this.stack[this.top++] = type;
    }
    
    public Frame copy() {
        final Frame frame = new Frame(this.locals.length, this.stack.length);
        System.arraycopy(this.locals, 0, frame.locals, 0, this.locals.length);
        System.arraycopy(this.stack, 0, frame.stack, 0, this.stack.length);
        frame.top = this.top;
        return frame;
    }
    
    public Frame copyStack() {
        final Frame frame = new Frame(this.locals.length, this.stack.length);
        System.arraycopy(this.stack, 0, frame.stack, 0, this.stack.length);
        frame.top = this.top;
        return frame;
    }
    
    public boolean mergeStack(final Frame frame) {
        boolean b = false;
        if (this.top != frame.top) {
            throw new RuntimeException("Operand stacks could not be merged, they are different sizes!");
        }
        for (int i = 0; i < this.top; ++i) {
            if (this.stack[i] != null) {
                final Type type = this.stack[i];
                final Type merge = type.merge(frame.stack[i]);
                if (merge == Type.BOGUS) {
                    throw new RuntimeException("Operand stacks could not be merged due to differing primitive types: pos = " + i);
                }
                this.stack[i] = merge;
                if (!merge.equals(type) || merge.popChanged()) {
                    b = true;
                }
            }
        }
        return b;
    }
    
    public boolean merge(final Frame frame) {
        boolean b = false;
        for (int i = 0; i < this.locals.length; ++i) {
            if (this.locals[i] != null) {
                final Type type = this.locals[i];
                final Type merge = type.merge(frame.locals[i]);
                this.locals[i] = merge;
                if (!merge.equals(type) || merge.popChanged()) {
                    b = true;
                }
            }
            else if (frame.locals[i] != null) {
                this.locals[i] = frame.locals[i];
                b = true;
            }
        }
        return b | this.mergeStack(frame);
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("locals = [");
        for (int i = 0; i < this.locals.length; ++i) {
            sb.append((this.locals[i] == null) ? "empty" : this.locals[i].toString());
            if (i < this.locals.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("] stack = [");
        for (int j = 0; j < this.top; ++j) {
            sb.append(this.stack[j]);
            if (j < this.top - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    boolean isJsrMerged() {
        return this.jsrMerged;
    }
    
    void setJsrMerged(final boolean jsrMerged) {
        this.jsrMerged = jsrMerged;
    }
    
    boolean isRetMerged() {
        return this.retMerged;
    }
    
    void setRetMerged(final boolean retMerged) {
        this.retMerged = retMerged;
    }
}
