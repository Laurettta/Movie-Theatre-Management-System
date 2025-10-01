package com.mtm.Movie.Theatre.Management.API.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeRequestDto {

    @NotBlank(message = "movieId cannot be empty")
    private String movieId;

    @NotBlank(message = "theatreId cannot be empty")
    private String theatreId;

    @NotNull(message = "showtime cannot be empty")
    private LocalDateTime showtime;

    @NotNull(message = "totalSeats cannot be empty")
    private Integer totalSeats;  

    private Integer availableSeats;
}
