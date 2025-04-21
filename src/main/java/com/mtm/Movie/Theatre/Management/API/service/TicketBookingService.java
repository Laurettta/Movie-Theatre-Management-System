package com.mtm.Movie.Theatre.Management.API.service;

import com.mtm.Movie.Theatre.Management.API.dto.TicketBookingRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.TicketBookingResponseDto;
import com.mtm.Movie.Theatre.Management.API.model.TicketBooking;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketBookingService {
    TicketBookingResponseDto saveBookings(TicketBookingRequestDto dto);

    List<TicketBookingResponseDto> getAllBookings();

    ResponseEntity<List<TicketBookingResponseDto>> getBookingsByUserId(String userId);

    ResponseEntity<Object> deleteBookings(String id);
}
