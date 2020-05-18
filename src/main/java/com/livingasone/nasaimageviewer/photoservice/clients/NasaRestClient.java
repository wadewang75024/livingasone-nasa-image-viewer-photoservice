package com.livingasone.nasaimageviewer.photoservice.clients;

import java.io.InputStream;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.livingasone.nasaimageviewer.photoservice.data.PhotoList;
import com.livingasone.nasaimageviewer.photoservice.AppConstants;

@Service
public class NasaRestClient extends RestTemplateClient {
	public static Logger logger = LogManager.getLogger(NasaRestClient.class);
	
	
	public PhotoList getPhotoList(String name, String date) throws Exception{
		logger.info("getPhotoList starts...");	
		
		String urlOverHttps = AppConstants.NASA_REST_URI + 
				              AppConstants.NASA_REST_URI_ROVER_PATH + 
				              name + 
				              AppConstants.NASA_REST_URI_PHTOTO_PATH;	    
		
		UriComponentsBuilder builder = 
					UriComponentsBuilder.fromHttpUrl(urlOverHttps)
			        						.queryParam(AppConstants.NASA_API_KEY_PARAM_NAME, 
			        								    AppConstants.NASAS_API_KEY)
			        						.queryParam(AppConstants.NASA_EARTH_DATE_PARAM_NAME, 
		        								    	date);
			
		logger.info("Making the call with URL: " + builder.toUriString());			
	    ResponseEntity<PhotoList> response = rest.exchange(builder.toUriString(), HttpMethod.GET, null, PhotoList.class);    		    
	    return response.getBody();
	}
	
	public InputStream getPhoto(String url) throws Exception {
		logger.info("getPhoto with URL: " + url);	
	    HttpHeaders headers = new HttpHeaders();    
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));	
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
		
		ResponseEntity<Resource> responseEntity = rest.exchange(url, HttpMethod.GET, httpEntity, Resource.class );
		
		logger.info("Size of responseEntity: " + responseEntity.getBody().getInputStream().available());
	    return responseEntity.getBody().getInputStream();
	}
}
