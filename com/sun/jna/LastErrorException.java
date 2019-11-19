package com.sun.jna;

public class LastErrorException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    private int errorCode;
    
    private static String formatMessage(final int n) {
        return Platform.isWindows() ? ("GetLastError() returned " + n) : ("errno was " + n);
    }
    
    private static String parseMessage(final String s) {
        try {
            return formatMessage(Integer.parseInt(s));
        }
        catch (NumberFormatException ex) {
            return s;
        }
    }
    
    public int getErrorCode() {
        return this.errorCode;
    }
    
    public LastErrorException(String substring) {
        super(parseMessage(substring.trim()));
        try {
            if (substring.startsWith("[")) {
                substring = substring.substring(1, substring.indexOf("]"));
            }
            this.errorCode = Integer.parseInt(substring);
        }
        catch (NumberFormatException ex) {
            this.errorCode = -1;
        }
    }
    
    public LastErrorException(final int n) {
        this(n, formatMessage(n));
    }
    
    protected LastErrorException(final int errorCode, final String s) {
        super(s);
        this.errorCode = errorCode;
    }
}
