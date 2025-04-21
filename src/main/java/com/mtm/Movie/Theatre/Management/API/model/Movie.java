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
@Document(collection = "movies")
@Builder
public class Movie {

    @Id
    private String id;
    private String title;
    private String genre;
    private int duration;
    private String releaseDate;
}
