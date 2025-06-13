package com.example.BookMyShowBackend.dto;

import lombok.Setter;

@Setter
public class SignUpResponseDto {

    private ResponseStatus responseStatus;
    private Long userId;
}
