package com.boot.user.dto;

import lombok.Data;

@Data
public class PaginationInfo {
    private long totalPages;
    private long currentPage;
    private long totalElements;
    private long pageSize;
}
