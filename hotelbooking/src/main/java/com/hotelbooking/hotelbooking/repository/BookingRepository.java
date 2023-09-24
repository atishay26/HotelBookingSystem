package com.hotelbooking.hotelbooking.repository;

import com.hotelbooking.hotelbooking.model.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingDetails,Integer> {
}
