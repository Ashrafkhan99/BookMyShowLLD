package com.example.BookMyShowBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Show_Seat_Type")
public class ShowSeatType extends BaseModel {

    @ManyToOne
    private Show show;

    @ManyToOne
    private SeatType seatType;
    private int price;;
}
