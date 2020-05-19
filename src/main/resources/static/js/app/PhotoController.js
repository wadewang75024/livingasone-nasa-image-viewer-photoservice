'use strict'

var module = angular.module('photoservice.controllers', []);
module.controller
("PhotoController", 
	[ "$scope", "PhotoService",
		function($scope, PhotoService) {	
			$scope.photoDto=[];
			
			$scope.photoImage;

			$scope.displayPhotos = function() {				
				PhotoService.getPhotos($scope.photoDto).then(function(value) {
					console.log("works");
					$scope.allPhotos= value.data.photos;							
				}, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				}
				);
			}
			
			$scope.displayPhoto = function(photo) {				
				PhotoService.getPhoto(photo).then(function(value) {		
					$scope.photoImage = value.data;					
				}, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				}
				);
			}
					
		}
	]
);