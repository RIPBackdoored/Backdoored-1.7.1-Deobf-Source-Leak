package javassist.bytecode;

import javassist.CtClass;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.DataInputStream;

public class SignatureAttribute extends AttributeInfo
{
    public static final String tag = "Signature";
    
    SignatureAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) throws IOException {
        super(constPool, n, dataInputStream);
    }
    
    public SignatureAttribute(final ConstPool constPool, final String s) {
        super(constPool, "Signature");
        final int addUtf8Info = constPool.addUtf8Info(s);
        this.set(new byte[] { (byte)(addUtf8Info >>> 8), (byte)addUtf8Info });
    }
    
    public String getSignature() {
        return this.getConstPool().getUtf8Info(ByteArray.readU16bit(this.get(), 0));
    }
    
    public void setSignature(final String s) {
        ByteArray.write16bit(this.getConstPool().addUtf8Info(s), this.info, 0);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map map) {
        return new SignatureAttribute(constPool, this.getSignature());
    }
    
    @Override
    void renameClass(final String s, final String s2) {
        this.setSignature(renameClass(this.getSignature(), s, s2));
    }
    
    @Override
    void renameClass(final Map map) {
        this.setSignature(renameClass(this.getSignature(), map));
    }
    
    static String renameClass(final String s, final String s2, final String s3) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(s2, s3);
        return renameClass(s, hashMap);
    }
    
    static String renameClass(final String s, final Map map) {
        if (map == null) {
            return s;
        }
        final StringBuilder sb = new StringBuilder();
        int n = 0;
        int n2 = 0;
        while (true) {
            final int index = s.indexOf(76, n2);
            if (index < 0) {
                break;
            }
            final StringBuilder sb2 = new StringBuilder();
            int n3 = index;
            char char1;
            try {
                while ((char1 = s.charAt(++n3)) != ';') {
                    sb2.append(char1);
                    if (char1 == '<') {
                        char char2;
                        while ((char2 = s.charAt(++n3)) != '>') {
                            sb2.append(char2);
                        }
                        sb2.append(char2);
                    }
                }
            }
            catch (IndexOutOfBoundsException ex) {
                break;
            }
            n2 = n3 + 1;
            final String s2 = map.get(sb2.toString());
            if (s2 == null) {
                continue;
            }
            sb.append(s.substring(n, index));
            sb.append('L');
            sb.append(s2);
            sb.append(char1);
            n = n2;
        }
        return s;
    }
    
    private static boolean isNamePart(final int n) {
        return n != 59 && n != 60;
    }
    
    public static ClassSignature toClassSignature(final String s) throws BadBytecode {
        try {
            return parseSig(s);
        }
        catch (IndexOutOfBoundsException ex) {
            throw error(s);
        }
    }
    
    public static MethodSignature toMethodSignature(final String s) throws BadBytecode {
        try {
            return parseMethodSig(s);
        }
        catch (IndexOutOfBoundsException ex) {
            throw error(s);
        }
    }
    
    public static ObjectType toFieldSignature(final String s) throws BadBytecode {
        try {
            return parseObjectType(s, new Cursor(), false);
        }
        catch (IndexOutOfBoundsException ex) {
            throw error(s);
        }
    }
    
    public static Type toTypeSignature(final String s) throws BadBytecode {
        try {
            return parseType(s, new Cursor());
        }
        catch (IndexOutOfBoundsException ex) {
            throw error(s);
        }
    }
    
    private static ClassSignature parseSig(final String s) throws BadBytecode, IndexOutOfBoundsException {
        final Cursor cursor = new Cursor();
        final TypeParameter[] typeParams = parseTypeParams(s, cursor);
        final ClassType classType = parseClassType(s, cursor);
        final int length = s.length();
        final ArrayList<ClassType> list = new ArrayList<ClassType>();
        while (cursor.position < length && s.charAt(cursor.position) == 'L') {
            list.add(parseClassType(s, cursor));
        }
        return new ClassSignature(typeParams, classType, list.<ClassType>toArray(new ClassType[list.size()]));
    }
    
    private static MethodSignature parseMethodSig(final String s) throws BadBytecode {
        final Cursor cursor = new Cursor();
        final TypeParameter[] typeParams = parseTypeParams(s, cursor);
        if (s.charAt(cursor.position++) != '(') {
            throw error(s);
        }
        final ArrayList<Type> list = new ArrayList<Type>();
        while (s.charAt(cursor.position) != ')') {
            list.add(parseType(s, cursor));
        }
        final Cursor cursor2 = cursor;
        ++cursor2.position;
        final Type type = parseType(s, cursor);
        final int length = s.length();
        final ArrayList<ObjectType> list2 = new ArrayList<ObjectType>();
        while (cursor.position < length && s.charAt(cursor.position) == '^') {
            final Cursor cursor3 = cursor;
            ++cursor3.position;
            final ObjectType objectType = parseObjectType(s, cursor, false);
            if (objectType instanceof ArrayType) {
                throw error(s);
            }
            list2.add(objectType);
        }
        return new MethodSignature(typeParams, list.<Type>toArray(new Type[list.size()]), type, list2.<ObjectType>toArray(new ObjectType[list2.size()]));
    }
    
    private static TypeParameter[] parseTypeParams(final String s, final Cursor cursor) throws BadBytecode {
        final ArrayList<TypeParameter> list = new ArrayList<TypeParameter>();
        if (s.charAt(cursor.position) == '<') {
            ++cursor.position;
            while (s.charAt(cursor.position) != '>') {
                final int position = cursor.position;
                final int index = cursor.indexOf(s, 58);
                final ObjectType objectType = parseObjectType(s, cursor, true);
                final ArrayList<ObjectType> list2 = new ArrayList<ObjectType>();
                while (s.charAt(cursor.position) == ':') {
                    ++cursor.position;
                    list2.add(parseObjectType(s, cursor, false));
                }
                list.add(new TypeParameter(s, position, index, objectType, list2.<ObjectType>toArray(new ObjectType[list2.size()])));
            }
            ++cursor.position;
        }
        return list.<TypeParameter>toArray(new TypeParameter[list.size()]);
    }
    
    private static ObjectType parseObjectType(final String s, final Cursor cursor, final boolean b) throws BadBytecode {
        final int position = cursor.position;
        switch (s.charAt(position)) {
            case 'L':
                return parseClassType2(s, cursor, null);
            case 'T':
                return new TypeVariable(s, position + 1, cursor.indexOf(s, 59));
            case '[':
                return parseArray(s, cursor);
            default:
                if (b) {
                    return null;
                }
                throw error(s);
        }
    }
    
    private static ClassType parseClassType(final String s, final Cursor cursor) throws BadBytecode {
        if (s.charAt(cursor.position) == 'L') {
            return parseClassType2(s, cursor, null);
        }
        throw error(s);
    }
    
    private static ClassType parseClassType2(final String s, final Cursor cursor, final ClassType classType) throws BadBytecode {
        final int n = ++cursor.position;
        char c;
        do {
            c = s.charAt(cursor.position++);
        } while (c != '$' && c != '<' && c != ';');
        final int n2 = cursor.position - 1;
        TypeArgument[] typeArgs;
        if (c == '<') {
            typeArgs = parseTypeArgs(s, cursor);
            c = s.charAt(cursor.position++);
        }
        else {
            typeArgs = null;
        }
        final ClassType make = ClassType.make(s, n, n2, typeArgs, classType);
        if (c == '$' || c == '.') {
            --cursor.position;
            return parseClassType2(s, cursor, make);
        }
        return make;
    }
    
    private static TypeArgument[] parseTypeArgs(final String s, final Cursor cursor) throws BadBytecode {
        final ArrayList<TypeArgument> list = new ArrayList<TypeArgument>();
        char char1;
        while ((char1 = s.charAt(cursor.position++)) != '>') {
            TypeArgument typeArgument;
            if (char1 == '*') {
                typeArgument = new TypeArgument(null, '*');
            }
            else {
                if (char1 != '+' && char1 != '-') {
                    char1 = ' ';
                    --cursor.position;
                }
                typeArgument = new TypeArgument(parseObjectType(s, cursor, false), char1);
            }
            list.add(typeArgument);
        }
        return list.<TypeArgument>toArray(new TypeArgument[list.size()]);
    }
    
    private static ObjectType parseArray(final String s, final Cursor cursor) throws BadBytecode {
        int n = 1;
        while (s.charAt(++cursor.position) == '[') {
            ++n;
        }
        return new ArrayType(n, parseType(s, cursor));
    }
    
    private static Type parseType(final String s, final Cursor cursor) throws BadBytecode {
        Type objectType = parseObjectType(s, cursor, true);
        if (objectType == null) {
            objectType = new BaseType(s.charAt(cursor.position++));
        }
        return objectType;
    }
    
    private static BadBytecode error(final String s) {
        return new BadBytecode("bad signature: " + s);
    }
    
    static /* synthetic */ BadBytecode access$000(final String s) {
        return error(s);
    }
}
