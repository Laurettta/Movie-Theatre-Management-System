package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.dto.ShowtimeRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.ShowtimeResponseDto;
import com.mtm.Movie.Theatre.Management.API.exception.ShowtimeNotFoundException;
import com.mtm.Movie.Theatre.Management.API.mapper.ShowtimeMapper;
import com.mtm.Movie.Theatre.Management.API.model.Showtime;
import com.mtm.Movie.Theatre.Management.API.repository.ShowtimeRepository;
import com.mtm.Movie.Theatre.Management.API.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {


    private final ShowtimeRepository showtimeRepository;

    private final ShowtimeMapper showtimeMapper;

    @Override
    public ShowtimeResponseDto saveShowtime(ShowtimeRequestDto dto)
    {
        Showtime showtime = showtimeMapper.fromDto(dto);
        return showtimeMapper.fromShowtime(showtimeRepository.save(showtime));
    }

    @Override
    public ResponseEntity<List<ShowtimeResponseDto>> getShowtimeByMovieId(String movieId) {
        List<Showtime> showtime = showtimeRepository.findByMovieId(movieId);
        return ResponseEntity.ok(showtimeMapper.toDtoList(showtime));
    }

    @Override
    public ResponseEntity<List<ShowtimeResponseDto>> getShowtimeByTheatre(String theatreId) {
        List<Showtime> showtime = showtimeRepository.findByTheatreId(theatreId);
                return ResponseEntity.ok(showtimeMapper.toDtoList(showtime));
    }

    @Override
    public ResponseEntity<Object> deleteShowtimeById(String id) {
        return showtimeRepository.findById(id)
                .map(showtime -> {
                    showtimeRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new ShowtimeNotFoundException("Show time not found"));
    }
}
