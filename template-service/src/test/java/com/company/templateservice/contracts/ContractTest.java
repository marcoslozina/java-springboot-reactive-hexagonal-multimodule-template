package com.company.templateservice.contracts;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureStubRunner(ids = "com.company:user-service:+:stubs:8080", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ContractTest {

    @Test
    public void testContract() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/users/1", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
