package r.k.b.c.y;

public class c extends j
{
    public static final boolean suz;
    public static final int suc;
    public static final boolean sua;
    
    public c() {
        super();
    }
    
    @Override
    public String o() {
        return "JustLearntEngrish";
    }
    
    @Override
    public String s(final String s) {
        final StringBuilder sb = new StringBuilder();
        final String[] split = s.split(" ");
        final int length = split.length;
        int n = 0;
        if (n < length) {
            final String s2 = split[n];
            sb.append(s2.substring(0, 1).toUpperCase()).append(s2.substring(1)).append(" ");
            ++n;
        }
        return sb.toString();
    }
}
