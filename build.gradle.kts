
plugins {
    java
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.diffplug.spotless") version "7.0.2"
    id("checkstyle")
    id("org.sonarqube") version "6.1.0.5360"
    id("org.owasp.dependencycheck") version "12.1.0"
    id("com.github.ben-manes.versions") version "0.52.0"
    jacoco
}

group = "com.company.templateservice"
version = "1.0.0-RELEASE"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
    sourceCompatibility = JavaVersion.VERSION_23
    targetCompatibility = JavaVersion.VERSION_23
}

springBoot {
    mainClass.set("com.company.Application") // Asegurate de que esta clase exista
}

spotless {
    java {
        googleJavaFormat("1.17.0")
        target("src/**/*.java")
    }
}

checkstyle {
    toolVersion = "10.12.2"
    configFile = file("$rootDir/config/checkstyle/checkstyle.xml")
    isIgnoreFailures = false
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2024.0.1")
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.4.4")
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")

    group = rootProject.group
    version = rootProject.version

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(23))
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }

    tasks.register<Test>("archTest") {
        description = "Ejecuta los tests de arquitectura"
        useJUnitPlatform()
        include("**/*ArchitectureTest.class")
    }
}

dependencies {
    // Observabilidad y configuración segura
    implementation(Deps.Observability.micrometerPrometheus)
    implementation(Deps.Cloud.vaultConfig)
    implementation(Deps.Spring.bootOauth2)
    implementation(Deps.Spring.bootSecurity)
    implementation(Deps.Spring.bootWebflux)
    implementation(Deps.Spring.securityConfig)
    implementation(Deps.OpenAPI.springdocWebflux)
    implementation(Deps.Validation.jakartaValidation)
    implementation(Deps.Validation.hibernateValidator)
    implementation(Deps.Validation.jakartaEl)

    // Testing
    testImplementation(Deps.Test.wiremock)
    testImplementation(Deps.Test.restAssured)
    testImplementation(Deps.Test.junitApi)
    testRuntimeOnly(Deps.Test.junitEngine)
    testImplementation(Deps.Spring.bootTest)
    testImplementation(Deps.Test.reactorTest)
    testImplementation(Deps.Test.springSecurityTest)
    testImplementation(Deps.Test.archunit)
}

tasks.named<Jar>("bootJar") {
    archiveFileName.set("app.jar")
}

tasks.register("audit") {
    dependsOn("checkAll")
}

sonarqube {
    properties {
        property("sonar.projectKey", "marcoslozina_template-service")
        property("sonar.organization", "marcoslozina")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}
