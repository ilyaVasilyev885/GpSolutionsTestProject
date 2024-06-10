package com.gpsolutions.services.impl;

import com.gpsolutions.entities.AmenityModel;
import com.gpsolutions.entities.HotelModel;
import com.gpsolutions.enums.SearchParams;
import com.gpsolutions.exceptions.HotelNotFoundException;
import com.gpsolutions.repositories.AmenityRepository;
import com.gpsolutions.repositories.HotelRepository;
import com.gpsolutions.utils.HotelSpecifications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class HotelServiceImplTest {
    private static final String AMENITY_NAME_1 = "amenity1";
    private static final String AMENITY_NAME_2 = "amenity2";

    @Autowired
    private HotelServiceImpl hotelService;

    @MockBean
    private HotelRepository hotelRepository;
    @MockBean
    private AmenityRepository amenityRepository;
    @MockBean
    private Map<SearchParams, Function<String[], Specification<HotelModel>>> specs;

    private HotelModel hotelModel;
    private AmenityModel amenityModel1;
    private AmenityModel amenityModel2;
    private List<AmenityModel> amenities;

    @BeforeEach
    public void setUp() {
        hotelModel = new HotelModel();
        amenityModel1 = new AmenityModel(1L, AMENITY_NAME_1);
        amenityModel2 = new AmenityModel(2L, AMENITY_NAME_2);
        amenities = Arrays.asList(amenityModel1, amenityModel2);

    }

    @Test
    public void injectedComponentIsNotNull() {
        assertNotNull(hotelService);
    }

    @Test
    public void whenFindByIdCalled_thenRepositoryCalled() {
        hotelService.findById(1L);
        verify(hotelRepository, times(1)).findById(1L);
    }

    @Test
    public void whenFindAllCalled_thenRepositoryCalled() {
        hotelService.findAll();
        verify(hotelRepository, times(1)).findAll();
    }

    @Test
    public void whenSaveCalled_thenRepositoryCalled() {
        hotelService.save(hotelModel);
        verify(hotelRepository, times(1)).save(hotelModel);
    }

    @Test
    public void whenAddAmenitiesCalled_thenRepositoryCalled() {

        when(hotelRepository.findById(1L)).thenReturn(Optional.ofNullable(hotelModel));
        when(amenityRepository.findByNameIgnoreCase(AMENITY_NAME_1)).thenReturn(amenityModel1);
        when(amenityRepository.findByNameIgnoreCase(AMENITY_NAME_2)).thenReturn(null);
        when(amenityRepository.save(amenityModel2)).thenReturn(amenityModel2);

        hotelService.addAmenities(1L, amenities);

        assertEquals(hotelModel.getAmenities(), amenities);
        verify(hotelRepository, times(1)).save(hotelModel);
    }

    @Test
    public void shouldThrowExceptionWhenAddAmenitiesHotelNotPresent() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(HotelNotFoundException.class, () -> hotelService.addAmenities(1L, amenities));
    }

    @Test
    public void whenFindByParamsCalled_thenRepositoryWithSpecsCalled() {
        when(specs.get(SearchParams.CITY)).thenReturn(HotelSpecifications::hasCity);
        when(specs.get(SearchParams.BRAND)).thenReturn(HotelSpecifications::hasBrand);
        when(specs.get(SearchParams.COUNTRY)).thenReturn(HotelSpecifications::hasCountry);
        when(specs.get(SearchParams.NAME)).thenReturn(HotelSpecifications::hasName);

        Map<String, String[]> params = new HashMap<>();
        params.put("city", new String[]{"testCity"});
        params.put("brand", new String[]{"testBrand"});
        params.put("name", new String[]{"testName"});
        params.put("country", new String[]{"testCountry"});
        params.put("amenities", new String[]{AMENITY_NAME_1, AMENITY_NAME_2});
        hotelModel.setAmenities(amenities);
        List<HotelModel> expected = Collections.singletonList(hotelModel);
        when(hotelRepository.findAll(any(Specification.class))).thenReturn(expected);

        List<HotelModel> actual = hotelService.findByParams(params);

        assertEquals(expected, actual);
        verify(hotelRepository, times(1)).findAll(any(Specification.class));
    }
}