package com.company.templateservice.domain.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.*;

@Table("products")  // Equivalente a @Table en JPA pero para R2DBC
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private Long id; // En R2DBC no se usa @GeneratedValue con IDENTITY

    private String name;
    private Double price;
}
