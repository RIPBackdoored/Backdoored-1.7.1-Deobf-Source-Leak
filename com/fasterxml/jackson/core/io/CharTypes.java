package com.fasterxml.jackson.core.io;

import java.util.Arrays;

public final class CharTypes
{
    private static final char[] HC;
    private static final byte[] HB;
    private static final int[] sInputCodes;
    private static final int[] sInputCodesUTF8;
    private static final int[] sInputCodesJsNames;
    private static final int[] sInputCodesUtf8JsNames;
    private static final int[] sInputCodesComment;
    private static final int[] sInputCodesWS;
    private static final int[] sOutputEscapes128;
    private static final int[] sHexValues;
    
    public CharTypes() {
        super();
    }
    
    public static int[] getInputCodeLatin1() {
        return CharTypes.sInputCodes;
    }
    
    public static int[] getInputCodeUtf8() {
        return CharTypes.sInputCodesUTF8;
    }
    
    public static int[] getInputCodeLatin1JsNames() {
        return CharTypes.sInputCodesJsNames;
    }
    
    public static int[] getInputCodeUtf8JsNames() {
        return CharTypes.sInputCodesUtf8JsNames;
    }
    
    public static int[] getInputCodeComment() {
        return CharTypes.sInputCodesComment;
    }
    
    public static int[] getInputCodeWS() {
        return CharTypes.sInputCodesWS;
    }
    
    public static int[] get7BitOutputEscapes() {
        return CharTypes.sOutputEscapes128;
    }
    
    public static int charToHex(final int n) {
        return (n > 127) ? -1 : CharTypes.sHexValues[n];
    }
    
    public static void appendQuoted(final StringBuilder sb, final String s) {
        final int[] sOutputEscapes128 = CharTypes.sOutputEscapes128;
        final int length = sOutputEscapes128.length;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 >= length || sOutputEscapes128[char1] == 0) {
                sb.append(char1);
            }
            else {
                sb.append('\\');
                final int n = sOutputEscapes128[char1];
                if (n < 0) {
                    sb.append('u');
                    sb.append('0');
                    sb.append('0');
                    final char c = char1;
                    sb.append(CharTypes.HC[c >> 4]);
                    sb.append(CharTypes.HC[c & '\u000f']);
                }
                else {
                    sb.append((char)n);
                }
            }
        }
    }
    
    public static char[] copyHexChars() {
        return CharTypes.HC.clone();
    }
    
    public static byte[] copyHexBytes() {
        return CharTypes.HB.clone();
    }
    
    static {
        HC = "0123456789ABCDEF".toCharArray();
        final int length = CharTypes.HC.length;
        HB = new byte[length];
        for (int i = 0; i < length; ++i) {
            CharTypes.HB[i] = (byte)CharTypes.HC[i];
        }
        final int[] sInputCodes2 = new int[256];
        for (int j = 0; j < 32; ++j) {
            sInputCodes2[j] = -1;
        }
        sInputCodes2[92] = (sInputCodes2[34] = 1);
        sInputCodes = sInputCodes2;
        final int[] sInputCodesUTF9 = new int[CharTypes.sInputCodes.length];
        System.arraycopy(CharTypes.sInputCodes, 0, sInputCodesUTF9, 0, sInputCodesUTF9.length);
        for (int k = 128; k < 256; ++k) {
            int n;
            if ((k & 0xE0) == 0xC0) {
                n = 2;
            }
            else if ((k & 0xF0) == 0xE0) {
                n = 3;
            }
            else if ((k & 0xF8) == 0xF0) {
                n = 4;
            }
            else {
                n = -1;
            }
            sInputCodesUTF9[k] = n;
        }
        sInputCodesUTF8 = sInputCodesUTF9;
        final int[] sInputCodesJsNames2 = new int[256];
        Arrays.fill(sInputCodesJsNames2, -1);
        for (int l = 33; l < 256; ++l) {
            if (Character.isJavaIdentifierPart((char)l)) {
                sInputCodesJsNames2[l] = 0;
            }
        }
        sInputCodesJsNames2[64] = 0;
        sInputCodesJsNames2[42] = (sInputCodesJsNames2[35] = 0);
        sInputCodesJsNames2[43] = (sInputCodesJsNames2[45] = 0);
        sInputCodesJsNames = sInputCodesJsNames2;
        final int[] sInputCodesUtf8JsNames2 = new int[256];
        System.arraycopy(CharTypes.sInputCodesJsNames, 0, sInputCodesUtf8JsNames2, 0, sInputCodesUtf8JsNames2.length);
        Arrays.fill(sInputCodesUtf8JsNames2, 128, 128, 0);
        sInputCodesUtf8JsNames = sInputCodesUtf8JsNames2;
        final int[] sInputCodesComment2 = new int[256];
        System.arraycopy(CharTypes.sInputCodesUTF8, 128, sInputCodesComment2, 128, 128);
        Arrays.fill(sInputCodesComment2, 0, 32, -1);
        sInputCodesComment2[9] = 0;
        sInputCodesComment2[10] = 10;
        sInputCodesComment2[13] = 13;
        sInputCodesComment2[42] = 42;
        sInputCodesComment = sInputCodesComment2;
        final int[] sInputCodesWS2 = new int[256];
        System.arraycopy(CharTypes.sInputCodesUTF8, 128, sInputCodesWS2, 128, 128);
        Arrays.fill(sInputCodesWS2, 0, 32, -1);
        sInputCodesWS2[9] = (sInputCodesWS2[32] = 1);
        sInputCodesWS2[10] = 10;
        sInputCodesWS2[13] = 13;
        sInputCodesWS2[47] = 47;
        sInputCodesWS2[35] = 35;
        sInputCodesWS = sInputCodesWS2;
        final int[] sOutputEscapes129 = new int[128];
        for (int n2 = 0; n2 < 32; ++n2) {
            sOutputEscapes129[n2] = -1;
        }
        sOutputEscapes129[34] = 34;
        sOutputEscapes129[92] = 92;
        sOutputEscapes129[8] = 98;
        sOutputEscapes129[9] = 116;
        sOutputEscapes129[12] = 102;
        sOutputEscapes129[10] = 110;
        sOutputEscapes129[13] = 114;
        sOutputEscapes128 = sOutputEscapes129;
        Arrays.fill(sHexValues = new int[128], -1);
        for (int n3 = 0; n3 < 10; ++n3) {
            CharTypes.sHexValues[48 + n3] = n3;
        }
        for (int n4 = 0; n4 < 6; ++n4) {
            CharTypes.sHexValues[97 + n4] = 10 + n4;
            CharTypes.sHexValues[65 + n4] = 10 + n4;
        }
    }
}
