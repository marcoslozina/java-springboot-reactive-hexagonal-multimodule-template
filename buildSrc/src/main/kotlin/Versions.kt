/**
 * Centralized version declarations for all project dependencies.
 * This improves maintainability and makes it easy to upgrade versions in one place.
 */
object Versions {

    // 📈 Observability & Monitoring
    const val micrometer = "1.12.3" // Micrometer metrics library (used with Prometheus)

    // 📘 API Documentation
    const val springdocOpenApi = "2.5.0" // OpenAPI + Swagger support for Spring WebFlux

    // ✅ Validation Framework
    const val jakartaValidation = "3.0.2" // Jakarta Bean Validation API
    const val hibernateValidator = "8.0.1.Final" // Hibernate Validator implementation
    const val jakartaEl = "4.0.2" // Jakarta Expression Language (required by Hibernate Validator)

    // 🧪 Testing
    const val junit = "5.10.1" // JUnit 5 (Jupiter) core version
    const val junitPlatform = "1.10.1" // JUnit platform and engine integration

    const val wiremock = "3.0.1" // WireMock for mocking HTTP services
    const val restAssured = "5.3.1" // REST-assured for fluent REST testing
    const val archunit = "1.2.1" // ArchUnit for architecture validation through tests

    // 📝 Logging (structured JSON logging support)
    const val logstashLogback = "7.4" // Logstash Logback Encoder (requires Logback 1.4.11+)

    const val commonsBeanutils = "1.9.4"
    const val commonsIo = "2.15.0"
    const val httpClient5 = "5.2.1"
    const val artemis = "2.31.2"
    const val jetty = "11.0.20"
}
