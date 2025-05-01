package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.dto.request.TicketBookingRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.TicketBookingResponseDto;
import com.mtm.Movie.Theatre.Management.API.exception.AccessDeniedException;
import com.mtm.Movie.Theatre.Management.API.exception.TicketBookingNotFoundException;
import com.mtm.Movie.Theatre.Management.API.mapper.TicketBookingMapper;
import com.mtm.Movie.Theatre.Management.API.model.TicketBooking;
import com.mtm.Movie.Theatre.Management.API.repository.TicketBookingRepository;
import com.mtm.Movie.Theatre.Management.API.service.TicketBookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketBookingServiceImpl implements TicketBookingService {

    private final TicketBookingRepository ticketBookingRepository;
    private final TicketBookingMapper ticketBookingMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public TicketBookingResponseDto saveBookings(TicketBookingRequestDto dto) {
        TicketBooking ticketBooking = ticketBookingMapper.fromDto(dto);
        return ticketBookingMapper.fromTicketBooking(ticketBookingRepository.save(ticketBooking));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Override
    public List<TicketBookingResponseDto> getAllBookings() {
        return ticketBookingMapper.toDtoList(ticketBookingRepository.findAll());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Override
    public ResponseEntity<List<TicketBookingResponseDto>> getBookingsByUserId(String userId) {
        List<TicketBooking> bookings = ticketBookingRepository.findByUserId(userId);

        if (bookings.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ticketBookingMapper.toDtoList(bookings));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<Object> deleteBookings(String id) {
        return ticketBookingRepository.findById(id)
                .map(ticketBooking -> {
                    ticketBookingRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(()-> new TicketBookingNotFoundException(" TicketBooking Not Found"));
    }
}
