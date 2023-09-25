package com.hotelbooking.hotelbooking.repository;

import com.hotelbooking.hotelbooking.model.Hotel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.hotelbooking.hotelbooking.util.DataUtil.getCache;
import static com.hotelbooking.hotelbooking.util.DataUtil.getHotelMap;

public interface HotelRepository  {
    Map<Integer, Hotel> hotelMap = getHotelMap();
    Map<String, List<Hotel>> cache = getCache();

    List<Hotel> findByCityName(String city);

    Optional<Hotel> findById(int hotelId) ;

    void save(Hotel hotel);
}
