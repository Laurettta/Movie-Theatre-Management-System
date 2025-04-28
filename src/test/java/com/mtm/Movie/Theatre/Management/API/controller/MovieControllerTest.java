package com.mtm.Movie.Theatre.Management.API.controller;

import com.mtm.Movie.Theatre.Management.API.dto.request.MovieRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.MovieResponseDto;
import com.mtm.Movie.Theatre.Management.API.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    private MovieRequestDto movieRequestDto;
    private MovieResponseDto movieResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        movieRequestDto = new MovieRequestDto("Wrong turn", "Horror", 180, "2025-05-05T12:00");
        movieResponseDto = new MovieResponseDto("1", "Wrong turn", "180", 180, "2025-05-05T12:00");
    }

    @Test
    void saveMovie_success() {
        when(movieService.saveMovie(movieRequestDto)).thenReturn(movieResponseDto);

        MovieResponseDto result = movieController.saveMovie(movieRequestDto);

        assertEquals("1", result.getId());
        assertEquals("Wrong turn", result.getTitle());
        verify(movieService, times(1)).saveMovie(movieRequestDto);
    }

    @Test
    void getAllMovies_success() {
        List<MovieResponseDto> movies = List.of(movieResponseDto);
        when(movieService.getAllMovies()).thenReturn(movies);

        List<MovieResponseDto> result = movieController.getAllMovies();

        assertEquals(1, result.size());
        assertEquals("Wrong turn", result.get(0).getTitle());
        verify(movieService, times(1)).getAllMovies();
    }

    @Test
    void getMovieById_success() {
        when(movieService.getMovieById("1")).thenReturn(ResponseEntity.ok(movieResponseDto));

        ResponseEntity<MovieResponseDto> result = movieController.getMovieById("1");

        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Wrong turn", result.getBody().getTitle());
        verify(movieService, times(1)).getMovieById("1");
    }

    @Test
    void getMovieById_notFound() {
        when(movieService.getMovieById("999")).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<MovieResponseDto> result = movieController.getMovieById("999");

        assertEquals(404, result.getStatusCodeValue());
        verify(movieService, times(1)).getMovieById("999");
    }


    @Test
    void updateMovie_success() {
        when(movieService.updateMovie("1", movieRequestDto)).thenReturn(ResponseEntity.ok(movieResponseDto));

        ResponseEntity<MovieResponseDto> result = movieController.updateMovie("1", movieRequestDto);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Wrong turn", result.getBody().getTitle());
        verify(movieService, times(1)).updateMovie("1", movieRequestDto);
    }

    @Test
    void deleteMovie_success() {
        when(movieService.deleteMovie("1")).thenReturn(ResponseEntity.noContent().build());

        ResponseEntity<Object> result = movieController.deleteMovie("1");

        assertEquals(204, result.getStatusCodeValue());
        verify(movieService, times(1)).deleteMovie("1");
    }

    @Test
    void deleteMovie_notFound() {
        when(movieService.deleteMovie("999")).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<Object> result = movieController.deleteMovie("999");

        assertEquals(404, result.getStatusCodeValue());
        verify(movieService, times(1)).deleteMovie("999");
    }

}