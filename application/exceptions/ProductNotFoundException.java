package com.company.templateservice.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)  // Devuelve un 404 automáticamente si se lanza esta excepción
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Product with ID " + id + " not found.");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}

