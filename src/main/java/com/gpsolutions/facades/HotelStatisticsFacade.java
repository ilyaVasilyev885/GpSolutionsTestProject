package com.gpsolutions.facades;

import java.util.Map;

public interface HotelStatisticsFacade {
    Map<String, Long> groupHotelsByParam(String param);
}
