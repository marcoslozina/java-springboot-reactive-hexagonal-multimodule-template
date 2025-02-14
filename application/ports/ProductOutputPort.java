package com.company.templateservice.application.ports;

import com.company.templateservice.domain.entities.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductOutputPort {

    Mono<Product> save(Product product);

    Mono<Product> findById(Long id);

    Flux<Product> findAll();

    Mono<Void> deleteById(Long id);
}
