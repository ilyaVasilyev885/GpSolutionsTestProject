package com.gpsolutions.services.impl;

import com.gpsolutions.dao.HotelStatisticsDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class HotelStatisticsServiceImplTest {

    @Autowired
    private HotelStatisticsServiceImpl hotelStatisticsService;

    @MockBean
    private HotelStatisticsDAO hotelStatisticsDAO;

    @Test
    public void injectedComponentIsNotNull() {
        assertNotNull(hotelStatisticsService);
    }

    @Test
    public void whenGroupHotelsByBrandCalled_thenDaoCalled() {
        hotelStatisticsService.groupHotelsByBrand();
        verify(hotelStatisticsDAO, times(1)).groupHotelsByBrand();
    }

    @Test
    public void whenGroupHotelsByCityCalled_thenDaoCalled() {
        hotelStatisticsService.groupHotelsByCity();
        verify(hotelStatisticsDAO, times(1)).groupHotelsByCity();
    }

    @Test
    public void whenGroupHotelsByCountryCalled_thenDaoCalled() {
        hotelStatisticsService.groupHotelsByCountry();
        verify(hotelStatisticsDAO, times(1)).groupHotelsByCountry();
    }

    @Test
    public void whenGroupHotelsByAmenitiesCalled_thenDaoCalled() {
        hotelStatisticsService.groupHotelsByAmenities();
        verify(hotelStatisticsDAO, times(1)).groupHotelsByAmenities();
    }
}