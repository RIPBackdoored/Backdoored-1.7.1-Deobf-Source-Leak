package com.google.api.services.sheets.v4;

import com.google.api.client.util.GenericData;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.util.Preconditions;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.client.util.Key;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;

public class BatchUpdate extends SheetsRequest<BatchUpdateValuesResponse>
{
    private static final String REST_PATH = "v4/spreadsheets/{spreadsheetId}/values:batchUpdate";
    @Key
    private String spreadsheetId;
    final /* synthetic */ Values this$2;
    
    protected BatchUpdate(final Values this$2, final String s, final BatchUpdateValuesRequest batchUpdateValuesRequest) {
        this.this$2 = this$2;
        super(this$2.this$1.this$0, "POST", "v4/spreadsheets/{spreadsheetId}/values:batchUpdate", batchUpdateValuesRequest, BatchUpdateValuesResponse.class);
        this.spreadsheetId = Preconditions.<String>checkNotNull(s, (Object)"Required parameter spreadsheetId must be specified.");
    }
    
    @Override
    public BatchUpdate set$Xgafv(final String s) {
        return (BatchUpdate)super.set$Xgafv(s);
    }
    
    @Override
    public BatchUpdate setAccessToken(final String accessToken) {
        return (BatchUpdate)super.setAccessToken(accessToken);
    }
    
    @Override
    public BatchUpdate setAlt(final String alt) {
        return (BatchUpdate)super.setAlt(alt);
    }
    
    @Override
    public BatchUpdate setCallback(final String callback) {
        return (BatchUpdate)super.setCallback(callback);
    }
    
    @Override
    public BatchUpdate setFields(final String fields) {
        return (BatchUpdate)super.setFields(fields);
    }
    
    @Override
    public BatchUpdate setKey(final String key) {
        return (BatchUpdate)super.setKey(key);
    }
    
    @Override
    public BatchUpdate setOauthToken(final String oauthToken) {
        return (BatchUpdate)super.setOauthToken(oauthToken);
    }
    
    @Override
    public BatchUpdate setPrettyPrint(final Boolean prettyPrint) {
        return (BatchUpdate)super.setPrettyPrint(prettyPrint);
    }
    
    @Override
    public BatchUpdate setQuotaUser(final String quotaUser) {
        return (BatchUpdate)super.setQuotaUser(quotaUser);
    }
    
    @Override
    public BatchUpdate setUploadType(final String uploadType) {
        return (BatchUpdate)super.setUploadType(uploadType);
    }
    
    @Override
    public BatchUpdate setUploadProtocol(final String uploadProtocol) {
        return (BatchUpdate)super.setUploadProtocol(uploadProtocol);
    }
    
    public String getSpreadsheetId() {
        return this.spreadsheetId;
    }
    
    public BatchUpdate setSpreadsheetId(final String spreadsheetId) {
        this.spreadsheetId = spreadsheetId;
        return this;
    }
    
    @Override
    public BatchUpdate set(final String s, final Object o) {
        return (BatchUpdate)super.set(s, o);
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
