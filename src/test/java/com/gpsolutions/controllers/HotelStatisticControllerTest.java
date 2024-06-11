package com.gpsolutions.controllers;

import static com.gpsolutions.constants.GpSolutionsConstants.HISTOGRAM_API;
import static com.gpsolutions.constants.GpSolutionsConstants.PROPERTY_VIEW_API;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.gpsolutions.facades.HotelStatisticsFacade;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HotelStatisticController.class)
class HotelStatisticControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelStatisticsFacade hotelStatisticsFacade;

    @Test
    public void whenCityParam_shouldReturnStatus200() {
        getHistogramByParam("city");
    }

    @Test
    public void whenAmenitiesParam_shouldReturnStatus200() {
        getHistogramByParam("amenities");
    }

    @Test
    public void whenCountryParam_shouldReturnStatus200() {
        getHistogramByParam("country");
    }

    @Test
    public void whenBrandParam_shouldReturnStatus200() {
        getHistogramByParam("brand");
    }

    @SneakyThrows
    @Test
    public void whenInvalidParam_shouldReturnStatus400() {
        mockMvc.perform(get(PROPERTY_VIEW_API + HISTOGRAM_API + "/test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @SneakyThrows
    private void getHistogramByParam(String param) {
        mockMvc.perform(get(PROPERTY_VIEW_API + HISTOGRAM_API + "/" + param)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(hotelStatisticsFacade, times(1)).groupHotelsByParam(param);
    }

}