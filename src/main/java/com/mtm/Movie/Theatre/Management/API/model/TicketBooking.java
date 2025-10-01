package com.mtm.Movie.Theatre.Management.API.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@Builder
public class TicketBooking {

    @Id
    private String id;
    private String userId;
    private String showtimeId;
    private int seats;
    private String movieId;
    private LocalDateTime bookingDate = LocalDateTime.now();
}
