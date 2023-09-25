package com.hotelbooking.hotelbooking.controllers;

import com.hotelbooking.hotelbooking.exceptions.InvalidRequestParameterException;
import com.hotelbooking.hotelbooking.model.Hotel;
import com.hotelbooking.hotelbooking.model.HotelDetails;
import com.hotelbooking.hotelbooking.service.HotelService;
import com.hotelbooking.hotelbooking.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity getAllHotels(@RequestParam String city) {
        try {
            RequestValidator.isValidRequestParameter(city);
            List<Hotel> availableHotels = hotelService.getAllHotels(city);
            return ResponseEntity.ok()
                    .body(availableHotels);
        } catch(InvalidRequestParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid city");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity getHotelDetails(@PathVariable int hotelId) {
        try {
            HotelDetails hotelDetails = hotelService.getHotelDetails(hotelId);
            return ResponseEntity.ok(hotelDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
