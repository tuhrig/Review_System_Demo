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
- Basic project setup for Karma JavaScript tests with Grunt

# Systems

## User Interface System

- AngularJS based user interface
- Allows to submit and read reviews
- The backend is a Zuul proxy which forwards requests (to the review system)

## Review System

- Takes and persists all reviews
- Throws _NEW REVIEW_ events to inform other system about new reviews
- Listens for _APPROVED REVIEW_ events and _REJECTED REVIEW_ events

## Checking System

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

# Test

## Java Tests

    ./gradlew check
    
## JavaScript Tests
    
    cd User_Interface_System/
    grunt check