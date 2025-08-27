package org.spring.hayat.dto;

import lombok.Data;

@Data
public class CategoryRequest {
    private Long parentId;
    private String name;
    private String description;
}
