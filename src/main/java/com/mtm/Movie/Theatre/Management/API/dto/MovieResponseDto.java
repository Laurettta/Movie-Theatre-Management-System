package com.mtm.Movie.Theatre.Management.API.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResponseDto {

    private String id;
    private String title;
    private String genre;
    private int duration;
    private String releaseDate;

}
