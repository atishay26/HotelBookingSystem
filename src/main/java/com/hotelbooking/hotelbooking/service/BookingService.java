package com.hotelbooking.hotelbooking.service;

import com.hotelbooking.hotelbooking.exceptions.InvalidBookingException;
import com.hotelbooking.hotelbooking.exceptions.InvalidHotelException;
import com.hotelbooking.hotelbooking.model.BookingDetails;
import com.hotelbooking.hotelbooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.hotelbooking.hotelbooking.util.BookingUtil.validateRoomCapacity;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelService hotelService;

    public int createBooking(BookingDetails bookingDetails, boolean isUpdate) throws InvalidHotelException {
        validateRoomCapacity(hotelService.getHotelDetails(bookingDetails.getHotelId()), bookingDetails);
        hotelService.bookHotel(bookingDetails.getHotelId(), bookingDetails.getRoomId());
        BookingDetails booking;
        if (isUpdate) {
            booking = bookingRepository.update(bookingDetails);
        } else {
            booking = bookingRepository.save(bookingDetails);
        }
        return booking.getBookingId();
    }

    public int updateBooking( BookingDetails bookingDetails)
            throws InvalidBookingException, InvalidHotelException {
        getBookingDetails(bookingDetails.getBookingId()); //to validate if booking exists
        hotelService.cancelHotelBooking(bookingDetails.getHotelId(), bookingDetails.getRoomId());
        return createBooking(bookingDetails, true);
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
