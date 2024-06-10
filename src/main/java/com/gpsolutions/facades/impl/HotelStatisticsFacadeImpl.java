package com.gpsolutions.facades.impl;

import com.gpsolutions.annotations.AfterInit;
import com.gpsolutions.enums.SearchParams;
import com.gpsolutions.facades.HotelStatisticsFacade;
import com.gpsolutions.services.HotelStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class HotelStatisticsFacadeImpl implements HotelStatisticsFacade {
    private HotelStatisticsService hotelStatisticsService;
    private Map<SearchParams, Supplier<Map<String, Long>>> searchParamsMap;

    @Autowired
    public HotelStatisticsFacadeImpl(HotelStatisticsService hotelStatisticsService) {
        this.hotelStatisticsService = hotelStatisticsService;
    }

    @AfterInit
    public void init() {
        searchParamsMap = new HashMap<>();
        searchParamsMap.put(SearchParams.CITY, hotelStatisticsService::groupHotelsByCity);
        searchParamsMap.put(SearchParams.AMENITIES, hotelStatisticsService::groupHotelsByAmenities);
        searchParamsMap.put(SearchParams.COUNTRY, hotelStatisticsService::groupHotelsByCountry);
        searchParamsMap.put(SearchParams.BRAND, hotelStatisticsService::groupHotelsByBrand);
    }

    @Override
    public Map<String, Long> groupHotelsByParam(String param) {
        return searchParamsMap.get(SearchParams.valueOf(param.toUpperCase())).get();
    }


}
