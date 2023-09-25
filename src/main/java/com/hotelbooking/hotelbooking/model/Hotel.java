package com.hotelbooking.hotelbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {

    private Integer hotelId;
    private Integer numRoomsAvailable;
    private String name;
    private HotelDetails hotelDetails;
    private City city;
}
