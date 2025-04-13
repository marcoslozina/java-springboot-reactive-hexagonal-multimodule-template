# 🧱 Template Service

[![CI](https://github.com/marcoslozina/template-service/actions/workflows/gradle.yml/badge.svg)](https://github.com/marcoslozina/template-service/actions/workflows/gradle.yml)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=marcoslozina_template-service&metric=coverage)](https://sonarcloud.io/summary/new_code?id=marcoslozina_template-service)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=marcoslozina_template-service&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=marcoslozina_template-service)
[![Release](https://img.shields.io/github/v/release/marcoslozina/template-service)](https://github.com/marcoslozina/template-service/releases)

---

## 🧩 Overview

**Template Service** is a production-ready backend starter built with **Hexagonal Architecture**, reactive programming, and modern cloud-native tooling.  
It is designed to provide a clean, extensible foundation for microservices running in secure, observable, and testable environments.

---

## 🚀 Tech Stack

| Layer                      | Technology                                                                 |
|---------------------------|----------------------------------------------------------------------------|
| Language                  | Java 23 (OpenJDK)                                                          |
| Framework                 | Spring Boot 3.4.4 (Reactive - WebFlux)                                     |
| Architecture              | Hexagonal Architecture (Ports & Adapters)                                  |
| Build Tool                | Gradle Kotlin DSL (`build.gradle.kts`)                                     |
| Configuration             | Spring Cloud Config with HashiCorp Vault                                   |
| API Specification         | OpenAPI 3 + springdoc-openapi                                              |
| Reactive DB (Dev)         | R2DBC with H2                                                               |
| Kafka Messaging           | Spring Kafka (Reactive Support)                                            |
| Validation                | Jakarta Validation + Hibernate Validator                                   |

---

## 🔐 Security

- OAuth2 Resource Server
- Spring Security + Role-based access control
- Vault integration for secret management

---

## ⚙️ Dev Tools & Code Quality

| Tool              | Purpose                          |
|-------------------|----------------------------------|
| Spotless          | Code formatting (Google style)   |
| Checkstyle        | Static code analysis             |
| SonarCloud        | Code quality & security reports  |
| OWASP Dependency Check | CVE vulnerability scanning   |
| Jacoco            | Test coverage reporting          |
| ArchUnit          | Architecture rule enforcement    |

---

## 🔄 CI/CD Pipelines

| Workflow             | Description                                                 |
|----------------------|-------------------------------------------------------------|
| `gradle.yml`         | Build, test, lint, and format check on PRs                  |
| `sonar.yml`          | Runs SonarCloud scan with coverage integration              |
| `release.yml`        | Auto-builds and packages `.jar` on Git tag (e.g. v1.0.0)    |
| GitHub Actions       | Full integration for continuous integration & delivery      |

---

## 📦 Build & Run

```bash
./gradlew clean build
./gradlew bootRun
