package com.gpsolutions.controllers;

import com.gpsolutions.annotations.ValidHistogramParams;
import com.gpsolutions.facades.HotelStatisticsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.gpsolutions.constants.GpSolutionsConstants.HISTOGRAM_API;
import static com.gpsolutions.constants.GpSolutionsConstants.PROPERTY_VIEW_API;

@RestController
@RequestMapping(PROPERTY_VIEW_API + HISTOGRAM_API)
public class HotelStatisticController {

    private HotelStatisticsFacade hotelStatisticsFacade;

    @Autowired
    public HotelStatisticController(HotelStatisticsFacade hotelStatisticsFacade) {
        this.hotelStatisticsFacade = hotelStatisticsFacade;
    }

    @GetMapping("/{param}")
    public Map<String, Long> getHistogramByParam(@PathVariable @ValidHistogramParams String param) {
        return hotelStatisticsFacade.groupHotelsByParam(param);
    }
}
