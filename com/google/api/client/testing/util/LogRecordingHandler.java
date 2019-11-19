package com.google.api.client.testing.util;

import java.util.Iterator;
import java.util.ArrayList;
import com.google.api.client.util.Lists;
import java.util.logging.LogRecord;
import java.util.List;
import com.google.api.client.util.Beta;
import java.util.logging.Handler;

@Beta
public class LogRecordingHandler extends Handler
{
    private final List<LogRecord> records;
    
    public LogRecordingHandler() {
        super();
        this.records = (List<LogRecord>)Lists.<Object>newArrayList();
    }
    
    @Override
    public void publish(final LogRecord logRecord) {
        this.records.add(logRecord);
    }
    
    @Override
    public void flush() {
    }
    
    @Override
    public void close() throws SecurityException {
    }
    
    public List<String> messages() {
        final ArrayList<String> arrayList = Lists.<String>newArrayList();
        final Iterator<LogRecord> iterator = this.records.iterator();
        while (iterator.hasNext()) {
            arrayList.add(iterator.next().getMessage());
        }
        return arrayList;
    }
}
