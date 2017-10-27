Review System Demo Application
==============================

# About

This is a simple demo project to show a message driven architecture of Spring Boot projects.
Note that this demo is highly opinionated and might not fit to your needs or preferences.
 
# Use cases of the demo

- An user can submit a review about a product.
- Each review must be checked and approved (or rejected).
- All approved reviews can be listed.

# What to see in this demo

- Usage of ActiveMQ to enable an async message-driven communication between system.
- Basic project setup for Spring Boot (multi-module) projects with Gradle.
- Setup of an embedded Zuul proxy to route requests from the AngularJS UI to different backend services.
- Usage of Docker Compose for a local development environment.

# Systems

## User Interface System (port 9002)

- AngularJS based user interface
- Allows to submit and read reviews
- The backend is a Zuul proxy which forwards requests (to the review system)

## Review System (port 9001)

- Takes and persists all reviews
- Throws _NEW REVIEW_ events to inform other system about new reviews
- Listens for _APPROVED REVIEW_ events and _REJECTED REVIEW_ events

## Checking System (port 9000)

- Checks incoming reviews to either approve or reject them
- Listens for _NEW REVIEW_ events
- Throws _APPROVED REVIEW_ events or _REJECTED REVIEW_ events

# Run

Perquisites:

- _Java_ must be installed
- _docker_ and _docker-compose_ must be installed

Run it:

    docker-compose up
    
    ./gradlew review_system:bootrun
    ./gradlew checking_system:bootrun
    ./gradlew user_interface_system:bootrun
    
Go to: http://localhost:9002

ActiveMQ Admin Console: http://localhost:8161/admin (Credentials: admin / admin)

# Test

## Java Tests

    ./gradlew check
    
## JavaScript Tests
    
    cd User_Interface_System/
    grunt check
    
# Links to my related blog posts
    
- Using ActiveMQ virtual topics to send events between systems and handle errors (failing event consumption).     
 // http://tuhrig.de/effective-error-handling-for-activemq-topics    
 
- A comparison of various Java and JavaScript build tools (used in this project). // http://tuhrig.de/java-vs-javascript-build-tools 