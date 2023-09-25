package com.hotelbooking.hotelbooking.model;

import lombok.Data;

@Data
public class BookingDetails {

    private int bookingId;
    private int hotelId;
    private int roomId;
    private int numPax;
}
