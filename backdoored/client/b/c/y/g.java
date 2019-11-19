package r.k.b.c.y;

public class g extends j
{
    public static final boolean sgd;
    public static final int sgs;
    public static final boolean sgl;
    
    public g() {
        super();
    }
    
    @Override
    public String o() {
        return "Emphasize";
    }
    
    @Override
    public String s(String replaceAll) {
        replaceAll = replaceAll.replaceAll(" ", "");
        final StringBuilder sb = new StringBuilder();
        final char[] charArray = replaceAll.toCharArray();
        final int length = charArray.length;
        int n = 0;
        if (n < length) {
            sb.append(Character.toUpperCase(charArray[n])).append(" ");
            ++n;
        }
        return sb.toString();
    }
}
