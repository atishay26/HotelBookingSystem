package com.hotelbooking.hotelbooking.controllers;

import com.hotelbooking.hotelbooking.exceptions.InvalidBookingException;
import com.hotelbooking.hotelbooking.exceptions.InvalidHotelException;
import com.hotelbooking.hotelbooking.model.BookingDetails;
import com.hotelbooking.hotelbooking.model.BookingResponse;
import com.hotelbooking.hotelbooking.service.BookingService;
import com.hotelbooking.hotelbooking.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/{bookingId}")
    public ResponseEntity getBookingDetails(@PathVariable int bookingId) {
        try {
            BookingDetails bookingDetails = bookingService.getBookingDetails(bookingId);
            return ResponseEntity.ok(bookingDetails);
        } catch (InvalidBookingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity createBooking(@RequestBody BookingDetails bookingDetails) {
        try {
            RequestValidator.validateBookingDetails(bookingDetails);
            int bookingId = bookingService.createBooking(bookingDetails, false);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new BookingResponse(bookingId));
        } catch (InvalidHotelException ihe) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ihe.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity updateBooking(@RequestBody BookingDetails bookingDetails) {
        try {
            RequestValidator.validateBookingDetails(bookingDetails);
            bookingService.updateBooking(bookingDetails);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new BookingResponse(bookingDetails.getBookingId()));
        } catch (InvalidHotelException ihe) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ihe.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
