package com.mtm.Movie.Theatre.Management.API.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@Builder
public class Theatre {

    @Id
    private String id;
    private String name;
    private String location;
    private int seatingCapacity;
}
