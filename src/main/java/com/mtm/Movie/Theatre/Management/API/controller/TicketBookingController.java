package com.mtm.Movie.Theatre.Management.API.controller;
import com.mtm.Movie.Theatre.Management.API.dto.request.TicketBookingRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.TicketBookingResponseDto;
import com.mtm.Movie.Theatre.Management.API.service.TicketBookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/bookings")
//@RequiredArgsConstructor
public class TicketBookingController {


    private final TicketBookingService ticketBookingService;

    public TicketBookingController(TicketBookingService ticketBookingService) {
        this.ticketBookingService = ticketBookingService;
    }

    // Accessible by any authenticated user (USER or ADMIN)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/save")
    public TicketBookingResponseDto saveBookings(@Valid @RequestBody TicketBookingRequestDto dto){
        return ticketBookingService.saveBookings(dto);
    }

    // Accessible by any authenticated user (USER or ADMIN)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/all")
    public List<TicketBookingResponseDto> getAllBookings(){
        return ticketBookingService.getAllBookings();
    }

    // Accessible by any authenticated user (USER or ADMIN)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TicketBookingResponseDto>> getBookingsByUserId (@PathVariable String userId){
        return ticketBookingService.getBookingsByUserId(userId);
    }

    // Accessible by any authenticated user (USER or ADMIN)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBooking(@PathVariable String id){
        return ticketBookingService.deleteBookings(id);
    }
}
