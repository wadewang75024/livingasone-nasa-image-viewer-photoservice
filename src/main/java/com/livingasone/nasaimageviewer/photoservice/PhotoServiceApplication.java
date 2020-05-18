package com.livingasone.nasaimageviewer.photoservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhotoServiceApplication {
	public static Logger logger = LogManager.getLogger(PhotoServiceApplication.class);
	public static void main(String[] args) {
	    logger.info("PhotoServiceApplication main starts...");
	    SpringApplication.run(PhotoServiceApplication.class, args);
	}
}
