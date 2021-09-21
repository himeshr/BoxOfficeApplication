# BoxOfficeApplication

# Getting Started

This is a Java - Springboot application built and documented using Maven.
To run this application you would need Java 11 JRE to be installed on the system.

The starter class is BoxOfficeApplication.
To run this application on you system, import the project into your IDE as a Java-Maven project.
Then Perform following actions:
1. "Maven-> update project"
2. "Run as -> Maven Install"

Your app should then be up and running.

# Application info
This application randomly generates SoldTicketsInfo objects using SoldTicketsInfoSupplier and feeds it into a stream.

It has following consumers for the stream of SoldTicketsInfo objects.
* SoldTicketsInfoPrinterConsumer - Prints movie name
* MovieTotalTicketsCounterConsumer - Computes total tickets sold for a movie

It also has a listener attached to MovieTotalTicketsCounterConsumer, which would raise alters if "Total tickets count for a particular movie exceeds preset threshold value".

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/#build-image)
