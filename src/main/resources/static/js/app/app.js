'use strict'

var roverserviceApp = angular.module(
		  'nasa.photoservice', 
		[ 'ui.bootstrap', 
		  'photoservice.controllers',
		  'photoservice.services' ]);

roverserviceApp.constant("CONSTANTS", {
	getPhotos : "/api/v1/rovers"
});