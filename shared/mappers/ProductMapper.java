package com.company.templateservice.shared.mappers;

import com.company.templateservice.domain.entities.Product;
import com.company.templateservice.shared.dtos.ProductDTO;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }

    public static Product toEntity(ProductDTO productDTO) {
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getPrice());
    }
}
