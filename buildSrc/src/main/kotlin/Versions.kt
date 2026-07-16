/**
 * Centralized version declarations for all project dependencies.
 * This improves maintainability and makes it easy to upgrade versions in one place.
 * Last updated: January 2025 - All dependencies updated to latest stable versions
 */
object Versions {

    // 📈 Observability & Monitoring
    const val micrometer = "1.13.8" // Micrometer metrics library (used with Prometheus) - matches Spring Boot 3.3.6

    // 📘 API Documentation
    const val springdocOpenApi = "2.6.0" // OpenAPI + Swagger support for Spring WebFlux

    // ✅ Validation Framework
    const val jakartaValidation = "3.0.2" // Jakarta Bean Validation API
    const val hibernateValidator = "8.0.1.Final" // Hibernate Validator implementation
    const val jakartaEl = "4.0.2" // Jakarta Expression Language (required by Hibernate Validator)

    // 🧪 Testing
    const val junit = "5.10.2"
    const val junitPlatform = "1.10.2"
    const val wiremock = "3.0.1"
    const val restAssured = "5.5.6" // Updated to latest stable
    const val archunit = "1.3.0"

    // 📝 Logging
    const val logstashLogback = "8.0" // Updated to latest stable
    const val logback = "1.5.33" // 🔐 updated to fix CVE-2026-9828 (HardenedObjectInputStream allowlist bypass)

    // 🔐 Security-related fixes (updated for CVE mitigation)
    const val commonsBeanutils = "1.9.4"
    const val commonsIo = "2.20.0" // 🔐 updated to latest stable
    const val httpClient5 = "5.3.1" // Latest stable (5.6-alpha1 is RC)
    const val artemis = "2.35.0" // 🔐 updated to latest stable
    const val jetty = "11.0.21" // 🔐 updated to latest stable (11.x compatible with Spring Boot 3.3)
    const val xmlunit = "2.10.0" // Updated to latest
    const val bcprov = "1.84" // 🔐 updated to fix CVE-2025-14813 (GOST28147 CTR keystream reuse) + covert timing channel
    const val nimbusJoseJwt = "9.40" // 🔐 updated to latest stable (10.5 is major version, needs compatibility check)
    const val netty = "4.1.135.Final" // 🔐 updated to fix multiple CVEs (hostname verification bypass, SNI, DNS cache poisoning, HTTP/2 DoS)
    const val spring = "6.1.15" // 🔐 updated to latest stable (matches Spring Boot 3.3.6)
    const val springSecurity = "6.3.5" // ⚠️ NOT bumped: CVE-2026-47838 (X.509 impersonation) fix for the 6.3.x line (6.3.18) is Enterprise-support-only, not published to Maven Central. Public OSS fix requires Spring Security 6.5.11, incompatible with this project's Spring Boot 3.3.6 / Spring Security 6.3.x baseline. See PR description.
    const val jacksonDatabind = "2.21.4" // 🔐 added to fix CVE-2026-54512 / CVE-2026-54513 (PolymorphicTypeValidator bypasses) + case-insensitive @JsonIgnoreProperties bypass + InetSocketAddress SSRF
    const val handlebars = "4.5.2" // 🔐 added to fix CVE-2026-55760 (FileTemplateLoader path traversal) - transitive via wiremock-jre8
}
