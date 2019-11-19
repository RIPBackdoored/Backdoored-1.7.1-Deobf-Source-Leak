package com.fasterxml.jackson.core;

import java.io.Serializable;

public class Version implements Comparable<Version>, Serializable
{
    private static final long serialVersionUID = 1L;
    private static final Version UNKNOWN_VERSION;
    protected final int _majorVersion;
    protected final int _minorVersion;
    protected final int _patchLevel;
    protected final String _groupId;
    protected final String _artifactId;
    protected final String _snapshotInfo;
    
    @Deprecated
    public Version(final int n, final int n2, final int n3, final String s) {
        this(n, n2, n3, s, null, null);
    }
    
    public Version(final int majorVersion, final int minorVersion, final int patchLevel, final String snapshotInfo, final String s, final String s2) {
        super();
        this._majorVersion = majorVersion;
        this._minorVersion = minorVersion;
        this._patchLevel = patchLevel;
        this._snapshotInfo = snapshotInfo;
        this._groupId = ((s == null) ? "" : s);
        this._artifactId = ((s2 == null) ? "" : s2);
    }
    
    public static Version unknownVersion() {
        return Version.UNKNOWN_VERSION;
    }
    
    public boolean isUnknownVersion() {
        return this == Version.UNKNOWN_VERSION;
    }
    
    public boolean isSnapshot() {
        return this._snapshotInfo != null && this._snapshotInfo.length() > 0;
    }
    
    @Deprecated
    public boolean isUknownVersion() {
        return this.isUnknownVersion();
    }
    
    public int getMajorVersion() {
        return this._majorVersion;
    }
    
    public int getMinorVersion() {
        return this._minorVersion;
    }
    
    public int getPatchLevel() {
        return this._patchLevel;
    }
    
    public String getGroupId() {
        return this._groupId;
    }
    
    public String getArtifactId() {
        return this._artifactId;
    }
    
    public String toFullString() {
        return this._groupId + '/' + this._artifactId + '/' + this.toString();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this._majorVersion).append('.');
        sb.append(this._minorVersion).append('.');
        sb.append(this._patchLevel);
        if (this.isSnapshot()) {
            sb.append('-').append(this._snapshotInfo);
        }
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        return this._artifactId.hashCode() ^ this._groupId.hashCode() + this._majorVersion - this._minorVersion + this._patchLevel;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        final Version version = (Version)o;
        return version._majorVersion == this._majorVersion && version._minorVersion == this._minorVersion && version._patchLevel == this._patchLevel && version._artifactId.equals(this._artifactId) && version._groupId.equals(this._groupId);
    }
    
    @Override
    public int compareTo(final Version version) {
        if (version == this) {
            return 0;
        }
        this._groupId.compareTo(version._groupId);
        this._artifactId.compareTo(version._artifactId);
        final int n = this._majorVersion - version._majorVersion;
        final int n2 = this._minorVersion - version._minorVersion;
        return this._patchLevel - version._patchLevel;
    }
    
    @Override
    public /* bridge */ int compareTo(final Object o) {
        return this.compareTo((Version)o);
    }
    
    static {
        UNKNOWN_VERSION = new Version(0, 0, 0, null, null, null);
    }
}
