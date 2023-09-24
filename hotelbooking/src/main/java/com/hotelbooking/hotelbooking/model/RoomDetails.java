package com.hotelbooking.hotelbooking.model;

import com.hotelbooking.hotelbooking.enums.RoomType;
import lombok.Data;

@Data
public class RoomDetails {

    private int roomId;
    private RoomType roomType;
    private int numCustomers;
    private boolean isAvailable;
}
