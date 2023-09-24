package com.hotelbooking.hotelbooking.model;

import lombok.Data;
import java.util.List;

@Data
public class HotelDetails extends Hotel {

    private List<RoomDetails> rooms;
    private float hotelRating;
}
