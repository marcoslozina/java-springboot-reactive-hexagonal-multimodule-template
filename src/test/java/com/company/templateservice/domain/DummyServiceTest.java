package com.company.templateservice.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DummyServiceTest {

  private final DummyService service = new DummyService();

  @Test
  void testGreet() {
    assertEquals("Hello, Marcos", service.greet("Marcos"));
  }

  @Test
  void testIsPositive() {
    assertTrue(service.isPositive(1));
    assertFalse(service.isPositive(-1));
  }
}
