# Targregator

This service aggregates product information from multiple sources: naming from another web service and pricing from a mongo database.

## Requirements

To run locally you'll need Docker or both Java 11 and MongoDB.

## Getting Started

1. If you have mongodb available at localhost:27017 (running locally or in a container) then run the following gradle task:

    `./gradlew localMongo bootRun` 

2. Otherwise, with docker running locally, you can build a docker image and run it with docker-compose, which will also spin up a containerized mongo instance:

    `./gradlew bootBuildImage`
    
    `docker-compose up`
    
    It is not necessary to rebuild the image each time -- unless there are code changes, skip the _bootBuildImage_ command on subsequent runs.

## Using the service

Try these endpoints:

* http://localhost:8080/actuator/health (should include circuitbreaker and mongo dependencies)
* http://localhost:8080/products/13860428 (should result in 200 with JSON response. Try twice -- second time should be faster due to caching -- expiration is 30s for demo purposes)
* http://localhost:8080/products/13860429 (should result in 404 due to missing pricing. Try twice -- second time should be faster)
* http://localhost:8080/products/13860430 (should result in 404 due to missing name, as of 11 October 2020)
* http://localhost:8080/actuator/circuitbreakerevents (should show resilience4j events of interest)

## Other Stuff

For ergonomics of evaluation, spring security has been disabled. To enable it, uncomment the dependency in the build.gradle file and rebuild. Everything including tests should continue to work. You'll have to grab the default password from the output logs and use the default username of `user`.  