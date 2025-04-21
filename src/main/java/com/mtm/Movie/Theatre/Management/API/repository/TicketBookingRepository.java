package com.mtm.Movie.Theatre.Management.API.repository;

import com.mtm.Movie.Theatre.Management.API.model.TicketBooking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TicketBookingRepository extends MongoRepository<TicketBooking, String> {
    List<TicketBooking> findByUserId(String userId);
}
