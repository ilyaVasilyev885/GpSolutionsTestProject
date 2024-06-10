package com.gpsolutions.controllers;

import com.gpsolutions.annotations.ValidSearchParams;
import com.gpsolutions.dto.HotelBasicResponseDTO;
import com.gpsolutions.dto.HotelDTO;
import com.gpsolutions.facades.HotelFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.gpsolutions.constants.GpSolutionsConstants.*;

@RestController
@RequestMapping(PROPERTY_VIEW_API)
public class HotelController {

    private HotelFacade hotelFacade;

    @Autowired
    public HotelController(HotelFacade hotelFacade) {
        this.hotelFacade = hotelFacade;
    }

    @GetMapping(HOTELS_API)
    public List<HotelBasicResponseDTO> getAllHotels() {
        return hotelFacade.findAll();
    }

    @GetMapping(HOTELS_API + "/{id}")
    public HotelDTO getHotelById(@PathVariable Long id) {
        return hotelFacade.findById(id);
    }

    @PostMapping(HOTELS_API)
    @ResponseStatus(HttpStatus.CREATED)
    public HotelBasicResponseDTO createHotel(@RequestBody @Valid HotelDTO hotelDTO) {
        return hotelFacade.save(hotelDTO);
    }

    @PostMapping(HOTELS_API + "/{id}" + AMENITIES_API)
    @ResponseStatus(HttpStatus.CREATED)
    public void addAmenitiesToHotel(
            @PathVariable Long id,
            @RequestBody @NotEmpty(message = "list amenities should not be empty") List<String> amenities) {
        hotelFacade.addAmenities(id, amenities);
    }

    @GetMapping(SEARCH_API)
    public List<HotelBasicResponseDTO> searchHotels(@ValidSearchParams HttpServletRequest request) {
        return hotelFacade.findAll(request.getParameterMap());
    }


}
