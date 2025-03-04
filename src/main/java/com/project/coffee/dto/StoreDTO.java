package com.project.coffee.dto;


import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDTO {

    private Integer storeId;
    private Integer userId;
    private String storeName;
    private String description;
    private Timestamp createdAt;

}
