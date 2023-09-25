package com.hotelbooking.hotelbooking.util;

import com.hotelbooking.hotelbooking.enums.RoomType;
import com.hotelbooking.hotelbooking.model.City;
import com.hotelbooking.hotelbooking.model.Hotel;
import com.hotelbooking.hotelbooking.model.HotelDetails;
import com.hotelbooking.hotelbooking.model.Room;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUtil {

    static Hotel taj;
    static Hotel lalit;
    static Hotel itc;

    public static Map<String, List<Hotel>> getCache() {
        Map<String,List<Hotel>> cache = new HashMap<>();
        cache.put("Delhi", Arrays.asList(taj,lalit));
        return cache;
    }

    public static Map<Integer, Hotel> getHotelMap() {
        City delhi = new City(1,"Delhi");
        City goa = new City(2, "Goa");

        taj = getTaj(delhi);
        lalit = getLalit(delhi);
        itc = getITC(goa);

        Map<Integer,Hotel> hotelMap = new HashMap<>();
        hotelMap.put(1,taj);
        hotelMap.put(2,lalit);
        hotelMap.put(3,itc);
        return hotelMap;
    }

    static Hotel getTaj(City delhi) {
        return Hotel.builder()
                .name("Taj")
                .hotelId(1)
                .city(delhi)
                .numRoomsAvailable(2)
                .hotelDetails(getTajHotelDetails())
                .build();
    }

    static HotelDetails getTajHotelDetails() {
        return HotelDetails.builder()
                .hotelRating(5)
                .hotelId(1)
                .rooms(getRoomsForTaj())
                .build();
    }

    static List<Room> getRoomsForTaj() {
        Room r1 = Room.builder()
                .id(1)
                .hotelId(1)
                .capacity(5)
                .isRoomAvailable(true)
                .roomType(RoomType.DELUXE)
                .build();
        Room r2 = Room.builder()
                .id(2)
                .hotelId(1)
                .capacity(2)
                .isRoomAvailable(true)
                .roomType(RoomType.DOUBLE)
                .build();
        Room r3 = Room.builder()
                .id(3)
                .hotelId(1)
                .capacity(1)
                .isRoomAvailable(false)
                .roomType(RoomType.SINGLE)
                .build();
        return Arrays.asList(r1,r2,r3);
    }

    static Hotel getLalit(City delhi) {
        return Hotel.builder()
                .name("Lalit")
                .hotelId(2)
                .city(delhi)
                .numRoomsAvailable(1)
                .hotelDetails(getLalitHotelDetails())
                .build();
    }

    static HotelDetails getLalitHotelDetails() {
        return HotelDetails.builder()
                .hotelRating(4)
                .hotelId(2)
                .rooms(getRoomsForLalit())
                .build();
    }

    static List<Room> getRoomsForLalit() {
        Room r1 = Room.builder()
                .id(1)
                .hotelId(2)
                .capacity(5)
                .isRoomAvailable(true)
                .roomType(RoomType.DELUXE)
                .build();
        Room r2 = Room.builder()
                .id(2)
                .hotelId(2)
                .capacity(2)
                .isRoomAvailable(false)
                .roomType(RoomType.DOUBLE)
                .build();

        return Arrays.asList(r1,r2);
    }

    static Hotel getITC(City goa) {
        return Hotel.builder()
                .name("Lalit")
                .hotelId(3)
                .city(goa)
                .numRoomsAvailable(3)
                .hotelDetails(getITCHotelDetails())
                .build();
    }

    static HotelDetails getITCHotelDetails() {
        return HotelDetails.builder()
                .hotelRating(4)
                .hotelId(3)
                .rooms(getRoomsForITC())
                .build();
    }

    static List<Room> getRoomsForITC() {
        Room r1 = Room.builder()
                .id(1)
                .hotelId(3)
                .capacity(5)
                .isRoomAvailable(true)
                .roomType(RoomType.DELUXE)
                .build();
        Room r2 = Room.builder()
                .id(2)
                .hotelId(2)
                .capacity(2)
                .isRoomAvailable(false)
                .roomType(RoomType.DOUBLE)
                .build();
        Room r3 = Room.builder()
                .id(3)
                .hotelId(1)
                .capacity(1)
                .isRoomAvailable(true)
                .roomType(RoomType.SINGLE)
                .build();

        return Arrays.asList(r1,r2,r3);
    }
}
