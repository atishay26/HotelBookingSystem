package com.hotelbooking.hotelbooking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Hotel {

    @Id
    private Integer hotelId;
    private Integer numRoomsAvailable;
    private String city;

    @OneToMany
    @JsonBackReference
    private HotelDetails hotelDetails;

}
