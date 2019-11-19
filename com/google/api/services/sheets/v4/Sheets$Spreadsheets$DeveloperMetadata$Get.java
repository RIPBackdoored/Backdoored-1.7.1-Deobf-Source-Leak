package com.google.api.services.sheets.v4;

import com.google.api.client.util.GenericData;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.http.HttpRequest;
import java.io.IOException;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Key;
import com.google.api.services.sheets.v4.model.DeveloperMetadata;

public class Get extends SheetsRequest<com.google.api.services.sheets.v4.model.DeveloperMetadata>
{
    private static final String REST_PATH = "v4/spreadsheets/{spreadsheetId}/developerMetadata/{metadataId}";
    @Key
    private String spreadsheetId;
    @Key
    private Integer metadataId;
    final /* synthetic */ DeveloperMetadata this$2;
    
    protected Get(final DeveloperMetadata this$2, final String s, final Integer n) {
        this.this$2 = this$2;
        super(this$2.this$1.this$0, "GET", "v4/spreadsheets/{spreadsheetId}/developerMetadata/{metadataId}", (Object)null, com.google.api.services.sheets.v4.model.DeveloperMetadata.class);
        this.spreadsheetId = Preconditions.<String>checkNotNull(s, (Object)"Required parameter spreadsheetId must be specified.");
        this.metadataId = Preconditions.<Integer>checkNotNull(n, (Object)"Required parameter metadataId must be specified.");
    }
    
    public HttpResponse executeUsingHead() throws IOException {
        return super.executeUsingHead();
    }
    
    public HttpRequest buildHttpRequestUsingHead() throws IOException {
        return super.buildHttpRequestUsingHead();
    }
    
    @Override
    public Get set$Xgafv(final String s) {
        return (Get)super.set$Xgafv(s);
    }
    
    @Override
    public Get setAccessToken(final String accessToken) {
        return (Get)super.setAccessToken(accessToken);
    }
    
    @Override
    public Get setAlt(final String alt) {
        return (Get)super.setAlt(alt);
    }
    
    @Override
    public Get setCallback(final String callback) {
        return (Get)super.setCallback(callback);
    }
    
    @Override
    public Get setFields(final String fields) {
        return (Get)super.setFields(fields);
    }
    
    @Override
    public Get setKey(final String key) {
        return (Get)super.setKey(key);
    }
    
    @Override
    public Get setOauthToken(final String oauthToken) {
        return (Get)super.setOauthToken(oauthToken);
    }
    
    @Override
    public Get setPrettyPrint(final Boolean prettyPrint) {
        return (Get)super.setPrettyPrint(prettyPrint);
    }
    
    @Override
    public Get setQuotaUser(final String quotaUser) {
        return (Get)super.setQuotaUser(quotaUser);
    }
    
    @Override
    public Get setUploadType(final String uploadType) {
        return (Get)super.setUploadType(uploadType);
    }
    
    @Override
    public Get setUploadProtocol(final String uploadProtocol) {
        return (Get)super.setUploadProtocol(uploadProtocol);
    }
    
    public String getSpreadsheetId() {
        return this.spreadsheetId;
    }
    
    public Get setSpreadsheetId(final String spreadsheetId) {
        this.spreadsheetId = spreadsheetId;
        return this;
    }
    
    public Integer getMetadataId() {
        return this.metadataId;
    }
    
    public Get setMetadataId(final Integer metadataId) {
        this.metadataId = metadataId;
        return this;
    }
    
    @Override
    public Get set(final String s, final Object o) {
        return (Get)super.set(s, o);
    }
    
    @Override
    public /* bridge */ SheetsRequest set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ SheetsRequest setUploadProtocol(final String uploadProtocol) {
        return this.setUploadProtocol(uploadProtocol);
    }
    
    @Override
    public /* bridge */ SheetsRequest setUploadType(final String uploadType) {
        return this.setUploadType(uploadType);
    }
    
    @Override
    public /* bridge */ SheetsRequest setQuotaUser(final String quotaUser) {
        return this.setQuotaUser(quotaUser);
    }
    
    @Override
    public /* bridge */ SheetsRequest setPrettyPrint(final Boolean prettyPrint) {
        return this.setPrettyPrint(prettyPrint);
    }
    
    @Override
    public /* bridge */ SheetsRequest setOauthToken(final String oauthToken) {
        return this.setOauthToken(oauthToken);
    }
    
    @Override
    public /* bridge */ SheetsRequest setKey(final String key) {
        return this.setKey(key);
    }
    
    @Override
    public /* bridge */ SheetsRequest setFields(final String fields) {
        return this.setFields(fields);
    }
    
    @Override
    public /* bridge */ SheetsRequest setCallback(final String callback) {
        return this.setCallback(callback);
    }
    
    @Override
    public /* bridge */ SheetsRequest setAlt(final String alt) {
        return this.setAlt(alt);
    }
    
    @Override
    public /* bridge */ SheetsRequest setAccessToken(final String accessToken) {
        return this.setAccessToken(accessToken);
    }
    
    @Override
    public /* bridge */ SheetsRequest set$Xgafv(final String s) {
        return this.set$Xgafv(s);
    }
    
    @Override
    public /* bridge */ AbstractGoogleJsonClientRequest set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClientRequest set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ GenericData set(final String s, final Object o) {
        return this.set(s, o);
    }
}
