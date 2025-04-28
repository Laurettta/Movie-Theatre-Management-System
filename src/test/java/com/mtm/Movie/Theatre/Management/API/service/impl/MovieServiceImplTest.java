package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.dto.request.MovieRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.MovieResponseDto;
import com.mtm.Movie.Theatre.Management.API.exception.MovieNotFoundException;
import com.mtm.Movie.Theatre.Management.API.mapper.MovieMapper;
import com.mtm.Movie.Theatre.Management.API.model.Movie;
import com.mtm.Movie.Theatre.Management.API.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieMapper movieMapper;

    @InjectMocks
    private MovieServiceImpl movieService;

    private Movie movie;
    private MovieRequestDto movieRequestDto;
    private MovieResponseDto movieResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        movie = new Movie("1", "Lion King", "Drama", 120, "2024-05-10");
        movieRequestDto = new MovieRequestDto("Lion King", "Drama", 12, "2024-05-10");
        movieResponseDto = new MovieResponseDto("1", "Lion King", "Drama", 120, "2024-05-10");
    }

    @Test
    void saveMovieTest() {
        when(movieMapper.fromDto(movieRequestDto)).thenReturn(movie);
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        when(movieMapper.fromMovie(movie)).thenReturn(movieResponseDto);

        MovieResponseDto savedMovie = movieService.saveMovie(movieRequestDto);

        assertEquals("Lion King", savedMovie.getTitle());
        verify(movieRepository, times(1)).save(any(Movie.class));
    }

    @Test
    void getMovies() {
        Page<Movie> moviePage = new PageImpl<>(List.of(movie));
        PageRequest pageRequest = PageRequest.of(0, 10);
        when(movieRepository.findAll(pageRequest)).thenReturn(moviePage);
        when(movieMapper.fromMovie(movie)).thenReturn(movieResponseDto);

        Page<MovieResponseDto> result = movieService.getMovies(0, 10);

        assertEquals(1, result.getTotalElements());
        assertEquals("Lion King", result.getContent().get(0).getTitle());
        verify(movieRepository).findAll(pageRequest);
    }

    @Test
    void getAllMovies() {
        List<Movie> movies = List.of(movie);
        List<MovieResponseDto> responseDtos = List.of(movieResponseDto);

        when(movieRepository.findAll()).thenReturn(movies);
        when(movieMapper.toDtoList(movies)).thenReturn(responseDtos);

        List<MovieResponseDto> result = movieService.getAllMovies();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(movieRepository).findAll();
    }

    @Test
    void getMovieById_success() {
        when(movieRepository.findById("1")).thenReturn(Optional.of(movie));
        when(movieMapper.fromMovie(movie)).thenReturn(movieResponseDto);

        var response = movieService.getMovieById("1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Lion King", response.getBody().getTitle());
    }

    @Test
    void getMovieById_notFound() {
        when(movieRepository.findById("99")).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> movieService.getMovieById("99"));
    }

    @Test
    void updateMovie_success() {
        MovieRequestDto updatedDto = new MovieRequestDto("Updated", "Thriller", 130, "2025-01-01");
        Movie updatedMovie = new Movie("1", "Updated", "Thriller", 130, "2025-01-01");
        MovieResponseDto updatedResponseDto = new MovieResponseDto("1", "Updated", "Thriller", 130, "2025-01-01");

        when(movieRepository.findById("1")).thenReturn(Optional.of(movie));
        when(movieRepository.save(any(Movie.class))).thenReturn(updatedMovie);
        when(movieMapper.fromMovie(updatedMovie)).thenReturn(updatedResponseDto);

        var response = movieService.updateMovie("1", updatedDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody().getTitle());
    }

    @Test
    void updateMovie_notFound() {
        when(movieRepository.findById("404")).thenReturn(Optional.empty());
        MovieRequestDto dto = new MovieRequestDto("Any", "Genre", 100, "2025-01-01");

        assertThrows(MovieNotFoundException.class, () -> movieService.updateMovie("404", dto));
    }

    @Test
    void deleteMovie_success() {
        when(movieRepository.findById("1")).thenReturn(Optional.of(movie));
        doNothing().when(movieRepository).deleteById("1");

        var response = movieService.deleteMovie("1");

        assertEquals(204, response.getStatusCodeValue());
        verify(movieRepository).deleteById("1");
    }

    @Test
    void deleteMovie_notFound() {
        when(movieRepository.findById("999")).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> movieService.deleteMovie("999"));
    }

}