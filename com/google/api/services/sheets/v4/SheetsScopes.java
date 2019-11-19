package com.google.api.services.sheets.v4;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SheetsScopes
{
    public static final String DRIVE = "https://www.googleapis.com/auth/drive";
    public static final String DRIVE_FILE = "https://www.googleapis.com/auth/drive.file";
    public static final String DRIVE_READONLY = "https://www.googleapis.com/auth/drive.readonly";
    public static final String SPREADSHEETS = "https://www.googleapis.com/auth/spreadsheets";
    public static final String SPREADSHEETS_READONLY = "https://www.googleapis.com/auth/spreadsheets.readonly";
    
    public static Set<String> all() {
        final HashSet<String> set = new HashSet<String>();
        set.add("https://www.googleapis.com/auth/drive");
        set.add("https://www.googleapis.com/auth/drive.file");
        set.add("https://www.googleapis.com/auth/drive.readonly");
        set.add("https://www.googleapis.com/auth/spreadsheets");
        set.add("https://www.googleapis.com/auth/spreadsheets.readonly");
        return (Set<String>)Collections.<Object>unmodifiableSet((Set<?>)set);
    }
    
    private SheetsScopes() {
        super();
    }
}
