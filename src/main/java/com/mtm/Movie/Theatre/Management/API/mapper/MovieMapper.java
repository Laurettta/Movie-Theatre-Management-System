package com.mtm.Movie.Theatre.Management.API.mapper;

import com.mtm.Movie.Theatre.Management.API.dto.MovieRequestDto;
import com.mtm.Movie.Theatre.Management.API.dto.MovieResponseDto;
import com.mtm.Movie.Theatre.Management.API.model.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

     public Movie fromDto(MovieRequestDto dto){
        return Movie.builder()
                .genre(dto.getGenre())
                .title(dto.getTitle())
                .releaseDate(dto.getReleaseDate())
                .duration(dto.getDuration())
                .build();
    }


    public MovieResponseDto fromMovie(Movie movie){
        return MovieResponseDto.builder()
                .id(movie.getId())
                .genre(movie.getGenre())
                .title(movie.getTitle())
                .releaseDate(movie.getReleaseDate())
                .duration(movie.getDuration())
                .build();
    }

    public List<MovieResponseDto> toDtoList(List<Movie> movies){
        List<MovieResponseDto> response = new ArrayList<>();
        for (Movie movie : movies){
            MovieResponseDto m = MovieResponseDto.builder()
                    .id(movie.getId())
                    .genre(movie.getGenre())
                    .title(movie.getTitle())
                    .releaseDate(movie.getReleaseDate())
                    .duration(movie.getDuration())
                    .build();
            response.add(m);
        }
        return response;
    }
}
