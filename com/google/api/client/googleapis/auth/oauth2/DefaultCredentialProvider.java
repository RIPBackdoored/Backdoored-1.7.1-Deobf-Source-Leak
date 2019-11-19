package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.auth.oauth2.TokenResponse;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.security.AccessControlException;
import java.util.Locale;
import java.io.File;
import java.io.IOException;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Beta;

@Beta
class DefaultCredentialProvider extends SystemEnvironmentProvider
{
    static final String CREDENTIAL_ENV_VAR = "GOOGLE_APPLICATION_CREDENTIALS";
    static final String WELL_KNOWN_CREDENTIALS_FILE = "application_default_credentials.json";
    static final String CLOUDSDK_CONFIG_DIRECTORY = "gcloud";
    static final String HELP_PERMALINK = "https://developers.google.com/accounts/docs/application-default-credentials";
    static final String APP_ENGINE_CREDENTIAL_CLASS = "com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential$AppEngineCredentialWrapper";
    static final String CLOUD_SHELL_ENV_VAR = "DEVSHELL_CLIENT_PORT";
    private GoogleCredential cachedCredential;
    private Environment detectedEnvironment;
    
    DefaultCredentialProvider() {
        super();
        this.cachedCredential = null;
        this.detectedEnvironment = null;
    }
    
    final GoogleCredential getDefaultCredential(final HttpTransport httpTransport, final JsonFactory jsonFactory) throws IOException {
        synchronized (this) {
            if (this.cachedCredential == null) {
                this.cachedCredential = this.getDefaultCredentialUnsynchronized(httpTransport, jsonFactory);
            }
            if (this.cachedCredential != null) {
                return this.cachedCredential;
            }
        }
        throw new IOException(String.format("The Application Default Credentials are not available. They are available if running on Google App Engine, Google Compute Engine, or Google Cloud Shell. Otherwise, the environment variable %s must be defined pointing to a file defining the credentials. See %s for more information.", "GOOGLE_APPLICATION_CREDENTIALS", "https://developers.google.com/accounts/docs/application-default-credentials"));
    }
    
    private final GoogleCredential getDefaultCredentialUnsynchronized(final HttpTransport httpTransport, final JsonFactory jsonFactory) throws IOException {
        if (this.detectedEnvironment == null) {
            this.detectedEnvironment = this.detectEnvironment(httpTransport);
        }
        switch (this.detectedEnvironment) {
            case ENVIRONMENT_VARIABLE:
                return this.getCredentialUsingEnvironmentVariable(httpTransport, jsonFactory);
            case WELL_KNOWN_FILE:
                return this.getCredentialUsingWellKnownFile(httpTransport, jsonFactory);
            case APP_ENGINE:
                return this.getAppEngineCredential(httpTransport, jsonFactory);
            case CLOUD_SHELL:
                return this.getCloudShellCredential(jsonFactory);
            case COMPUTE_ENGINE:
                return this.getComputeCredential(httpTransport, jsonFactory);
            default:
                return null;
        }
    }
    
    private final File getWellKnownCredentialsFile() {
        File file;
        if (this.getProperty("os.name", "").toLowerCase(Locale.US).indexOf("windows") >= 0) {
            file = new File(new File(this.getEnv("APPDATA")), "gcloud");
        }
        else {
            file = new File(new File(this.getProperty("user.home", ""), ".config"), "gcloud");
        }
        return new File(file, "application_default_credentials.json");
    }
    
    boolean fileExists(final File file) {
        return file.exists() && !file.isDirectory();
    }
    
    String getProperty(final String s, final String s2) {
        return System.getProperty(s, s2);
    }
    
    Class<?> forName(final String s) throws ClassNotFoundException {
        return Class.forName(s);
    }
    
    private final Environment detectEnvironment(final HttpTransport httpTransport) throws IOException {
        if (this.runningUsingEnvironmentVariable()) {
            return Environment.ENVIRONMENT_VARIABLE;
        }
        if (this.runningUsingWellKnownFile()) {
            return Environment.WELL_KNOWN_FILE;
        }
        if (this.runningOnAppEngine()) {
            return Environment.APP_ENGINE;
        }
        if (this.runningOnCloudShell()) {
            return Environment.CLOUD_SHELL;
        }
        if (OAuth2Utils.runningOnComputeEngine(httpTransport, this)) {
            return Environment.COMPUTE_ENGINE;
        }
        return Environment.UNKNOWN;
    }
    
    private boolean runningUsingEnvironmentVariable() throws IOException {
        final String env = this.getEnv("GOOGLE_APPLICATION_CREDENTIALS");
        if (env == null || env.length() == 0) {
            return false;
        }
        try {
            final File file = new File(env);
            if (!file.exists() || file.isDirectory()) {
                throw new IOException(String.format("Error reading credential file from environment variable %s, value '%s': File does not exist.", "GOOGLE_APPLICATION_CREDENTIALS", env));
            }
            return true;
        }
        catch (AccessControlException ex) {
            return false;
        }
    }
    
    private GoogleCredential getCredentialUsingEnvironmentVariable(final HttpTransport httpTransport, final JsonFactory jsonFactory) throws IOException {
        final String env = this.getEnv("GOOGLE_APPLICATION_CREDENTIALS");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(env);
            return GoogleCredential.fromStream(inputStream, httpTransport, jsonFactory);
        }
        catch (IOException ex) {
            throw OAuth2Utils.<IOException>exceptionWithCause(new IOException(String.format("Error reading credential file from environment variable %s, value '%s': %s", "GOOGLE_APPLICATION_CREDENTIALS", env, ex.getMessage())), ex);
        }
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
    
    private boolean runningUsingWellKnownFile() {
        final File wellKnownCredentialsFile = this.getWellKnownCredentialsFile();
        try {
            return this.fileExists(wellKnownCredentialsFile);
        }
        catch (AccessControlException ex) {
            return false;
        }
    }
    
    private GoogleCredential getCredentialUsingWellKnownFile(final HttpTransport httpTransport, final JsonFactory jsonFactory) throws IOException {
        final File wellKnownCredentialsFile = this.getWellKnownCredentialsFile();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(wellKnownCredentialsFile);
            return GoogleCredential.fromStream(inputStream, httpTransport, jsonFactory);
        }
        catch (IOException ex) {
            throw new IOException(String.format("Error reading credential file from location %s: %s", wellKnownCredentialsFile, ex.getMessage()));
        }
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
    
    private boolean runningOnAppEngine() {
        Class<?> forName;
        try {
            forName = this.forName("com.google.appengine.api.utils.SystemProperty");
        }
        catch (ClassNotFoundException ex8) {
            return false;
        }
        NoSuchFieldException ex;
        try {
            final Field field = forName.getField("environment");
            return field.getType().getMethod("value", (Class<?>[])new Class[0]).invoke(field.get(null), new Object[0]) != null;
        }
        catch (NoSuchFieldException ex2) {
            ex = ex2;
        }
        catch (SecurityException ex3) {
            ex = (NoSuchFieldException)ex3;
        }
        catch (IllegalArgumentException ex4) {
            ex = (NoSuchFieldException)ex4;
        }
        catch (IllegalAccessException ex5) {
            ex = (NoSuchFieldException)ex5;
        }
        catch (NoSuchMethodException ex6) {
            ex = (NoSuchFieldException)ex6;
        }
        catch (InvocationTargetException ex7) {
            ex = (NoSuchFieldException)ex7;
        }
        throw OAuth2Utils.<RuntimeException>exceptionWithCause(new RuntimeException(String.format("Unexpcted error trying to determine if runnning on Google App Engine: %s", ex.getMessage())), ex);
    }
    
    private final GoogleCredential getAppEngineCredential(final HttpTransport httpTransport, final JsonFactory jsonFactory) throws IOException {
        ClassNotFoundException ex;
        try {
            return (GoogleCredential)this.forName("com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential$AppEngineCredentialWrapper").getConstructor(HttpTransport.class, JsonFactory.class).newInstance(httpTransport, jsonFactory);
        }
        catch (ClassNotFoundException ex2) {
            ex = ex2;
        }
        catch (NoSuchMethodException ex3) {
            ex = (ClassNotFoundException)ex3;
        }
        catch (InstantiationException ex4) {
            ex = (ClassNotFoundException)ex4;
        }
        catch (IllegalAccessException ex5) {
            ex = (ClassNotFoundException)ex5;
        }
        catch (InvocationTargetException ex6) {
            ex = (ClassNotFoundException)ex6;
        }
        throw OAuth2Utils.<IOException>exceptionWithCause(new IOException(String.format("Application Default Credentials failed to create the Google App Engine service account credentials class %s. Check that the component 'google-api-client-appengine' is deployed.", "com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential$AppEngineCredentialWrapper")), ex);
    }
    
    private boolean runningOnCloudShell() {
        return this.getEnv("DEVSHELL_CLIENT_PORT") != null;
    }
    
    private GoogleCredential getCloudShellCredential(final JsonFactory jsonFactory) {
        return new CloudShellCredential(Integer.parseInt(this.getEnv("DEVSHELL_CLIENT_PORT")), jsonFactory);
    }
    
    private final GoogleCredential getComputeCredential(final HttpTransport httpTransport, final JsonFactory jsonFactory) {
        return new ComputeGoogleCredential(httpTransport, jsonFactory);
    }
}
