package r.k.s;

import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class n
{
    public static final int slr;
    public static final boolean slf;
    
    public n() {
        super();
    }
    
    public static String d(final String s, final String s2, final String s3) throws IOException {
        final byte[] bytes = s3.getBytes(StandardCharsets.UTF_8);
        final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s + "/documents").openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
        httpURLConnection.setFixedLengthStreamingMode(bytes.length);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.connect();
        try {
            httpURLConnection.getOutputStream().write(bytes);
            final JsonObject jsonObject = new Gson().<JsonObject>fromJson(new InputStreamReader(httpURLConnection.getInputStream()), JsonObject.class);
            if (s2 != null && !s2.isEmpty()) {
                new StringBuilder().append(".").append(s2).toString();
            }
            httpURLConnection.disconnect();
            return s + "/" + jsonObject.get("key").getAsString() + "." + s2;
        }
        finally {
            httpURLConnection.disconnect();
        }
    }
}
