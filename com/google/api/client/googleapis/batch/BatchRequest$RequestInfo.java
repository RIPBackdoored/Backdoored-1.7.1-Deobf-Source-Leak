package com.google.api.client.googleapis.batch;

import com.google.api.client.http.HttpRequest;

static class RequestInfo<T, E>
{
    final BatchCallback<T, E> callback;
    final Class<T> dataClass;
    final Class<E> errorClass;
    final HttpRequest request;
    
    RequestInfo(final BatchCallback<T, E> callback, final Class<T> dataClass, final Class<E> errorClass, final HttpRequest request) {
        super();
        this.callback = callback;
        this.dataClass = dataClass;
        this.errorClass = errorClass;
        this.request = request;
    }
}
