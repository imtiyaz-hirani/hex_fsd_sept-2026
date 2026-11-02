package com.springboot.helpdesk.dto.response;

public record PaginationDto(
        int totalPages,
        Long totalRecords,
        Integer numRecordsInPage
) {
}
