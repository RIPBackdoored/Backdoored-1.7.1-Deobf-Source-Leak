package javassist.bytecode;

static class PrettyPrinter
{
    PrettyPrinter() {
        super();
    }
    
    static String toString(final String s) {
        final StringBuffer sb = new StringBuffer();
        if (s.charAt(0) == '(') {
            int type = 1;
            sb.append('(');
            while (s.charAt(type) != ')') {
                if (type > 1) {
                    sb.append(',');
                }
                type = readType(sb, type, s);
            }
            sb.append(')');
        }
        else {
            readType(sb, 0, s);
        }
        return sb.toString();
    }
    
    static int readType(final StringBuffer sb, int n, final String s) {
        char c = s.charAt(n);
        int n2 = 0;
        while (c == '[') {
            ++n2;
            c = s.charAt(++n);
        }
        if (c == 'L') {
            while (true) {
                char char1 = s.charAt(++n);
                if (char1 == ';') {
                    break;
                }
                if (char1 == '/') {
                    char1 = '.';
                }
                sb.append(char1);
            }
        }
        else {
            sb.append(Descriptor.toPrimitiveClass(c).getName());
        }
        while (n2-- > 0) {
            sb.append("[]");
        }
        return n + 1;
    }
}
