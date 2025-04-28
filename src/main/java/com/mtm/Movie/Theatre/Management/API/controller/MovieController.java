package com.mtm.Movie.Theatre.Management.API.controller;

import com.mtm.Movie.Theatre.Management.API.dto.request.MovieRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.MovieResponseDto;
import com.mtm.Movie.Theatre.Management.API.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    private final MovieService movieService;

    @PostMapping("/save")
    public MovieResponseDto saveMovie(@Valid @RequestBody MovieRequestDto dto){
        return movieService.saveMovie(dto);
    }

    @GetMapping("/all")
    public List<MovieResponseDto> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable String id){
        return movieService.getMovieById(id);
    }

    @GetMapping("")
    public ResponseEntity<Page<MovieResponseDto>> getMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<MovieResponseDto> movies = movieService.getMovies(page, size);
        return ResponseEntity.ok(movies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> updateMovie(@PathVariable String id,
                                                 @RequestBody MovieRequestDto dto) {
        return movieService.updateMovie(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable String id){
        return movieService.deleteMovie(id);
    }

    
}
