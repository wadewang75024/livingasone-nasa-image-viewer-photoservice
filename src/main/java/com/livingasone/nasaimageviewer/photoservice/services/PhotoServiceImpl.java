package com.livingasone.nasaimageviewer.photoservice.services;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.http.util.ByteArrayBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livingasone.nasaimageviewer.photoservice.clients.NasaRestClient;
import com.livingasone.nasaimageviewer.photoservice.data.PhotoList;

@Service
public class PhotoServiceImpl implements PhotoService {
	public static Logger logger = LogManager.getLogger(PhotoServiceImpl.class);
	
	@Autowired 
	private NasaRestClient client;
	
	public PhotoList getPhotos(String name, String date) throws Exception {
		logger.info("getPhotos starts...");
		return client.getPhotoList(name, date);
	}
	
	public File getPhoto(String url) throws Exception {
		logger.info("getPhoto starts...");
		String property = "java.io.tmpdir";
        String tempDir = System.getProperty(property);
        
        logger.info("tempDir: " + tempDir);
        
		final String name = url.substring(url.lastIndexOf('/')+1, url.length());
		final String imageFileName = new StringBuilder(tempDir).append(name).toString();
		
		logger.info("imageFileName: " + imageFileName);
		File imageFile = new File(imageFileName+".JPG");
		if ( imageFile.exists()) {
			return imageFile;
		}
		else {	
			InputStream is = client.getPhoto(url);		
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(50);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }

            FileOutputStream fos = new FileOutputStream(imageFile);
            fos.write(baf.toByteArray());
            fos.close();
                
            return imageFile;
		}
		
	}
}
