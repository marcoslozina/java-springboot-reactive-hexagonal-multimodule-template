package com.company.templateservice.application;

import com.company.templateservice.domain.DomainService;

public class UseCase {
  private final DomainService domainService;

  public UseCase(DomainService domainService) {
    this.domainService = domainService;
  }

  public void execute() {
    domainService.processDomainLogic();
  }
}
