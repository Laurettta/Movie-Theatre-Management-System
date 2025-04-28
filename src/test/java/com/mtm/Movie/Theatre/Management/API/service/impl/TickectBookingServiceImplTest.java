package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.dto.request.TicketBookingRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.TicketBookingResponseDto;
import com.mtm.Movie.Theatre.Management.API.exception.TicketBookingNotFoundException;
import com.mtm.Movie.Theatre.Management.API.mapper.TicketBookingMapper;
import com.mtm.Movie.Theatre.Management.API.model.TicketBooking;
import com.mtm.Movie.Theatre.Management.API.repository.TicketBookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TickectBookingServiceImplTest {

    @InjectMocks
    private TicketBookingServiceImpl ticketBookingService;

    @Mock
    private TicketBookingRepository ticketBookingRepository;

    @Mock
    private TicketBookingMapper ticketBookingMapper;

    private TicketBooking ticketBooking;
    private TicketBookingRequestDto requestDto;
    private TicketBookingResponseDto responseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ticketBooking = new TicketBooking("1", "user123", "showtimeId456", 3, "2025-05-05T14:30:00");
        requestDto = new TicketBookingRequestDto("user123", "showtimeId456", 3, "2025-05-05T14:30:00");
        responseDto = new TicketBookingResponseDto("1", "user123", "showtimeId456", 3, "2025-05-05T14:30:00");
    }

    @Test
    void saveBookings_shouldReturnSavedBooking() {
        when(ticketBookingMapper.fromDto(requestDto)).thenReturn(ticketBooking);
        when(ticketBookingRepository.save(ticketBooking)).thenReturn(ticketBooking);
        when(ticketBookingMapper.fromTicketBooking(ticketBooking)).thenReturn(responseDto);

        TicketBookingResponseDto result = ticketBookingService.saveBookings(requestDto);

        assertEquals(responseDto, result);
        verify(ticketBookingRepository).save(ticketBooking);
    }

    @Test
    void getAllBookings_shouldReturnListOfBookingDtos() {
        List<TicketBooking> bookings = List.of(ticketBooking);
        List<TicketBookingResponseDto> bookingDtos = List.of(responseDto);

        when(ticketBookingRepository.findAll()).thenReturn(bookings);
        when(ticketBookingMapper.toDtoList(bookings)).thenReturn(bookingDtos);

        List<TicketBookingResponseDto> result = ticketBookingService.getAllBookings();

        assertEquals(bookingDtos, result);
        verify(ticketBookingRepository).findAll();
    }

    @Test
    void getBookingsByUserId_whenFound_shouldReturnBookingList() {
        List<TicketBooking> bookings = List.of(ticketBooking);
        List<TicketBookingResponseDto> bookingDtos = List.of(responseDto);

        when(ticketBookingRepository.findByUserId("user123")).thenReturn(bookings);
        when(ticketBookingMapper.toDtoList(bookings)).thenReturn(bookingDtos);

        ResponseEntity<List<TicketBookingResponseDto>> result = ticketBookingService.getBookingsByUserId("user123");

        assertEquals(ResponseEntity.ok(bookingDtos), result);
    }

    @Test
    void getBookingsByUserId_whenNotFound_shouldReturnNotFound() {
        when(ticketBookingRepository.findByUserId("user123")).thenReturn(Collections.emptyList());

        ResponseEntity<List<TicketBookingResponseDto>> result = ticketBookingService.getBookingsByUserId("user123");

        assertEquals(ResponseEntity.notFound().build(), result);
    }

    @Test
    void deleteBookings_whenFound_shouldDeleteAndReturnNoContent() {
        when(ticketBookingRepository.findById("1")).thenReturn(Optional.of(ticketBooking));

        ResponseEntity<Object> result = ticketBookingService.deleteBookings("1");

        assertEquals(ResponseEntity.noContent().build(), result);
        verify(ticketBookingRepository).deleteById("1");
    }

    @Test
    void deleteBookings_whenNotFound_shouldThrowException() {
        when(ticketBookingRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(TicketBookingNotFoundException.class, () -> ticketBookingService.deleteBookings("1"));
    }
}
