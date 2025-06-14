package com.mtm.Movie.Theatre.Management.API.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowtimeResponseDto {

    private String id;
    private String movieId;
    private String theatreId;
    private LocalDateTime showtime;
}
