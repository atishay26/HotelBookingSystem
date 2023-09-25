package com.hotelbooking.hotelbooking.repository;

import com.hotelbooking.hotelbooking.model.BookingDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface BookingRepository {
    Map<Integer,BookingDetails> bookings = new HashMap<>();

    Optional<BookingDetails> findById(int bookingId);

    BookingDetails save(BookingDetails bookingDetails);

    BookingDetails update(BookingDetails bookingDetails);
}
