#!/bin/bash

set -e

echo "🔨 Iniciando restructuración de proyecto..."

# Crear carpeta gradle para configuración modular
mkdir -p gradle

# Mover lógica de calidad a quality.gradle
cat > gradle/quality.gradle <<EOF
plugins {
    id 'com.diffplug.spotless' version "\${spotlessVersion}"
    id 'pmd'
    id 'org.sonarqube' version "\${sonarQubeVersion}"
}

spotless {
    java {
        target 'src/**/*.java'
        googleJavaFormat()
    }
}

pmd {
    toolVersion = "\${pmdVersion}"
    sourceSets = [sourceSets.main]
    ruleSetFiles = files("\${rootDir}/config/pmd/ruleset.xml")
    ruleSets = []
}

sonarqube {
    properties {
        property "sonar.projectKey", "template-service"
        property "sonar.host.url", "https://sonarqube.example.com"
    }
}
EOF

# Mover lógica de testing a testing.gradle
cat > gradle/testing.gradle <<EOF
plugins {
    id 'jacoco'
    id 'info.solidsoft.pitest' version "\${pitestVersion}"
}

jacoco {
    toolVersion = "\${jacocoVersion}"
}

tasks.withType(Test).configureEach {
    finalizedBy 'jacocoTestReport'
}

tasks.named('jacocoTestReport') {
    dependsOn 'test'
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

pitest {
    junit5PluginVersion = "\${pitestJunitPluginVersion}"
    targetClasses = ['com.company.templateservice.*']
    mutationThreshold = 80
}
EOF

# Mover dependencias a dependencies.gradle
cat > gradle/dependencies.gradle <<EOF
dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:\${springCloudVersion}"
        mavenBom "io.opentelemetry:opentelemetry-bom:\${otelVersion}"
        mavenBom "com.fasterxml.jackson:jackson-bom:\${jacksonBomVersion}"
    }
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-webflux'
    implementation 'org.springframework.boot:spring-boot-starter-data-r2dbc'
    implementation "io.r2dbc:r2dbc-h2:\${r2dbcH2Version}"
    implementation "org.hibernate.reactive:hibernate-reactive-core:\${hibernateReactiveVersion}"

    implementation "software.amazon.awssdk:s3:\${awsSdkVersion}"
    implementation "software.amazon.awssdk:dynamodb:\${awsSdkVersion}"
    implementation "software.amazon.awssdk:sqs:\${awsSdkVersion}"
    implementation "software.amazon.awssdk:sns:\${awsSdkVersion}"

    implementation "io.projectreactor.kafka:reactor-kafka:\${reactorKafkaVersion}"
    implementation 'com.fasterxml.jackson.core:jackson-databind'
    implementation 'io.micrometer:micrometer-tracing-bridge-otel'
    implementation "io.opentelemetry:opentelemetry-exporter-otlp"

    implementation "net.sourceforge.pmd:pmd-java:\${pmdVersion}"
}
EOF

# Mover seguridad a security.gradle
cat > gradle/security.gradle <<EOF
plugins {
    id 'org.owasp.dependencycheck' version '8.4.1'
    id 'com.github.ben-manes.versions' version "\${benManesVersion}"
}

dependencyUpdates {
    revision = 'release'
    outputFormatter = 'json'
    outputDir = 'build/dependencyUpdates'
    reportfileName = 'report'
}

dependencyCheck {
    failBuildOnCVSS = 7
    suppressionFile = file("\${project.rootDir}/dependency-check-suppressions.xml")
    analyzers {
        assemblyEnabled = false
    }
}

tasks.register('checkAll') {
    dependsOn 'clean', 'test', 'jacocoTestReport', 'dependencyUpdates', 'dependencyCheckAnalyze'
}
EOF

# Crear observability.gradle (placeholder)
cat > gradle/observability.gradle <<EOF
// Observabilidad: por ahora vacío (a futuro logs estructurados, tracing avanzado, etc.)
EOF

# Crear suppressions vacío si no existe
if [ ! -f "dependency-check-suppressions.xml" ]; then
    cat > dependency-check-suppressions.xml <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<suppressions xmlns="https://jeremylong.github.io/DependencyCheck/dependency-suppression.1.2.xsd">
    <!-- Ejemplo -->
    <suppress>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>
        <notes>
            <note>Auditado internamente, se permite temporalmente.</note>
        </notes>
    </suppress>
</suppressions>
EOF
fi

# Limpiar build.gradle
cat > build.gradle <<EOF
plugins {
    id 'java'
    id 'org.springframework.boot' version "\${springBootVersion}"
    id 'io.spring.dependency-management' version "\${dependencyManagementVersion}"
}

group = 'com.company.templateservice'
version = '1.0.0-RELEASE'

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion.toInteger()))
    }
}

springBoot {
    mainClass.set("com.company.templateservice.Application")
}

apply from: "\${rootDir}/gradle/dependencies.gradle"
apply from: "\${rootDir}/gradle/quality.gradle"
apply from: "\${rootDir}/gradle/testing.gradle"
apply from: "\${rootDir}/gradle/observability.gradle"
apply from: "\${rootDir}/gradle/security.gradle"

subprojects {
    apply plugin: 'java'
    apply plugin: 'io.spring.dependency-management'

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(javaVersion.toInteger()))
        }
    }

    dependencies {
        testImplementation "org.junit.jupiter:junit-jupiter:\${junitVersion}"
        testImplementation "org.mockito:mockito-core:\${mockitoVersion}"
        testImplementation "com.tngtech.archunit:archunit-junit5:\${archunitVersion}"
    }

    tasks.withType(Test).configureEach {
        useJUnitPlatform()
        testLogging.events = ["passed", "skipped", "failed"]
    }

    plugins.withId('org.springframework.boot') {
        bootJar.enabled = false
    }
    jar {
        enabled = true
    }
}
EOF

echo "✅ Restructuración completada con éxito."
