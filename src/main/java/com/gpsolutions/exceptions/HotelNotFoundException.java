package com.gpsolutions.exceptions;

public class HotelNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final String EXCEPTION_MSG = "Could not find a hotel with id = ";

    public HotelNotFoundException(Long id) {
        super(EXCEPTION_MSG + id);
    }
}
