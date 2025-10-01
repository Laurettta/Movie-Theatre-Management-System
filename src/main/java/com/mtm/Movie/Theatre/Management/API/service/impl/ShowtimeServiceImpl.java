package com.mtm.Movie.Theatre.Management.API.service.impl;

import com.mtm.Movie.Theatre.Management.API.dto.request.ShowtimeRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.ShowtimeResponseDto;
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
    public ShowtimeResponseDto saveShowtime(ShowtimeRequestDto dto) {
        // Map DTO to entity
        Showtime showtime = showtimeMapper.fromDto(dto);

        // Initialize availableSeats if not set or zero
        if (showtime.getAvailableSeats() <= 0) {
            showtime.setAvailableSeats(showtime.getTotalSeats()); // ensure seats available
        }

        // Save showtime
        Showtime savedShowtime = showtimeRepository.save(showtime);
        return showtimeMapper.fromShowtime(savedShowtime);
    }

//    @Override
//    public ShowtimeResponseDto saveShowtime(ShowtimeRequestDto dto) {
//        // Map DTO to entity
//        Showtime showtime = showtimeMapper.fromDto(dto);
//
//        // Initialize availableSeats if not set
//        if (showtime.getAvailableSeats() == 0) {
//            showtime.setAvailableSeats(showtime.getTotalSeats()); // ensure seats available
//        }
//
//        // Save showtime
//        Showtime savedShowtime = showtimeRepository.save(showtime);
//        return showtimeMapper.fromShowtime(savedShowtime);
//    }

    @Override
    public ResponseEntity<List<ShowtimeResponseDto>> getShowtimeByMovieId(String movieId) {
        List<Showtime> showtimes = showtimeRepository.findByMovieId(movieId);
        return ResponseEntity.ok(showtimeMapper.toDtoList(showtimes));
    }

    @Override
    public ResponseEntity<List<ShowtimeResponseDto>> getShowtimeByTheatre(String theatreId) {
        List<Showtime> showtimes = showtimeRepository.findByTheatreId(theatreId);
        return ResponseEntity.ok(showtimeMapper.toDtoList(showtimes));
    }

    @Override
    public List<ShowtimeResponseDto> getAllShowtimes() {
        List<Showtime> showtimes = showtimeRepository.findAll();
        return showtimeMapper.toDtoList(showtimes);
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
