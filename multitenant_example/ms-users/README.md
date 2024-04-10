# ms-users

# Stack

Gradle 7.5.1
Spring Boot 2.3.10.RELEASE
Kotlin 1.6.21

## Build 🔧
Run this command to install the dependencies and build the project:
```bash
$ ./gradlew build
```

## Run
To run the project:
```bash
$ ./gradlew bootRun
```

The development environment runs on http://localhost:8080. Run a test GET request at http://localhost:8080/actuator

## Unit Tests
To run the unit tests:
```bash
$ ./gradlew test
```

## Tabla de endpoints

| Método | Endpoint | Descripción                             |
| ------ | -------- |-----------------------------------------|
| POST   | /users   | Create a new usuer |


