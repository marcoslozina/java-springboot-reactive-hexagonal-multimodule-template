package com.company.templateservice.integration;

import com.company.templateservice.controller.AdminController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = AdminController.class)
@Import(com.company.templateservice.config.SecurityConfig.class)
public class SecurityIntegrationTest {

  @Autowired private WebTestClient webClient;

  @Test
  @WithMockUser(roles = "ADMIN")
  void adminWithRoleCanAccess() {
    webClient
        .get()
        .uri("/admin/hello")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(String.class)
        .isEqualTo("Hello Admin 👋");
  }

  @Test
  @WithMockUser(roles = "USER")
  void userWithoutAdminRoleIsDenied() {
    webClient.get().uri("/admin/hello").exchange().expectStatus().isForbidden();
  }
}
