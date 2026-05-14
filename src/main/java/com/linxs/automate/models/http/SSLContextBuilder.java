package com.linxs.automate.models.http;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class SSLContextBuilder {

    private static SSLContext trustAllSSLContext;

    private SSLContextBuilder() {
    }

    public synchronized static SSLContext getTrustAllSSLContext() throws NoSuchAlgorithmException, KeyManagementException {

        if (trustAllSSLContext == null) {

            TrustManager[] trustAll = new TrustManager[]{
                    new X509TrustManager() {
                        public void checkClientTrusted(X509Certificate[] x, String y) {}
                        public void checkServerTrusted(X509Certificate[] x, String y) {}
                        public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                    }
            };

            trustAllSSLContext = SSLContext.getInstance("TLS");
            trustAllSSLContext.init(null, trustAll, new SecureRandom());

        }

        return trustAllSSLContext;
    }
}
