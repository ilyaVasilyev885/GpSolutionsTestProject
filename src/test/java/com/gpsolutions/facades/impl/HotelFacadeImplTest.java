package com.gpsolutions.facades.impl;

import com.gpsolutions.dto.HotelBasicResponseDTO;
import com.gpsolutions.dto.HotelDTO;
import com.gpsolutions.entities.AmenityModel;
import com.gpsolutions.entities.HotelModel;
import com.gpsolutions.mappers.AmenityMapper;
import com.gpsolutions.mappers.HotelBasicResponseMapper;
import com.gpsolutions.mappers.HotelMapper;
import com.gpsolutions.services.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class HotelFacadeImplTest {

    @Autowired
    private HotelFacadeImpl hotelFacade;

    @MockBean
    private HotelMapper hotelMapper;
    @MockBean
    private HotelService hotelService;
    @MockBean
    private HotelBasicResponseMapper hotelBasicResponseMapper;
    @MockBean
    private AmenityMapper amenityMapper;

    private HotelModel hotelModel1;
    private HotelModel hotelModel2;

    private HotelDTO hotelDTO1;
    private HotelDTO hotelDTO2;

    private HotelBasicResponseDTO hotelBasicResponseDTO1;
    private HotelBasicResponseDTO hotelBasicResponseDTO2;

    @BeforeEach
    public void setUp() {
        hotelModel1 = new HotelModel();
        hotelModel2 = new HotelModel();

        hotelDTO1 = new HotelDTO();
        hotelDTO2 = new HotelDTO();

        hotelBasicResponseDTO1 = new HotelBasicResponseDTO();
        hotelBasicResponseDTO2 = new HotelBasicResponseDTO();
    }

    @Test
    public void injectedComponentIsNotNull() {
        assertNotNull(hotelFacade);
    }

    @Test
    public void whenFindAllCalled_thenListBasicResponseReturned() {
        List<HotelModel> models = Arrays.asList(hotelModel1, hotelModel2);
        when(hotelService.findAll()).thenReturn(models);
        when(hotelMapper.toDto(hotelModel1)).thenReturn(hotelDTO1);
        when(hotelMapper.toDto(hotelModel2)).thenReturn(hotelDTO2);
        when(hotelBasicResponseMapper.toResponseDTO(hotelDTO1)).thenReturn(hotelBasicResponseDTO1);
        when(hotelBasicResponseMapper.toResponseDTO(hotelDTO2)).thenReturn(hotelBasicResponseDTO2);

        List<HotelBasicResponseDTO> actual = hotelFacade.findAll();

        assertNotNull(actual);
        assertEquals(2, actual.size());
        verify(hotelService, times(1)).findAll();
    }

    @Test
    public void whenSaveCalled_thenShouldSave() {
        when(hotelMapper.toModel(hotelDTO1)).thenReturn(hotelModel1);
        when(hotelService.save(hotelModel1)).thenReturn(hotelModel1);
        when(hotelMapper.toDto(hotelModel1)).thenReturn(hotelDTO1);
        when(hotelBasicResponseMapper.toResponseDTO(hotelDTO1)).thenReturn(hotelBasicResponseDTO1);

        HotelBasicResponseDTO actual = hotelFacade.save(hotelDTO1);

        assertNotNull(actual);
        verify(hotelService, times(1)).save(hotelModel1);
    }

    @Test
    public void whenAddAmenitiesCalled_thenAddAmenitiesServiceShouldCalled() {
        List<String> testAmenities = Arrays.asList("test1", "test2");
        AmenityModel amenityModel1 = new AmenityModel();
        AmenityModel amenityModel2 = new AmenityModel();

        when(amenityMapper.toModelFromString("test1")).thenReturn(amenityModel1);
        when(amenityMapper.toModelFromString("test2")).thenReturn(amenityModel2);

        hotelFacade.addAmenities(1L, testAmenities);

        verify(hotelService, times(1)).addAmenities(1L, Arrays.asList(amenityModel1, amenityModel2));
    }

    @Test
    public void whenFindAllByParamsCalled_thenServiceShouldCalled() {
        List<HotelModel> models = Arrays.asList(hotelModel1, hotelModel2);
        Map<String, String[]> params = new HashMap<>();
        when(hotelService.findByParams(params)).thenReturn(models);
        when(hotelMapper.toDto(hotelModel1)).thenReturn(hotelDTO1);
        when(hotelMapper.toDto(hotelModel2)).thenReturn(hotelDTO2);
        when(hotelBasicResponseMapper.toResponseDTO(hotelDTO1)).thenReturn(hotelBasicResponseDTO1);
        when(hotelBasicResponseMapper.toResponseDTO(hotelDTO2)).thenReturn(hotelBasicResponseDTO2);

        List<HotelBasicResponseDTO> actual = hotelFacade.findAll(params);

        assertNotNull(actual);
        assertEquals(2, actual.size());
        verify(hotelService, times(1)).findByParams(params);
    }



}