package com.google.api.client.googleapis.auth.oauth2;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.json.JsonFactory;

public class CloudShellCredential extends GoogleCredential
{
    private static final int ACCESS_TOKEN_INDEX = 2;
    private static final int READ_TIMEOUT_MS = 5000;
    protected static final String GET_AUTH_TOKEN_REQUEST = "2\n[]";
    private final int authPort;
    private final JsonFactory jsonFactory;
    
    public CloudShellCredential(final int authPort, final JsonFactory jsonFactory) {
        super();
        this.authPort = authPort;
        this.jsonFactory = jsonFactory;
    }
    
    protected int getAuthPort() {
        return this.authPort;
    }
    
    @Override
    protected TokenResponse executeRefreshToken() throws IOException {
        final Socket socket = new Socket("localhost", this.getAuthPort());
        socket.setSoTimeout(5000);
        final TokenResponse tokenResponse = new TokenResponse();
        try {
            new PrintWriter(socket.getOutputStream(), true).println("2\n[]");
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedReader.readLine();
            tokenResponse.setAccessToken(((List<Object>)this.jsonFactory.createJsonParser(bufferedReader).<Object>parseArray(LinkedList.class, Object.class)).get(2).toString());
        }
        finally {
            socket.close();
        }
        return tokenResponse;
    }
}
