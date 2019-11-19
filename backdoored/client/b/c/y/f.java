package r.k.b.c.y;

import java.util.Map;

public class f extends j
{
    private Map<String, String> swl;
    public static final boolean swy;
    public static final int swx;
    public static final boolean swk;
    
    public f() {
        super();
        this.swl = new f$s(this);
    }
    
    @Override
    public String o() {
        return "L33t";
    }
    
    @Override
    public String s(final String s) {
        final StringBuilder sb = new StringBuilder();
        final String[] split = s.split("");
        final int length = split.length;
        int n = 0;
        final String s2 = split[n];
        sb.append(this.swl.getOrDefault(s2.toLowerCase(), s2));
        ++n;
        return sb.toString();
    }
}
