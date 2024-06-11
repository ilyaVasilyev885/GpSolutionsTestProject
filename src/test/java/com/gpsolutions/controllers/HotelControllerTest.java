package com.gpsolutions.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpsolutions.dto.AddressDTO;
import com.gpsolutions.dto.ArrivalTimeDTO;
import com.gpsolutions.dto.ContactDTO;
import com.gpsolutions.dto.HotelDTO;
import com.gpsolutions.facades.HotelFacade;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.gpsolutions.constants.GpSolutionsConstants.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HotelController.class)
class HotelControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objMapper;

    @MockBean
    private HotelFacade hotelFacade;

    @Test
    @SneakyThrows
    public void whenFindAll_thenReturnStatus200() {
        mockMvc.perform(get(PROPERTY_VIEW_API + HOTELS_API)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(hotelFacade, times(1)).findAll();
    }

    @Test
    @SneakyThrows
    public void whenFindById_thenReturnStatus200() {
        mockMvc.perform(get(PROPERTY_VIEW_API + HOTELS_API + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(hotelFacade, times(1)).findById(1L);
    }

    @Test
    @SneakyThrows
    public void whenPostHotel_thenReturnStatus201() {
        HotelDTO dto = new HotelDTO();
        dto.setName("name");
        dto.setBrand("brand");
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity("Minsk");
        addressDTO.setCountry("Belarus");
        addressDTO.setStreet("Pobediteley Avenue");
        addressDTO.setHouseNumber(11);
        addressDTO.setPostalCode("220015");
        dto.setAddress(addressDTO);
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setPhone("+375 29 100-10-10");
        contactDTO.setEmail("testemail@gmail.com");
        dto.setContacts(contactDTO);
        ArrivalTimeDTO arrivalTimeDTO = new ArrivalTimeDTO();
        arrivalTimeDTO.setCheckIn(LocalTime.NOON);
        dto.setArrivalTime(arrivalTimeDTO);

        mockMvc.perform(post(PROPERTY_VIEW_API + HOTELS_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @SneakyThrows
    @Test
    public void whenAddAmenitiesToHotel_thenReturnStatus201() {
        List<String> amenities = new ArrayList<>();
        amenities.add("testAmenity1");
        amenities.add("testAmenity2");

        mockMvc.perform(post(PROPERTY_VIEW_API + HOTELS_API + "/1" + AMENITIES_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objMapper.writeValueAsString(amenities)))
                .andExpect(status().isCreated());

        verify(hotelFacade, times(1)).addAmenities(1L, amenities);
    }

    @SneakyThrows
    @Test
    public void whenSearch_thenReturnStatus200() {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("amenities", "testAmenity1");
        requestParams.add("city", "Minsk");
        mockMvc.perform(get(PROPERTY_VIEW_API + SEARCH_API)
                        .params(requestParams)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(hotelFacade).findAll(anyMap());
    }

}