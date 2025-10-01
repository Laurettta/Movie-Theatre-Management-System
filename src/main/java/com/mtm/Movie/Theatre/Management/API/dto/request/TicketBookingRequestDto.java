package com.mtm.Movie.Theatre.Management.API.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketBookingRequestDto {

    @NotBlank(message = "userId cannot be empty")
    private String userId;

    @NotBlank(message = "showtimeId cannot be empty")
    private String showtimeId;

    @Min(value = 1, message = "seats must be at least 1")
    private int seats;

}
