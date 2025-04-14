# [](https://github.com/marcoslozina/template-service/compare/v1.0.0...v) (2025-04-14)



# 📦 Changelog

This changelog documents all notable changes to the `template-service` project.  
Changes follow the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) specification.

---

## [1.0.0] - 2025-04-06

### ✨ Initial Release

- Backend Java template with Hexagonal Architecture
- Reactive Spring Boot 3 application (WebFlux-based)
- Security layer with Keycloak and Role-Based Access Control (RBAC)
- Docker Compose setup with PostgreSQL, Kafka, Loki, Vault, Prometheus, and Grafana
- OpenAPI (Swagger) documentation with security schema
- Audit logging for HTTP access requests
- Structured configuration for environments (`config/`, `shared/`)
- GitHub Actions CI with changelog automation
- Static code analysis tools: Checkstyle, PMD, Spotless, Git hooks
- Production-ready API gateway template (`apigateway.txt`)
