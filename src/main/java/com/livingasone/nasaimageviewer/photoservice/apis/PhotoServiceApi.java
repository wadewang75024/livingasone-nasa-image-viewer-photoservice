package com.livingasone.nasaimageviewer.photoservice.apis;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.livingasone.nasaimageviewer.photoservice.data.PhotoList;
import com.livingasone.nasaimageviewer.photoservice.services.PhotoService;
import com.livingasone.nasaimageviewer.photoservice.exceptions.BusinessException;

@RestController
@RequestMapping("/api/v1")
public class PhotoServiceApi {
	public static Logger logger = LogManager.getLogger(PhotoServiceApi.class);
	
	@Autowired
	private PhotoService service;
	
	@GetMapping("/rovers/{name}/photos")
	public PhotoList getPhotos(@PathVariable String name, 
			                   @RequestParam("earth_date") String date) {
		logger.info("getPhotos:{}-{} ", name, date);
		try {
			return service.getPhotos(name, date);
		}
		catch (Exception e) {
			throw new BusinessException();
		}
	}
	
	@GetMapping(value = "/rovers/{name}/photos/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable String id, @PathVariable String name,
			                                @RequestParam("img_src") String imgSrc) throws Exception {
		logger.info("getImage: {}-{}-{}", name, id, imgSrc );
		File file = service.getPhoto(imgSrc);
		byte[] encoded = Base64.getEncoder().encode(Files.readAllBytes(file.toPath()));
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(encoded);
	}
}
