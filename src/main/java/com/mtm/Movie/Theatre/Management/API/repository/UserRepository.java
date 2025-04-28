package com.mtm.Movie.Theatre.Management.API.repository;

import com.mtm.Movie.Theatre.Management.API.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUserNameOrEmail(String username, String email);
    Optional<User> findByUserName(String username);
}
