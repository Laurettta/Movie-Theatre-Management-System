package com.mtm.Movie.Theatre.Management.API.service;

import com.mtm.Movie.Theatre.Management.API.dto.ShowtimeRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.ShowtimeResponseDto;
import com.mtm.Movie.Theatre.Management.API.model.Showtime;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShowtimeService {
    ShowtimeResponseDto saveShowtime(ShowtimeRequestDto dto);
    
    ResponseEntity<List<ShowtimeResponseDto>> getShowtimeByMovieId(String movieId);

    ResponseEntity<List<ShowtimeResponseDto>> getShowtimeByTheatre(String theatreId);

    ResponseEntity<Object> deleteShowtimeById(String id);
}
