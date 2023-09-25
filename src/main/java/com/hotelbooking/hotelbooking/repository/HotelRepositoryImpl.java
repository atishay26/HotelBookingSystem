package com.hotelbooking.hotelbooking.repository;

import com.hotelbooking.hotelbooking.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class HotelRepositoryImpl implements HotelRepository{

    @Override
    public List<Hotel> findByCityName(String city) {
        if(cache.containsKey(city))
            return cache.get(city);

        System.out.println("Cache Miss");
        List<Hotel> hotels = hotelMap.keySet()
                .stream()
                .map(hotelMap::get)
                .filter(hotel -> city.equalsIgnoreCase(hotel.getCity().getName()))
                .collect(Collectors.toList());
        cache.put(city,hotels);
        return hotels;
    }

    @Override
    public Optional<Hotel> findById(int hotelId) {
        return Optional.ofNullable(hotelMap.get(hotelId));
    }

    @Override
    public void save(Hotel hotel) {
        hotelMap.put(hotel.getHotelId(), hotel);
    }
}
