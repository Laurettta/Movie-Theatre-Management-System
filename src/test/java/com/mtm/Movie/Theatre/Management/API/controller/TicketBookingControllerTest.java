package com.mtm.Movie.Theatre.Management.API.controller;

import com.mtm.Movie.Theatre.Management.API.dto.request.TicketBookingRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.TicketBookingResponseDto;
import com.mtm.Movie.Theatre.Management.API.service.TicketBookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class TicketBookingControllerTest {

    @Mock
    private TicketBookingService ticketBookingService;

    @InjectMocks
    private TicketBookingController ticketBookingController;

    private TicketBookingRequestDto requestDto;
    private TicketBookingResponseDto responseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        requestDto = new TicketBookingRequestDto("user123", "show123", 2, LocalDateTime.parse("2025-04-21T18:30"));
        responseDto = new TicketBookingResponseDto("bookingId1", "user123", "show123", 2, LocalDateTime.parse( "2025-04-21T20:00"));
    }

    @Test
    void bookTicket_success() {
        when(ticketBookingService.saveBookings(requestDto)).thenReturn(responseDto);

        TicketBookingResponseDto result = ticketBookingController.saveBookings(requestDto);

        assertNotNull(result);
        assertEquals("bookingId1", result.getId());
        assertEquals("user123", result.getUserId());
        verify(ticketBookingService, times(1)).saveBookings(requestDto);
    }

    @Test
    void getAllBookings_success() {
        List<TicketBookingResponseDto> bookings = List.of(responseDto);
        when(ticketBookingService.getAllBookings()).thenReturn(bookings);

        List<TicketBookingResponseDto> result = ticketBookingController.getAllBookings();

        assertEquals(1, result.size());
        assertEquals("user123", result.get(0).getUserId());
        verify(ticketBookingService, times(1)).getAllBookings();
    }


    @Test
    void deleteBooking_success() {
        when(ticketBookingService.deleteBookings("bookingId1")).thenReturn(ResponseEntity.noContent().build());

        ResponseEntity<Object> result = ticketBookingController.deleteBooking("bookingId1");

        assertEquals(204, result.getStatusCodeValue());
        verify(ticketBookingService, times(1)).deleteBookings("bookingId1");
    }

    @Test
    void deleteBooking_notFound() {
        when(ticketBookingService.deleteBookings("unknownId")).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<Object> result = ticketBookingController.deleteBooking("unknownId");

        assertEquals(404, result.getStatusCodeValue());
        verify(ticketBookingService, times(1)).deleteBookings("unknownId");
    }


}
