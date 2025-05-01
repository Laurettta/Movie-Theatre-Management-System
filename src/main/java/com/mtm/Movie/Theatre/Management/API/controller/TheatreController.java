package com.mtm.Movie.Theatre.Management.API.controller;

import com.mtm.Movie.Theatre.Management.API.dto.request.TheatreRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.TheatreResponseDto;
import com.mtm.Movie.Theatre.Management.API.service.TheatreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatres")
@RequiredArgsConstructor
public class TheatreController {

    private final TheatreService theatreService;

    @PostMapping("/save")
    public TheatreResponseDto saveTheatre(@Valid @RequestBody TheatreRequestDto dto) {
        return theatreService.saveTheatre(dto);
    }

    @GetMapping("/all")
    public List<TheatreResponseDto> getAllTheatres(){
        return theatreService.getAllTheatres();
    }


    @GetMapping("/{id}")
    public ResponseEntity<TheatreResponseDto> getTheatreById(@PathVariable String id){
        return theatreService.getTheatreById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TheatreResponseDto> updateTheatre(@PathVariable String id,
                                                 @RequestBody TheatreRequestDto dto){
        return theatreService.updateTheatre(id,dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTheatre(@PathVariable String id){
        return theatreService.deleteTheatre(id);
    }
}
