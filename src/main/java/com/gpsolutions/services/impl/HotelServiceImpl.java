package com.gpsolutions.services.impl;

import com.gpsolutions.entities.AmenityModel;
import com.gpsolutions.entities.HotelModel;
import com.gpsolutions.enums.SearchParams;
import com.gpsolutions.exceptions.HotelNotFoundException;
import com.gpsolutions.repositories.AmenityRepository;
import com.gpsolutions.repositories.HotelRepository;
import com.gpsolutions.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;
    private AmenityRepository amenityRepository;
    private Map<SearchParams, Function<String[], Specification<HotelModel>>> specs;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository,
                            Map<SearchParams, Function<String[], Specification<HotelModel>>> specs,
                            AmenityRepository amenityRepository) {
        this.hotelRepository = hotelRepository;
        this.specs = specs;
        this.amenityRepository = amenityRepository;
    }

    @Override
    public HotelModel findById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    @Override
    public List<HotelModel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    @Transactional
    public HotelModel save(HotelModel hotelModel) {
        return hotelRepository.save(hotelModel);
    }

    @Override
    @Transactional
    public void addAmenities(Long id, List<AmenityModel> amenities) {
        HotelModel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));
        List<AmenityModel> savedAmenities = amenities.stream()
                .map(amenity -> {
                            AmenityModel byName = amenityRepository.findByNameIgnoreCase(amenity.getName());
                            if (byName != null) {
                                return byName;
                            }
                            return amenityRepository.save(amenity);
                        }
                )
                .collect(Collectors.toList());
        hotel.setAmenities(savedAmenities);
        hotelRepository.save(hotel);
    }

    @Override
    public List<HotelModel> findByParams(Map<String, String[]> params) {
        Map<String, String[]> caseInsensitiveParams = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        caseInsensitiveParams.putAll(params);
        List<HotelModel> hotels = hotelRepository.findAll(getQuery(caseInsensitiveParams));
        List<HotelModel> hotelsWithAmenityParam = new ArrayList<>();
        String[] amenityParamsArray = caseInsensitiveParams.get(SearchParams.AMENITIES.toString());
        if (amenityParamsArray != null) {
            List<String> amenityParams = Arrays.asList(amenityParamsArray);
            for (HotelModel hotel : hotels) {
                List<String> amenities = convertAmenityToString(hotel.getAmenities());
                if (amenities.containsAll(amenityParams)) {
                    hotelsWithAmenityParam.add(hotel);
                }
            }
            return hotelsWithAmenityParam;
        } else {
            return hotels;
        }
    }

    private List<String> convertAmenityToString(List<AmenityModel> listAmenities) {
        return listAmenities.stream()
                .map(AmenityModel::getName)
                .toList();
    }

    private Specification<HotelModel> getQuery(Map<String, String[]> params) {
        Specification<HotelModel> result = Specification.where(null);
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(SearchParams.AMENITIES.toString())) {
                continue;
            }
            Specification<HotelModel> hotelModelSpecification = specs.get(SearchParams.valueOf(entry.getKey().toUpperCase())).apply(entry.getValue());
            result = result.and(hotelModelSpecification);

        }
        return result;
    }

}
