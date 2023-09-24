package com.hotelbooking.hotelbooking.service;

import com.hotelbooking.hotelbooking.exceptions.InvalidBookingException;
import com.hotelbooking.hotelbooking.exceptions.InvalidHotelException;
import com.hotelbooking.hotelbooking.model.BookingDetails;
import com.hotelbooking.hotelbooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelService hotelService;

    public int createBooking(BookingDetails bookingDetails) throws InvalidHotelException {
        hotelService.bookHotel(bookingDetails.getHotelId(), bookingDetails.getRoomId());
        BookingDetails booking = bookingRepository.save(bookingDetails);
        return booking.getId();
    }

    public int updateBooking(int bookingId, BookingDetails bookingDetails)
            throws InvalidBookingException, InvalidHotelException {
        BookingDetails currDetails = getBookingDetails(bookingId);
        hotelService.cancelHotelBooking(bookingDetails.getHotelId(), bookingDetails.getRoomId());
        return createBooking(bookingDetails);
    }

    public BookingDetails getBookingDetails(int bookingId) throws InvalidBookingException {
        Optional<BookingDetails> bookingDetailsOptional = bookingRepository.findById(bookingId);
        if (bookingDetailsOptional.isPresent()) {
            return bookingDetailsOptional.get();
        } else {
            throw new InvalidBookingException(String.format("Could not find booking with id: %s", bookingId));
        }
    }
}
