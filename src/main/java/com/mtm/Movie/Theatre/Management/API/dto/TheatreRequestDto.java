package com.mtm.Movie.Theatre.Management.API.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheatreRequestDto {

    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotBlank(message = "Location cannot be empty")
    private String location;

    @Min(value = 1, message = "seatingCapacity must be greater than one")
    private int seatingCapacity;
}
