package com.gpsolutions.services.impl;

import com.gpsolutions.dao.HotelStatisticsDAO;
import com.gpsolutions.services.HotelStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(readOnly = true)
public class HotelStatisticsServiceImpl implements HotelStatisticsService {

    private HotelStatisticsDAO hotelStatisticsDAO;

    @Autowired
    public HotelStatisticsServiceImpl(HotelStatisticsDAO hotelStatisticsDAO) {
        this.hotelStatisticsDAO = hotelStatisticsDAO;
    }

    @Override
    public Map<String, Long> groupHotelsByBrand() {
        return hotelStatisticsDAO.groupHotelsByBrand();
    }

    @Override
    public Map<String, Long> groupHotelsByCity() {
        return hotelStatisticsDAO.groupHotelsByCity();
    }

    @Override
    public Map<String, Long> groupHotelsByCountry() {
        return hotelStatisticsDAO.groupHotelsByCountry();
    }

    @Override
    public Map<String, Long> groupHotelsByAmenities() {
        return hotelStatisticsDAO.groupHotelsByAmenities();
    }
}
