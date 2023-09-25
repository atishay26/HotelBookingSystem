package com.hotelbooking.hotelbooking.repository;

import com.hotelbooking.hotelbooking.model.BookingDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BookingRepositoryImpl implements BookingRepository {

    @Override
    public Optional<BookingDetails> findById(int bookingId){
        return Optional.ofNullable(bookings.get(bookingId));
    }

    @Override
    public BookingDetails save(BookingDetails bookingDetails) {
        int bookingId = bookings.size()+1;
        bookings.put(bookingId, bookingDetails);
        bookingDetails.setBookingId(bookingId);
        return bookingDetails;
    }

    @Override
    public BookingDetails update(BookingDetails bookingDetails) {
        bookings.put(bookingDetails.getBookingId(), bookingDetails);
        return bookingDetails;
    }
}
