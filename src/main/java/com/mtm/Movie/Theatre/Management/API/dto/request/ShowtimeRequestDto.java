package com.mtm.Movie.Theatre.Management.API.dto.request;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "showtime cannot be empty")
    private LocalDateTime showtime;
}
