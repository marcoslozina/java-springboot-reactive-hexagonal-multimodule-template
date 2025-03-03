#!/bin/bash

set -e

echo "🔨 Aplicando reestructuración modular mixta..."

# Crear carpeta gradle para la configuración compartida
mkdir -p gradle

# 1️⃣ Crear `dependencies.gradle` con dependencias comunes (core y testing)
cat > gradle/dependencies.gradle <<EOF
dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:\${springCloudVersion}"
        mavenBom "io.opentelemetry:opentelemetry-bom:\${otelVersion}"
        mavenBom "com.fasterxml.jackson:jackson-bom:\${jacksonBomVersion}"
    }
}

dependencies {
    // Core Framework
    implementation 'org.springframework.boot:spring-boot-starter'

    // Observabilidad común
    implementation 'io.micrometer:micrometer-tracing-bridge-otel'
    implementation "io.opentelemetry:opentelemetry-exporter-otlp"

    // Calidad de código común
    implementation "net.sourceforge.pmd:pmd-java:\${pmdVersion}"

    // Testing común
    testImplementation "org.junit.jupiter:junit-jupiter:\${junitVersion}"
    testImplementation "org.mockito:mockito-core:\${mockitoVersion}"
    testImplementation "com.tngtech.archunit:archunit-junit5:\${archunitVersion}"
}
EOF

# 2️⃣ Crear `quality.gradle`
cat > gradle/quality.gradle <<EOF
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

# 3️⃣ Crear `testing.gradle`
cat > gradle/testing.gradle <<EOF
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
EOF

# 4️⃣ Crear `security.gradle`
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

# 5️⃣ Crear `observability.gradle` (placeholder, para crecer después)
cat > gradle/observability.gradle <<EOF
// Configuración de observabilidad si hace falta en el futuro (OTEL, Logs JSON, etc.)
EOF

# 6️⃣ Limpiar `build.gradle` root y aplicar imports
cat > build.gradle <<EOF
plugins {
    id 'java'
    id 'org.springframework.boot' version "\${springBootVersion}"
    id 'io.spring.dependency-management' version "\${dependencyManagementVersion}"
    id 'com.diffplug.spotless' version "\${spotlessVersion}"
    id 'pmd'
    id 'org.sonarqube' version "\${sonarQubeVersion}"
    id 'jacoco'
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
EOF

# 7️⃣ Revisión y limpieza de los submódulos (conservamos los específicos)

for module in adapters/in adapters/in/rest adapters/out/persistence application domain infrastructure shared; do
    module_gradle="${module}/build.gradle"
    if [ -f "$module_gradle" ]; then
        case "$module" in
            adapters/in|adapters/in/rest)
                echo "dependencies { implementation 'org.springframework.boot:spring-boot-starter-webflux' }" > "$module_gradle"
                ;;
            adapters/out/persistence)
                echo "dependencies { implementation 'org.springframework.boot:spring-boot-starter-data-r2dbc'; runtimeOnly 'org.postgresql:r2dbc-postgresql' }" > "$module_gradle"
                ;;
            application)
                echo "dependencies { implementation 'org.springframework.boot:spring-boot-starter-reactor' }" > "$module_gradle"
                ;;
            domain)
                echo "dependencies { /* Si hay algo específico se deja */ }" > "$module_gradle"
                ;;
            infrastructure)
                echo "dependencies { /* Por ahora vacío, si tiene cosas propias se agregan */ }" > "$module_gradle"
                ;;
            shared)
                echo "dependencies { implementation 'org.springframework.boot:spring-boot-starter' }" > "$module_gradle"
                ;;
        esac
    fi
done

echo "✅ Estructura mixta aplicada correctamente."
