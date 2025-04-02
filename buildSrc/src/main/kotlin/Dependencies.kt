import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency

object Dependencies {

    object Spring {
        const val bootWebflux = "org.springframework.boot:spring-boot-starter-webflux"
        const val bootSecurity = "org.springframework.boot:spring-boot-starter-security"
        const val bootOauth2 = "org.springframework.boot:spring-boot-starter-oauth2-resource-server"
        const val bootTest = "org.springframework.boot:spring-boot-starter-test"
        const val securityConfig = "org.springframework.security:spring-security-config"
    }

    object Cloud {
        const val vaultConfig = "org.springframework.cloud:spring-cloud-starter-vault-config"
    }

    object Observability {
        const val micrometerPrometheus = "io.micrometer:micrometer-registry-prometheus:${Versions.micrometer}"
    }

    object Logging {
        const val logstashLogback = "net.logstash.logback:logstash-logback-encoder:${Versions.logstashLogback}"
    }

    object OpenAPI {
        const val springdocWebflux = "org.springdoc:springdoc-openapi-starter-webflux-ui:${Versions.springdocOpenApi}"
    }

    object Validation {
        const val jakartaValidation = "jakarta.validation:jakarta.validation-api:${Versions.jakartaValidation}"
        const val hibernateValidator = "org.hibernate.validator:hibernate-validator:${Versions.hibernateValidator}"
        const val jakartaEl = "org.glassfish:jakarta.el:${Versions.jakartaEl}"
    }

    object Test {
        const val junitApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
        const val junitEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
        const val wiremock = "com.github.tomakehurst:wiremock-jre8:${Versions.wiremock}"
        const val restAssured = "io.rest-assured:rest-assured:${Versions.restAssured}"
        const val reactorTest = "io.projectreactor:reactor-test"
        const val springSecurityTest = "org.springframework.security:spring-security-test"
        const val archunit = "com.tngtech.archunit:archunit-junit5:${Versions.archunit}"

        val junitPlatformCommonsStrict = moduleWithStrictVersion(
            "org.junit.platform:junit-platform-commons", Versions.junitPlatform
        )
    }
}


fun moduleWithStrictVersion(groupAndName: String, version: String): ExternalModuleDependency =
    DefaultExternalModuleDependency(
        groupAndName.substringBefore(":"), groupAndName.substringAfter(":"), version
    ).apply {
        this.version {
            strictly(version)
        }
    }
