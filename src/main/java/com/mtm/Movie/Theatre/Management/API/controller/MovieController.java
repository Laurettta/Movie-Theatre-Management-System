package com.mtm.Movie.Theatre.Management.API.controller;

import com.mtm.Movie.Theatre.Management.API.dto.request.MovieRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.MovieResponseDto;
import com.mtm.Movie.Theatre.Management.API.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public MovieResponseDto saveMovie(@Valid @RequestBody MovieRequestDto dto){
        return movieService.saveMovie(dto);
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable String id){
        return movieService.getMovieById(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<MovieResponseDto>> getMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<MovieResponseDto> movies = movieService.getMovies(page, size);
        return ResponseEntity.ok(movies);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> updateMovie(@PathVariable String id,
                                                 @RequestBody MovieRequestDto dto) {
        return movieService.updateMovie(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable String id){
        return movieService.deleteMovie(id);
    }


//    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
}
