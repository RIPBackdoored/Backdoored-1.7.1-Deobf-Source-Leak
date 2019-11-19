package javassist.bytecode;

private static class Cursor
{
    int position;
    
    private Cursor() {
        super();
        this.position = 0;
    }
    
    int indexOf(final String s, final int n) throws BadBytecode {
        final int index = s.indexOf(n, this.position);
        if (index < 0) {
            throw SignatureAttribute.access$000(s);
        }
        this.position = index + 1;
        return index;
    }
    
    Cursor(final SignatureAttribute$1 object) {
        this();
    }
}
