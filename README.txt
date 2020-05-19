1. General
	The NASA Image Download project has been implemented as 3 microservices with Spring Boot 2, Java 8 and angularjs technologies. 

	The 3 microservices are 
 		a. livingasone-nasa-image-viewer-datesservice, 
 			which can be accessed via http://localhost:3000/api/v1/dates
 		b. livingasone-nasa-image-viewer-roverservice, 
 			which can be accessed via http://localhost:3001/api/v1/rovers
 		c. livingasone-nasa-image-viewer-photoservice, 
 			which can be accessed via http://localhost:3002/api/v1/rovers/Curiosity/photos?earth_date=2017-02-27 and
 						http://localhost:3002/api/v1/rovers/Curiosity/photos/617458?img_src=http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01622/opgs/edr/fcam/FLB_541484941EDR_F0611140FHAZ00341M_.JPG

2. Configuration (in application properties)
	a. livingasone-nasa-image-viewer-datesservice: running on port 3000
	b. livingasone-nasa-image-viewer-roverservice: running on port 3001
 	c. livingasone-nasa-image-viewer-photoservice: running on port 3002
 
 3. How to start the application locally
 	a. Go to the corresponding microservice home directory
 	b. run ./gradlew bootRun
 	
 4. How to access the UI  
 	A angularjs-based UI has been added to each microservice to interact with the corrsponding backend REST service.  Test results
 	via UI can also be found under the testResults directory.

 	a. livingasone-nasa-image-viewer-datesservice: via http://localhost:3000/views/index.html
	b. livingasone-nasa-image-viewer-roverservice: via http://localhost:3001/views/index.html
 	c. livingasone-nasa-image-viewer-photoservice: via http://localhost:3002/views/index.html
 
 5. Testing
  
 	All codes have been tested via junit and postman. The unit test codes are located at the test directory of each microservice.  
 	Postman test results can be found under testResults directory under each microservice.
 

 6. Where is image downloaded and saved by the REST service
 	On window, they can be found under C:\Users\<user>\AppData\Local\Temp