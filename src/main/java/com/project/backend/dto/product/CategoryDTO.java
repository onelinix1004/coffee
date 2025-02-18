package com.project.backend.dto.product;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    private Long id;
    private String name;
    private Long parentId;
    private List<CategoryDTO> subcategories;
}

