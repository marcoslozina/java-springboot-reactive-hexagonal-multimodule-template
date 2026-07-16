dependencies {
    constraints {
        // 🔐 Spring Framework / Security (DoS, Auth Bypass, Password length)
        // Updated to latest stable versions compatible with Spring Boot 3.3.6
        // Note: Spring Boot 3.3.6 manages these versions, but we force them for security
        // ⚠️ spring-security-web NOT bumped for CVE-2026-47838 (X.509 impersonation): the OSS fix for the
        // 6.3.x line (6.3.18) is Enterprise-support-only; public fix requires 6.5.11, which needs a
        // Spring Boot minor version upgrade. See PR description for details.
        implementation("org.springframework:spring-web:6.1.15")
        implementation("org.springframework:spring-context:6.1.15")
        implementation("org.springframework.security:spring-security-web:6.3.5")
        implementation("org.springframework.security:spring-security-core:6.3.5")
        implementation("org.springframework.security:spring-security-crypto:6.3.5")

        // 🛡️ Jetty (SpringDoc, Swagger, etc.)
        // Updated to latest Jetty 11.x (compatible with Spring Boot 3.3)
        implementation("org.eclipse.jetty:jetty-server:11.0.21")
        implementation("org.eclipse.jetty:jetty-http:11.0.21")
        implementation("org.eclipse.jetty:jetty-util:11.0.21")
        implementation("org.eclipse.jetty:jetty-io:11.0.21")

        // ⚡ Netty (WebFlux internals, DoS vuln)
        // 🔐 Updated to fix CVE-2026-44249 (IPv6 subnet-filter bypass), CVE-2026-47691 (DNS cache poisoning),
        // CVE-2026-50560 (HTTP/2 DoS), CVE-2026-50010 (TLS hostname verification bypass) and others
        implementation("io.netty:netty-common:4.1.135.Final")
        implementation("io.netty:netty-handler:4.1.135.Final")
        implementation("io.netty:netty-resolver-dns:4.1.135.Final")
        implementation("io.netty:netty-codec-http2:4.1.135.Final")

        // 🔐 Nimbus JOSE JWT (DoS via parsing)
        // Updated to latest stable version
        implementation("com.nimbusds:nimbus-jose-jwt:9.40")

        // ⚠️ Logback-core (EL Injection + CSRF via config)
        // 🔐 Updated to fix CVE-2026-9828 (HardenedObjectInputStream allowlist bypass)
        implementation("ch.qos.logback:logback-core:1.5.33")
        implementation("ch.qos.logback:logback-classic:1.5.33")

        // 🔒 Bouncy Castle (Marvin attack, DNS poisoning, Infinite loop, CPU usage)
        // 🔐 Updated to fix CVE-2025-14813 (GOST28147 CTR keystream reuse after 255 blocks) + covert timing channel
        implementation("org.bouncycastle:bcprov-jdk18on:1.84")

        // 🔐 Jackson-databind (PolymorphicTypeValidator bypasses, SSRF, @JsonIgnoreProperties bypass)
        implementation("com.fasterxml.jackson.core:jackson-databind:2.21.4")

        // 🔐 Handlebars (transitive via wiremock-jre8) - FileTemplateLoader Path Traversal
        implementation("com.github.jknack:handlebars:4.5.2")

        // ⚠️ Apache Commons
        implementation("commons-beanutils:commons-beanutils:1.9.4")
        implementation("commons-io:commons-io:2.20.0")

        // 🌐 Apache HTTP Client 5
        implementation("org.apache.httpcomponents.client5:httpclient5:5.3.1")

        // 📨 ActiveMQ Artemis
        // Updated to latest stable version
        implementation("org.apache.activemq:artemis-core-client:2.35.0")

        // 🧪 XMLUnit (Insecure defaults for XSLT)
        // Updated to latest stable version
        implementation("org.xmlunit:xmlunit-core:2.10.0")
    }
}
