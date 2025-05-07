# 📽️ Template Service

## 📌 Project Status

[![CI](https://github.com/marcoslozina/template-service/actions/workflows/ci.yml/badge.svg)](https://github.com/marcoslozina/template-service/actions/workflows/ci.yml)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=marcoslozina_template-service&metric=coverage)](https://sonarcloud.io/summary/new_code?id=marcoslozina_template-service)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=marcoslozina_template-service&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=marcoslozina_template-service)
[![Release](https://img.shields.io/github/v/release/marcoslozina/template-service)](https://github.com/marcoslozina/template-service/releases)



---

## 🌱 Overview

**Template Service** is a production-ready backend starter built with **Hexagonal Architecture**, reactive programming, and cloud-native best practices.  
It provides a clean and extensible foundation for building secure, observable, and testable microservices.

---

## 📏 Architecture Diagram

![Hexagonal Architecture](docs/images/architecture-diagram.png)

---

## 🛠️ Technology Stack by Layer

### 🛋️ Development

[![Java](https://img.shields.io/badge/Java-23-blue?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.4-brightgreen?logo=spring)](https://spring.io/projects/spring-boot)
[![Gradle](https://img.shields.io/badge/Gradle-8.7-green?logo=gradle)](https://gradle.org/)
[![Reactive](https://img.shields.io/badge/WebFlux-Reactive-orange?logo=reactivex)](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)
[![R2DBC](https://img.shields.io/badge/R2DBC-Reactive%20DB-blueviolet)](https://r2dbc.io/)

> Java 23, Spring Boot 3.4.4, Gradle Kotlin DSL and WebFlux for reactive backend development.

### 🔐 Security

[![Vault](https://img.shields.io/badge/Vault-Secrets-black?logo=hashicorp)](https://www.vaultproject.io/)
[![OAuth2](https://img.shields.io/badge/OAuth2-Resource_Server-yellow)](https://oauth.net/2/)
[![Spring Security](https://img.shields.io/badge/Spring_Security-RBAC-red)](https://spring.io/projects/spring-security)

> OAuth2 Resource Server, Role-Based Access Control (RBAC), and Vault for secure credentials.

### 🔄 CI / CD

[![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-Automation-blue?logo=githubactions)](https://docs.github.com/en/actions)
[![Release](https://img.shields.io/github/v/release/marcoslozina/template-service)](https://github.com/marcoslozina/template-service/releases)
[![Dependencies](https://img.shields.io/badge/Dependencies-Up--to--date-brightgreen)](https://github.com/ben-manes/gradle-versions-plugin)

> Fully automated CI/CD pipeline for testing, formatting, code analysis, and versioned releases. Weekly dependency check included.

### 🌍 Security & Vulnerabilities

[![OWASP](https://img.shields.io/badge/OWASP-Dependency_Check-lightgrey)](https://owasp.org/www-project-dependency-check/)

> Runs `OWASP Dependency Check` on schedule to detect vulnerabilities in 3rd-party libraries.

### 📈 Observability

[![Prometheus](https://img.shields.io/badge/Prometheus-Metrics-orange?logo=prometheus)](https://prometheus.io/)
[![Grafana](https://img.shields.io/badge/Grafana-Dashboard-yellow?logo=grafana)](https://grafana.com/)
[![Loki](https://img.shields.io/badge/Loki-Logs-grey?logo=grafana)](https://grafana.com/oss/loki/)
[![Logstash](https://img.shields.io/badge/Logstash-Structured_Logs-black)](https://www.elastic.co/logstash)

> Metrics via Micrometer + Prometheus, dashboards via Grafana, and structured logging using Logstash.

### 📊 Code Quality

[![Jacoco](https://img.shields.io/badge/Jacoco-Coverage-red?logo=codecov)](https://www.jacoco.org/jacoco/)
[![SonarCloud](https://img.shields.io/badge/SonarCloud-Analysis-yellow?logo=sonarcloud)](https://sonarcloud.io/dashboard?id=marcoslozina_template-service)
[![ArchUnit](https://img.shields.io/badge/ArchUnit-Architecture-blue)](https://www.archunit.org/)
[![Checkstyle](https://img.shields.io/badge/Checkstyle-Static_Analysis-orange)](https://checkstyle.org/)
[![PMD](https://img.shields.io/badge/PMD-Rules-blueviolet)](https://pmd.github.io/)

> Code coverage, static analysis, and architectural constraints automatically enforced via CI.

### 🖌️ Style & Formatting

[![Spotless](https://img.shields.io/badge/Style-Spotless-blue)](https://github.com/diffplug/spotless)
[![Google Format](https://img.shields.io/badge/Style-Google%20Java%20Format-ffb400)](https://github.com/google/google-java-format)
[![Lint](https://img.shields.io/badge/linter-Checkstyle-orange)](https://checkstyle.org/)
> Consistent code formatting enforced automatically with Spotless + Google Java Format.

---

## 📦 Build & Run Commands

```bash
# Clean, build, and run tests
./gradlew clean build

# Run the app
./gradlew bootRun

# Run unit tests
./gradlew test

# Run architecture tests
./gradlew archTest

# Run code formatting check
./gradlew spotlessCheck

# Generate Jacoco report
./gradlew jacocoTestReport

# Check dependency updates
./gradlew dependencyUpdates -Drevision=release

# Run security dependency audit
./gradlew dependencyCheckAnalyze
```

---

## 📂 Project Structure

```text
src/
 ├── main/
 │    ├── java/
 │    │    └── com/company/templateservice/
 │    │         ├── application/   # Application services (use cases)
 │    │         ├── domain/         # Core domain models and business logic
 │    │         ├── infrastructure/ # Outbound adapters (DB, Kafka, Vault, etc.)
 │    │         ├── adapter/         # Inbound adapters (controllers, API endpoints)
 │    │         ├── shared/          # Shared utilities, exceptions, constants
 │    │         └── config/          # Spring Boot configuration classes
 │    └── resources/                # Application properties, YAML configs
 └── test/
      └── java/
           └── com/company/templateservice/
                ├── application/   # Application layer unit tests
                ├── domain/         # Domain model and business rule tests
                ├── infrastructure/ # Infrastructure integration tests
                ├── adapter/        # API/controller endpoint tests
                ├── shared/         # Shared utils testing
                └── integration/    # Full end-to-end integration tests
Dockerfile
build.gradle.kts
settings.gradle.kts
README.md
docker-compose.yml
```

---

## 🤝 Contributing

1. Fork this repo
2. Clone it locally
3. Create a new feature branch
4. Commit and push your changes
5. Open a pull request

---

## 📜 License

Licensed under the MIT License. See [`LICENSE`](LICENSE) for more.

---

> Made with ❤️ by [@marcoslozina](https://github.com/marcoslozina)

