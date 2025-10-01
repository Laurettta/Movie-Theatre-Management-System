package com.mtm.Movie.Theatre.Management.API.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Showtime {

    @Id
    private String id;
    private String movieId;
    private String theatreId;

    // store as LocalDateTime in DB
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime showtime;

    private int totalSeats;       // total seats for the showtime
    private int availableSeats;   // seats left for booking
}
