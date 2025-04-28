package com.mtm.Movie.Theatre.Management.API.dto.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequestDto {

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Genre cannot be empty")
    private String genre;

    @Min(value = 1, message = "Duration must be greater than 0")
    private int duration;

    @NotBlank(message = "Release date is required")
    private String releaseDate;


}
