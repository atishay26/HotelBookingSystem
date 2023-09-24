package com.hotelbooking.hotelbooking.repository;

import com.hotelbooking.hotelbooking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    List<Hotel> findByCity(String city);
}
