package com.fasterxml.jackson.core.util;

import java.util.Arrays;
import java.io.IOException;
import java.io.Writer;
import com.fasterxml.jackson.core.io.NumberInput;
import java.math.BigDecimal;
import java.util.ArrayList;

public final class TextBuffer
{
    static final char[] NO_CHARS;
    static final int MIN_SEGMENT_LEN = 1000;
    static final int MAX_SEGMENT_LEN = 262144;
    private final BufferRecycler _allocator;
    private char[] _inputBuffer;
    private int _inputStart;
    private int _inputLen;
    private ArrayList<char[]> _segments;
    private boolean _hasSegments;
    private int _segmentSize;
    private char[] _currentSegment;
    private int _currentSize;
    private String _resultString;
    private char[] _resultArray;
    
    public TextBuffer(final BufferRecycler allocator) {
        super();
        this._allocator = allocator;
    }
    
    public void releaseBuffers() {
        if (this._allocator == null) {
            this.resetWithEmpty();
        }
        else if (this._currentSegment != null) {
            this.resetWithEmpty();
            final char[] currentSegment = this._currentSegment;
            this._currentSegment = null;
            this._allocator.releaseCharBuffer(2, currentSegment);
        }
    }
    
    public void resetWithEmpty() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            this.clearSegments();
        }
    }
    
    public void resetWith(final char c) {
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            this.clearSegments();
        }
        else if (this._currentSegment == null) {
            this._currentSegment = this.buf(1);
        }
        this._currentSegment[0] = c;
        final int n = 1;
        this._segmentSize = n;
        this._currentSize = n;
    }
    
    public void resetWithShared(final char[] inputBuffer, final int inputStart, final int inputLen) {
        this._resultString = null;
        this._resultArray = null;
        this._inputBuffer = inputBuffer;
        this._inputStart = inputStart;
        this._inputLen = inputLen;
        if (this._hasSegments) {
            this.clearSegments();
        }
    }
    
    public void resetWithCopy(final char[] array, final int n, final int n2) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            this.clearSegments();
        }
        else if (this._currentSegment == null) {
            this._currentSegment = this.buf(n2);
        }
        final int n3 = 0;
        this._segmentSize = n3;
        this._currentSize = n3;
        this.append(array, n, n2);
    }
    
    public void resetWithCopy(final String s, final int n, final int n2) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            this.clearSegments();
        }
        else if (this._currentSegment == null) {
            this._currentSegment = this.buf(n2);
        }
        final int n3 = 0;
        this._segmentSize = n3;
        this._currentSize = n3;
        this.append(s, n, n2);
    }
    
    public void resetWithString(final String resultString) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = resultString;
        this._resultArray = null;
        if (this._hasSegments) {
            this.clearSegments();
        }
        this._currentSize = 0;
    }
    
    public char[] getBufferWithoutReset() {
        return this._currentSegment;
    }
    
    private char[] buf(final int n) {
        if (this._allocator != null) {
            return this._allocator.allocCharBuffer(2, n);
        }
        return new char[Math.max(n, 1000)];
    }
    
    private void clearSegments() {
        this._hasSegments = false;
        this._segments.clear();
        final int n = 0;
        this._segmentSize = n;
        this._currentSize = n;
    }
    
    public int size() {
        if (this._inputStart >= 0) {
            return this._inputLen;
        }
        if (this._resultArray != null) {
            return this._resultArray.length;
        }
        if (this._resultString != null) {
            return this._resultString.length();
        }
        return this._segmentSize + this._currentSize;
    }
    
    public int getTextOffset() {
        return (this._inputStart >= 0) ? this._inputStart : 0;
    }
    
    public boolean hasTextAsCharacters() {
        return this._inputStart >= 0 || this._resultArray != null || this._resultString == null;
    }
    
    public char[] getTextBuffer() {
        if (this._inputStart >= 0) {
            return this._inputBuffer;
        }
        if (this._resultArray != null) {
            return this._resultArray;
        }
        if (this._resultString != null) {
            return this._resultArray = this._resultString.toCharArray();
        }
        if (!this._hasSegments) {
            return (this._currentSegment == null) ? TextBuffer.NO_CHARS : this._currentSegment;
        }
        return this.contentsAsArray();
    }
    
    public String contentsAsString() {
        if (this._resultString == null) {
            if (this._resultArray != null) {
                this._resultString = new String(this._resultArray);
            }
            else if (this._inputStart >= 0) {
                if (this._inputLen < 1) {
                    return this._resultString = "";
                }
                this._resultString = new String(this._inputBuffer, this._inputStart, this._inputLen);
            }
            else {
                final int segmentSize = this._segmentSize;
                final int currentSize = this._currentSize;
                this._resultString = "";
            }
        }
        return this._resultString;
    }
    
    public char[] contentsAsArray() {
        char[] resultArray = this._resultArray;
        if (resultArray == null) {
            resultArray = (this._resultArray = this.resultArray());
        }
        return resultArray;
    }
    
    public BigDecimal contentsAsDecimal() throws NumberFormatException {
        if (this._resultArray != null) {
            return NumberInput.parseBigDecimal(this._resultArray);
        }
        if (this._inputStart >= 0 && this._inputBuffer != null) {
            return NumberInput.parseBigDecimal(this._inputBuffer, this._inputStart, this._inputLen);
        }
        if (this._segmentSize == 0 && this._currentSegment != null) {
            return NumberInput.parseBigDecimal(this._currentSegment, 0, this._currentSize);
        }
        return NumberInput.parseBigDecimal(this.contentsAsArray());
    }
    
    public double contentsAsDouble() throws NumberFormatException {
        return NumberInput.parseDouble(this.contentsAsString());
    }
    
    public int contentsAsInt(final boolean b) {
        if (this._inputStart >= 0 && this._inputBuffer != null) {
            if (b) {
                return -NumberInput.parseInt(this._inputBuffer, this._inputStart + 1, this._inputLen - 1);
            }
            return NumberInput.parseInt(this._inputBuffer, this._inputStart, this._inputLen);
        }
        else {
            if (b) {
                return -NumberInput.parseInt(this._currentSegment, 1, this._currentSize - 1);
            }
            return NumberInput.parseInt(this._currentSegment, 0, this._currentSize);
        }
    }
    
    public long contentsAsLong(final boolean b) {
        if (this._inputStart >= 0 && this._inputBuffer != null) {
            if (b) {
                return -NumberInput.parseLong(this._inputBuffer, this._inputStart + 1, this._inputLen - 1);
            }
            return NumberInput.parseLong(this._inputBuffer, this._inputStart, this._inputLen);
        }
        else {
            if (b) {
                return -NumberInput.parseLong(this._currentSegment, 1, this._currentSize - 1);
            }
            return NumberInput.parseLong(this._currentSegment, 0, this._currentSize);
        }
    }
    
    public int contentsToWriter(final Writer writer) throws IOException {
        if (this._resultArray != null) {
            writer.write(this._resultArray);
            return this._resultArray.length;
        }
        if (this._resultString != null) {
            writer.write(this._resultString);
            return this._resultString.length();
        }
        if (this._inputStart >= 0) {
            final int inputLen = this._inputLen;
            if (inputLen > 0) {
                writer.write(this._inputBuffer, this._inputStart, inputLen);
            }
            return inputLen;
        }
        int n = 0;
        if (this._segments != null) {
            for (int i = 0; i < this._segments.size(); ++i) {
                final char[] array = this._segments.get(i);
                final int length = array.length;
                writer.write(array, 0, length);
                n += length;
            }
        }
        final int currentSize = this._currentSize;
        if (currentSize > 0) {
            writer.write(this._currentSegment, 0, currentSize);
            n += currentSize;
        }
        return n;
    }
    
    public void ensureNotShared() {
        if (this._inputStart >= 0) {
            this.unshare(16);
        }
    }
    
    public void append(final char c) {
        if (this._inputStart >= 0) {
            this.unshare(16);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] array = this._currentSegment;
        if (this._currentSize >= array.length) {
            this.expand(1);
            array = this._currentSegment;
        }
        array[this._currentSize++] = c;
    }
    
    public void append(final char[] array, int n, int i) {
        if (this._inputStart >= 0) {
            this.unshare(i);
        }
        this._resultString = null;
        this._resultArray = null;
        final char[] currentSegment = this._currentSegment;
        final int n2 = currentSegment.length - this._currentSize;
        if (n2 >= i) {
            System.arraycopy(array, n, currentSegment, this._currentSize, i);
            this._currentSize += i;
            return;
        }
        if (n2 > 0) {
            System.arraycopy(array, n, currentSegment, this._currentSize, n2);
            n += n2;
            i -= n2;
        }
        do {
            this.expand(i);
            final int min = Math.min(this._currentSegment.length, i);
            System.arraycopy(array, n, this._currentSegment, 0, min);
            this._currentSize += min;
            n += min;
            i -= min;
        } while (i > 0);
    }
    
    public void append(final String s, int n, int i) {
        if (this._inputStart >= 0) {
            this.unshare(i);
        }
        this._resultString = null;
        this._resultArray = null;
        final char[] currentSegment = this._currentSegment;
        final int n2 = currentSegment.length - this._currentSize;
        if (n2 >= i) {
            s.getChars(n, n + i, currentSegment, this._currentSize);
            this._currentSize += i;
            return;
        }
        if (n2 > 0) {
            s.getChars(n, n + n2, currentSegment, this._currentSize);
            i -= n2;
            n += n2;
        }
        do {
            this.expand(i);
            final int min = Math.min(this._currentSegment.length, i);
            s.getChars(n, n + min, this._currentSegment, 0);
            this._currentSize += min;
            n += min;
            i -= min;
        } while (i > 0);
    }
    
    public char[] getCurrentSegment() {
        if (this._inputStart >= 0) {
            this.unshare(1);
        }
        else {
            final char[] currentSegment = this._currentSegment;
            if (currentSegment == null) {
                this._currentSegment = this.buf(0);
            }
            else if (this._currentSize >= currentSegment.length) {
                this.expand(1);
            }
        }
        return this._currentSegment;
    }
    
    public char[] emptyAndGetCurrentSegment() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            this.clearSegments();
        }
        char[] currentSegment = this._currentSegment;
        if (currentSegment == null) {
            currentSegment = (this._currentSegment = this.buf(0));
        }
        return currentSegment;
    }
    
    public int getCurrentSegmentSize() {
        return this._currentSize;
    }
    
    public void setCurrentLength(final int currentSize) {
        this._currentSize = currentSize;
    }
    
    public String setCurrentAndReturn(final int currentSize) {
        this._currentSize = currentSize;
        if (this._segmentSize > 0) {
            return this.contentsAsString();
        }
        final int currentSize2 = this._currentSize;
        return this._resultString = "";
    }
    
    public char[] finishCurrentSegment() {
        if (this._segments == null) {
            this._segments = new ArrayList<char[]>();
        }
        this._hasSegments = true;
        this._segments.add(this._currentSegment);
        final int length = this._currentSegment.length;
        this._segmentSize += length;
        this._currentSize = 0;
        int n = length + (length >> 1);
        if (n < 1000) {
            n = 1000;
        }
        else if (n > 262144) {
            n = 262144;
        }
        return this._currentSegment = this.carr(n);
    }
    
    public char[] expandCurrentSegment() {
        final char[] currentSegment = this._currentSegment;
        final int length = currentSegment.length;
        int n = length + (length >> 1);
        if (n > 262144) {
            n = length + (length >> 2);
        }
        return this._currentSegment = Arrays.copyOf(currentSegment, n);
    }
    
    public char[] expandCurrentSegment(final int n) {
        final char[] currentSegment = this._currentSegment;
        if (currentSegment.length >= n) {
            return currentSegment;
        }
        return this._currentSegment = Arrays.copyOf(currentSegment, n);
    }
    
    @Override
    public String toString() {
        return this.contentsAsString();
    }
    
    private void unshare(final int n) {
        final int inputLen = this._inputLen;
        this._inputLen = 0;
        final char[] inputBuffer = this._inputBuffer;
        this._inputBuffer = null;
        final int inputStart = this._inputStart;
        this._inputStart = -1;
        final int n2 = inputLen + n;
        if (this._currentSegment == null || n2 > this._currentSegment.length) {
            this._currentSegment = this.buf(n2);
        }
        if (inputLen > 0) {
            System.arraycopy(inputBuffer, inputStart, this._currentSegment, 0, inputLen);
        }
        this._segmentSize = 0;
        this._currentSize = inputLen;
    }
    
    private void expand(final int n) {
        if (this._segments == null) {
            this._segments = new ArrayList<char[]>();
        }
        final char[] currentSegment = this._currentSegment;
        this._hasSegments = true;
        this._segments.add(currentSegment);
        this._segmentSize += currentSegment.length;
        this._currentSize = 0;
        final int length = currentSegment.length;
        int n2 = length + (length >> 1);
        if (n2 < 1000) {
            n2 = 1000;
        }
        else if (n2 > 262144) {
            n2 = 262144;
        }
        this._currentSegment = this.carr(n2);
    }
    
    private char[] resultArray() {
        if (this._resultString != null) {
            return this._resultString.toCharArray();
        }
        if (this._inputStart >= 0) {
            final int inputLen = this._inputLen;
            if (inputLen < 1) {
                return TextBuffer.NO_CHARS;
            }
            final int inputStart = this._inputStart;
            return Arrays.copyOf(this._inputBuffer, inputLen);
        }
        else {
            final int size = this.size();
            if (size < 1) {
                return TextBuffer.NO_CHARS;
            }
            int n = 0;
            final char[] carr = this.carr(size);
            if (this._segments != null) {
                for (int i = 0; i < this._segments.size(); ++i) {
                    final char[] array = this._segments.get(i);
                    final int length = array.length;
                    System.arraycopy(array, 0, carr, n, length);
                    n += length;
                }
            }
            System.arraycopy(this._currentSegment, 0, carr, n, this._currentSize);
            return carr;
        }
    }
    
    private char[] carr(final int n) {
        return new char[n];
    }
    
    static {
        NO_CHARS = new char[0];
    }
}
