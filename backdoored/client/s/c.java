package r.k.s;

import com.google.api.services.sheets.v4.model.ValueRange;
import java.util.List;
import java.security.GeneralSecurityException;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.services.sheets.v4.Sheets;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.auth.oauth2.Credential;

class c
{
    private static String pk;
    public static final boolean pq;
    public static final int pv;
    public static final boolean pj;
    
    c() {
        super();
    }
    
    private static Credential sx() throws IOException {
        return GoogleCredential.fromStream(c.class.getResourceAsStream("/resources/backdoored-client-340b78ae95c4.json")).createScoped(Collections.<String>singleton("https://www.googleapis.com/auth/spreadsheets"));
    }
    
    private static Sheets sk() throws GeneralSecurityException, IOException {
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), sx()).setApplicationName("Backdoored License Handler").build();
    }
    
    private static String j(final String p0) throws IOException, GeneralSecurityException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: aconst_null    
        //     5: areturn        
        //     6: nop            
        //     7: nop            
        //     8: invokestatic    r/k/s/c.sk:()Lcom/google/api/services/sheets/v4/Sheets;
        //    11: invokevirtual   com/google/api/services/sheets/v4/Sheets.spreadsheets:()Lcom/google/api/services/sheets/v4/Sheets$Spreadsheets;
        //    14: invokevirtual   com/google/api/services/sheets/v4/Sheets$Spreadsheets.values:()Lcom/google/api/services/sheets/v4/Sheets$Spreadsheets$Values;
        //    17: getstatic       r/k/s/c.pk:Ljava/lang/String;
        //    20: aload_0        
        //    21: invokevirtual   com/google/api/services/sheets/v4/Sheets$Spreadsheets$Values.get:(Ljava/lang/String;Ljava/lang/String;)Lcom/google/api/services/sheets/v4/Sheets$Spreadsheets$Values$Get;
        //    24: invokevirtual   com/google/api/services/sheets/v4/Sheets$Spreadsheets$Values$Get.execute:()Ljava/lang/Object;
        //    27: checkcast       Lcom/google/api/services/sheets/v4/model/ValueRange;
        //    30: astore_1       
        //    31: iconst_0       
        //    32: istore_2       
        //    33: iload_2        
        //    34: aload_1        
        //    35: invokevirtual   com/google/api/services/sheets/v4/model/ValueRange.getValues:()Ljava/util/List;
        //    38: invokeinterface java/util/List.size:()I
        //    43: if_icmpge       66
        //    46: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //    49: aload_1        
        //    50: invokevirtual   com/google/api/services/sheets/v4/model/ValueRange.getValues:()Ljava/util/List;
        //    53: iload_2        
        //    54: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    59: invokevirtual   java/io/PrintStream.println:(Ljava/lang/Object;)V
        //    62: iinc            2, 1
        //    65: nop            
        //    66: aload_1        
        //    67: invokevirtual   com/google/api/services/sheets/v4/model/ValueRange.getValues:()Ljava/util/List;
        //    70: ifnull          90
        //    73: aload_1        
        //    74: invokevirtual   com/google/api/services/sheets/v4/model/ValueRange.getValues:()Ljava/util/List;
        //    77: iconst_0       
        //    78: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    83: checkcast       Ljava/util/List;
        //    86: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //    89: nop            
        //    90: ldc             ""
        //    92: astore_2       
        //    93: aload_2        
        //    94: areturn        
        //    Exceptions:
        //  throws java.io.IOException
        //  throws java.security.GeneralSecurityException
        //    StackMapTable: 00 06 FF 00 04 00 06 07 00 60 00 00 00 00 01 00 00 01 FF 00 1A 00 06 07 00 60 07 00 76 01 01 01 01 00 00 20 17 41 07 00 60
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static List<List<Object>> e(final String s) throws IOException, GeneralSecurityException {
        return sk().spreadsheets().values().get(c.pk, s).execute().getValues();
    }
    
    public static boolean o(final String s) throws IOException, GeneralSecurityException {
        final ValueRange valueRange = sk().spreadsheets().values().get(c.pk, "A2:A").execute();
        final int n = 0;
        return n < valueRange.getValues().size() && valueRange.getValues().get(n).toString().replace("[", "").replace("]", "").equals(s);
    }
    
    static {
        c.pk = "1_kxn8nNafDEUPpKNZ6ozlUaASlODC_Sf9hIniJvH33E";
    }
}
