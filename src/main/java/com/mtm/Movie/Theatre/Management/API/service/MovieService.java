package com.mtm.Movie.Theatre.Management.API.service;

import com.mtm.Movie.Theatre.Management.API.dto.request.MovieRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.MovieResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {
     MovieResponseDto saveMovie(MovieRequestDto moviedto);

     Page<MovieResponseDto> getMovies(int page, int size);

    List<MovieResponseDto> getAllMovies();

    ResponseEntity<MovieResponseDto> getMovieById(String id);

    ResponseEntity<MovieResponseDto> updateMovie(String id, MovieRequestDto dto);

    ResponseEntity<Object> deleteMovie(String id);
}
