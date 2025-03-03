package com.project.coffee.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {

    private Integer categoryId;
    private String name;
    private Integer parentId;
    private Timestamp createdAt;

}