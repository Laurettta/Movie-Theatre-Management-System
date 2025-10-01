package com.mtm.Movie.Theatre.Management.API.mapper;

import com.mtm.Movie.Theatre.Management.API.dto.request.TicketBookingRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.TicketBookingResponseDto;
import com.mtm.Movie.Theatre.Management.API.model.TicketBooking;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketBookingMapper {
    public TicketBooking fromDto(TicketBookingRequestDto dto){
        return TicketBooking.builder()
                .seats(dto.getSeats())
                .showtimeId(dto.getShowtimeId())
                .userId(dto.getUserId())
                .build();
    }

    public TicketBookingResponseDto fromTicketBooking(TicketBooking ticketBooking){
        return TicketBookingResponseDto.builder()
                .userId(ticketBooking.getUserId())
                .bookingDate(ticketBooking.getBookingDate())
                .showtimeId(ticketBooking.getShowtimeId())
                .seats(ticketBooking.getSeats())
                .build();
    }

    public List<TicketBookingResponseDto> toDtoList(List<TicketBooking> ticketBookings){
        List<TicketBookingResponseDto> response = new ArrayList<>();
        for (TicketBooking ticketBooking : ticketBookings){
            TicketBookingResponseDto t = TicketBookingResponseDto.builder()
                    .bookingDate(ticketBooking.getBookingDate())
                    .seats(ticketBooking.getSeats())
                    .userId(ticketBooking.getUserId())
                    .showtimeId(ticketBooking.getShowtimeId())
                    .build();
            response.add(t);
        }
        return response;
    }

}
