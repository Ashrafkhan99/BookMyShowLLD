package com.example.BookMyShowBackend.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Seat_Types")
public class SeatType extends BaseModel {

    private String name;
}
