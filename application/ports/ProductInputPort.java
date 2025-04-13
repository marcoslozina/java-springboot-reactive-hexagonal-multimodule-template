package com.company.templateservice.application.ports;

import com.company.templateservice.shared.dtos.ProductDTO;

import java.util.List;

public interface ProductInputPort {
    ProductDTO getProductById(Long id);

    List<ProductDTO> getAllProducts();

    ProductDTO createProduct(ProductDTO productDTO);

    void deleteProduct(Long id);
}
