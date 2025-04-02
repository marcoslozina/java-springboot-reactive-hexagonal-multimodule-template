package com.company.templateservice.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

public class HexagonalArchitectureTest {

  private final JavaClasses importedClasses =
      new ClassFileImporter().importPackages("com.company.templateservice");

  @Test
  void domainShouldNotDependOnApplicationOrAdapters() {
    ArchRuleDefinition.noClasses()
        .that()
        .resideInAPackage("..domain..")
        .should()
        .dependOnClassesThat()
        .resideInAnyPackage("..application..", "..adapters..")
        .check(importedClasses);
  }

  @Test
  void applicationShouldNotDependOnAdapters() {
    ArchRuleDefinition.noClasses()
        .that()
        .resideInAPackage("..application..")
        .should()
        .dependOnClassesThat()
        .resideInAnyPackage("..adapters..")
        .check(importedClasses);
  }

  @Test
  void adaptersShouldOnlyDependOnApplicationOrDomain() {
    ArchRuleDefinition.classes()
        .that()
        .resideInAPackage("..adapters..")
        .should()
        .onlyDependOnClassesThat()
        .resideInAnyPackage("..application..", "..domain..", "java..")
        .check(importedClasses);
  }
}
