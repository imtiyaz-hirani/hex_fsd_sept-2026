package com.springboot.helpdesk.dto.request;

public record CustomerDto( //add validation annotations
        String name,
        String city,
        String email,
        String username,
        String password
) {
}
