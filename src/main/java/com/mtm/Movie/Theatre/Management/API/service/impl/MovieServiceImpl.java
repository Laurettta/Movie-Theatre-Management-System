package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.dto.MovieRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.MovieResponseDto;
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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;


    @Override
    public MovieResponseDto saveMovie(MovieRequestDto dto) {
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
        return movieRepository.findById(id)
                .map(movie -> {
                    movieRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(()->new MovieNotFoundException("movie not found"));
    }


}
