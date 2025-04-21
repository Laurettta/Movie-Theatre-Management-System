package com.mtm.Movie.Theatre.Management.API.mapper;

import com.mtm.Movie.Theatre.Management.API.dto.MovieRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.MovieResponseDto;
import com.mtm.Movie.Theatre.Management.API.dto.TheatreRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.TheatreResponseDto;
import com.mtm.Movie.Theatre.Management.API.model.Theatre;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class TheatreMapper {


    public Theatre fromDto(TheatreRequestDto dto){
        return Theatre.builder()
                .name(dto.getName())
                .seatingCapacity(dto.getSeatingCapacity())
                .location(dto.getLocation())
                .build();
    }

    public TheatreResponseDto fromTheatre(Theatre theatre){
        return TheatreResponseDto.builder()
                .name(theatre.getName())
                .seatingCapacity(theatre.getSeatingCapacity())
                .location(theatre.getLocation())
                .build();
    }

    public List<TheatreResponseDto> toDtoList(List<Theatre> theatres){
        List<TheatreResponseDto> response = new ArrayList<>();
        for (Theatre theatre : theatres){
            TheatreResponseDto t = TheatreResponseDto.builder()
                    .location(theatre.getLocation())
                    .seatingCapacity(theatre.getSeatingCapacity())
                    .name(theatre.getName())
                    .build();
            response.add(t);
        }
        return response;
    }
}
