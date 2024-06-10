package com.gpsolutions.dao.impl;

import com.gpsolutions.dao.HotelStatisticsDAO;
import com.gpsolutions.entities.HotelModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static com.gpsolutions.constants.GpSolutionsConstants.*;

@Component
public class HotelStatisticsDAOImpl implements HotelStatisticsDAO {
    private EntityManager entityManager;

    @Autowired
    public HotelStatisticsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Map<String, Long> groupHotelsByBrand() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
        Root<HotelModel> hotel = query.from(HotelModel.class);

        query.multiselect(hotel.get(HOTEL_BRAND_FIELD), criteriaBuilder.count(hotel));
        query.groupBy(hotel.get(HOTEL_BRAND_FIELD));

        return getResult(query);
    }

    @Override
    public Map<String, Long> groupHotelsByCity() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
        Root<HotelModel> hotel = query.from(HotelModel.class);

        query.multiselect(hotel.get(HOTEL_ADDRESS_FIELD).get(ADDRESS_CITY_FIELD), criteriaBuilder.count(hotel));
        query.groupBy(hotel.get(HOTEL_ADDRESS_FIELD).get(ADDRESS_CITY_FIELD));

        return getResult(query);
    }

    @Override
    public Map<String, Long> groupHotelsByCountry() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
        Root<HotelModel> hotel = query.from(HotelModel.class);

        query.multiselect(hotel.get(HOTEL_ADDRESS_FIELD).get(ADDRESS_COUNTRY_FIELD), criteriaBuilder.count(hotel));
        query.groupBy(hotel.get(HOTEL_ADDRESS_FIELD).get(ADDRESS_COUNTRY_FIELD));


        return getResult(query);
    }

    @Override
    public Map<String, Long> groupHotelsByAmenities() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
        Root<HotelModel> hotel = query.from(HotelModel.class);

        hotel.join(HOTEL_AMENITIES_FIELD);
        query.multiselect(hotel.get(HOTEL_AMENITIES_FIELD).get(AMENITIES_NAME_FIELD), criteriaBuilder.count(hotel));
        query.groupBy(hotel.get(HOTEL_AMENITIES_FIELD).get(AMENITIES_NAME_FIELD));

        return getResult(query);
    }


    private Map<String, Long> getResult(CriteriaQuery<Object[]> query) {
        List<Object[]> results = entityManager.createQuery(query).getResultList();
        Map<String, Long> result = new HashMap<>();
        for (Object[] pair : results) {
            result.put((String) pair[0],(Long) pair[1]);
        }
        return result;
    }
}
