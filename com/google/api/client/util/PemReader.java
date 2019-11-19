package com.google.api.client.util;

import java.util.regex.Matcher;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.util.regex.Pattern;

@Beta
public final class PemReader
{
    private static final Pattern BEGIN_PATTERN;
    private static final Pattern END_PATTERN;
    private BufferedReader reader;
    
    public PemReader(final Reader reader) {
        super();
        this.reader = new BufferedReader(reader);
    }
    
    public Section readNextSection() throws IOException {
        return this.readNextSection(null);
    }
    
    public Section readNextSection(final String s) throws IOException {
        String s2 = null;
        StringBuilder sb = null;
        while (true) {
            final String line = this.reader.readLine();
            if (line == null) {
                Preconditions.checkArgument(s2 == null, "missing end tag (%s)", s2);
                return null;
            }
            if (sb == null) {
                final Matcher matcher = PemReader.BEGIN_PATTERN.matcher(line);
                if (!matcher.matches()) {
                    continue;
                }
                final String group = matcher.group(1);
                if (s != null && !group.equals(s)) {
                    continue;
                }
                sb = new StringBuilder();
                s2 = group;
            }
            else {
                final Matcher matcher2 = PemReader.END_PATTERN.matcher(line);
                if (matcher2.matches()) {
                    final String group2 = matcher2.group(1);
                    Preconditions.checkArgument(group2.equals(s2), "end tag (%s) doesn't match begin tag (%s)", group2, s2);
                    return new Section(s2, Base64.decodeBase64(sb.toString()));
                }
                sb.append(line);
            }
        }
    }
    
    public static Section readFirstSectionAndClose(final Reader reader) throws IOException {
        return readFirstSectionAndClose(reader, null);
    }
    
    public static Section readFirstSectionAndClose(final Reader reader, final String s) throws IOException {
        final PemReader pemReader = new PemReader(reader);
        try {
            return pemReader.readNextSection(s);
        }
        finally {
            pemReader.close();
        }
    }
    
    public void close() throws IOException {
        this.reader.close();
    }
    
    static {
        BEGIN_PATTERN = Pattern.compile("-----BEGIN ([A-Z ]+)-----");
        END_PATTERN = Pattern.compile("-----END ([A-Z ]+)-----");
    }
}
