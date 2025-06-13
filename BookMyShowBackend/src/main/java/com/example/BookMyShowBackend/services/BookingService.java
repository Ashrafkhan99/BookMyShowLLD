package com.example.BookMyShowBackend.services;

import com.example.BookMyShowBackend.Repo.BookingRepository;
import com.example.BookMyShowBackend.Repo.ShowRepository;
import com.example.BookMyShowBackend.Repo.ShowSeatRepository;
import com.example.BookMyShowBackend.Repo.UserRepository;
import com.example.BookMyShowBackend.exceptions.ShowNotFoundException;
import com.example.BookMyShowBackend.exceptions.ShowSeatNotFoundException;
import com.example.BookMyShowBackend.exceptions.UserNotFoundException;
import com.example.BookMyShowBackend.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private UserRepository userRepository;

    private ShowRepository showRepository;

    private ShowSeatRepository showSeatRepository;

    private PriceCalculatorService priceCalculatorService;

    private BookingRepository bookingRepository;

    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          PriceCalculatorService priceCalculatorService,
                          BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculatorService = priceCalculatorService;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookTicket(Long userId, Long showId, List<Long> showSeatIds) throws UserNotFoundException, ShowNotFoundException, ShowSeatNotFoundException {

        /*
            * Get the user by userId and check if it exists.
            * If not, throw UserNotFoundException.
            * Get the show by showId and check if it exists.
            * If not, throw ShowNotFoundException.
            * Get the show seats by showSeatIds and check if they exist.
            * check the status of all showSeat object is AVAILABLE.
            * If not, throw ShowSeatNotFoundException.
            --------------------- First Lock --------------------------------------------------------------
            * if Yes, then update those seats status to BLOCK
            (you have to provide another lock while updating the status for the above step)
            * save the status in the DB.
            ------------------------ Release Lock --------------------------------------------------------------
            *
            *  Create a Booking object
            * return the Booking object.
         */
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with ID: " + userId.toString());
        }
        User user = userOptional.get();

        Optional<Show> showOptional = showRepository.findById(showId);
        if (showOptional.isEmpty()) {
            throw new ShowNotFoundException("Show not found with ID: " + showId.toString());
        }
        Show show = showOptional.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        for (ShowSeat showSeat : showSeats) {
            if (showSeat.getShowSeatStatus() != ShowSeatStatus.AVAILABLE) {
                throw new ShowSeatNotFoundException("Show seat not available to book ticket whose ID: " + showSeat.getId());
            }
        }

        List<ShowSeat> savedShowSeats = new ArrayList<>();
        for (ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShowSeats(savedShowSeats);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setAmount(priceCalculatorService.CalculatePrice(showSeats, show));
        return bookingRepository.save(booking);
    }
}
