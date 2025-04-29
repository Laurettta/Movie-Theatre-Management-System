package com.mtm.Movie.Theatre.Management.API.controller;

import com.mtm.Movie.Theatre.Management.API.dto.request.MovieRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.MovieResponseDto;
import com.mtm.Movie.Theatre.Management.API.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // Only accessible by ADMINs
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public MovieResponseDto saveMovie(@Valid @RequestBody MovieRequestDto dto){
        return movieService.saveMovie(dto);
    }

    // Accessible by any authenticated user (USER or ADMIN)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/all")
    public List<MovieResponseDto> getAllMovies(){
        return movieService.getAllMovies();
    }

    // Accessible by any authenticated user (USER or ADMIN)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable String id){
        return movieService.getMovieById(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("")
    public ResponseEntity<Page<MovieResponseDto>> getMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<MovieResponseDto> movies = movieService.getMovies(page, size);
        return ResponseEntity.ok(movies);
    }

    // Only accessible by ADMINs
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> updateMovie(@PathVariable String id,
                                                 @RequestBody MovieRequestDto dto) {
        return movieService.updateMovie(id, dto);
    }

    // Only accessible by ADMINs
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable String id){
        return movieService.deleteMovie(id);
    }

    
}
