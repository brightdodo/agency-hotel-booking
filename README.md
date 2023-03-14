# agency-hotel-booking
Agency Hotel Booking application having a React frontend calling a backend REST API

Agency Hotel backend is build with Java 17, Jersey and Spring Boot 3. 

## To run it:
### build
```
$ mvn clean install
```

then build a docker image with build-packs: 
```
$ mvn spring-boot:build-image
```

or use the traditional Dockerfile
```
$ docker build -t hotel/agency-hotel-booking .
```
### verify the build
```
$ docker images 
```

### Run
```
$ docker run -p 8080:8080 hotel/agency-hotel-booking
$ docker ps

$ curl http://localhost:8080/hotel/reservations
```