# 🧱 Template Service

## Estado del proyecto

- [![CI](https://github.com/marcoslozina/template-service/actions/workflows/ci.yml/badge.svg)](https://github.com/marcoslozina/template-service/actions/workflows/ci.yml) **CI Status**
- [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=marcoslozina_template-service&metric=coverage)](https://sonarcloud.io/summary/new_code?id=marcoslozina_template-service) **Coverage**
- [![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=marcoslozina_template-service&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=marcoslozina_template-service) **Quality Gate**
- [![Release](https://img.shields.io/github/v/release/marcoslozina/template-service)](https://github.com/marcoslozina/template-service/releases) **Latest Release**
- [![Code Style](https://img.shields.io/badge/style-Google%20Java%20Format-blue)](https://github.com/google/google-java-format) **Code Style**
- [![Lint](https://img.shields.io/badge/linter-Checkstyle-orange)](https://checkstyle.org/) **Lint**
- [![Dependencies](https://img.shields.io/badge/dependencies-up%20to%20date-brightgreen)](https://github.com/ben-manes/gradle-versions-plugin)

---

## 🧩 Overview

**Template Service** is a production-ready backend starter built with **Hexagonal Architecture**, reactive programming, and modern cloud-native tooling.  
It is designed to provide a clean, extensible foundation for microservices running in secure, observable, and testable environments.

---

## 🧱 Arquitectura

```text
           ┌──────────────────────────────┐
           │         External API         │
           │    (Clients / Users / UI)    │
           └────────────┬─────────────────┘
                        │
               ┌────────▼────────┐
               │  Application    │
               │ (Use Cases)     │
               └────────┬────────┘
                        │
         ┌──────────────┼──────────────┐
         ▼              ▼              ▼
   Domain Logic   Ports (Interfaces)   Adapters Out
                                    (DB, Kafka, Vault)
