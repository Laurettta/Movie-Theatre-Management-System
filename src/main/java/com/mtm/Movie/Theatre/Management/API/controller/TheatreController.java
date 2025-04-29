package com.mtm.Movie.Theatre.Management.API.controller;

import com.mtm.Movie.Theatre.Management.API.dto.request.TheatreRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.TheatreResponseDto;
import com.mtm.Movie.Theatre.Management.API.service.TheatreService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    private final TheatreService theatreService;

    // Only accessible by ADMINs
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public TheatreResponseDto saveTheatre(@Valid @RequestBody TheatreRequestDto dto) {
        return theatreService.saveTheatre(dto);
    }

    // Accessible by any authenticated user (USER or ADMIN)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/all")
    public List<TheatreResponseDto> getAllTheatres(){
        return theatreService.getAllTheatres();
    }

    // Accessible by any authenticated user (USER or ADMIN)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<TheatreResponseDto> getTheatreById(@PathVariable String id){
        return theatreService.getTheatreById(id);
    }

    // Only accessible by ADMINs
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TheatreResponseDto> updateTheatre(@PathVariable String id,
                                                 @RequestBody TheatreRequestDto dto){
        return theatreService.updateTheatre(id,dto);
    }

    // Only accessible by ADMINs
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTheatre(@PathVariable String id){
        return theatreService.deleteTheatre(id);
    }
}
