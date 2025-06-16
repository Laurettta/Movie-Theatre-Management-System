package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.dto.request.TicketBookingRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.TicketBookingResponseDto;
import com.mtm.Movie.Theatre.Management.API.exception.AccessDeniedException;
import com.mtm.Movie.Theatre.Management.API.exception.BadRequestException;
import com.mtm.Movie.Theatre.Management.API.exception.ShowtimeNotFoundException;
import com.mtm.Movie.Theatre.Management.API.exception.TicketBookingNotFoundException;
import com.mtm.Movie.Theatre.Management.API.mapper.TicketBookingMapper;
import com.mtm.Movie.Theatre.Management.API.model.Showtime;
import com.mtm.Movie.Theatre.Management.API.model.TicketBooking;
import com.mtm.Movie.Theatre.Management.API.repository.ShowtimeRepository;
import com.mtm.Movie.Theatre.Management.API.repository.TicketBookingRepository;
import com.mtm.Movie.Theatre.Management.API.service.LockService;
import com.mtm.Movie.Theatre.Management.API.service.TicketBookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class TicketBookingServiceImpl implements TicketBookingService {
    private final TicketBookingRepository ticketBookingRepository;
    private final ShowtimeRepository showtimeRepository;
    private final TicketBookingMapper ticketBookingMapper;
    private final LockService lockService;

    @Override
    public TicketBookingResponseDto saveBookings(TicketBookingRequestDto dto) {
        String showtimeId = dto.getShowtimeId();

        if(!lockService.acquireLock(showtimeId,5, TimeUnit.SECONDS)){
            throw new BadRequestException("Could not acquire booking lock for showtime " + showtimeId);
        }

        try {
            Showtime showtime = showtimeRepository.findById(showtimeId)
                    .orElseThrow(()-> new ShowtimeNotFoundException("Showtime not found: " + showtimeId));

            if (showtime.getAvailableSeats() < dto.getSeats()){
                throw new BadRequestException(
                        String.format("Requested %d seats but only %d available for showtime %s",
                        dto.getSeats(), showtime.getAvailableSeats(), showtimeId));
            }

            showtime.setAvailableSeats(showtime.getAvailableSeats() - dto.getSeats());
            showtimeRepository.save(showtime);

        TicketBooking ticketBooking = ticketBookingMapper.fromDto(dto);
        ticketBooking.setMovieId(showtime.getMovieId());
        return ticketBookingMapper.fromTicketBooking(ticketBookingRepository.save(ticketBooking));

    } finally {
            lockService.releaseLock(showtimeId);
        }
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
