This branch is for Master Microservices with SpringBoot, Docker, Kubernetes Udemy course by Eazybytes. 

So far the progress is:
  - images of 3 microservices and their own mysql db images are created
  - added a config server which contains information about each microservice
  - added an eurekaserver for service discovery, service registry and load balancing
  - a gateway service added for getting requests from one point and resillience

Design Patterns Mentioned
  1.Database Patterns
    - Database per service
    - Shared Database
    - CQRS (Command Query Responsibility Segregation)
  2.Communication Patterns
    - Synchronous Patterns
      a. API Gateway
      b. Service to service
    - Asynchronous Patterns
      a. Message Queue ( RabbitMQ )
  3.Resillience Patterns
    - Circuit Breaker
    - Retry
    - Timeouts
    - Bulkhead


  Notes: 
  Images created with google jib.
  for more informations about used technologies such as ressilience4j anda SpringBoot:
  https://spring.io/projects/spring-boot
  https://resilience4j.readme.io/docs/getting-started
  https://stripe.com/blog/rate-limiters
