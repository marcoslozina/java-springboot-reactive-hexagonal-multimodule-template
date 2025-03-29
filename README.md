# Template Service - JAVA21

## 📋 Descripción

Este es un template base para servicios backend en Java 21, siguiendo arquitectura hexagonal.

---

# ⚙️ Setup del Entorno

## 1️⃣ Instalar SDKMAN

```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
```

## 2️⃣ Entrar al proyecto y ejecutar:

```bash
sdk env
./bootstrap.sh
```

## 3️⃣ Ejecutar el build:

```bash
./gradlew clean build
```

## 4️⃣ (Opcional) Build en Docker:

```bash
./docker-build.sh
```

## 5️⃣ IntelliJ - Config recomendado:

- Project SDK: **21.0.5-amzn**
- Gradle JVM: **Project SDK**

## 6️⃣ Dependency Check:

```bash
./gradlew dependencyCheckAnalyze -Dorg.owasp.dependencycheck.apiKey=$OWASP_DEPENDENCY_CHECK_APIKEY
```

---

# 📂 Estructura recomendada

```
template-service/
├── .sdkmanrc
├── bootstrap.sh
├── docker-build.sh
├── gradle.properties
└── README.md
```

---

# 📣 Notas

- Este template es compatible con **Java 21**.
- Si actualizás Java o Gradle, actualizá estos archivos.
- Evitar `sudo` al usar Gradle.

![Build](https://github.com/USUARIO/template-service/actions/workflows/sonar.yml/badge.svg)
![Coverage](https://sonarcloud.io/api/project_badges/measure?project=USUARIO_TEMPLATE-SERVICE&metric=coverage)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=USUARIO_TEMPLATE-SERVICE&metric=alert_status)
![Release](https://img.shields.io/github/v/release/USUARIO/template-service)

