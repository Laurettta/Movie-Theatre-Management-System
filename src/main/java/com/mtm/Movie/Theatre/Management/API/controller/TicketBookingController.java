package com.mtm.Movie.Theatre.Management.API.controller;
import com.mtm.Movie.Theatre.Management.API.dto.TicketBookingRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.TicketBookingResponseDto;
import com.mtm.Movie.Theatre.Management.API.model.TicketBooking;
import com.mtm.Movie.Theatre.Management.API.service.TicketBookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/save")
    public TicketBookingResponseDto saveBookings(@Valid @RequestBody TicketBookingRequestDto dto){
        return ticketBookingService.saveBookings(dto);
    }

    @GetMapping("/all")
    public List<TicketBookingResponseDto> getAllBookings(){
        return ticketBookingService.getAllBookings();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TicketBookingResponseDto>> getBookingsByUserId (@PathVariable String userId){
        return ticketBookingService.getBookingsByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBooking(@PathVariable String id){
        return ticketBookingService.deleteBookings(id);
    }
}
