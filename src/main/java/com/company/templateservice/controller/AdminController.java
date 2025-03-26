package com.company.templateservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @GetMapping("/hello")
  public Mono<String> helloAdmin() {
    return Mono.just("Hello Admin 👋");
  }
}
