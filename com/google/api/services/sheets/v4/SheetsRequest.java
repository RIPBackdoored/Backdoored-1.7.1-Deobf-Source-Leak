package com.google.api.services.sheets.v4;

import com.google.api.client.util.GenericData;
import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.util.Key;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;

public abstract class SheetsRequest<T> extends AbstractGoogleJsonClientRequest<T>
{
    @Key("$.xgafv")
    private String $Xgafv;
    @Key("access_token")
    private String accessToken;
    @Key
    private String alt;
    @Key
    private String callback;
    @Key
    private String fields;
    @Key
    private String key;
    @Key("oauth_token")
    private String oauthToken;
    @Key
    private Boolean prettyPrint;
    @Key
    private String quotaUser;
    @Key
    private String uploadType;
    @Key("upload_protocol")
    private String uploadProtocol;
    
    public SheetsRequest(final Sheets sheets, final String s, final String s2, final Object o, final Class<T> clazz) {
        super(sheets, s, s2, o, clazz);
    }
    
    public String get$Xgafv() {
        return this.$Xgafv;
    }
    
    public SheetsRequest<T> set$Xgafv(final String $Xgafv) {
        this.$Xgafv = $Xgafv;
        return this;
    }
    
    public String getAccessToken() {
        return this.accessToken;
    }
    
    public SheetsRequest<T> setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
    
    public String getAlt() {
        return this.alt;
    }
    
    public SheetsRequest<T> setAlt(final String alt) {
        this.alt = alt;
        return this;
    }
    
    public String getCallback() {
        return this.callback;
    }
    
    public SheetsRequest<T> setCallback(final String callback) {
        this.callback = callback;
        return this;
    }
    
    public String getFields() {
        return this.fields;
    }
    
    public SheetsRequest<T> setFields(final String fields) {
        this.fields = fields;
        return this;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public SheetsRequest<T> setKey(final String key) {
        this.key = key;
        return this;
    }
    
    public String getOauthToken() {
        return this.oauthToken;
    }
    
    public SheetsRequest<T> setOauthToken(final String oauthToken) {
        this.oauthToken = oauthToken;
        return this;
    }
    
    public Boolean getPrettyPrint() {
        return this.prettyPrint;
    }
    
    public SheetsRequest<T> setPrettyPrint(final Boolean prettyPrint) {
        this.prettyPrint = prettyPrint;
        return this;
    }
    
    public String getQuotaUser() {
        return this.quotaUser;
    }
    
    public SheetsRequest<T> setQuotaUser(final String quotaUser) {
        this.quotaUser = quotaUser;
        return this;
    }
    
    public String getUploadType() {
        return this.uploadType;
    }
    
    public SheetsRequest<T> setUploadType(final String uploadType) {
        this.uploadType = uploadType;
        return this;
    }
    
    public String getUploadProtocol() {
        return this.uploadProtocol;
    }
    
    public SheetsRequest<T> setUploadProtocol(final String uploadProtocol) {
        this.uploadProtocol = uploadProtocol;
        return this;
    }
    
    @Override
    public final Sheets getAbstractGoogleClient() {
        return (Sheets)super.getAbstractGoogleClient();
    }
    
    @Override
    public SheetsRequest<T> setDisableGZipContent(final boolean disableGZipContent) {
        return (SheetsRequest)super.setDisableGZipContent(disableGZipContent);
    }
    
    @Override
    public SheetsRequest<T> setRequestHeaders(final HttpHeaders requestHeaders) {
        return (SheetsRequest)super.setRequestHeaders(requestHeaders);
    }
    
    @Override
    public SheetsRequest<T> set(final String s, final Object o) {
        return (SheetsRequest)super.set(s, o);
    }
    
    @Override
    public /* bridge */ AbstractGoogleJsonClientRequest set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ AbstractGoogleJsonClientRequest setRequestHeaders(final HttpHeaders requestHeaders) {
        return this.setRequestHeaders(requestHeaders);
    }
    
    @Override
    public /* bridge */ AbstractGoogleJsonClientRequest setDisableGZipContent(final boolean disableGZipContent) {
        return this.setDisableGZipContent(disableGZipContent);
    }
    
    @Override
    public /* bridge */ AbstractGoogleJsonClient getAbstractGoogleClient() {
        return this.getAbstractGoogleClient();
    }
    
    @Override
    public /* bridge */ AbstractGoogleClientRequest set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClientRequest setRequestHeaders(final HttpHeaders requestHeaders) {
        return this.setRequestHeaders(requestHeaders);
    }
    
    @Override
    public /* bridge */ AbstractGoogleClient getAbstractGoogleClient() {
        return this.getAbstractGoogleClient();
    }
    
    @Override
    public /* bridge */ AbstractGoogleClientRequest setDisableGZipContent(final boolean disableGZipContent) {
        return this.setDisableGZipContent(disableGZipContent);
    }
    
    @Override
    public /* bridge */ GenericData set(final String s, final Object o) {
        return this.set(s, o);
    }
}
