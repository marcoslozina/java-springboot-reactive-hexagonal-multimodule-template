package com.company.templateservice.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements ProductInputPort {

    private final ProductOutputPort productOutputPort;

    @Autowired
    public ProductService(ProductOutputPort productOutputPort) {
        this.productOutputPort = productOutputPort;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return productOutputPort.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }
}