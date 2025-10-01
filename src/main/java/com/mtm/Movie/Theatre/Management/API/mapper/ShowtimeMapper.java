package com.mtm.Movie.Theatre.Management.API.mapper;

import com.mtm.Movie.Theatre.Management.API.dto.request.ShowtimeRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.ShowtimeResponseDto;
import com.mtm.Movie.Theatre.Management.API.model.Showtime;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShowtimeMapper {

    public Showtime fromDto(ShowtimeRequestDto dto){
        return Showtime.builder()
                .movieId(dto.getMovieId())
                .theatreId(dto.getTheatreId())
                .showtime(dto.getShowtime())
                .totalSeats(dto.getTotalSeats())
                .availableSeats(dto.getAvailableSeats() != null ? dto.getAvailableSeats() : 0)
                .build();
    }

    public ShowtimeResponseDto fromShowtime(Showtime showtime){
        return ShowtimeResponseDto.builder()
                .id(showtime.getId())
                .startTime(showtime.getShowtime().atOffset(ZoneOffset.UTC))
                .movieId(showtime.getMovieId())
                .theatreId(showtime.getTheatreId())
                .totalSeats(showtime.getTotalSeats())
                .availableSeats(showtime.getAvailableSeats())
                .build();
    }


//    public List<ShowtimeResponseDto> toDtoList(List<Showtime> showtimes) {
//        return showtimes.stream()
//                .map(this::fromShowtime)
//                .collect(Collectors.toList());
//    }

    public List<ShowtimeResponseDto> toDtoList(List<Showtime> showtimes){
        List<ShowtimeResponseDto> response = new ArrayList<>();
        for (Showtime showtime : showtimes){
            ShowtimeResponseDto s = ShowtimeResponseDto.builder()
                    .id(showtime.getId())
                    .theatreId(showtime.getTheatreId())
                    .movieId(showtime.getMovieId())
                    .startTime(showtime.getShowtime().atOffset(ZoneOffset.UTC))
                    .totalSeats(showtime.getTotalSeats())
                    .availableSeats(showtime.getAvailableSeats())
                    .build();
            response.add(s);
        }
        return response;
    }
}
