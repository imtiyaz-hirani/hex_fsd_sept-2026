package com.springboot.helpdesk.dto.response;

import java.util.List;

public record ChartDto(
        List<String> labels,
        List<Long> values
) {
}
