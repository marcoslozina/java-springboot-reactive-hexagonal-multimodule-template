import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency

/**
 * Centralized dependency declarations organized by concern/domain.
 * This improves readability, makes version upgrades easier, and avoids duplication.
 */
object Dependencies {

    object Spring {
        const val bootWebflux = "org.springframework.boot:spring-boot-starter-webflux" // Reactive web framework
        const val bootSecurity = "org.springframework.boot:spring-boot-starter-security" // Core Spring Security
        const val bootOauth2 = "org.springframework.boot:spring-boot-starter-oauth2-resource-server" // OAuth2 resource server
        const val bootTest = "org.springframework.boot:spring-boot-starter-test" // Spring Boot test utilities
        const val securityConfig = "org.springframework.security:spring-security-config" // Programmatic security config support
    }

    object Cloud {
        const val vaultConfig = "org.springframework.cloud:spring-cloud-starter-vault-config" // Spring Cloud Vault integration
    }

    object Observability {
        const val micrometerPrometheus = "io.micrometer:micrometer-registry-prometheus:${Versions.micrometer}" // Micrometer + Prometheus registry
    }

    object Logging {
        const val logstashLogback = "net.logstash.logback:logstash-logback-encoder:${Versions.logstashLogback}"
        // Enables structured logging (JSON) for Logback. Required if using logback-spring.xml with JSON encoders.
    }

    object OpenAPI {
        const val springdocWebflux = "org.springdoc:springdoc-openapi-starter-webflux-ui:${Versions.springdocOpenApi}"
        // OpenAPI documentation for Spring WebFlux apps (includes Swagger UI)
    }

    object Validation {
        const val jakartaValidation = "jakarta.validation:jakarta.validation-api:${Versions.jakartaValidation}" // Java validation API
        const val hibernateValidator = "org.hibernate.validator:hibernate-validator:${Versions.hibernateValidator}" // Hibernate implementation of Jakarta Validation
        const val jakartaEl = "org.glassfish:jakarta.el:${Versions.jakartaEl}" // Expression Language support (required by validators)
    }

    object Test {
        const val junitApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}" // JUnit 5 API
        const val junitEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}" // JUnit 5 runtime engine
        const val wiremock = "com.github.tomakehurst:wiremock-jre8:${Versions.wiremock}" // HTTP mocking for integration tests
        const val restAssured = "io.rest-assured:rest-assured:${Versions.restAssured}" // Fluent HTTP testing DSL
        const val reactorTest = "io.projectreactor:reactor-test" // Reactor-specific test utilities
        const val springSecurityTest = "org.springframework.security:spring-security-test" // Mock support for Spring Security
        const val archunit = "com.tngtech.archunit:archunit-junit5:${Versions.archunit}" // Enforces architecture rules with tests

        // Workaround to strictly enforce version of JUnit platform commons and avoid classpath resolution issues
        val junitPlatformCommonsStrict = moduleWithStrictVersion(
            "org.junit.platform:junit-platform-commons", Versions.junitPlatform
        )
    }
}

object SecurityFixes {
    const val commonsBeanutils = "commons-beanutils:commons-beanutils:${Versions.commonsBeanutils}"
    const val commonsIo = "commons-io:commons-io:${Versions.commonsIo}"
    const val httpClient5 = "org.apache.httpcomponents.client5:httpclient5:${Versions.httpClient5}"
    const val artemis = "org.apache.activemq:artemis-core-client:${Versions.artemis}"
    const val jettyServer = "org.eclipse.jetty:jetty-server:${Versions.jetty}"
    const val jettyHttp = "org.eclipse.jetty:jetty-http:${Versions.jetty}"
}

/**
 * Helper function to create a module dependency with a strict version constraint.
 * This prevents version conflicts that can occur with transitive dependencies.
 */
fun moduleWithStrictVersion(groupAndName: String, version: String): ExternalModuleDependency =
    DefaultExternalModuleDependency(
        groupAndName.substringBefore(":"), groupAndName.substringAfter(":"), version
    ).apply {
        this.version {
            strictly(version)
        }
    }
