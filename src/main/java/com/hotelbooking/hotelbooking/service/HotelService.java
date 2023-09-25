package com.hotelbooking.hotelbooking.service;

import com.hotelbooking.hotelbooking.exceptions.InvalidHotelException;
import com.hotelbooking.hotelbooking.model.Hotel;
import com.hotelbooking.hotelbooking.model.HotelDetails;
import com.hotelbooking.hotelbooking.model.Room;
import com.hotelbooking.hotelbooking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels(String city) {
        return hotelRepository.findByCityName(city);
    }

    public HotelDetails getHotelDetails(int hotelId) {
        return hotelRepository.findById(hotelId)
                .map(Hotel::getHotelDetails)
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("Could not find details for hotelId: %s", hotelId)));

    }

    public void bookHotel(int hotelId, int roomId) throws InvalidHotelException {
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
        if (hotelOptional.isPresent()) {
            Hotel hotel = hotelOptional.get();
            int currRoomAvailability = hotel.getNumRoomsAvailable();
            Room roomBooked = Optional.ofNullable(hotel.getHotelDetails())
                    .map(HotelDetails::getRooms)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(room -> roomId == room.getId())
                    .findFirst()
                    .orElseThrow(() -> new InvalidHotelException("Could not find hotel/room"));

            roomBooked.setRoomAvailable(false);
            hotel.setNumRoomsAvailable(currRoomAvailability-1);
            hotelRepository.save(hotel);
        }
    }

    public void cancelHotelBooking(int hotelId, int roomId) throws InvalidHotelException {
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
        if (hotelOptional.isPresent()) {
            Hotel hotel = hotelOptional.get();
            int currRoomAvailability = hotel.getNumRoomsAvailable();
            Room roomBooked = Optional.ofNullable(hotel.getHotelDetails())
                    .map(HotelDetails::getRooms)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(room -> roomId == room.getId())
                    .findFirst()
                    .orElseThrow(() -> new InvalidHotelException("Could not find hotel/room"));

            roomBooked.setRoomAvailable(true);
            hotel.setNumRoomsAvailable(currRoomAvailability+1);
            hotelRepository.save(hotel);
        }
    }
}
