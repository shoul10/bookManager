# One-to-Many Bidirectional Relationship Mapping
### Java Web Services and RESTful API with Spring Boot 

This tutorial will walk you through the steps of mapping a JPA/Hibernate One-to-Many bidirectional relationship with Spring Boot, Spring Data JPA, Lombok, H2 and Swagger. The following table relationship diagram illustrates the database designed in this solution:

![Image of Yaktocat](https://github.com/shoul10/bookManager/raw/master/one-to-many.png)

One-to-many relationship refers to the relationship between two entities/tables A and B in which one element/row of A may be linked with many elements of B, but a member of B is linked to only one element of A.

In this example, the book_category and book tables have a one-to-many relationship. One category may be linked with many books but one book is linked to only one category.

### What you'll need
- [Java](https://www.java.com) 1.8
- [Spring Boot](http://spring.io/projects/spring-boot) 2.1.2
- [maven](https://maven.apache.org/)
- JPA/Hibernate
- [H2](http://www.h2database.com)
- DevTools
- [Lombok](https://projectlombok.org/)
- [JUnit 4](https://junit.org/junit4/)
- [Swagger](https://swagger.io/) 2

### build with Maven & Run the application
```sh
mvn package && java -jar target/bookManager.jar
```

Database UI
http://localhost:8080/h2/

Swagger UI
http://localhost:8080/swagger-ui.html
