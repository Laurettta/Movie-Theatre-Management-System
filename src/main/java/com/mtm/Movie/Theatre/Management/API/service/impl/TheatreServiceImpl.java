package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.dto.request.TheatreRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.TheatreResponseDto;
import com.mtm.Movie.Theatre.Management.API.exception.AccessDeniedException;
import com.mtm.Movie.Theatre.Management.API.exception.TheatreNotFoundException;
import com.mtm.Movie.Theatre.Management.API.mapper.TheatreMapper;
import com.mtm.Movie.Theatre.Management.API.model.Theatre;
import com.mtm.Movie.Theatre.Management.API.repository.TheatreRepository;
import com.mtm.Movie.Theatre.Management.API.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository theatreRepository;
    private final TheatreMapper theatreMapper;

    private void checkIfAdmin() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getAuthorities().stream()
                .noneMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            throw new AccessDeniedException("Access denied: you are not allowed to perform this action.");
        }
    }

    @Override
    public TheatreResponseDto saveTheatre(TheatreRequestDto theatredto) {
        checkIfAdmin();
        Theatre theatre = theatreMapper.fromDto(theatredto);
        return theatreMapper.fromTheatre(theatreRepository.save(theatre));
    }

    @Override
    public List<TheatreResponseDto> getAllTheatres() {
        return theatreMapper.toDtoList(theatreRepository.findAll());
    }

    @Override
    public ResponseEntity<TheatreResponseDto> getTheatreById(String id) {
        return theatreRepository.findById(id)
                .map(theatre -> ResponseEntity.ok(theatreMapper.fromTheatre(theatre)))
                .orElseThrow(() -> new TheatreNotFoundException("Theatre not found"));
    }

    @Override
    public ResponseEntity<TheatreResponseDto> updateTheatre(String id, TheatreRequestDto dto) {
        checkIfAdmin();
        return theatreRepository.findById(id)
                .map(existingTheatre ->{
                    existingTheatre.setName(dto.getName());
                    existingTheatre.setLocation(dto.getLocation());
                    existingTheatre.setSeatingCapacity(dto.getSeatingCapacity());


                    return ResponseEntity.ok(theatreMapper.fromTheatre(theatreRepository.save(existingTheatre)));
                })
                .orElseThrow(()-> new TheatreNotFoundException("Theatre not found"));
    }

    @Override
    public ResponseEntity<Object> deleteTheatre(String id) {
        checkIfAdmin();
        return theatreRepository.findById(id)
                .map(theatre -> {
                    theatreRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new TheatreNotFoundException("Theatre not found"));
    }
}
