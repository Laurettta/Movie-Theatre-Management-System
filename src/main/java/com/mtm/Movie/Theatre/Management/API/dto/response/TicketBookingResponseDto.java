package com.mtm.Movie.Theatre.Management.API.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketBookingResponseDto {

    private String id;
    private String userId;
    private String showtimeId;
    private int seats;
    private LocalDateTime bookingDate;
}
