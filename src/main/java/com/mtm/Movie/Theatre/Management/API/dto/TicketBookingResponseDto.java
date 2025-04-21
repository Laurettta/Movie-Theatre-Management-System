package com.mtm.Movie.Theatre.Management.API.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketBookingResponseDto {

    private String id;
    private String userId;
    private String showtimeId;
    private int seats;
    private String bookingDate;
}
