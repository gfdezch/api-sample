# Quick Start #

* Clone the repository.
* Open terminal console and change directory to be placed in project root folder.
* Run docker compose to up the containers: 

```powershell
docker compose -f docker/docker-compose.yaml --build -d
```

* Run maven to build the .jar package: 

```powershell
mvn clean install
```

* Execute the .jar package: 

```powershell
java -jar .\target\api-1.0.0-SNAPSHOT.jar
```

# What we use here? #
* Architecture:
    * Hexagonal
    * Vertical slicing.
* Tenologies:
    * Docker
* Spring boot
* Flyway
* MyBatis
* Obervability
    * Loki
    * Prometheus
    * Jeager
    * Graphana
* Testcontainers

# What is this? #

This is an example project to show the basics about how to create a robust, obervable and scalable application  using Spring Boot, Docker and Testcontainers.

The application adheres to the Hexagonal Architecture (also known as Ports and Adapters) which aims to create loosely coupled application components that can be easily connected to their software environment. This makes the application more maintainable and adaptable to development and deployment changes.

The project is structured using Vertical Slicing, a technique that breaks down features into manageable, isolated slices, each providing a piece of functionality across all layers of the application. This approach enhances the understandability and testability of the code, and allows for more efficient parallel development.

Overall, this project is a testament to modern software development practices, leveraging Spring Boot's ease of use, Hexagonal Architecture's flexibility, Vertical Slicing's manageability, and the power of design patterns to create a robust, scalable, and maintainable application.

