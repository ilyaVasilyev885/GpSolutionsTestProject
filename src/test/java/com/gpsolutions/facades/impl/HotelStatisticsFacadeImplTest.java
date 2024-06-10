package com.gpsolutions.facades.impl;

import com.gpsolutions.services.HotelStatisticsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class HotelStatisticsFacadeImplTest {

    @Autowired
    private HotelStatisticsFacadeImpl hotelStatisticsFacade;

    @MockBean
    private HotelStatisticsService hotelStatisticsService;

    @Test
    public void injectedComponentIsNotNull() {
        assertNotNull(hotelStatisticsFacade);
    }

    @Test
    public void whenGroupHotelsByCityParamCalled_thenServiceCalled() {
        hotelStatisticsFacade.groupHotelsByParam("city");
        verify(hotelStatisticsService, times(1)).groupHotelsByCity();
    }

    @Test
    public void whenGroupHotelsByAmenitiesParamCalled_thenServiceCalled() {
        hotelStatisticsFacade.groupHotelsByParam("amenities");
        verify(hotelStatisticsService, times(1)).groupHotelsByAmenities();
    }

    @Test
    public void whenGroupHotelsByCountryParamCalled_thenServiceCalled() {
        hotelStatisticsFacade.groupHotelsByParam("country");
        verify(hotelStatisticsService, times(1)).groupHotelsByCountry();
    }

    @Test
    public void whenGroupHotelsByCityBrandCalled_thenServiceCalled() {
        hotelStatisticsFacade.groupHotelsByParam("brand");
        verify(hotelStatisticsService, times(1)).groupHotelsByBrand();
    }
}