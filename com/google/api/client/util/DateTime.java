package com.google.api.client.util;

import java.util.regex.Matcher;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.TimeZone;
import java.io.Serializable;

public final class DateTime implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static final TimeZone GMT;
    private static final Pattern RFC3339_PATTERN;
    private final long value;
    private final boolean dateOnly;
    private final int tzShift;
    
    public DateTime(final Date date, final TimeZone timeZone) {
        this(false, date.getTime(), (timeZone == null) ? null : Integer.valueOf(timeZone.getOffset(date.getTime()) / 60000));
    }
    
    public DateTime(final long n) {
        this(false, n, null);
    }
    
    public DateTime(final Date date) {
        this(date.getTime());
    }
    
    public DateTime(final long n, final int n2) {
        this(false, n, n2);
    }
    
    public DateTime(final boolean dateOnly, final long value, final Integer n) {
        super();
        this.dateOnly = dateOnly;
        this.value = value;
        this.tzShift = (dateOnly ? 0 : ((n == null) ? (TimeZone.getDefault().getOffset(value) / 60000) : n));
    }
    
    public DateTime(final String s) {
        super();
        final DateTime rfc3339 = parseRfc3339(s);
        this.dateOnly = rfc3339.dateOnly;
        this.value = rfc3339.value;
        this.tzShift = rfc3339.tzShift;
    }
    
    public long getValue() {
        return this.value;
    }
    
    public boolean isDateOnly() {
        return this.dateOnly;
    }
    
    public int getTimeZoneShift() {
        return this.tzShift;
    }
    
    public String toStringRfc3339() {
        final StringBuilder sb = new StringBuilder();
        final GregorianCalendar gregorianCalendar = new GregorianCalendar(DateTime.GMT);
        gregorianCalendar.setTimeInMillis(this.value + this.tzShift * 60000L);
        appendInt(sb, gregorianCalendar.get(1), 4);
        sb.append('-');
        appendInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        appendInt(sb, gregorianCalendar.get(5), 2);
        if (!this.dateOnly) {
            sb.append('T');
            appendInt(sb, gregorianCalendar.get(11), 2);
            sb.append(':');
            appendInt(sb, gregorianCalendar.get(12), 2);
            sb.append(':');
            appendInt(sb, gregorianCalendar.get(13), 2);
            if (gregorianCalendar.isSet(14)) {
                sb.append('.');
                appendInt(sb, gregorianCalendar.get(14), 3);
            }
            if (this.tzShift == 0) {
                sb.append('Z');
            }
            else {
                int tzShift = this.tzShift;
                if (this.tzShift > 0) {
                    sb.append('+');
                }
                else {
                    sb.append('-');
                    tzShift = -tzShift;
                }
                final int n = tzShift / 60;
                final int n2 = tzShift % 60;
                appendInt(sb, n, 2);
                sb.append(':');
                appendInt(sb, n2, 2);
            }
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return this.toStringRfc3339();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DateTime)) {
            return false;
        }
        final DateTime dateTime = (DateTime)o;
        return this.dateOnly == dateTime.dateOnly && this.value == dateTime.value && this.tzShift == dateTime.tzShift;
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(new long[] { this.value, this.dateOnly ? 1 : 0, this.tzShift });
    }
    
    public static DateTime parseRfc3339(final String s) throws NumberFormatException {
        final Matcher matcher = DateTime.RFC3339_PATTERN.matcher(s);
        if (!matcher.matches()) {
            throw new NumberFormatException("Invalid date/time format: " + s);
        }
        final int int1 = Integer.parseInt(matcher.group(1));
        final int n = Integer.parseInt(matcher.group(2)) - 1;
        final int int2 = Integer.parseInt(matcher.group(3));
        final boolean b = matcher.group(4) != null;
        final String group = matcher.group(9);
        final boolean b2 = group != null;
        int int3 = 0;
        int int4 = 0;
        int int5 = 0;
        int n2 = 0;
        Integer value = null;
        if (b2) {
            throw new NumberFormatException("Invalid date/time format, cannot specify time zone shift without specifying time: " + s);
        }
        if (b) {
            int3 = Integer.parseInt(matcher.group(5));
            int4 = Integer.parseInt(matcher.group(6));
            int5 = Integer.parseInt(matcher.group(7));
            if (matcher.group(8) != null) {
                n2 = (int)((float)Integer.parseInt(matcher.group(8).substring(1)) / Math.pow(10.0, matcher.group(8).substring(1).length() - 3));
            }
        }
        final GregorianCalendar gregorianCalendar = new GregorianCalendar(DateTime.GMT);
        gregorianCalendar.set(int1, n, int2, int3, int4, int5);
        gregorianCalendar.set(14, n2);
        long timeInMillis = gregorianCalendar.getTimeInMillis();
        if (b && b2) {
            int n3;
            if (Character.toUpperCase(group.charAt(0)) == 'Z') {
                n3 = 0;
            }
            else {
                n3 = Integer.parseInt(matcher.group(11)) * 60 + Integer.parseInt(matcher.group(12));
                if (matcher.group(10).charAt(0) == '-') {
                    n3 = -n3;
                }
                timeInMillis -= n3 * 60000L;
            }
            value = n3;
        }
        return new DateTime(true, timeInMillis, value);
    }
    
    private static void appendInt(final StringBuilder sb, int n, int n2) {
        if (n < 0) {
            sb.append('-');
            n = -n;
        }
        for (int i = n; i > 0; i /= 10, --n2) {}
        for (int j = 0; j < n2; ++j) {
            sb.append('0');
        }
        if (n != 0) {
            sb.append(n);
        }
    }
    
    static {
        GMT = TimeZone.getTimeZone("GMT");
        RFC3339_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})([Tt](\\d{2}):(\\d{2}):(\\d{2})(\\.\\d+)?)?([Zz]|([+-])(\\d{2}):(\\d{2}))?");
    }
}
