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

import com.livingasone.nasaimageviewer.photoservice.clients.NasaRestClient;
import com.livingasone.nasaimageviewer.photoservice.data.Camera;
import com.livingasone.nasaimageviewer.photoservice.data.Photo;
import com.livingasone.nasaimageviewer.photoservice.data.PhotoList;
import com.livingasone.nasaimageviewer.photoservice.data.Rover;
import com.livingasone.nasaimageviewer.photoservice.services.PhotoService;
import com.livingasone.nasaimageviewer.photoservice.services.PhotoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PhotoServiceTest {
	
	 @Mock
     private NasaRestClient client;
	 
	 @InjectMocks
	 private PhotoService service = new PhotoServiceImpl();
	 
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
	 }
	 
	 @Test
	 public void testGetPhotos() throws Exception {	       
		 Mockito
	          .when(client.getPhotoList(this.name, this.earthDate))
	          .thenReturn(photoList);
	 
	     PhotoList plist = service.getPhotos(name, earthDate);
	     assertEquals(photoList, plist);
	 }
}
