package com.hotelbooking.hotelbooking.validator;

import com.hotelbooking.hotelbooking.exceptions.InvalidHotelException;
import com.hotelbooking.hotelbooking.model.BookingDetails;
import com.hotelbooking.hotelbooking.model.Hotel;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class RequestValidator {
    public static boolean isValidRequestParameter(String param) {
        return StringUtils.hasLength(param);
    }

    public static boolean validateHotel(Hotel hotel) throws InvalidHotelException {

        validateNonNullField(hotel, "hotel");
        validateNonNullField(hotel.getNumRoomsAvailable(), "numRoomsAvailable");
        validateNonEmptyString(hotel.getCity(), "city");

        return true;
    }

    private static void validateNonEmptyString(String field, String name) throws InvalidHotelException {
        if (!StringUtils.hasLength(field)) {
            throw new InvalidHotelException(String.format("Field: %s cannot be empty.", name));
        }
    }

    private static void validateNonNullField(Object field, String name) throws InvalidHotelException {
        if (Objects.isNull(field)) {
            throw new InvalidHotelException(String.format("Field: %s cannot be null.", name));
        }
    }

    public static void validateBookingDetails(BookingDetails bookingDetails) throws InvalidHotelException {
        validateNonNullField(bookingDetails, "bookingDetails");
        validateNonNullField(bookingDetails.getHotelId(), "hotelId");
        validateNonNullField(bookingDetails.getRoomId(), "roomId");
    }
}
