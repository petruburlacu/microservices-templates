# Getting Started

### Metrics
For detailed information on monitoring the application using Micrometer, Prometheus, and Grafana, refer to the [Monitoring Guide](help/monitoring.md).

| Service     | IP Address | Port         |
|-------------|------------|--------------|
| Prometheus  | localhost  | 9090         |
| Grafana     | localhost  | 3000         |
| Application | localhost  | 8080         |
| Actuator    | localhost  | 8081 |

### Reference Documentation
For further reference, please consider the following sections:

* [Roadmap](help/roadmap.md)
* [Monitoring](help/monitoring.md)
* [REST Api - Swagger OpenAPI](help/swagger.md)
* [Structured Logging](help/logging.md)

### Project Structure
```
src
└── main
    ├── java
    │   └── com
    │       └── org
    │           ├── feature (e.g: documents)
    │               ├── controller
    │               ├── service
    │               ├── repository
    │               ├── model
    │               ├── dto
    │               ├── mapper
    │               └── exception
    │           ├── config        // For configuration-related classes (e.g., SecurityConfig, MongoDBConfig)
    │           ├── security      // For security-related classes (e.g., JWT filters, authentication providers)
    │           ├── exception     // Centralized exception handling (e.g., GlobalExceptionHandler)
    │           ├── util          // Utility classes (e.g., common helpers, date utilities)
    │           └── common        // Any common/shared components (e.g., custom annotations, constants, etc.)
    └── resources
        ├── application.yml
        └── static


```

Benefits
- Clarity: Each feature has a clear, self-contained structure that makes it easy to navigate.
- Encapsulation: Related classes are grouped together, improving cohesion.
- Scalability: As features grow, you can easily extend the structure without reorganizing the entire project.
#### Considerations
1. If we have specific configurations for a feature, we should consider adding a config package within the feature directory.
2. Keep tests organized alongside a feature. You can mirror the structure under `src/test`:
```
src
└── test
    └── java
        └── com
            └── org
                └── feature
                    ├── controller
                    ├── service
                    ├── repository
                    ├── model
                    ├── dto
                    └── exception
```
3. Multiple Features: (NOT RECOMMENDED) If a microservice project covers multiple features, we should create separate folders for each feature under the main package.
4. Shared Components: If there are components shared across features, creating a common or shared package to hold those classes is recommended

## Roadmap
For detailed information on the project's roadmap, refer to the [Roadmap Guide](help/roadmap.md).

### Additional Links
These additional references should also help you:

