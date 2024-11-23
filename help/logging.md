# Logback Logging and Spring Profiles

## Overview

This document provides an overview of how to configure Logback logging and use Spring profiles (`dev` and `prod`) in your Spring Boot microservice project.

## Logback Configuration

Logback is a logging framework for Java applications, and it is the default logging framework used by Spring Boot. The configuration is done using an XML file named `resources/logback.xml`.

### Logback Profile Configurations
- `dev`: the logging configuration is set to output logs in a human-readable format to the console.
- `prod`: the logging configuration is set to output logs in JSON format to the console, which is suitable for log aggregation and analysis tools.

## Spring Profiles

Spring profiles provide a way to segregate parts of your application configuration and make it only available in certain environments. This is useful for having different configurations for development and production environments.

### Setting the Active Profile

The active profile can be set in the `application.yml` file. By default, the `prod` profile is active.

```yaml
spring:
  profiles:
    active: prod
```
