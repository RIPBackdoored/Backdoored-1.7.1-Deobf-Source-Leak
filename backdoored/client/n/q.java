package r.k.n;

import r.k.b.c.h;
import r.k.b.i.p;

public class q extends c
{
    private String[] skz;
    public static final boolean skc;
    public static final int ska;
    public static final boolean skm;
    
    public q() {
        super(new String[] { "friend", "friends", "f" });
        this.skz = new String[] { "add", "del", "list" };
    }
    
    @Override
    public boolean d(final String[] array) {
        if (!this.d(array, this.skz, "name")) {
            return false;
        }
        if (array[0].equals("add") && !array[array.length - 1].equals("add")) {
            if (!p.si(array[1])) {
                p.st(array[1]);
                h.o("Added '" + array[1] + "' to your friends!", "green");
            }
            h.o("'" + array[1] + "' was already a friend", "red");
            return true;
        }
        if (array[0].equals("del") && !array[array.length - 1].equals("del")) {
            if (p.si(array[1])) {
                p.sn(array[1]);
                h.o("Removed '" + array[1] + "' from your friends!", "green");
            }
            h.o("'" + array[1] + "' wasnt a friend", "red");
            return true;
        }
        if (array[0].equals("list")) {
            final StringBuilder sb = new StringBuilder("Friends: ");
            int n = 0;
            if (n <= p.kb().size() - 1) {
                if (n == p.kb().size() - 1) {
                    sb.append(p.kb().get(n));
                }
                sb.append(p.kb().get(n)).append(", ");
                ++n;
            }
            h.o(sb.toString(), "red");
            return true;
        }
        return false;
    }
    
    @Override
    public String k() {
        return "-friend add cookiedragon234\n-friend del 2b2tnews\n-friend list";
    }
}
