package com.hotelbooking.hotelbooking.util;

import com.hotelbooking.hotelbooking.exceptions.InvalidHotelException;
import com.hotelbooking.hotelbooking.model.BookingDetails;
import com.hotelbooking.hotelbooking.model.HotelDetails;
import com.hotelbooking.hotelbooking.model.Room;

import java.util.Collections;
import java.util.Optional;

public class BookingUtil {
    public static  void validateRoomCapacity(HotelDetails hotelDetails, BookingDetails bookingDetails)
            throws InvalidHotelException {
        Room selectedRoom = Optional.ofNullable(hotelDetails.getRooms())
                .orElse(Collections.emptyList())
                .stream()
                .filter(room -> bookingDetails.getRoomId() == room.getId())
                .findFirst()
                .orElseThrow(() -> new InvalidHotelException("RoomId/HotelId not found"));
        if (selectedRoom.getCapacity() < bookingDetails.getNumPax())
            throw new InvalidHotelException("Selected Room does have enough capacity");
    }
}
