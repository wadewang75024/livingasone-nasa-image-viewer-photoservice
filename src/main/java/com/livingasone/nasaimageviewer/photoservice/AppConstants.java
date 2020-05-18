package com.livingasone.nasaimageviewer.photoservice;

public interface AppConstants {
		
	final String NASA_REST_URI = "https://api.nasa.gov/mars-photos/api/v1";
	final String NASA_REST_URI_ROVER_PATH = "/rovers/";
	final String NASA_REST_URI_PHTOTO_PATH = "/photos";
	final String NASAS_API_KEY = "gyiCAARMmpZikoFEinbhEwChPhXbRevM0AdA6s1f";
	final String NASA_API_KEY_PARAM_NAME = "api_key";
	final String NASA_EARTH_DATE_PARAM_NAME = "earth_date";
	final int REST_CLIENT_CONNECTION_TIMEOUT=5000;
	final String IMAGE_CACHE_PATH="/tmp/";
}
