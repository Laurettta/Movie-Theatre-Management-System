package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.dto.TicketBookingRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.TicketBookingResponseDto;
import com.mtm.Movie.Theatre.Management.API.exception.TicketBookingNotFoundException;
import com.mtm.Movie.Theatre.Management.API.mapper.TicketBookingMapper;
import com.mtm.Movie.Theatre.Management.API.model.TicketBooking;
import com.mtm.Movie.Theatre.Management.API.repository.TicketBookingRepository;
import com.mtm.Movie.Theatre.Management.API.service.TicketBookingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketBookingServiceImpl implements TicketBookingService {

    private final TicketBookingRepository ticketBookingRepository;

    private final TicketBookingMapper ticketBookingMapper;

    @Override
    public TicketBookingResponseDto saveBookings(TicketBookingRequestDto dto) {
        TicketBooking ticketBooking = ticketBookingMapper.fromDto(dto);
        return ticketBookingMapper.fromTicketBooking(ticketBookingRepository.save(ticketBooking));
    }

    @Override
    public List<TicketBookingResponseDto> getAllBookings() {
        return ticketBookingMapper.toDtoList(ticketBookingRepository.findAll());
    }

    @Override
    public ResponseEntity<List<TicketBookingResponseDto>> getBookingsByUserId(String userId) {
        List<TicketBooking> bookings = ticketBookingRepository.findByUserId(userId);

        if (bookings.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ticketBookingMapper.toDtoList(bookings));
    }

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
