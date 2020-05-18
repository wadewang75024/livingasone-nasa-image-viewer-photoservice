package com.livingasone.nasaimageviewer.photoservice.services;

import java.io.File;

import com.livingasone.nasaimageviewer.photoservice.data.PhotoList;

public interface PhotoService {
	PhotoList getPhotos(String name, String date) throws Exception;
	File getPhoto(String url) throws Exception;
}
