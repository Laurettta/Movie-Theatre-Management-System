package com.mtm.Movie.Theatre.Management.API.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TicketBookingNotFoundException extends RuntimeException{
    public TicketBookingNotFoundException(String message) {
        super(message);
    }
}
