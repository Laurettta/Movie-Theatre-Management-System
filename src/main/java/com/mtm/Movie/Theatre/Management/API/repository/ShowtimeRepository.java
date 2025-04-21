package com.mtm.Movie.Theatre.Management.API.repository;

import com.mtm.Movie.Theatre.Management.API.model.Showtime;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShowtimeRepository extends MongoRepository<Showtime,String> {
    List<Showtime> findByMovieId(String movieId);

    List<Showtime> findByTheatreId(String theatreId);
}
