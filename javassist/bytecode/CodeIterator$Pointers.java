package javassist.bytecode;

static class Pointers
{
    int cursor;
    int mark0;
    int mark;
    ExceptionTable etable;
    LineNumberAttribute line;
    LocalVariableAttribute vars;
    LocalVariableAttribute types;
    StackMapTable stack;
    StackMap stack2;
    
    Pointers(final int cursor, final int mark, final int mark2, final ExceptionTable etable, final CodeAttribute codeAttribute) {
        super();
        this.cursor = cursor;
        this.mark = mark;
        this.mark0 = mark2;
        this.etable = etable;
        this.line = (LineNumberAttribute)codeAttribute.getAttribute("LineNumberTable");
        this.vars = (LocalVariableAttribute)codeAttribute.getAttribute("LocalVariableTable");
        this.types = (LocalVariableAttribute)codeAttribute.getAttribute("LocalVariableTypeTable");
        this.stack = (StackMapTable)codeAttribute.getAttribute("StackMapTable");
        this.stack2 = (StackMap)codeAttribute.getAttribute("StackMap");
    }
    
    void shiftPc(final int n, final int n2, final boolean b) throws BadBytecode {
        if (n < this.cursor || (n == this.cursor && b)) {
            this.cursor += n2;
        }
        if (n < this.mark || (n == this.mark && b)) {
            this.mark += n2;
        }
        if (n < this.mark0 || (n == this.mark0 && b)) {
            this.mark0 += n2;
        }
        this.etable.shiftPc(n, n2, b);
        if (this.line != null) {
            this.line.shiftPc(n, n2, b);
        }
        if (this.vars != null) {
            this.vars.shiftPc(n, n2, b);
        }
        if (this.types != null) {
            this.types.shiftPc(n, n2, b);
        }
        if (this.stack != null) {
            this.stack.shiftPc(n, n2, b);
        }
        if (this.stack2 != null) {
            this.stack2.shiftPc(n, n2, b);
        }
    }
    
    void shiftForSwitch(final int n, final int n2) throws BadBytecode {
        if (this.stack != null) {
            this.stack.shiftForSwitch(n, n2);
        }
        if (this.stack2 != null) {
            this.stack2.shiftForSwitch(n, n2);
        }
    }
}
