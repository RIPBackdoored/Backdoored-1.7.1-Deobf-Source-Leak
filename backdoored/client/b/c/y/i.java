package r.k.b.c.y;

import java.util.Map;

public class i extends j
{
    private Map<String, String> lx;
    public static final boolean lk;
    public static final int lq;
    public static final boolean lv;
    
    public i() {
        super();
        this.lx = new i$s(this);
    }
    
    @Override
    public String o() {
        return "Chav";
    }
    
    @Override
    public String s(final String s) {
        final StringBuilder sb = new StringBuilder();
        final String[] split = s.split(" ");
        final int length = split.length;
        int n = 0;
        if (n < length) {
            final String s2 = split[n];
            sb.append(this.lx.getOrDefault(s2.toLowerCase(), s2)).append(" ");
            ++n;
        }
        return sb.toString();
    }
}
