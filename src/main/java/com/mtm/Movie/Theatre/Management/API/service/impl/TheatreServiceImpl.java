package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.dto.TheatreRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.TheatreResponseDto;
import com.mtm.Movie.Theatre.Management.API.exception.TheatreNotFoundException;
import com.mtm.Movie.Theatre.Management.API.mapper.TheatreMapper;
import com.mtm.Movie.Theatre.Management.API.model.Theatre;
import com.mtm.Movie.Theatre.Management.API.repository.TheatreRepository;
import com.mtm.Movie.Theatre.Management.API.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository theatreRepository;

    private final TheatreMapper theatreMapper;

//    constructor, field, setter



    @Override
    public TheatreResponseDto saveTheatre(TheatreRequestDto theatredto) {
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
        return theatreRepository.findById(id)
                .map(theatre -> {
                    theatreRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new TheatreNotFoundException("Theatre not found"));
    }
}
