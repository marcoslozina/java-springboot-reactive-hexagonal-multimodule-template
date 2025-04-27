plugins {
    java
    id("io.spring.dependency-management")
}

val springBootVersion: String by project
val springCloudVersion: String by project
val junitVersion: String by project

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23)) // o leerlo también de properties si querés
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
}
