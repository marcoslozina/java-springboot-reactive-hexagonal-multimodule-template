# 🛡️ Security and Observability - Local Setup

This project includes services like **Keycloak** (for authentication), **Grafana** (for observability), and optionally *
*Prometheus** (for metrics). Follow this guide to test and verify everything is working correctly.

---

## 📦 Prerequisites

- Docker
- Docker Compose
- Free ports: `3000` (Grafana), `8080` (Keycloak), `9090` (Prometheus if used)

---

## ▶️ How to start all services

From the root of the project, run:

```bash
docker-compose up -d
```

> This will start all containers defined in `docker-compose.yml`.

---

## ✅ Verify running services

### 🔐 Keycloak

- URL: [http://localhost:8080](http://localhost:8080)
- Default admin credentials:
    - Username: `admin`
    - Password: `admin` (or check your `docker-compose.yml`)
- Recommended actions:
    - Create a custom **Realm**.
    - Create a **Client** of type `confidential`.
    - Configure redirect URIs if needed.

---

### 📊 Grafana

- URL: [http://localhost:3000](http://localhost:3000)
- Default credentials:
    - Username: `admin`
    - Password: `admin`
- Recommended actions:
    - Add **Prometheus** as a data source: `http://prometheus:9090`
    - Import dashboards for Spring Boot, JVM metrics, or database observability.

---

### 📈 (Optional) Prometheus

- URL: [http://localhost:9090](http://localhost:9090)
- Used as data source for Grafana
- Check that it's scraping metrics correctly

---

## 🧪 How to test integration

1. Ensure your services expose metrics at `/actuator/prometheus` (Spring Boot).
2. In Prometheus, try running basic queries like:
   ```promQL
   up{job="template-service"}
   ```
3. In Grafana, import a dashboard with ID `4701` (Spring Boot Metrics).

---

## 🧼 How to stop all services

```bash
docker-compose down
```

> This will stop and remove the containers (but not the persistent volumes).

---

## 🛠 Additional Tips

- If you make changes to Keycloak or Grafana config, consider using persistent volumes to retain settings across
  restarts.
- You can export your Keycloak realm using `kcadm.sh` and include it in version control for reproducibility.

---

## 🧪 Template Service Integration Test (✅ Confirmed)

Your `template-service` backend is now fully integrated and visible in Prometheus.

### What was verified:

- ✅ `template-service` is listed in Prometheus targets at [http://localhost:9090/targets](http://localhost:9090/targets)
- ✅ It exposes metrics at: [http://localhost:8081/actuator/prometheus](http://localhost:8081/actuator/prometheus)
- ✅ It shows `up{job="template-service"}` with value `1` in Prometheus

You can now query it using:

```promql
up{job="template-service"}
```

And use that job in Grafana dashboards as a data source.

