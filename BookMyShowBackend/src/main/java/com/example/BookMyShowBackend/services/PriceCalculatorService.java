package com.example.BookMyShowBackend.services;

import com.example.BookMyShowBackend.Repo.ShowSeatTypeRepository;
import com.example.BookMyShowBackend.model.Show;
import com.example.BookMyShowBackend.model.ShowSeat;
import com.example.BookMyShowBackend.model.ShowSeatType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {

    private ShowSeatTypeRepository showSeatTypeRepository;

    PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int CalculatePrice(List<ShowSeat> showSeats, Show show) {
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        int totalPrice = 0;
        for (ShowSeat showSeat : showSeats) {
            for (ShowSeatType showSeatType : showSeatTypes) {
                if (showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    totalPrice += showSeatType.getPrice();
                    break;
                }
            }
        }

        return totalPrice;
    }
}
