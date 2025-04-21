package com.mtm.Movie.Theatre.Management.API.service;

import com.mtm.Movie.Theatre.Management.API.dto.TheatreRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.TheatreResponseDto;
import com.mtm.Movie.Theatre.Management.API.model.Theatre;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TheatreService{
    TheatreResponseDto saveTheatre(TheatreRequestDto dto);

    List<TheatreResponseDto> getAllTheatres();

    ResponseEntity<TheatreResponseDto> getTheatreById(String id);

    ResponseEntity<TheatreResponseDto> updateTheatre(String id, TheatreRequestDto dto);

    ResponseEntity<Object> deleteTheatre(String id);
}
