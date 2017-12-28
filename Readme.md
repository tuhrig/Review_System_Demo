Review System Demo
==================

_A Spring-Boot and AngularJS boilerplate project_

[![Build Status](https://travis-ci.org/tuhrig/Review_System_Demo.svg?branch=master)](https://travis-ci.org/tuhrig/Review_System_Demo)

# About

This is a boilerplate project to show a message driven architecture for Spring Boot projects.
Note that this application is highly opinionated and might not fit to your needs or preferences.

Read more: http://tuhrig.de/spring-boot-boilerplate-project-with-activemq-and-angularjs

# Tags

Spring Boot, AngularJS, Domain Driven Design, Lombok, ActiveMQ

# Use cases of the demo

- An user can submit a review about a product.
- Each review will be checked and approved (or rejected).
- All approved reviews can be listed.
- Statistics about how many reviews have been submitted, approved and rejected can be seen.

# Systems

## User Interface System (port 9002)

- AngularJS based user interface
- Allows to submit and read reviews and view some overall statistics
- The backend is a Zuul proxy which forwards requests (to the review system)

## Review System (port 9001)

- Takes and persists all reviews
- Throws _NEW REVIEW_ events to inform other system about new reviews
- Listens for _APPROVED REVIEW_ events and _REJECTED REVIEW_ events

## Checking System (port 9000)

- Checks incoming reviews to either approve or reject them
- Listens for _NEW REVIEW_ events
- Throws _APPROVED REVIEW_ events or _REJECTED REVIEW_ events

## Statistics System (port 9003)

- Listens for _NEW REVIEW_, _APPROVED REVIEW_ and _REJECTED REVIEW_ event
- Provides simple statistics on how many reviews have been submitted, approved or rejected 

# Run

Perquisites:

- _Java_ must be installed
- _docker_ and _docker-compose_ must be installed

Run it:

    docker-compose up
    ./gradlew bootrun --parallel --max-workers=4
    
Go to: http://localhost:9002

ActiveMQ Admin Console: http://localhost:8161/admin (Credentials: admin / admin)
H2 Web Console: http://localhost:9001/h2-console

# Test

## Java Tests

    ./gradlew check
    
## JavaScript Tests
    
    cd User_Interface_System/
    grunt check
    
## (Protractor) E2E-Tests
    
    cd E2E_Tests/
    grunt protractor
    
**Note:**
In order to run the E2E tests, all four systems (review, statistics, checking and UI) must be running.
The E2E tests will then open a Chrome browser to navigate on the web application.

# Developer Notes 

## Domain IDs

Domain IDs identify entities.
They are unique business keys and should have a meaningful structure (not just database increments).

Annotations:
- `@Value` as its immutable

Example: `ReviewId.java`

## Domain Entities

A domain entity contains actual business functionality.
Every domain entity is identified by its ID (not by its current data or state).

Annotations:
- `@Builder` to be able to build the entity when loading it from the database
- `@AllArgsConstructor(access = AccessLevel.PRIVATE)` to enable the builder
- `@Getter` to map the entity to REST resources
- `@Slf4j` to log when executing business functionality
- `@EqualsAndHashCode(of = "myEntityId")` to enforce equality based on the entity ID

Rules:
- The _only_ place to implement business logic
- Should be free of any framework dependency
- No JPA or Jackson annotations

Example: `Review.java`

## Domain Events

A message send to the world which informs about some event that has happened in the past.

Annotations:
- `@Data` as the events are simple containers to exchange information

Rules:
- No logic, just simple POJOs to hold data
- Try to use only simple types (e.g. String, int, boolean) to guarantee easy serialisation

Example: `ReviewSubmittedEvent.java`

## REST Resources

Annotations:
- `@Data` for simple containers to exchange information

Rules:
- No logic
- Use simple types (e.g. String, int, boolean) to guarantee easy serialisation
- Only use in REST layer

Example: `ReviewResource.java`

## JPA Entities

Annotations:
- `@Data` for simple data containers
- `@Entity(name = "MY_TABLE_NAME")` to enable JPA on this entity
- `@EntityListeners(AuditingEntityListener.class)` to enable auditing (created and last modified date)

Rules:
- No logic
- Use simple types (e.g. String, int, boolean) to have no dependencies outside
- Only use in database layer

Example: `ReviewEntity.java`