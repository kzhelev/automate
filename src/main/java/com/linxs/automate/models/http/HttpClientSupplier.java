package com.linxs.automate.models.http;

import java.net.http.HttpClient;

public class HttpClientSupplier {

    private final HttpClient httpClient = HttpClient.newBuilder().build();

    private static HttpClientSupplier instance;

    private HttpClientSupplier() {

    }

    public synchronized static HttpClientSupplier getInstance() {
        if (instance == null) {
            instance = new HttpClientSupplier();
        }
        return instance;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }
}
