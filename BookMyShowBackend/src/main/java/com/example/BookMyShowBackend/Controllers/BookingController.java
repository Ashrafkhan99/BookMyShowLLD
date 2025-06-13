package com.example.BookMyShowBackend.Controllers;

import com.example.BookMyShowBackend.dto.BookMovieRequestDto;
import com.example.BookMyShowBackend.dto.BookMovieResponseDto;
import com.example.BookMyShowBackend.dto.ResponseStatus;
import com.example.BookMyShowBackend.exceptions.ShowNotFoundException;
import com.example.BookMyShowBackend.exceptions.ShowSeatNotFoundException;
import com.example.BookMyShowBackend.exceptions.UserNotFoundException;
import com.example.BookMyShowBackend.model.Booking;
import com.example.BookMyShowBackend.model.BookingStatus;
import com.example.BookMyShowBackend.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private BookingService bookingService;

    BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookMovieResponseDto bookTicket(BookMovieRequestDto bookMovieRequestDto) throws UserNotFoundException, ShowNotFoundException, ShowSeatNotFoundException {
        BookMovieResponseDto bookMovieResponseDto = new BookMovieResponseDto();

        Booking booking = bookingService.bookTicket(bookMovieRequestDto.getUserId(),
                bookMovieRequestDto.getShowId(),
                bookMovieRequestDto.getSeatNumbers());

        bookMovieResponseDto.setBookingId(booking.getId());
        bookMovieResponseDto.setResponseStatus(ResponseStatus.SUCCESS);

        return bookMovieResponseDto;
    }
}
