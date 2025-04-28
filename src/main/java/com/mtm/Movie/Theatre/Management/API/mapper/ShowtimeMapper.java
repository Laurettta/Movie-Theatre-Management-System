package com.mtm.Movie.Theatre.Management.API.mapper;

import com.mtm.Movie.Theatre.Management.API.dto.request.ShowtimeRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.response.ShowtimeResponseDto;
import com.mtm.Movie.Theatre.Management.API.model.Showtime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ShowtimeMapper {

    public Showtime fromDto(ShowtimeRequestDto dto){
        return Showtime.builder()
                .movieId(dto.getMovieId())
                .theatreId(dto.getTheatreId())
                .showtime(dto.getShowtime())
                .build();
    }

    public ShowtimeResponseDto fromShowtime(Showtime showtime){
        return ShowtimeResponseDto.builder()
                .showtime(showtime.getId())
                .movieId(showtime.getMovieId())
                .theatreId(showtime.getTheatreId())
                .build();
    }

    public List<ShowtimeResponseDto> toDtoList(List<Showtime> showtimes){
        List<ShowtimeResponseDto> response = new ArrayList<>();
        for (Showtime showtime : showtimes){
            ShowtimeResponseDto s = ShowtimeResponseDto.builder()
                    .theatreId(showtime.getTheatreId())
                    .movieId(showtime.getMovieId())
                    .showtime(showtime.getId())
                    .build();
            response.add(s);
        }
        return response;
    }
}
