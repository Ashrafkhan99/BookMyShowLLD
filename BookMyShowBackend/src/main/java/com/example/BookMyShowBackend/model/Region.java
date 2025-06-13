package com.example.BookMyShowBackend.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "regions")

public class Region extends BaseModel {

    private String name;

}
