package com.hotelbooking.hotelbooking.model;

import com.hotelbooking.hotelbooking.enums.RoomType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    private int id;
    private int hotelId;
    private boolean isRoomAvailable;
    private RoomType roomType;
    private int capacity;

}
