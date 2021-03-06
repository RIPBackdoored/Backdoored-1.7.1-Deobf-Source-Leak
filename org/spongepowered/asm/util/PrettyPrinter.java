package org.spongepowered.asm.util;

import java.io.PrintStream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;
import java.util.Map;
import com.google.common.base.Strings;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class PrettyPrinter
{
    private final HorizontalRule horizontalRule;
    private final List<Object> lines;
    private Table table;
    private boolean recalcWidth;
    protected int width;
    protected int wrapWidth;
    protected int kvKeyWidth;
    protected String kvFormat;
    
    public PrettyPrinter() {
        this(100);
    }
    
    public PrettyPrinter(final int width) {
        super();
        this.horizontalRule = new HorizontalRule(new char[] { '*' });
        this.lines = new ArrayList<Object>();
        this.recalcWidth = false;
        this.width = 100;
        this.wrapWidth = 80;
        this.kvKeyWidth = 10;
        this.kvFormat = makeKvFormat(this.kvKeyWidth);
        this.width = width;
    }
    
    public PrettyPrinter wrapTo(final int wrapWidth) {
        this.wrapWidth = wrapWidth;
        return this;
    }
    
    public int wrapTo() {
        return this.wrapWidth;
    }
    
    public PrettyPrinter table() {
        this.table = new Table();
        return this;
    }
    
    public PrettyPrinter table(final String... array) {
        this.table = new Table();
        for (int length = array.length, i = 0; i < length; ++i) {
            this.table.addColumn(array[i]);
        }
        return this;
    }
    
    public PrettyPrinter table(final Object... array) {
        this.table = new Table();
        Column column = null;
        for (final Object o : array) {
            if (o instanceof String) {
                column = this.table.addColumn((String)o);
            }
            else if (o instanceof Integer && column != null) {
                final int intValue = (int)o;
                if (intValue > 0) {
                    column.setWidth(intValue);
                }
                else if (intValue < 0) {
                    column.setMaxWidth(-intValue);
                }
            }
            else if (o instanceof Alignment && column != null) {
                column.setAlignment((Alignment)o);
            }
            else if (o != null) {
                column = this.table.addColumn(o.toString());
            }
        }
        return this;
    }
    
    public PrettyPrinter spacing(final int colSpacing) {
        if (this.table == null) {
            this.table = new Table();
        }
        this.table.setColSpacing(colSpacing);
        return this;
    }
    
    public PrettyPrinter th() {
        return this.th(false);
    }
    
    private PrettyPrinter th(final boolean b) {
        if (this.table == null) {
            this.table = new Table();
        }
        if (!b || this.table.addHeader) {
            this.table.headerAdded();
            this.addLine(this.table);
        }
        return this;
    }
    
    public PrettyPrinter tr(final Object... array) {
        this.th(true);
        this.addLine(this.table.addRow(array));
        this.recalcWidth = true;
        return this;
    }
    
    public PrettyPrinter add() {
        this.addLine("");
        return this;
    }
    
    public PrettyPrinter add(final String s) {
        this.addLine(s);
        this.width = Math.max(this.width, s.length());
        return this;
    }
    
    public PrettyPrinter add(final String s, final Object... array) {
        final String format = String.format(s, array);
        this.addLine(format);
        this.width = Math.max(this.width, format.length());
        return this;
    }
    
    public PrettyPrinter add(final Object[] array) {
        return this.add(array, "%s");
    }
    
    public PrettyPrinter add(final Object[] array, final String s) {
        for (int length = array.length, i = 0; i < length; ++i) {
            this.add(s, array[i]);
        }
        return this;
    }
    
    public PrettyPrinter addIndexed(final Object[] array) {
        final String string = "[%" + String.valueOf(array.length - 1).length() + "d] %s";
        for (int i = 0; i < array.length; ++i) {
            this.add(string, i, array[i]);
        }
        return this;
    }
    
    public PrettyPrinter addWithIndices(final Collection<?> collection) {
        return this.addIndexed(collection.toArray());
    }
    
    public PrettyPrinter add(final IPrettyPrintable prettyPrintable) {
        if (prettyPrintable != null) {
            prettyPrintable.print(this);
        }
        return this;
    }
    
    public PrettyPrinter add(final Throwable t) {
        return this.add(t, 4);
    }
    
    public PrettyPrinter add(Throwable cause, final int n) {
        while (cause != null) {
            this.add("%s: %s", cause.getClass().getName(), cause.getMessage());
            this.add(cause.getStackTrace(), n);
            cause = cause.getCause();
        }
        return this;
    }
    
    public PrettyPrinter add(final StackTraceElement[] array, final int count) {
        final String repeat = Strings.repeat(" ", count);
        for (int length = array.length, i = 0; i < length; ++i) {
            this.add("%s%s", repeat, array[i]);
        }
        return this;
    }
    
    public PrettyPrinter add(final Object o) {
        return this.add(o, 0);
    }
    
    public PrettyPrinter add(final Object o, final int count) {
        return this.append(o, count, Strings.repeat(" ", count));
    }
    
    private PrettyPrinter append(final Object o, final int n, final String s) {
        if (o instanceof String) {
            return this.add("%s%s", s, o);
        }
        if (o instanceof Iterable) {
            final Iterator<Object> iterator = ((Iterable)o).iterator();
            while (iterator.hasNext()) {
                this.append(iterator.next(), n, s);
            }
            return this;
        }
        if (o instanceof Map) {
            this.kvWidth(n);
            return this.add((Map<?, ?>)o);
        }
        if (o instanceof IPrettyPrintable) {
            return this.add((IPrettyPrintable)o);
        }
        if (o instanceof Throwable) {
            return this.add((Throwable)o, n);
        }
        if (o.getClass().isArray()) {
            return this.add((Object[])o, n + "%s");
        }
        return this.add("%s%s", s, o);
    }
    
    public PrettyPrinter addWrapped(final String s, final Object... array) {
        return this.addWrapped(this.wrapWidth, s, array);
    }
    
    public PrettyPrinter addWrapped(final int n, final String s, final Object... array) {
        String group = "";
        final String replace = String.format(s, array).replace("\t", "    ");
        final Matcher matcher = Pattern.compile("^(\\s+)(.*)$").matcher(replace);
        if (matcher.matches()) {
            group = matcher.group(1);
        }
        try {
            final Iterator<String> iterator = this.getWrapped(n, replace, group).iterator();
            while (iterator.hasNext()) {
                this.addLine(iterator.next());
            }
        }
        catch (Exception ex) {
            this.add(replace);
        }
        return this;
    }
    
    private List<String> getWrapped(final int n, String string, final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        while (string.length() > n) {
            int lastIndex = string.lastIndexOf(32, n);
            if (lastIndex < 10) {
                lastIndex = n;
            }
            list.add(string.substring(0, lastIndex));
            string = s + string.substring(lastIndex + 1);
        }
        if (string.length() > 0) {
            list.add(string);
        }
        return list;
    }
    
    public PrettyPrinter kv(final String s, final String s2, final Object... array) {
        return this.kv(s, (Object)String.format(s2, array));
    }
    
    public PrettyPrinter kv(final String s, final Object o) {
        this.addLine(new KeyValue(s, o));
        return this.kvWidth(s.length());
    }
    
    public PrettyPrinter kvWidth(final int kvKeyWidth) {
        if (kvKeyWidth > this.kvKeyWidth) {
            this.kvKeyWidth = kvKeyWidth;
            this.kvFormat = makeKvFormat(kvKeyWidth);
        }
        this.recalcWidth = true;
        return this;
    }
    
    public PrettyPrinter add(final Map<?, ?> map) {
        for (final Map.Entry<Object, V> entry : map.entrySet()) {
            this.kv((entry.getKey() == null) ? "null" : entry.getKey().toString(), entry.getValue());
        }
        return this;
    }
    
    public PrettyPrinter hr() {
        return this.hr('*');
    }
    
    public PrettyPrinter hr(final char c) {
        this.addLine(new HorizontalRule(new char[] { c }));
        return this;
    }
    
    public PrettyPrinter centre() {
        if (!this.lines.isEmpty() && this.lines.get(this.lines.size() - 1) instanceof String) {
            this.addLine(new CentredText(this.lines.remove(this.lines.size() - 1)));
        }
        return this;
    }
    
    private void addLine(final Object o) {
        if (o == null) {
            return;
        }
        this.lines.add(o);
        this.recalcWidth |= (o instanceof IVariableWidthEntry);
    }
    
    public PrettyPrinter trace() {
        return this.trace(getDefaultLoggerName());
    }
    
    public PrettyPrinter trace(final Level level) {
        return this.trace(getDefaultLoggerName(), level);
    }
    
    public PrettyPrinter trace(final String s) {
        return this.trace(System.err, LogManager.getLogger(s));
    }
    
    public PrettyPrinter trace(final String s, final Level level) {
        return this.trace(System.err, LogManager.getLogger(s), level);
    }
    
    public PrettyPrinter trace(final Logger logger) {
        return this.trace(System.err, logger);
    }
    
    public PrettyPrinter trace(final Logger logger, final Level level) {
        return this.trace(System.err, logger, level);
    }
    
    public PrettyPrinter trace(final PrintStream printStream) {
        return this.trace(printStream, getDefaultLoggerName());
    }
    
    public PrettyPrinter trace(final PrintStream printStream, final Level level) {
        return this.trace(printStream, getDefaultLoggerName(), level);
    }
    
    public PrettyPrinter trace(final PrintStream printStream, final String s) {
        return this.trace(printStream, LogManager.getLogger(s));
    }
    
    public PrettyPrinter trace(final PrintStream printStream, final String s, final Level level) {
        return this.trace(printStream, LogManager.getLogger(s), level);
    }
    
    public PrettyPrinter trace(final PrintStream printStream, final Logger logger) {
        return this.trace(printStream, logger, Level.DEBUG);
    }
    
    public PrettyPrinter trace(final PrintStream printStream, final Logger logger, final Level level) {
        this.log(logger, level);
        this.print(printStream);
        return this;
    }
    
    public PrettyPrinter print() {
        return this.print(System.err);
    }
    
    public PrettyPrinter print(final PrintStream printStream) {
        this.updateWidth();
        this.printSpecial(printStream, this.horizontalRule);
        for (final ISpecialEntry next : this.lines) {
            if (next instanceof ISpecialEntry) {
                this.printSpecial(printStream, next);
            }
            else {
                this.printString(printStream, next.toString());
            }
        }
        this.printSpecial(printStream, this.horizontalRule);
        return this;
    }
    
    private void printSpecial(final PrintStream printStream, final ISpecialEntry specialEntry) {
        printStream.printf("/*%s*/\n", specialEntry.toString());
    }
    
    private void printString(final PrintStream printStream, final String s) {
        if (s != null) {
            printStream.printf("/* %-" + this.width + "s */\n", s);
        }
    }
    
    public PrettyPrinter log(final Logger logger) {
        return this.log(logger, Level.INFO);
    }
    
    public PrettyPrinter log(final Logger logger, final Level level) {
        this.updateWidth();
        this.logSpecial(logger, level, this.horizontalRule);
        for (final ISpecialEntry next : this.lines) {
            if (next instanceof ISpecialEntry) {
                this.logSpecial(logger, level, next);
            }
            else {
                this.logString(logger, level, next.toString());
            }
        }
        this.logSpecial(logger, level, this.horizontalRule);
        return this;
    }
    
    private void logSpecial(final Logger logger, final Level level, final ISpecialEntry specialEntry) {
        logger.log(level, "/*{}*/", new Object[] { specialEntry.toString() });
    }
    
    private void logString(final Logger logger, final Level level, final String s) {
        if (s != null) {
            logger.log(level, String.format("/* %-" + this.width + "s */", s));
        }
    }
    
    private void updateWidth() {
        if (this.recalcWidth) {
            this.recalcWidth = false;
            for (final IVariableWidthEntry next : this.lines) {
                if (next instanceof IVariableWidthEntry) {
                    this.width = Math.min(4096, Math.max(this.width, next.getWidth()));
                }
            }
        }
    }
    
    private static String makeKvFormat(final int n) {
        return String.format("%%%ds : %%s", n);
    }
    
    private static String getDefaultLoggerName() {
        final String className = new Throwable().getStackTrace()[2].getClassName();
        final int lastIndex = className.lastIndexOf(46);
        return (lastIndex == -1) ? className : className.substring(lastIndex + 1);
    }
    
    public static void dumpStack() {
        new PrettyPrinter().add(new Exception("Stack trace")).print(System.err);
    }
    
    public static void print(final Throwable t) {
        new PrettyPrinter().add(t).print(System.err);
    }
}
