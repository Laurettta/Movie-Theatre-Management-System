package com.mtm.Movie.Theatre.Management.API.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class Showtime {

    @Id
    private String id;
    private String movieId;
    private String theatreId;
    private String showtime;
    private int availableSeats;
}
