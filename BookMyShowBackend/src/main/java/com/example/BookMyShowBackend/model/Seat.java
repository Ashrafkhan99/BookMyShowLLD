package com.example.BookMyShowBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Seats")
public class Seat extends BaseModel {

    private String seatNumber;

    @ManyToOne
    private SeatType seatType;
    private int rowVal;
    private int colVal;

    @ManyToOne
    private Screen screen;
}
