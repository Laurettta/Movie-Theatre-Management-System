package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.dto.request.MovieRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.MovieResponseDto;
import com.mtm.Movie.Theatre.Management.API.exception.AccessDeniedException;
import com.mtm.Movie.Theatre.Management.API.exception.MovieNotFoundException;
import com.mtm.Movie.Theatre.Management.API.mapper.MovieMapper;
import com.mtm.Movie.Theatre.Management.API.model.Movie;
import com.mtm.Movie.Theatre.Management.API.repository.MovieRepository;
import com.mtm.Movie.Theatre.Management.API.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;

    private void checkIfAdmin() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getAuthorities().stream()
                .noneMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            throw new AccessDeniedException("Access denied: you are not allowed to perform this action.");
        }
    }

    @Override
    public MovieResponseDto saveMovie(MovieRequestDto dto) {
        checkIfAdmin();
        Movie movie = movieMapper.fromDto(dto);
        return movieMapper.fromMovie(movieRepository.save(movie));
    }

    @Override
    public Page<MovieResponseDto> getMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        return moviePage.map(movieMapper::fromMovie);
    }

    @Override
    public List<MovieResponseDto> getAllMovies() {
        return movieMapper.toDtoList(movieRepository.findAll());
    }

    @Override
    public ResponseEntity<MovieResponseDto> getMovieById(String id) {
        return movieRepository.findById(id)
                .map(movie -> ResponseEntity.ok(movieMapper.fromMovie(movie)))
                .orElseThrow(() -> new MovieNotFoundException("movie not found"));
    }

    @Override
    public ResponseEntity<MovieResponseDto> updateMovie(String id, MovieRequestDto dto) {
        checkIfAdmin();
        return movieRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(dto.getTitle());
                    existing.setGenre(dto.getGenre());
                    existing.setDuration(dto.getDuration());
                    existing.setReleaseDate(dto.getReleaseDate());
                    return ResponseEntity.ok(movieMapper.fromMovie(movieRepository.save(existing)));
                })

                .orElseThrow(() -> new MovieNotFoundException("movie not found"));
    }

    @Override
    public ResponseEntity<Object> deleteMovie(String id) {
        checkIfAdmin();
        return movieRepository.findById(id)
                .map(movie -> {
                    movieRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(()->new MovieNotFoundException("movie not found"));
    }


}
