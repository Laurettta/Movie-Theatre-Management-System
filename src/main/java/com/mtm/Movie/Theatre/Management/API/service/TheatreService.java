package com.mtm.Movie.Theatre.Management.API.service;

import com.mtm.Movie.Theatre.Management.API.dto.request.TheatreRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.TheatreResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TheatreService{
    TheatreResponseDto saveTheatre(TheatreRequestDto dto);

    List<TheatreResponseDto> getAllTheatres();

    ResponseEntity<TheatreResponseDto> getTheatreById(String id);

    ResponseEntity<TheatreResponseDto> updateTheatre(String id, TheatreRequestDto dto);

    ResponseEntity<Object> deleteTheatre(String id);
}
