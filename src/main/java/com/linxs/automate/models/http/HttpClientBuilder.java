package com.linxs.automate.models.http;

import javax.net.ssl.SSLContext;
import java.net.http.HttpClient;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;

public class HttpClientBuilder {

    public static HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(30)).build();
    public static HttpClient trustAllHttpsClient;

    static {

        SSLContext sc = null;

        try {
            sc = SSLContextBuilder.getTrustAllSSLContext();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        trustAllHttpsClient = HttpClient.newBuilder()
                                        .sslContext(sc)
                                        .connectTimeout(Duration.ofSeconds(30))
                                        .version(HttpClient.Version.HTTP_1_1)
                                        .build();
    }
}
