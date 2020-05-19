package com.livingasone.nasaimageviewer.photoservice;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.livingasone.nasaimageviewer.photoservice.clients.NasaRestClient;
import com.livingasone.nasaimageviewer.photoservice.data.Camera;
import com.livingasone.nasaimageviewer.photoservice.data.Photo;
import com.livingasone.nasaimageviewer.photoservice.data.PhotoList;
import com.livingasone.nasaimageviewer.photoservice.data.Rover;
import com.livingasone.nasaimageviewer.photoservice.data.RoverList;

@RunWith(MockitoJUnitRunner.class)
public class RestTemplateClientTest {
	 
	    @Mock
	    private RestTemplate rest;
	 
	    @InjectMocks
	    private NasaRestClient client = new NasaRestClient();
	    
	    UriComponentsBuilder builder;
		 
	    PhotoList photoList = new PhotoList();
		String name;
		String earthDate;
		 
		@Before
		public void init() {
			 List<Photo> photos = new ArrayList<Photo>();
			 Photo p1= new Photo();
			 p1.setId("617458");
			 p1.setEarthDate("2017-02-27");
			 p1.setImgSrc("http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01622/opgs/edr/fcam/FLB_541484941EDR_F0611140FHAZ00341M_.JPG");
			 p1.setSol("1622");
			 Camera c1 = new Camera();
			 c1.setId("20");
			 c1.setFullName("Front Hazard Avoidance Camera");
			 c1.setName("FHAZ");
			 Rover r1 = new Rover();
			 r1.setId("5");
			 r1.setName("Curiosity");
			 p1.setCamera(c1);
			 p1.setRover(r1);
			 
			 photos.add(p1);
			 
			 this.name = "Curiosity";
			 this.earthDate = "2017-02-27";  	 
			 
			 String urlOverHttps = AppConstants.NASA_REST_URI + 
		              AppConstants.NASA_REST_URI_ROVER_PATH + 
		              name + 
		              AppConstants.NASA_REST_URI_PHTOTO_PATH;	    

			 builder = 
			 UriComponentsBuilder.fromHttpUrl(urlOverHttps)
	        						.queryParam(AppConstants.NASA_API_KEY_PARAM_NAME, 
	        								    AppConstants.NASAS_API_KEY)
	        						.queryParam(AppConstants.NASA_EARTH_DATE_PARAM_NAME, 
	        									earthDate);
		}
	    
	    @Test
		public void testGetPhotos() throws Exception {	
			 Mockito
		     .when(client.makeGetCallWithExchange(builder.toUriString(), 
		    		                              RoverList.class))
		     .thenReturn(new ResponseEntity<>(photoList, HttpStatus.OK));
			 
		     PhotoList rlist = client.getPhotoList(name, earthDate);
		     assertEquals(photoList, rlist);
		}
}
