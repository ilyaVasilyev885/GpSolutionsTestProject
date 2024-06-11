package com.gpsolutions.utils;

import com.gpsolutions.entities.HotelModel;
import org.springframework.data.jpa.domain.Specification;

import static com.gpsolutions.constants.GpSolutionsConstants.*;

public class HotelSpecifications {

    public static Specification<HotelModel> hasName(String[] name) {
        return (hotel, query, builder) -> {
            return builder.equal(builder.lower(hotel.get(HOTEL_NAME_FIELD)), name[0].toLowerCase());
        };
    }

    public static Specification<HotelModel> hasCity(String[] city) {
        return (hotel, query, builder) -> {
            return builder.equal(builder.lower(hotel.get(HOTEL_ADDRESS_FIELD).get(ADDRESS_CITY_FIELD)), city[0].toLowerCase());
        };
    }

    public static Specification<HotelModel> hasBrand(String[] brand) {
        return (hotel, query, builder) -> {
            return builder.equal(builder.lower(hotel.get(HOTEL_BRAND_FIELD)), brand[0].toLowerCase());
        };
    }

    public static Specification<HotelModel> hasCountry(String[] country) {
        return (hotel, query, builder) -> {
            return builder.equal(builder.lower(hotel.get(HOTEL_ADDRESS_FIELD).get(ADDRESS_COUNTRY_FIELD)), country[0].toLowerCase());
        };
    }
}
