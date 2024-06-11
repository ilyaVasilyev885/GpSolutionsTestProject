package com.gpsolutions.config;

import com.gpsolutions.entities.HotelModel;
import com.gpsolutions.enums.SearchParams;
import com.gpsolutions.utils.HotelSpecifications;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration
public class Config {

    @Bean
    public Map<SearchParams, Function<String[], Specification<HotelModel>>> getSearchSpecs() {
        Map<SearchParams, Function<String[], Specification<HotelModel>>> result = new HashMap<>();
        result.put(SearchParams.CITY, HotelSpecifications::hasCity);
        result.put(SearchParams.BRAND, HotelSpecifications::hasBrand);
        result.put(SearchParams.NAME, HotelSpecifications::hasName);
        result.put(SearchParams.COUNTRY, HotelSpecifications::hasCountry);
        return result;
    }

}
