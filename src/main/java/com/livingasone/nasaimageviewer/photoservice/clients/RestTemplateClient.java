package com.livingasone.nasaimageviewer.photoservice.clients;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.livingasone.nasaimageviewer.photoservice.AppConstants;

public abstract class RestTemplateClient {
	public static Logger logger = LogManager.getLogger(RestTemplateClient.class);
	protected RestTemplate rest;
	
	public RestTemplateClient() {
		rest = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(new GsonHttpMessageConverter());
		messageConverters.add(new ResourceHttpMessageConverter() );
		rest.setMessageConverters(messageConverters); 
	}
	
	@PostConstruct
	public void init() throws Exception {
		logger.info("init starts...");
		TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
	    SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
	    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, 
	      NoopHostnameVerifier.INSTANCE);
	     
	    Registry<ConnectionSocketFactory> socketFactoryRegistry = 
	      RegistryBuilder.<ConnectionSocketFactory> create()
	      .register("https", sslsf)
	      .register("http", new PlainConnectionSocketFactory())
	      .build();
	 
	    BasicHttpClientConnectionManager connectionManager = 
	      new BasicHttpClientConnectionManager(socketFactoryRegistry);
	    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
	      .setConnectionManager(connectionManager).build();	    
	
	    HttpComponentsClientHttpRequestFactory requestFactory = 
	      new HttpComponentsClientHttpRequestFactory(httpClient);
	    
	    requestFactory.setConnectionRequestTimeout(AppConstants.REST_CLIENT_CONNECTION_TIMEOUT);
	    requestFactory.setReadTimeout(AppConstants.REST_CLIENT_CONNECTION_TIMEOUT);
		rest.setRequestFactory(requestFactory);
	}
	
	public <T> ResponseEntity<T> makeGetCallWithExchange(String url, Class c) {
		logger.info("makeGetCallWithExchange");
		ResponseEntity<T> response = rest.exchange(url, HttpMethod.GET, null, c);  
		return response;
	}
}
