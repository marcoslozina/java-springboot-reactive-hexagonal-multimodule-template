// ✅ Load project-wide version values from gradle.properties file
val logstashLogbackVersion = project.findProperty("logstashLogbackVersion") as String           // For structured logging (Logback JSON encoder)
val springBootVersion = project.findProperty("springBootVersion") as String                      // Spring Boot core version
val springCloudVersion = project.findProperty("springCloudVersion") as String                    // Spring Cloud dependencies (Vault, Config, etc.)
val dependencyManagementVersion = project.findProperty("dependencyManagementVersion") as String  // Spring dependency management plugin
val checkstyleVersion = project.findProperty("checkstyleVersion") as String                      // Checkstyle static analysis tool
val javaVersion = project.findProperty("javaVersion") as String                                  // Java language version (used in toolchain)
val junitVersion = project.findProperty("junitVersion") as String                                // JUnit Jupiter version
val junitPlatformVersion = project.findProperty("junitPlatformVersion") as String                // JUnit Platform (engine, launcher, etc.)

// ✅ Plugins used in the project
plugins {
    java // Java support
    jacoco // Code coverage tool
    id("checkstyle") // Static code analysis
    id("org.springframework.boot") version "3.4.4" // Spring Boot support
    id("io.spring.dependency-management") version "1.1.7" // BOM and version alignment
    id("com.diffplug.spotless") version "7.0.3" // Code formatting
    id("org.sonarqube") version "6.1.0.5360" // Code quality analysis with SonarQube
    id("org.owasp.dependencycheck") version "12.1.1" // Security audit for dependencies
    id("com.github.ben-manes.versions") version "0.52.0" // Plugin to check for dependency updates
}

group = "com.company.templateservice"
version = "1.0.0-RELEASE"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion.toInt()))
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
        mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
        mavenBom("org.junit:junit-bom:$junitVersion")
    }
}

dependencies {
    // 🌐 App dependencies
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

    implementation(Dependencies.Logging.logstashLogback)

    // 🧪 Fix JUnit reflection issue
    implementation(Dependencies.Test.junitPlatformCommonsStrict)

    // 🔍 Testing
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
    description = "Runs architecture tests"
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
    toolVersion = checkstyleVersion
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
            languageVersion.set(JavaLanguageVersion.of(javaVersion.toInt()))
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }

    tasks.register("printVersion") {
        group = "info"
        description = "Prints the project version"
        doLast {
            println("Project version: ${project.version}")
        }
    }
}

configurations.all {
    resolutionStrategy {
        force("org.junit.jupiter:junit-jupiter:$junitVersion")
        force("org.junit.jupiter:junit-jupiter-api:$junitVersion")
        force("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
        force("org.junit.platform:junit-platform-commons:$junitPlatformVersion")
        force("org.junit.platform:junit-platform-engine:$junitPlatformVersion")
        force("org.junit.platform:junit-platform-launcher:$junitPlatformVersion")
    }
}
