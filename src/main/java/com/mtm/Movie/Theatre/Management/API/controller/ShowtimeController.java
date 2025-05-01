package com.mtm.Movie.Theatre.Management.API.controller;

import com.mtm.Movie.Theatre.Management.API.dto.request.ShowtimeRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.ShowtimeResponseDto;
import com.mtm.Movie.Theatre.Management.API.service.ShowtimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("showtimes")
@RequiredArgsConstructor
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    @PostMapping("/save")
    public ShowtimeResponseDto saveShowtime(@Valid @RequestBody ShowtimeRequestDto dto){
        return showtimeService.saveShowtime(dto);
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowtimeResponseDto>> getShowtimeMovie(@PathVariable String movieId){
        return showtimeService.getShowtimeByMovieId(movieId);
    }

    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<List<ShowtimeResponseDto>> getShowtimeByTheatre(@PathVariable String theatreId){
        return showtimeService.getShowtimeByTheatre(theatreId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteShowtime(@PathVariable String id){
        return showtimeService.deleteShowtimeById(id);
    }

}
