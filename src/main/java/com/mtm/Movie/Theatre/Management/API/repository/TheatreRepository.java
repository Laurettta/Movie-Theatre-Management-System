package com.mtm.Movie.Theatre.Management.API.repository;

import com.mtm.Movie.Theatre.Management.API.model.Theatre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TheatreRepository extends MongoRepository<Theatre,String> {
}
