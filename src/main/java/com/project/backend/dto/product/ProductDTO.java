package com.project.backend.dto.product;

import com.project.backend.entity.Brand;
import com.project.backend.entity.product.Category;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private BigDecimal price;
    private Category category;
    private Brand brand;
}

