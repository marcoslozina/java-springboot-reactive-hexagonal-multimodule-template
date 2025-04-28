plugins {
    java
    id("io.spring.dependency-management")
}

val springBootVersion: String by project
val springCloudVersion: String by project
val junitVersion: String by project
val javaVersion: String by project

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion.toInt()))
    }
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
    implementation(Dependencies.Spring.bootWebflux)
    implementation(Dependencies.Spring.bootSecurity)
    implementation(Dependencies.OpenAPI.springdocWebflux)

    testImplementation(Dependencies.Spring.bootTest)
    testImplementation("org.springframework.security:spring-security-test")
    implementation(project(":application"))
    implementation(project(":"))
}
