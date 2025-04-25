# 🧱 Template Service

[![CI Status](https://img.shields.io/badge/CI--Status-Passed-brightgreen?style=flat&logo=github)](https://github.com/marcoslozina/template-service/actions/workflows/ci.yml)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=marcoslozina_template-service&metric=coverage)](https://sonarcloud.io/summary/new_code?id=marcoslozina_template-service)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=marcoslozina_template-service&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=marcoslozina_template-service)
[![Release](https://img.shields.io/github/v/release/marcoslozina/template-service)](https://github.com/marcoslozina/template-service/releases)


---

## 🧰 Tech Stack

![Java](https://img.shields.io/badge/Java-23-blue?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.4-brightgreen?logo=spring)
![Gradle](https://img.shields.io/badge/Gradle-8.7-green?logo=gradle)
![Reactive](https://img.shields.io/badge/Reactive-WebFlux-orange?logo=reactivex)
![Kafka](https://img.shields.io/badge/Kafka-Apache-black?logo=apachekafka)
![Vault](https://img.shields.io/badge/HashiCorp-Vault-black?logo=hashicorp)
![Prometheus](https://img.shields.io/badge/Prometheus-Metrics-orange?logo=prometheus)
![Grafana](https://img.shields.io/badge/Grafana-Dashboard-yellow?logo=grafana)
![Loki](https://img.shields.io/badge/Loki-Logs-lightgrey?logo=grafana)
![Checkstyle](https://img.shields.io/badge/Checkstyle-Code_Style-orange)
![PMD](https://img.shields.io/badge/PMD-Static_Analysis-blueviolet)
![Jacoco](https://img.shields.io/badge/Jacoco-Coverage-red?logo=codecov)
![SonarCloud](https://img.shields.io/badge/SonarCloud-Analysis-yellow?logo=sonarcloud)
![Docker](https://img.shields.io/badge/Docker-Container-blue?logo=docker)
![Swagger](https://img.shields.io/badge/OpenAPI-Swagger_UI-green?logo=swagger)

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
