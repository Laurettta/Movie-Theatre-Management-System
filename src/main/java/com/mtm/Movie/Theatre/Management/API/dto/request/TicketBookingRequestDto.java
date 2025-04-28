package com.mtm.Movie.Theatre.Management.API.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketBookingRequestDto {

    @NotBlank(message = "userId cannot be empty")
    private String userId;

    @NotBlank(message = "showtimeId cannot be empty")
    private String showtimeId;

    @Min(value = 1, message = "seats must be greater than 1")
    private int seats;

    @NotBlank(message = "bookingDate cannot be empty")
    private String bookingDate;
}
