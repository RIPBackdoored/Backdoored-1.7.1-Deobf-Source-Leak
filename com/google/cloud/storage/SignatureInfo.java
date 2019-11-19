package com.google.cloud.storage;

import java.util.HashMap;
import com.google.common.base.Preconditions;
import com.google.common.net.UrlEscapers;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.common.collect.ImmutableMap;
import java.net.URI;
import java.util.Map;

public class SignatureInfo
{
    public static final char COMPONENT_SEPARATOR = '\n';
    public static final String GOOG4_RSA_SHA256 = "GOOG4-RSA-SHA256";
    public static final String SCOPE = "/auto/storage/goog4_request";
    private final HttpMethod httpVerb;
    private final String contentMd5;
    private final String contentType;
    private final long expiration;
    private final Map<String, String> canonicalizedExtensionHeaders;
    private final URI canonicalizedResource;
    private final Storage.SignUrlOption.SignatureVersion signatureVersion;
    private final String accountEmail;
    private final long timestamp;
    private final String yearMonthDay;
    private final String exactDate;
    
    private SignatureInfo(final Builder builder) {
        super();
        this.httpVerb = builder.httpVerb;
        this.contentMd5 = builder.contentMd5;
        this.contentType = builder.contentType;
        this.expiration = builder.expiration;
        this.canonicalizedResource = builder.canonicalizedResource;
        this.signatureVersion = builder.signatureVersion;
        this.accountEmail = builder.accountEmail;
        this.timestamp = builder.timestamp;
        if (Storage.SignUrlOption.SignatureVersion.V4.equals(this.signatureVersion) && !builder.canonicalizedExtensionHeaders.containsKey("host")) {
            this.canonicalizedExtensionHeaders = (Map<String, String>)new ImmutableMap.Builder().putAll(builder.canonicalizedExtensionHeaders).put((Object)"host", (Object)"storage.googleapis.com").build();
        }
        else {
            this.canonicalizedExtensionHeaders = builder.canonicalizedExtensionHeaders;
        }
        final Date date = new Date(this.timestamp);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.yearMonthDay = simpleDateFormat.format(date);
        this.exactDate = simpleDateFormat2.format(date);
    }
    
    public String constructUnsignedPayload() {
        if (Storage.SignUrlOption.SignatureVersion.V4.equals(this.signatureVersion)) {
            return this.constructV4UnsignedPayload();
        }
        return this.constructV2UnsignedPayload();
    }
    
    private String constructV2UnsignedPayload() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.httpVerb.name()).append('\n');
        if (this.contentMd5 != null) {
            sb.append(this.contentMd5);
        }
        sb.append('\n');
        if (this.contentType != null) {
            sb.append(this.contentType);
        }
        sb.append('\n');
        sb.append(this.expiration).append('\n');
        if (this.canonicalizedExtensionHeaders != null) {
            sb.append((CharSequence)new CanonicalExtensionHeadersSerializer(Storage.SignUrlOption.SignatureVersion.V2).serialize(this.canonicalizedExtensionHeaders));
        }
        sb.append(this.canonicalizedResource);
        return sb.toString();
    }
    
    private String constructV4UnsignedPayload() {
        final StringBuilder sb = new StringBuilder();
        sb.append("GOOG4-RSA-SHA256").append('\n');
        sb.append(this.exactDate).append('\n');
        sb.append(this.yearMonthDay).append("/auto/storage/goog4_request").append('\n');
        sb.append(this.constructV4CanonicalRequestHash());
        return sb.toString();
    }
    
    private String constructV4CanonicalRequestHash() {
        final StringBuilder sb = new StringBuilder();
        final CanonicalExtensionHeadersSerializer canonicalExtensionHeadersSerializer = new CanonicalExtensionHeadersSerializer(Storage.SignUrlOption.SignatureVersion.V4);
        sb.append(this.httpVerb.name()).append('\n');
        sb.append(this.canonicalizedResource).append('\n');
        sb.append(this.constructV4QueryString()).append('\n');
        sb.append((CharSequence)canonicalExtensionHeadersSerializer.serialize(this.canonicalizedExtensionHeaders)).append('\n');
        sb.append((CharSequence)canonicalExtensionHeadersSerializer.serializeHeaderNames(this.canonicalizedExtensionHeaders)).append('\n');
        sb.append("UNSIGNED-PAYLOAD");
        return Hashing.sha256().hashString((CharSequence)sb.toString(), StandardCharsets.UTF_8).toString();
    }
    
    public String constructV4QueryString() {
        final StringBuilder serializeHeaderNames = new CanonicalExtensionHeadersSerializer(Storage.SignUrlOption.SignatureVersion.V4).serializeHeaderNames(this.canonicalizedExtensionHeaders);
        final StringBuilder sb = new StringBuilder();
        sb.append("X-Goog-Algorithm=").append("GOOG4-RSA-SHA256").append("&");
        sb.append("X-Goog-Credential=" + UrlEscapers.urlFormParameterEscaper().escape(this.accountEmail + "/" + this.yearMonthDay + "/auto/storage/goog4_request") + "&");
        sb.append("X-Goog-Date=" + this.exactDate + "&");
        sb.append("X-Goog-Expires=" + this.expiration + "&");
        sb.append("X-Goog-SignedHeaders=" + UrlEscapers.urlFormParameterEscaper().escape(serializeHeaderNames.toString()));
        return sb.toString();
    }
    
    public HttpMethod getHttpVerb() {
        return this.httpVerb;
    }
    
    public String getContentMd5() {
        return this.contentMd5;
    }
    
    public String getContentType() {
        return this.contentType;
    }
    
    public long getExpiration() {
        return this.expiration;
    }
    
    public Map<String, String> getCanonicalizedExtensionHeaders() {
        return this.canonicalizedExtensionHeaders;
    }
    
    public URI getCanonicalizedResource() {
        return this.canonicalizedResource;
    }
    
    public Storage.SignUrlOption.SignatureVersion getSignatureVersion() {
        return this.signatureVersion;
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
    
    public String getAccountEmail() {
        return this.accountEmail;
    }
    
    static /* synthetic */ HttpMethod access$900(final SignatureInfo signatureInfo) {
        return signatureInfo.httpVerb;
    }
    
    static /* synthetic */ String access$1000(final SignatureInfo signatureInfo) {
        return signatureInfo.contentMd5;
    }
    
    static /* synthetic */ String access$1100(final SignatureInfo signatureInfo) {
        return signatureInfo.contentType;
    }
    
    static /* synthetic */ long access$1200(final SignatureInfo signatureInfo) {
        return signatureInfo.expiration;
    }
    
    static /* synthetic */ Map access$1300(final SignatureInfo signatureInfo) {
        return signatureInfo.canonicalizedExtensionHeaders;
    }
    
    static /* synthetic */ URI access$1400(final SignatureInfo signatureInfo) {
        return signatureInfo.canonicalizedResource;
    }
    
    static /* synthetic */ Storage.SignUrlOption.SignatureVersion access$1500(final SignatureInfo signatureInfo) {
        return signatureInfo.signatureVersion;
    }
    
    static /* synthetic */ String access$1600(final SignatureInfo signatureInfo) {
        return signatureInfo.accountEmail;
    }
    
    static /* synthetic */ long access$1700(final SignatureInfo signatureInfo) {
        return signatureInfo.timestamp;
    }
    
    SignatureInfo(final Builder builder, final SignatureInfo$1 object) {
        this(builder);
    }
}
