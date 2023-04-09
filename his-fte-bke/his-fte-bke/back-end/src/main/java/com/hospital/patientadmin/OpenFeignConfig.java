package com.hospital.patientadmin;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Client;
import feign.auth.BasicAuthRequestInterceptor;

@Configuration
@EnableFeignClients(basePackages="com.hospital.patientadmin.proxy")
public class OpenFeignConfig {
	
	//NOTE: Just for dev env so that we don't have to instal mirth certs
	@Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("admin", "admin");
    }
	
	@Bean
	public Client feignClient() {
	    return new Client.Default(getSSLSocketFactory(),getHostnameVerifier());
	}

	//SSLSocketFactory
	// Install the all-trusting trust manager
	public static SSLSocketFactory getSSLSocketFactory() {
	    try {
	        SSLContext sslContext = SSLContext.getInstance("SSL");
	        sslContext.init(null, getTrustManager(), new SecureRandom());
	        return sslContext.getSocketFactory();
	    }
	    catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

	//TrustManager
	// trust manager that does not validate certificate chains
	private static TrustManager[] getTrustManager() {
	    TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
	        @Override
	        public void checkClientTrusted(X509Certificate[] chain, String authType) {

	        }

	        @Override
	        public void checkServerTrusted(X509Certificate[] chain, String authType) {

	        }

	        @Override
	        public X509Certificate[] getAcceptedIssuers()
	        {
	            return new X509Certificate[]{};
	        }
	    }};
	    return trustAllCerts;
	}

	//HostnameVerifier
	public static HostnameVerifier getHostnameVerifier() {
	    HostnameVerifier hostnameVerifier = new HostnameVerifier() {
	        @Override
	        public boolean verify(String s, SSLSession sslSession)
	        {
	            return true;
	        }
	    };
	    return hostnameVerifier;
	}
}
