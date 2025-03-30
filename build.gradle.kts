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
}

springBoot {
    mainClass.set("com.company.Application")
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.4.4")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2024.0.1")
    }
}

dependencies {
    implementation(Dependencies.Observability.micrometerPrometheus)
    implementation(Dependencies.Cloud.vaultConfig)
    implementation(Dependencies.Spring.bootOauth2)
    implementation(Dependencies.Spring.bootSecurity)
    implementation(Dependencies.Spring.bootWebflux)
    implementation(Dependencies.Spring.securityConfig)
    implementation(Dependencies.OpenAPI.springdocWebflux)
    implementation(Dependencies.Validation.jakartaValidation)
    implementation(Dependencies.Validation.hibernateValidator)
    implementation(Dependencies.Validation.jakartaEl)

    implementation("org.junit.platform:junit-platform-commons") {
        version {
            strictly(Versions.junitPlatform)
        }
    }

    // Testing
    testImplementation(Dependencies.Test.wiremock)
    testImplementation(Dependencies.Test.restAssured)
    testImplementation(Dependencies.Test.junitApi)
    testRuntimeOnly(Dependencies.Test.junitEngine)

    testImplementation(Dependencies.Spring.bootTest) {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(group = "org.junit.jupiter", module = "junit-jupiter")
        exclude(group = "org.junit", module = "junit-bom")
        exclude(group = "org.junit.platform", module = "junit-platform-commons")
    }

    testImplementation(Dependencies.Test.reactorTest)
    testImplementation(Dependencies.Test.springSecurityTest)
    testImplementation(Dependencies.Test.archunit)
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.named<Jar>("bootJar") {
    archiveFileName.set("app.jar")
}

tasks.register("audit") {
    dependsOn("checkAll")
}

tasks.register<Test>("archTest") {
    description = "Ejecuta los tests de arquitectura"
    useJUnitPlatform()
    include("**/*ArchitectureTest.class")
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

sonarqube {
    properties {
        property("sonar.projectKey", "marcoslozina_template-service")
        property("sonar.organization", "marcoslozina")
        property("sonar.host.url", "https://sonarcloud.io")
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
}

configurations.all {
    resolutionStrategy {
        force("org.junit.jupiter:junit-jupiter:${Versions.junit}")
        force("org.junit.jupiter:junit-jupiter-api:${Versions.junit}")
        force("org.junit.jupiter:junit-jupiter-engine:${Versions.junit}")
        force("org.junit.platform:junit-platform-commons:${Versions.junitPlatform}")
        force("org.junit.platform:junit-platform-engine:${Versions.junitPlatform}")
        force("org.junit.platform:junit-platform-launcher:${Versions.junitPlatform}")
    }
}
