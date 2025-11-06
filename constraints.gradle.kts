dependencies {
    constraints {
        // 🔐 Spring Framework / Security (DoS, Auth Bypass, Password length)
        // Updated to latest stable versions compatible with Spring Boot 3.3.6
        // Note: Spring Boot 3.3.6 manages these versions, but we force them for security
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
        // Updated to latest stable version
        implementation("io.netty:netty-common:4.1.112.Final")
        implementation("io.netty:netty-handler:4.1.112.Final")

        // 🔐 Nimbus JOSE JWT (DoS via parsing)
        // Updated to latest stable version
        implementation("com.nimbusds:nimbus-jose-jwt:9.40")

        // ⚠️ Logback-core (EL Injection + CSRF via config)
        // Updated to latest stable version
        implementation("ch.qos.logback:logback-core:1.5.20")
        implementation("ch.qos.logback:logback-classic:1.5.20")

        // 🔒 Bouncy Castle (Marvin attack, DNS poisoning, Infinite loop, CPU usage)
        implementation("org.bouncycastle:bcprov-jdk18on:1.78.1")

        // ⚠️ Apache Commons
        implementation("commons-beanutils:commons-beanutils:1.9.4")
        implementation("commons-io:commons-io:2.16.1")

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
