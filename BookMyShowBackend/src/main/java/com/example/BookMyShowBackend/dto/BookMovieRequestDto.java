package com.example.BookMyShowBackend.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BookMovieRequestDto {

    private Long showId;
    private Long userId;
    private List<Long> seatNumbers;
}
