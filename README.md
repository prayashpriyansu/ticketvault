# TicketVault

A backend engineering learning project for building a realistic ticketing system
in small, reviewable steps.

## Current state

The Spring Boot application starts and its application context test passes.
Database integration, ticketing operations, and authentication are not implemented
yet. Current work is tracked in [the foundation phase](docs/phase-01-foundation.md).

## Prerequisites

- JDK 26 (verified with OpenJDK 26.0.2).
- Internet access for the first Maven dependency download.

The project uses Spring Boot 4.1.1. The committed Maven wrapper supplies Maven;
you do not need a separate Maven installation. Ensure your terminal and IDE use
JDK 26.

## Test

From the repository root:

```sh
./mvnw test
```

The current test checks that Spring can load the application context. It does not
yet test HTTP endpoints or database behaviour.

## Run locally

```sh
./mvnw spring-boot:run
```

The application listens on port 8080. A request to `http://localhost:8080/`
returns 404 because no endpoint is defined yet. Stop the application with Ctrl+C.

In Windows PowerShell, use `./mvnw.cmd test` and
`./mvnw.cmd spring-boot:run` instead.

## Development

Use small task branches and pull requests. See [the working agreement](docs/README.md)
for the learning workflow. PostgreSQL, Docker Compose, and Flyway are planned for
the foundation phase; they are not required to run this scaffold.
