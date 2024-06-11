package com.gpsolutions.services;

import java.util.Map;

public interface HotelStatisticsService {
    Map<String, Long> groupHotelsByBrand();
    Map<String, Long> groupHotelsByCity();
    Map<String, Long> groupHotelsByCountry();
    Map<String, Long> groupHotelsByAmenities();
}
