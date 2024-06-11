# SiC Services

Project is created according to microservices architecture combined with maven Multi-Module

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)

## Installation

Follow these steps to install and set up the project:

- Make sure `maven 3.9.2` and `Java 17` are installed
- Import project to IDE (`sic-services` is root project)

```
sic-services
├── sic-core
│   └── pom.xml
├── sic-log-service
│   └── pom.xml
├── sic-payment-service
│   └── pom.xml
├── sic-sample-service
│   └── pom.xml
└── pom.xml
```

- Run maven command from root project folder:

```
mvn clean install -DskipTests
```

## Configuration

[Explain any configuration options or settings available in the project. Describe how to customize or modify the project's behavior.]
