'use strict'

angular.module('photoservice.services', []).factory('PhotoService',
		[ "$http", "CONSTANTS", function($http, CONSTANTS) {
			var service = {};
			var imageUrl="";
			service.getPhotos = function(photoDto) {
				return  $http({
				    url: CONSTANTS.getPhotos + "/" + photoDto.name + "/photos", 
				    method: "GET",
				    params: {earth_date: photoDto.date}
				 });
			};
			service.getPhoto = function(photo) {
				console.log("url: " + photo.imgSrc);		
				console.log("name: " + photo.rover.name);
				console.log("id: " + photo.id);
				imageUrl=CONSTANTS.getPhotos + "/" + photo.rover.name + "/photos/" + photo.id;
				console.log("imageURL: " + imageUrl);
				return  $http({
				    url: imageUrl, 
				    method: "GET",
				    params: {img_src: photo.imgSrc}
				 });
			}
			return service;
		} 
		]
);