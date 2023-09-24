package com.hotelbooking.hotelbooking.service;

import com.hotelbooking.hotelbooking.exceptions.InvalidHotelException;
import com.hotelbooking.hotelbooking.model.Hotel;
import com.hotelbooking.hotelbooking.model.HotelDetails;
import com.hotelbooking.hotelbooking.model.RoomDetails;
import com.hotelbooking.hotelbooking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels(String city) {
        return hotelRepository.findByCity(city);
    }

    public HotelDetails getHotelDetails(int hotelId) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
        if (hotelOptional.isPresent()) {
            Hotel hotel = hotelOptional.get();
            return hotel.getHotelDetails();
        }
        throw new IllegalArgumentException(String.format("Could not find details for hotelId: %s", hotelId));
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public void bookHotel(int hotelId, int roomId) throws InvalidHotelException {
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
        if (hotelOptional.isPresent()) {
            Hotel hotel = hotelOptional.get();
            int currRoomAvailability = hotel.getNumRoomsAvailable();
            RoomDetails roomBooked = Optional.ofNullable(hotel.getHotelDetails())
                    .map(HotelDetails::getRooms)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(roomDetails -> roomId == roomDetails.getRoomId())
                    .findFirst()
                    .orElseThrow(() -> new InvalidHotelException("Could not find hotel/room"));

            roomBooked.setAvailable(false);
            hotel.setNumRoomsAvailable(currRoomAvailability-1);
            hotelRepository.save(hotel);
        }
    }

    public void cancelHotelBooking(int hotelId, int roomId) throws InvalidHotelException {
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
        if (hotelOptional.isPresent()) {
            Hotel hotel = hotelOptional.get();
            int currRoomAvailability = hotel.getNumRoomsAvailable();
            RoomDetails roomBooked = Optional.ofNullable(hotel.getHotelDetails())
                    .map(HotelDetails::getRooms)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(roomDetails -> roomId == roomDetails.getRoomId())
                    .findFirst()
                    .orElseThrow(() -> new InvalidHotelException("Could not find hotel/room"));

            roomBooked.setAvailable(true);
            hotel.setNumRoomsAvailable(currRoomAvailability+1);
            hotelRepository.save(hotel);
        }
    }
}
