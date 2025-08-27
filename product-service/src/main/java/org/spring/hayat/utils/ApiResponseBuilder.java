package org.spring.hayat.utils;


import org.spring.hayat.dto.ApiResponse;
import org.spring.hayat.dto.PaginationInfo;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ApiResponseBuilder {


    public <T> ResponseEntity<ApiResponse<T>> ok(T data, String code, String message) {
        return ResponseEntity.ok(new ApiResponse<>(code, message, data, LocalDateTime.now(), true, null));
    }


    public <T> ResponseEntity<ApiResponse<T>> okWithPagination(T data, String message, String code, Page<?> page) {
        return ResponseEntity.ok(new ApiResponse<>(code, message, data, LocalDateTime.now(), true, buildPaginationInfo(page)));

    }

    private PaginationInfo buildPaginationInfo(Page<?> page) {
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPage(page.getNumber());
        paginationInfo.setPageSize(page.getSize());
        paginationInfo.setTotalElements(page.getTotalElements());
        paginationInfo.setTotalPages(page.getTotalPages());
        return paginationInfo;
    }
}
