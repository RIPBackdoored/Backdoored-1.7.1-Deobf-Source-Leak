package r.k.b.c.y;

import org.apache.commons.lang3.ArrayUtils;

public class u extends j
{
    private static final char[] skb;
    public static final boolean skw;
    public static final int skg;
    public static final boolean sku;
    
    public u() {
        super();
    }
    
    @Override
    public String o() {
        return "Fancy";
    }
    
    @Override
    public String s(final String s) {
        final StringBuilder sb = new StringBuilder();
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        if (n < length) {
            final char valueToFind = charArray[n];
            if (valueToFind < '!' || valueToFind > '\u0080') {
                throw null;
            }
            if (ArrayUtils.contains(u.skb, valueToFind)) {
                sb.append(valueToFind);
            }
            sb.append(Character.toChars(valueToFind + '\ufee0'));
            ++n;
        }
        return sb.toString();
    }
    
    static {
        skb = new char[] { '(', ')', '{', '}', '[', ']', '|' };
    }
}
