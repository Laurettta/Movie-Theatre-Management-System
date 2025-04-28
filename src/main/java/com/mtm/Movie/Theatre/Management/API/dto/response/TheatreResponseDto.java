package com.mtm.Movie.Theatre.Management.API.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheatreResponseDto {

    private String id;
    private String name;
    private String location;
    private int seatingCapacity;
}
