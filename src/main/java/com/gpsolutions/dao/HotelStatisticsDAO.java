package com.gpsolutions.dao;

import java.util.Map;

public interface HotelStatisticsDAO {
    Map<String, Long> groupHotelsByBrand();
    Map<String, Long> groupHotelsByCity();
    Map<String, Long> groupHotelsByCountry();
    Map<String, Long> groupHotelsByAmenities();
}
