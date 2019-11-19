package javassist.bytecode;

public static class Iterator
{
    private String desc;
    private int index;
    private int curPos;
    private boolean param;
    
    public Iterator(final String desc) {
        super();
        this.desc = desc;
        final int n = 0;
        this.curPos = n;
        this.index = n;
        this.param = false;
    }
    
    public boolean hasNext() {
        return this.index < this.desc.length();
    }
    
    public boolean isParameter() {
        return this.param;
    }
    
    public char currentChar() {
        return this.desc.charAt(this.curPos);
    }
    
    public boolean is2byte() {
        final char currentChar = this.currentChar();
        return currentChar == 'D' || currentChar == 'J';
    }
    
    public int next() {
        int index = this.index;
        char c = this.desc.charAt(index);
        if (c == '(') {
            ++this.index;
            c = this.desc.charAt(++index);
            this.param = true;
        }
        if (c == ')') {
            ++this.index;
            c = this.desc.charAt(++index);
            this.param = false;
        }
        while (c == '[') {
            c = this.desc.charAt(++index);
        }
        if (c == 'L') {
            index = this.desc.indexOf(59, index) + 1;
            if (index <= 0) {
                throw new IndexOutOfBoundsException("bad descriptor");
            }
        }
        else {
            ++index;
        }
        this.curPos = this.index;
        this.index = index;
        return this.curPos;
    }
}
