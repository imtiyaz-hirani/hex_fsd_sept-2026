package com.springboot.helpdesk.dto.response;

import java.util.List;

public record FilterDto(
        List<TicketRespDto> list,
        PaginationDto paginationInfo
) {
}
